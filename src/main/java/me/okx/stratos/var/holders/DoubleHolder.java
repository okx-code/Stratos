package me.okx.stratos.var.holders;

public class DoubleHolder extends Holder<Double> {
    public DoubleHolder(double value) {
        super(value);
    }

    public DoubleHolder(String value) {
        super(Double.parseDouble(value));
    }
}
