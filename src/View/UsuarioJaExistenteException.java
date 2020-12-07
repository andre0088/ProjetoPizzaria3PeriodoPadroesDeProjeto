package View;

public class UsuarioJaExistenteException extends Exception{
	public UsuarioJaExistenteException() {
		super("Já existe um funcionário com o mesmo ID ou senha !");
	}
}
