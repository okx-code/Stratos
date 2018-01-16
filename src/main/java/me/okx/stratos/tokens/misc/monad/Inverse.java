package me.okx.stratos.tokens.misc.monad;

import me.okx.stratos.tokens.types.Monad;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.DoubleHolder;

public class Inverse extends Monad {
    @Override
    public Variable run(Variable a) {
        return new DoubleHolder(1D/new DoubleHolder(a.toString()).getValue());
    }
}
