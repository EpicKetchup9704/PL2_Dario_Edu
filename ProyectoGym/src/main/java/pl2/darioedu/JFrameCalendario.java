/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pl2.darioedu;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author Darío
 */
public class JFrameCalendario extends javax.swing.JFrame {
    private int ultimoPulsado;
    private JTextField areaRellenar;
    private int mes;
    private String nombreMes;
    private int anno;
    private final int diaMin = LocalDate.now().getDayOfMonth();
    private final int annoMin = LocalDate.now().getYear();
    private final int mesMin = LocalDate.now().getMonthValue();
    private final int annoMax = 2030; //Establecemos el valor máximo de los años a x.
    private final int mesMax = 12; //Establecemos el mes máximo
    private final int diaMax = 31; //Establecemos el día maximo
    private ArrayList<Integer> valoresBotones;
    private final ArrayList<JToggleButton> listaBotones;
    /**
     * Creates new form CalendarioJPanel
     * 
     */
    
    public JFrameCalendario() {
        this.mes = LocalDate.now().getMonthValue(); //->int
        this.anno = LocalDate.now().getYear(); //->int
        this.nombreMes = Globales.listaMeses[mes-1];
        this.ultimoPulsado = this.diaMax;
        initComponents(); //->Se inician componentes
        this.listaBotones = new ArrayList<>(Arrays.asList(B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,B11,B12,B13,B14,B15,B16,B17,B18,B19,B20,B21,B22,B23,B24,B25,B26,B27,B28,B29,B30,B31,B32,B33,B34,B35,B36,B37,B38,B39,B40,B41,B42));
        this.Anno.setText(this.nombreMes + " " + String.valueOf(this.anno)); //Se va a poner el texo como mes + + anno
        this.valoresBotones = this.crearValoresListaBotonesMin(this.anno, this.mes);
        this.setValorBotones(this.valoresBotones);
        this.jButton1.setEnabled(false);
        this.jButton2.setEnabled(true);
        this.areaRellenar = null;
        this.jLabel1.setText(setTextoMostrar());
        
    }
    
    public JFrameCalendario(JTextField area){
        this.areaRellenar = area;
        this.mes = LocalDate.now().getMonthValue(); //->int
        this.anno = LocalDate.now().getYear(); //->int
        this.nombreMes = Globales.listaMeses[mes-1];
        this.ultimoPulsado = this.diaMax;
        initComponents(); //->Se inician componentes
        this.listaBotones = new ArrayList<>(Arrays.asList(B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,B11,B12,B13,B14,B15,B16,B17,B18,B19,B20,B21,B22,B23,B24,B25,B26,B27,B28,B29,B30,B31,B32,B33,B34,B35,B36,B37,B38,B39,B40,B41,B42));
        this.Anno.setText(this.nombreMes + " " + String.valueOf(this.anno)); //Se va a poner el texo como mes + + anno
        this.valoresBotones = this.crearValoresListaBotonesMin(this.anno, this.mes);
        this.setValorBotones(this.valoresBotones);
        this.jButton1.setEnabled(false);
        this.jButton2.setEnabled(true);
        this.jLabel1.setText(setTextoMostrar());
    }
    
    public JFrameCalendario(int mes, String nombreMes, int anno, JTextField area) {
        this.areaRellenar = area;
        this.mes = mes;
        this.nombreMes = nombreMes;
        this.anno = anno;
        this.nombreMes = Globales.listaMeses[mes-1];
        initComponents();
        this.listaBotones = new ArrayList<>(Arrays.asList(B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,B11,B12,B13,B14,B15,B16,B17,B18,B19,B20,B21,B22,B23,B24,B25,B26,B27,B28,B29,B30,B31,B32,B33,B34,B35,B36,B37,B38,B39,B40,B41,B42));
        this.Anno.setText(Globales.listaMeses[mes-1] + " " + String.valueOf(anno));
        ArrayList<Integer> valores = JFrameCalendario.crearValoresListaBotones(anno, mes);
        this.setValorBotones(valores);
    }
    
    public void setArea(JTextField area){
        this.areaRellenar= area;
    }
    
