package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class OuvinteCancelar implements ActionListener{

private JFrame janela;
	
	public OuvinteCancelar(JFrame janela) {
		this.janela=janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		janela.dispose();
		
	}
	
}
