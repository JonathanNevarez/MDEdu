import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter } from 'react-router-dom';
import { describe, expect, it } from 'vitest';
import { App } from '../../src/app/App';

describe('Navegación inicial', () => {
  it('presenta el inicio e informa que las actividades aún no están disponibles', () => {
    render(<MemoryRouter><App /></MemoryRouter>);

    expect(screen.getByRole('heading', {
      level: 1,
      name: 'Un espacio para aprender lógica de programación',
    })).toBeVisible();
    expect(screen.getByText(/Las actividades todavía no están disponibles/)).toBeVisible();
    expect(screen.getByRole('link', { name: 'Inicio' }))
      .toHaveAttribute('aria-current', 'page');
  });

  it('permite volver al inicio al abrir una ruta inexistente', async () => {
    const user = userEvent.setup();
    render(<MemoryRouter initialEntries={['/pagina-inexistente']}><App /></MemoryRouter>);

    expect(screen.getByRole('heading', { name: 'No encontramos esta página' })).toBeVisible();
    await user.click(screen.getByRole('link', { name: 'Volver al inicio' }));

    expect(screen.getByRole('heading', {
      name: 'Un espacio para aprender lógica de programación',
    })).toBeVisible();
    expect(screen.queryByRole('heading', { name: 'No encontramos esta página' }))
      .not.toBeInTheDocument();
  });
});
