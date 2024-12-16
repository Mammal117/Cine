/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.raven.model.Ticket;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI
 */
public class CancelTicket extends javax.swing.JPanel {

    Ticket ticket;

    /**
     * Creates new form CancelTicket
     */
    public CancelTicket() {
        initComponents();
        ticket = new Ticket();
        loadTicketsData();
    }

    private void deleteTicket() {
        int choice = Integer.parseInt(IDText.getText());
        ticket.deleteTicket(choice);

    }

    private void loadTicketsData() {
        // Get the data from the Salas class
        //List<Map<String, Object>> peliculasData = pelicula.showMovies();

        if (ticket == null) {
            System.out.println("ticket is null!");
        } else {
            List<Map<String, Object>> ticketsData = ticket.showAllTicketsByUser(1);

            // Debug: Print the data to see what we're getting
            if (ticketsData == null || ticketsData.isEmpty()) {
                System.out.println("No data found!");
            } else {
                System.out.println("Datos cargados con exito.");
                // Print some sample data
                for (Map<String, Object> ticketData : ticketsData) {
                    System.out.println(ticketData);
                }
            }

            // Display the data in the SalasPanel (Form_2)
            //home2.displayMovies(peliculasData);
            if (displayTickets1 == null) {
                System.out.println("form5 is null!");
            } else {
                displayTickets1.displayTickets(ticketsData);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        displayTickets1 = new com.raven.form.DisplayTickets();
        IDText = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        lblTicketD = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        btnDelete.setText("eliminar");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblTicketD.setText("ID de Ticket");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(displayTickets1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete)
                    .addComponent(lblTicketD, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IDText, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(displayTickets1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(lblTicketD)
                .addGap(18, 18, 18)
                .addComponent(IDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnDelete)
                .addContainerGap(36, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        deleteTicket();
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDText;
    private javax.swing.JButton btnDelete;
    private com.raven.form.DisplayTickets displayTickets1;
    private javax.swing.JLabel lblTicketD;
    // End of variables declaration//GEN-END:variables
}
