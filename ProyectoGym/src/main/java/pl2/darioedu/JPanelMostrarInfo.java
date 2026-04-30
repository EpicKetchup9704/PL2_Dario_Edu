/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pl2.darioedu;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 
 * @author Darío
 * Hay que añadir ventanas emergentes para cada accion / deseada
 */
public class JPanelMostrarInfo extends javax.swing.JPanel {
    private Socio socio;
    private Actividad actividad;
    private Sesion sesion;
    private boolean admin;

    public final String reducirCadena(String cadena){
        String devolver = cadena.length() > 25 ? cadena.substring(0,20) + "..." : cadena;
        return devolver;
    }
    
    public final String reducirCadena(String cadena, int num){
        String devolver = cadena.length() > num ? cadena.substring(0,num-3) + "..." : cadena;
        return devolver;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    
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
    
    
    public JPanelMostrarInfo() {
        initComponents();
        this.Titulo = new JLabel("No hay título introducido");
    }
    public JPanelMostrarInfo(Socio socio){
        //Constructor para el admin para mostrar los socios que hay y gestionarlos
        initComponents();
        this.actividad = null;
        this.sesion = null;
        this.admin = false;
        this.socio = socio;
        this.Titulo.setText(socio.getNombre());
        this.AP1.setText("Correo: " + this.reducirCadena(socio.getCorreo()));
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
        //Constructor para el admin, para mostrar actividades y gestionarlas
        initComponents();
        this.actividad = actividad;
        this.sesion = null;
        this.admin = false;
        this.socio = null;
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
        initComponents();
        if(esAdmin){
            if (!(clase==null)){
            //Constructor para el admin, para mostrar reservas
            this.actividad = actividad;
            this.sesion = clase;
            this.admin = true;
            this.socio = user;
            this.Titulo.setText(actividad.getTitulo());
            this.AP1.setText("Tipo: "+actividad.getTipo());
            this.AP2.setText("Sala: "+actividad.getSala().getNombre());
            this.AP3.setText("Reserva: "+clase.getSesionStringFormateado(':'));
            this.AP4.setText("Socio: "+user.getNombre());
            this.Imagen1.setVisible(true);
            this.Imagen1.setEnabled(true);
            this.Imagen1.setIcon(new ImageIcon("src/main/resources/Cruz.png"));
            this.Imagen2.setVisible(false);
            this.Imagen2.setEnabled(false);
            //this.Imagen2.setIcon(new ImageIcon("src/main/resources/Editar.png"));
            }
            else{
                //Constructor admin mostrar Actividades sin poder editar (visor)
                this.actividad = actividad;
                this.sesion = null;
                this.admin = true;
                this.socio = null;
                this.Titulo.setText(actividad.getTitulo());
                this.AP1.setText("Tipo: "+actividad.getTipo()+ " | Sala: "+actividad.getSala().getNombre());
                String apartado = (actividad.getDiaSemanaRecursivo()==null) ? "No hay " : actividad.getDiaSemanaRecursivo().toString() + " | De " + actividad.getSesiones().get(0).getHoraInicio()+ " a "+ actividad.getSesiones().get(0).getHoraFin() ;
                this.AP2.setText("Día sema fijo: " + apartado);
                this.AP3.setText("Aforo: "+actividad.getSala().getAforo() + " | Plazas: "+actividad.getSala().aforoRestante());
                this.AP4.setText("Monitor: " +actividad.getMonitor() + " | Sesiones: " + actividad.getSesiones().size());
                this.Imagen1.setVisible(false);
                this.Imagen1.setEnabled(false);
                this.Imagen2.setVisible(false);
                this.Imagen2.setEnabled(false);
            }
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

        Titulo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        Titulo.setText("Titulo:");

        AP1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        AP1.setText("Apartado 1:");

        AP2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        AP2.setText("Apartado 2:");

        AP3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        AP3.setText("Apartado 3:");

        AP4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
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
        if ((this.actividad == null)&&(this.sesion==null)&&(!this.admin)&&(this.socio!=null)){
           int n = JOptionPane.showConfirmDialog(null,"¿Desea cambiar el estado del Socio?","JavaFit - MENU",JOptionPane.YES_NO_OPTION);
           if (n==JOptionPane.YES_OPTION){
               ArrayList<Usuario> listaUser = Main.getListaUsuarioStatic();
                for (Usuario user : listaUser){
                    if (user instanceof Socio socio1){
                      if (socio1.equals(this.socio)){
                          if (this.socio.getVipStatus()){
                              socio1.cambiarStatusVip(false);
                          }
                          else{
                              socio1.cambiarStatusVip(true);
                          }
                        }
                    }  
                }
           Main.guardarListaUsuarios(listaUser); 
           }
        }
        else if ((this.actividad != null)&&(this.sesion==null)&&(!this.admin)&&(this.socio!=null)){
            JOptionPane.showMessageDialog(null,"Mensaje para modificar actividad / admin","None",JOptionPane.INFORMATION_MESSAGE);
        }
        else if ((this.actividad != null)&&(this.sesion!=null)&&(this.admin)&&(this.socio!=null)){
            JOptionPane.showMessageDialog(null,"Mensaje para editar reservas actividad / admin","None",JOptionPane.INFORMATION_MESSAGE);
        }
        else if ((this.actividad != null)&&(this.sesion==null)&&(this.admin)&&(this.socio!=null)){
            //JOptionPane.showMessageDialog(null,"Mensaje para editar reservas actividad / admin","None",JOptionPane.INFORMATION_MESSAGE);
        }
        else if ((this.actividad != null)&&(this.sesion!=null)&&(!this.admin)&&(this.socio==null)){
            JOptionPane.showMessageDialog(null,"Mensaje para editar búsqueda actividad continuar con reserva / socio","None",JOptionPane.INFORMATION_MESSAGE);
        }
        else if ((this.actividad != null)&&(this.sesion!=null)&&(!this.admin)&&(this.socio!=null)){
            JOptionPane.showMessageDialog(null,"Mensaje para editar resrvas actividad continuar / socio","None",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            new PanelModificarAct(this.actividad).setVisible(true);
        }
    }//GEN-LAST:event_Imagen2ActionPerformed

    private void Imagen1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Imagen1ActionPerformed
        if ((this.actividad == null)&&(this.sesion==null)&&(!this.admin)&&(this.socio!=null)){
            //JOptionPane.showMessageDialog(null,"Mensaje para dado un socio editar su info / admin ","None",JOptionPane.INFORMATION_MESSAGE);
        }
        else if ((this.actividad != null)&&(this.sesion==null)&&(!this.admin)&&(this.socio!=null)){
            JOptionPane.showMessageDialog(null,"Mensaje para borrar actividad / admin","None",JOptionPane.INFORMATION_MESSAGE);
        }
        else if ((this.actividad != null)&&(this.sesion!=null)&&(this.admin)&&(this.socio!=null)){
        int n = JOptionPane.showConfirmDialog(null,"¿Desea borrar la reserva?","JavaFit - MENU",JOptionPane.YES_NO_OPTION);
            //Cuando la opción es seleccionada como Yes / Se elimina la actividad después de hacer la búsqueda
            if (n==JOptionPane.YES_OPTION){
                ArrayList<Usuario> listaUser = Main.getListaUsuarioStatic();
                for (Usuario user : listaUser){
                    if (user instanceof Socio socio1){
                        for (Sesion ses : socio1.getListaSesion()){
                                if(ses.equals(this.sesion)){
                                    socio1.getListaSesion().remove(ses);
                                }
                            }
                        }
                    }
                Main.guardarListaUsuarios(listaUser);
                }
        }
        else if ((this.actividad != null)&&(this.sesion==null)&&(this.admin)&&(this.socio!=null)){
            //JOptionPane.showMessageDialog(null,"Mensaje para editar reservas actividad / admin","None",JOptionPane.INFORMATION_MESSAGE);
        }
        else if ((this.actividad != null)&&(this.sesion!=null)&&(!this.admin)&&(this.socio==null)){
            //JOptionPane.showMessageDialog(null,"Mensaje para editar búsqueda actividad continuar con reserva / socio","None",JOptionPane.INFORMATION_MESSAGE);
        }
        else if ((this.actividad != null)&&(this.sesion!=null)&&(!this.admin)&&(this.socio!=null)){
            //JOptionPane.showMessageDialog(null,"Mensaje para editar resrvas actividad continuar / socio","None",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            int n = JOptionPane.showConfirmDialog(null,"¿Desea borrar la Actividad? | Se borrarán las reservas asociadas a dicha actividad","JavaFit - MENU",JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION){
                ArrayList<Actividad> listaAct = Main.getListaActividadStatic();
                ArrayList<Usuario> listaUser = Main.getListaUsuarioStatic();
                listaAct.remove(this.actividad);
                for (Usuario user : listaUser){
                    if (user instanceof Socio socio1){
                        if(socio1.getListaActividades().contains(this.actividad)){
                            listaAct.remove(this.actividad);
                            for(Sesion ses:socio1.getListaSesion()){
                                if (this.actividad.getSesiones().contains(ses)){
                                    socio1.getListaSesion().remove(ses);
                                }
                            }
                        }
                    } else {
                    }
                }
                Main.guardarListaActividades(listaAct);
                Main.guardarListaUsuarios(listaUser);
            }
        }
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
