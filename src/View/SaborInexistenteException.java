package View;

public class SaborInexistenteException extends Exception{
	public SaborInexistenteException() {
		super("Esse sabor n�o est� cadastrado.");
	}
}
