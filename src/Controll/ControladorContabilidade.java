package Controll;

import Model.Contabilidade;
import View.NaoHaPedidosException;

public class ControladorContabilidade {
	
	public void contabilidade() throws NaoHaPedidosException {
		Contabilidade contabilidade = new Contabilidade();
		contabilidade.gerarContabilidade();
	}
	
}
