/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pl2.darioedu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.SpinnerNumberModel;

/**
 * Ventana Modificar Actividad
 * @author Daríoooooo :)
 * @author Eduardo
 * Esta es la ventana para modificar la actividad. Muestra los aspectos de la actividad anterior y algunos que no se pueden modificar.
 * Asimismo, se incluye varias funcionalidades como la de seleccionar de un JComboBox la imagen deseada o para seleccionar si la actividad
 * es o no extraordinaria.
 */
public class PanelModificarAct extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PanelModificarAct.class.getName());
    private int horaIncioSeleccionada;
    private int horaFinSeleccionada = 0;
    private boolean especial;
    private int dia;
    private int anno;
    private int mes;
    private Sala sala;
    private String nombre;
    private String tipo;
    private String monitor;
    private DayOfWeek recursivo;
    private Actividad actividad;
    
    /**
     * Creates new form PanelConsultaSocios
     * @param actPrevia
     */
    
    public PanelModificarAct (Actividad actPrevia) {
        initComponents();
        this.actividad = actPrevia;
        this.sala = actPrevia.getSala();
        this.Error.setVisible(false);
        this.setCampos(actPrevia);
        //Para que la modficicación se haga dentro de la lista de Actividades<
        this.getReferenceActividad(actividad);
    }
    
    
    public final void getReferenceActividad(Actividad actividad){
        ArrayList<Actividad> listaAct = UtilTienda.getInstancia().getListaActividadStatic();
        for (Actividad act : listaAct){
            if (act.equals(actividad)){
                this.actividad = act;
                break;
            }
        }
    }
    
    private DayOfWeek stringToDayOfWeek(String diaSemana){
        DayOfWeek devolver = null;
        switch (diaSemana){    
                case "Lunes" -> devolver = DayOfWeek.MONDAY;
                case "Martes" -> devolver = DayOfWeek.TUESDAY;
                case "Miércoles" -> devolver = DayOfWeek.WEDNESDAY;
                case "Jueves" -> devolver = DayOfWeek.THURSDAY;
                case "Viernes" -> devolver = DayOfWeek.FRIDAY;
                case "Sábado" -> devolver = DayOfWeek.SATURDAY;
                case "Domingo" -> devolver = DayOfWeek.SUNDAY;}
        return devolver;
    }
    private String dayOfWeekToString(DayOfWeek dia){
        String devolver = null;
        switch (dia){    
                case DayOfWeek.MONDAY -> devolver = "Lunes";
                case DayOfWeek.TUESDAY -> devolver = "Martes";
                case DayOfWeek.WEDNESDAY -> devolver = "Miércoles";
                case DayOfWeek.THURSDAY -> devolver = "Jueves";
                case DayOfWeek.FRIDAY -> devolver = "Viernes";
                case DayOfWeek.SATURDAY -> devolver = "Sábado";
                case DayOfWeek.SUNDAY -> devolver = "Domingo";
                case null -> devolver = "Ninguno";}
        return devolver;
    }
    
    private boolean coincideSesion(){
        ArrayList<Actividad> listaAct = UtilTienda.getInstancia().getListaActividadStatic();
        boolean condicion = listaAct.stream().noneMatch(act->act.getSesiones().stream().noneMatch(ses->ses.getNumDiA()==this.dia&&ses.getMes()==this.mes&&ses.getAnno()==anno));
        return !condicion;
    }
    
    private boolean coincideRecursivo(){
        ArrayList<Actividad> listaAct = UtilTienda.getInstancia().getListaActividadStatic();
        List<Actividad> listaAux = listaAct.stream().filter(act->act.getDiaSemanaRecursivo()!=null).collect(Collectors.toList());
        //Filtramos para cuando ninguna de las actividades contiene a la nuestra
        boolean condicion = listaAux.stream().noneMatch(act->act.getSesiones().stream().noneMatch(ses->ses.getHoraFin()==this.horaFinSeleccionada&&ses.getHoraInicio()==this.horaIncioSeleccionada));
        return !condicion;
    }
    
    private boolean coincideActividad(){
        ArrayList<Actividad> listaAct = UtilTienda.getInstancia().getListaActividadStatic();
        boolean condicion1 = listaAct.stream().noneMatch(act->act.getTitulo().equals(this.nombre));
        return !condicion1;
    }
    
    private void setError(String error){
        this.Error.setText(error);
        this.Error.setVisible(true);
    }
    
    private void ocultarRecursivo(boolean ocultar){
        this.jFormattedTextField1.setEditable(ocultar);
        this.jSpinner3.setEnabled(ocultar);
        this.jSpinner4.setEnabled(ocultar);
        this.jComboBox1.setEditable(!ocultar);
        this.jComboBox1.setEnabled(!ocultar);
        this.jSpinner1.setEnabled(!ocultar);
        this.jSpinner2.setEnabled(!ocultar);
    }
    
    private void ocultarError(){
        this.Error.setVisible(false);
    }
    
    public final void setCampos(Actividad actividad){
        this.Campo1.setText(actividad.getTitulo());
        this.Campo2.setText(actividad.getTipo());
        this.Campo3.setText(actividad.getMonitor());
        this.Campo6.setText("Nombre : " + actividad.getSala().getNombre() + "Aforo: " + actividad.getSala().getAforo());
        this.nombre = actividad.getTitulo();
        this.tipo = actividad.getTipo();
        this.sala = actividad.getSala();
        this.recursivo = actividad.getDiaSemanaRecursivo();
        if (this.recursivo == null){
            this.ocultarRecursivo(true);
        }
        else{
            String dia1 = this.dayOfWeekToString(this.recursivo);
            this.jComboBox1.setSelectedItem(dia1);
            this.ocultarRecursivo(false);
        }
    }

    private void pasarValores(){
        this.actividad.setTitulo(this.nombre);
        this.actividad.setMonitor(this.monitor);
        this.actividad.setExtraordinario(this.especial);
        this.actividad.setTipo(this.tipo);
        this.actividad.setDiaSemanaRecursivo(this.recursivo);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButtonVolverAtras = new javax.swing.JButton();
        BotonAyuda = new javax.swing.JButton();
        Campo1 = new javax.swing.JTextField();
        Texto1 = new javax.swing.JLabel();
        Campo2 = new javax.swing.JTextField();
        Texto2 = new javax.swing.JLabel();
        Campo3 = new javax.swing.JTextField();
        Texto3 = new javax.swing.JLabel();
        Texto4 = new javax.swing.JLabel();
        Campo6 = new javax.swing.JTextField();
        Texto6 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jComboBox1 = new javax.swing.JComboBox<>();
        Error = new javax.swing.JLabel();
        GuardarCambios = new javax.swing.JButton();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        Texto7 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        Texto8 = new javax.swing.JLabel();
        Texto5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JavaFit - Admin Modify Activity - Version "+Globales.version);
        setMinimumSize(new java.awt.Dimension(493, 316));
        setResizable(false);

        jButtonVolverAtras.setBackground(new java.awt.Color(255, 153, 51));
        jButtonVolverAtras.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButtonVolverAtras.setText("<-");
        jButtonVolverAtras.addActionListener(this::jButtonVolverAtrasActionPerformed);

        BotonAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AyudaBoton.png"))); // NOI18N
        BotonAyuda.setMaximumSize(new java.awt.Dimension(50, 50));
        BotonAyuda.setMinimumSize(new java.awt.Dimension(50, 50));
        BotonAyuda.setPreferredSize(new java.awt.Dimension(50, 50));
        BotonAyuda.addActionListener(this::BotonAyudaActionPerformed);

        Campo1.setMaximumSize(new java.awt.Dimension(351, 22));
        Campo1.setMinimumSize(new java.awt.Dimension(351, 22));
        Campo1.setPreferredSize(new java.awt.Dimension(351, 22));
        Campo1.addActionListener(this::Campo1ActionPerformed);
        Campo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Campo1KeyReleased(evt);
            }
        });

        Texto1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Texto1.setText("Nombre:");

        Campo2.setMaximumSize(new java.awt.Dimension(351, 22));
        Campo2.setMinimumSize(new java.awt.Dimension(351, 22));
        Campo2.setPreferredSize(new java.awt.Dimension(351, 22));
        Campo2.addActionListener(this::Campo2ActionPerformed);
        Campo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Campo2KeyReleased(evt);
            }
        });

        Texto2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Texto2.setText("Tipo");

        Campo3.setMaximumSize(new java.awt.Dimension(351, 22));
        Campo3.setMinimumSize(new java.awt.Dimension(351, 22));
        Campo3.setPreferredSize(new java.awt.Dimension(351, 22));
        Campo3.addActionListener(this::Campo3ActionPerformed);
        Campo3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Campo3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Campo3KeyTyped(evt);
            }
        });

        Texto3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Texto3.setText("Monitor");

        Texto4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Texto4.setText("Dia Semana:");

        Campo6.setEditable(false);
        Campo6.setMaximumSize(new java.awt.Dimension(351, 22));
        Campo6.setMinimumSize(new java.awt.Dimension(351, 22));
        Campo6.setPreferredSize(new java.awt.Dimension(351, 22));
        Campo6.addActionListener(this::Campo6ActionPerformed);
        Campo6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Campo6KeyReleased(evt);
            }
        });

        Texto6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Texto6.setText("Sala:");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, this.horaIncioSeleccionada+1, 1));
        jSpinner1.addChangeListener(this::jSpinner1StateChanged);

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        jSpinner2.addChangeListener(this::jSpinner2StateChanged);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo", "Ninguno" }));
        jComboBox1.setMaximumSize(new java.awt.Dimension(211, 22));
        jComboBox1.setMinimumSize(new java.awt.Dimension(211, 22));
        jComboBox1.setPreferredSize(new java.awt.Dimension(211, 22));
        jComboBox1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox1FocusLost(evt);
            }
        });
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        Error.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Error.setForeground(new java.awt.Color(255, 0, 0));
        Error.setText("Evento ya programado en esa fecha");

        GuardarCambios.setBackground(new java.awt.Color(255, 153, 51));
        GuardarCambios.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        GuardarCambios.setText("Guardar Cambios");
        GuardarCambios.addActionListener(this::GuardarCambiosActionPerformed);

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yy"))));
        jFormattedTextField1.setToolTipText("La fecha introducida ha de ser sin carácteres intermedios de la forma dd-mm-yyyy");
        jFormattedTextField1.setMaximumSize(new java.awt.Dimension(211, 22));
        jFormattedTextField1.setMinimumSize(new java.awt.Dimension(211, 22));
        jFormattedTextField1.setPreferredSize(new java.awt.Dimension(211, 22));
        jFormattedTextField1.addActionListener(this::jFormattedTextField1ActionPerformed);
        jFormattedTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextField1KeyReleased(evt);
            }
        });

        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(0, 0, this.horaIncioSeleccionada+1, 1));
        jSpinner3.addChangeListener(this::jSpinner3StateChanged);

        jSpinner4.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        jSpinner4.addChangeListener(this::jSpinner4StateChanged);

        Texto7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Texto7.setText("Día:");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Si es especial");
        jRadioButton1.addActionListener(this::jRadioButton1ActionPerformed);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("No es especial");
        jRadioButton2.addActionListener(this::jRadioButton2ActionPerformed);

        Texto8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Texto8.setText("¿Es especial?:");

        Texto5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Texto5.setText("Imagen:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 30, Short.MAX_VALUE))
                            .addComponent(Texto4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Texto3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Texto7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(Texto2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Texto1, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(Texto6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Error)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BotonAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Campo3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Campo2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(GuardarCambios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Campo1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(Campo6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(Texto5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGap(0, 11, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Texto8, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton1)
                        .addGap(29, 29, 29))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotonAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Error)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Campo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Texto1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Campo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Texto2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Campo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Texto3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Texto4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Texto7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Campo6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Texto6)
                    .addComponent(Texto5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(Texto8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GuardarCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVolverAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverAtrasActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButtonVolverAtrasActionPerformed

    private void BotonAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAyudaActionPerformed
        new PanelAyuda("Este panel te permite modificar la actividad que se haya seleccionado en el apartado de Gestión de Actividades").setVisible(true);
    }//GEN-LAST:event_BotonAyudaActionPerformed

    private void Campo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Campo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Campo1ActionPerformed

    private void Campo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Campo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Campo2ActionPerformed

    private void Campo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Campo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Campo3ActionPerformed

    private void Campo6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Campo6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Campo6ActionPerformed

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged
        this.horaIncioSeleccionada = (int) this.jSpinner2.getValue();
    }//GEN-LAST:event_jSpinner2StateChanged

    private void Campo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo1KeyReleased
        if (this.Campo1.getText() == null){
            this.setError("No se puede tener un campo nulo");
        }
        else{
            this.ocultarError();
            this.nombre = this.Campo1.getText();
        }
    }//GEN-LAST:event_Campo1KeyReleased

    private void Campo2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo2KeyReleased
        if (this.Campo2.getText() == null){
            this.setError("No se puede tener un campo nulo");
        }
        else{
            this.ocultarError();
            this.tipo = this.Campo2.getText();
        }
    }//GEN-LAST:event_Campo2KeyReleased

    private void Campo3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_Campo3KeyTyped

    private void Campo3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo3KeyReleased
        if (this.Campo3.getText() == null){
            this.setError("No se puede tener un campo nulo");
        }
        else{
            this.ocultarError();
            this.monitor = this.Campo3.getText();
        }
    }//GEN-LAST:event_Campo3KeyReleased

    private void Campo6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo6KeyReleased

    }//GEN-LAST:event_Campo6KeyReleased

    private void jComboBox1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusLost

    }//GEN-LAST:event_jComboBox1FocusLost

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        this.jSpinner1.setModel(new SpinnerNumberModel(0, 0, this.horaIncioSeleccionada+1, 1));
        this.horaFinSeleccionada = (int) this.jSpinner1.getValue();
    }//GEN-LAST:event_jSpinner1StateChanged

    private void GuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarCambiosActionPerformed
        System.out.println("Pasado a guardar cambios");
        if (this.recursivo == null&&!this.coincideSesion()&&!this.coincideActividad()){    
            this.pasarValores();
            UtilTienda.getInstancia().guardarListaActividades(UtilTienda.getInstancia().getListaActividadStatic());
            this.setVisible(false);
            this.dispose();
            System.out.println("Cambios guardados con éxito");}
        else if (this.recursivo != null&&!this.coincideRecursivo()&&!this.coincideActividad()){
            this.pasarValores();
            UtilTienda.getInstancia().guardarListaActividades(UtilTienda.getInstancia().getListaActividadStatic());
            this.setVisible(false);
            this.dispose();}
        else {
            this.setError("Los campos introducidos no son válidos ");
            System.out.println("Cambios no guardados");
        }
    }//GEN-LAST:event_GuardarCambiosActionPerformed

    private void jSpinner3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner3StateChanged
        this.jSpinner3.setModel(new SpinnerNumberModel(0, 0, this.horaIncioSeleccionada+1, 1));
        this.horaFinSeleccionada = (int)this.jSpinner1.getValue();
    }//GEN-LAST:event_jSpinner3StateChanged

    private void jSpinner4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner4StateChanged
         this.horaIncioSeleccionada = (int)this.jSpinner4.getValue();
    }//GEN-LAST:event_jSpinner4StateChanged

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed

    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (String.valueOf(this.jComboBox1.getSelectedItem()).equals("Ninguno")){
            this.setError("No se seleccionará día recursivo");
            this.recursivo = null;
            this.ocultarRecursivo(true);
        }
    else{
        this.setError("Se seleccionará" + String.valueOf(this.jComboBox1.getSelectedItem())+"como día recursivo");
        this.recursivo = this.stringToDayOfWeek((String)this.jComboBox1.getSelectedItem());
        this.ocultarRecursivo(false);
    }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jFormattedTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField1KeyReleased
        try{
        int diaS = Integer.parseInt(String.valueOf(this.jFormattedTextField1.getValue()).substring(0,2));
        int mesS = Integer.parseInt(String.valueOf(this.jFormattedTextField1.getValue()).substring(2,4));
        int annoS = Integer.parseInt(String.valueOf(this.jFormattedTextField1.getValue()).substring(4,8));
        LocalDate diaM = LocalDate.of(annoS,mesS,diaS);
        if (LocalDate.now().isAfter(diaM)||LocalDate.now().isEqual(diaM)){
            this.setError("La fecha seleccionada no es válida");
            }
        else{
            this.dia = diaS;
            this.mes = mesS;
            this.anno = annoS;
            }
        } catch(NumberFormatException | IndexOutOfBoundsException error){
            this.setError("El formato de la fecha no es válido");
        }
        
    }//GEN-LAST:event_jFormattedTextField1KeyReleased

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        this.especial = false;
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        this.especial = true;
    }//GEN-LAST:event_jRadioButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAyuda;
    private javax.swing.JTextField Campo1;
    private javax.swing.JTextField Campo2;
    private javax.swing.JTextField Campo3;
    private javax.swing.JTextField Campo6;
    private javax.swing.JLabel Error;
    private javax.swing.JButton GuardarCambios;
    private javax.swing.JLabel Texto1;
    private javax.swing.JLabel Texto2;
    private javax.swing.JLabel Texto3;
    private javax.swing.JLabel Texto4;
    private javax.swing.JLabel Texto5;
    private javax.swing.JLabel Texto6;
    private javax.swing.JLabel Texto7;
    private javax.swing.JLabel Texto8;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonVolverAtras;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    // End of variables declaration//GEN-END:variables
}
