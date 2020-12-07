package Controll;

import java.util.ArrayList;
import java.util.Date;

import Model.Cliente;
import Model.Pedido;
import View.ClienteJaExistenteException;
import View.DadosNaoPreenchidosException;
import View.IdInexistenteException;
import View.PedidoJaExistenteException;
import View.SaborInexistenteException;

public class ControladorCliente {
	
	public void cadastroEPedidoC(String nome,String cpf,String endereco,String telefone,Date data,String id,float precoPedido,int qtdPizzas,String status,String sabor,String tamanho) throws DadosNaoPreenchidosException, ClienteJaExistenteException, SaborInexistenteException, PedidoJaExistenteException{
		if(endereco.equals("") || cpf.equals("") || nome.equals("") || id.equals("") || sabor.equals("") || qtdPizzas==0 || telefone.equals("") || tamanho.equals("")) {
			throw new DadosNaoPreenchidosException();
		}
		
		Cliente cliente = new Cliente();
		cliente.setEndereco(endereco);
		cliente.setIdentificacao(cpf);
		cliente.setNome(nome);
		cliente.setTelefone(telefone);
		cliente.setPedidos(new ArrayList<Pedido>());
		
		
		cliente.cadastroEPedidoCliente(cliente,data,id,precoPedido,qtdPizzas,status,sabor,tamanho);
	}
	
	public void editarC(String nome,String endereco,String cpf, String telefone) throws DadosNaoPreenchidosException{
		
		if(endereco.equals("") || cpf.equals("") || nome.equals("") || telefone.equals("")) {
			throw new DadosNaoPreenchidosException();
		}
		
		Cliente cliente = new Cliente();
		cliente.editarCliente(cpf, endereco, telefone, nome);
		
	}
	
	public void excluirC(String id) throws IdInexistenteException{
		Cliente cliente = new Cliente();
		cliente.excluirCliente(id);
	}
	
	public ArrayList<Cliente> listarC() {
		Cliente cliente = new Cliente();
		return cliente.listarClientes();
	}
	
	public String endCliente(String cpf){
		Cliente cliente = new Cliente();
		return cliente.enderecoCliente(cpf);
	}
	
}
	
	
