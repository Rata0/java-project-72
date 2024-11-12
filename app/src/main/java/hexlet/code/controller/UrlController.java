package hexlet.code.controller;

import io.javalin.http.Context;

import java.sql.SQLException;

public class UrlController {
    public static void create(Context ctx) throws SQLException {
        String url = ctx.formParam("url");
        System.out.println(url);
    }
}
