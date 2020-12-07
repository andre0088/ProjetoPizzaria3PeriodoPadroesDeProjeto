package View;

import java.awt.Font;

import javax.swing.JLabel;

public class LabelPequeno extends JLabel{
	public LabelPequeno(String titulo,int a,int b,int c,int d) {
		this.setText(titulo);
		this.setBounds(a, b, c, d);
		this.setFont(new Font("Times new Roman", Font.PLAIN, 20));
	}
}
