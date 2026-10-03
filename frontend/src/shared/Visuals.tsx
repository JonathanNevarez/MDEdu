import type { CSSProperties } from 'react';
// One local stroke family; the surrounding control supplies the accessible name.
const paths = {
  route: 'M5 5h5v5H5z M14 14h5v5h-5z M15 5h4v4 M5 15v4h4 M10 7h4a3 3 0 0 1 3 3v4',
  sequence: 'M4 5h6v6H4z M14 13h6v6h-6z M10 8h5v5 M6 15v4h4',
  variable: 'M4 7l8-4 8 4-8 4z M4 7v10l8 4 8-4V7 M12 11v10',
  branch: 'M12 3v5 M12 8l-7 5v7 M12 8l7 5v7 M2 17l3 3 3-3 M16 17l3 3 3-3',
  loop: 'M19 8a8 8 0 0 0-13-2L3 9 M3 4v5h5 M5 16a8 8 0 0 0 13 2l3-3 M16 15h5v5',
  lock: 'M7 10V7a5 5 0 0 1 10 0v3 M5 10h14v11H5z M12 14v3', check: 'M5 12l4 4L19 6', arrow: 'M4 12h16 M14 6l6 6-6 6',
  spark: 'M12 2l3 7 7 3-7 3-3 7-3-7-7-3 7-3z', flask: 'M9 3h6 M10 3v7L4 19q-1 2 2 2h12q3 0 2-2l-6-9V3 M7 16h10',
  book: 'M12 5v16 M12 5Q7 1 2 4v15q5-3 10 2 5-5 10-2V4q-5-3-10 1', flag: 'M5 22V3 M5 3c5-4 9 4 15 0v10c-6 4-10-4-15 0',
  info: 'M12 11v6 M12 7h.01 M22 12a10 10 0 1 1-20 0 10 10 0 0 1 20 0',
} as const;
export type IconName = keyof typeof paths;
export function Icon({name, className = ''}: {name: IconName; className?: string}) {
  return <svg className={`ui-icon ${className}`} viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.7" strokeLinecap="round" strokeLinejoin="round" aria-hidden="true"><path d={paths[name]}/></svg>;
}
export const conceptIcons: IconName[] = ['sequence', 'variable', 'branch', 'loop'];
// Original vector placeholder, isolated for replacement by a future approved Luma asset.
export function LumaPortrait({state = 'IDLE'}: {state?: string}) {
  return <svg className="luma-portrait" data-mood={state} viewBox="0 0 160 180" aria-hidden="true">
    <ellipse cx="80" cy="166" rx="49" ry="8" fill="#244e5a" opacity=".12"/>
    <path d="M43 157l5-47q3-20 20-25l34 7q20 5 17 32l-4 33-17 3-4-28-25 1-4 28z" fill="#f8f3df" stroke="#58776e" strokeWidth="3"/>
    <path d="M54 77L43 29q-2-15 7-16 10-1 15 45 M83 59l8-42q3-14 12-11 8 3 4 16l-9 56" fill="#faf6e7" stroke="#58776e" strokeWidth="3"/>
    <path d="M52 25l7 29 M99 18l-7 35" stroke="#d9c4a2" strokeWidth="5" strokeLinecap="round"/>
    <path d="M46 64q8-17 30-17 24-2 30 21l-4 35q-2 15-25 15-28 0-30-20z" fill="#fffbed" stroke="#58776e" strokeWidth="3"/>
    <path d="M51 60q7-15 17-11 8-9 15 0 9-3 15 7" fill="#fffbed" stroke="#58776e" strokeWidth="3" strokeLinecap="round"/>
    <ellipse cx="77" cy="90" rx="19" ry="16" fill="#e8dec7"/>
    {state === 'SUCCESS' ? <path d="M55 76q6-7 12 0 M88 76q6-7 12 0" fill="none" stroke="#244b4c" strokeWidth="3" strokeLinecap="round"/> : <g fill="#244b4c"><ellipse cx="61" cy="76" rx="3" ry="4"/><ellipse cx="94" cy="76" rx="3" ry="4"/></g>}
    <path d="M73 86h8l-4 5z" fill="#47675c"/><path d="M77 92v4m-6 0q6 6 12 0" fill="none" stroke="#47675c" strokeWidth="2" strokeLinecap="round"/>
    <path d="M48 106q26 17 54 0l3 12q-28 15-59 0z" fill="#267c8b"/><path d="M88 119l-3 24 15-5-4-21" fill="#267c8b"/><path d="M50 112l9 7 9-3 9 7 9-4 11 2" fill="none" stroke="#f3d580" strokeWidth="3"/>
  </svg>;
}
export function Landscape({className = ''}: {className?: string}) {
  return <svg className={`landscape ${className}`} viewBox="0 0 1200 440" preserveAspectRatio="xMidYMid slice" aria-hidden="true">
    <rect width="1200" height="440" fill="#d8eff6"/><circle cx="1020" cy="70" r="43" fill="#fff4c9"/>
    <g fill="#fff" opacity=".7"><path d="M95 71q-2-19 22-19 14-35 46-7 30-3 30 26z"/><path d="M742 55q6-17 26-13 19-29 39-6 27-4 30 19z"/></g>
    <path d="M0 217Q150 90 300 195T620 155T950 176T1200 138V440H0Z" fill="#bedcce"/>
    <path d="M0 271Q170 146 335 246T660 218T1000 216T1200 246V440H0Z" fill="#b4d6ab"/>
    <path d="M0 390Q260 350 410 365T746 351Q948 347 1200 306V440H0Z" fill="#97c4a0"/>
    <path d="M790 226q-86 38-28 73t-24 56q-64 35-26 85h219q-182-53-104-89t-4-60q-68-29 9-65" fill="#acdce7"/>
    <path d="M803 296q-52 16-15 35 M748 400l53 12" fill="none" stroke="#e6f6f9" strokeWidth="3"/>
    <g stroke="#729773" strokeWidth="5" fill="#759e78"><path d="M71 299v49 M52 310q-16-36 11-45 24-16 39 19 9 31-26 29z"/><path d="M1124 245v40 M1106 251q-18-31 9-41 30-13 36 22 6 27-31 24z"/></g>
    <g fill="#f7f5d7"><circle cx="103" cy="374" r="3"/><circle cx="112" cy="370" r="3"/><circle cx="1076" cy="357" r="3"/><circle cx="1085" cy="362" r="3"/></g>
  </svg>;
}
export function LoadingState({text = 'Preparando tu aventura…'}: {text?: string}) {
  return <div className="loading-state" role="status"><LumaPortrait/><div><p className="eyebrow">Mi primera programación</p><p>{text}</p><span className="loading-track" aria-hidden="true"/></div></div>;
}
export function MasteryBar({value, label}: {value: number; label: string}) {
  return <div className="mastery-meter"><span>{label}<strong>{(value * 100).toFixed(1)} %</strong></span><meter min={0} max={1} value={value} aria-label={label} style={{'--mastery': `${Math.max(0, Math.min(1, value)) * 100}%`} as CSSProperties}>{value}</meter></div>;
}
