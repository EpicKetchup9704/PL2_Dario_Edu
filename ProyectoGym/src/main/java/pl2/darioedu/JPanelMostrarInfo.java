/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pl2.darioedu;

import javax.swing.ImageIcon;

/**
 *
 * @author Darío
 */
public class JPanelMostrarInfo extends javax.swing.JPanel {
    private boolean pulsado1 = false;
    private boolean pulsado2 = false;
    private Socio socio = null;
    private Actividad actividad = null;
    private Sesion sesion = null;
    private boolean admin = false;

    public Socio getSocio() {
        return socio;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public boolean isAdmin() {
        return admin;
    }
    
    
    public boolean getPulsadoB1(){
        return this.pulsado1;
    }
    public boolean getPulsadoB2(){
        return this.pulsado2;
    }
    
    
    public JPanelMostrarInfo() {
        initComponents();
    }
    public JPanelMostrarInfo(Socio socio){
        initComponents();
        this.socio = socio;
        this.Titulo.setText(socio.getNombre());
        this.AP1.setText("Correo: " + String.valueOf(socio.getCorreo()));
        this.AP2.setText("Telf: "+String.valueOf(socio.getTelefono()));
        this.AP3.setText("VIP: "+String.valueOf(socio.getVipStatus()));
        this.AP4.setText("Tarjeta: "+socio.getTarjeta());
        this.Imagen1.setVisible(false);
        this.Imagen2.setVisible(true);
        if(socio.getVipStatus()==true){this.Imagen2.setIcon(new ImageIcon(("src/main/resources/Estrella.png")));}
        else{this.Imagen2.setVisible(true);
            this.Imagen2.setIcon(new ImageIcon(("src/main/resources/Vacio.png")));}
    }
    public JPanelMostrarInfo(Actividad actividad){
        //Constructor para el admin, para mostrar actividades
        initComponents();
        this.actividad = actividad;
            this.Titulo.setText(actividad.getTitulo());
            this.AP1.setText("Tipo: "+actividad.getTipo());
            this.AP2.setText("Sala: "+actividad.getSala().getNombre());
            this.AP3.setText("Aforo: "+actividad.getSala().getAforo() + " | Plazas: "+actividad.getSala().aforoRestante());
            this.AP4.setText("Monitor: " +actividad.getMonitor());
            this.Imagen1.setVisible(true);
            this.Imagen1.setEnabled(true);
            this.Imagen1.setIcon(new ImageIcon("src/main/resources/Cruz.png"));
            this.Imagen2.setVisible(true);
            this.Imagen2.setEnabled(true);
            this.Imagen2.setIcon(new ImageIcon("src/main/resources/Editar.png"));
    }
    public JPanelMostrarInfo(Actividad actividad, Sesion clase, boolean esAdmin, Socio user){
        if(esAdmin){
            //Constructor para el admin, para mostrar reservas
            this.actividad = actividad;
            this.sesion = clase;
            this.admin = true;
            this.socio = user;
            this.Titulo.setText(actividad.getTitulo());
            this.AP1.setText("Tipo: "+actividad.getTipo());
            this.AP2.setText("Sala: "+actividad.getSala().getNombre());
            this.AP3.setText("Reserva: "+clase.getSesionStringFormateado(':'));
            this.AP4.setText("Socio +"+user.getNombre());
            this.Imagen1.setVisible(true);
            this.Imagen1.setEnabled(true);
            this.Imagen1.setIcon(new ImageIcon("src/main/resources/Cruz.png"));
            this.Imagen2.setVisible(true);
            this.Imagen2.setEnabled(true);
            this.Imagen2.setIcon(new ImageIcon("src/main/resources/Editar.png"));
        }
        else{
            if(user == null){
                //Constructor para el usuario, para consulta de búsqueda (tema de Sesion)
            this.actividad = actividad;
            this.sesion = clase;
            this.admin = false;
            this.socio = null;
            this.Titulo.setText(actividad.getTitulo());
            this.AP1.setText("Tipo: "+actividad.getTipo());
            this.AP2.setText("Sala: "+actividad.getSala().getNombre());
            this.AP3.setText("Clase: "+clase.getSesionStringFormateado(':'));
            this.AP4.setText("Monitor"+actividad.getMonitor());
            this.Imagen1.setVisible(false);
            this.Imagen1.setEnabled(false);
            this.Imagen2.setVisible(true);
            this.Imagen2.setEnabled(true);
            this.Imagen2.setIcon((new ImageIcon("src/main/resources/FlechaOk.png")));
            }
            else{
                //Constructor para el usuario, para consulta de reservas (tema de Sesion)
                this.actividad = actividad;
                this.sesion = clase;
                this.admin = false;
                this.socio = user;
                this.Titulo.setText(actividad.getTitulo());
                this.AP1.setText("Tipo: "+actividad.getTipo());
                this.AP2.setText("Sala: "+actividad.getSala().getNombre());
                this.AP3.setText("Reserva: "+clase.getSesionStringFormateado(':'));
                this.AP4.setText("Monitor"+actividad.getMonitor());
                this.Imagen1.setVisible(false);
                this.Imagen1.setEnabled(false);
                this.Imagen2.setVisible(true);
                this.Imagen2.setEnabled(true);
                this.Imagen2.setIcon(new ImageIcon("src/main/resources/Cruz.png"));
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Imagen2 = new javax.swing.JButton();
        Imagen1 = new javax.swing.JButton();
        Titulo = new javax.swing.JLabel();
        AP1 = new javax.swing.JLabel();
        AP2 = new javax.swing.JLabel();
        AP3 = new javax.swing.JLabel();
        AP4 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(300, 100));
        setMinimumSize(new java.awt.Dimension(300, 100));
        setPreferredSize(new java.awt.Dimension(300, 100));

        Imagen2.addActionListener(this::Imagen2ActionPerformed);

        Imagen1.addActionListener(this::Imagen1ActionPerformed);

        Titulo.setText("Titulo:");

        AP1.setText("Apartado 1:");

        AP2.setText("Apartado 2:");

        AP3.setText("Apartado 3:");

        AP4.setText("Apartado 4:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AP1)
                            .addComponent(AP2)
                            .addComponent(AP3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                        .addComponent(Imagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Imagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Titulo)
                            .addComponent(AP4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(Titulo)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Imagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Imagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AP1)
                        .addGap(2, 2, 2)
                        .addComponent(AP2)
                        .addGap(2, 2, 2)
                        .addComponent(AP3)))
                .addGap(2, 2, 2)
                .addComponent(AP4))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Imagen2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Imagen2ActionPerformed
        this.pulsado2 = true;
    }//GEN-LAST:event_Imagen2ActionPerformed

    private void Imagen1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Imagen1ActionPerformed
       this.pulsado1 = true;
    }//GEN-LAST:event_Imagen1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AP1;
    private javax.swing.JLabel AP2;
    private javax.swing.JLabel AP3;
    private javax.swing.JLabel AP4;
    private javax.swing.JButton Imagen1;
    private javax.swing.JButton Imagen2;
    private javax.swing.JLabel Titulo;
    // End of variables declaration//GEN-END:variables
}
