package me.okx.stratos.tokens.input;

import me.okx.stratos.tokens.types.Nilad;
import me.okx.stratos.var.Variable;

public class FirstInput extends Nilad {
    private InputManager inputManager;

    public FirstInput(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    @Override
    public Variable run() {
        return inputManager.getInput(0);
    }
}