    public void setColorCelda(int dia, Color color){
        //Con este método hacemos que una celda (correspondiente con su posicion) se coloree de un color en concreto
        this.listaBotones.get(this.valoresBotones.get(dia)).setForeground(color);
    }
    public boolean[] setEstadoFlechas(int anno, int mes){
    if (anno == annoMin && mes == mesMin){
        boolean [] devolver ={false, true};
        return devolver;
    }
    else if (anno == annoMax && mes == mesMax){
        boolean [] devolver ={true,false};
        return devolver;
    }
    else{
        boolean [] devolver ={true, true};
        return devolver;}
}
    public final String setTextoMostrar(){
        String seleccion = this.getFechaSelecionada('-');
        return "Fecha: " + seleccion;
    }
    public void refrescarJPanel(int anno, int mes){
        this.modificarEstadoResto("null");
        this.ultimoPulsado = 1;
        this.anno = anno;
        this.mes = mes;
        this.jLabel1.setText(setTextoMostrar());
        this.jButton1.setEnabled(setEstadoFlechas(anno,mes)[0]);
        this.jButton2.setEnabled(setEstadoFlechas(anno,mes)[1]);
        this.nombreMes = Globales.listaMeses[mes-1];
        this.Anno.setText(this.nombreMes + " " + String.valueOf(this.anno));
        if (anno<=annoMin && mes <=mesMin){
            this.setValorBotones(this.crearValoresListaBotonesMin(anno, mes));
        }
        else if (anno >=annoMax && mes >=mesMax){
            this.setValorBotones(this.crearValoresListaBotonesMax(anno, mes));
        }
        else{this.setValorBotones(crearValoresListaBotones(anno, mes));}
        
    }
    public static final ArrayList<Integer> crearValoresListaBotones(int anno, int mes){
        /*
        Permite crear una lista de valores enteros para el estado de los botones (0 si no es visible y su correspondiente dia)
        */
        ArrayList<Integer> lista = new ArrayList<>();//Ponemos Integer (clase envoltura) ya que int se refiere al tipo de dato
        int numero = LocalDate.of(anno,mes,1).getDayOfWeek().getValue(); //Empezamos averiguando qué día empieza el mes y lo pasamos a su valor enter
        int max = LocalDate.of(anno,mes,1).lengthOfMonth(); //Devuelve el último día del mes
        //Esta parte es para insertar los números correspondientes antes de la aparicion del uno
        for (int i=1; i<(numero);i++){
            lista.add(0);
        }
        //Esta parte es para añadir todos los días del mes
        for (int i = 1; i <= max; i++){
            lista.add(i);
        }
        while (lista.size() < 42){
            lista.add(0);
        }
        return lista;
    }
    public final ArrayList<Integer> crearValoresListaBotonesMin(int anno, int mes){
        //Método para cuando el mes coincide con el mes min y anno min coinciden
        ArrayList<Integer> listaAuxiliar = crearValoresListaBotones(anno,mes);
                for (int i = 0; i<=diaMin;i++){
                    listaAuxiliar.set(i, 0);
                }
        return listaAuxiliar;
        
    }
    public final ArrayList<Integer> crearValoresListaBotonesMax(int anno, int mes){
        //Método para cuando el mes coincide con el mes max y anno max coinciden
        ArrayList<Integer> listaAuxiliar = crearValoresListaBotones(anno,mes);
                for (int i = diaMax - 1; i<listaAuxiliar.size();i++){
                    listaAuxiliar.set(i, 0);
                }
        return listaAuxiliar;
    }
    
    public void setPeriodo(int mes, int anno){
        this.mes = mes;
        this.anno = anno;
        this.nombreMes = Globales.listaMeses[mes-1];
    }
    
    public void setTitulo(int mes, int anno){
        this.Anno.setText(Globales.listaMeses[mes-1] +" "+ String.valueOf(anno));
    }
    
    
    
    public final void setValorBotones(ArrayList<Integer> listaNum){ //Lo llamamos en el constructor y puede hacer una sobrecarga de metodos = final
        for (int i = 0; i < this.listaBotones.size(); i++){
        JToggleButton aux = this.listaBotones.get(i);
        int valor = (int)listaNum.get(i);
        if (valor==0){
            aux.setText(" ");
            aux.setEnabled(false);}
        else {
            if((this.mes == mesMin) && (this.anno == annoMin) && (valor<diaMin)){
                aux.setText(Integer.toString(valor));
                aux.setEnabled(false);}
            else if((this.mes == mesMax) && (this.anno == annoMax) && (valor>=diaMax)){
                aux.setText(Integer.toString(valor));
                aux.setEnabled(false);}
            else{
                aux.setText(Integer.toString(valor));
                aux.setEnabled(true);  }
                }
        }
        
    }
    
    public void modificarEstadoResto(String identificador){
        for (JToggleButton aux : this.listaBotones){
             aux.setSelected(aux.getText().equals(identificador));
                     }
        this.jLabel1.setText(this.setTextoMostrar());

    }
    
    public int getMesCalendario(){
        return this.mes;
    }
    
    public int getAnnoCalendario(){
        return this.anno;
    }
    public int getDiaCalendarioPulsadoUltimo(){
        return this.ultimoPulsado;
    }
    
