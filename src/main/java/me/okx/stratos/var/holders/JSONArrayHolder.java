package me.okx.stratos.var.holders;

import me.okx.stratos.var.Variable;
import org.json.JSONArray;
import org.json.JSONException;

public class JSONArrayHolder extends Holder<JSONArray> {

    public JSONArrayHolder(JSONArray value) {
        super(value);
    }

    public JSONArrayHolder(Variable value) throws JSONException {
        super(new JSONArray(value.toString()));
    }
}
