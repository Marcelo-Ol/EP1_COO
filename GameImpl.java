
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

    /**
     * Método que devolve a cor da posição do tabuleiro. Se possui uma cor, significa que é um templo. Caso contrário, é um espaço normal
     * @param position Posição do tabuleiro
     * @return O enum Color que representa a cor da posição
     * @Override 
     */
    public Color getSpotColor(Position position){
        Spot spot = new Spot(position);
        return spot.getColor();
    }

    /**
     * Método que devolve a peça que está na posição do tabuleiro
     * @param position Posição do tabuleiro
     * @return Um objeto Piece que representa a peça na posição indicada. Se não tiver peça, devolve null
     * @Override
     */
    public Piece getPiece(Position position){
        Spot spot = new Spot(position);
        return spot.getPiece();
    }

     /**
     * Método que devolve a carta que está na mesa, que será substituída após a próxima jogada
     * @return Um objeto Card que representa a carta na mesa
     * @Override
     */
    public Card getTableCard(){
       
    }

     /**
     * Método que devolve as informações sobre o jogador com as peças vermelhas
     * @return Um objeto Player que representa o jogador vermelho
     * @Override
     */
    public Player getRedPlayer(){
        return new Player("Marcelo", Color.RED, cartas);
    }

    /**
     * Método que devolve as informações sobre o jogador com as peças azuis
     * @return Um objeto Player que representa o jogador azul
     * @Override
     */
    public Player getBluePlayer(){
         return new Player("Golhers", Color.BLUE, cartas);
    }

    /**
     * Método que move uma peça
     * @param card A carta de movimento que será usada
     * @param cardMove A posição da carta para onde a peça irá se mover
     * @param currentPos A posição da peça que irá se mover
     * @exception IncorrectTurnOrderException Caso não seja a vez de um jogador fazer um movimento
     * @exception IllegalMovementException Caso uma peça seja movida para fora do tabuleiro ou para uma posição onde já tem uma peça da mesma cor
     * @exception InvalidCardException Caso uma carta que não está na mão do jogador seja usada
     * @exception InvalidPieceException Caso uma peça que não está no tabuleiro seja usada
     * @Override
     */
    public void makeMove(Card card, Position cardMove, Position currentPos) throws IncorrectTurnOrderException, IllegalMovementException, InvalidCardException, InvalidPieceException{

    }
    
    /**
     * Método que confere se um jogador de uma determinada cor venceu o jogo. Critérios de vitória:
     * — Derrotou a peça de mestre adversária
     * — Posicionou o seu mestre na posição da base adversária
     * @param color Cor das peças do jogador que confere a condição de vitória
     * @return Um booleano true para caso esteja em condições de vencer e false caso contrário
     * @Override
     */
    public boolean checkVictory(Color color){
    // temporário
    if(mestre tomado || mestre ocupou templo adversário){
        return true;
    }
    return false;
    }

    /**
     * Método que imprime o tabuleiro no seu estado atual
     * OBS: Esse método é opcional não será utilizado na correção, mas serve para acompanhar os resultados parciais do jogo
     * @Override
     */
    public void printBoard(){

    }
}
