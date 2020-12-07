package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class OuvinteBtAcessoMotoboy implements ActionListener{
	private JFrame janela;
	
	public OuvinteBtAcessoMotoboy(JFrame janela){
		this.janela=janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		new JanelaMotoboy().setLocationRelativeTo(janela);
		janela.dispose();
	}

}
