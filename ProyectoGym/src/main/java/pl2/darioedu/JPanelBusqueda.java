package pl2.darioedu;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Darío
 */
public class JPanelBusqueda extends javax.swing.JPanel{
    public JFrameCalendario calendario = new JFrameCalendario();
    private String busqueda = "";
    private String seleccionado = "";
    private final String[] listaPosibilidadesUsario = {"Dia","Monitor","Tipo"};
    private final String[] listaPosibilidadesAdminActividades = {"Dia","Monitor","Tipo"};
    private final String[] listaPosibilidadesAdminSocios = {"Nombre","Correo"};
    private final String[] listaPosibilidadesAdminReservas = {"Dia"};
    private final String[] listaPosibilidadesAdminActividadesBuscar = {"Dia Semana","Titulo","Monitor", "Tipo","Sala", "Dia"};
    
    public JPanelBusqueda() {
        initComponents();
    }
    public void setBusqueda(){
    this.busqueda = this.jTextField1.getText();
    }
    public void aparecerCalendario(){
        this.calendario.setArea(this.jTextField1);
        this.calendario.setVisible(true);
    }
    
    public void ocultarCalendario(){
        this.calendario.setVisible(false);
    }
    
    public void eliminarCalendario(){
        this.calendario.setVisible(false);
        this.calendario.dispose();
    }
    
    public String getSeleccionado(){
        return this.seleccionado;
    }
    
    public void resetTextoBusqueda(){
        this.busqueda = null;
    }
    
    public void setTextArea(String texto){
        this.jTextField1.setText(texto);
    }
    
    public void setBusquedaUsuarioActividad(){
        this.jComboBox1.setModel(new DefaultComboBoxModel<>(listaPosibilidadesUsario));
        this.seleccionado = listaPosibilidadesUsario[0];
    }
    
    public void setBusquedaAdminActividad(){
        this.jComboBox1.setModel(new DefaultComboBoxModel<>(listaPosibilidadesAdminActividades));
        this.seleccionado = listaPosibilidadesAdminActividades[0];
    }
    
    public void setBusquedaAdminActividadBuscar(){
        this.jComboBox1.setModel(new DefaultComboBoxModel<>(listaPosibilidadesAdminActividadesBuscar));
        this.seleccionado = listaPosibilidadesAdminActividadesBuscar[0];
    }
    
    
    public void setBusquedaAdminSocio(){
        this.jComboBox1.setModel(new DefaultComboBoxModel<>(listaPosibilidadesAdminSocios));
        this.seleccionado = listaPosibilidadesAdminSocios[0];
    }
    public void setBusquedaAdminReserva(){
        this.jComboBox1.setModel(new DefaultComboBoxModel<>(listaPosibilidadesAdminReservas));
        this.seleccionado = listaPosibilidadesAdminReservas[0];
    }
    
