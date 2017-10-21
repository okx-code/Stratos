package me.okx.stratos.tokens.misc.monad;

import me.okx.stratos.tokens.types.Monad;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.DoubleHolder;

public class Negate extends Monad {
    @Override
    public Variable run(Variable a) {
        return new DoubleHolder(-new DoubleHolder(a.toString()).getValue());
    }
}
