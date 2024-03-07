import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import ForgotPassword from './ForgotPassword';

const mock = new MockAdapter(axios);

jest.mock('axios');

describe('ForgotPassword component', () => {
  beforeEach(() => {
    mock.reset(); // Reset the mock adapter before each test
  });

  it('renders ForgotPassword component', () => {
    render(
      <MemoryRouter>
        <ForgotPassword />
      </MemoryRouter>
    );

    expect(screen.getByText('Forgot your password?')).toBeInTheDocument();
    // Add more assertions as needed
  });

  it('validates email format on form submit', async () => {
    render(
      <MemoryRouter>
        <ForgotPassword />
      </MemoryRouter>
    );

    const submitButton = screen.getByText('Submit');
    fireEvent.click(submitButton);

    await waitFor(() => {
      expect(screen.getByText('Please enter a valid email')).toBeInTheDocument();
    });
  });

  it('submits form with a valid email and receives a success response', async () => {
    mock.onPost('http://localhost:8080/api/GxHospital/Users/send-reset-password').reply(200, {
      status: 200,
      data: 'Reset email sent successfully.',
    });

    render(
      <MemoryRouter>
        <ForgotPassword />
      </MemoryRouter>
    );

    const emailInput = screen.getByPlaceholderText('Email');
    fireEvent.change(emailInput, { target: { value: 'test@example.com' } });

    const submitButton = screen.getByText('Submit');
    fireEvent.click(submitButton);

    await waitFor(() => {
      expect(screen.getByText('An email has been sent to you.')).toBeInTheDocument();
    });

    // Add more assertions for success case
  });

  it('handles error when submitting form with an invalid email', async () => {
    render(
      <MemoryRouter>
        <ForgotPassword />
      </MemoryRouter>
    );

    const emailInput = screen.getByPlaceholderText('Email');
    fireEvent.change(emailInput, { target: { value: 'invalidemail' } });

    const submitButton = screen.getByText('Submit');
    fireEvent.click(submitButton);

    await waitFor(() => {
      expect(screen.getByText('Your account is not registered with us')).toBeInTheDocument();
    });

    // Add more assertions for error case
  });
});
