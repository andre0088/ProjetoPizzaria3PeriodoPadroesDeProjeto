package View;

import java.awt.Font;

import javax.swing.JLabel;

public class JLabelPersonalizado extends JLabel{
	public JLabelPersonalizado(String titulo,int a,int b,int c,int d) {
		this.setText(titulo);
		this.setBounds(a, b, c, d);
		this.setFont(new Font("Times new Roman", Font.PLAIN, 28));
	}
}
