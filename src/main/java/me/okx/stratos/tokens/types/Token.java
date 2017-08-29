package me.okx.stratos.tokens.types;

import me.okx.stratos.tokens.input.StorageInput;
import me.okx.stratos.var.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Token {
    private int arity;

    public Token(int arity) {
        this.arity = arity;
    }

    public int getArity() {
        return arity;
    }

    public Variable eval(StorageInput input, Variable... args) {
        List<Variable> list = new ArrayList<>(Arrays.asList(args));

        while(list.size() < arity) {
            list.add(input.run());
        }

        if(this instanceof Dyad) {
            return ((Dyad) this).run(list.get(0), list.get(1));
        } else if(this instanceof Monad) {
            return ((Monad) this).run(list.get(0));
        } else if(this instanceof Nilad) {
            return ((Nilad) this).run();
        }
        return null;
    }
}
