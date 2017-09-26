package me.okx.stratos.tokens.input;

import me.okx.stratos.tokens.types.Nilad;
import me.okx.stratos.var.Variable;

public class NthInput extends Nilad {
    private StorageInput iu;
    private int n;

    public NthInput(int n, StorageInput iu) {
        this.iu = iu;
        this.n = n;
    }

    @Override
    public Variable run() {
        return iu.get(n);
    }
}
