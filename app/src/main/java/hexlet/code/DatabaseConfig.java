package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {
    private static HikariDataSource hikariDataSource;

    public static DataSource getDataSource() {
        if (hikariDataSource == null) {
            HikariConfig config = new HikariConfig();

            String jdbcUrl = getJdbcUrl();
            config.setJdbcUrl(jdbcUrl);

            hikariDataSource = new HikariDataSource(config);
            createSchema();
        }
        return hikariDataSource;
    }

    private static String getJdbcUrl() {
        return System.getenv().getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project");
    }

    private static void createSchema() {
        String sql = "CREATE TABLE IF NOT EXISTS urls ("
                + "id SERIAL PRIMARY KEY,"
                + "name VARCHAR(255) NOT NULL,"
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ")";

        try (Connection connection = hikariDataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Таблица urls успешно создана.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
