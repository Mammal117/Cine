/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.main;

import com.raven.event.EventMenuSelected;
import com.raven.form.AddRooms;
import com.raven.form.AddMovies;
import com.raven.form.AddFunctions;
import com.raven.form.DisplayFunctions;
import com.raven.form.GetTicket;
import com.raven.form.DisplayTickets;
import com.raven.form.DisplayMovies;
import com.raven.form.Perfil;
import com.raven.model.ModelUser;
import com.raven.connection.DatabaseOperation;
import com.raven.model.Funcion;
import com.raven.model.Pelicula;
import com.raven.model.Sala;

import java.util.List;
import java.util.Map;
import javax.swing.JComponent;

/**
 *
 * @author MSI
 */
public class Administrador extends javax.swing.JFrame {

    /**
     * Creates new form EntryPoint
     */
    private DisplayMovies displayMovies;
    private AddRooms addRooms;
    private AddMovies addMovies;
    private AddFunctions addFunctions;
    private DisplayFunctions displayFunctions;
    private GetTicket form6;
    private DisplayTickets form7;
    private ModelUser admin;
    private Sala sala;
    private Pelicula pelicula;
    private Funcion funcion;
    private DatabaseOperation db;
    private Perfil perfil;

    public Administrador(ModelUser admin) {
        initComponents();
        //setBackground(new Color(0, 0, 0, 0));
        this.admin = admin;
        addRooms = new AddRooms();
        addMovies = new AddMovies();
        displayMovies = new DisplayMovies();
        addFunctions = new AddFunctions();
        displayFunctions = new DisplayFunctions();
        form6 = new GetTicket();
        form7 = new DisplayTickets();
        perfil = new Perfil(admin);
        sala = new Sala();
        pelicula = new Pelicula();
        funcion = new Funcion();
        db = new DatabaseOperation();

        menu.initMoving(Administrador.this);
        menu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    setForm(perfil);
                } else if (index == 1) {
                    setForm(addRooms);
                    loadSalasData(sala, addRooms);
                } else if (index == 2) {
                    loadSalasData(sala, addRooms);
                } else if (index == 3) {
                    setForm(addMovies);
                } else if (index == 4) {
                    setForm(displayMovies);
                    loadPeliculasData(pelicula, displayMovies);
                    loadSalasData(sala, addRooms);
                    System.out.println("shsdh");
                } else if (index == 8) {
                    setForm(addFunctions);
                } else if (index == 9) {
                    setForm(displayFunctions);
                    loadFuncionesData(funcion, displayFunctions);
                } else if (index == 10) {
                    setForm(form6);
                    System.out.println("gettickertrr");
                } else if (index == 11) {
                    setForm(form7);
                } else if (index == 12) {
                    //System.exit(0);
                    Administrador.this.dispose();
                    // Open the Main frame for login/register
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new Main().setVisible(true);  // Open Main frame
                        }
                    });
                }
            }

        });
        //  set when system open start with home form
        //setForm(new DisplayMovies());
        setForm(perfil);
    }

    public Administrador() {
    }

    private void setForm(JComponent com) {
        panelPrincipal.removeAll();
        panelPrincipal.add(com);
        panelPrincipal.repaint();
        panelPrincipal.revalidate();
    }

    private void loadFuncionesData(Funcion f, DisplayFunctions df) {

        if (f == null) {
            System.out.println("funcion is null!");
        } else {
            List<Map<String, Object>> funcionesData = f.showFunciones();

            // Debug: Print the data to see what we're getting
            if (funcionesData == null || funcionesData.isEmpty()) {
                System.out.println("No data found!");
            } else {
                System.out.println("Data loaded successfully.");
                // Print some sample data
                for (Map<String, Object> funcion : funcionesData) {
                    System.out.println(funcion);
                }
            }
            if (df == null) {
                System.out.println("form5 is null!");
            } else {
                df.displayFunciones(funcionesData);
            }
        }
    }

    private void loadPeliculasData(Pelicula p, DisplayMovies dm) {

        if (p == null) {
            System.out.println("pelicula is null!");
        } else {
            List<Map<String, Object>> peliculasData = p.showMovies();

            // Debug: Print the data to see what we're getting
            if (peliculasData == null || peliculasData.isEmpty()) {
                System.out.println("No data found!");
            } else {
                System.out.println("Data loaded successfully.");
                // Print some sample data
                for (Map<String, Object> peli : peliculasData) {
                    System.out.println(peli);
                }
            }

            if (dm == null) {
                System.out.println("home2 is null!");
            } else {
                dm.displayMovies(peliculasData);
                addMovies.displayMovies(peliculasData);
            }
        }
        displayMovies.init();
    }

    public void loadSalasData(Sala s, AddRooms ad) {
        // Get the data from the Salas class
        List<Map<String, Object>> salasData = s.showSalas();

        // Debug: Print the data to see what we're getting
        if (salasData == null || salasData.isEmpty()) {
            System.out.println("No data found!");
        } else {
            System.out.println("Data loaded successfully.");
            // Print some sample data
            for (Map<String, Object> sala : salasData) {
                System.out.println(sala);
            }
        }

        // Display the data in the SalasPanel (Form_2)
        ad.displaySalas(salasData);
        displayMovies.displaySalas(salasData);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder = new com.raven.swing.PanelBorder();
        panelPrincipal = new javax.swing.JPanel();
        menu = new com.raven.component.MenuAdmin();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBorder.setPreferredSize(new java.awt.Dimension(1060, 840));

        panelPrincipal.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorderLayout = new javax.swing.GroupLayout(panelBorder);
        panelBorder.setLayout(panelBorderLayout);
        panelBorderLayout.setHorizontalGroup(
            panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
            .addGroup(panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE))
        );
        panelBorderLayout.setVerticalGroup(
            panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 840, Short.MAX_VALUE)
            .addGroup(panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(ModelUser user) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador(user).setVisible(true);
            }
        });
    }

    public static void main(String[] x) {
        ModelUser user = new ModelUser("usedddddddddr", 1, "correo", "Admin", null);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.component.MenuAdmin menu;
    private com.raven.swing.PanelBorder panelBorder;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
