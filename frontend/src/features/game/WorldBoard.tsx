import {Landscape} from '../../shared/Visuals';
import type { WorldConfig, WorldState, Position } from './types';
const same = (a: Position, b: Position) => a.x === b.x && a.y === b.y;
const angle = { NORTH: 0, EAST: 90, SOUTH: 180, WEST: 270 };
export function WorldBoard({ world, state }: { world: WorldConfig; state: WorldState }) {
  return <div className="world-board" role="img" aria-label={`GridWorld: personaje en ${state.playerPosition.x}, ${state.playerPosition.y}, dirección ${state.playerDirection}`}>
    <div className="world-scenery"><Landscape/></div>
    <svg viewBox={`0 0 ${world.width * 56} ${world.height * 56}`} aria-hidden="true">
      {Array.from({ length: world.width * world.height }, (_, i) => {
        const p = { x: i % world.width, y: Math.floor(i / world.width) };
        const obstacle = world.obstacles.some(o => same(o, p)); const door = state.doors.find(d => same(d.position, p));
        return <g key={i} transform={`translate(${p.x * 56},${p.y * 56})`}>
          <rect x="2" y="2" width="52" height="52" rx="10" stroke="#E6D9B5" strokeWidth="1" fill={obstacle ? '#77AF73' : '#F8ECCB'} />
          {obstacle && <g><ellipse cx="29" cy="45" rx="21" ry="6" fill="#326B4940"/><path d="M27 43V23" stroke="#806548" strokeWidth="7"/><path d="M7 33L20 11L29 17L36 7L50 34Z" fill="#23895A"/><path d="M15 27L22 14L27 20M30 24L37 10L44 26" fill="#69BB70"/></g>}
          {same(world.goalPosition, p) && <><path d="M22 44V10L44 18L22 26" fill="#f0c85d" stroke="#7c672c" strokeWidth="3" /><circle cx="22" cy="44" r="3" fill="#7c672c" /></>}
          {state.keys.some(k => same(k, p)) && <g fill="none" stroke="#ac6900" strokeWidth="5"><circle cx="20" cy="20" r="8" /><path d="M26 26L40 40M35 35L41 29" /></g>}
          {door && <g stroke={door.open ? '#488568' : '#844626'} strokeWidth="4" fill={door.open ? '#c3dbb4' : '#c38c55'}><path d={door.open ? 'M15 45V10L30 15V49Z' : 'M14 45V22Q14 7 28 7Q42 7 42 22V45Z'} /><circle cx="33" cy="30" r="2" /></g>}
        </g>;
      })}
      <g className="world-player" style={{ transform: `translate(${state.playerPosition.x * 56}px, ${state.playerPosition.y * 56}px)` }}>
        <ellipse cx="28" cy="47" rx="17" ry="5" fill="#234e4a40"/>
        <path d="M20 39L19 48M34 39L36 48" stroke="#173F76" strokeWidth="6" strokeLinecap="round"/>
        <rect x="16" y="25" width="25" height="19" rx="7" fill="#287EC8" stroke="#15578C" strokeWidth="2"/>
        <path d="M18 32L13 39M39 32L43 39" stroke="#F0BB84" strokeWidth="5" strokeLinecap="round"/>
        <circle cx="28" cy="20" r="12" fill="#F0BD89"/>
        <path d="M16 21Q11 9 21 7L20 3L28 7Q42 4 41 19L34 13L23 16L19 23Z" fill="#5F4134"/>
        <circle cx="25" cy="21" r="1.4" fill="#2D3847"/><circle cx="33" cy="21" r="1.4" fill="#2D3847"/>
        <path d="M26 27Q29 29 32 26" fill="none" stroke="#925C40" strokeWidth="1.5"/>
        <g transform={`rotate(${angle[state.playerDirection]} 28 28)`}><path d="M28 0L33 7H23Z" fill="#174891" stroke="white" strokeWidth="1.5"/></g>
      </g>
    </svg>
    <p className="world-position">Posición: {state.playerPosition.x}, {state.playerPosition.y} · {state.playerDirection} {state.hasKey && '· Llave recogida'}</p>
  </div>;
}
