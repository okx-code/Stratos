package me.okx.stratos.var.holders;

import me.okx.stratos.var.Variable;

public class Holder<T> implements Variable {
    private T value;

    public Holder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public String toString() {
        return value.toString();
    }
}
