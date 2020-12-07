package Controll;

import java.util.ArrayList;

import Model.Pizza;
import View.DadosNaoPreenchidosException;
import View.IdInexistenteException;
import View.PizzaJaExistenteException;

public class ControladorPizza {
	
	public void cadastrarP(String tipo, String tamanho, String id, String preparo, String ingredientes,float preco) throws DadosNaoPreenchidosException,PizzaJaExistenteException{
		
		if(id.equals("") ||preparo.equals("") ||ingredientes.equals("") || 
				preco==0 || tamanho.equals("") || tipo.equals("")) {
					throw new DadosNaoPreenchidosException();
		}
		Pizza pizza = new Pizza();
		pizza.setCodigoId(id);
		pizza.setFormaPreparo(preparo);
		pizza.setIgredientes(ingredientes);
		pizza.setPrecoCompleto(preco);
		pizza.setTamanho(tamanho);
		pizza.setTipo(tipo);
		pizza.cadastrarPizza(pizza);
	}
	
	public void editarP(String id,String preparo,String igredientes,float preco,String tamanho, String sabor) throws DadosNaoPreenchidosException,IdInexistenteException{

		if(id.equals("") || preparo.equals("") || igredientes.equals("") || preco==0 || tamanho.equals("") || sabor.equals("")) {
			throw new DadosNaoPreenchidosException();
		}
		
		Pizza pizza = new Pizza();
		pizza.setCodigoId(id);
		pizza.setFormaPreparo(preparo);
		pizza.setIgredientes(igredientes);
		pizza.setPrecoCompleto(preco);
		pizza.setTamanho(tamanho);
		pizza.setTipo(sabor);
		pizza.editarPizza(pizza);
	
		
	}
	
	public void excluirP(String id){
		Pizza pizza = new Pizza();
		pizza.excluirPizza(id);
	}
	
	public ArrayList<Pizza> listarP(){
		Pizza pizza = new Pizza();
		return pizza.listarPizzas();
	}
	
	public float retornarPrecoPizza(String sabor, String tamanho) {
		Pizza pizza = new Pizza();
		return pizza.retornarPreco(sabor, tamanho);
	}

}
