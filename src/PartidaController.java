import java.util.Date;

/**
 * Created by Dani on 17/06/2017.
 */
public class PartidaController {
    Partida partida;

    public PartidaController(Partida partida) {
        this.partida = partida;
    }

    //
    public Partida nueva() {
        Partida modelop=new Partida(-1,new Date());
        this.partida=modelop;
        modelop.setId(save());
        return modelop;
    }
    public int save() {
        return new DataBase().insertarPartida(partida);
    }
}
