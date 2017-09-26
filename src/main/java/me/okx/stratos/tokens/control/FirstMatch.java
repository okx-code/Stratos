package me.okx.stratos.tokens.control;

import me.okx.stratos.TokenManager;
import me.okx.stratos.tokens.input.StorageInput;
import me.okx.stratos.tokens.types.ControlFlow;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.BooleanHolder;
import me.okx.stratos.var.holders.Holder;
import me.okx.stratos.var.holders.JSONArrayHolder;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Optional;

public class FirstMatch extends ControlFlow {
    @Override
    public Optional<Variable> run(String left, String right, TokenManager eval, StorageInput input) {
        try {
            JSONArray array =
                    new JSONArrayHolder(eval.run(left, input)).getValue();
            for(int i = 0; i < array.length(); i++) {
                StorageInput clone = input.clone();
                clone.push(new Holder<>(array.get(i)));
                boolean value =
                        new BooleanHolder(eval.run(right, clone).toString()).getValue();
                if(value) {
                    return Optional.of(new Holder<>(array.get(i)));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