    public String getFechaSelecionada(){
        String mesAux = (this.mes<10) ? ("0" + String.valueOf(mes)) : String.valueOf(mes);
        String diaAux = this.ultimoPulsado<10 ? "0" + String.valueOf(this.ultimoPulsado) : String.valueOf(this.ultimoPulsado);
        String annoAux = String.valueOf(this.anno);
        return (annoAux+mesAux+diaAux);

    }
        public String getFechaSelecionada(char separador){
        String mesAux = this.mes<10 ? "0" + String.valueOf(mes) : String.valueOf(mes);
        String diaAux = this.ultimoPulsado<10 ? "0" + String.valueOf(this.ultimoPulsado) : String.valueOf(this.ultimoPulsado);
        String annoAux = String.valueOf(this.anno);
        return annoAux + separador + mesAux + separador + diaAux;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Anno = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        B1 = new javax.swing.JToggleButton();
        B2 = new javax.swing.JToggleButton();
        B3 = new javax.swing.JToggleButton();
        B4 = new javax.swing.JToggleButton();
        B5 = new javax.swing.JToggleButton();
        B6 = new javax.swing.JToggleButton();
        B7 = new javax.swing.JToggleButton();
        B14 = new javax.swing.JToggleButton();
        B9 = new javax.swing.JToggleButton();
        B10 = new javax.swing.JToggleButton();
        B11 = new javax.swing.JToggleButton();
        B12 = new javax.swing.JToggleButton();
        B13 = new javax.swing.JToggleButton();
        B8 = new javax.swing.JToggleButton();
        B21 = new javax.swing.JToggleButton();
        B16 = new javax.swing.JToggleButton();
        B17 = new javax.swing.JToggleButton();
        B18 = new javax.swing.JToggleButton();
        B19 = new javax.swing.JToggleButton();
        B20 = new javax.swing.JToggleButton();
        B15 = new javax.swing.JToggleButton();
        B26 = new javax.swing.JToggleButton();
        B27 = new javax.swing.JToggleButton();
        B22 = new javax.swing.JToggleButton();
        B28 = new javax.swing.JToggleButton();
        B23 = new javax.swing.JToggleButton();
        B24 = new javax.swing.JToggleButton();
        B25 = new javax.swing.JToggleButton();
        B33 = new javax.swing.JToggleButton();
        B34 = new javax.swing.JToggleButton();
        B29 = new javax.swing.JToggleButton();
        B35 = new javax.swing.JToggleButton();
        B30 = new javax.swing.JToggleButton();
        B31 = new javax.swing.JToggleButton();
        B32 = new javax.swing.JToggleButton();
        B36 = new javax.swing.JToggleButton();
        B37 = new javax.swing.JToggleButton();
        B38 = new javax.swing.JToggleButton();
        B39 = new javax.swing.JToggleButton();
        B40 = new javax.swing.JToggleButton();
        B41 = new javax.swing.JToggleButton();
        B42 = new javax.swing.JToggleButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(210, 450));
        jPanel1.setMinimumSize(new java.awt.Dimension(210, 450));

        Anno.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        Anno.setText("Septiembre 2026");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FlechaIzq.png"))); // NOI18N
        jButton1.setMaximumSize(new java.awt.Dimension(75, 75));
        jButton1.setMinimumSize(new java.awt.Dimension(75, 75));
        jButton1.setPreferredSize(new java.awt.Dimension(75, 75));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FlechaDer.png"))); // NOI18N
        jButton2.setMaximumSize(new java.awt.Dimension(75, 75));
        jButton2.setMinimumSize(new java.awt.Dimension(75, 75));
        jButton2.setPreferredSize(new java.awt.Dimension(75, 75));
        jButton2.addActionListener(this::jButton2ActionPerformed);

        B1.setText("1");
        B1.setMaximumSize(new java.awt.Dimension(43, 43));
        B1.setMinimumSize(new java.awt.Dimension(43, 43));
        B1.setPreferredSize(new java.awt.Dimension(43, 43));
        B1.addChangeListener(this::B1StateChanged);
        B1.addActionListener(this::B1ActionPerformed);

        B2.setText("1");
        B2.setMaximumSize(new java.awt.Dimension(43, 43));
        B2.setMinimumSize(new java.awt.Dimension(43, 43));
        B2.setPreferredSize(new java.awt.Dimension(43, 43));
        B2.addChangeListener(this::B2StateChanged);
        B2.addActionListener(this::B2ActionPerformed);

        B3.setText("1");
        B3.setMaximumSize(new java.awt.Dimension(43, 43));
        B3.setMinimumSize(new java.awt.Dimension(43, 43));
        B3.setPreferredSize(new java.awt.Dimension(43, 43));
        B3.addChangeListener(this::B3StateChanged);
        B3.addActionListener(this::B3ActionPerformed);

        B4.setText("1");
        B4.setMaximumSize(new java.awt.Dimension(43, 43));
        B4.setMinimumSize(new java.awt.Dimension(43, 43));
        B4.setPreferredSize(new java.awt.Dimension(43, 43));
        B4.addChangeListener(this::B4StateChanged);
        B4.addActionListener(this::B4ActionPerformed);

        B5.setText("1");
        B5.setMaximumSize(new java.awt.Dimension(43, 43));
        B5.setMinimumSize(new java.awt.Dimension(43, 43));
        B5.setPreferredSize(new java.awt.Dimension(43, 43));
        B5.addChangeListener(this::B5StateChanged);
        B5.addActionListener(this::B5ActionPerformed);

