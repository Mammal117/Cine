package com.raven.model;

import com.raven.connection.DatabaseOperation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ticket {

    DatabaseOperation db = new DatabaseOperation();

    public void pickSeat(int userID, int funcionID, int seatNumber) {
        //redirect to payment portal
        //insert their booking info in bookings table
        String sql = "insert into Tickets (UsuarioID,FuncionID,Asiento) values(?,?,?)";
        Object[] values = {userID, funcionID, seatNumber};
        int rowsAffected = db.executeUpdate(sql, values);
        if (rowsAffected > 0) {
            System.out.println("ticket completado con exito");
        } else {
            System.out.println("Error .ticket fallido."); //Something went wrong
        }
    }

    public ArrayList<Integer> getBookedSeats(int FuncionID) {
        String sql = "SELECT Asiento from Tickets where FuncionID = ?";
        ArrayList<Integer> bookedSeats = new ArrayList<>();

        try (Connection conn = db.connectToDatabase(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, FuncionID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookedSeats.add(rs.getInt("Asiento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookedSeats;
    }

    public List<Map<String, Object>> showAllTicketsByUser(int UserID) {
        //String sql = "SELECT * from Tickets where UsuarioID = " + UserID;
        String sql = "SELECT * FROM Tickets WHERE UsuarioID = " + UserID + " ORDER BY FuncionID";
        return db.getRecords(sql);
    }

    public int deleteTicket(int TicketID) {//void (0,1) int (1,2)// interseccion (1)
        //enter any booking id and works
        //future scope : to verify if booking is users or not
        String sql = "DELETE from Tickets where TicketID =?";
        Object[] values = {TicketID};
        int rowsAffected = db.executeUpdate(sql, values);
        if (rowsAffected > 0) {
            System.out.println("Booking cancelled successfully");
        } else {
            System.out.println("Something went wrong. it failed.");
        }
        return rowsAffected;
    }
}
