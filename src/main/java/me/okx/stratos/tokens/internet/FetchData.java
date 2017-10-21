package me.okx.stratos.tokens.internet;

import me.okx.stratos.tokens.types.Monad;
import me.okx.stratos.var.Variable;
import me.okx.stratos.var.holders.Holder;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class FetchData extends Monad {
    @Override
    public Variable run(Variable a) {
        String str = a.toString();
        String[] attempts = { str, "www." + str, "http://" + str, "https://" + str,
                "http://www." + str, "https://www." + str };

        for(String attempt : attempts) {
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(attempt).openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);

                conn.setRequestProperty("Accept-Encoding", "gzip");

                byte[] bytes = IOUtils.toByteArray(conn.getInputStream());
                String content;

                if (conn.getContentEncoding() != null &&
                        conn.getContentEncoding().equalsIgnoreCase("gzip")) {
                    InputStream inStream = new GZIPInputStream(
                            new ByteArrayInputStream(bytes));
                    ByteArrayOutputStream baoStream2 = new ByteArrayOutputStream();
                    byte[] buffer = new byte[8192];
                    int len;
                    while ((len = inStream.read(buffer)) > 0) {
                        baoStream2.write(buffer, 0, len);
                    }
                    content = baoStream2.toString("UTF-8");
                } else {
                    content = new String(bytes);
                }

                return new Holder<>(content);
            } catch (Exception ex) {

            }
        }

        return null;
    }
}
