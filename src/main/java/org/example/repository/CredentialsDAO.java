package org.example.repository;

import org.example.util.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class CredentialsDAO {

    public CredentialsDAO () {}

    public int getAuthenticatedID (String username, String password) throws SQLException {
        String query = "SELECT employeeId FROM credentials WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next()? rs.getInt("employeeID") : -1;
            }

        }
    }
}
