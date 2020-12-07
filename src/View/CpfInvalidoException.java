package View;

public class CpfInvalidoException extends Exception{
	public CpfInvalidoException() {
		super("Esse cpf não está cadastrado no sistema.");
	}
}
