package me.okx.stratos.tokens.input;

import me.okx.stratos.var.StringHolder;
import me.okx.stratos.var.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputManager {
    private List<Variable> inputs = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public StringHolder getInput() {
        StringHolder input =  new StringHolder(scanner.nextLine());
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
}