    public List<Actividad> getBusquedaUsuarioActividad(){
        this.busqueda = jTextField1.getText();
        //Cuando la barra de búsqueda se ha seleccionado con 
        ArrayList<Actividad> listaActividad = Main.getListaActividadStatic();
        try{
        int anno = 0;
        int dia= 0;
        int mes = 0;
        if (this.busquedaValida()){
          if(this.seleccionado.equals("Dia")){
          anno = Integer.parseInt(this.busqueda.substring(0, 4));
          mes = Integer.parseInt(this.busqueda.substring(4,6));
          dia = Integer.parseInt(this.busqueda.substring(6,8));
          }
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
        catch (NumberFormatException | IndexOutOfBoundsException error1){
            ArrayList<Actividad> devolver = new ArrayList();
            return devolver;
        }
    }
    
    public List<Actividad> getBusquedaAdminActividad(){
        this.busqueda = jTextField1.getText();
        //Cuando la barra de búsqueda se ha seleccionado con 
        ArrayList<Actividad> listaActividad = Main.getListaActividadStatic();
        try{
            int anno = 0;
            int dia= 0;
            int mes = 0;
             if (this.busquedaValida()){
                if(this.seleccionado.equals("Dia")){
                    anno = Integer.parseInt(this.busqueda.substring(0, 4));
                    mes = Integer.parseInt(this.busqueda.substring(4,6));
                    dia = Integer.parseInt(this.busqueda.substring(6,8));}
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
        }catch (NumberFormatException | IndexOutOfBoundsException error1){
            ArrayList<Actividad> devolver = new ArrayList();
            return devolver;
        }
    }
    
        
    public List<Actividad> getBusquedaAdminActividadBuscar(){
        this.busqueda = jTextField1.getText();
        //Cuando la barra de búsqueda se ha seleccionado con 
        ArrayList<Actividad> listaActividad = Main.getListaActividadStatic();
        try{
        int anno = 0;
        int dia= 0;
        int mes = 0;
        if (this.busquedaValida()){
          if(this.seleccionado.equals("Dia")){
          anno = Integer.parseInt(this.busqueda.substring(0, 4));
          mes = Integer.parseInt(this.busqueda.substring(4,6));
          dia = Integer.parseInt(this.busqueda.substring(6,8));}
          List<Actividad> devolver;
          switch (this.seleccionado){
              case "Dia" -> 
                  devolver = this.buscarActividadDia(listaActividad,anno,mes,dia);
              case "Monitor" ->
                  devolver = this.buscarActividadMonitor(listaActividad, this.busqueda);
              case "Tipo" ->
                  devolver = this.buscarActividadTipo(listaActividad, this.busqueda);
              case "Dia Semana" ->
                  devolver = this.buscarActividadDiaSemana(listaActividad, getDiaSemana(this.busqueda));
              case "Titulo" ->
                  devolver = this.buscarActividadTitulo(listaActividad, this.busqueda);
              case "Sala"->
                  devolver = this.buscarActividadSala(listaActividad, this.busqueda);
              default-> 
                  devolver = new ArrayList<>();
          }
          return devolver;       
        }else{
        List<Actividad> devolver = new ArrayList<>();
        return devolver;}
        }catch (NumberFormatException | IndexOutOfBoundsException error1){
            ArrayList<Actividad> devolver = new ArrayList();
            return devolver;
        }
    }
    

    public List<Usuario> getBusquedaAdminSocio(){
        this.busqueda = jTextField1.getText();
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
        this.busqueda = jTextField1.getText();
        ArrayList<Usuario> listaUsuario = Main.getListaUsuarioStatic();
        List<Socio> listaSocio =listaUsuario.stream().filter(us -> us instanceof Socio).map(us -> (Socio) us).toList();
        try{
        if (this.busquedaValida()){
          int anno = Integer.parseInt(this.busqueda.substring(0, 4));
          int mes = Integer.parseInt(this.busqueda.substring(4,6));
          int dia = Integer.parseInt(this.busqueda.substring(6,8));
          List<Sesion> devolver;
          switch (this.seleccionado){
              case "Dia" -> 
                  devolver = this.buscarActividadAdminReserva(anno, mes, dia, listaSocio);
              default-> 
                  devolver = new ArrayList<>();
          }
          return devolver;       
        }else{
        List<Sesion> devolver = new ArrayList<>();
        return devolver;}
        }catch (NumberFormatException | IndexOutOfBoundsException error1){
            List<Sesion> devolver = new ArrayList<>();
            return devolver;
        }
    }
    
    private List<Sesion> buscarActividadAdminReserva(int anno, int mes, int dia, List<Socio> listaSocio){
        try{
            
            List<Sesion> devolver = listaSocio.stream().flatMap(soc -> soc.getListaSesion().stream()).filter(ses -> ses.getNumDiA() == dia && ses.getMes() == mes && ses.getAnno() == anno).toList();          
            return devolver;
        }
        catch (Exception error){
            List<Sesion> listaDevolver = new ArrayList<>();
            return listaDevolver;
        } 
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
        return this.seleccionado != null && this.busqueda != null && !this.busqueda.trim().isEmpty();
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
    
    public final List<Actividad> buscarActividadTitulo(ArrayList<Actividad> listaActividad,String titulo){
        try{
        List<Actividad> listaDevolver = listaActividad.stream().filter(act ->act.getTitulo().equals(titulo)).collect(Collectors.toList());
        return listaDevolver;}
        catch (Exception error){
            List<Actividad> listaDevolver = new ArrayList<>();
            return listaDevolver;
        } 
    }
    
    public final List<Actividad> buscarActividadSala(ArrayList<Actividad> listaActividad,String nombreSala){
        try{
        List<Actividad> listaDevolver = listaActividad.stream().filter(act ->act.getSala().getNombre().equals(nombreSala)).collect(Collectors.toList());
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
    
    public DayOfWeek getDiaSemana(String diaSemana){
        DayOfWeek devolver = null;
        ArrayList<String> diaSem = new ArrayList<>(Arrays.asList("lunes","martes","miercoles","miércoles","jueves","viernes","sabado","sábado","domingo"));
        ArrayList<String> diaCorto = new ArrayList<>(Arrays.asList("L","M","X","J","V","S","D"));
        if(diaSem.contains(diaSemana.toLowerCase())){    
        switch (diaSemana){    
                case "lunes" -> devolver = DayOfWeek.MONDAY;
                case "martes" -> devolver = DayOfWeek.TUESDAY;
                case "miercoles" -> devolver = DayOfWeek.WEDNESDAY;
                case "miércoles" -> devolver = DayOfWeek.WEDNESDAY;
                case "jueves" -> devolver = DayOfWeek.THURSDAY;
                case "viernes" -> devolver = DayOfWeek.FRIDAY;
                case "sábado" -> devolver = DayOfWeek.SATURDAY;
                case "sabado" -> devolver = DayOfWeek.SATURDAY;
                case "domingo" -> devolver = DayOfWeek.SUNDAY;}
            return devolver;
            }
        else if (diaCorto.contains(diaSemana.toUpperCase())){
            switch (diaSemana){ 
                case "L" -> devolver = DayOfWeek.MONDAY;
                case "M" -> devolver = DayOfWeek.TUESDAY;
                case "X" -> devolver = DayOfWeek.WEDNESDAY;
                case "J" -> devolver = DayOfWeek.THURSDAY;
                case "V" -> devolver = DayOfWeek.FRIDAY;
                case "S" -> devolver = DayOfWeek.SATURDAY;
                case "D" -> devolver = DayOfWeek.SUNDAY;}
            return devolver;
            }
        else{
            return devolver;
        }
    }
    
    public final List<Actividad> buscarActividadDiaSemana(ArrayList<Actividad> listaActividad,DayOfWeek diaSemana){
        try{
            List<Actividad> listaFiltrada = listaActividad.stream().filter(act->act.getDiaSemanaRecursivo()!=null).collect(Collectors.toList());
            List<Actividad> listaDevolver = listaFiltrada.stream().filter(act->act.getDiaSemanaRecursivo().equals(diaSemana)).collect(Collectors.toList());
            return listaDevolver;
        }
        catch (Exception error){
            List<Actividad> listaDevolver = new ArrayList<>();
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

        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(220, 70));
        setMinimumSize(new java.awt.Dimension(220, 70));
        setPreferredSize(new java.awt.Dimension(220, 70));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setMaximumSize(new java.awt.Dimension(75, 50));
        jComboBox1.setMinimumSize(new java.awt.Dimension(75, 50));
        jComboBox1.setPreferredSize(new java.awt.Dimension(75, 50));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addActionListener(this::jTextField1ActionPerformed);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        this.seleccionado = this.jComboBox1.getSelectedItem().toString();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        this.busqueda = this.jTextField1.getText();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        this.busqueda = this.jTextField1.getText();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        if (((String)this.jComboBox1.getSelectedItem()).equals("Dia")){
           this.aparecerCalendario();
       }
       this.busqueda = (String)this.jTextField1.getText();
       this.seleccionado = (String) this.jComboBox1.getSelectedItem();
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained

    }//GEN-LAST:event_jTextField1FocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
