package View;

public class NaoHaPedidosException extends Exception{
	public NaoHaPedidosException() {
		super("Nenhum pedido foi finalizado at� o momento.");
	}
}
