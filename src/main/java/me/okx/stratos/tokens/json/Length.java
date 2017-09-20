package me.okx.stratos.tokens.json;

import me.okx.stratos.tokens.types.Monad;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.Holder;
import me.okx.stratos.var.holders.JSONArrayHolder;
import org.json.JSONException;

public class Length extends Monad {
    @Override
    public Variable run(Variable a) {
        try {
            return new Holder<>(new JSONArrayHolder(a).getValue().length());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
