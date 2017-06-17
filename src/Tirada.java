import java.util.Arrays;
import java.util.Scanner;

public class Tirada {
	private byte[] jugada;
	private byte[] t_ayuda;
	private byte[] bienMal;

    public Tirada(byte[] jugada, byte[] t_ayuda, byte[] bienMal) {
        this.jugada = jugada;
        this.t_ayuda = t_ayuda;
        this.bienMal = bienMal;
    }

    @Override
    public String toString() {
        return "Tirada{" +
                "jugada=" + Arrays.toString(jugada) +
                ", t_ayuda=" + Arrays.toString(t_ayuda) +
                ", bienMal=" + Arrays.toString(bienMal) +
                '}';
    }
}
