package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class OuvinteBtLogout implements ActionListener{
		
	private JFrame janela;
		
		
	public OuvinteBtLogout(JFrame janela) {
			
		this.janela=janela;
		
	}

		
	public void actionPerformed(ActionEvent e) {
				
		new JanelaDeLogin().setLocationRelativeTo(janela);
				
		janela.dispose();
			
			
		}
		
	
}
