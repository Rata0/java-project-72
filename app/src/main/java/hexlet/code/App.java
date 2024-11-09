package hexlet.code;

import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static Javalin getApp() {
        Javalin app = Javalin.create(config -> config.bundledPlugins.enableDevLogging())
                .get("/", ctx -> ctx.result("Hello World"));

        log.info("Обработка по пути /");
        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
        log.info("Приложение запущено на порту 7070");
    }
}
