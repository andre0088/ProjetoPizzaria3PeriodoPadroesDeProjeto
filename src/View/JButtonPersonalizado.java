package View;

import java.awt.Font;

import javax.swing.JButton;

public class JButtonPersonalizado extends JButton{
	public JButtonPersonalizado(String titulo,int a,int b,int c,int d) {
		this.setBounds(a, b, c, d);
		this.setText(titulo);
		this.setFont(new Font("Times",Font.PLAIN,20));
	}
}
