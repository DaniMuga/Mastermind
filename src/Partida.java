import java.util.Date;
import java.util.Random;

public class Partida {
    private final byte max=5;
    private byte[] rand;
    private boolean acabado = false;
    private int id;
    private Date fecha;

    //CONSTRUCTOR----------
    public Partida( int id, Date fecha) {

        rand = crearObjetivo(max, 0, 9);  //Crea una tabla de randoms de 0 a 9 de 5(max) números
        this.id = id;
        this.fecha=fecha;
    }
    public Partida  (int id, byte[] rand,boolean acabado ){
        this.id=id;
        this.rand=rand;
        this.acabado=acabado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //MÉTODOS--------------
    public byte[] crearObjetivo(int l_taula, int inici, int fi) { //Crea la tabla de randoms
        byte taula[] = new byte[l_taula];  //Crea la tabla vacía
        Random rand = new Random();
        fi = fi - inici + 1;
        for (int i = 0; i < taula.length; i++) { //recorrido en el que va añadiendo los números
            byte valor = (byte) (rand.nextInt(fi));
            valor = (byte) (valor + inici);
            taula[i] = valor;
        }
        return taula;
    }

    /*public void hacerTirada(){
        Tirada t = new Tirada(rand,max); //crea un objeto tirada al que se le pasan la tabla de randoms y el max de la tabla
        l_tiradas.add(t); //en la lista de tiradas, añade la tirada hecha
        if (t.getBien()==5) acabado=true; //si obtiene la tabla de bien con un 5, es decir, ha acertado la combinación, se da por terminada la partida.
    }*/
    //GETTERS-----------
    public boolean getAcabado() {
        return acabado;
    } //devuelve si está acabado o no

    public void setAcabado(boolean acabado) {
        this.acabado = acabado;
    }

    public byte[] getRand() {
        return rand;
    }

    public Date getFecha() {
        return fecha;
    }
}
