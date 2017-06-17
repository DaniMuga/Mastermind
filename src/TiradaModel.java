import java.util.Scanner;

/**
 * Created by Dani on 17/06/2017.
 */
public class TiradaModel {
    private byte max;
    private byte[] jugada;
    private byte[] t_ayuda;
    private byte[] bienMal;
    private int num_tirada = 0;

    //CONSTRUCTOR
    public TiradaModel(byte[] rand, int max) {
        this.max = (byte) max; //guarda max en un atributo
        jugada = crearTirada(); //hace una tirada y la guarda en una tabla
        t_ayuda = comprobarTabla(jugada, rand); //comprueba los que están bien o mal puestos (0/1/2)
        bienMal = contarBienMal(t_ayuda); //cuenta los que están bien y mal
        num_tirada++; //contador de tiradas
    }

    //METODOS----------
    public byte[] crearTirada() {
        byte tirada[] = new byte[max];
//		int temp=Integer.parseInt(System.console().readLine());
        int temp = (new Scanner(System.in)).nextInt();
        for (int i = max - 1; i >= 0; i--) { //recorrido inverso en el que añade los números
            tirada[i] = (byte) (temp % 10);
            temp = temp / 10;
        }
        return tirada;
    }

    //Añadir 0/1/2 en tabla de ayuda----
    public byte[] comprobarTabla(byte[] jugada, byte[] rand) {
        byte ayuda[] = new byte[max];
        for (int i = 0; i < max; i++) { //primer recorrido que comprueba los que están bien posicionados.
            if (rand[i] == jugada[i]) {
                ayuda[i] = 1;
            }
        }
        for (int i = 0; i < max; i++) { //segundo recorrido que comprueba los que están pero no bien posicionados
            if (ayuda[i] == 1) continue; //si encuentra uno que ya está bien posicionado, pasa al siguiente
            for (int j = 0; j < max; j++) {
                if (ayuda[j] == 2 | ayuda[j] == 1) continue; //si ese número ya ha sido encontrado, pasa al siguiente
                if (rand[i] == jugada[j]) {
                    ayuda[j] = 2;
                    break;
                }
            }
        }
        return ayuda;
    }

    //Contar Bien y Mal----
    public byte[] contarBienMal(byte[] t_ayuda) {
        byte bienMal[] = new byte[2];
        for (int i = 0; i < t_ayuda.length; i++) { //hace un recorrido de la tabla de ayuda y cuenta los que están bien o mal
            if (t_ayuda[i] == 1) bienMal[0]++;
            if (t_ayuda[i] == 2) bienMal[1]++;
        }
        return bienMal;
    }

    //GETTERS----------------
    public int getBien() {
        return bienMal[0];
    } //devuelve el número de correctos

    public byte getMax() {
        return max;
    }

    public byte[] getJugada() {
        return jugada;
    }

    public byte[] getT_ayuda() {
        return t_ayuda;
    }

    public byte[] getBienMal() {
        return bienMal;
    }

    public int getNum_tirada() {
        return num_tirada;
    }
    //TOSTRING-------------

    public String toString() {
        return (java.util.Arrays.toString(jugada) + "\n" + java.util.Arrays.toString(t_ayuda) + "\n"
                + "Bien colocados: " + bienMal[0] + "\nAparecen pero no bien colocados: " + bienMal[1] + "\nTirada número: " + num_tirada);
    }

}
