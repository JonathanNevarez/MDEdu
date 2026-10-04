import { beforeEach, afterEach, describe, expect, it, vi } from 'vitest';
vi.mock('../../src/features/telemetry/client', () => ({
  ensureSession: vi.fn(async () => ({id: 'session-fixture', status: 'ACTIVE'})),
  studentFetch: (_student: string, path: string, init?: RequestInit) => fetch(`http://127.0.0.1:8080/api${path}`, init),
}));
const empty = { levels: [
  { levelId: 'SEQUENCES', conceptId: 'SEQUENCES', unlocked: true, completed: false, masteryScore: 0, attemptCount: 0 },
  { levelId: 'VARIABLES', conceptId: 'VARIABLES', unlocked: false, completed: false, masteryScore: 0, attemptCount: 0 },
] };
function response(body: unknown, status = 200) { return new Response(JSON.stringify(body), { status, headers: { 'Content-Type': 'application/json' } }); }
beforeEach(() => { localStorage.clear(); vi.resetModules(); });
afterEach(() => vi.unstubAllGlobals());
describe('Identidad y progreso del servidor', () => {
  it('uses authenticated identity and never trusts a spoofed local UUID', async () => {
    localStorage.setItem('mdedu.student.id.v1','victim');
    const fetch=vi.fn().mockResolvedValueOnce(response({studentId:'A',studentCode:'EST-001',authenticated:true})).mockResolvedValueOnce(response(empty));vi.stubGlobal('fetch',fetch);
    const {ensureStudent}=await import('../../src/features/game/learning');
    expect(await ensureStudent()).toEqual({studentId:'A',progress:empty});expect(fetch.mock.calls[0]![0]).toMatch(/auth\/student\/me$/);expect(fetch.mock.calls[1]![0]).toMatch(/students\/A\/progress$/);
  });
  it('does not create a student when unauthenticated', async () => {
    const fetch=vi.fn().mockResolvedValue(response({},401));vi.stubGlobal('fetch',fetch);
    const {ensureStudent}=await import('../../src/features/game/learning');await expect(ensureStudent()).rejects.toThrow();expect(fetch).toHaveBeenCalledTimes(1);
  });
  it('does not claim historical data or create a replacement on 404', async () => {
    const fetch=vi.fn().mockResolvedValueOnce(response({studentId:'A',studentCode:'EST-001',authenticated:true})).mockResolvedValueOnce(response({},404));vi.stubGlobal('fetch',fetch);
    const {ensureStudent}=await import('../../src/features/game/learning');await expect(ensureStudent()).rejects.toThrow();expect(fetch).toHaveBeenCalledTimes(2);
  });
  it('concurrent mounts share the authenticated lookup', async () => {
    const fetch=vi.fn().mockResolvedValueOnce(response({studentId:'A',studentCode:'EST-001',authenticated:true})).mockResolvedValueOnce(response(empty));vi.stubGlobal('fetch',fetch);
    const {ensureStudent}=await import('../../src/features/game/learning');const [a,b]=await Promise.all([ensureStudent(),ensureStudent()]);expect(a).toEqual(b);expect(fetch).toHaveBeenCalledTimes(2);
  });
  it('removes legacy progress without converting it into mastery', async () => {
    localStorage.setItem('mdedu.game.progress.v1','forged');vi.stubGlobal('fetch',vi.fn().mockResolvedValueOnce(response({studentId:'A',studentCode:'EST-001',authenticated:true})).mockResolvedValueOnce(response(empty)));
    const {ensureStudent}=await import('../../src/features/game/learning');expect((await ensureStudent()).progress).toEqual(empty);expect(localStorage.getItem('mdedu.game.progress.v1')).toBeNull();
  });
  it('network failure does not create an identity', async () => {
    const fetch=vi.fn().mockRejectedValue(new Error('offline'));vi.stubGlobal('fetch',fetch);
    const {ensureStudent}=await import('../../src/features/game/learning');await expect(ensureStudent()).rejects.toThrow('No se pudo conectar');expect(fetch).toHaveBeenCalledTimes(1);
  });
  it('locked/unlocked/completed se toman exclusivamente del servidor', async () => {
    const { serverLevelStatus } = await import('../../src/features/game/learning'); expect(serverLevelStatus('SEQUENCES', empty)).toBe('UNLOCKED'); expect(serverLevelStatus('VARIABLES', empty)).toBe('LOCKED');
    expect(serverLevelStatus('LOOPS', { levels: [{ ...empty.levels[0]!, levelId: 'LOOPS', completed: true }] })).toBe('COMPLETED');
  });
  it('envía intento único con tiempo y cero pistas; recibe progreso tras éxito', async () => {
    const next = { levels: empty.levels.map(l => ({ ...l, unlocked: true, completed: l.levelId === 'SEQUENCES' })) };
    const fetch = vi.fn().mockResolvedValue(response({ attemptId: 'one', execution: { evaluation: { activityPassed: true } }, progress: next }, 201)); vi.stubGlobal('fetch', fetch);
    const { submitAttempt } = await import('../../src/features/game/learning'); const program = { contractVersion: 1 as const, name: 'p', statements: [] };
    expect((await submitAttempt('A', 'SEQUENCES', program, 15000, new AbortController().signal)).progress).toEqual(next);
    expect(fetch).toHaveBeenCalledTimes(1); expect(JSON.parse(fetch.mock.calls[0]![1].body)).toEqual({ studentId: 'A', levelId: 'SEQUENCES', program, resolutionTimeMs: 15000, hintCount: 0 });
  });
  it('respuesta fallida conserva bloqueo según progreso backend', async () => {
    vi.stubGlobal('fetch', vi.fn().mockResolvedValue(response({ attemptId: 'one', execution: { evaluation: { activityPassed: false } }, progress: empty }, 201)));
    const { submitAttempt, serverLevelStatus } = await import('../../src/features/game/learning'); const r = await submitAttempt('A', 'SEQUENCES', { contractVersion: 1, name: 'p', statements: [] }, 1, new AbortController().signal);
    expect(serverLevelStatus('VARIABLES', r.progress)).toBe('LOCKED');
  });
});
