/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MSI
 */
public class DisplayFunctions extends javax.swing.JPanel {

    /**
     * Creates new form Form_5
     */
    public DisplayFunctions() {
        initComponents();
    }

    public void displayFunciones(List<Map<String, Object>> funcionesData) {
        // Check if there's data
        if (funcionesData == null || funcionesData.isEmpty()) {
            System.out.println("No data available for funciones.");
            return;
        }

        // Column names for the JTable
        String[] columnNames = {"Funcion ID", "Pelicula", "Sala", "Hora Funcion"};

        // Convert the data into a 2D array for JTable
        Object[][] data = new Object[funcionesData.size()][4];

        for (int i = 0; i < funcionesData.size(); i++) {
            Map<String, Object> funcion = funcionesData.get(i);
            data[i][0] = funcion.get("FuncionID");
            data[i][1] = funcion.get("Titulo");
            data[i][2] = funcion.get("Nombre");
            data[i][3] = funcion.get("funcion");
        }

        // Create a table model with the data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // If the JTable and JScrollPane are already initialized, just update the model
        if (funTable == null || funScrollPane == null) {
            funTable = new JTable(model);
            funScrollPane = new JScrollPane(funTable);

            // Add the scrollable table to the panel
            this.add(funScrollPane, BorderLayout.CENTER);
        } else {
            // Update the model if JTable already exists
            funTable.setModel(model);
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

        funScrollPane = new javax.swing.JScrollPane();
        funTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        funTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        funScrollPane.setViewportView(funTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(funScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 862, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(funScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane funScrollPane;
    private javax.swing.JTable funTable;
    // End of variables declaration//GEN-END:variables
}
