
package Visual;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import java.util.Set;
import java.util.TreeMap;

import javax.swing.DefaultListModel;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import sistemaeducativo.Alumno;
import sistemaeducativo.Asignatura;
import sistemaeducativo.AsignaturaAprobadaYaRegistradaException;
import sistemaeducativo.AsignaturaYaRegistradaEnProfesorException;
import sistemaeducativo.Cursada;
import sistemaeducativo.Facultad;
import sistemaeducativo.Profesor;

/**
 *
 * @author Usuario
 */
public class VentanaAltasBajasCompetencia extends javax.swing.JFrame
{

    /** Creates new form VentanaAltasBajasCompetencia */
    private Facultad facultad;
    private VentanaProfesor ventana;
    private DefaultListModel modeloBuscar;
    private DefaultListModel modeloAlta;
    private DefaultListModel modeloBaja;
    public VentanaAltasBajasCompetencia(Facultad f,VentanaProfesor ventana)
    {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        facultad=f;
        this.ventana=ventana;
        this.cerrar();
        setResizable(false);
        setTitle("Sistema Educativo -Profesor-Altas/Bajas Competencia");
        setLocationRelativeTo(null);
        this.modeloBaja=new DefaultListModel();
        this.modeloBuscar=new DefaultListModel();
        this.modeloAlta=new DefaultListModel();
    }

    public void limpiarModelo()
    {
        this.modeloAlta.clear();
        this.modeloBaja.clear();
        this.modeloBuscar.clear();
        
    }
    
    public void cerrar() {
        try{
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    ventana.setVisible(true);
                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents()//GEN-BEGIN:initComponents
    {

        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabelNombreMod2 = new javax.swing.JLabel();
        jTextFieldNombre2 = new javax.swing.JTextField();
        jLabelApellidoMod2 = new javax.swing.JLabel();
        jTextFieldApellido2 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jButtonBuscarMod2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabelSeleccionarMod = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListProfesores = new javax.swing.JList<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListAsignaturasAltas = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jButtonAlta = new javax.swing.JButton();
        jButtonVolverAlta = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListAsignaturasBaja = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jButtonBaja = new javax.swing.JButton();
        jButtonVolverBaja = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jButtonCargar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel6.setLayout(new java.awt.GridLayout(2, 2));

        jLabelNombreMod2.setText("Nombre del profesor");
        jPanel6.add(jLabelNombreMod2);
        jPanel6.add(jTextFieldNombre2);

        jLabelApellidoMod2.setText("Apellido del profesor");
        jPanel6.add(jLabelApellidoMod2);
        jPanel6.add(jTextFieldApellido2);

        jPanel11.setLayout(new java.awt.GridLayout());

        jButtonBuscarMod2.setText("Buscar");
        jButtonBuscarMod2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonBuscarMod2ActionPerformed(evt);
            }
        });
        jPanel11.add(jButtonBuscarMod2);

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setLayout(new java.awt.GridLayout());

        jLabelSeleccionarMod.setText("Seleccione el profesor");
        jPanel7.add(jLabelSeleccionarMod);

        jListProfesores.setModel(new javax.swing.AbstractListModel<String>()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListProfesores);

        jPanel7.add(jScrollPane2);

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Alta");

        jListAsignaturasAltas.setModel(new javax.swing.AbstractListModel<String>()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListAsignaturasAltas);

        jLabel2.setText("Seleccione una Asignatura ");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 130, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel13.setLayout(new java.awt.GridLayout(2, 0));

