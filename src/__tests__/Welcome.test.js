import React from "react";
import { fireEvent, render, screen } from "@testing-library/react";
import { MemoryRouter } from "react-router-dom";
import WelcomePage from "../Pages/WelcomePage";

test("renders Welcome Page", () => {
    render(
        <MemoryRouter>
            <WelcomePage />
        </MemoryRouter>
    );
});
test("selects Login option", () => {
    const { getByLabelText } = render(
        <MemoryRouter>
            <WelcomePage />
        </MemoryRouter>
    );
    const loginRadioButton = getByLabelText("Login");

    loginRadioButton.checked = true;

    fireEvent.change(loginRadioButton);

    expect(loginRadioButton.checked).toBe(true);
});
test("selects Register option", () => {
    const { getByLabelText } = render(
        <MemoryRouter>
            <WelcomePage />
        </MemoryRouter>
    );
    const registerRadioButton = getByLabelText("Register");

    registerRadioButton.checked = true;

    fireEvent.change(registerRadioButton);

    expect(registerRadioButton.checked).toBe(true);
});

test("handles Continue for Register", () => {
    const { getByLabelText } = render(
        <MemoryRouter>
            <WelcomePage />
        </MemoryRouter>
    );

    const registerRadioButton = getByLabelText("Register");

    registerRadioButton.checked = true;

    fireEvent.change(registerRadioButton);

    const continueButton = screen.getByRole("button", { name: /continue/i });

    fireEvent.click(continueButton);
});
test("handles Continue for Login", () => {
    const { getByLabelText } = render(
        <MemoryRouter>
            <WelcomePage />
        </MemoryRouter>
    );

    const loginRadioButton = getByLabelText("Login");

    loginRadioButton.checked = true;

    fireEvent.change(loginRadioButton);

    const continueButton = screen.getByRole("button", { name: /continue/i });

    fireEvent.click(continueButton);
});
