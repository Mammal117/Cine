![2024-12-17 21_12_57-](https://github.com/user-attachments/assets/5a87cc34-b14b-417d-a905-127c57deb1af)Este código es una aplicación gráfica que funciona como sistema de administración para un cine. Permite a un administrador gestionar salas, películas, funciones y su perfil mediante una interfaz gráfica en Java Swing. La ventana principal tiene un menú lateral con diferentes opciones, y al seleccionar una, se muestra el contenido correspondiente en el panel principal.

El programa carga datos desde clases que simulan una base de datos, como Sala, Pelicula y Funcion, y los muestra en formularios gráficos donde el administrador puede visualizarlos o modificarlos. Por ejemplo, al seleccionar "salas", se cargan las salas existentes y se muestran para editar o agregar nuevas. Algo similar ocurre con las películas y funciones.

El flujo general es: se inicia la aplicación, se abre la ventana principal, y el menú permite navegar entre las diferentes funciones de gestión. Además, el sistema permite cerrar sesión y regresar a una ventana de inicio de sesión. Todo está organizado en componentes y métodos para que cada acción del administrador se traduzca en un cambio en la interfaz gráfica y los datos.

![2024-12-17 20_53_25-](https://github.com/user-attachments/assets/a3fef882-adb1-4d25-a8c5-3210ebfcd614)
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
}    



![2024-12-17 20_53_42-](https://github.com/user-attachments/assets/eb0a4fe2-39bb-4f05-9178-030295cb5070)


El programa también implementa un sistema de eventos para manejar interacciones del usuario. Por ejemplo, cuando se selecciona una opción del menú lateral, se ejecuta un método que carga los datos necesarios y actualiza el panel principal. Esto asegura que la aplicación responde en tiempo real a las acciones del administrador.

Además, hay un manejo básico de conexión a una base de datos a través de la clase DatabaseOperation. Aunque no se muestra el código completo de esta clase, sugiere que los datos de las películas, funciones y salas se almacenan en algún lugar externo, probablemente una base de datos real o simulada. La aplicación interactúa con estas clases para consultar o actualizar los datos según sea necesario.

Por último, incluye una funcionalidad para gestionar sesiones de usuario. El administrador que ingresa a la aplicación se representa con un objeto de la clase ModelUser, que guarda información como su ID, nombre y correo. Esto permite personalizar la experiencia (como mostrar un perfil único) y controlar quién tiene acceso a la administración.

En resumen, el código combina interfaz gráfica, manejo de datos y lógica de negocio para crear una herramienta de gestión flexible y modular.

![2024-12-17 20_54_26-](https://github.com/user-attachments/assets/0bf08851-5202-4e32-ba53-88bf3243dc07)

El administrador puede agregar, modificar o eliminar información sobre las salas donde se proyectan películas, asegurándose de que todas estén configuradas correctamente. También puede registrar nuevas películas en el sistema, actualizarlas o eliminarlas según sea necesario, lo que le permite mantener actualizado el catálogo de películas disponibles.

Además, el administrador puede programar funciones, asociándolas con una sala y una película específicas, y configurar horarios para estas proyecciones. También tiene la posibilidad de visualizar toda la información registrada, como las salas, las películas y las funciones, lo que facilita la supervisión de los datos.

Desde la perspectiva personal, el administrador puede consultar su información de perfil y cerrar sesión cuando lo desee. Al cerrar sesión, la aplicación regresa al menú de inicio, permitiendo que otro usuario pueda acceder. Finalmente, existe una función relacionada con la gestión de boletos, que podría ser utilizada para supervisar o simular la venta de entradas. En resumen, el administrador tiene control absoluto sobre la configuración y operación del sistema.
![2024-12-17 21_11_57-](https://github.com/user-attachments/assets/b8562c51-dade-4c27-a0b5-0d2279685c03)
![2024-12-17 21_11_35-](https://github.com/user-attachments/assets/b0f7cb74-f08d-4a14-8617-ad3c162cf762)




Customer

package com.raven.main;

import com.raven.event.EventMenuSelected;
import com.raven.form.Perfil;
import com.raven.form.DisplayFunctions;
import com.raven.form.GetTicketDetails;
import com.raven.form.GetTicket;
import com.raven.model.Funcion;
import com.raven.model.ModelUser;
import com.raven.model.Ticket;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;

/**
 *
 * @author MSI
 */
public class Customer extends javax.swing.JFrame {

    List<Map<String, Object>> ticketsData = new Ticket().showAllTicketsByUser(1);

    /**
     * Creates new form EntryPoint
     */
    private ModelUser ctmer;
    private Perfil perfil;
    private DisplayFunctions displayFunctions;
    private GetTicketDetails displayTickets;
    private GetTicket ticketNuevo;
    //private GetTicketDetails ticketsDetails;
    private Funcion funcion;

    public Customer(ModelUser ctmer) {
        initComponents();
        //setBackground(new Color(0, 0, 0, 0));
        this.ctmer = ctmer;
        displayFunctions = new DisplayFunctions();
        funcion = new Funcion();
        ticketNuevo = new GetTicket();
        displayTickets = new GetTicketDetails();
        perfil = new Perfil(ctmer);

        menu.initMoving(Customer.this);
        menu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    setForm(perfil);
                } else if (index == 1) {
                    setForm(displayFunctions);
                    loadFuncionesData(funcion, displayFunctions);
                } else if (index == 2) {
                    setForm(ticketNuevo);
                    System.out.println("tickettt");
                } else if (index == 3) {
                    setForm(displayTickets);
                    displayTickets.displayTickets(ticketsData);
                    //new Customer().initComponents();
                    System.out.println("ticketdetails");
                } else if (index == 7) {
                    System.out.println("closing");
                    Customer.this.dispose();
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

    public Customer() {
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
                System.out.println("Datos cargados con exito.");
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

    public void loadTicketsData(Ticket t, GetTicketDetails gtd) {

        if (t == null) {
            System.out.println("funcion is null!");
        } else {
            List<Map<String, Object>> ticketsUserData = t.showAllTicketsByUser(1);

            // Debug: Print the data to see what we're getting
            if (ticketsUserData == null || ticketsUserData.isEmpty()) {
                System.out.println("No data found!");
            } else {
                System.out.println("Data loaded successfully.");
                // Print some sample data
                for (Map<String, Object> funcion : ticketsUserData) {
                    System.out.println(funcion);
                }
            }
            if (gtd == null) {
                System.out.println("form5 is null!");
            } else {
                gtd.displayTickets(ticketsUserData);
            }
        }
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
        menu = new com.raven.component.MenuCustomer();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorderLayout = new javax.swing.GroupLayout(panelBorder);
        panelBorder.setLayout(panelBorderLayout);
        panelBorderLayout.setHorizontalGroup(
            panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1050, Short.MAX_VALUE)
            .addGroup(panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE))
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
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    /**
     * @param user
     * @param args the command line arguments
     */
    //public static void main(Usuario user) {
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
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer(user).setVisible(true);
            }
        });
    }

    public static void main(String[] x) {
        ModelUser user = new ModelUser(2, "user", "correo", "Customer", null);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private com.raven.component.MenuCustomer menu;
    private com.raven.swing.PanelBorder panelBorder;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration                   
}

La clase Customer es una implementación en Java que define la interfaz gráfica y las funcionalidades específicas para los usuarios que interactúan con un sistema, presumiblemente un cine o sistema de tickets. Su propósito principal es gestionar la experiencia del cliente mediante un menú interactivo y dinámico que permite acceder a diferentes funcionalidades según las necesidades del usuario.

El sistema utiliza un diseño basado en eventos, donde al seleccionar un elemento del menú, se cargan diferentes formularios o vistas dentro del panel principal de la interfaz. Estas vistas incluyen el perfil del usuario, una lista de funciones disponibles (películas u otros eventos), un formulario para la creación de boletos y otra vista para revisar detalles de boletos adquiridos.

El constructor principal de la clase recibe un objeto ModelUser, que representa al cliente actual. Este objeto permite personalizar las funcionalidades según los datos específicos del usuario, como su identificación y privilegios. Además, se inicializan varias instancias de clases relacionadas con formularios (Perfil, DisplayFunctions, GetTicket, y GetTicketDetails) para manejar tareas específicas dentro del sistema.

El sistema incluye métodos dedicados para cargar datos dinámicamente. Por ejemplo, el método loadFuncionesData se encarga de obtener información sobre funciones disponibles y enviarla al formulario correspondiente para su visualización. De forma similar, el método loadTicketsData gestiona la obtención y visualización de datos de boletos adquiridos por el usuario. Estos métodos están diseñados para manejar posibles errores, como datos nulos o vacíos, con mensajes de depuración para facilitar la solución de problemas.

Un aspecto clave es el manejo del menú mediante eventos. Cuando el usuario selecciona una opción, el sistema identifica el índice del elemento seleccionado y realiza las acciones correspondientes, como cargar un formulario específico en el panel principal, cerrar la sesión del usuario o regresar al menú principal del sistema. Esto se logra a través de un componente de menú personalizado que facilita la interacción y la navegación.

La interfaz gráfica se construye con Swing, utilizando paneles y componentes personalizados como MenuCustomer y PanelBorder. La organización del diseño asegura que la interfaz sea responsiva y clara para el usuario, permitiendo transiciones suaves entre diferentes secciones del sistema.

En resumen, esta clase no solo ofrece una interfaz gráfica funcional, sino que también integra la lógica necesaria para proporcionar una experiencia personalizada, eficiente y adaptable a las necesidades de los clientes. La estructura modular y el uso de patrones de diseño aseguran que sea fácil de mantener y extender en el futuro.

![2024-12-17 21_12_57-](https://github.com/user-attachments/assets/9e929656-130c-4781-8445-4890999564bf)
![2024-12-17 21_13_52-](https://github.com/user-attachments/assets/c9eace7e-0653-4096-a89d-baea3f9c564e)
![2024-12-17 21_15_32-](https://github.com/user-attachments/assets/48cdbeb5-4d53-4978-acde-eadfe6d5e44b)








