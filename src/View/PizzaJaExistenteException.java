package View;

public class PizzaJaExistenteException extends Exception{
	public PizzaJaExistenteException() {
		super("Já existe uma pizza cadastrada com o mesmo ID.");
	}
}
