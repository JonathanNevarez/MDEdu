import { beforeEach, afterEach, describe, expect, it, vi } from 'vitest';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import catalogData from '../../../backend/src/main/resources/game/levels.v1.json';
import type { Catalog, TraceEvent } from '../../src/features/game/types';
import { completeLevel, emptyProgress, levelStatus, progressKey, readProgress, saveProgress } from '../../src/features/game/progress';
import { initialState, replayState } from '../../src/features/game/replay';
import { gameToolbox } from '../../src/features/game/GameEditor';
import { AdventurePage } from '../../src/features/game/AdventurePage';
const catalog = catalogData as Catalog;
beforeEach(() => localStorage.clear());
afterEach(() => vi.unstubAllGlobals());
describe('Aventura provisional', () => {
  it('desbloquea por prerequisites y conserva progreso tras recargar', () => {
    let progress = emptyProgress();
    expect(catalog.levels.map(l => levelStatus(l, progress))).toEqual(['UNLOCKED', 'LOCKED', 'LOCKED', 'LOCKED']);
    for (const level of catalog.levels) {
      expect(levelStatus(level, progress)).toBe('UNLOCKED');
      progress = completeLevel(progress, level.id); expect(saveProgress(progress)).toBe(true);
      expect(readProgress()).toEqual(progress);
    }
    expect(completeLevel(progress, 'LOOPS').completedLevelIds).toHaveLength(4);
  });
  it('no depende del orden numérico y exige todos los requisitos', () => {
    const level = { ...catalog.levels[0]!, order: 999, prerequisiteLevelIds: ['a', 'b'] };
    expect(levelStatus(level, { version: 1, completedLevelIds: ['a'] })).toBe('LOCKED');
    expect(levelStatus(level, { version: 1, completedLevelIds: ['b', 'a'] })).toBe('UNLOCKED');
  });
  it('rechaza almacenamiento corrupto o versión incompatible', () => {
    for (const text of ['broken', '{"version":2,"completedLevelIds":[]}', '{"version":1,"completedLevelIds":[4]}']) {
      localStorage.setItem(progressKey, text); expect(readProgress()).toEqual(emptyProgress());
    }
  });
  it('replay recorre exactamente snapshots recibidos, sin calcular posiciones', () => {
    const initial = initialState(catalog.levels[0]!.worldConfig);
    const trace: TraceEvent[] = [
      { index: 0, type: 'PROGRAM_STARTED', detail: 'Inicio', state: initial },
      { index: 1, type: 'MOVE', detail: 'Estado recibido', state: { ...initial, playerPosition: { x: 4, y: 3 } } },
      { index: 2, type: 'VARIABLE_ASSIGNED', detail: 'Valor', state: { ...initial, variables: [{ id: 'v1', name: 'x', type: 'INTEGER', value: 9 }] } },
    ];
    trace.forEach((event, index) => expect(replayState(trace, index, initial)).toBe(event.state));
    expect(replayState(trace, -1, initial)).toBe(initial);
  });
  it('la toolbox respeta cada grupo y no anticipa ciclos', () => {
    expect(gameToolbox(catalog.levels[0]!.allowedBlockGroups).contents.map(c => c.name)).toEqual(['Movimiento']);
    expect(gameToolbox(catalog.levels[2]!.allowedBlockGroups).contents.flatMap(c => c.contents).some(b => b.type === 'mdedu_repeat')).toBe(false);
    expect(gameToolbox(catalog.levels[3]!.allowedBlockGroups).contents.flatMap(c => c.contents).some(b => b.type === 'mdedu_repeat')).toBe(true);
  });
  it('el clic bloqueado explica el requisito sin abrir la actividad', async () => {
    vi.stubGlobal('fetch', vi.fn(async (url: string) => ({ ok: true, json: async () => url.endsWith('/api/game/levels') ? catalog : url.endsWith('/api/auth/student/me') ? { studentId: 'student',studentCode:'EST-001',authenticated:true } : { levels: catalog.levels.map((l, i) => ({ levelId: l.id, completed: false, unlocked: i === 0 })) } })));
    render(<MemoryRouter initialEntries={['/aventura']}><Routes><Route path="/aventura" element={<AdventurePage />} /></Routes></MemoryRouter>);
    const locked = await screen.findByRole('button', { name: /Variables:.*Bloqueado/ });
    await userEvent.click(locked);
    expect(screen.getByRole('status')).toHaveTextContent('Completa el nivel anterior para continuar.');
    expect(screen.getByRole('heading', { name: 'Mi primera programación' })).toBeVisible();
    expect(screen.queryByTestId('game-blockly')).not.toBeInTheDocument();
  });
  it('una URL directa no abre un nivel bloqueado', async () => {
    vi.stubGlobal('fetch', vi.fn(async (url: string) => ({ ok: true, json: async () => url.endsWith('/api/game/levels') ? catalog : url.endsWith('/api/auth/student/me') ? { studentId: 'student',studentCode:'EST-001',authenticated:true } : { levels: catalog.levels.map((l, i) => ({ levelId: l.id, completed: false, unlocked: i === 0 })) } })));
    render(<MemoryRouter initialEntries={['/aventura/LOOPS']}><Routes><Route path="/aventura/:levelId" element={<AdventurePage />} /></Routes></MemoryRouter>);
    expect(await screen.findByRole('heading', { name: 'Nivel bloqueado' })).toBeVisible();
    expect(screen.queryByTestId('game-blockly')).not.toBeInTheDocument();
  });
});
