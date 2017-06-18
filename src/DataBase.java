import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class DataBase {
    private String url = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String pwd = "9462";
    private Connection con = null;
    private Statement st = null;

    public DataBase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pwd);
            st = con.createStatement();
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS BDMasterMind");
            st.executeUpdate("USE BDMasterMind");
            crearTablaPartidas();
            crearTablaTiradas();

        } catch (SQLException e) {
            mostraSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void mostraSQLException(SQLException ex) {
        ex.printStackTrace(System.err);
        System.err.println("SQLState: " + ex.getSQLState());
        System.err.println("Error Code: " + ex.getErrorCode());
        System.err.println("Message: " + ex.getMessage());
        Throwable t = ex.getCause();
        while (t != null) {
            System.out.println("Cause: ");
            t = t.getCause();
        }
    }

    public void close() {
        try {
            if (st != null) st.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            mostraSQLException(e);
        }
    }

    public void crearTablaPartidas() {
        try {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Partidas (\n" +
                    "  id         INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                    "  rand       VARCHAR(10)," +
                    "  fecha      DATETIME," +
                    "  finalizada BOOLEAN" +
                    ")");
        } catch (SQLException e) {
            mostraSQLException(e);
        }
    }

    public void crearTablaTiradas() {
        try {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Tiradas (" +
                    "  id         INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                    "  id_partida INT REFERENCES Partida (id)," +
                    "  jugada     VARCHAR(10)," +
                    "  bien       INT," +
                    "  mal        INT," +
                    "  ayuda      VARCHAR(10)" +
                    ")");
        } catch (SQLException e) {
            mostraSQLException(e);
        }
    }

    public Vector<Vector<String>> cargarPartidas() {
        Vector<Vector<String>> out = new Vector<>();
        try {
            ResultSet rs = st.executeQuery("SELECT id,fecha,finalizada FROM Partidas");
            while (rs.next())
                out.add(new Vector<>(Arrays.asList(rs.getString(1), rs.getString(2), String.valueOf(rs.getBoolean(3)))));
        } catch (SQLException e) {
            mostraSQLException(e);
        }
        return out;
    }

    public int insertarPartida(PartidaModel p) {
        int id = -1;
        try {
            st.executeUpdate("insert into Partidas values(null,'" + arrayToString(p.getRand()) + "','" + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(p.getFecha()) + "'," + p.getAcabado() + " )", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) id = rs.getInt(1);
        } catch (SQLException e) {
            mostraSQLException(e);
        }
        return id;
    }

    public void insertarTirada(TiradaModel t) {
        try {

            st.executeUpdate("insert into Tiradas values(null," + t.getPartidaId() + ",'" + arrayToString(t.getJugada()) + "'," + t.getBien() + "," + t.getMal() + ",'" + arrayToString(t.getT_ayuda()) + "' )");

        } catch (SQLException e) {
            mostraSQLException(e);
        }
    }

    public void eliminarPartida(int p) {
        try {
            st.executeUpdate("DELETE FROM Partidas WHERE id=" + p);
            st.executeUpdate("DELETE FROM Tiradas WHERE id_partida=" + p);
        } catch (SQLException e) {
            mostraSQLException(e);
        }
    }

    public void marcarAcabado(PartidaModel p) {
        try {

            st.executeUpdate("UPDATE Partidas SET finalizada=TRUE WHERE id=" + p.getId());

        } catch (SQLException e) {
            mostraSQLException(e);
        }
    }

    public PartidaModel cargarPartida(int id, boolean ayuda) {
        int id_p = -1;
        String rand_p = "";
        boolean finalizada_p = false;
        ArrayList<TiradaModel> tiradas = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM Partidas WHERE id=" + id);
            if (rs.next()) {
                id_p = rs.getInt("id");
                rand_p = rs.getString("rand");
                finalizada_p = rs.getBoolean("finalizada");
            }
            ResultSet rs_tiradas = st.executeQuery("SELECT * FROM Tiradas WHERE id_partida=" + id);
            while (rs_tiradas.next()) {
                tiradas.add(
                        new TiradaModel(
                                stringToArray(rs_tiradas.getString("jugada")),
                                stringToArray(rs_tiradas.getString("ayuda")),
                                new byte[]{rs_tiradas.getByte("bien"), rs_tiradas.getByte("mal")}
                        ));
            }
        } catch (SQLException e) {
            mostraSQLException(e);
        }
        return new PartidaModel(id_p, stringToArray(rand_p), finalizada_p,tiradas,ayuda);
    }

    private String arrayToString(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b :
                array) {
            sb.append(b);
        }
        return sb.toString();
    }

    private byte[] stringToArray(String str) {
        byte[] b = new byte[str.length()];
        String[] split = str.split("");
        for (int i = 0; i < str.length(); i++) {
            b[i] = Byte.parseByte(split[i]);
        }
        return b;
    }
}
