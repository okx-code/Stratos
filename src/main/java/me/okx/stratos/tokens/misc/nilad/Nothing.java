package me.okx.stratos.tokens.misc.nilad;

import me.okx.stratos.tokens.types.Nilad;
import me.okx.stratos.var.Variable;

public class Nothing extends Nilad {
    @Override
    public Variable run() {
        return null;
    }
}
