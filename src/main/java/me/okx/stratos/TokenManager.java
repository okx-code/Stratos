package me.okx.stratos;

import me.okx.stratos.tokens.control.FirstMatch;
import me.okx.stratos.tokens.control.If;
import me.okx.stratos.tokens.input.InputManager;
import me.okx.stratos.tokens.input.NthInput;
import me.okx.stratos.tokens.input.StorageInput;
import me.okx.stratos.tokens.input.StoreData;
import me.okx.stratos.tokens.internet.FetchData;
import me.okx.stratos.tokens.internet.UrlEscape;
import me.okx.stratos.tokens.internet.UrlUnescape;
import me.okx.stratos.tokens.json.GetArray;
import me.okx.stratos.tokens.json.GetNthElement;
import me.okx.stratos.tokens.json.GetString;
import me.okx.stratos.tokens.json.Length;
import me.okx.stratos.tokens.misc.dyad.*;
import me.okx.stratos.tokens.misc.monad.Extend;
import me.okx.stratos.tokens.misc.monad.Inverse;
import me.okx.stratos.tokens.misc.monad.Negate;
import me.okx.stratos.tokens.misc.monad.Root;
import me.okx.stratos.tokens.misc.nilad.Alias;
import me.okx.stratos.tokens.types.ControlFlow;
import me.okx.stratos.tokens.types.Token;
import me.okx.stratos.util.ParseQuotedString;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.DoubleHolder;
import me.okx.stratos.var.holders.Holder;

import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class TokenManager {
    private Variable output;

    private Map<String, Token> tokens = new HashMap<>();

    public TokenManager(String[] program) {
        this(program, new String[0]);
    }

    public TokenManager(String[] program, String[] inputData) {
        InputManager iu;
        if(inputData.length > 0) {
            iu = new InputManager(Arrays.stream(inputData).map(Holder::new).collect(Collectors.toList()));
        } else {
            iu = new InputManager();
        }
        StorageInput input = new StorageInput(iu);

        // Data / Input
        tokens.put("{", new StoreData(input));
        tokens.put("I", input);
        tokens.put("⁰", new NthInput(0, input));
        tokens.put("¹", new NthInput(1, input));

        // Dyads
        tokens.put("-", new Subtract());
        tokens.put("/", new Divide());
        tokens.put("+", new Concatenate());
        tokens.put("=", new Equality());

        tokens.put("@", new GetNthElement());
        tokens.put("r", new GetArray());
        tokens.put("f", new FetchData());

        tokens.put("⊂", new IsSubstring());

        // Monads
        tokens.put("_", new Negate());
        tokens.put(">", new Extend());
        tokens.put("l", new Length());
        tokens.put("√", new Root(2));
        tokens.put("∛", new Root(3));
        tokens.put("∜", new Root(4));
        tokens.put("⅟", new Inverse());

        tokens.put("s", new GetString());
        tokens.put("c", new UrlEscape());
        tokens.put("u", new UrlUnescape());

        // Nilads
        tokens.put("⅐", new Alias(new DoubleHolder(1D/7D)));
        tokens.put("⅑", new Alias(new DoubleHolder(1D/9D)));
        tokens.put("⅒", new Alias(new DoubleHolder(1D/10D)));
        tokens.put("⅓", new Alias(new DoubleHolder(1D/3D)));
        tokens.put("⅔", new Alias(new DoubleHolder(2D/3D)));
        tokens.put("⅕", new Alias(new DoubleHolder(1D/5D)));
        tokens.put("⅖", new Alias(new DoubleHolder(2D/5D)));
        tokens.put("⅗", new Alias(new DoubleHolder(3D/5D)));
        tokens.put("⅘", new Alias(new DoubleHolder(4D/5D)));
        tokens.put("⅙", new Alias(new DoubleHolder(1D/6D)));
        tokens.put("⅚", new Alias(new DoubleHolder(5D/6D)));
        tokens.put("⅛", new Alias(new DoubleHolder(1D/8D)));
        tokens.put("⅜", new Alias(new DoubleHolder(3D/8D)));
        tokens.put("⅝", new Alias(new DoubleHolder(5D/8D)));
        tokens.put("⅞", new Alias(new DoubleHolder(7D/8D)));
        tokens.put("Ⅺ", new Alias(new DoubleHolder(11)));


        // Control - \ for extension
        tokens.put("i", new If()); // e
        tokens.put(")", new FirstMatch());

        output = exec(program, input);
    }

    public Variable getOutput() {
        return output;
    }

    public Variable exec(String[] lines, StorageInput input) {
        for(int i = 0; i < lines.length; i++) {
            if(i == lines.length - 1) {
                return run(lines[i], input);
            } else {
                input.push(run(lines[i], input));
            }
        }
        return null;
    }

    public Variable run(String program, StorageInput input) {
        if(program.isEmpty()) {
            return input.run();
        }
        byte[] charsArray = program.getBytes(Charset.forName("UTF-8"));
        StringBuilder b = new StringBuilder();
        for(byte c  : charsArray) {
            b.append(String.format("%02X ", (int) c));
        }

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

        // Find leftmost control flow
        for(int i = 0; i < chars.size(); i++) {
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

                return ((ControlFlow) t)
                        .run(left.toString(), right.toString(), this, input)
                        .orElse(new Holder<>(""));
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
                Variable leftRun = run(left.toString(), input);
                Variable rightRun = run(right.toString(), input);
                return t.eval(input, leftRun, rightRun);
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

        if(program.startsWith("\"")) {
            return new Holder<>(ParseQuotedString.parse(program, input));
        } else if(program.matches("[0-9]+")) {
            return new Holder<>(String.valueOf(Integer.parseInt(program)));
        }

        return tokens.get(chars.get(0)).eval(input);
    }
}
