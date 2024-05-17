import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Jdbc {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    // Static block to initialize connection properties
    static {
        try (InputStream input = Jdbc.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new IOException("Unable to find db.properties");
            }
            Properties prop = new Properties();
            prop.load(input);
            URL = prop.getProperty("db.url");
            USER = prop.getProperty("db.username");
            PASSWORD = prop.getProperty("db.password");
        } catch (IOException ex) {
            System.out.println("Error loading database properties: " + ex.getMessage());
            throw new ExceptionInInitializerError("Failed to load database properties");
        }
    }

    // Method to get a database connection
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            throw new SQLException("Database connection error.");
        }
    }

    // Method to get a user by username
    public static Personne getUserByUsername(String username) {
        Personne user = null;
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM personne WHERE nom = ?")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("personne_id");
                    String name = resultSet.getString("nom");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String role = resultSet.getString("role");

                    user = new Personne(id, name, email, password, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
