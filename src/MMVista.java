import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

public class MMVista {
    //PARTIDA
    private static PartidaModel partidaModel;
    private JFrame frame = new JFrame("MasterMind");
    private JPanel mainPanel = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private JPanel panel1 = new JPanel(); //Menú principal
    private JPanel panel2 = new JPanel();
    private JPanel todo2 = new JPanel();//Ingame
    private JPanel panel3 = new JPanel();
    private JPanel todo3 = new JPanel();// Cargar Partidas
    //-Panel1
    private JButton b_iniciar = new JButton("Nueva Partida");
    private JButton b_cargar = new JButton("Cargar Partida");
    private JButton b_salir = new JButton("Salir");
    //-Panel2
    private JTextField result = new JTextField("");
    private String[] items = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private JComboBox<String> comboBox1 = new JComboBox<>(items);
    private JComboBox<String> comboBox2 = new JComboBox<>(items);
    private JComboBox<String> comboBox3 = new JComboBox<>(items);
    private JComboBox<String> comboBox4 = new JComboBox<>(items);
    private JComboBox<String> comboBox5 = new JComboBox<>(items);
    private JButton enviar_comb = new JButton("Enviar");
    //-Panel3
    private JButton b_cargar2 = new JButton("Cargar partida");
    private JButton b_eliminar = new JButton("Eliminar partida");
    private JButton b_volverMP = new JButton("Volver al menú pricipal");
    //MENÚ
    private JMenuBar bar2 = new JMenuBar();
    private JMenu menu2 = new JMenu("Menú");
    private JMenu m_juego = new JMenu("Juego");
    private JMenuItem volvermenu = new JMenuItem("Volver al menú principal");
    private JMenuItem salirmenu = new JMenuItem("Salir del juego");
    private JCheckBoxMenuItem mb_ayuda = new JCheckBoxMenuItem("Ayuda");
    private JMenuItem mb_guardarp = new JMenuItem("Guardar Partida");

    private Vector<String> cabesa = new Vector<>();

    private JTable tabla_tiradas;
    private JTable tabla_partidas;

    public MMVista() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(980, 660);
        mainPanel.setLayout(cardLayout);
        panel1.setLayout(new GridBagLayout());
        panel1.setBackground(Color.WHITE);
        todo2.setLayout(new BorderLayout());
        todo2.add(bar2, BorderLayout.NORTH);
        todo2.add(panel2, BorderLayout.CENTER);
        panel2.setLayout(new GridBagLayout());
        todo3.setLayout(new BorderLayout());
        todo3.add(panel3, BorderLayout.CENTER);
        panel3.setLayout(new GridBagLayout());
        panel3.setBackground(Color.PINK);

        mainPanel.add(panel1, "panel1");
        mainPanel.add(todo2, "panel2");
        mainPanel.add(todo3, "panel3");

        frame.setContentPane(mainPanel);
        cardLayout.show(mainPanel, "panel1");


        //--------------------------------------------------PANEL 1----------------------------------------------
        JLabel titulo = new JLabel("MASTERMIND");
        titulo.setFont(new Font("", Font.PLAIN, 20));


