package com.raven.model;

import com.raven.component.Card;
import com.raven.connection.DatabaseOperation;
import com.raven.swing.ImageAvatar;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

public class Pelicula {

    private DatabaseOperation db = new DatabaseOperation();

    public void insertMovie(String title, String genre, int duration, String synopsis) {
        String sql = "INSERT INTO Peliculas (titulo, genero, duracion, sinopsis) VALUES (?,?,?,?)";
        Object[] values = {title, genre, duration, synopsis};
        int rowsAffected = db.executeUpdate(sql, values);
        if (rowsAffected > 0) {
            System.out.println("Pelicula insertada exitosamente");
        } else {
            System.out.println("algo salio mal Movie no insertada.");
        }
    }

    public void uploadPoster(int movieID, JLabel poster) {
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
        int option = ch.showOpenDialog(poster);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = ch.getSelectedFile();
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            poster.setIcon(resizeIcon(icon));
            poster.repaint();
            try {
                String sql = "update `peliculas` set `Profile`=? where PeliculaID=? limit 1";
                Connection conn = db.connectToDatabase();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setBinaryStream(1, Files.newInputStream(file.toPath()));
                p.setInt(2, movieID);
                p.execute();
                System.out.println("Portada colocada para la película con ID: " + movieID);
            } catch (IOException | SQLException e) {
                System.err.println("Error updating image: " + e.getMessage());
                System.err.println(e);
            }
        }
    }

    private Icon resizeIcon(Icon icon) {
        Image img = ((ImageIcon) icon).getImage();
        Image resizedImg = img.getScaledInstance(200, 250, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    public void editPoster(int movieID, JPanel jp, JButton b) {
        // TODO add your handling code here:
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
        int option = ch.showOpenDialog(jp);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = ch.getSelectedFile();
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            b.setIcon(icon);
            jp.repaint();

            // Aquí obtienes la tarjeta (jp) y actualizas el icono
            if (jp instanceof Card) {
                ((Card) jp).updateIcon(icon);
            }

            try {
                String sql = "update `peliculas` set `Profile`=? where PeliculaID=? limit 1";
                Connection conn = db.connectToDatabase();
                PreparedStatement p = conn.prepareStatement(sql);
                p.setBinaryStream(1, Files.newInputStream(file.toPath()));
                p.setInt(2, movieID);
                p.execute();
                System.out.println("Portada actualizada para la película con ID: " + movieID);

            } catch (IOException | SQLException e) {
                System.err.println(e);
            }
        }
    }

    public List<Map<String, Object>> showMovies() {
        String sql = "SELECT * FROM Peliculas";
        return db.getRecords(sql);
    }

    public List<Map<String, Object>> getMovieData(int id) {
        String sql = "SELECT `Profile` FROM `Peliculas` where PeliculaID = " + id;
        return db.getRecords(sql);
    }

}
