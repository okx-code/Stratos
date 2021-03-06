package me.okx.stratos;

import me.okx.stratos.var.Variable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static final String[] CODE_PAGE = {
            "√", "∛", "∜", "⊂", "⅟", "⅐", "⅑", "⅒", "⅓", "⅔", "\n", "⅕", "⅖", "⅗", "⅘", "⅙",
            "⅚", "⅛", "⅜", "⅝", "⅞", "Ⅺ", "⁰", "¹", "²", "³", "⁴", "⁵", "⁶", "⁷", "⁸", "⁹",
            " ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ":", ";", "<", "=", ">", "?",
            "@", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "[", "\\", "]", "^", "_",
            "`", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "{", "|", "}", "~", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
    };

    public static void main(String[] args) throws IOException {

        if(args.length == 0) {
            printUsage();
            return;
        }

        boolean useCodePage = false;
        boolean decode = false;

        for(String arg : args) {
            if(arg.equalsIgnoreCase("-c")) {
                useCodePage = true;
            } else if(arg.equalsIgnoreCase("-u")) {
                decode = true;
            }
        }

        String file = null;
        for(String arg : args) {
            if(!arg.startsWith("-")) {
                file = arg;
                break;
            }
        }

        if(file == null) {
            printUsage();
            return;
        }

        String program;
        byte[] content = Files.readAllBytes(new File(file).toPath());

        if(useCodePage) {
            StringBuilder builder = new StringBuilder();
            for(byte b : content) {
                builder.append(CODE_PAGE[b]);
            }
            program = builder.toString();
        } else {
            program = new String(content);
        }

        if(decode) {
            StringBuilder builder = new StringBuilder();
            StringBuilder on = new StringBuilder();
            for(char c : program.toCharArray()) {
                on.append(c);

                for(int i = 0; i < CODE_PAGE.length; i++) {
                    if(CODE_PAGE[i].equals(on.toString())) {
                        builder.append(String.format("%02X ", i));
                        on.setLength(0);
                        break;
                    }
                }
            }
            System.out.println(builder.toString());

            return;
        }

        Variable out = new TokenManager(program.split("\\r?\\n")).getOutput();

        if(out != null) {
            System.out.println(out);
        }
    }

    private static void printUsage() {
        System.out.println("java -jar Stratos-1.0-SNAPSHOT.jar <file> <options>\n" +
                "Options:\n" +
                "  -c By default, Stratos programs are read in unicode. This option makes Stratos read in its custom codepage.\n" +
                "  -u Decode from unicode to Stratos's encoding");
    }
}
