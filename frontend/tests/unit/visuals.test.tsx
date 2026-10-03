import {cleanup, render, screen} from '@testing-library/react';
import {afterEach, expect, it} from 'vitest';
import {MasteryBar, LoadingState} from '../../src/shared/Visuals';
import {LumaTutor} from '../../src/features/adaptive/AdaptiveRenderer';
afterEach(cleanup);
it('presents the backend mastery value accessibly without inventing categories',()=>{
 render(<MasteryBar label="Dominio del concepto" value={.735}/>);
 const meter=screen.getByRole('meter',{name:'Dominio del concepto'});
 expect(meter).toHaveAttribute('value','0.735');expect(screen.getByText('73.5 %')).toBeVisible();
});
it('keeps loading announced without a fictitious numerical progress',()=>{
 render(<LoadingState text="Preparando tu reto…"/>);
 expect(screen.getByRole('status')).toHaveTextContent('Preparando tu reto…');
 expect(screen.queryByRole('progressbar')).toBeNull();
});
it('honors hidden tutor mode and communicates success in text',()=>{
 const {rerender}=render(<LumaTutor mode="HIDDEN" success={false} message={null}/>);
 expect(screen.queryByRole('region',{name:'Luma'})).toBeNull();
 rerender(<LumaTutor mode="GUIDE" success message={null}/>);
 expect(screen.getByRole('region',{name:'Luma'})).toHaveAttribute('data-state','SUCCESS');
 expect(screen.getByText('¡Lo lograste!')).toBeVisible();
});
