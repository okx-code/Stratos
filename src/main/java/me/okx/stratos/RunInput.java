package me.okx.stratos;

import me.okx.stratos.var.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunInput {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        do {
            lines.add(next);
            next = sc.nextLine();
        } while(!next.isEmpty());

        Variable out = new TokenManager(lines.toArray(new String[0])).getOutput();

        if(out != null) {
            System.out.println(out);
        }
    }
}
