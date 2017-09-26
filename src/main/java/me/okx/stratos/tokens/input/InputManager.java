package me.okx.stratos.tokens.input;

import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.Holder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputManager implements Cloneable {
    private List<Variable> inputs = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public Holder<String> getInput() {
        Holder<String> input =  new Holder<>(scanner.nextLine());
        inputs.add(input);
        return input;
    }

    public void add(Variable v) {
        inputs.add(v);
    }

    public Variable getInput(int id) {
        while(inputs.size() < id) {
            getInput();
        }
        return inputs.get(id);
    }

    public InputManager clone() {
        InputManager clone = new InputManager();
        clone.inputs = new ArrayList<>(inputs);
        clone.scanner = new Scanner(System.in);
        return clone;
    }
}
