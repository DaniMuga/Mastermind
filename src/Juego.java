public class Juego {
    public static void main(String[] args) {
        Partida p =new Partida(5); //crea una partida con 5 num aleatorios
        while(!p.getAcabado()){ //mientras no ha ganado, siguie haciendo tiradas
            p.hacerTirada();
            System.out.println(p.toString());
        }
    }
}
