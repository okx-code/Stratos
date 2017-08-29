package me.okx.stratos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static final String[] CODE_PAGE = {
            "b̊", "c̊", "d̊", "e̊", "f̊", "g̊", "h̊", "i̊", "j̊", "k̊", "\n", "m̊", "n̊", "o̊", "p̊", "q̊",
            "r̊", "s̊", "t̊", "ů", "v̊", "ẘ", "⁰", "¹", "²", "³", "⁴", "⁵", "⁶", "⁷", "⁸", "⁹",
            " ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ":", ";", "<", "=", ">", "?",
            "@", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "[", "\\", "]", "^", "_",
            "`", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "{", "|", "}", "~", "Ȧ",
            "Ḃ", "Ċ", "Ḋ", "Ė", "Ḟ", "Ġ", "Ḣ", "İ", "J̇", "K̇", "L̇", "Ṁ", "Ṅ", "Ȯ", "Ṗ", "Q̇",
            "Ṙ", "Ṡ", "Ṫ", "U̇", "V̇", "Ẇ", "Ẋ", "Ẏ", "Ż", "ȧ", "ḃ", "ċ", "ḋ", "ė", "ḟ", "ġ",
            "ḣ", "k̇", "l̇", "ṁ", "ṅ", "ȯ", "ṗ", "q̇", "ṙ", "ṡ", "ṫ", "u̇", "v̇", "ẇ", "ẋ", "ẏ",
            "ż", "Ạ", "Ḅ", "C̣", "Ḍ", "Ẹ", "F̣", "G̣", "Ḥ", "Ị", "J̣", "Ḳ", "Ḷ", "Ṃ", "Ṇ", "Ọ",
            "P̣", "Q̣", "Ṛ", "Ṣ", "Ṭ", "Ụ", "Ṿ", "Ẉ", "X̣", "Ỵ", "Ẓ", "ạ", "ḅ", "c̣", "ḍ", "ẹ",
            "f̣", "g̣", "ḥ", "ị", "j", "ḳ", "ḷ", "ṃ", "ṇ", "ọ", "p̣", "q̣", "ṛ", "ṣ", "ṭ", "ụ",
            "ṿ", "ẉ", "x̣", "ỵ", "ẓ", "Å", "B̊", "C̊", "D̊", "E̊", "F̊", "G̊", "H̊", "I̊", "J̊", "K̊",
            "L̊", "M̊", "N̊", "O̊", "P̊", "Q̊", "R̊", "S̊", "T̊", "Ů", "V̊", "W̊", "X̊", "Y̊", "Z̊", "å"
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

        /*List<String> lines = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        do {
            lines.add(next);
            next = sc.nextLine();
        } while(!next.isEmpty());*/
        System.out.println(new TokenManager(program.split("\\r?\\n")).getOutput());
    }

    private static void printUsage() {
        System.out.println("java -jar Stratos-1.0-SNAPSHOT.jar\n" +
                "Options:\n" +
                "  -c By default, Stratos programs are read in unicode. This option makes Stratos read in its custom codepage.\n" +
                "  -u Decode from unicode to Stratos's encoding");
    }
}
