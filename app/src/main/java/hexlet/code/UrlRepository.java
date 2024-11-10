package hexlet.code;

import hexlet.code.model.Url;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UrlRepository extends BaseRepository {
    public UrlRepository(DataSource dataSource) {
        super(dataSource);
    }

    public void addUrl(Url url) {
        String sql = "INSERT INTO urls (name, created_at) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, url.getName());
            statement.setTimestamp(2, url.getCreatedAt());
            statement.executeUpdate();
            System.out.println("URL добавлен: " + url.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
