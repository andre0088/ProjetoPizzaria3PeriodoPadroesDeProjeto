package Controll;

import java.util.ArrayList;
import Model.Cliente;
import Model.Pedido;
import View.CpfInvalidoException;
import View.DadosNaoPreenchidosException;
import View.IdInexistenteException;
import View.PedidoJaExistenteException;
import View.SaborInexistenteException;

public class ControladorPedido {
	
	public void excluirP(String id,String cpf) throws IdInexistenteException{
		Pedido pedido = new Pedido();
		pedido.excluirPedido(id, cpf);
	}
	
	
	public void realizarP(String id,String cpf, int qtdPizzas, String sabor, String tamanho, String status,float precoFinal) throws DadosNaoPreenchidosException,PedidoJaExistenteException,CpfInvalidoException,SaborInexistenteException{
		
		
		if(cpf.equals("") || id.equals("") || sabor.equals("") || status.equals("") || tamanho.equals("")) {
			throw new DadosNaoPreenchidosException();
		}
		
		Pedido pedido = new Pedido();
		pedido.realizarPedido( id, cpf,qtdPizzas, sabor,tamanho, status,precoFinal);
		
	}
	
	public void editarStatusPizzaiollo(String id,String cpf,String statusAntigo,String novoStts) throws IdInexistenteException{
		Pedido pedido = new Pedido();
		pedido.editarStatusPedidoPizzaiollo(id, cpf,statusAntigo,novoStts);
		
	}
	
	public void editarStatusMotoboy(String cpf,String status) throws IdInexistenteException {
		Pedido pedido = new Pedido();
		pedido.editarStatusPedidoMotoBoy(cpf, status);
	}
	
	public ArrayList<Pedido> listarP(){
		Pedido pedido = new Pedido();
		return pedido.listarPedidos();
	}
	
	public ArrayList<Pedido> pedidosCliente(String cpf){
		Cliente cl= new Cliente();
		return cl.listarPedidosCliente(cpf);
	}


}
