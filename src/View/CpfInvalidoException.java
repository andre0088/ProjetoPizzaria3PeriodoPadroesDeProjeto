package View;

public class CpfInvalidoException extends Exception{
	public CpfInvalidoException() {
		super("Esse cpf n�o est� cadastrado no sistema.");
	}
}
