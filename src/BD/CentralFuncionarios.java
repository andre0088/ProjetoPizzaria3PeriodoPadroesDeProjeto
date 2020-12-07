package BD;

import java.util.ArrayList;

import Model.Funcionario;

public class CentralFuncionarios {
	private Funcionario usuarioLogado;
	
	private ArrayList<Funcionario>funcionarios = new ArrayList<Funcionario>();

	public Funcionario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Funcionario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	
	
	
}
