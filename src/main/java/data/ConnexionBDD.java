package data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnexionBDD {

    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        Properties props = new Properties();
        try (InputStream input = ConnexionBDD.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (input == null) {
                throw new RuntimeException("Fichier db.properties introuvable dans resources");
            }
            props.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Erreur de chargement de la configuration BDD", e);
        }

        URL = props.getProperty("db.url");
        USER = props.getProperty("db.user");

        PASSWORD = System.getenv("DB_PASSWORD");
        if (PASSWORD == null || PASSWORD.isBlank()) {
            throw new RuntimeException(
                    "Variable d'environnement DB_PASSWORD non définie. " +
                            "Définis-la avant de lancer l'application."
            );
        }
    }

    private ConnexionBDD() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}