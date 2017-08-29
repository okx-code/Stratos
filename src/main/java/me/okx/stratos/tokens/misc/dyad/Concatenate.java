package me.okx.stratos.tokens.misc.dyad;

import me.okx.stratos.tokens.types.Dyad;
import me.okx.stratos.var.StringHolder;
import me.okx.stratos.var.Variable;

public class Concatenate extends Dyad {
    @Override
    public Variable run(Variable a, Variable b) {
        return new StringHolder(a.toString() + b.toString());
    }
}
