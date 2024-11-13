package hexlet.code.controller;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import io.javalin.http.Context;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class UrlController {
    public static void create(Context ctx) throws URISyntaxException, SQLException {
        String url = ctx.formParam("url");
        URI uri = new URI(url);
        StringBuilder domain = new StringBuilder(uri.getScheme())
                .append("://")
                .append(uri.getHost());

        if (uri.getPort() != -1) {
            domain.append(":").append(uri.getPort());
        }

        boolean check = UrlRepository.find(domain.toString());
        if (!check) {
            Url url1 = new Url(domain.toString());
            System.out.println(url1.toString());
            UrlRepository.save(url1);
        }
    }
}