        B6.setText("1");
        B6.setMaximumSize(new java.awt.Dimension(43, 43));
        B6.setMinimumSize(new java.awt.Dimension(43, 43));
        B6.setPreferredSize(new java.awt.Dimension(43, 43));
        B6.addChangeListener(this::B6StateChanged);
        B6.addActionListener(this::B6ActionPerformed);

        B7.setText("1");
        B7.setMaximumSize(new java.awt.Dimension(43, 43));
        B7.setMinimumSize(new java.awt.Dimension(43, 43));
        B7.setPreferredSize(new java.awt.Dimension(43, 43));
        B7.addChangeListener(this::B7StateChanged);
        B7.addActionListener(this::B7ActionPerformed);

        B14.setText("1");
        B14.setMaximumSize(new java.awt.Dimension(43, 43));
        B14.setMinimumSize(new java.awt.Dimension(43, 43));
        B14.setPreferredSize(new java.awt.Dimension(43, 43));
        B14.addChangeListener(this::B14StateChanged);
        B14.addActionListener(this::B14ActionPerformed);

        B9.setText("1");
        B9.setMaximumSize(new java.awt.Dimension(43, 43));
        B9.setMinimumSize(new java.awt.Dimension(43, 43));
        B9.setPreferredSize(new java.awt.Dimension(43, 43));
        B9.addChangeListener(this::B9StateChanged);
        B9.addActionListener(this::B9ActionPerformed);

        B10.setText("1");
        B10.setMaximumSize(new java.awt.Dimension(43, 43));
        B10.setMinimumSize(new java.awt.Dimension(43, 43));
        B10.setPreferredSize(new java.awt.Dimension(43, 43));
        B10.addChangeListener(this::B10StateChanged);
        B10.addActionListener(this::B10ActionPerformed);

        B11.setText("1");
        B11.setMaximumSize(new java.awt.Dimension(43, 43));
        B11.setMinimumSize(new java.awt.Dimension(43, 43));
        B11.setPreferredSize(new java.awt.Dimension(43, 43));
        B11.addChangeListener(this::B11StateChanged);
        B11.addActionListener(this::B11ActionPerformed);

        B12.setText("1");
        B12.setMaximumSize(new java.awt.Dimension(43, 43));
        B12.setMinimumSize(new java.awt.Dimension(43, 43));
        B12.setPreferredSize(new java.awt.Dimension(43, 43));
        B12.addChangeListener(this::B12StateChanged);
        B12.addActionListener(this::B12ActionPerformed);

        B13.setText("1");
        B13.setMaximumSize(new java.awt.Dimension(43, 43));
        B13.setMinimumSize(new java.awt.Dimension(43, 43));
        B13.setPreferredSize(new java.awt.Dimension(43, 43));
        B13.addChangeListener(this::B13StateChanged);
        B13.addActionListener(this::B13ActionPerformed);

        B8.setText("1");
        B8.setMaximumSize(new java.awt.Dimension(43, 43));
        B8.setMinimumSize(new java.awt.Dimension(43, 43));
        B8.setPreferredSize(new java.awt.Dimension(43, 43));
        B8.addChangeListener(this::B8StateChanged);
        B8.addActionListener(this::B8ActionPerformed);

        B21.setText("1");
        B21.setMaximumSize(new java.awt.Dimension(43, 43));
        B21.setMinimumSize(new java.awt.Dimension(43, 43));
        B21.setPreferredSize(new java.awt.Dimension(43, 43));
        B21.addChangeListener(this::B21StateChanged);
        B21.addActionListener(this::B21ActionPerformed);

        B16.setText("1");
        B16.setMaximumSize(new java.awt.Dimension(43, 43));
        B16.setMinimumSize(new java.awt.Dimension(43, 43));
        B16.setPreferredSize(new java.awt.Dimension(43, 43));
        B16.addChangeListener(this::B16StateChanged);
        B16.addActionListener(this::B16ActionPerformed);

        B17.setText("1");
        B17.setMaximumSize(new java.awt.Dimension(43, 43));
        B17.setMinimumSize(new java.awt.Dimension(43, 43));
        B17.setPreferredSize(new java.awt.Dimension(43, 43));
        B17.addChangeListener(this::B17StateChanged);
        B17.addActionListener(this::B17ActionPerformed);

        B18.setText("1");
        B18.setMaximumSize(new java.awt.Dimension(43, 43));
        B18.setMinimumSize(new java.awt.Dimension(43, 43));
        B18.setPreferredSize(new java.awt.Dimension(43, 43));
        B18.addChangeListener(this::B18StateChanged);
        B18.addActionListener(this::B18ActionPerformed);

        B19.setText("1");
        B19.setMaximumSize(new java.awt.Dimension(43, 43));
        B19.setMinimumSize(new java.awt.Dimension(43, 43));
        B19.setPreferredSize(new java.awt.Dimension(43, 43));
        B19.addChangeListener(this::B19StateChanged);
        B19.addActionListener(this::B19ActionPerformed);

