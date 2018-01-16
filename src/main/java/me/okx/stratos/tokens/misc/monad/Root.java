package me.okx.stratos.tokens.misc.monad;

import me.okx.stratos.tokens.types.Monad;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.DoubleHolder;

public class Root extends Monad {
    private double root;

    public Root(double root) {
        this.root = root;
    }

    @Override
    public Variable run(Variable a) {
        return new DoubleHolder(root(new DoubleHolder(a.toString()).getValue(), root));
    }

    private double root(double num, double root) {
        return Math.pow(Math.E, Math.log(num)/root);
    }
}
