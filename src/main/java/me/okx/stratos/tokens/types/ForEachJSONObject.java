package me.okx.stratos.tokens.types;

import me.okx.stratos.var.holders.JSONObjectHolder;

public abstract class ForEachJSONObject extends Token {
    public ForEachJSONObject() {
        super(-1);
    }

    public abstract void run(JSONObjectHolder var);
}
