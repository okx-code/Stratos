package me.okx.stratos.tokens.json;

import me.okx.stratos.tokens.types.Dyad;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.JSONArrayHolder;
import me.okx.stratos.var.holders.JSONObjectHolder;
import org.json.JSONException;
import org.json.JSONObject;

public class GetArray extends Dyad {
    @Override
    public Variable run(Variable a, Variable b) {
        try {
            JSONObject json = new JSONObjectHolder(a).getValue();

            return new JSONArrayHolder(json.getJSONArray(b.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
