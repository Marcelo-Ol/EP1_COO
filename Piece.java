import java.util.ArrayList;

/**
 * Classe que contém informações das peças de jogo
 */
public class Piece { 
    /**
     * Construtor que define a cor e o tipo da peça
     * @param color Cor da peça
     * @param isMaster Se o tipo da peça é mestre ou não
     */

    private Color color;
    private boolean isMaster;
    private ArrayList<Piece> pieces;

    public Piece(Color color, boolean isMaster) {
        this.color = color;
        this.isMaster = isMaster;
    }

    // Array de peças DEPOIS TIRAR PLMDS
    public ArrayList<Piece> ArrayPieces(int row, Color cor){
        Piece peça = new Piece(cor.BLUE, false);

        for(int i = 0; i < 10; i++){
            boolean mestre = false;

            if(i == 2 || i == 7){
                mestre = true;
            }

            if(row == 0){
                peça = new Piece(cor.BLUE, mestre);
            }
            if(row == 4){
                peça = new Piece(cor.RED, mestre);
            }

            this.pieces.add(i, peça);
        }
        return pieces;
    }

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
    public boolean isMaster(boolean isMaster) {
        if(isMaster)
            return true;
        else
            return false;
    }
}
