package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.controller.UrlController;
import hexlet.code.model.Url;
import hexlet.code.repository.BaseRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class App {

    public static void main(String[] args) {
        getApp().start();
    }

    public static Javalin getApp() {
        var hikariConfig = new HikariConfig();
        String jdbc = getJdbcUrl();
        hikariConfig.setJdbcUrl(jdbc);

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        String sql = "CREATE TABLE IF NOT EXISTS urls ("
                + "id SERIAL PRIMARY KEY,"
                + "name VARCHAR(255) NOT NULL,"
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ")";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Таблица urls успешно создана.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        BaseRepository.dataSource = dataSource;

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

    private static String getJdbcUrl() {
        return System.getenv().getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project");
    }
}
