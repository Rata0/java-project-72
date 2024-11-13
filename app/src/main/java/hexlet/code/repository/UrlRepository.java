package hexlet.code.repository;

import hexlet.code.model.Url;

import java.sql.*;

public class UrlRepository extends BaseRepository {
    public static void save(Url url) throws SQLException {
        String sql = "INSERT INTO urls (name, created_at) VALUES (?, ?)";

        try (var conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, url.getName());
            preparedStatement.setTimestamp(2, url.getCreatedAt());
            preparedStatement.executeUpdate();

            setGeneratedKey(url, preparedStatement);
            System.out.println("SAVE DATA !!!");
        }
    }

    public static boolean find(String name) throws SQLException {
        String sql = "SELECT * FROM urls WHERE name = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                System.out.println("Найден");
                return true;
            }
            System.out.println("Не найден");
            return false;
        }
    }

    private static void setGeneratedKey(Url url, PreparedStatement preparedStatement) throws SQLException {
        try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                url.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Failed to retrieve generated ID after save.");
            }
        }
    }

    private static Url createUrlFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Timestamp createdAt = resultSet.getTimestamp("created_at");
        Url url = new Url(name, createdAt);
        url.setId(id);
        System.out.println("DATA FIND !!!" + url.toString());
        return url;
    }

}
