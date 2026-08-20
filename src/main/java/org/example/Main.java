package org.example;

import data.ConnexionBDD;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConnexionBDD.getConnection()) {
            System.out.println("Connexion réussie : " + conn.isValid(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}