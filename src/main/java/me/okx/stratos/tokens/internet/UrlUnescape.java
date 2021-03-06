package me.okx.stratos.tokens.internet;

import me.okx.stratos.tokens.types.Monad;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.Holder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UrlUnescape extends Monad {
    @Override
    public Variable run(Variable a) {
        try {
            return new Holder<>(URLDecoder.decode(a.toString(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
