package me.okx.stratos.tokens.control;

import me.okx.stratos.TokenManager;
import me.okx.stratos.tokens.input.StorageInput;
import me.okx.stratos.tokens.types.ControlFlow;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.BooleanHolder;

import java.util.Optional;

public class If extends ControlFlow {
    @Override
    public Optional<Variable> run(String left, String right, TokenManager eval, StorageInput input) {
        if(new BooleanHolder(eval.run(left, input).toString()).getValue()) {
            return Optional.of(eval.run(right, input));
        } else {
            return Optional.empty();
        }
    }
}
