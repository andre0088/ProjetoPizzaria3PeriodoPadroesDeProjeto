package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public abstract class JanelaPadrao extends JFrame{
	public JanelaPadrao() {
		this.setResizable(false);
		this.setSize(500,500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		backGround();
		
	}
	
		public void backGround(){
			JLabel barra= new JLabel(new ImageIcon("barra.jpg"));
			barra.setBounds(0,0,500,145);
			add(barra);
		}

}
