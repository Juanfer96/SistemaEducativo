
package Visual;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import sistemaeducativo.Asignatura;
import sistemaeducativo.Facultad;
import sistemaeducativo.Persistencia;

/**
 *
 * @author Usuario
 */
public class VentanaPrincipal extends javax.swing.JFrame
{
    private Facultad facultad;
    
    /** Creates new form VentanaPrincipal */
    public VentanaPrincipal(Facultad facultad)
    {
        initComponents();
        this.facultad=facultad;
        cerrar();
        setResizable(false);
        setTitle("Sistema Educativo ");
        setLocationRelativeTo(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        jPanel1 = new javax.swing.JPanel();
        jButtonAlumnoPrincipal = new javax.swing.JButton();
        jButtonAsignaturaPrincipal = new javax.swing.JButton();
        jButtonProfesorPrincipal = new javax.swing.JButton();
        jButtonCursadaPrincipal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.GridLayout(2, 2));

        jButtonAlumnoPrincipal.setText("Alumno");
        jButtonAlumnoPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlumnoPrincipalActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAlumnoPrincipal);

        jButtonAsignaturaPrincipal.setText("Asignatura");
        jButtonAsignaturaPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAsignaturaPrincipalActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAsignaturaPrincipal);

        jButtonProfesorPrincipal.setText("Profesor");
        jButtonProfesorPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProfesorPrincipalActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonProfesorPrincipal);

        jButtonCursadaPrincipal.setText("Cursada");
        jButtonCursadaPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCursadaPrincipalActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCursadaPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addGap(97, 97, 97))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );

        pack();
    }//GEN-END:initComponents

    public void cerrar() {
        try{
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    confirmarSalida();
                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void confirmarSalida() {
        int valor=JOptionPane.showConfirmDialog(this,"�Esta seguro de cerrar la aplicacion?","Advertencia",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(valor==JOptionPane.YES_OPTION){
            Persistencia.guardarArchivo(this.facultad);
            System.exit(0);
        }
    }
    
    private void jButtonAlumnoPrincipalActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAlumnoPrincipalActionPerformed
    {//GEN-HEADEREND:event_jButtonAlumnoPrincipalActionPerformed
        VentanaAlumno ventanaAlumno = new VentanaAlumno(facultad, this);
        ventanaAlumno.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonAlumnoPrincipalActionPerformed

    private void jButtonProfesorPrincipalActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonProfesorPrincipalActionPerformed
    {//GEN-HEADEREND:event_jButtonProfesorPrincipalActionPerformed
        // TODO add your handling code here:
        VentanaProfesor ventanaProfesor=new VentanaProfesor(this.facultad,this);
        ventanaProfesor.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonProfesorPrincipalActionPerformed

    private void jButtonAsignaturaPrincipalActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAsignaturaPrincipalActionPerformed
    {//GEN-HEADEREND:event_jButtonAsignaturaPrincipalActionPerformed
        // TODO add your handling code here:
        VentanaAsignatura ventanaAsignatura=new VentanaAsignatura(this.facultad,this);
        ventanaAsignatura.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonAsignaturaPrincipalActionPerformed

    private void jButtonCursadaPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCursadaPrincipalActionPerformed
        //Cambiar
        VentanaAltasBajasHorariosCursada ventana=new VentanaAltasBajasHorariosCursada(this.facultad,this);
        ventana.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCursadaPrincipalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        Facultad facultad=Persistencia.leerArchivo();
        Asignatura a=facultad.agregarAsignatura("FISICA");
        facultad.agregarCursada(a, "1");
        VentanaPrincipal ventana = new VentanaPrincipal(facultad);
        ventana.setVisible(true);
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing
                                                                   .UIManager
                                                                   .getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing
                         .UIManager
                         .setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(VentanaPrincipal.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(VentanaPrincipal.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(VentanaPrincipal.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(VentanaPrincipal.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt
            .EventQueue
            .invokeLater(new Runnable()
            {
                public void run()
                {
                    //VentanaPrincipal ventana = new VentanaPrincipal(Persistencia.leerArchivo());
                    //ventana.setVisible(true);
                }
            });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlumnoPrincipal;
    private javax.swing.JButton jButtonAsignaturaPrincipal;
    private javax.swing.JButton jButtonCursadaPrincipal;
    private javax.swing.JButton jButtonProfesorPrincipal;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    }
