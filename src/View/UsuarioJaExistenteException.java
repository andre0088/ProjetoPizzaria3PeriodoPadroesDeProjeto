package View;

public class UsuarioJaExistenteException extends Exception{
	public UsuarioJaExistenteException() {
		super("J� existe um funcion�rio com o mesmo ID ou senha !");
	}
}
