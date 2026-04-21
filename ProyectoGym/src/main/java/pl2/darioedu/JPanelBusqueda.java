package pl2.darioedu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JComboBox;

/**
 *
 * @author Darío
 */
public class JPanelBusqueda extends javax.swing.JPanel {
    private boolean pulsado = false;
    private String busqueda = "";
    private String seleccionado = null;
    private final String[] listaPosibilidadesUsario = {"Dia","Monitor","Tipo"};
    private final String[] listaPosibilidadesAdminActividades = {"Dia","Monitor","Tipo"};
    private final String[] listaPosibilidadesAdminSocios = {"Nombre","Correo"};
    private final String[] listaPosibilidadesAdminReservas = {"Dia"};
    public JPanelBusqueda() {
        initComponents();
    }
    public void setTextArea(String texto){
        this.jTextField1.setText(texto);
    }
    
    public void setBusquedaUsuarioActividad(){
        this.jComboBox1 = new JComboBox(listaPosibilidadesUsario);
    }
    
    public void setBusquedaAdminActividad(){
        this.jComboBox1 = new JComboBox(listaPosibilidadesAdminActividades);
    }
    
    public void setBusquedaAdminSocio(){
        this.jComboBox1 = new JComboBox(listaPosibilidadesAdminSocios);
    }
    public void setBusquedaAdminReserva(){
        this.jComboBox1 = new JComboBox(listaPosibilidadesAdminReservas);
    }
    
    public List<Actividad> getBusquedaUsuarioActividad(){
        //Cuando la barra de búsqueda se ha seleccionado con 
        ArrayList<Actividad> listaActividad = Main.getListaActividadStatic();
        if (this.busquedaValida()){
          int anno = Integer.parseInt(this.busqueda.substring(0, 4));
          int mes = Integer.parseInt(this.busqueda.substring(4,6));
          int dia = Integer.parseInt(this.busqueda.substring(6,8));
          List<Actividad> devolver;
          switch (this.seleccionado){
              case "Dia" -> 
                  devolver = this.buscarActividadDia(listaActividad,anno,mes,dia);
              case "Monitor" ->
                  devolver = this.buscarActividadMonitor(listaActividad, this.busqueda);
              case "Tipo" ->
                  devolver = this.buscarActividadTipo(listaActividad, this.busqueda);
              default-> 
                  devolver = new ArrayList<>();
          }
          return devolver;       
        }else{
        List<Actividad> devolver = new ArrayList<>();
        return devolver;}
    }
    
    public List<Actividad> getBusquedaAdminActividad(){
        //Cuando la barra de búsqueda se ha seleccionado con 
        ArrayList<Actividad> listaActividad = Main.getListaActividadStatic();
        if (this.busquedaValida()){
          int anno = Integer.parseInt(this.busqueda.substring(0, 4));
          int mes = Integer.parseInt(this.busqueda.substring(4,6));
          int dia = Integer.parseInt(this.busqueda.substring(6,8));
          List<Actividad> devolver;
          switch (this.seleccionado){
              case "Dia" -> 
                  devolver = this.buscarActividadDia(listaActividad,anno,mes,dia);
              case "Monitor" ->
                  devolver = this.buscarActividadMonitor(listaActividad, this.busqueda);
              case "Tipo" ->
                  devolver = this.buscarActividadTipo(listaActividad, this.busqueda);
              default-> 
                  devolver = new ArrayList<>();
          }
          return devolver;       
        }else{
        List<Actividad> devolver = new ArrayList<>();
        return devolver;}
    }

    public List<Usuario> getBusquedaAdminSocio(){
        //Cuando la barra de búsqueda se ha seleccionado con 
        ArrayList<Usuario> listaUsuario = Main.getListaUsuarioStatic();
        List<Socio> listaSocio =listaUsuario.stream().filter(us -> us instanceof Socio).map(us -> (Socio) us).toList();
        if (this.busquedaValida()){
          List<Usuario> devolver;
          switch (this.seleccionado){
              case "Nombre" ->
                  devolver = this.buscarSocioNombre(listaSocio, this.busqueda);
              case "Correo" ->
                  devolver = this.buscarSocioCorreo(listaSocio, this.busqueda);
              default-> 
                  devolver = new ArrayList<>();
          }
          return devolver;       
        }else{
        List<Usuario> devolver = new ArrayList<>();
        return devolver;}
    }
    
