package me.okx.stratos.util;

import me.okx.stratos.tokens.input.StorageInput;

import java.util.HashMap;
import java.util.Map;

public class ParseQuotedString {
    private ParseQuotedString() {

    }

    private static Map<String, String> replacements = new HashMap<>();

    static {
        replacements.put("⁰", "count");
        replacements.put("¹", "api.stackexchange.com/");
        replacements.put("²", "?site=codegolf");
        replacements.put("³", "display");
        replacements.put("⁴", "name");
        replacements.put("⁵", "question");
        replacements.put("⁶", "%");
        replacements.put("⁷", "item");
        replacements.put("⁸", "user");
        replacements.put("⁹", "id");
    }

    public static String parse(String s, StorageInput input) {
        s = s.substring(1);

        if(s.endsWith("\"")) {
            s = s.substring(0, s.length() - 1);
        }

        char[] chars = s.toCharArray();
        String[] sChars = new String[chars.length];

        boolean escape = false;
        for(int i = 0; i < chars.length; i++) {
            char c = chars[i];
            sChars[i] = String.valueOf(c);
            if(c == '¬') {
                escape = true;
                continue;
            }

            if(c == '%' && !escape) {
                sChars[i] = input.run().toString();
            }

            escape = false;
        }

        s = String.join("", sChars);

        for(Map.Entry<String, String> entry : replacements.entrySet()) {
            s = s.replace(entry.getKey(), entry.getValue());
        }

        return s;
    }
}

/*
b̊c̊d̊e̊f̊g̊h̊i̊j̊k̊ m̊n̊o̊p̊q̊
r̊s̊t̊ův̊ẘ⁰¹²³⁴⁵⁶⁷⁸⁹
 !"#$%&'()*+,-./
0123456789:;<=>?
@ABCDEFGHIJKLMNO
PQRSTUVWXYZ[\]^_
`abcdefghijklmno
pqrstuvwxyz{|}~Ȧ
ḂĊḊĖḞĠḢİJ̇K̇L̇ṀṄȮṖQ̇
ṘṠṪU̇V̇ẆẊẎŻȧḃċḋėḟġ
ḣk̇l̇ṁṅȯṗq̇ṙṡṫu̇v̇ẇẋẏ
żẠḄC̣ḌẸF̣G̣ḤỊJ̣ḲḶṂṆỌ
P̣Q̣ṚṢṬỤṾẈX̣ỴẒạḅc̣ḍẹ
f̣g̣ḥịjḳḷṃṇọp̣q̣ṛṣṭụ
ṿẉx̣ỵẓÅB̊C̊D̊E̊F̊G̊H̊I̊J̊K̊
L̊M̊N̊O̊P̊Q̊R̊S̊T̊ŮV̊W̊X̊Y̊Z̊å
 */