
package mesaayudatec;

import mesaayudatec.newpackage.model.MySqlConexion;
import javax.swing.*;
import java.sql.*;

public class DetalleIncidencia extends javax.swing.JFrame {
private int idUsuario;
    private int idTicket;
    private TablaUsuario parent;

    public DetalleIncidencia(int idUsuario, int idTicket, TablaUsuario parent) {
        this.idUsuario = idUsuario;
        this.idTicket = idTicket;
        this.parent = parent;
        initComponents();
        setLocationRelativeTo(null);
        cargarCombos();
        cargarDatosTicket();
    }

    // Constructor vacío (solo para NetBeans)
    public DetalleIncidencia() {
        initComponents();
    }

    // ----------- CARGAR COMBOS -----------
    private void cargarCombos() {
        ComboHelper.cargarDepartamentos(jC_Departamento);
        ComboHelper.cargarEstados(jC_Estado);
        ComboHelper.cargarPrioridades(jC_Prioridad);
        ComboHelper.cargarUsuarios(jC_Nombre); // 👈 Agregamos la carga de usuarios
    }

    // ----------- CARGAR DATOS DEL TICKET -----------
    private void cargarDatosTicket() {
        String sql = """
            SELECT t.titulo, t.descripcion, d.nombre AS departamento, 
                   e.nombre AS estado, p.nombre AS prioridad, 
                   u.nombre AS asignado
            FROM tb_ticket t
            JOIN tb_departamento d ON d.id = t.id_departamento
            JOIN tb_estados e ON e.id = t.id_status
            JOIN tb_prioridades p ON p.id = t.id_prioridad
            JOIN tb_usuario u ON u.idusuario = t.id_usuario
            WHERE t.id = ?
        """;

        try (Connection con = new MySqlConexion().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idTicket);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    setTitle("Seguimiento de Ticket #" + idTicket);
                    jTextArea_Ultima_Actualizacion.setText("Descripción: " + rs.getString("descripcion"));

                    seleccionarItem(jC_Departamento, rs.getString("departamento"));
                    seleccionarItem(jC_Estado, rs.getString("estado"));
                    seleccionarItem(jC_Prioridad, rs.getString("prioridad"));
                    seleccionarItem(jC_Nombre, rs.getString("asignado"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar ticket: " + e.getMessage());
        }
    }

    // ----------- MÉTODO PARA SELECCIONAR ITEM EN COMBO -----------
    private void seleccionarItem(JComboBox combo, String valor) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            Object item = combo.getItemAt(i);
            if (item instanceof Item) {
                if (((Item) item).getNombre().equalsIgnoreCase(valor)) {
                    combo.setSelectedIndex(i);
                    break;
                }
            } else if (item.toString().equalsIgnoreCase(valor)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    // ----------- GRABAR ACTUALIZACIÓN -----------
    private void grabarActualizacion() {
        int idDepto = ComboHelper.getIdSeleccionado(jC_Departamento);
        int idEstado = ComboHelper.getIdSeleccionado(jC_Estado);
        int idPrioridad = ComboHelper.getIdSeleccionado(jC_Prioridad);
        int idAsignado = ComboHelper.getIdSeleccionado(jC_Nombre); // 👈 ID del usuario asignado
        String descripcion = jTextArea_Ultima_Actualizacion.getText().trim();

        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Escribe una descripción de la actualización.");
            return;
        }

        String sqlHistorial = """
            INSERT INTO tb_historial_ticket (id_ticket, id_usuario, descripcion, fecha, id_prioridad)
            VALUES (?, ?, ?, NOW(), ?)
        """;

        String sqlUpdate = """
            UPDATE tb_ticket SET id_departamento=?, id_status=?, id_prioridad=?, id_usuario=? WHERE id=?
        """;

        try (Connection con = new MySqlConexion().getConexion();
             PreparedStatement psHistorial = con.prepareStatement(sqlHistorial);
             PreparedStatement psTicket = con.prepareStatement(sqlUpdate)) {

            // Guardar en historial
            psHistorial.setInt(1, idTicket);
            psHistorial.setInt(2, idUsuario);
            psHistorial.setString(3, descripcion);
            psHistorial.setInt(4, idPrioridad);
            psHistorial.executeUpdate();

            // Actualizar ticket
            psTicket.setInt(1, idDepto);
            psTicket.setInt(2, idEstado);
            psTicket.setInt(3, idPrioridad);
            psTicket.setInt(4, idAsignado);
            psTicket.setInt(5, idTicket);
            psTicket.executeUpdate();

            JOptionPane.showMessageDialog(this, "✅ Actualización guardada correctamente.");
            jTextArea_Ultima_Actualizacion.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "❌ Error al guardar: " + e.getMessage());
        }
    }

