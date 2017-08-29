package me.okx.stratos.tokens.types;

import me.okx.stratos.var.Variable;

public abstract class Dyad extends Token {
    public Dyad() {
        super(2);
    }

    public abstract Variable run(Variable a, Variable b);
}