        jButtonAlta.setText("A�adir a la competencia");
        jButtonAlta.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonAltaActionPerformed(evt);
            }
        });
        jPanel13.add(jButtonAlta);

        jButtonVolverAlta.setText("Volver");
        jButtonVolverAlta.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonVolverAltaActionPerformed(evt);
            }
        });
        jPanel13.add(jButtonVolverAlta);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Baja");

        jListAsignaturasBaja.setModel(new javax.swing.AbstractListModel<String>()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jListAsignaturasBaja);

        jLabel4.setText("Asignaturas habilitadas del profesor-Seleccione una");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 3, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel16.setLayout(new java.awt.GridLayout(2, 0));

        jButtonBaja.setText("Eliminar de la compentencia");
        jButtonBaja.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonBajaActionPerformed(evt);
            }
        });
        jPanel16.add(jButtonBaja);

        jButtonVolverBaja.setText("Volver");
        jButtonVolverBaja.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonVolverBajaActionPerformed(evt);
            }
        });
        jPanel16.add(jButtonVolverBaja);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel17.setLayout(new java.awt.GridLayout());

        jButtonCargar.setText("Cargar");
        jButtonCargar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCargarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(296, 296, 296)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(176, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCargar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 445, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(420, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(80, 80, 80)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(328, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(206, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }//GEN-END:initComponents

    private void jButtonBuscarMod2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBuscarMod2ActionPerformed
    {//GEN-HEADEREND:event_jButtonBuscarMod2ActionPerformed
        // TODO add your handling code here
        String nombre=this.jTextFieldNombre2.getText().toUpperCase();
        String apellido=this.jTextFieldApellido2.getText().toUpperCase();
        ArrayList<Profesor> profesores=this.facultad.buscarProfesorPorNombre(nombre, apellido);;
        Iterator it=profesores.iterator();

        while(it.hasNext())
        {
            Profesor a=(Profesor)it.next();
            modeloBuscar.addElement(a);
        }
        this.jListProfesores.setModel(modeloBuscar);
    }//GEN-LAST:event_jButtonBuscarMod2ActionPerformed

    private void jButtonAltaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAltaActionPerformed
    {//GEN-HEADEREND:event_jButtonAltaActionPerformed

        int n=this.jListProfesores.getSelectedIndex();
        Profesor p=(Profesor) this.modeloBuscar.getElementAt(n);
        int x=this.jListAsignaturasAltas.getSelectedIndex();
        Asignatura asig=(Asignatura) this.modeloAlta.getElementAt(x);
        try
        {
            this.facultad.agregarCompetenciaProfesor(p, asig);
            JOptionPane.showMessageDialog(null, "La asignatura fue a�adida con exito");
        } catch (AsignaturaYaRegistradaEnProfesorException e)
        {
            JOptionPane.showMessageDialog(null, "La asignatura ya se encuentra como habilitada");
        }
        this.limpiarModelo();
    }//GEN-LAST:event_jButtonAltaActionPerformed

    private void jButtonVolverAltaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonVolverAltaActionPerformed
    {//GEN-HEADEREND:event_jButtonVolverAltaActionPerformed
        this.ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonVolverAltaActionPerformed

    private void jButtonBajaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBajaActionPerformed
    {//GEN-HEADEREND:event_jButtonBajaActionPerformed
        // TODO add your handling code here:
        int n=this.jListProfesores.getSelectedIndex();
        Profesor p=(Profesor) this.modeloBuscar.getElementAt(n);
        int x=this.jListAsignaturasBaja.getSelectedIndex();
        Asignatura asig=(Asignatura) this.modeloBaja.getElementAt(x);
        this.facultad.eliminarCompetenciaProfesor(p, asig);
        JOptionPane.showMessageDialog(null, "La asignatura fue eliminada con exito");
        this.limpiarModelo();
    }//GEN-LAST:event_jButtonBajaActionPerformed

    private void jButtonVolverBajaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonVolverBajaActionPerformed
    {//GEN-HEADEREND:event_jButtonVolverBajaActionPerformed
        this.ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonVolverBajaActionPerformed

    private void jButtonCargarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCargarActionPerformed
    {//GEN-HEADEREND:event_jButtonCargarActionPerformed
        // TODO add your handling code here:
        int n=this.jListProfesores.getSelectedIndex();
        Profesor p=(Profesor) this.modeloBuscar.getElementAt(n);
        TreeMap<String,Asignatura> asignaturas=this.facultad.getAsignaturas();
        Set keys = asignaturas.keySet();
           for (Iterator i = keys.iterator(); i.hasNext();) {
             String key = (String) i.next();
             Asignatura a = (Asignatura) asignaturas.get(key);
             this.modeloAlta.addElement(a);
           }
        this.jListAsignaturasAltas.setModel(modeloAlta);

        Hashtable<String,Asignatura> historia=p.getCompetencia();
        Enumeration e = historia.elements();
        Asignatura asig;
        while( e.hasMoreElements() )
        {
            asig =(Asignatura) e.nextElement();
            this.modeloBaja.addElement(asig);
        }
        this.jListAsignaturasBaja.setModel(modeloBaja);
    }//GEN-LAST:event_jButtonCargarActionPerformed

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
                .getLogger(VentanaAltasBajasCompetencia.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(VentanaAltasBajasCompetencia.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(VentanaAltasBajasCompetencia.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(VentanaAltasBajasCompetencia.class.getName())
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
                    //new VentanaAltasBajasCompetencia().setVisible(true);
                }
            });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlta;
    private javax.swing.JButton jButtonBaja;
    private javax.swing.JButton jButtonBuscarMod;
    private javax.swing.JButton jButtonBuscarMod1;
    private javax.swing.JButton jButtonBuscarMod2;
    private javax.swing.JButton jButtonCargar;
    private javax.swing.JButton jButtonVolverAlta;
    private javax.swing.JButton jButtonVolverBaja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelApellidoMod;
    private javax.swing.JLabel jLabelApellidoMod1;
    private javax.swing.JLabel jLabelApellidoMod2;
    private javax.swing.JLabel jLabelNombreMod;
    private javax.swing.JLabel jLabelNombreMod1;
    private javax.swing.JLabel jLabelNombreMod2;
    private javax.swing.JLabel jLabelSeleccionarMod;
    private javax.swing.JList<String> jListAsignaturasAltas;
    private javax.swing.JList<String> jListAsignaturasBaja;
    private javax.swing.JList<String> jListProfesores;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldApellido1;
    private javax.swing.JTextField jTextFieldApellido2;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNombre1;
    private javax.swing.JTextField jTextFieldNombre2;
    // End of variables declaration//GEN-END:variables

}