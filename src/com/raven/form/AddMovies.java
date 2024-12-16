/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.raven.model.Model_Card;
import com.raven.model.Pelicula;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAVEN
 */
public class AddMovies extends javax.swing.JPanel {

    int filaSeleccionada = -1;
    Pelicula pelicula = new Pelicula();

    /**
     * Creates new form Form_1
     */
    public AddMovies() {
        initComponents();
    }

    public void displayMovies(List<Map<String, Object>> moviesData) {
        // Check if there's data
        if (moviesData == null || moviesData.isEmpty()) {
            System.out.println("No data available for peliculas.");
            return;
        }

        // Column names for the JTable
        String[] columnNames = {"PeliculaID", "Titulo", "Genero", "Duracion", "Sinopsis"};

        // Convert the data into a 2D array for JTable
        Object[][] data = new Object[moviesData.size()][5];

        for (int i = 0; i < moviesData.size(); i++) {
            Map<String, Object> pelicula = moviesData.get(i);
            data[i][0] = pelicula.get("PeliculaID");
            data[i][1] = pelicula.get("Titulo");
            data[i][2] = pelicula.get("Genero");
            data[i][3] = pelicula.get("Duracion");
            data[i][4] = pelicula.get("Sinopsis");
        }

        // Create a table model with the data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // If the JTable and JScrollPane are already initialized, just update the model
        if (movieTable == null || movieScrollPane == null) {
            movieTable = new JTable(model);
            movieScrollPane = new JScrollPane(movieTable);

            // Add the scrollable table to the panel
            this.add(movieScrollPane, BorderLayout.CENTER);
        } else {
            // Update the model if JTable already exists
            movieTable.setModel(model);
        }

        // Ensure that the panel is revalidated and repainted to reflect the new table
        this.revalidate();
        this.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        lblDuracion = new javax.swing.JLabel();
        lblSinopsis = new javax.swing.JLabel();
        inputTitulo = new javax.swing.JTextField();
        inputGenero = new javax.swing.JTextField();
        inputDuracion = new javax.swing.JTextField();
        inputSinopsis = new javax.swing.JTextField();
        btnInsertar = new javax.swing.JButton();
        cardMovie1 = new com.raven.component.CardMovie();
        movieScrollPane = new javax.swing.JScrollPane();
        movieTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        lblTitulo.setText("Titulo");

        lblGenero.setText("Genero");

        lblDuracion.setText("Duracion");

        lblSinopsis.setText("Sinopsis");

        btnInsertar.setText("Agregar");
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
            }
        });

        movieTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        movieTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                movieTableMouseClicked(evt);
            }
        });
        movieScrollPane.setViewportView(movieTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cardMovie1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSinopsis)
                                    .addComponent(lblDuracion)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTitulo)
                                            .addComponent(lblGenero))
                                        .addGap(40, 40, 40)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(inputTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(inputDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(inputGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(inputSinopsis, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(49, 49, 49)
                        .addComponent(movieScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cardMovie1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTitulo)
                            .addComponent(inputTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGenero)
                            .addComponent(inputGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(movieScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDuracion)
                    .addComponent(inputDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSinopsis)
                    .addComponent(inputSinopsis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(btnInsertar)
                .addContainerGap(257, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed
        // TODO add your handling code here:

        String titulo = inputTitulo.getText();
        String genero = inputGenero.getText();
        int duracion = Integer.parseInt(inputDuracion.getText());
        String sinopsis = inputSinopsis.getText();
        pelicula.insertMovie(titulo, genero, duracion, sinopsis);
        System.out.println(titulo + genero + duracion + sinopsis);
        inputTitulo.setText("");
        inputGenero.setText("");
        inputDuracion.setText("");
        inputSinopsis.setText("");
    }//GEN-LAST:event_btnInsertarActionPerformed

    private void movieTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieTableMouseClicked
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        int column = source.columnAtPoint(evt.getPoint());

        if (row >= 0 && column >= 0) {
            filaSeleccionada = row;
            int id = (int) source.getValueAt(row, 0);
            String title = source.getValueAt(row, 1).toString();
            String genre = source.getValueAt(row, 2).toString();
            String duration = source.getValueAt(row, 3).toString();
            String synopsis = source.getValueAt(row, 4).toString();

            inputTitulo.setText(title);
            inputGenero.setText(genre);
            inputDuracion.setText(duration);
            inputSinopsis.setText(synopsis);

            List<Map<String, Object>> movieData = pelicula.getMovieData(id);
            for (Map<String, Object> poster : movieData) {
                ImageIcon icon;
                if (poster.get("Profile") != null) {
                    icon = new ImageIcon((byte[]) poster.get("Profile"));
                } else {
                    icon = new ImageIcon(getClass().getResource("/com/raven/icon/testing/14.jpg"));
                }
                cardMovie1.setData(new Model_Card(icon, id));
            }
        }
    }//GEN-LAST:event_movieTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInsertar;
    private com.raven.component.CardMovie cardMovie1;
    private javax.swing.JTextField inputDuracion;
    private javax.swing.JTextField inputGenero;
    private javax.swing.JTextField inputSinopsis;
    private javax.swing.JTextField inputTitulo;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblSinopsis;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane movieScrollPane;
    private javax.swing.JTable movieTable;
    // End of variables declaration//GEN-END:variables
}