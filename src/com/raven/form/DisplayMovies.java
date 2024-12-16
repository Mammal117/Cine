package com.raven.form;

import com.raven.component.Card;
import com.raven.model.Funcion;
import com.raven.model.Model_Card;
import com.raven.model.Pelicula;
import com.raven.swing.ScrollBar;
import com.raven.swing.WrapLayout;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DisplayMovies extends javax.swing.JPanel {

    Pelicula pelicula;
    Funcion funcion = new Funcion();
    int filaSeleccionada = -1;

    public DisplayMovies() {
        initComponents();
        init();

    }

    public void init() {
        pelicula = new Pelicula();

        panel.removeAll();

        panel.setLayout(new WrapLayout(WrapLayout.LEADING));
        jScrollPane1.setVerticalScrollBar(new ScrollBar());

        // Aquí podrías cargar las películas desde la base de datos usando `showMovies()`
        List<Map<String, Object>> movies = pelicula.showMovies();
        for (Map<String, Object> movie : movies) {
            int idPelicula = (int) movie.get("PeliculaID"); // Obtener la ID de la película
            String title = (String) movie.get("Titulo");
            String genre = (String) movie.get("Genero");

            // Usar la portada de la base de datos o una por defecto si no tiene
            ImageIcon icon;
            if (movie.get("Profile") != null) {
                icon = new ImageIcon((byte[]) movie.get("Profile")); // Obtener la imagen de la base de datos
            } else {
                icon = new ImageIcon(getClass().getResource("/com/raven/icon/testing/14.jpg")); // Imagen por defecto
            }

            Card c;

            String description = "Género: " + genre + "\nDuración: " + movie.get("Duracion") + " mins\nSinopsis: " + movie.get("Sinopsis");
            panel.add(c = new Card((new Model_Card(icon, title, description, idPelicula))));
            //panel.add(new CardMain(new Model_Card(icon, title, description, idPelicula)));
            //c.editCover(idPelicula);
            c.getEdit().addActionListener(e -> {
                // Your code here to handle the button click
                System.out.println("Button clicked! " + idPelicula);
            });
        }

//        panel.add(new Card(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/testing/1.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Card(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/testing/2.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
        panel.revalidate();
        panel.repaint();
    }

    public void displayMovies(List<Map<String, Object>> moviesData) {
        // Check if there's data
        if (moviesData == null || moviesData.isEmpty()) {
            System.out.println("No data available for peliculas.");
            return;
        }

        // Column names for the JTable
        String[] columnNames = {"Titulo", "Genero"};

        // Convert the data into a 2D array for JTable
        Object[][] data = new Object[moviesData.size()][2];

        for (int i = 0; i < moviesData.size(); i++) {
            Map<String, Object> pelicula = moviesData.get(i);
            data[i][0] = pelicula.get("Titulo");
            data[i][1] = pelicula.get("Genero");
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

    public void displaySalas(List<Map<String, Object>> salasData) {
        // Check if there's data
        if (salasData == null || salasData.isEmpty()) {
            System.out.println("No data available for Salas.");
            return;
        }

        // Column names for the JTable
        String[] columnNames = {"Nombre", "Capacidad"};

        // Convert the data into a 2D array for JTable
        Object[][] data = new Object[salasData.size()][2];

        for (int i = 0; i < salasData.size(); i++) {
            Map<String, Object> sala = salasData.get(i);
            data[i][0] = sala.get("Nombre");
            data[i][1] = sala.get("Asientos");
        }

        // Create a table model with the data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // If the JTable and JScrollPane are already initialized, just update the model
        if (roomTable == null || roomScrollPane == null) {
            roomTable = new JTable(model);
            roomScrollPane = new JScrollPane(roomTable);

            // Add the scrollable table to the panel
            this.add(roomScrollPane, BorderLayout.CENTER);
        } else {
            // Update the model if JTable already exists
            roomTable.setModel(model);
        }

        // Ensure that the panel is revalidated and repainted to reflect the new table
        this.revalidate();
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        movieScrollPane = new javax.swing.JScrollPane();
        movieTable = new javax.swing.JTable();
        roomScrollPane = new javax.swing.JScrollPane();
        roomTable = new javax.swing.JTable();
        etIDSala = new javax.swing.JLabel();
        etIDPeli = new javax.swing.JLabel();
        etHoraFuncion = new javax.swing.JLabel();
        inputSala = new javax.swing.JTextField();
        inputPeli = new javax.swing.JTextField();
        inputHora = new javax.swing.JTextField();
        btnInsertar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);

        panel.setBackground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1023, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 774, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panel);

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

        roomTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        roomTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomTableMouseClicked(evt);
            }
        });
        roomScrollPane.setViewportView(roomTable);

        etIDSala.setText("Sala:");

        etIDPeli.setText("ID de la pelicula:");

        etHoraFuncion.setText("Hora de la funcion");

        btnInsertar.setText("Agregar");
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roomScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(movieScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(inputSala, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                        .addComponent(inputPeli, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(etIDSala, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(etIDPeli)))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inputHora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(etHoraFuncion, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnInsertar)
                                .addGap(130, 130, 130))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(movieScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(roomScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etHoraFuncion)
                            .addComponent(etIDPeli)
                            .addComponent(etIDSala))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputPeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addComponent(btnInsertar)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed
        // TODO add your handling code here:

//        int sala = Integer.parseInt(inputSala.getText());
//        int peli = Integer.parseInt(inputPeli.getText());
        String sala = (inputSala.getText());
        String peli = (inputPeli.getText());
        String horaFun = inputHora.getText();
        funcion.insertFuncion(peli, sala, horaFun);
        System.out.println(peli + " " + sala + horaFun);
        inputPeli.setText("");
        inputSala.setText("");
        inputHora.setText("");
    }//GEN-LAST:event_btnInsertarActionPerformed

    private void roomTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomTableMouseClicked
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        int column = source.columnAtPoint(evt.getPoint());

        if (row >= 0 && column >= 0) {
            filaSeleccionada = row;
            String name = source.getValueAt(row, 0).toString();
            int capacity = (int) source.getValueAt(row, 1);

            inputSala.setText(name);
        }
    }//GEN-LAST:event_roomTableMouseClicked

    private void movieTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieTableMouseClicked
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        int column = source.columnAtPoint(evt.getPoint());

        if (row >= 0 && column >= 0) {
            filaSeleccionada = row;
            String title = source.getValueAt(row, 0).toString();
            String genre = source.getValueAt(row, 0).toString();

            inputPeli.setText(title);
        }
    }//GEN-LAST:event_movieTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInsertar;
    private javax.swing.JLabel etHoraFuncion;
    private javax.swing.JLabel etIDPeli;
    private javax.swing.JLabel etIDSala;
    private javax.swing.JTextField inputHora;
    private javax.swing.JTextField inputPeli;
    private javax.swing.JTextField inputSala;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane movieScrollPane;
    private javax.swing.JTable movieTable;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane roomScrollPane;
    private javax.swing.JTable roomTable;
    // End of variables declaration//GEN-END:variables
}
