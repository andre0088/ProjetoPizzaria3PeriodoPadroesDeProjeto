package View;

public class ClienteJaExistenteException extends Exception{
	public ClienteJaExistenteException() {
		super("J� existe um cliente com esse cpf cadastrado.");
	}
}
