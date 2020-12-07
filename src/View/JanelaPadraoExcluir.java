package View;

import javax.swing.JLabel;
import javax.swing.JTextField;

public abstract class JanelaPadraoExcluir extends JanelaPadrao{
	JTextField id;
	
	public JanelaPadraoExcluir() {
		adicionarJLabel();
		adicionarJTextFields();
	}
	
	public void adicionarJLabel() {
		JLabel id= new LabelPequeno("Código de ID", 190, 200, 120, 50);
		add(id);
	}
	
	public void adicionarJTextFields() {
		id= new JTextFieldPersonalizado(150,280,190,30);
		add(id);
	}

}
