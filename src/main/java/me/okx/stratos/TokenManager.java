package me.okx.stratos;

import me.okx.stratos.tokens.control.If;
import me.okx.stratos.tokens.input.FirstInput;
import me.okx.stratos.tokens.input.InputManager;
import me.okx.stratos.tokens.input.StorageInput;
import me.okx.stratos.tokens.input.StoreData;
import me.okx.stratos.tokens.internet.FetchData;
import me.okx.stratos.tokens.internet.UrlEscape;
import me.okx.stratos.tokens.json.GetArray;
import me.okx.stratos.tokens.json.GetNthElement;
import me.okx.stratos.tokens.json.GetString;
import me.okx.stratos.tokens.json.Length;
import me.okx.stratos.tokens.misc.dyad.Concatenate;
import me.okx.stratos.tokens.misc.dyad.Divide;
import me.okx.stratos.tokens.misc.dyad.Equality;
import me.okx.stratos.tokens.misc.monad.Extend;
import me.okx.stratos.tokens.types.ControlFlow;
import me.okx.stratos.tokens.types.Token;
import me.okx.stratos.util.ParseQuotedString;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.Holder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TokenManager {
    private Variable output;

    private Map<String, Token> tokens = new HashMap<>();

    public TokenManager(String[] program) {
        InputManager iu = new InputManager();
        StorageInput input = new StorageInput(iu);

        // Data / Input
        tokens.put("{", new StoreData(input));
        tokens.put("I", input);
        tokens.put("⁰", new FirstInput(input));

        // Dyads
        tokens.put("/", new Divide());
        tokens.put("+", new Concatenate());
        tokens.put("=", new Equality());

        tokens.put("@", new GetNthElement());
        tokens.put("r", new GetArray());
        tokens.put("f", new FetchData());

        // Monads
        tokens.put(">", new Extend());
        tokens.put("l", new Length());

        tokens.put("s", new GetString());
        tokens.put("c", new UrlEscape());

        // Control
        tokens.put("i", new If());
        // e: else

        output = exec(program, input);
    }

    public Variable getOutput() {
        return output;
    }

    private Variable exec(String[] lines, StorageInput input) {
        for(int i = 0; i < lines.length; i++) {
            if(i == lines.length - 1) {
                return run(lines[i], input);
            } else {
                input.push(run(lines[i], input));
            }
        }
        return null;
    }

    private Variable run(String program, StorageInput input) {
        List<String> chars = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for(char c : program.toCharArray()) {
            sb.append(c);

            for(String s : Main.CODE_PAGE) {
                if(s.equals(sb.toString())) {
                    sb.setLength(0);
                    chars.add(s);
                }
            }
        }


        boolean inString = false;

        // Find rightmost control flow
        for(int i = chars.size() - 1; i >= 0; i--) {
            String c = chars.get(i);

            if(c.equals("\"")) {
                inString = !inString;
            }

            if(inString) {
                continue;
            }

            Token t = tokens.get(c);

            if(t != null && t instanceof ControlFlow) {
                // Scan left
                StringBuilder left = new StringBuilder();
                for(int j = 0; j < i; j++) {
                    left.append(chars.get(j));
                }

                // Scan right
                StringBuilder right = new StringBuilder();
                for(int j = i+1; j < chars.size(); j++) {
                    right.append(chars.get(j));
                }

                if(((ControlFlow) t).check(run(left.toString(), input))) {
                    return run(right.toString(), input);
                } else if(right.toString().contains("e")) {
                    return run(right.toString().split("e")[1], input);
                }
            }
        }

        // Find rightmost dyad
        for(int i = chars.size() - 1; i >= 0; i--) {
            String c = chars.get(i);

            if(c.equals("\"")) {
                inString = !inString;
            }

            if(inString) {
                continue;
            }

            Token t = tokens.get(c);


            if(t != null && t.getArity() == 2) {
                // Scan left
                StringBuilder left = new StringBuilder();
                for(int j = 0; j < i; j++) {
                    left.append(chars.get(j));
                }

                // Scan right
                StringBuilder right = new StringBuilder();
                for(int j = i+1; j < chars.size(); j++) {
                    right.append(chars.get(j));
                }

                return t.eval(input, run(left.toString(), input), run(right.toString(), input));
            }
        }

        // Find leftmost monad
        for(int i = 0; i < chars.size(); i++) {
            String c = chars.get(i);

            if(c.equals("\"")) {
                inString = !inString;
            }

            if(inString) {
                continue;
            }

            Token t = tokens.get(c);

            if (t != null && t.getArity() == 1) {
                // Scan right
                StringBuilder right = new StringBuilder();
                for (int j = i + 1; j < chars.size(); j++) {
                    right.append(chars.get(j));
                }

                return t.eval(input, run(right.toString(), input));
            }
        }

        if(!program.isEmpty()) {
            if(program.startsWith("\"")) {
                return new Holder<>(ParseQuotedString.parse(program, input));
            } else if(program.matches("[0-9]+")) {
                return new Holder<>(String.valueOf(Integer.parseInt(program)));
            }

            return tokens.get(chars.get(0)).eval(input);
        }
        return input.run();
    }
}
