import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Dani on 17/06/2017.
 */
public class PartidaModel {

    private byte[] rand;
    private boolean acabado = false;
    private List<Tirada> l_tiradas = new ArrayList<>();
    //CONSTRUCTOR----------


    //MÉTODOS--------------


    //GETTERS-----------
    public boolean getAcabado() {
        return acabado;
    } //devuelve si está acabado o no

    //TOSTRNG-----------
    public String toString() {
        String imp = "Aleatori: " + Arrays.toString(rand) + "\n\nENTRADA\tBEN_POS\tMAL_POS\tTAULA\n"+l_tiradas;
        return imp;
    }


}
