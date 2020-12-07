package Controll;

import java.util.ArrayList;


import Model.Funcionario;
import View.DadosNaoPreenchidosException;
import View.LoginOuSenhaInvalidosException;
import View.UsuarioJaExistenteException;

public class ControladorFuncionario {
	
	public Funcionario fzrLogin(String login, String senha) throws LoginOuSenhaInvalidosException {
		Funcionario fun = new Funcionario();
		fun.setLogin(login);
		fun.setSenha(senha);
		return(fun.fazerLogin(fun));
	}
	
	public void cadastrarF(String nome,String login,String id, String senha, String cargo) throws DadosNaoPreenchidosException, UsuarioJaExistenteException{
		
		if(id.equals("") || nome.equals("")) {
			throw new DadosNaoPreenchidosException();
		}
		
		if((login.equals("") || senha.equals("")) && (cargo.equals("Limpeza")==false)) {
			throw new DadosNaoPreenchidosException();
		}
		
		Funcionario fun = new Funcionario();
		fun.setCargo(cargo);
		fun.setIdentificacao(id);
		fun.setLogin(login);
		fun.setNome(nome);
		fun.setSenha(senha);
		fun.cadastrarFuncionario(fun);
		

	}
	
	public void editarF(String nome,String login,String id, String senha, String cargo) throws DadosNaoPreenchidosException, UsuarioJaExistenteException{

		if(id.equals("") || nome.equals("")) {
			throw new DadosNaoPreenchidosException();
		}
		
		if((login.equals("") || login.equals("")) && (cargo.equals("Limpeza")==false)) {
			throw new DadosNaoPreenchidosException();
		}
		
		Funcionario novo = new Funcionario();
		novo.setCargo(cargo);
		novo.setIdentificacao(id);
		novo.setLogin(login);
		novo.setNome(nome);
		novo.setSenha(senha);
		novo.editarFuncionario(novo);
		
	}
	
	public void excluirF(String id){
		Funcionario fun = new Funcionario();
		fun.excluirFuncionario(id);	
	
	}
	
	public String usuarioL() {
		Funcionario fun = new Funcionario();
		if(fun.usuarioLogado().getCargo().equals("Gerente")) {
			return "Gerente"+"-"+fun.usuarioLogado().getNome();
		}else if(fun.usuarioLogado().getCargo().equals("Admin")){
			return "Admin"+"-"+fun.usuarioLogado().getNome();
		}else if(fun.usuarioLogado().getCargo().equals("Atendente")) {
			return "Atendente"+"-"+fun.usuarioLogado().getNome();
		}else if(fun.usuarioLogado().getCargo().equals("Pizzaiollo")) {
			return "Pizaiollo"+"-"+fun.usuarioLogado().getNome();
		}else {
			return "Motoboy"+"-"+fun.usuarioLogado().getNome();
		}
	}
	
	public ArrayList<Funcionario> listarF(){
		Funcionario funcionario = new Funcionario();
		return funcionario.listarFuncionarios();
	}
	
		
	
}
