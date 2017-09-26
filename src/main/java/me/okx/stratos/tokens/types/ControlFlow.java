package me.okx.stratos.tokens.types;

import me.okx.stratos.TokenManager;
import me.okx.stratos.tokens.input.StorageInput;
import me.okx.stratos.var.Variable;

import java.util.Optional;

public abstract class ControlFlow extends Token {
    public ControlFlow() {
        super(-2);
    }

    public abstract Optional<Variable> run(String left, String right, TokenManager eval, StorageInput input);
}
