/**
 * Classe que contém informações das peças de jogo
 */
public class Piece { 
    /**
     * Construtor que define a cor e o tipo da peça
     * @param color Cor da peça
     * @param isMaster Se o tipo da peça é mestre ou não
     */

    private Color cor;
    private boolean mestre;

    public Piece(Color color, boolean isMaster) {
        this.cor = color;
        this.mestre = isMaster(isMaster);
    }

    /**
     * Método que devolve a cor da peça
     * @return Enum Color com a cor da peça
     */
    public Color getColor() {
       // return cor; ?
        return null;
    }

    /**
     * Método que devolve se é um mestre ou não
     * @return Booleano true para caso seja um mestre e false caso contrário
     */
    public boolean isMaster(boolean isMaster) {
        if(meio da matriz)
            return true;
        else
            return false;
    }
}