    // ----------- VOLVER A TABLA -----------
    private void volverATabla() {
        if (parent != null) {
            parent.refrescar();
            parent.setVisible(true);
        }
        dispose();
    }

    // ----------- ABRIR HISTORIAL -----------
    private void abrirHistorial() {
        new HistorialIncidencia(idUsuario, idTicket, this).setVisible(true);
        this.setVisible(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jC_Estado = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jC_Nombre = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jC_Departamento = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jC_Prioridad = new javax.swing.JComboBox<>();
        jB_Volver = new javax.swing.JButton();
        jB_Grabar = new javax.swing.JButton();
        jB_Historial = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Ultima_Actualizacion = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        jC_Estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Estado");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Asignado");

        jC_Nombre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Departamento");

        jC_Departamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Prioridad");

        jC_Prioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jB_Volver.setBackground(new java.awt.Color(51, 51, 51));
        jB_Volver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jB_Volver.setForeground(new java.awt.Color(255, 255, 255));
        jB_Volver.setText("Volver");
        jB_Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_VolverActionPerformed(evt);
            }
        });

        jB_Grabar.setBackground(new java.awt.Color(51, 51, 51));
        jB_Grabar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jB_Grabar.setForeground(new java.awt.Color(255, 255, 255));
        jB_Grabar.setText("Grabar");
        jB_Grabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_GrabarActionPerformed(evt);
            }
        });

        jB_Historial.setBackground(new java.awt.Color(51, 51, 51));
        jB_Historial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jB_Historial.setForeground(new java.awt.Color(255, 255, 255));
        jB_Historial.setText("Historial");
        jB_Historial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_HistorialActionPerformed(evt);
            }
        });

        jTextArea_Ultima_Actualizacion.setColumns(20);
        jTextArea_Ultima_Actualizacion.setRows(5);
        jScrollPane1.setViewportView(jTextArea_Ultima_Actualizacion);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Ultima actualizacion ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 214, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jC_Prioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(158, 158, 158))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jB_Historial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jB_Grabar)
                                .addGap(18, 18, 18)
                                .addComponent(jB_Volver)
                                .addGap(28, 28, 28))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(jC_Departamento, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jC_Nombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jC_Estado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jC_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jC_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jC_Departamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jC_Prioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jB_Volver)
                    .addComponent(jB_Grabar)
                    .addComponent(jB_Historial))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jB_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_VolverActionPerformed
        volverATabla();
    }//GEN-LAST:event_jB_VolverActionPerformed

    private void jB_GrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_GrabarActionPerformed
         grabarActualizacion();
    }//GEN-LAST:event_jB_GrabarActionPerformed

    private void jB_HistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_HistorialActionPerformed
       abrirHistorial();
    }//GEN-LAST:event_jB_HistorialActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(DetalleIncidencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetalleIncidencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetalleIncidencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetalleIncidencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetalleIncidencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_Grabar;
    private javax.swing.JButton jB_Historial;
    private javax.swing.JButton jB_Volver;
    private javax.swing.JComboBox<String> jC_Departamento;
    private javax.swing.JComboBox<String> jC_Estado;
    private javax.swing.JComboBox<String> jC_Nombre;
    private javax.swing.JComboBox<String> jC_Prioridad;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_Ultima_Actualizacion;
    // End of variables declaration//GEN-END:variables
}
