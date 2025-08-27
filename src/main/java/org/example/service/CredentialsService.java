package org.example.service;

import org.example.repository.CredentialsDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class CredentialsService {
    private final CredentialsDAO dao;

    public CredentialsService(CredentialsDAO dao) {
        this.dao = dao;
    }

    public int getAuthenticatedId(String username, String password) throws SQLException {
        return dao.getAuthenticatedID(username, password);
    }
}
