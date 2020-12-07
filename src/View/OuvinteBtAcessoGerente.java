package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class OuvinteBtAcessoGerente implements ActionListener{
	private JFrame janela;
	
	public OuvinteBtAcessoGerente(JFrame janela) {
		this.janela=janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		new JanelaGerenciarFuncionarios().setLocationRelativeTo(janela);
		janela.dispose();	
	}
}
