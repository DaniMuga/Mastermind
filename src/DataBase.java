import java.sql.*;
import java.util.*;
class DataBase{
	private String url="jdbc:mysql://localhost:3306/";
	private String user="root";
	private	String pwd="";
	private Connection con=null;
	private	Statement st=null;
	public DataBase(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pwd);
			st=con.createStatement();
			st.executeUpdate("create database if not exists BDMasterMind");
			st.executeUpdate("use BDMasterMind");

			
		}catch(SQLException e){
			mostraSQLException(e);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	public void mostraSQLException(SQLException ex){
		ex.printStackTrace(System.err);
		System.err.println("SQLState: "+ex.getSQLState());
		System.err.println("Error Code: "+ex.getErrorCode());
		System.err.println("Message: "+ex.getMessage());
		Throwable t=ex.getCause();
		while(t!=null){
			System.out.println("Cause: ");
			t=t.getCause();
		}
	}

	public void close(){
		try{
			if(st!=null)st.close();
			if(con!=null)con.close();
		}catch(SQLException e){
			mostraSQLException(e);
		}
	}
	public void crearTablaPartidas(){
		try{
			st.executeUpdate("CREATE TABLE IF NOT EXISTS Partidas (\n" +
									"  id         INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
									"  rand       VARCHAR(10)," +
									"  fecha      DATETIME," +
									"  finalizada BOOLEAN" +
									")");
		}catch(SQLException e){
			mostraSQLException(e);
		}
	}
	public void crearTablaTiradas(){
		try{
			st.executeUpdate("CREATE TABLE IF NOT EXISTS Tiradas (" +
									"  id         INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
									"  id_partida INT REFERENCES Partidas (id)," +
									"  jugada     VARCHAR(10)," +
									"  bien       INT," +
									"  mal        INT," +
									"  ayuda      VARCHAR(10),\n" +
									"  FOREIGN KEY (id_partida) REFERENCES Partidas (id)" +
									")");
		}catch(SQLException e){
			mostraSQLException(e);
		}
	}
	public void insertarPartida(Partida p){
		try{
			String random="";
			for(byte b:p.getRand()){
				random+=b;
			}
			st.executeUpdate("insert into Paridas values(null,'"+random+"',now(),"+p.getAcabado()+" )");
			
		}catch(SQLException e){
			mostraSQLException(e);
		}
	}
	public void insertarTirada(TiradaModel t){
		try{

			st.executeUpdate("insert into Tiradas values(null,,'"+t.getJugada()+"',"+t.getBien()+","+t.getMal()+","+t.getT_ayuda()+" )");

		}catch(SQLException e){
			mostraSQLException(e);
		}
	}
}