        //--ADD
        panel1.add(titulo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
        panel1.add(b_iniciar, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(50, 0, 10, 0), 0, 0));
        panel1.add(b_cargar, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));
        panel1.add(b_salir, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));

        //EVENTOS
        b_iniciar.addActionListener(new cardListener());
        b_cargar.addActionListener(new cardListener());
        b_salir.addActionListener(new cardListener());
        //--------------------------------------------------PANEL 2----------------------------------------------
        //MENU
        menu2.add(volvermenu);
        menu2.addSeparator();
        menu2.add(salirmenu);
        m_juego.add(mb_guardarp);
        m_juego.addSeparator();
        m_juego.add(mb_ayuda);
        bar2.add(menu2);
        bar2.add(m_juego);

        volvermenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((DefaultTableModel) tabla_tiradas.getModel()).getDataVector().removeAllElements();
                enviar_comb.setEnabled(true);
                cardLayout.show(mainPanel, "panel1");
            }
        });
        salirmenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //fin MENU

        comboBox1.setPreferredSize(new Dimension(55, 40));
        comboBox1.setMaximumRowCount(10);
        comboBox2.setPreferredSize(new Dimension(55, 40));
        comboBox2.setMaximumRowCount(10);
        comboBox3.setPreferredSize(new Dimension(55, 40));
        comboBox3.setMaximumRowCount(10);
        comboBox4.setPreferredSize(new Dimension(55, 40));
        comboBox4.setMaximumRowCount(10);
        comboBox5.setPreferredSize(new Dimension(55, 40));
        comboBox5.setMaximumRowCount(10);
        //Panel ComboBoxes
        JPanel panelCombo = new JPanel();
        panelCombo.add(comboBox1);
        panelCombo.add(comboBox2);
        panelCombo.add(comboBox3);
        panelCombo.add(comboBox4);
        panelCombo.add(comboBox5);
        panelCombo.setBorder(new TitledBorder(new EtchedBorder(), "Combinación"));
        panel2.add(panelCombo, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));
        result.setEditable(false);
        result.setPreferredSize(new Dimension(500, 400));

        //TABLA
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        cabesa.add("Tirada");
        cabesa.add("Bien");
        cabesa.add("Mal");
        cabesa.add("Ayuda");
        Vector<Vector<String>> contenido = new Vector<>();
        tabla_tiradas = new JTable(new DefaultTableModel(contenido, cabesa) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tabla_tiradas.getTableHeader().setReorderingAllowed(false);
        tabla_tiradas.getTableHeader().setResizingAllowed(false);
        tabla_tiradas.getColumnModel().getColumn(0).setPreferredWidth(250);
        tabla_tiradas.getColumnModel().getColumn(3).setPreferredWidth(250);
        JScrollPane tabla_t = new JScrollPane(tabla_tiradas);
        tabla_t.setBackground(Color.WHITE);
        //ENVIAR TIRADA
        enviar_comb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int combo;
                String combinacion = "";
                combinacion = combinacion + comboBox1.getSelectedItem();
                combinacion = combinacion + comboBox2.getSelectedItem();
                combinacion = combinacion + comboBox3.getSelectedItem();
                combinacion = combinacion + comboBox4.getSelectedItem();
                combinacion = combinacion + comboBox5.getSelectedItem();
                combo = Integer.parseInt(combinacion);
                Vector<String> tirada = new TiradaController(partidaModel).nuevaTirada(combo, mb_ayuda.getState());
                ((DefaultTableModel) tabla_tiradas.getModel()).addRow(tirada);
                if (partidaModel.getAcabado()) {
                    enviar_comb.setEnabled(false);
                }
            }
        });

        panel2.add(tabla_t, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));
        panel2.add(enviar_comb, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));


        //--------------------------------------------------PANEL 3----------------------------------------------
        Vector<String> columns = new Vector<>();
        columns.add("Id");
        columns.add("Fecha");
        columns.add("Finalizada");

        Vector<Vector<String>> partidas_cargadas = new DataBase().cargarPartidas();
        tabla_partidas = new JTable(new DefaultTableModel(partidas_cargadas, columns) {
            @Override
            public int getRowCount() {
                return partidas_cargadas.size();
            }

            @Override
            public int getColumnCount() {
                return columns.size();
            }

            @Override
            public String getColumnName(int columnIndex) {
                return columns.get(columnIndex);
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return partidas_cargadas.get(rowIndex).get(columnIndex);
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

            }

            @Override
            public void addTableModelListener(TableModelListener l) {

            }

            @Override
            public void removeTableModelListener(TableModelListener l) {

            }
        });
        tabla_partidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla_partidas.getTableHeader().setReorderingAllowed(false);
        tabla_partidas.getTableHeader().setResizingAllowed(false);
        tabla_partidas.setDefaultRenderer(String.class, centerRenderer);
        JScrollPane sc = new JScrollPane(tabla_partidas);
        JPanel botones_p3 = new JPanel();
        botones_p3.add(b_cargar2);
        botones_p3.add(b_eliminar);
        b_eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataBase().eliminarPartida(Integer.parseInt((String) tabla_partidas.getModel().getValueAt(tabla_partidas.getSelectedRow(), 0)));
                ((DefaultTableModel) tabla_partidas.getModel()).removeRow(tabla_partidas.getSelectedRow());
                ((DefaultTableModel) tabla_partidas.getModel()).fireTableDataChanged();
                tabla_partidas.addNotify();
            }
        });
        b_cargar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partidaModel = new DataBase().cargarPartida(Integer.parseInt((String) tabla_partidas.getModel().getValueAt(tabla_partidas.getSelectedRow(), 0)),mb_ayuda.getState());
                ((DefaultTableModel)tabla_tiradas.getModel()).setDataVector(partidaModel.listaTiradasToVector(),cabesa);
                tabla_tiradas.addNotify();
                if(partidaModel.getAcabado()){
                    enviar_comb.setEnabled(false);
                }
                cardLayout.show(mainPanel, "panel2");
            }
        });
        b_volverMP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "panel1");
            }
        });

        panel3.add(sc, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel3.add(botones_p3, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel3.add(b_volverMP, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    private class cardListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();

            if (src.equals(b_iniciar)) {
                partidaModel = new PartidaController(null).nueva();
                cardLayout.show(mainPanel, "panel2");
            }
            if (src.equals(b_cargar)) {
                if (partidaModel != null) {
                    if (partidaModel.getFecha()!=null)
                    ((DefaultTableModel) tabla_partidas.getModel()).addRow(new Vector<String>(Arrays.asList("" + partidaModel.getId(), new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(partidaModel.getFecha()), String.valueOf(partidaModel.getAcabado()))));
                    tabla_partidas.addNotify();
                }
                cardLayout.show(mainPanel, "panel3");
            }
            if (src.equals(b_salir)) {
                System.exit(0);
            }
        }
    }
}

