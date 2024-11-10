package hexlet.code;

import hexlet.code.model.Url;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

@Slf4j
public class App {
    public static Javalin getApp() {
        Javalin app = Javalin.create(config -> config.bundledPlugins.enableDevLogging())
                .get("/", ctx -> ctx.result("Hello World"));

        log.info("Обработка по пути /");
        return app;
    }

    public static void main(String[] args) {
        DataSource dataSource = DatabaseConfig.getDataSource();

        UrlRepository urlRepository = new UrlRepository(dataSource);

        Url newUrl = new Url("http://example.com");
        urlRepository.addUrl(newUrl);
    }
}
