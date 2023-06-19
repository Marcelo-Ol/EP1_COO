
import java.util.ArrayList;
import java.util.List;

public class GameImpl implements Game {

    private String jogador1;
    private String jogador2;
    public Card[] cartas;
    private List<Piece> pecas = new ArrayList<Piece>();
    private static List<Spot> locais = new ArrayList<Spot>();
    int contador = 0; //contador usado no isPlayerTurn()
    Card[] todasCartas = Card.createCards(); 
  
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
        return todasCartas[0]; //assumindo que a primeira carta do deck vai pra mesa
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
         return new Player("Guilherme", Color.BLUE, cartas);
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
        Color color; //
        if (!isPlayerTurn(color)) { 
            throw new IncorrectTurnOrderException("Não é a vez do jogador fazer um movimento.");
        }

        if (isOutOfBounds(cardMove, currentPos)) {
            throw new IllegalMovementException("A peça está sendo movida para fora do tabuleiro.");
        }

        Piece targetPiece = getPieceAtPosition(cardMove);
        if (!targetPiece.equals(null) && targetPiece.getColor().equals(color)) {
            throw new IllegalMovementException("A peça não pode ser movida para uma posição ocupada por uma peça da mesma cor.");
        }

        if (!isCardInHand(card, color)) {
            throw new InvalidCardException("A carta usada não está na mão do jogador.");
        }
    
        Piece currentPiece = getPieceAtPosition(currentPos);
    
        if (currentPiece == null) {
            throw new InvalidPieceException("Não há uma peça na posição atual.");
        }
        currentPos.setRow(currentPos.getRow() + cardMove.getRow()); 
        currentPos.setCol(currentPos.getCol() + cardMove.getCol()); 

        if(card.getColor().equals(Color.BLUE)){
            Player player = new Player(getBluePlayer().getName(), Color.BLUE, player.getCards());
        }
        Player player = new Player(getRedPlayer().getName(), Color.RED, player.getCards());
        player.swapCard(getTableCard(), card);
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
        Piece playerMestre = null;
        Piece opponentMestre = null;

        // Encontrar o mestre do jogador e do adversário
        for (Piece piece : pecas) {
            if (piece.getColor().equals(color) && piece.isMaster()) {
                playerMestre = piece;
            } else if(!piece.getColor().equals(color) && piece.isMaster()) {
                opponentMestre = piece;
            }
        }

        // Verificar se o mestre adversário foi capturado
        if (opponentMestre.equals(null)) {
            return true; // Mestre adversário foi capturado
        }

        // Verificar se o mestre do jogador ocupou o templo adversário
        Position temploAdversarioPosition;
        if (color.equals(Color.BLUE)) {
            temploAdversarioPosition = new Position(4, 2);
        } else if (color.equals(Color.RED)) {
            temploAdversarioPosition = new Position(0, 2);
        } else 
            throw new InvalidColorException("Cor inválida");

        Spot spot = new Spot(playerMestre, temploAdversarioPosition);
        if (mestreAdversarioCapturado(spot)) {
            return true;
        }
    return false;
    }

    /**
     * Método que verifica se é o turno do player
     * @param color Cor do jogador que verifica se é seu turno
     * @return Um booleano true se for o turno do jogador e false se não for
     */
    private boolean isPlayerTurn(Color color){
        boolean corAtual = getTableCard().getColor().equals(color);
        while(contador == 0){
            if (corAtual){
                contador++;
                return true;
            }  
            contador++;
            return false;
    }
        if(contador % 2 == 0){ //Se o contador for par o jogador do turno atual é igual ao do primeiro turno
            if(corAtual)
                return true;
            return false;
    }
        if(!corAtual)
            return true;
        return false;
    }
    
    /**
     * Método que verifica se o mestre adversário foi capturado
     * @param spot O local onde o mestre adversário está
     * @return Um booleano true se o mestre adversário foi capturado e false se não for
     */
    private boolean mestreAdversarioCapturado(Spot spot){
        if (spot.getPiece().equals(null)) return false; // se o jogador nao possui mestre ele não pode vencer

        if (spot.getPosition().getRow() == (spot.getPosition().getRow())){

            if(spot.getPosition().getCol() == (spot.getPosition().getCol())){
                return true;
            }
        } 
    return false;
    }

    /**
     * Método que verifica se o mestre adversário foi capturado
     * @param cardMove A posição da carta para onde a peça irá se mover
     * @param currentPos A posição da peça que irá se mover
     * @return Um booleano true se o movimento for para fora do tabuleiro e false se não for
     */
    private boolean isOutOfBounds(Position cardMove, Position currentPos){
        Position outOfBounds = new Position(0, 0);
        outOfBounds.setRow(currentPos.getRow() + cardMove.getRow());
        outOfBounds.setCol(currentPos.getCol() + cardMove.getCol()); 

        if(outOfBounds.getRow() > 4 || outOfBounds.getRow() < 0 ){
            return true;
        }
        if(outOfBounds.getCol() > 4 || outOfBounds.getCol() < 0 ){
            return true;
        }
        return false;
    }

    /**
     * Método que pega a peça em uma determinada posição
     * @param posicao A posição que sera verificada qual peça estaria
     * @return A piece que está na posição do parâmetro ou null se não existir
     */
    private Piece getPieceAtPosition(Position posicao){
        for (int i = 0; i < locais.size(); i++){
		    if(locais.get(i).getPosition() == posicao){
                return locais.get(i).getPiece();
        }
        }
        return null;
    }

    /**
     * Método que verifica se o mestre adversário foi capturado
     * @param cardMove A posição da carta para onde a peça irá se mover
     * @param currentPos A posição da peça que irá se mover
     * @return Um booleano true se o movimento for para fora do tabuleiro e false se não for
     */
    private boolean isCardInHand(Card card, Color cor){
        if(cor.equals(Color.BLUE)){
            for(int i = 1; i <= 2; i++){ //assumindo que a 2ª e 3ª carta do deck serão para o jogador com as peças azuis
                if(todasCartas[i] == card)
                    return true;
            }
        }else if (cor.equals(Color.RED)) {
            for(int i = 3; i <= 4; i++){ //assumindo que a 4ª e 5ª carta do deck serão para o jogador com as peças vermelhas
                if(todasCartas[i] == card)
                    return true;
            } 
        }else 
            throw new InvalidColorException("Cor inválida");
        return false;
        }
    }
