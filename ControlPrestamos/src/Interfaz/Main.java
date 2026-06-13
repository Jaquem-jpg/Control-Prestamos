package Interfaz;

import control.Controladora;  //Importamos la controladora
import java.awt.GridLayout;   // Para ordenar los campos de texto
import javax.swing.*;

/**
 *
 * @author Jaquem Obando González
 */
public class Main extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());

    // Atributo de la lógica
    private Controladora controladora;
    
    // Contenedor de pestañas principal como atributo
    private JTabbedPane tabsPrincipal;
    
    //  Declaramos los componentes visuales de Personas
    private JTextField txtEmail, txtNombre, txtTelefono;
    private JButton btnRegistrarUsuario;

    public Main() {
        initComponents();
        
        // Inicializamos la controladora y ajustamos la ventana
        this.controladora = new Controladora();
        this.setTitle("Control de Prestamos");
        this.setSize(700, 500); 
        this.setLocationRelativeTo(null); 
        
        // Llamamos a crear el esqueleto de pestañas
        inicializarMenuPrincipal();
    }

    
    private void inicializarMenuPrincipal() {
        tabsPrincipal = new JTabbedPane();
        
        //  Contenedor secundario para sub-pestañas de Administración
        JTabbedPane tabsAdministracion = new JTabbedPane();
        
        // Invocamos el panel que va a construir el formulario
        JPanel panelPersonas = crearPanelPersonas(); 
        JPanel panelItems = new JPanel();
        JPanel panelCategorias = new JPanel();
        JPanel panelTipos = new JPanel();
        
        // Agregamos las sub-pestañas al contenedor de administración
        tabsAdministracion.addTab("Personas", panelPersonas);
        tabsAdministracion.addTab("Items", panelItems);
        tabsAdministracion.addTab("Categorias", panelCategorias);
        tabsAdministracion.addTab("Tipos", panelTipos);
        
        // Paneles para los otros bloques principales
        JPanel panelPrestamos = new JPanel();
        JPanel panelReportes = new JPanel();
        
        // Añadimos los bloques al contenedor principal
        tabsPrincipal.addTab("Administracion", tabsAdministracion);
        tabsPrincipal.addTab("Prestamos", panelPrestamos);
        tabsPrincipal.addTab("Reportes", panelReportes);
        
        // Colocamos el contenedor de pestañas como el fondo de la ventana
        this.setContentPane(tabsPrincipal);
    }

    // Método  para construir el formulario de Personas
    //  Conectamos el botón a la lógica mediante un evento
    private JPanel crearPanelPersonas() {
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel gridCampos = new JPanel(new GridLayout(3, 2, 10, 10));
        
        gridCampos.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        gridCampos.add(txtEmail);

        gridCampos.add(new JLabel("Nombre Completo:"));
        txtNombre = new JTextField();
        gridCampos.add(txtNombre);

        gridCampos.add(new JLabel("Telefono:"));
        txtTelefono = new JTextField();
        gridCampos.add(txtTelefono);

        JPanel panelBoton = new JPanel();
        btnRegistrarUsuario = new JButton("Registrar Persona");
        panelBoton.add(btnRegistrarUsuario);

        // 1. Añadimos el listener para capturar el clic del usuario
        btnRegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarUsuarioActionPerformed(evt);
            }
        });

        panelForm.add(gridCampos);
        panelForm.add(Box.createVerticalStrut(15)); 
        panelForm.add(panelBoton);

        return panelForm;
    }

    // 2. Método que extrae los textos y llama de manera segura a la controladora
    private void btnRegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {
        String email = txtEmail.getText().trim();
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();

        // Validación básica en la vista para no enviar datos vacíos
        if (email.isEmpty() || nombre.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos Vacios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Invocamos el backend a través de la controladora
            controladora.registrarUsuario(email, nombre, telefono);
            
            // Si todo sale bien, avisamos al usuario
            JOptionPane.showMessageDialog(this, "Persona registrada con exito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpiamos los campos para un nuevo registro
            txtEmail.setText("");
            txtNombre.setText("");
            txtTelefono.setText("");
            
        } catch (Exception ex) {
            // Atrapamos cualquier excepción de la lógica (ej: usuario duplicado) y la mostramos
            JOptionPane.showMessageDialog(this, "Error al registrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}