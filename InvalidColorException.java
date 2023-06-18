/**
 * Exceção para quando se tenta usar uma cor inválida
 */
public class InvalidColorException extends OnitamaGameException {
    /**
     * Construtor que recebe uma mensagem e repassa para a superclasse
     * @param message A mensagem descrevendo o motivo do problema
     */
    public InvalidColorException(String message) {
        super(message);
    }
}
