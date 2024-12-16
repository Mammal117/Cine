package com.raven.model;

import com.raven.connection.DatabaseOperation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Sala {

    private DatabaseOperation db = new DatabaseOperation();

    public void insertSala(String name, int Seatings) {
        String sql = "INSERT INTO Salas (Nombre, Asientos) VALUES (?,?)";
        Object[] values = {name, Seatings};
        int rowsAffected = db.executeUpdate(sql, values);
        if (rowsAffected > 0) {
            System.out.println("Sala insertada con exito");
        } else {
            System.out.println("algo salió mal, sala no insertada.");
        }
    }

    public List<Map<String, Object>> showSalas() {
        String sql = "SELECT * from Salas";
        return db.getRecords(sql);
    }

    public int getSalaCapacity(int FuncionID) {
        String sql = "SELECT Asientos from Salas where SalaID = (SELECT SalaID from Funciones where FuncionID = ?)";
        int Capacidad = 0;
        try (Connection conn = db.connectToDatabase();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, FuncionID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Capacidad = rs.getInt("Asientos");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Capacidad;
    }

}
