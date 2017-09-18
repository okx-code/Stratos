package me.okx.stratos.tokens.misc.monad;

import me.okx.stratos.tokens.types.Monad;
import me.okx.stratos.var.Variable;

public class Print extends Monad {
    @Override
    public Variable run(Variable a) {
        System.out.println(a);
        return null;
    }
}
