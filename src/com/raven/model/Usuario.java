package com.raven.model;

import com.raven.connection.DatabaseOperation;
import com.raven.swing.ImageAvatar;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class Usuario {

    private DatabaseOperation db = new DatabaseOperation();

    public void updateProfilePicture(int userID, ImageAvatar pic) {
        JFileChooser ch = new JFileChooser();
        ch.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                String name = file.getName().toLowerCase();
                return file.isDirectory() || name.endsWith(".png") || name.endsWith(".jpg");
            }

            @Override
            public String getDescription() {
                return "Image File";
            }
        });
        int option = ch.showOpenDialog(pic);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = ch.getSelectedFile();
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            pic.setIcon(icon);
            pic.repaint();
            try {
                String sql = "update `user` set `Profile`=? where UserID=? limit 1";
                Connection conn = db.connectToDatabase();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setBinaryStream(1, Files.newInputStream(file.toPath()));
                p.setInt(2, userID);
                p.execute();
            } catch (IOException | SQLException e) {
                System.err.println("Error updating image: " + e.getMessage());
                System.err.println(e);
            }
        }
    }

    public ModelUser getUsuario(String username, String pass) {
        ModelUser userData = null;
        String sql = "SELECT UsuarioID, NombreDeUsuario, Correo, Rol from `usuarios` where BINARY(NombreDeUsuario)=? and BINARY(`Contraseña`)=? limit 1";
        try (Connection conn = db.connectToDatabase();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt(1);
                username = rs.getString(2);
                String email = rs.getString(3);
                String role = rs.getString(4);
                userData = new ModelUser(userID, username, email, role);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userData;
    }

    public List<Map<String, Object>> getUserData(int id) {
        String sql = "SELECT * FROM `user` where UserID = " + id;
        return db.getRecords(sql);
    }
}
