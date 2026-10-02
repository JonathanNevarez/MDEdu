import type { WorldConfig, WorldState, Position } from './types';
const same = (a: Position, b: Position) => a.x === b.x && a.y === b.y;
const angle = { NORTH: 0, EAST: 90, SOUTH: 180, WEST: 270 };
export function WorldBoard({ world, state }: { world: WorldConfig; state: WorldState }) {
  return <div className="world-board" role="img" aria-label={`GridWorld: personaje en ${state.playerPosition.x}, ${state.playerPosition.y}, dirección ${state.playerDirection}`}>
    <svg viewBox={`0 0 ${world.width * 56} ${world.height * 56}`} aria-hidden="true">
      {Array.from({ length: world.width * world.height }, (_, i) => {
        const p = { x: i % world.width, y: Math.floor(i / world.width) };
        const obstacle = world.obstacles.some(o => same(o, p)); const door = state.doors.find(d => same(d.position, p));
        return <g key={i} transform={`translate(${p.x * 56},${p.y * 56})`}>
          <rect x="2" y="2" width="52" height="52" rx="9" fill={obstacle ? '#536f65' : '#f6efce'} />
          {obstacle && <path d="M10 40 L21 13 L33 29 L41 18 L49 40Z" fill="#a5bd9a" />}
          {same(world.goalPosition, p) && <><path d="M22 44V10L44 18L22 26" fill="#e56746" stroke="#713b35" strokeWidth="3" /><circle cx="22" cy="44" r="3" fill="#713b35" /></>}
          {state.keys.some(k => same(k, p)) && <g fill="none" stroke="#ac6900" strokeWidth="5"><circle cx="20" cy="20" r="8" /><path d="M26 26L40 40M35 35L41 29" /></g>}
          {door && <g stroke={door.open ? '#488568' : '#844626'} strokeWidth="4" fill={door.open ? '#c3dbb4' : '#c38c55'}><path d={door.open ? 'M15 45V10L30 15V49Z' : 'M14 45V11H42V45Z'} /><circle cx="33" cy="30" r="2" /></g>}
        </g>;
      })}
      <g className="world-player" style={{ transform: `translate(${state.playerPosition.x * 56}px, ${state.playerPosition.y * 56}px)` }}>
        <circle cx="28" cy="30" r="19" fill="#253d69" stroke="white" strokeWidth="3" />
        <g transform={`rotate(${angle[state.playerDirection]} 28 28)`}><path d="M28 12L36 26H20Z" fill="#ffd478" /></g>
        <circle cx="23" cy="34" r="2" fill="white" /><circle cx="33" cy="34" r="2" fill="white" />
      </g>
    </svg>
    <p className="world-position">Posición: {state.playerPosition.x}, {state.playerPosition.y} · {state.playerDirection} {state.hasKey && '· Llave recogida'}</p>
  </div>;
}