        B20.setText("1");
        B20.setMaximumSize(new java.awt.Dimension(43, 43));
        B20.setMinimumSize(new java.awt.Dimension(43, 43));
        B20.setPreferredSize(new java.awt.Dimension(43, 43));
        B20.addChangeListener(this::B20StateChanged);
        B20.addActionListener(this::B20ActionPerformed);

        B15.setText("1");
        B15.setMaximumSize(new java.awt.Dimension(43, 43));
        B15.setMinimumSize(new java.awt.Dimension(43, 43));
        B15.setPreferredSize(new java.awt.Dimension(43, 43));
        B15.addChangeListener(this::B15StateChanged);
        B15.addActionListener(this::B15ActionPerformed);

        B26.setText("1");
        B26.setMaximumSize(new java.awt.Dimension(43, 43));
        B26.setMinimumSize(new java.awt.Dimension(43, 43));
        B26.setPreferredSize(new java.awt.Dimension(43, 43));
        B26.addChangeListener(this::B26StateChanged);
        B26.addActionListener(this::B26ActionPerformed);

        B27.setText("1");
        B27.setMaximumSize(new java.awt.Dimension(43, 43));
        B27.setMinimumSize(new java.awt.Dimension(43, 43));
        B27.setPreferredSize(new java.awt.Dimension(43, 43));
        B27.addChangeListener(this::B27StateChanged);
        B27.addActionListener(this::B27ActionPerformed);

        B22.setText("1");
        B22.setMaximumSize(new java.awt.Dimension(43, 43));
        B22.setMinimumSize(new java.awt.Dimension(43, 43));
        B22.setPreferredSize(new java.awt.Dimension(43, 43));
        B22.addChangeListener(this::B22StateChanged);
        B22.addActionListener(this::B22ActionPerformed);

        B28.setText("1");
        B28.setMaximumSize(new java.awt.Dimension(43, 43));
        B28.setMinimumSize(new java.awt.Dimension(43, 43));
        B28.setPreferredSize(new java.awt.Dimension(43, 43));
        B28.addChangeListener(this::B28StateChanged);
        B28.addActionListener(this::B28ActionPerformed);

        B23.setText("1");
        B23.setMaximumSize(new java.awt.Dimension(43, 43));
        B23.setMinimumSize(new java.awt.Dimension(43, 43));
        B23.setPreferredSize(new java.awt.Dimension(43, 43));
        B23.addChangeListener(this::B23StateChanged);
        B23.addActionListener(this::B23ActionPerformed);

        B24.setText("1");
        B24.setMaximumSize(new java.awt.Dimension(43, 43));
        B24.setMinimumSize(new java.awt.Dimension(43, 43));
        B24.setPreferredSize(new java.awt.Dimension(43, 43));
        B24.addChangeListener(this::B24StateChanged);
        B24.addActionListener(this::B24ActionPerformed);

        B25.setText("1");
        B25.setMaximumSize(new java.awt.Dimension(43, 43));
        B25.setMinimumSize(new java.awt.Dimension(43, 43));
        B25.setPreferredSize(new java.awt.Dimension(43, 43));
        B25.addChangeListener(this::B25StateChanged);
        B25.addActionListener(this::B25ActionPerformed);

        B33.setText("1");
        B33.setMaximumSize(new java.awt.Dimension(43, 43));
        B33.setMinimumSize(new java.awt.Dimension(43, 43));
        B33.setPreferredSize(new java.awt.Dimension(43, 43));
        B33.addChangeListener(this::B33StateChanged);
        B33.addActionListener(this::B33ActionPerformed);

        B34.setText("1");
        B34.setMaximumSize(new java.awt.Dimension(43, 43));
        B34.setMinimumSize(new java.awt.Dimension(43, 43));
        B34.setPreferredSize(new java.awt.Dimension(43, 43));
        B34.addChangeListener(this::B34StateChanged);
        B34.addActionListener(this::B34ActionPerformed);

        B29.setText("1");
        B29.setMaximumSize(new java.awt.Dimension(43, 43));
        B29.setMinimumSize(new java.awt.Dimension(43, 43));
        B29.setPreferredSize(new java.awt.Dimension(43, 43));
        B29.addChangeListener(this::B29StateChanged);
        B29.addActionListener(this::B29ActionPerformed);

        B35.setText("1");
        B35.setMaximumSize(new java.awt.Dimension(43, 43));
        B35.setMinimumSize(new java.awt.Dimension(43, 43));
        B35.setPreferredSize(new java.awt.Dimension(43, 43));
        B35.addChangeListener(this::B35StateChanged);
        B35.addActionListener(this::B35ActionPerformed);

        B30.setText("1");
        B30.setMaximumSize(new java.awt.Dimension(43, 43));
        B30.setMinimumSize(new java.awt.Dimension(43, 43));
        B30.setPreferredSize(new java.awt.Dimension(43, 43));
        B30.addChangeListener(this::B30StateChanged);
        B30.addActionListener(this::B30ActionPerformed);

