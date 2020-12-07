package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class OuvinteBtAcessoPizzaiollo implements ActionListener{
	private JFrame janela;
	
	public OuvinteBtAcessoPizzaiollo(JFrame janela){
		this.janela=janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		new JanelaPizzaiollo().setLocationRelativeTo(janela);;
		janela.dispose();
		
	}

}
