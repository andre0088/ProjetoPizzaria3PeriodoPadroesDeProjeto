package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.scene.layout.Background;

public abstract class JanelaPadraoGerencia extends JFrame{
	public JanelaPadraoGerencia() {
		this.setResizable(false);
		this.setSize(1100,700);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
	}
	

}
