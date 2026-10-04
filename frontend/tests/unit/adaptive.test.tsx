import { expect, it, vi } from 'vitest';
import { fireEvent, render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import { AdaptiveRenderer, CodePanel, HintPanel, LumaTutor, Transitioner } from '../../src/features/adaptive/AdaptiveRenderer';
import { safeConfiguration, type UiResponse } from '../../src/features/adaptive/types';
const standard = (): UiResponse => ({configuration: safeConfiguration('LOOPS'), attemptId: null, feedback: null,
 code: null, fingerprint: 'a', safeDefault: false, reason: null});
it('same renderer interprets configuration A and B without recompilation', () => {
 const a = standard(); const b: UiResponse = {...a, configuration: {...a.configuration, showCodePanel: true,
 generatedCodeVisible: true, hintPanelMode: 'EXPANDED', hintStage: 'PARTIAL_HELP', tutorMode: 'HINT'}, code: {available: true, text: 'move();', reason: null}};
 const props = {loading: false, error: false, success: false, onRepeat: vi.fn()};
 const {rerender} = render(<MemoryRouter><AdaptiveRenderer {...props} value={a}/></MemoryRouter>);
 expect(screen.queryByRole('region', {name: 'Código generado'})).not.toBeInTheDocument();
 expect(screen.queryByRole('region', {name: 'Pista'})).not.toBeInTheDocument();
 rerender(<MemoryRouter><AdaptiveRenderer {...props} value={b}/></MemoryRouter>);
 fireEvent.click(screen.getByText('Ver código'));
 expect(screen.getByRole('region', {name: 'Código generado'})).toBeVisible();
 expect(screen.getByRole('region', {name: 'Pista'})).toBeVisible();expect(screen.getByRole('region', {name: 'Luma'})).toHaveAttribute('data-state','HINT');
});
it('renders feedback and code as escaped text', () => {
 const payload = '<img src=x onerror="alert(1)"><script>bad()</script>';
 const {container} = render(<><CodePanel text={payload}/><HintPanel hintStage="SOCRATIC_QUESTION" message={payload} question={null} focus={null}/></>);
 expect(container.querySelector('script,img')).toBeNull();expect(screen.getAllByText(payload)).toHaveLength(2);
});
it('repeat only invokes explicit handler and advance uses backend target', () => {
 const a = standard();a.configuration.navigationMode='REPEAT';a.configuration.repeatCurrentActivity=true;const repeat=vi.fn();
 const props={loading:false,error:false,success:false,onRepeat:repeat};
 const {rerender}=render(<MemoryRouter><AdaptiveRenderer value={a} {...props}/></MemoryRouter>);
 expect(repeat).not.toHaveBeenCalled();fireEvent.click(screen.getByRole('button',{name:'Intentar de nuevo'}));expect(repeat).toHaveBeenCalledTimes(1);
 a.configuration={...a.configuration,navigationMode:'ADVANCE',repeatCurrentActivity:false,nextActivityId:'CONDITIONALS'};
 rerender(<MemoryRouter><AdaptiveRenderer value={a} {...props}/></MemoryRouter>);
 expect(screen.getByRole('link',{name:'Continuar'})).toHaveAttribute('href','/aprender/CONDITIONALS');
});
it('transition announces configuration differences with polite live region', () => {
 const a=safeConfiguration('LOOPS');const {rerender}=render(<Transitioner configuration={a}/>);
 rerender(<Transitioner configuration={{...a,showCodePanel:true,transitionMode:'SUBTLE'}}/>);
 expect(screen.getByRole('status')).toHaveAttribute('aria-live','polite');expect(screen.getByRole('status')).toHaveTextContent('vista de código');
});
it('Luma is bounded and reports success without a chat input', () => {
 const {container}=render(<LumaTutor mode="GUIDE" success message={null}/>);
 expect(screen.getByRole('region',{name:'Luma'})).toHaveAttribute('data-state','SUCCESS');expect(container.querySelector('input,textarea')).toBeNull();
});
it('safe default stays in the activity and has no advance link', () => {
 render(<MemoryRouter><AdaptiveRenderer value={standard()} loading={false} error success={false} onRepeat={vi.fn()}/></MemoryRouter>);
 expect(screen.queryByRole('link',{name:'Continuar'})).toBeNull();expect(screen.getByText(/Puedes seguir trabajando/)).toBeVisible();
});

it('feedback detail interprets configuration without changing stored text', () => {
 const a=standard();a.feedback={message:'Mensaje persistido',question:'Pregunta persistida',focus:'Foco',hintStage:'CONCEPTUAL_HINT'};
 const props={loading:false,error:false,success:false,onRepeat:vi.fn()};
 const {rerender}=render(<MemoryRouter><AdaptiveRenderer value={a} {...props}/></MemoryRouter>);
 expect(screen.getByText('Mensaje persistido')).toBeVisible();expect(screen.queryByText('Pregunta persistida')).toBeNull();
 const b={...a,configuration:{...a.configuration,feedbackDetailLevel:'DETAILED' as const}};
 rerender(<MemoryRouter><AdaptiveRenderer value={b} {...props}/></MemoryRouter>);
 expect(screen.getByText('Mensaje persistido')).toBeVisible();expect(screen.getByText('Pregunta persistida')).toBeVisible();
});
