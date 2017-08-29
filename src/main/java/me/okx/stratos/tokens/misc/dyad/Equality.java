package me.okx.stratos.tokens.misc.dyad;

import me.okx.stratos.tokens.types.Dyad;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.BooleanHolder;

public class Equality extends Dyad {
    @Override
    public Variable run(Variable a, Variable b) {
        return new BooleanHolder(a.toString().equals(b.toString()));
    }
}
