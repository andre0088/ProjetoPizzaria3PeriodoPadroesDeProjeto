package Model;

import BD.CentralFuncionarios;
import BD.PersistenciaFuncionario;
import View.JanelaDeLogin;
public class Principal {
	public static void main(String[] args) {
		PersistenciaFuncionario perFun = new PersistenciaFuncionario();
		CentralFuncionarios centralFuncionarios = perFun.recuperarCentral();
		if(centralFuncionarios.getFuncionarios().isEmpty()) {
			Funcionario admin= new Funcionario();
			admin.setCargo("Admin");
			admin.setIdentificacao("Admin");
			admin.setLogin("admin");
			admin.setSenha("admin");
			admin.setNome("Admin");
			centralFuncionarios.getFuncionarios().add(admin);
		}
		perFun.salvarCentral(centralFuncionarios);
		new JanelaDeLogin();
	}
}
