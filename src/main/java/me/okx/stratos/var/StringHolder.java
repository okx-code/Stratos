package me.okx.stratos.var;

public class StringHolder implements Variable {
    private String value;

    public StringHolder(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
