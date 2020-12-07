package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controll.ControladorContabilidade;

public class OuvinteBtContabilidade implements ActionListener{
	private JFrame janela;
	
	public OuvinteBtContabilidade(JFrame janela) {
		this.janela=janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		ControladorContabilidade controlador= new ControladorContabilidade();
		try {
			controlador.contabilidade();
			JOptionPane.showMessageDialog(janela, "Pdf gerado com sucesso!");
		} catch (NaoHaPedidosException e1) {
			JOptionPane.showMessageDialog(janela, e1.getMessage());
		}
		
	}

}
