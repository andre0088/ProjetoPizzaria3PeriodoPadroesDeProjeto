package View;

public class PedidoJaExistenteException extends Exception{
	public PedidoJaExistenteException() {
		super("Esse id j� exixte !");
	}
}
