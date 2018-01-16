package me.okx.stratos.tokens.misc.nilad;

import me.okx.stratos.tokens.types.Nilad;
import me.okx.stratos.var.Variable;

public class Alias extends Nilad {
    private Variable alias;

    public Alias(Variable alias) {
        this.alias = alias;
    }

    @Override
    public Variable run() {
        return alias;
    }
}
