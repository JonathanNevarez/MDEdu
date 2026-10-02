import type { TraceEvent, WorldConfig, WorldState } from './types';
export function initialState(world: WorldConfig): WorldState {
  return { playerPosition: world.playerPosition, playerDirection: world.playerDirection, hasKey: false,
    keys: world.keys, doors: world.doors.map(position => ({ position, open: false })), variables: [], atGoal: false };
}
/** Select received snapshots only: no interpreter or movement logic in the client. */
export function replayState(trace: TraceEvent[], index: number, initial: WorldState): WorldState {
  return trace[index]?.state ?? initial;
}
