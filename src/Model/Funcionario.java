package Model;

import java.util.ArrayList;

import BD.CentralFuncionarios;
import BD.PersistenciaFuncionario;
import View.LoginOuSenhaInvalidosException;
import View.UsuarioJaExistenteException;

public class Funcionario extends Usuario{
	
	String cargo;
	private String senha;
	private String login;
	
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public Funcionario fazerLogin(Funcionario fun) throws LoginOuSenhaInvalidosException{
		PersistenciaFuncionario per = new PersistenciaFuncionario();
		CentralFuncionarios central = per.recuperarCentral();
		
		Funcionario f = null;
		
		
		for(Funcionario elemento: central.getFuncionarios()) {
			if(elemento.getLogin().equals(fun.getLogin()) && elemento.getSenha().equals(fun.getSenha())) {
				f=elemento;
				central.setUsuarioLogado(elemento);
				break;
			}
		}
		
		if(f==null) {
			throw new LoginOuSenhaInvalidosException();
		}
		per.salvarCentral(central);
		return f;
	}
	
	public void cadastrarFuncionario(Funcionario u) throws UsuarioJaExistenteException{
		PersistenciaFuncionario per = new PersistenciaFuncionario();
		CentralFuncionarios central = per.recuperarCentral();
		
		for(int c=0;c<central.getFuncionarios().size();c++) {
			if (central.getFuncionarios().get(c).getLogin().equals(u.getLogin()) || central.getFuncionarios().get(c).getIdentificacao().equals(u.getIdentificacao())) {
				throw new UsuarioJaExistenteException();
			}
		}
		if(central.getFuncionarios().isEmpty()) {
			central.setUsuarioLogado(u);
		}
		central.getFuncionarios().add(u);
		per.salvarCentral(central);

	}
	
	public void editarFuncionario(Funcionario novo) throws UsuarioJaExistenteException{
		PersistenciaFuncionario per = new PersistenciaFuncionario();
		CentralFuncionarios central = per.recuperarCentral();
		
		Funcionario antigo = new Funcionario();
		
		for(int i =0;i<central.getFuncionarios().size();i++) {
			if(central.getFuncionarios().get(i).getIdentificacao().equals(novo.getIdentificacao())) {
				antigo=central.getFuncionarios().get(i);
				break;
			}
		}
		
		antigo.setLogin("");
		antigo.setIdentificacao("");
		
		for(int i=0;i<central.getFuncionarios().size();i++) {
			if(central.getFuncionarios().get(i).getLogin().equals(novo.getLogin()) || central.getFuncionarios().get(i).getIdentificacao().equals(novo.getIdentificacao())) {
				throw new UsuarioJaExistenteException();
			}
		}
		antigo.setLogin(novo.getLogin());
		antigo.setIdentificacao(novo.getIdentificacao());
		antigo.setCargo(novo.getCargo());
		antigo.setSenha(novo.getSenha());
		antigo.setNome(novo.getNome());
		
		per.salvarCentral(central);
		
	}
	
	public void excluirFuncionario(String id){
		PersistenciaFuncionario per = new PersistenciaFuncionario();
		CentralFuncionarios central = per.recuperarCentral();
		
		for(int i=0;i<central.getFuncionarios().size();i++) {
			if(central.getFuncionarios().get(i).getIdentificacao().equals(id)) {
				central.getFuncionarios().remove(i);
				break;
			}
		}

		per.salvarCentral(central);
	}
	
	public ArrayList<Funcionario> listarFuncionarios(){
		PersistenciaFuncionario per = new PersistenciaFuncionario();
		CentralFuncionarios central = per.recuperarCentral();
		return central.getFuncionarios();
	}
	
	public Funcionario usuarioLogado() {
		PersistenciaFuncionario per = new PersistenciaFuncionario();
		CentralFuncionarios central = per.recuperarCentral();
		return central.getUsuarioLogado();
	}
	
}
