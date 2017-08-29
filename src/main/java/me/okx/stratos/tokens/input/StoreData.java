package me.okx.stratos.tokens.input;

import me.okx.stratos.tokens.types.Monad;
import me.okx.stratos.var.Variable;

public class StoreData extends Monad {
    private StorageInput input;

    public StoreData(StorageInput input) {
        this.input = input;
    }

    @Override
    public Variable run(Variable a) {
        input.push(a);
        return a;
    }
}
