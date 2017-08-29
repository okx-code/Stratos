package me.okx.stratos.tokens.control;

import me.okx.stratos.tokens.types.ControlFlow;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.BooleanHolder;
import me.okx.stratos.var.holders.Holder;

public class If extends ControlFlow {
    @Override
    public boolean check(Variable a) {
        return new BooleanHolder(a.toString()).getValue();
    }
}
