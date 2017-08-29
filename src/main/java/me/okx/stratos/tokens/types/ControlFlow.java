package me.okx.stratos.tokens.types;

import me.okx.stratos.var.Variable;

public abstract class ControlFlow extends Token {
    public ControlFlow() {
        super(-1);
    }

    public abstract boolean check(Variable a);
}
