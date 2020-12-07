package View;

import javax.swing.JTextField;

public class JTextFieldPersonalizado extends JTextField{
	public JTextFieldPersonalizado(int a,int b,int c,int d) {
		this.setBounds(a, b, c, d);
		this.setHorizontalAlignment(JTextField.CENTER);
	}
}
