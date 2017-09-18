package me.okx.stratos.tokens.input;

import me.okx.stratos.tokens.types.Nilad;
import me.okx.stratos.var.Variable;

import java.util.Stack;

public class StorageInput extends Nilad {
    private Stack<Variable> data = new Stack<>();
    private InputManager inputManager;

    public StorageInput(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    @Override
    public Variable run() {
        if(data.size() > 0) {
            return data.pop();
        } else {
            return inputManager.getInput();
        }
    }

    public Variable get(int n) {
        if(data.size() > n) {
            return data.get(n);
        } else {
            return data.push(inputManager.getInput());
        }
    }

    public void push(Variable v) {
        data.push(v);
    }
}
