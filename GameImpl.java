
public class GameImpl implements Game {
    private String jogador1;
    private String jogador2;
    public Card[] cartas;
    //array de card cartas

     /**
     * Construtor sem parâmetros
     */
    public GameImpl(){

    }

     /**
     * Construtor que define o nome dos jogadores de Peças Vermelhas e Azuis, respectivamente
     * @param jogador1 Nome do jogador 1, peças vermelhas
     * @param jogador2 Nome do jogador 2, peças azuis
     */
    public GameImpl(String jogador1, String jogador2){
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
    }

    /**
     * Construtor que define o nome dos jogadores de Peças Vermelhas e Azuis, respectivamente
     * @param jogador1 Nome do jogador 1, peças vermelhas
     * @param jogador2 Nome do jogador 2, peças azuis
     * @param cartas Array de Cards
     */
    public GameImpl(String jogador1, String jogador2, Card[] cartas){
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.cartas = cartas;
    }
}
