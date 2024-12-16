package com.raven.model;

import com.raven.connection.DatabaseOperation;
import java.util.*;

public class Funcion {

    DatabaseOperation db = new DatabaseOperation();

    //public void insertFuncion(int MovieID, int SalaID, String Funcion) {
    public void insertFuncion(String tituloPelicula, String nombreSala, String horaFuncion) {
        //String sql = "INSERT INTO Funciones (PeliculaID,SalaID,Funcion) VALUES(?,?,?)";
        String sqi = "INSERT INTO Funciones (PeliculaID, SalaID, Funcion) "
                + "VALUES ((SELECT PeliculaID FROM Peliculas WHERE Titulo = ?), "
                + "(SELECT SalaID FROM Salas WHERE Nombre = ?), ?)";
        Object[] values = {tituloPelicula, nombreSala, horaFuncion};
        int rowsAffected = db.executeUpdate(sqi, values);
        if (rowsAffected > 0) {
            System.out.println("Funcion insertada con exito");
        } else {
            System.out.println("Something went wrong.Funcion not inserted.");
        }

    }

    public List<Map<String, Object>> showFunciones() {
        //String sql = "SELECT * from Funciones";
        String sql = "SELECT f.FuncionID, p.Titulo AS TituloPelicula, s.Nombre AS NombreSala, f.Funcion AS HoraFuncion "
                + "FROM Funciones f "
                + "JOIN Peliculas p ON f.PeliculaID = p.PeliculaID "
                + "JOIN Salas s ON f.SalaID = s.SalaID";
        return db.getRecords(sql);
    }

    public List<Map<String, Object>> showFuncionDetails(int funcionID) {
        String sql = "SELECT f.FuncionID, m.Titulo,m.Duracion,f.Funcion from Funciones f, Peliculas m where f.PeliculaID = m.PeliculaID and f.FuncionID = " + funcionID;
        return db.getRecords(sql);
    }
}
