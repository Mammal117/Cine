/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.raven.main.Administrador;
import com.raven.model.Sala;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AddRooms extends javax.swing.JPanel {

    Sala sala = new Sala();

    /**
     * Creates new form Form_1
     */
    public AddRooms() {
        initComponents();
    }

    public void displaySalas(List<Map<String, Object>> salasData) {
        // Check if there's data
        if (salasData == null || salasData.isEmpty()) {
            System.out.println("No data available for Salas.");
            return;
        }

        // Column names for the JTable
        String[] columnNames = {"Sala ID", "Nombre", "Capacidad"};

        // Convert the data into a 2D array for JTable
        Object[][] data = new Object[salasData.size()][3];

        for (int i = 0; i < salasData.size(); i++) {
            Map<String, Object> sala = salasData.get(i);
            data[i][0] = sala.get("SalaID");
            data[i][1] = sala.get("Nombre");
            data[i][2] = sala.get("Asientos");
        }

        // Create a table model with the data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // If the JTable and JScrollPane are already initialized, just update the model
        if (tablaSalas == null || jScrollPane1 == null) {
            tablaSalas = new JTable(model);
            jScrollPane1 = new JScrollPane(tablaSalas);

            // Add the scrollable table to the panel
            this.add(jScrollPane1, BorderLayout.CENTER);
        } else {
            // Update the model if JTable already exists
            tablaSalas.setModel(model);
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

        btnInsertar = new javax.swing.JButton();
        inputSala = new javax.swing.JTextField();
        inputCapacidad = new javax.swing.JTextField();
        lblSala = new javax.swing.JLabel();
        lblCapacidad = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSalas = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        btnInsertar.setBackground(new java.awt.Color(0, 204, 51));
        btnInsertar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnInsertar.setText("Agregar");
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
            }
        });

        lblSala.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSala.setText("Nombre de la sala:");

        lblCapacidad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCapacidad.setText("Capacidad:");

        tablaSalas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaSalas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(lblSala, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(300, 300, 300)
                        .addComponent(lblCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(404, 404, 404)
                        .addComponent(btnInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(inputSala, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(466, 466, 466)
                        .addComponent(inputCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(183, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSala, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputSala, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addComponent(btnInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(265, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed
        // TODO add your handling code here:
        String nombre = inputSala.getText().toUpperCase();
        int lugares = Integer.parseInt(inputCapacidad.getText());
        System.out.println(nombre + lugares);
        sala.insertSala(nombre, lugares);
        inputSala.setText("");
        inputCapacidad.setText("");
        new Administrador().loadSalasData(sala, this);
    }//GEN-LAST:event_btnInsertarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInsertar;
    private javax.swing.JTextField inputCapacidad;
    private javax.swing.JTextField inputSala;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCapacidad;
    private javax.swing.JLabel lblSala;
    private javax.swing.JTable tablaSalas;
    // End of variables declaration//GEN-END:variables
}
