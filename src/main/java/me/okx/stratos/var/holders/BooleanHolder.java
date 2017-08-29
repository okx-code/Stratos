package me.okx.stratos.var.holders;

public class BooleanHolder extends Holder<Boolean> {
    public BooleanHolder(boolean value) {
        super(value);
    }

    public BooleanHolder(String value) {
        super(value.equals("true") || value.equals("1"));
    }
}
