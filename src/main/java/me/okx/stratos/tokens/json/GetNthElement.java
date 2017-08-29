package me.okx.stratos.tokens.json;

import me.okx.stratos.tokens.types.Dyad;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.JSONArrayHolder;
import me.okx.stratos.var.holders.JSONObjectHolder;
import org.json.JSONException;

public class GetNthElement extends Dyad {
    @Override
    public Variable run(Variable a, Variable b) {
        try {
            return new JSONObjectHolder(new JSONArrayHolder(a).getValue().getJSONObject(Integer.parseInt(b.toString())));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