        B31.setText("1");
        B31.setMaximumSize(new java.awt.Dimension(43, 43));
        B31.setMinimumSize(new java.awt.Dimension(43, 43));
        B31.setPreferredSize(new java.awt.Dimension(43, 43));
        B31.addChangeListener(this::B31StateChanged);
        B31.addActionListener(this::B31ActionPerformed);

        B32.setText("1");
        B32.setMaximumSize(new java.awt.Dimension(43, 43));
        B32.setMinimumSize(new java.awt.Dimension(43, 43));
        B32.setPreferredSize(new java.awt.Dimension(43, 43));
        B32.addChangeListener(this::B32StateChanged);
        B32.addActionListener(this::B32ActionPerformed);

        B36.setText("1");
        B36.setMaximumSize(new java.awt.Dimension(43, 43));
        B36.setMinimumSize(new java.awt.Dimension(43, 43));
        B36.setPreferredSize(new java.awt.Dimension(43, 43));
        B36.addChangeListener(this::B36StateChanged);
        B36.addActionListener(this::B36ActionPerformed);

        B37.setText("1");
        B37.setMaximumSize(new java.awt.Dimension(43, 43));
        B37.setMinimumSize(new java.awt.Dimension(43, 43));
        B37.setPreferredSize(new java.awt.Dimension(43, 43));
        B37.addChangeListener(this::B37StateChanged);
        B37.addActionListener(this::B37ActionPerformed);

        B38.setText("1");
        B38.setMaximumSize(new java.awt.Dimension(43, 43));
        B38.setMinimumSize(new java.awt.Dimension(43, 43));
        B38.setPreferredSize(new java.awt.Dimension(43, 43));
        B38.addChangeListener(this::B38StateChanged);
        B38.addActionListener(this::B38ActionPerformed);

        B39.setText("1");
        B39.setMaximumSize(new java.awt.Dimension(43, 43));
        B39.setMinimumSize(new java.awt.Dimension(43, 43));
        B39.setPreferredSize(new java.awt.Dimension(43, 43));
        B39.addChangeListener(this::B39StateChanged);
        B39.addActionListener(this::B39ActionPerformed);

        B40.setText("1");
        B40.setMaximumSize(new java.awt.Dimension(43, 43));
        B40.setMinimumSize(new java.awt.Dimension(43, 43));
        B40.setPreferredSize(new java.awt.Dimension(43, 43));
        B40.addChangeListener(this::B40StateChanged);
        B40.addActionListener(this::B40ActionPerformed);

        B41.setText("1");
        B41.setMaximumSize(new java.awt.Dimension(43, 43));
        B41.setMinimumSize(new java.awt.Dimension(43, 43));
        B41.setPreferredSize(new java.awt.Dimension(43, 43));
        B41.addChangeListener(this::B41StateChanged);
        B41.addActionListener(this::B41ActionPerformed);

