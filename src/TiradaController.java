import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Dani on 17/06/2017.
 */
public class TiradaController {
    private byte[] rand;
    private Partida partida;


    public TiradaController(Partida partida) {
        this.rand = partida.getRand();
        this.partida = partida;
    }

    public Vector<String> nuevaTirada(int comb, boolean ayuda) {
        TiradaModel modelo = new TiradaModel(rand, 5, comb,partida.getId());
        Vector<String> tirada = new Vector<>();
        tirada.add(Arrays.toString(modelo.getJugada()));
        tirada.add(String.valueOf(modelo.getBien()));
        tirada.add(String.valueOf(modelo.getMal()));
        if (ayuda)
            tirada.add(Arrays.toString(modelo.getT_ayuda()));
        else
            tirada.add("");
        if (modelo.getBienMal()[0] == 5) partida.setAcabado(true);
        return tirada;
    }
}
