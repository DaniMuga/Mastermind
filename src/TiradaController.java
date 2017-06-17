/**
 * Created by Dani on 17/06/2017.
 */
public class TiradaController {
    private byte[] rand;
    private PartidaModel partida;

    public TiradaController(PartidaModel partida) {
        this.rand = partida.getRand();
        this.partida = partida;
    }

    public String nuevaTirada(int comb, boolean ayuda) {
        TiradaModel modelo = new TiradaModel(rand, 5);
        //partida.add(modelo.getTirada());
        return modelo.toString();
    }
}