        B42.setText("1");
        B42.setMaximumSize(new java.awt.Dimension(43, 43));
        B42.setMinimumSize(new java.awt.Dimension(43, 43));
        B42.setPreferredSize(new java.awt.Dimension(43, 43));
        B42.addChangeListener(this::B42StateChanged);
        B42.addActionListener(this::B42ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(B9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(B16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(B17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(B18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(B19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(B20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(B21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(B23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B27, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(B30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(B31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(B32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(B33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(B34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(B35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(B38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(B40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(Anno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(Anno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(B8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(B11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(B15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(B21, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(B22, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B23, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B24, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(B25, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B26, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B27, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B28, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(B29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B30, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B31, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(B32, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B33, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B34, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B35, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B38, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B39, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B40, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B41, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B42, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B37, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setText("OK");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Fecha: ");

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton4.setText("<-");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(460, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel1)
                    .addComponent(jButton4))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if ((this.mes -1) < 1){ //O ==0, es lo mismo
            refrescarJPanel(anno-1, 12);
        }
        else{
            refrescarJPanel(anno,mes-1);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if ((mes +1) > 12){ //O ==0, es lo mismo
            refrescarJPanel(anno+1, 1);
        }
        else{
            refrescarJPanel(anno,mes+1);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void B1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B1StateChanged

    private void B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B1ActionPerformed
        this.ultimoPulsado = Integer.parseInt(B1.getText());
        this.modificarEstadoResto(this.B1.getText());
    }//GEN-LAST:event_B1ActionPerformed

    private void B2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B2StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B2StateChanged

    private void B2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B2ActionPerformed
        this.ultimoPulsado = Integer.parseInt(B2.getText());
        this.modificarEstadoResto(this.B2.getText());
    }//GEN-LAST:event_B2ActionPerformed

    private void B3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B3StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B3StateChanged

    private void B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B3ActionPerformed
        this.ultimoPulsado = Integer.parseInt(B3.getText());
        this.modificarEstadoResto(this.B3.getText());
    }//GEN-LAST:event_B3ActionPerformed

    private void B4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B4StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B4StateChanged

    private void B4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B4ActionPerformed
        this.ultimoPulsado = Integer.parseInt(B4.getText());
        this.modificarEstadoResto(this.B4.getText());
    }//GEN-LAST:event_B4ActionPerformed

    private void B5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B5StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B5StateChanged

    private void B5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B5ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B5.getText());
        this.modificarEstadoResto(this.B5.getText());
    }//GEN-LAST:event_B5ActionPerformed

    private void B6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B6StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B6StateChanged

    private void B6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B6ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B6.getText());
        this.modificarEstadoResto(this.B6.getText());
    }//GEN-LAST:event_B6ActionPerformed

    private void B7StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B7StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B7StateChanged

    private void B7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B7ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B7.getText());
        this.modificarEstadoResto(this.B7.getText());
    }//GEN-LAST:event_B7ActionPerformed

    private void B14StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B14StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B14StateChanged

    private void B14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B14ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B14.getText());
        this.modificarEstadoResto(this.B14.getText());
    }//GEN-LAST:event_B14ActionPerformed

    private void B9StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B9StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B9StateChanged

    private void B9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B9ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B9.getText());
        this.modificarEstadoResto(this.B9.getText());
    }//GEN-LAST:event_B9ActionPerformed

    private void B10StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B10StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B10StateChanged

    private void B10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B10ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B10.getText());
        this.modificarEstadoResto(this.B10.getText());
    }//GEN-LAST:event_B10ActionPerformed

    private void B11StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B11StateChanged

    }//GEN-LAST:event_B11StateChanged

    private void B11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B11ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B11.getText());
        this.modificarEstadoResto(this.B11.getText());
    }//GEN-LAST:event_B11ActionPerformed

    private void B12StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B12StateChanged

    }//GEN-LAST:event_B12StateChanged

    private void B12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B12ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B12.getText());
        this.modificarEstadoResto(this.B12.getText());
    }//GEN-LAST:event_B12ActionPerformed

    private void B13StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B13StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B13StateChanged

    private void B13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B13ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B13.getText());
        this.modificarEstadoResto(this.B13.getText());
    }//GEN-LAST:event_B13ActionPerformed

    private void B8StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B8StateChanged

    }//GEN-LAST:event_B8StateChanged

    private void B8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B8ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B8.getText());
        this.modificarEstadoResto(this.B8.getText());
    }//GEN-LAST:event_B8ActionPerformed

    private void B21StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B21StateChanged

    }//GEN-LAST:event_B21StateChanged

