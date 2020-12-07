package View;

public class ClienteJaExistenteException extends Exception{
	public ClienteJaExistenteException() {
		super("Já existe um cliente com esse cpf cadastrado.");
	}
}
