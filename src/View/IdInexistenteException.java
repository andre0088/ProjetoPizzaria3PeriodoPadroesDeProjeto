package View;

public class IdInexistenteException extends Exception{
	public IdInexistenteException() {
		super("Código de Identificação inválido.");
	}
}
