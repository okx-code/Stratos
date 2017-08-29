package me.okx.stratos.tokens.input;

import me.okx.stratos.tokens.types.Nilad;
import me.okx.stratos.var.Variable;

public class Input extends Nilad {
    private InputManager inputManager;

    public Input(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    @Override
    public Variable run() {
        return inputManager.getInput();
    }
}
