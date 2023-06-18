

/**
 * Classe que contém informações das peças de jogo
 */
public class Piece { 
    private Color color;
    private boolean isMaster;
    private Position pos = new Position(0, 0);

    /**
     * Construtor que define a cor e o tipo da peça
     * @param color Cor da peça
     * @param isMaster Se o tipo da peça é mestre ou não
     */
    public Piece(Color color, boolean isMaster) {
        this.color = color;
        this.isMaster = isMaster;
    }

    /*Array de peças (DEPOIS TIRAR PLMDS)
    public void ArrayPieces(int row){
        Piece peça = new Piece(Color.BLUE, false);

        for(int i = 0; i < 11; i++){
            boolean mestre = false;

            if(i == 2 || i == 7){
                mestre = true;
            }

            if(row == 0){
                peça = new Piece(Color.BLUE, mestre);
            }
            if(row == 4){
                peça = new Piece(Color.RED, mestre);
            }

            this.pieces.add(i, peça);
        }
    }*/

    /* FOI UM TESTE COM O ARRAY D GAME


    /**
     * Método que devolve a cor da peça
     * @return Enum Color com a cor da peça
     */
    public Color getColor() {
        return color;
        // cor fora do enum (exception)
    }

    /**
     * Método que devolve se é um mestre ou não
     * @return Booleano true para caso seja um mestre e false caso contrário
     */
    public boolean isMaster() {
        return isMaster;
}
}
