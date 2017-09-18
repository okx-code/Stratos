package me.okx.stratos.tokens.misc.dyad;

import me.okx.stratos.tokens.types.Dyad;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.DoubleHolder;
import me.okx.stratos.var.holders.Holder;

public class Concatenate extends Dyad {
    @Override
    public Variable run(Variable a, Variable b) {
        try {
            double da = new DoubleHolder(a.toString()).getValue();
            double db = new DoubleHolder(b.toString()).getValue();

            return new DoubleHolder(da + db);

        } catch(Exception ex) {
            return new Holder<>(a.toString() + b.toString());
        }
    }
}
