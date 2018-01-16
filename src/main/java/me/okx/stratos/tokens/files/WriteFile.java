package me.okx.stratos.tokens.files;

import me.okx.stratos.tokens.types.Dyad;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.BooleanHolder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WriteFile extends Dyad {
    @Override
    public Variable run(Variable a, Variable b) {
        try {
            Files.write(new File(b.toString()).toPath(), b.toString().getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING);

            return new BooleanHolder(true);
        } catch(Exception ex) {
            return new BooleanHolder(false);
        }
    }
}
