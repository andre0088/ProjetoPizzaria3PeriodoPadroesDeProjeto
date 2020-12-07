package View;


import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public abstract class JanelaPadraoEditarcadastrarPizza extends JanelaPadrao{
	JTextField tipo;
	JTextField id;
	JComboBox<String> preparo;
	JComboBox<String> tamanhos;
	JTextArea igredientes;
	JTextField precoCompleto;
	
	public JanelaPadraoEditarcadastrarPizza() {
		adicionarJLabel();
		adicionarJTextFields();
		adicionarJComboBox();
		adicionarJTextArea();
	}
	public void adicionarJLabel() {
		JLabel tipo=new LabelPequeno("Tipo:", 20, 160, 50, 30);
		JLabel id=new LabelPequeno("id:", 20, 230, 50, 30);
		JLabel preparo=new LabelPequeno("Preparo:", 20, 300, 100, 30);
		JLabel tamanho=new LabelPequeno("Tamanho:", 260, 160, 80, 30);
		JLabel precoCompleto=new LabelPequeno("Preço total:", 260, 230, 120, 30);
		JLabel igredientes=new LabelPequeno("Igredientes:", 260, 300, 95, 30);
		add(tipo);
		add(id);
		add(preparo);
		add(igredientes);
		add(precoCompleto);
		add(tamanho);
		
	}
	
	public void adicionarJTextFields() {
		tipo= new JTextFieldPersonalizado(80, 160, 160, 30);
		id= new JTextFieldPersonalizado(80, 230, 160, 30);
		precoCompleto=new JTextFieldPersonalizado(360, 230, 120, 30);
		add(tipo);
		add(id);
		add(precoCompleto);
	}
	
	
	public void adicionarJComboBox() {
		String[]opcoes= {"Forno","Lenha"};
		preparo=new JComboBox<String>(opcoes);
		preparo.setBounds(105,300, 135, 30);
		String[]variedades= {"Pequena","Média","Grande"};
		tamanhos=new JComboBox<String>(variedades);
		tamanhos.setBounds(345, 160, 135, 30);
		add(preparo);
		add(tamanhos);
	}
	
	public void adicionarJTextArea() {
		igredientes = new JTextArea();
		igredientes.setBounds(365,300,115,60);
		igredientes.setLineWrap(true);
		add(igredientes);
	}
	
	

}
