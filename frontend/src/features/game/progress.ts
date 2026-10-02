import type { Level } from './types';
export const progressKey = 'mdedu.game.progress.v1';
export interface Progress { version: 1; completedLevelIds: string[] }
export const emptyProgress = (): Progress => ({ version: 1, completedLevelIds: [] });
export function readProgress(): Progress {
  try {
    const value: unknown = JSON.parse(localStorage.getItem(progressKey) ?? 'null');
    if (value && typeof value === 'object' && 'version' in value && value.version === 1 &&
      'completedLevelIds' in value && Array.isArray(value.completedLevelIds) && value.completedLevelIds.every(id => typeof id === 'string'))
      return { version: 1, completedLevelIds: [...new Set<string>(value.completedLevelIds)] };
  } catch { /* Corrupt or unavailable storage starts a fresh provisional session. */ }
  return emptyProgress();
}
export function levelStatus(level: Level, progress: Progress) {
  if (progress.completedLevelIds.includes(level.id)) return 'COMPLETED';
  return level.prerequisiteLevelIds.every(id => progress.completedLevelIds.includes(id)) ? 'UNLOCKED' : 'LOCKED';
}
export function completeLevel(progress: Progress, id: string): Progress {
  return { version: 1, completedLevelIds: [...new Set([...progress.completedLevelIds, id])] };
}
export function saveProgress(progress: Progress): boolean {
  try { localStorage.setItem(progressKey, JSON.stringify(progress)); return true; } catch { return false; }
}
