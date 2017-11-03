
package Visual;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.DefaultListModel;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import sistemaeducativo.Alumno;
import sistemaeducativo.AlumnoInhabilitadoException;
import sistemaeducativo.AlumnoOcupadoParaCursadaException;
import sistemaeducativo.AlumnoRegistradoEnCursadaException;
import sistemaeducativo.Asignatura;
import sistemaeducativo.Cursada;
import sistemaeducativo.Facultad;
import sistemaeducativo.Fecha;
import sistemaeducativo.NoExisteEntidadException;

/**
 *
 * @author Usuario
 */
public class VentanaAltasBajasAlumnosCursada extends javax.swing.JFrame
{

    /** Creates new form VentanaAltasBajasAlumnosCursada */
    private Facultad facultad;
    private VentanaCursada ventana;
    private DefaultListModel modeloCursadas;
    private DefaultListModel modeloAlumnosAlta;
    private DefaultListModel modeloAlumnosBaja;
    public VentanaAltasBajasAlumnosCursada(Facultad f, VentanaCursada ventana)
    {
        initComponents();
        this.facultad=f;
        this.ventana=ventana;
        this.cerrar();
        setResizable(false);
        setTitle("Sistema Educativo -Altas/Bajas alumnos de cursadas");
        setLocationRelativeTo(null);
        this.modeloCursadas=new DefaultListModel();
        this.modeloAlumnosAlta=new DefaultListModel();
        this.modeloAlumnosBaja=new DefaultListModel();
    }
    public void limpiarModelo()
    {
        this.modeloCursadas.clear();
        this.modeloAlumnosAlta.clear();
        this.modeloAlumnosBaja.clear();
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
    private void initComponents() {//GEN-BEGIN:initComponents

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNombreCursada = new javax.swing.JTextField();
        jButtonBuscarCursadas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListCursadas = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListAlumnosBaja = new javax.swing.JList<>();
        jButtonEliminarAlumno = new javax.swing.JButton();
        LabelSinAlumnos = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldApellido = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListAlumnosAlta = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        jButtonCargar = new javax.swing.JButton();
        jButtonAgregarAlumno = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        LabelIdCursada = new javax.swing.JLabel();
        jButtonVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignar y eliminar alumnos de cursadas"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "B�squeda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 102))); // NOI18N

        jLabel1.setText("Asignatura:");

        jButtonBuscarCursadas.setText("Buscar cursadas");
        jButtonBuscarCursadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarCursadasActionPerformed(evt);
            }
        });

        jListCursadas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListCursadasValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListCursadas);

        jLabel2.setText("Seleccione cursada:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldNombreCursada, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButtonBuscarCursadas, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldNombreCursada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBuscarCursadas)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Baja de alumnos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 102))); // NOI18N

        jLabel8.setText("Seleccione alumno:");

        jScrollPane2.setViewportView(jListAlumnosBaja);

        jButtonEliminarAlumno.setText("Eliminar Alumno");
        jButtonEliminarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarAlumnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LabelSinAlumnos)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(23, 23, 23))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(LabelSinAlumnos))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEliminarAlumno)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alta de alumnos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 102))); // NOI18N

        jLabel3.setText("Ingrese nombre y apellido");

        jPanel5.setLayout(new java.awt.GridLayout(2, 2));

        jLabel4.setText("Nombre");
        jPanel5.add(jLabel4);
        jPanel5.add(jTextFieldNombre);

        jLabel5.setText("Apellido");
        jPanel5.add(jLabel5);
        jPanel5.add(jTextFieldApellido);

        jScrollPane3.setViewportView(jListAlumnosAlta);

        jLabel6.setText("Seleccione el alumno a dar de Alta");

        jButtonCargar.setText("Cargar");
        jButtonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarActionPerformed(evt);
            }
        });

        jButtonAgregarAlumno.setText("Agregar Alumno");
        jButtonAgregarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarAlumnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonAgregarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jButtonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCargar))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonAgregarAlumno)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setText("ID de cursada seleccionada:");
        jLabel10.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jLabel10ComponentHidden(evt);
            }
        });

        jButtonVolver.setText("Volver");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LabelIdCursada))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButtonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(LabelIdCursada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jButtonVolver)
                .addContainerGap())
        );

        jPanel3.getAccessibleContext().setAccessibleName("Baja de Alumnos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }//GEN-END:initComponents

    private void jButtonBuscarCursadasActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBuscarCursadasActionPerformed
    {//GEN-HEADEREND:event_jButtonBuscarCursadasActionPerformed
        this.jListCursadas.clearSelection();
        this.limpiarModelo();
        String asignatura=this.jTextFieldNombreCursada.getText().toUpperCase();
        ArrayList<Cursada> cursadas=facultad.buscarCursadaPorNombre(asignatura);
        Iterator it=cursadas.iterator();
        while(it.hasNext())
        {
            Cursada c=(Cursada)it.next();
            modeloCursadas.addElement(c);
        }
        this.jListCursadas.setModel(modeloCursadas);
    }//GEN-LAST:event_jButtonBuscarCursadasActionPerformed

    private void jListCursadasValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_jListCursadasValueChanged
    {//GEN-HEADEREND:event_jListCursadasValueChanged
        int n=this.jListCursadas.getSelectedIndex();
        if(n!=-1){
            this.modeloAlumnosBaja.clear();
            Cursada c = (Cursada) this.modeloCursadas.getElementAt(n);
            this.LabelIdCursada.setText(c.getId());
            Hashtable<String,Alumno> alumnos = c.getAlumnos();
            if(alumnos.size()==0)
            {
                this.LabelSinAlumnos.setText("Actualemente la cursada no tiene alumnos establecidos");
            }else
            {
                this.LabelSinAlumnos.setText("");
                Enumeration e = alumnos.elements();
                Alumno a;
                while( e.hasMoreElements() ){
                    a =(Alumno) e.nextElement();
                    this.modeloAlumnosBaja.addElement(a);
                }
                this.jListAlumnosBaja.setModel(modeloAlumnosBaja);
            }
        }
        
    }//GEN-LAST:event_jListCursadasValueChanged

    private void jButtonEliminarAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarAlumnoActionPerformed
    {//GEN-HEADEREND:event_jButtonEliminarAlumnoActionPerformed
        int n=this.jListAlumnosBaja.getSelectedIndex();
        if(n!=-1)
        {
            Alumno a=(Alumno) this.modeloAlumnosBaja.getElementAt(n);
            int x=this.jListCursadas.getSelectedIndex();
            Cursada c=(Cursada) this.modeloCursadas.getElementAt(x);
            try {
                this.facultad.eliminarAlumnoCursada(c, a);
                JOptionPane.showMessageDialog(null, "El alumno fue eliminado correctamente de la cursada","Informaci�n",JOptionPane.INFORMATION_MESSAGE);
                this.modeloAlumnosBaja.removeElementAt(n);
                if(modeloAlumnosBaja.isEmpty())
                {
                    this.LabelSinAlumnos.setText("Actualemente la cursada no tiene alumnos establecidos");
                }
            } catch (NoExisteEntidadException e) {
                JOptionPane.showMessageDialog(null, "No existe la cursada o el alumno","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonEliminarAlumnoActionPerformed

    private void jLabel10ComponentHidden(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_jLabel10ComponentHidden
    {//GEN-HEADEREND:event_jLabel10ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10ComponentHidden

    private void jButtonCargarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCargarActionPerformed
    {//GEN-HEADEREND:event_jButtonCargarActionPerformed
        // TODO add your handling code here:
        String nombre=this.jTextFieldNombre.getText().toUpperCase();
        String apellido=this.jTextFieldApellido.getText().toUpperCase();
        ArrayList<Alumno> alumnos=facultad.buscarAlumnoPorNombre(nombre, apellido);;
        Iterator it=alumnos.iterator();
        
        while(it.hasNext())
        {
           Alumno a=(Alumno)it.next();
            this.modeloAlumnosAlta.addElement(a);
        }
        this.jListAlumnosAlta.setModel(modeloAlumnosAlta);
    }//GEN-LAST:event_jButtonCargarActionPerformed

    private void jButtonAgregarAlumnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAgregarAlumnoActionPerformed
    {//GEN-HEADEREND:event_jButtonAgregarAlumnoActionPerformed
        // TODO add your handling code here:
        int n=this.jListCursadas.getSelectedIndex();
        int x=this.jListAlumnosAlta.getSelectedIndex();
        if(n!=-1 && x!=-1)
        {
            Cursada c = (Cursada)this.modeloCursadas.getElementAt(n);
            Alumno a=(Alumno)this.modeloAlumnosAlta.getElementAt(x);
            try
            {
                this.facultad.agregarAlumnoEnCursada(a, c);
                JOptionPane.showMessageDialog(null, "El alumno fue a�adido correctamente","Informaci�n",JOptionPane.INFORMATION_MESSAGE);
            } catch (AlumnoInhabilitadoException e)
            {
                JOptionPane.showMessageDialog(null, "El alumno no esta habilitado para esta cursada","Error",JOptionPane.ERROR_MESSAGE);
            } catch (AlumnoOcupadoParaCursadaException e)
            {
                JOptionPane.showMessageDialog(null, "El alumno no tiene disponibilidad horaria para esta cursada","Error",JOptionPane.ERROR_MESSAGE);
            } catch (AlumnoRegistradoEnCursadaException e)
            {
                JOptionPane.showMessageDialog(null, "El Alumno ya esta registrado en esta cursada","Error",JOptionPane.ERROR_MESSAGE);
            } catch (NoExisteEntidadException e) {
                JOptionPane.showMessageDialog(null, "No existe la cursada o el alumno","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una cursada y un Alumno","Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButtonAgregarAlumnoActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonVolverActionPerformed
    {//GEN-HEADEREND:event_jButtonVolverActionPerformed
        // TODO add your handling code here:
        this.dispose();
        this.ventana.setVisible(true);
    }//GEN-LAST:event_jButtonVolverActionPerformed

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
                .getLogger(VentanaAltasBajasAlumnosCursada.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(VentanaAltasBajasAlumnosCursada.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(VentanaAltasBajasAlumnosCursada.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(VentanaAltasBajasAlumnosCursada.class.getName())
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
                  //  new VentanaAltasBajasAlumnosCursada().setVisible(true);
                }
            });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelIdCursada;
    private javax.swing.JLabel LabelSinAlumnos;
    private javax.swing.JButton jButtonAgregarAlumno;
    private javax.swing.JButton jButtonBuscarCursadas;
    private javax.swing.JButton jButtonCargar;
    private javax.swing.JButton jButtonEliminarAlumno;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jListAlumnosAlta;
    private javax.swing.JList<String> jListAlumnosBaja;
    private javax.swing.JList<String> jListCursadas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNombreCursada;
    // End of variables declaration//GEN-END:variables

}
