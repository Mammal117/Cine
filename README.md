![2024-12-17 20_53_25-](https://github.com/user-attachments/assets/014dc4e7-3748-46d2-a983-06a7a6a56484)
Este código es una aplicación gráfica que funciona como sistema de administración para un cine. Permite a un administrador gestionar salas, películas, funciones y su perfil mediante una interfaz gráfica en Java Swing. La ventana principal tiene un menú lateral con diferentes opciones, y al seleccionar una, se muestra el contenido correspondiente en el panel principal.

El programa carga datos desde clases que simulan una base de datos, como Sala, Pelicula y Funcion, y los muestra en formularios gráficos donde el administrador puede visualizarlos o modificarlos. Por ejemplo, al seleccionar "salas", se cargan las salas existentes y se muestran para editar o agregar nuevas. Algo similar ocurre con las películas y funciones.

El flujo general es: se inicia la aplicación, se abre la ventana principal, y el menú permite navegar entre las diferentes funciones de gestión. Además, el sistema permite cerrar sesión y regresar a una ventana de inicio de sesión. Todo está organizado en componentes y métodos para que cada acción del administrador se traduzca en un cambio en la interfaz gráfica y los datos.
package com.raven.main;

import com.raven.event.EventMenuSelected;
import com.raven.form.AddRooms;
import com.raven.form.AddMovies;
import com.raven.form.DisplayFunctions;
import com.raven.form.GetTicket;
import com.raven.form.AddFunctions;
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
    private AddFunctions addFunctions;
    private AddRooms addRooms;
    private AddMovies addMovies;
    private DisplayFunctions displayFunctions;
    private GetTicket getTicket;
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
        db = new DatabaseOperation();
        sala = new Sala();
        pelicula = new Pelicula();
        funcion = new Funcion();
        perfil = new Perfil(admin);
        addRooms = new AddRooms();
        addMovies = new AddMovies();
        addFunctions = new AddFunctions();
        displayFunctions = new DisplayFunctions();
        getTicket = new GetTicket();

        List<Map<String, Object>> salasData = new Sala().showSalas();
        List<Map<String, Object>> peliculasData = new Pelicula().showMovies();

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
                    setForm(addMovies);
                    addMovies.displayMovies(peliculasData);
                } else if (index == 3) {
                    setForm(addFunctions);
                    addFunctions.displaySalas(salasData);
                    loadPeliculasData(pelicula, addFunctions);
                    loadSalasData(sala, addFunctions);
                } else if (index == 7) {
                    setForm(displayFunctions);
                    loadFuncionesData(funcion, displayFunctions);
                } else if (index == 8) {
                    setForm(getTicket);
                    System.out.println("gettickett");
                } else if (index == 9) {
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

    public void loadPeliculasData(Pelicula p, AddFunctions af) {

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

            if (af == null) {
                System.out.println("home2 is null!");
            } else {
                af.displayMovies(peliculasData);
            }
        }
        addFunctions.init();
    }

    public void loadPeliculasData(Pelicula p, AddMovies am) {

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

            if (am == null) {
                System.out.println("home2 is null!");
            } else {
                am.displayMovies(peliculasData);
            }
        }
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
    }

    public void loadSalasData(Sala s, AddFunctions af) {
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
        af.displaySalas(salasData);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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
    }// </editor-fold>                        

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
        ModelUser user = new ModelUser(1, "usedddddddddr", "correo", "Admin", null);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private com.raven.component.MenuAdmin menu;
    private com.raven.swing.PanelBorder panelBorder;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declarati on                    
}    ![2024-12-17 20_53_25-](https://github.com/user-attachments/assets/a3fef882-adb1-4d25-a8c5-3210ebfcd614)




