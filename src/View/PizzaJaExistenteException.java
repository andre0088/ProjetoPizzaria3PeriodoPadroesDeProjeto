package View;

public class PizzaJaExistenteException extends Exception{
	public PizzaJaExistenteException() {
		super("J� existe uma pizza cadastrada com o mesmo ID.");
	}
}
