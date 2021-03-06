package me.okx.stratos.tokens.misc.monad;

import me.okx.stratos.tokens.types.Monad;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.Holder;

public class Extend extends Monad {
    @Override
    public Variable run(Variable a) {
        return new Holder<>(a.toString() + a.toString().charAt(a.toString().length() - 1));
    }
}
