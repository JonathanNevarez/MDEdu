import {render,screen} from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import {MemoryRouter} from 'react-router-dom';
import {afterEach,expect,it,vi} from 'vitest';
import {App} from '../../src/app/App';
afterEach(()=>vi.unstubAllGlobals());
it('redirects root to the real student login without obsolete navigation',async()=>{
 vi.stubGlobal('fetch',vi.fn().mockResolvedValue(new Response(null,{status:401})));
 render(<MemoryRouter><App/></MemoryRouter>);
 expect(await screen.findByRole('heading',{name:'Bienvenido'})).toBeVisible();
 expect(screen.queryByRole('link',{name:'Inicio'})).toBeNull();
 expect(screen.getByLabelText('Código de estudiante')).toBeVisible();
});
it('recovers an unknown route through Aprender and session access',async()=>{
 vi.stubGlobal('fetch',vi.fn().mockResolvedValue(new Response(null,{status:401})));
 render(<MemoryRouter initialEntries={['/no-existe']}><App/></MemoryRouter>);
 await userEvent.click(screen.getByRole('link',{name:'Volver a Aprender'}));
 expect(await screen.findByRole('heading',{name:'Bienvenido'})).toBeVisible();
});
