package me.okx.stratos.tokens.misc.dyad;

import me.okx.stratos.tokens.types.Dyad;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.Holder;

public class Concatenate extends Dyad {
    @Override
    public Variable run(Variable a, Variable b) {
        return new Holder<>(a.toString() + b.toString());
    }
}
