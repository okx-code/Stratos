package me.okx.stratos.tokens.misc.monad;

import me.okx.stratos.tokens.types.Monad;
import me.okx.stratos.var.StringHolder;
import me.okx.stratos.var.Variable;

public class Extend extends Monad {
    @Override
    public Variable run(Variable a) {
        return new StringHolder(a.toString() + a.toString().charAt(a.toString().length() - 1));
    }
}
