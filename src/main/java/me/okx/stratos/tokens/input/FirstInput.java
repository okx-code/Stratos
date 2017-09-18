package me.okx.stratos.tokens.input;

import me.okx.stratos.tokens.types.Nilad;
import me.okx.stratos.var.Variable;

public class FirstInput extends Nilad {
    private StorageInput iu;

    public FirstInput(StorageInput iu) {
        this.iu = iu;
    }

    @Override
    public Variable run() {
        return iu.get(0);
    }
}
