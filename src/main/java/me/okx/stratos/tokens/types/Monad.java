package me.okx.stratos.tokens.types;

import me.okx.stratos.var.Variable;

public abstract class Monad extends Token {
    public Monad() {
        super(1);
    }

    public abstract Variable run(Variable a);
}
