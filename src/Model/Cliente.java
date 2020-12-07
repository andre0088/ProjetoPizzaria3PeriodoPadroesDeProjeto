package Model;

import java.util.ArrayList;
import java.util.Date;

import BD.CentralClientes;
import BD.CentralPedidos;
import BD.CentralPizzas;
import BD.PersistenciaCliente;
import BD.PersistenciaPedido;
import BD.PersistenciaPizza;
import View.ClienteJaExistenteException;
import View.DadosNaoPreenchidosException;
import View.PedidoJaExistenteException;
import View.SaborInexistenteException;

public class Cliente extends Usuario{
	
	private String endereco;
	private String telefone;
	private ArrayList<Pedido> pedidos;
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public void cadastroEPedidoCliente(Cliente cliente,Date data,String id,float precoPedido,int qtdPizzas,String status,String sabor,String tamanho) throws DadosNaoPreenchidosException,ClienteJaExistenteException,SaborInexistenteException,PedidoJaExistenteException{
		PersistenciaCliente perCli = new PersistenciaCliente();
		CentralClientes centralClientes = perCli.recuperarCentral();
		PersistenciaPizza perPi = new PersistenciaPizza();
		CentralPizzas centralPizzas = perPi.recuperarCentral();
		PersistenciaPedido perPed = new PersistenciaPedido();
		CentralPedidos centralPedidos = perPed.recuperarCentral();
		
		Pizza pi= new Pizza();
		pi.setTipo(sabor);
		pi.setTamanho(tamanho);
		for(Pizza pizza:centralPizzas.getPizzas()) {
			if (pi.getTipo().equals(pizza.getTipo()) && pi.getTamanho().equals(pizza.getTamanho())){
				pi.setCodigoId(pizza.getCodigoId());
				pi.setFormaPreparo(pizza.getFormaPreparo());
				pi.setIgredientes(pizza.getIgredientes());
				pi.setPrecoCompleto(pizza.getPrecoCompleto());
				break;
			}
		}
		Pedido pedido = new Pedido();
		pedido.setCpfCliente(cliente.getIdentificacao());
		pedido.setData(data);
		pedido.setID(id);
		pedido.setPrecoFinal(precoPedido);
		pedido.setPizza(pi);
		pedido.setStatus(status);
		pedido.setQtdPizzas(qtdPizzas);
		
		int cont=0;
		for(int i=0;i<centralPedidos.getHistoricoDePedidos().size();i++){
			if(centralPedidos.getHistoricoDePedidos().get(i).getID().equals(pedido.getID()) && pedido.getStatus().equals(centralPedidos.getHistoricoDePedidos().get(i).getStatus()) 
					&& centralPedidos.getHistoricoDePedidos().get(i).getStatus().equals("Entregue")==false){
				
				throw new PedidoJaExistenteException();
			}
		}
		
		for(Pizza pizza:centralPizzas.getPizzas()) {
			if (pedido.getPizza().getTipo().equals(pizza.getTipo())){
				cont++;
				break;
			}
		}
		if(cont==0) {
			throw new SaborInexistenteException();
		}
		
		for(int i=0;i<centralClientes.getClientes().size();i++) {
			if(centralClientes.getClientes().get(i).getIdentificacao().equals(cliente.getIdentificacao())) {
				throw new ClienteJaExistenteException();
			}
		}
		cliente.getPedidos().add(pedido);
		centralPedidos.getHistoricoDePedidos().add(pedido);
		centralClientes.getClientes().add(cliente);
		
		perCli.salvarCentral(centralClientes);
		perPed.salvarCentral(centralPedidos);
		
	}
	
	public void editarCliente(String cpf,String endereco,String telefone, String nome) throws DadosNaoPreenchidosException{
		PersistenciaCliente perCli = new PersistenciaCliente();
		CentralClientes centralClientes = perCli.recuperarCentral();
		int cont =0;
		
		for(int i=0;i<centralClientes.getClientes().size();i++) {
			if(centralClientes.getClientes().get(i).getIdentificacao().equals(cpf)) {
				cont++;
			}
		}
		if(cont==0) {
			throw new DadosNaoPreenchidosException();
		}
		
		Cliente antigo = new Cliente();
		
		for(int i =0;i<centralClientes.getClientes().size();i++) {
			if(centralClientes.getClientes().get(i).getIdentificacao().equals(cpf)) {
				antigo=centralClientes.getClientes().get(i);
				break;
			}
		}
		
		antigo.setEndereco(endereco);
		antigo.setTelefone(telefone);
		antigo.setNome(nome);
		
		perCli.salvarCentral(centralClientes);
	}
	
	public void excluirCliente(String id){
		PersistenciaCliente perCli = new PersistenciaCliente();
		CentralClientes centralClientes = perCli.recuperarCentral();
		PersistenciaPedido perPed = new PersistenciaPedido();
		CentralPedidos centralPedidos = perPed.recuperarCentral();
		
		Cliente cli= new Cliente();
		
		for(int i=0;i<centralClientes.getClientes().size();i++) {
			if(centralClientes.getClientes().get(i).getIdentificacao().equals(id)) {
				cli=centralClientes.getClientes().get(i);
				centralClientes.getClientes().remove(i);
				break;
			}
		}
		for(int i=0;i<centralPedidos.getHistoricoDePedidos().size();i++) {
			if(centralPedidos.getHistoricoDePedidos().get(i).getCpfCliente().equals(cli.getIdentificacao()) && centralPedidos.getHistoricoDePedidos().get(i).getStatus().equals("Entregue")==false) {
				centralPedidos.getHistoricoDePedidos().remove(i);
			}
		}
		perCli.salvarCentral(centralClientes);
		perPed.salvarCentral(centralPedidos);
	}
	
	public ArrayList<Cliente> listarClientes(){
		PersistenciaCliente perCli = new PersistenciaCliente();
		CentralClientes centralClientes = perCli.recuperarCentral();
		return centralClientes.getClientes();
		
	}
	
	public ArrayList<Pedido> listarPedidosCliente(String cpf){
		PersistenciaCliente perCli = new PersistenciaCliente();
		CentralClientes centralClientes = perCli.recuperarCentral();
		
		ArrayList<Pedido> pedidosCliente = new ArrayList<Pedido>();
		for(Cliente cliente:centralClientes.getClientes()) {
			if(cliente.getIdentificacao().equals(cpf)) {
				pedidosCliente=cliente.getPedidos();
				break;
			}
		}
		return pedidosCliente;
	}

	public String enderecoCliente(String cpf){
		PersistenciaCliente perCli = new PersistenciaCliente();
		CentralClientes centralClientes = perCli.recuperarCentral();
		String endereco="";
		for(Cliente cliente: centralClientes.getClientes()) {
			if(cliente.getIdentificacao().equals(cpf)) {
				endereco=cliente.getEndereco();
				break;
			}
		}
		return endereco;
	}
}
