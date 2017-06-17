import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
public class Inicio_v{
	private JFrame frame=new JFrame("MasterMind");
	private JPanel mainPanel=new JPanel();
	private CardLayout cardLayout=new CardLayout();
	private JPanel panel1=new JPanel(); //Menú principal
	private JPanel panel2=new JPanel(); private JPanel todo2=new JPanel();//Ingame 
	private JPanel panel3=new JPanel(); private JPanel todo3=new JPanel();// Cargar Partidas
	//-Panel1
	private JButton b_iniciar=new JButton("Nueva Partida");
	private JButton b_cargar=new JButton("Cargar Partida");
	private JButton b_salir=new JButton("Salir");
	
	//-Panel2
	private JTextField result =new JTextField("");
	private String[] items ={"1","2","3","4","5","6","7","8","9"};
	private	JComboBox<String> comboBox1 = new JComboBox<>( items );
	private	JComboBox<String> comboBox2 = new JComboBox<>( items );
	private	JComboBox<String> comboBox3 = new JComboBox<>( items );
	private	JComboBox<String> comboBox4 = new JComboBox<>( items );
	private	JComboBox<String> comboBox5 = new JComboBox<>( items );
	private JButton enviar_comb=new JButton ("Enviar");

	//-Panel3
	private JButton b_cargar2=new JButton ("Cargar partida");
	private JButton b_eliminar=new JButton ("Eliminar partida");
	private JButton b_volverMP=new JButton ("Volver al menú pricipal");
	
	//MENÚ
	private JMenuBar bar2=new JMenuBar();
	private JMenu menu2=new JMenu("Menú");
	private JMenu m_juego=new JMenu("Juego");
	private JMenuItem volvermenu=new JMenuItem("Volver al menú principal");
	private	JMenuItem salirmenu=new JMenuItem("Salir del juego");
	private JCheckBoxMenuItem mb_ayuda=new JCheckBoxMenuItem("Ayuda");
	private JMenuItem mb_guardarp= new JMenuItem("Guardar Partida");

	//PARTIDA
	private static PartidaModel partida;
	
	public Inicio_v(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(980,660);
		mainPanel.setLayout(cardLayout);
		panel1.setLayout(new GridBagLayout()); panel1.setBackground(Color.WHITE);
		todo2.setLayout(new BorderLayout());
		todo2.add(bar2,BorderLayout.NORTH);
		todo2.add(panel2,BorderLayout.CENTER);
		panel2.setLayout(new GridBagLayout());
		todo3.setLayout(new BorderLayout());
		todo3.add(panel3,BorderLayout.CENTER);
		panel3.setLayout(new GridBagLayout()); panel3.setBackground(Color.PINK);
		
		mainPanel.add(panel1,"panel1");
		mainPanel.add(todo2,"panel2");
		mainPanel.add(todo3,"panel3");

		frame.setContentPane(mainPanel);
		cardLayout.show(mainPanel,"panel1");

		
		//--------------------------------------------------PANEL 1----------------------------------------------	
		JLabel titulo=new JLabel("MASTERMIND");
		titulo.setFont(new Font("",Font.PLAIN,20));
		
		
		
		//--ADD
		panel1.add(titulo,new GridBagConstraints(1,0,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,5,0),0,0));
		panel1.add(b_iniciar,new GridBagConstraints(1,1,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(50,0,10,0),0,0));
		panel1.add(b_cargar,new GridBagConstraints(1,2,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,10,0),0,0));
		panel1.add(b_salir,new GridBagConstraints(1,3,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,10,0),0,0));
		
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
			public void actionPerformed (ActionEvent e){
				cardLayout.show(mainPanel,"panel1");
			}
		});
		salirmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//fin MENU
		
		comboBox1.setPreferredSize(new Dimension(55, 40));
		comboBox1.setMaximumRowCount(9);
		comboBox2.setPreferredSize(new Dimension(55, 40));
		comboBox2.setMaximumRowCount(9);
		comboBox3.setPreferredSize(new Dimension(55, 40));
		comboBox3.setMaximumRowCount(9);
		comboBox4.setPreferredSize(new Dimension(55, 40));
		comboBox4.setMaximumRowCount(9);
		comboBox5.setPreferredSize(new Dimension(55, 40));
		comboBox5.setMaximumRowCount(9);
		//Panel ComboBoxes
		JPanel panelCombo=new JPanel();
		panelCombo.add(comboBox1); panelCombo.add(comboBox2); panelCombo.add(comboBox3); panelCombo.add(comboBox4); panelCombo.add(comboBox5);
		panelCombo.setBorder(new TitledBorder(new EtchedBorder(), "Combinación" ) );
		panel2.add(panelCombo,new GridBagConstraints(1,1,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,10,0),0,0));
		result.setEditable(false);
		result.setPreferredSize(new Dimension(500,400));

		//ENVIAR TIRADA
		enviar_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int combo;
				String combinacion="";
				combinacion=combinacion+comboBox1.getSelectedItem();
				combinacion=combinacion+comboBox2.getSelectedItem();
				combinacion=combinacion+comboBox3.getSelectedItem();
				combinacion=combinacion+comboBox4.getSelectedItem();
				combinacion=combinacion+comboBox5.getSelectedItem();
				combo=Integer.parseInt(combinacion);
				result.setText(partida+"\r\n"+(new TiradaController(partida)).nuevaTirada(combo,mb_ayuda.getState()));
                System.out.println(partida);
            }
		});
		
		panel2.add(result,new GridBagConstraints(1,0,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,10,0),0,0));
		panel2.add(enviar_comb,new GridBagConstraints(1,2,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,10,0),0,0));
		
		
		
		//--------------------------------------------------PANEL 3----------------------------------------------
		//headers for the table
        String[] columns = new String[] {
            "Id", "Name", "Hourly Rate", "Acabado"
        };
         
        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
            {1, "John", 40.0, false },
            {2, "Rambo", 70.0, false },
            {3, "Zorro", 60.0, true },
        };
        //create table with data
        JTable table = new JTable(data, columns);

        JScrollPane sc= new JScrollPane(table);
        JPanel botones_p3=new JPanel();
        botones_p3.add(b_cargar2);
        botones_p3.add(b_eliminar);
		
		b_volverMP.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e){
				cardLayout.show(mainPanel,"panel1");
			}
		});
		
		panel3.add(sc,new GridBagConstraints(1,0,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
		panel3.add(botones_p3,new GridBagConstraints(1,1,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
		panel3.add(b_volverMP,new GridBagConstraints(1,2,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
	
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setVisible(true);
	}

	private class cardListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JButton src=(JButton) e.getSource();
			
			if(src.equals(b_iniciar)){
				PartidaController controlador=new PartidaController();
				partida=controlador.nueva();
				cardLayout.show(mainPanel,"panel2");

			}
			if(src.equals(b_cargar)){
				cardLayout.show(mainPanel,"panel3");
			}
			if(src.equals(b_salir)){
				System.exit(0);
			}
		}
	}
	     

	public static void main (String args[]){
		new Inicio_v();
	}
}

