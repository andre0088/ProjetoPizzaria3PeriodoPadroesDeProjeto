package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class OuvinteBtAcessoAtendente implements ActionListener{
	private JFrame janela;
	
	public OuvinteBtAcessoAtendente(JFrame janela) {
		this.janela=janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		new JanelaGerenciarClientes().setLocationRelativeTo(janela);
		janela.dispose();	
	}
}
