package hexlet.code;

import hexlet.code.model.Url;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

@Slf4j
public class App {

    public static void main(String[] args) {
        DataSource dataSource = DatabaseConfig.getDataSource();

        UrlRepository urlRepository = new UrlRepository(dataSource);

        Url newUrl = new Url("http://example.com");
        urlRepository.addUrl(newUrl);

        getApp().start();
    }

    public static Javalin getApp() {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
            log.info("Обрабокта пути /");
        });

        return app;
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        return TemplateEngine.create(codeResolver, ContentType.Html);
    }
}
