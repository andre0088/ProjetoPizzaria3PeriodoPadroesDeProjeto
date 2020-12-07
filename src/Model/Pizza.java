package Model;

import java.util.ArrayList;

import BD.CentralPizzas;
import BD.PersistenciaPizza;
import View.IdInexistenteException;
import View.PizzaJaExistenteException;

public class Pizza {
	
	private String tipo;
	private String tamanho;
	private String codigoId;
	private String formaPreparo;
	private String Igredientes;
	private float precoCompleto;
	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getCodigoId() {
		return codigoId;
	}
	public void setCodigoId(String codigoId) {
		this.codigoId = codigoId;
	}
	public String getFormaPreparo() {
		return formaPreparo;
	}
	public void setFormaPreparo(String formaPreparo) {
		this.formaPreparo = formaPreparo;
	}
	public String getIgredientes() {
		return Igredientes;
	}
	public void setIgredientes(String igredientes) {
		Igredientes = igredientes;
	}
	public float getPrecoCompleto() {
		return precoCompleto;
	}
	public void setPrecoCompleto(float precoCompleto) {
		this.precoCompleto = precoCompleto;
	}
	
	public void cadastrarPizza(Pizza p) throws PizzaJaExistenteException{
		PersistenciaPizza perPiz = new PersistenciaPizza();
		CentralPizzas centralPizzas = perPiz.recuperarCentral();
		
		for(int i=0;i<centralPizzas.getPizzas().size();i++) {
			if(centralPizzas.getPizzas().get(i).getCodigoId().equals(p.getCodigoId())) {
				throw new PizzaJaExistenteException();
			}
		}
		centralPizzas.getPizzas().add(p);
		perPiz.salvarCentral(centralPizzas);
	}
	
	public void editarPizza(Pizza pizza) throws IdInexistenteException{
		PersistenciaPizza perPiz = new PersistenciaPizza();
		CentralPizzas centralPizzas = perPiz.recuperarCentral();
		
		int cont=0;
		for(int i=0;i<centralPizzas.getPizzas().size();i++) {
			if(centralPizzas.getPizzas().get(i).getCodigoId().equals(pizza.getCodigoId())) {
				cont++;
				centralPizzas.getPizzas().get(i).setFormaPreparo(pizza.getFormaPreparo());
				centralPizzas.getPizzas().get(i).setIgredientes(pizza.getIgredientes());
				centralPizzas.getPizzas().get(i).setPrecoCompleto(pizza.getPrecoCompleto());
				centralPizzas.getPizzas().get(i).setTamanho(pizza.getTamanho());
				centralPizzas.getPizzas().get(i).setTipo(pizza.getTipo());
				perPiz.salvarCentral(centralPizzas);
				break;
			}
		}
		if(cont==0) {
			throw new IdInexistenteException();
		}

	}
	
	public void excluirPizza(String id){
		PersistenciaPizza perPiz = new PersistenciaPizza();
		CentralPizzas centralPizzas = perPiz.recuperarCentral();
		
		for(int i=0;i<centralPizzas.getPizzas().size();i++) {
			if(centralPizzas.getPizzas().get(i).getCodigoId().equals(id)) {
				centralPizzas.getPizzas().remove(i);
				perPiz.salvarCentral(centralPizzas);
				break;
			}
		}

	}
	
	public ArrayList<Pizza> listarPizzas(){
		PersistenciaPizza perPiz = new PersistenciaPizza();
		CentralPizzas centralPizzas = perPiz.recuperarCentral();
		return centralPizzas.getPizzas();
	}
	
	public float retornarPreco(String sabor,String tamanho) {
		PersistenciaPizza perPiz = new PersistenciaPizza();
		CentralPizzas centralPizzas = perPiz.recuperarCentral();
		float preco=0;
		for(Pizza pizza:centralPizzas.getPizzas()) {
			if(pizza.getTipo().equals(sabor) && pizza.getTamanho().equals(tamanho)) {
				preco=pizza.getPrecoCompleto();
				break;
			}
		}
		return preco;
	}
	
}
