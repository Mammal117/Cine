/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.raven.main.Customer;
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
public class DisplayTickets extends javax.swing.JPanel {

    Customer c;

    /**
     * Creates new form Form_7
     */
    public DisplayTickets() {
        initComponents();
    }

    public void displayTickets(List<Map<String, Object>> ticketsData) {
        // Check if there's data
        if (ticketsData == null || ticketsData.isEmpty()) {
            System.out.println("No data available for tickets.");
            return;
        }

        // Column names for the JTable
        String[] columnNames = {"Ticket ID", "Funcion ID", "Asiento"};

        // Convert the data into a 2D array for JTable
        Object[][] data = new Object[ticketsData.size()][3];

        for (int i = 0; i < ticketsData.size(); i++) {
            Map<String, Object> ticket = ticketsData.get(i);
            data[i][0] = ticket.get("TicketID");
            data[i][1] = ticket.get("FuncionID");
            data[i][2] = ticket.get("Asiento");
        }

        // Create a table model with the data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // If the JTable and JScrollPane are already initialized, just update the model
        if (ticketTable == null || ticketScrollPane == null) {
            ticketTable = new JTable(model);
            ticketScrollPane = new JScrollPane(ticketTable);

            // Add the scrollable table to the panel
            this.add(ticketScrollPane, BorderLayout.CENTER);
        } else {
            // Update the model if JTable already exists
            ticketTable.setModel(model);
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

        ticketScrollPane = new javax.swing.JScrollPane();
        ticketTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        ticketTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        ticketScrollPane.setViewportView(ticketTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addComponent(ticketScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(ticketScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ticketScrollPane;
    private javax.swing.JTable ticketTable;
    // End of variables declaration//GEN-END:variables
}
