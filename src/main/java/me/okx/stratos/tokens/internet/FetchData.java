package me.okx.stratos.tokens.internet;

import me.okx.stratos.tokens.types.Monad;
import me.okx.stratos.var.StringHolder;
import me.okx.stratos.var.Variable;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class FetchData extends Monad {
    @Override
    public Variable run(Variable a) {
        try {
            String str = a.toString();

            if (!str.contains(":")) {
                str = "https://" + str;
            }

            HttpURLConnection conn = (HttpURLConnection) new URL(str).openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("Accept-Encoding", "gzip");

            byte[] bytes = IOUtils.toByteArray(conn.getInputStream());
            String content;

            if(conn.getContentEncoding().equalsIgnoreCase("gzip")) {
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

            StringHolder fetched = new StringHolder(content);

            return fetched;
        } catch(IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}