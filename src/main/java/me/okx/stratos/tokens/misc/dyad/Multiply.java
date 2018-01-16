package me.okx.stratos.tokens.misc.dyad;

import me.okx.stratos.tokens.types.Dyad;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.DoubleHolder;

public class Multiply extends Dyad {
    @Override
    public Variable run(Variable a, Variable b) {
        return new DoubleHolder(new DoubleHolder(a.toString()).getValue() *
                new DoubleHolder(b.toString()).getValue());
    }
}
