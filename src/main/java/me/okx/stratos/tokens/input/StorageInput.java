package me.okx.stratos.tokens.input;

import me.okx.stratos.tokens.types.Nilad;
import me.okx.stratos.var.Variable;

import java.util.Stack;

public class StorageInput extends Nilad implements Cloneable {
    private Stack<Variable> data = new Stack<>();
    private Stack<Variable> temp = new Stack<>();
    private InputManager inputManager;

    public StorageInput(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    @Override
    public Variable run() {
        if(temp.size() > 0) {
            return temp.pop();
        } else if(data.size() > 0) {
            return data.pop();
        } else {
            return inputManager.getInput();
        }
    }

    public void clearTemp() {
        temp.clear();
    }

    public void addTemp(Variable var) {
        temp.push(var);
    }

    public Variable get(int n) {
        if(temp.size() > n) {
            return temp.get(n);
        } else if(data.size() > n) {
            return data.get(n);
        } else {
            return data.push(inputManager.getInput());
        }
    }

    public void push(Variable v) {
        data.push(v);
    }

    public StorageInput clone() {
        StorageInput clone = new StorageInput(inputManager.clone());
        clone.data = (Stack<Variable>) data.clone();
        return clone;
    }
}
