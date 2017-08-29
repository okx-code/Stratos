package me.okx.stratos.var.holders;

import me.okx.stratos.var.Variable;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONObjectHolder extends Holder<JSONObject> {
    public JSONObjectHolder(JSONObject value) {
        super(value);
    }

    public JSONObjectHolder(Variable value) throws JSONException {
        super(new JSONObject(value.toString()));
    }
}
