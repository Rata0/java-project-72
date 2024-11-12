package hexlet.code;

import hexlet.code.controller.UrlController;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;
import lombok.extern.slf4j.Slf4j;


import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
public class App {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = DatabaseConfig.getDataSource();

        UrlRepository urlRepository = new UrlRepository(dataSource);

        Url newUrl = new Url("http://example.com");
        UrlRepository.save(newUrl);
        urlRepository.find("http://example.com");
        getApp().start();
    }

    public static Javalin getApp() {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });

        app.get(NamedRoutes.rootPath(), ctx -> {
            ctx.render("index.jte");
            log.info("Обрабокта пути " + NamedRoutes.rootPath());
        });

        app.post(NamedRoutes.urlsPath(), UrlController::create);

        return app;
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        return TemplateEngine.create(codeResolver, ContentType.Html);
    }
}