    private void B21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B21ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B12.getText());
        this.modificarEstadoResto(this.B12.getText());
    }//GEN-LAST:event_B21ActionPerformed

    private void B16StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B16StateChanged

    }//GEN-LAST:event_B16StateChanged

    private void B16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B16ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B16.getText());
        this.modificarEstadoResto(this.B16.getText());
    }//GEN-LAST:event_B16ActionPerformed

    private void B17StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B17StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B17StateChanged

    private void B17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B17ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B17.getText());
    }//GEN-LAST:event_B17ActionPerformed

    private void B18StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B18StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B18StateChanged

    private void B18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B18ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B18.getText());
        this.modificarEstadoResto(this.B18.getText());
    }//GEN-LAST:event_B18ActionPerformed

    private void B19StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B19StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B19StateChanged

    private void B19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B19ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B19.getText());
        this.modificarEstadoResto(this.B19.getText());
    }//GEN-LAST:event_B19ActionPerformed

    private void B20StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B20StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B20StateChanged

    private void B20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B20ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B20.getText());
        this.modificarEstadoResto(this.B20.getText());
    }//GEN-LAST:event_B20ActionPerformed

    private void B15StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B15StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B15StateChanged

    private void B15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B15ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B15.getText());
        this.modificarEstadoResto(this.B15.getText());
    }//GEN-LAST:event_B15ActionPerformed

    private void B26StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B26StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B26StateChanged

    private void B26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B26ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B26.getText());
        this.modificarEstadoResto(this.B26.getText());
    }//GEN-LAST:event_B26ActionPerformed

    private void B27StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B27StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B27StateChanged

    private void B27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B27ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B27.getText());
        this.modificarEstadoResto(this.B27.getText());
    }//GEN-LAST:event_B27ActionPerformed

    private void B22StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B22StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B22StateChanged

    private void B22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B22ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B22.getText());
        this.modificarEstadoResto(this.B22.getText());
    }//GEN-LAST:event_B22ActionPerformed

    private void B28StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B28StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B28StateChanged

    private void B28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B28ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B28.getText());
        this.modificarEstadoResto(this.B28.getText());
    }//GEN-LAST:event_B28ActionPerformed

    private void B23StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B23StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B23StateChanged

    private void B23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B23ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B23.getText());
        this.modificarEstadoResto(this.B23.getText());
    }//GEN-LAST:event_B23ActionPerformed

    private void B24StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B24StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B24StateChanged

    private void B24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B24ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B24.getText());
        this.modificarEstadoResto(this.B24.getText());
    }//GEN-LAST:event_B24ActionPerformed

    private void B25StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B25StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B25StateChanged

    private void B25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B25ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B25.getText());
        this.modificarEstadoResto(this.B25.getText());
    }//GEN-LAST:event_B25ActionPerformed

    private void B33StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B33StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B33StateChanged

    private void B33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B33ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B33.getText());
        this.modificarEstadoResto(this.B33.getText());
    }//GEN-LAST:event_B33ActionPerformed

    private void B34StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B34StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B34StateChanged

    private void B34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B34ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B34.getText());
        this.modificarEstadoResto(this.B34.getText());
    }//GEN-LAST:event_B34ActionPerformed

    private void B29StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B29StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B29StateChanged

    private void B29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B29ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B29.getText());
        this.modificarEstadoResto(this.B29.getText());
    }//GEN-LAST:event_B29ActionPerformed

    private void B35StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B35StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B35StateChanged

    private void B35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B35ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B35.getText());
        this.modificarEstadoResto(this.B35.getText());
    }//GEN-LAST:event_B35ActionPerformed

    private void B30StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B30StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B30StateChanged

    private void B30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B30ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B30.getText());
        this.modificarEstadoResto(this.B30.getText());
    }//GEN-LAST:event_B30ActionPerformed

    private void B31StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B31StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B31StateChanged

    private void B31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B31ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B31.getText());
        this.modificarEstadoResto(this.B31.getText());
    }//GEN-LAST:event_B31ActionPerformed

    private void B32StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B32StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B32StateChanged

    private void B32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B32ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B32.getText());
        this.modificarEstadoResto(this.B32.getText());
    }//GEN-LAST:event_B32ActionPerformed

    private void B36StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B36StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B36StateChanged

    private void B36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B36ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B36.getText());
        this.modificarEstadoResto(this.B36.getText());
    }//GEN-LAST:event_B36ActionPerformed

    private void B37StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B37StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B37StateChanged

    private void B37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B37ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B37.getText());
        this.modificarEstadoResto(this.B37.getText());
    }//GEN-LAST:event_B37ActionPerformed

    private void B38StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B38StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B38StateChanged

    private void B38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B38ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B38.getText());
        this.modificarEstadoResto(this.B38.getText());
    }//GEN-LAST:event_B38ActionPerformed

    private void B39StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B39StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B39StateChanged

    private void B39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B39ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B39.getText());
        this.modificarEstadoResto(this.B39.getText());
    }//GEN-LAST:event_B39ActionPerformed

    private void B40StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B40StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B40StateChanged

    private void B40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B40ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B40.getText());
        this.modificarEstadoResto(this.B40.getText());
    }//GEN-LAST:event_B40ActionPerformed

    private void B41StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B41StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B41StateChanged

    private void B41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B41ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B41.getText());
        this.modificarEstadoResto(this.B41.getText());
    }//GEN-LAST:event_B41ActionPerformed

    private void B42StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_B42StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_B42StateChanged

    private void B42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B42ActionPerformed
        this.ultimoPulsado = Integer.parseInt(this.B42.getText());
        this.modificarEstadoResto(this.B42.getText());
    }//GEN-LAST:event_B42ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.areaRellenar.setText(this.getFechaSelecionada());
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Anno;
    private javax.swing.JToggleButton B1;
    private javax.swing.JToggleButton B10;
    private javax.swing.JToggleButton B11;
    private javax.swing.JToggleButton B12;
    private javax.swing.JToggleButton B13;
    private javax.swing.JToggleButton B14;
    private javax.swing.JToggleButton B15;
    private javax.swing.JToggleButton B16;
    private javax.swing.JToggleButton B17;
    private javax.swing.JToggleButton B18;
    private javax.swing.JToggleButton B19;
    private javax.swing.JToggleButton B2;
    private javax.swing.JToggleButton B20;
    private javax.swing.JToggleButton B21;
    private javax.swing.JToggleButton B22;
    private javax.swing.JToggleButton B23;
    private javax.swing.JToggleButton B24;
    private javax.swing.JToggleButton B25;
    private javax.swing.JToggleButton B26;
    private javax.swing.JToggleButton B27;
    private javax.swing.JToggleButton B28;
    private javax.swing.JToggleButton B29;
    private javax.swing.JToggleButton B3;
    private javax.swing.JToggleButton B30;
    private javax.swing.JToggleButton B31;
    private javax.swing.JToggleButton B32;
    private javax.swing.JToggleButton B33;
    private javax.swing.JToggleButton B34;
    private javax.swing.JToggleButton B35;
    private javax.swing.JToggleButton B36;
    private javax.swing.JToggleButton B37;
    private javax.swing.JToggleButton B38;
    private javax.swing.JToggleButton B39;
    private javax.swing.JToggleButton B4;
    private javax.swing.JToggleButton B40;
    private javax.swing.JToggleButton B41;
    private javax.swing.JToggleButton B42;
    private javax.swing.JToggleButton B5;
    private javax.swing.JToggleButton B6;
    private javax.swing.JToggleButton B7;
    private javax.swing.JToggleButton B8;
    private javax.swing.JToggleButton B9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
