import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Dani on 17/06/2017.
 */
public class PartidaModel {
    private final byte max;
    private byte[] rand;
    private boolean acabado = false;
    private List<Tirada> l_tiradas = new ArrayList<>();
    //CONSTRUCTOR----------
    public PartidaModel(int max) {
        this.max = (byte) max; //guarda el max en un atributo
        rand = crearObjetivo(max, 0, 9);  //Crea una tabla de randoms de 0 a 9 de 5(max) números
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

    /*public void hacerTirada() {
        Tirada t = new Tirada(rand, max); //crea un objeto tirada al que se le pasan la tabla de randoms y el max de la tabla
        l_tiradas.add(t); //en la lista de tiradas, añade la tirada hecha
        if (t.getBien() == 5)
            acabado = true; //si obtiene la tabla de bien con un 5, es decir, ha acertado la combinación, se da por terminada la partida.
    }*/

    //GETTERS-----------
    public boolean getAcabado() {
        return acabado;
    } //devuelve si está acabado o no
    public byte[] getRand() {
        return rand;
    }
    //TOSTRNG-----------
    public String toString() {
        String imp = "Aleatori: " + Arrays.toString(rand) + "\n\nENTRADA\tBEN_POS\tMAL_POS\tTAULA\n"+l_tiradas;
        return imp;
    }

    public void add(Tirada tirada) {
        l_tiradas.add(tirada);
    }
}
