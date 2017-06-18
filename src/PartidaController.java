import java.util.Date;

/**
 * Created by Dani on 17/06/2017.
 */
public class PartidaController {
    PartidaModel partidaModel;

    public PartidaController(PartidaModel partidaModel) {
        this.partidaModel = partidaModel;
    }

    //
    public PartidaModel nueva() {
        PartidaModel modelop=new PartidaModel(-1,new Date());
        this.partidaModel =modelop;
        modelop.setId(save());
        return modelop;
    }
    public int save() {
        return new DataBase().insertarPartida(partidaModel);
    }

}
