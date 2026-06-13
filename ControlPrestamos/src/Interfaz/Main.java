package Interfaz;

import control.Controladora;  //Importamos la controladora
import javax.swing.JPanel;
import javax.swing.JTabbedPane; // Importamos el componente de pestañas

/**
 *
 * @author Jaquem Obando González
 */
public class Main extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());

    // Atributo de la lógica
    private Controladora controladora;
    
    //Declaramos el contenedor de pestañas principal como atributo
    private JTabbedPane tabsPrincipal;

    public Main() {
        initComponents();
        
        // Inicializamos la controladora y ajustamos la ventana
        this.controladora = new Controladora();
        this.setTitle("Control de Préstamos");
        this.setSize(700, 500); 
        this.setLocationRelativeTo(null); 
        
        //  Llamamos a crear el esqueleto de pestañas
        inicializarMenuPrincipal();
    }

    // Creamos las tres pestañas base que pide el documento
    private void inicializarMenuPrincipal() {
        tabsPrincipal = new JTabbedPane();
        
        // Paneles 
        JPanel panelAdministracion = new JPanel();
        JPanel panelPrestamos = new JPanel();
        JPanel panelReportes = new JPanel();
        
        // Añadimos las pestañas al contenedor principal
        tabsPrincipal.addTab("Administración", panelAdministracion);
        tabsPrincipal.addTab("Préstamos", panelPrestamos);
        tabsPrincipal.addTab("Reportes", panelReportes);
        
        // Colocamos el contenedor de pestañas como el fondo de la ventana
        this.setContentPane(tabsPrincipal);
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
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}