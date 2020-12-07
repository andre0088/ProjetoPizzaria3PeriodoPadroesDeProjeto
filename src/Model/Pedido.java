package Model;

import java.util.ArrayList;
import java.util.Date;

import BD.CentralClientes;
import BD.CentralPedidos;
import BD.CentralPizzas;
import BD.PersistenciaCliente;
import BD.PersistenciaPedido;
import BD.PersistenciaPizza;
import View.CpfInvalidoException;
import View.IdInexistenteException;
import View.PedidoJaExistenteException;
import View.SaborInexistenteException;

public class Pedido {
	
	private String cpfCliente;
	private String ID;
	private Pizza pizza;
	private float precoFinal;
	private Date data;
	private String status;
	private int qtdPizzas;
	


	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	public float getPrecoFinal() {
		return precoFinal;
	}
	public void setPrecoFinal(float precoFinal) {
		this.precoFinal = precoFinal;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getQtdPizzas() {
		return qtdPizzas;
	}
	public void setQtdPizzas(int qtdPizzas) {
		this.qtdPizzas = qtdPizzas;
	}
	
	public void excluirPedido(String id,String cpf) throws IdInexistenteException{
		PersistenciaCliente perCli = new PersistenciaCliente();
		CentralClientes centralClientes = perCli.recuperarCentral();
		PersistenciaPedido perPed = new PersistenciaPedido();
		CentralPedidos centralPedidos = perPed.recuperarCentral();
		
		int cont=0;
		
		for(Cliente c:centralClientes.getClientes()){
			if(c.getIdentificacao().equals(cpf)){
				ArrayList<Pedido>pedidosCliente=c.getPedidos();
				for(Pedido p: pedidosCliente){
					
					if(p.getCpfCliente().equals(cpf) && p.getStatus().equals("Entregue")==false && p.getID().equals(id)){
						cont++;
						pedidosCliente.remove(p);
						c.setPedidos(pedidosCliente);
						break;
					}
				}
			}
		}
		
		for(int i=0;i<centralPedidos.getHistoricoDePedidos().size();i++) {
			if(centralPedidos.getHistoricoDePedidos().get(i).getID().equals(id)==true && centralPedidos.getHistoricoDePedidos().get(i).getStatus().equals("Entregue")==false && centralPedidos.getHistoricoDePedidos().get(i).getCpfCliente().equals(cpf)) {
				cont++;
				centralPedidos.getHistoricoDePedidos().remove(i);
				break;
			}
		}
		
		if(cont==0) {
			throw new IdInexistenteException();
		}
		perCli.salvarCentral(centralClientes);
		perPed.salvarCentral(centralPedidos);
	}
	
	public void realizarPedido(String id,String cpf, int qtdPizzas, String sabor, String tamanho, String status,float precoFinal) throws PedidoJaExistenteException,CpfInvalidoException,SaborInexistenteException{
		PersistenciaCliente perCli = new PersistenciaCliente();
		CentralClientes centralClientes = perCli.recuperarCentral();
		PersistenciaPedido perPed = new PersistenciaPedido();
		CentralPedidos centralPedidos = perPed.recuperarCentral();
		PersistenciaPizza perPiz = new PersistenciaPizza();
		CentralPizzas centralPizzas= perPiz.recuperarCentral();
		
		for(int i=0;i<centralPedidos.getHistoricoDePedidos().size();i++){
			if(centralPedidos.getHistoricoDePedidos().get(i).getID().equals(id) && status.equals(centralPedidos.getHistoricoDePedidos().get(i).getStatus()) 
					&& centralPedidos.getHistoricoDePedidos().get(i).getStatus().equals("Entregue")==false){
				
				throw new PedidoJaExistenteException();
			}
		}
		int cont=0;
		
		Pizza pizza= new Pizza();
		for(Pizza pi: centralPizzas.getPizzas()) {
			if(pi.getTipo().equals(sabor) && pi.getTamanho().equals(tamanho)) {
				pizza.setCodigoId(pi.getCodigoId());
				pizza.setFormaPreparo(pi.getFormaPreparo());
				pizza.setIgredientes(pi.getIgredientes());
				pizza.setPrecoCompleto(pi.getPrecoCompleto());
				pizza.setTamanho(pi.getTamanho());
				pizza.setTipo(pi.getTipo());
				break;
			}
		}
		
		Pedido p = new Pedido();
		p.setCpfCliente(cpf);
		p.setData(new Date());
		p.setID(id);
		p.setPizza(pizza);
		p.setPrecoFinal(precoFinal);
		p.setQtdPizzas(qtdPizzas);
		p.setStatus(status);
		
		
		for(int i=0;i<centralClientes.getClientes().size();i++) {
			if(centralClientes.getClientes().get(i).getIdentificacao().equals(p.getCpfCliente())) {
				cont++;
				centralClientes.getClientes().get(i).getPedidos().add(p);
				centralPedidos.getHistoricoDePedidos().add(p);
				break;
			}
		}
		int cont2=0;
		
		for(Pizza pizzaaa:centralPizzas.getPizzas()) {
			if (pizzaaa.getTipo().equals(p.getPizza().getTipo())){
				cont2++;
				break;
			}
		}
		if(cont2==0) {
			throw new SaborInexistenteException();
		}
		if(cont==0) {
			throw new CpfInvalidoException();
		}
		
		perPiz.salvarCentral(centralPizzas);
		perCli.salvarCentral(centralClientes);
		perPed.salvarCentral(centralPedidos);
	}
	
	public void editarStatusPedidoPizzaiollo(String id,String cpf,String statusAntigo,String stts) throws IdInexistenteException{
		PersistenciaPedido perPed = new PersistenciaPedido();
		CentralPedidos centralPedidos = perPed.recuperarCentral();
		
		int cont=0;
		
		
		for(int i =0;i<centralPedidos.getHistoricoDePedidos().size();i++) {
			if(centralPedidos.getHistoricoDePedidos().get(i).getCpfCliente().equals(cpf) && centralPedidos.getHistoricoDePedidos().get(i).getStatus().equals(statusAntigo)
					&& centralPedidos.getHistoricoDePedidos().get(i).getID().equals(id) && centralPedidos.getHistoricoDePedidos().get(i).getStatus().equals("Entregue")==false) {
				
				cont++;
				centralPedidos.getHistoricoDePedidos().get(i).setStatus(stts);
				break;
			}
		}
		
		
		if(cont==0) {
			throw new IdInexistenteException();
		}
		
		perPed.salvarCentral(centralPedidos);
		
	}
	
	public void editarStatusPedidoMotoBoy(String cpf,String stts) throws IdInexistenteException{
		PersistenciaPedido perPed = new PersistenciaPedido();
		CentralPedidos centralPedidos = perPed.recuperarCentral();
		
		int cont=0;
		
		
		for(int i =0;i<centralPedidos.getHistoricoDePedidos().size();i++) {
			if(centralPedidos.getHistoricoDePedidos().get(i).getCpfCliente().equals(cpf) && 
					centralPedidos.getHistoricoDePedidos().get(i).getStatus().equals("Entregue")==false) {
				
				cont++;
				centralPedidos.getHistoricoDePedidos().get(i).setStatus(stts);
			}
		}
		
		
		if(cont==0) {
			throw new IdInexistenteException();
		}
		
		perPed.salvarCentral(centralPedidos);
		
	}
	
	public ArrayList<Pedido> listarPedidos(){
		PersistenciaPedido perPed = new PersistenciaPedido();
		CentralPedidos centralPedidos = perPed.recuperarCentral();
		return centralPedidos.getHistoricoDePedidos();
	}

	
}