    public List<Sesion> getBusquedaAdminReserva(){
        ArrayList<Usuario> listaUsuario = Main.getListaUsuarioStatic();
        List<Socio> listaSocio =listaUsuario.stream().filter(us -> us instanceof Socio).map(us -> (Socio) us).toList();
        if (this.busquedaValida()){
          int anno = Integer.parseInt(this.busqueda.substring(0, 4));
          int mes = Integer.parseInt(this.busqueda.substring(4,6));
          int dia = Integer.parseInt(this.busqueda.substring(6,8));
          List<Sesion> devolver;
          switch (this.seleccionado){
              case "Dia" -> 
                  devolver = listaSocio.stream().flatMap(s -> s.getListaSesion().stream()).filter(s -> s.getAnno() == anno && s.getMes() == mes && s.getNumDiA()== dia).collect(Collectors.toList());
              default-> 
                  devolver = new ArrayList<>();
          }
          return devolver;       
        }else{
        List<Sesion> devolver = new ArrayList<>();
        return devolver;}
    }
    
    public final List<Actividad> buscarActividadMonitor(ArrayList<Actividad> listaActividad, String nombre){
        try{
        List<Actividad> listaDevolver = listaActividad.stream().filter(act -> act.getMonitor().equals(nombre)).collect(Collectors.toList());
            return listaDevolver;}
        catch (Exception error){
            List<Actividad> listaDevolver = new ArrayList<>();
            return listaDevolver;
        } 
    }
    
    public boolean busquedaValida(){
        //Nos aseguramos de que la búsqueda es válida, si no no podemos realizar la consulta
        return ((this.seleccionado!=null)&&(this.pulsado)&&(!this.busqueda.equals("")));
    }
    
    public final List<Actividad> buscarActividadTipo(ArrayList<Actividad> listaActividad, String tipo){
        try{
        List<Actividad> listaDevolver = listaActividad.stream().filter(act -> act.getTipo().equals(tipo)).collect(Collectors.toList());
            return listaDevolver;}
        catch (Exception error){
            List<Actividad> listaDevolver = new ArrayList<>();
            return listaDevolver;
        } 
    }
    public final List<Actividad> buscarActividadDia(ArrayList<Actividad> listaActividad, int anno, int mes, int dia){
        try{
        List<Actividad> listaDevolver = listaActividad.stream().filter(act -> act.getSala().hayHueco()).collect(Collectors.toList()); //Filtramos la primera parte, es decir, si hay huehco o no
        listaDevolver = listaDevolver.stream().filter(act -> act.getSesiones().stream().anyMatch(s->s.getNumDiA() == dia && s.getMes() == mes && s.getAnno() == anno)).collect(Collectors.toList()); //Filtramos la segunda parte, si coincide con dia, mes y fecha
            return listaDevolver;}
        catch (Exception error){
            List<Actividad> listaDevolver = new ArrayList<>();
            return listaDevolver;
        } 
    }
    
    public final List<Usuario> buscarSocioNombre(List<Socio> listaSocio, String nombre){
        try{
        List<Usuario> listaDevolver = listaSocio.stream().filter(so -> so.getNombre().equals(nombre)).collect(Collectors.toList()); //Filtramos la primera parte, es decir, si hay huehco o no
            return listaDevolver;}
        catch (Exception error){
            List<Usuario> listaDevolver = new ArrayList<>();
            return listaDevolver;
        }
    }
    public final List<Usuario> buscarSocioVip(List<Socio> listaSocio){
        try{
        List<Usuario> listaDevolver = listaSocio.stream().filter(so -> so.getVipStatus()==true).collect(Collectors.toList()); //Filtramos la primera parte, es decir, si hay huehco o no
            return listaDevolver;}
        catch (Exception error){
            List<Usuario> listaDevolver = new ArrayList<>();
            return listaDevolver;
        }
    }
    public final List<Usuario> buscarSocioCorreo(List<Socio> listaSocio, String correo){
        try{
            List<Usuario> listaDevolver = listaSocio.stream().filter(so->so.getCorreo().equals(correo)).collect(Collectors.toList());
            return listaDevolver;
        }
        catch (Exception error){
            List<Usuario> listaDevolver = new ArrayList<>();
            return listaDevolver;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Buscar = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(300, 70));
        setMinimumSize(new java.awt.Dimension(300, 70));
        setPreferredSize(new java.awt.Dimension(300, 70));

        Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Buscar.png"))); // NOI18N
        Buscar.setMaximumSize(new java.awt.Dimension(50, 50));
        Buscar.setMinimumSize(new java.awt.Dimension(50, 50));
        Buscar.setPreferredSize(new java.awt.Dimension(50, 50));
        Buscar.addActionListener(this::BuscarActionPerformed);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setMaximumSize(new java.awt.Dimension(75, 50));
        jComboBox1.setMinimumSize(new java.awt.Dimension(75, 50));
        jComboBox1.setPreferredSize(new java.awt.Dimension(75, 50));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        this.pulsado = true;
    }//GEN-LAST:event_BuscarActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        this.seleccionado = String.valueOf(this.jComboBox1.getSelectedItem());
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        this.busqueda = this.jTextField1.getText();
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
