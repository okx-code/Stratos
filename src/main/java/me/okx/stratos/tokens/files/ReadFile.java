package me.okx.stratos.tokens.files;

import me.okx.stratos.tokens.types.Monad;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.Holder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ReadFile extends Monad {
    @Override
    public Variable run(Variable a) {
        try {
            return new Holder<>(new String(Files.readAllBytes(new File(a.toString()).toPath())));
        } catch (IOException e) {
            return new Holder<>("");
        }
    }
}
