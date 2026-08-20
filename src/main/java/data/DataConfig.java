package data;


import lombok.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DataConfig {

    @Value({"jdbc:postgresql://localhost:5432/ma_base")
    private String url;

    @Value("mon_user")
    private String username;

    @Value("1234")
    private String password;

    public DatabaseConfig() {}

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
