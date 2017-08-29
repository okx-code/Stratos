package me.okx.stratos.tokens.types;

import me.okx.stratos.var.Variable;

public abstract class Nilad extends Token {
    public Nilad() {
        super(0);
    }

    public abstract Variable run();
}
