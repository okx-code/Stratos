package me.okx.stratos.tokens.input;

import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.Holder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputManager implements Cloneable {
    private List<Variable> inputs;
    private int get = 0;
    private Scanner scanner;

    public InputManager() {
        this.inputs = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public InputManager(List<Variable> inputs) {
        this.inputs = inputs;
        scanner = new Scanner(String.join("\n", inputs.stream().map(Variable::toString).collect(Collectors.toList())));
    }

    public Holder<String> getInput() {
        if(get+1 < inputs.size()) {
            return new Holder<>(inputs.get(get++).toString());
        }
        if(!scanner.hasNext()) {
            get = 0;
            return getInput();
        }
        Holder<String> input =  new Holder<>(scanner.nextLine());
        inputs.add(input);
        get++;
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
