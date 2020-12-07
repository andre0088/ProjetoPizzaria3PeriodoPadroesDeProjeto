package View;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Controll.ControladorPizza;

public class JanelaPadraoEditarRealizarPedido extends JanelaPadrao{
	 JTextField id;
	 JTextField cpf;
	 JTextField qtdPizzas;
	 JComboBox<String> saborTamanhos;
	 JComboBox<String> status;
	 
	public JanelaPadraoEditarRealizarPedido() {
		adicionarJLabel();
		adicionarJComboBox();
		adicionarJTextFields();
	}
	public void adicionarJLabel() {
		JLabel id = new LabelPequeno("ID do pedido:", 25, 170, 120, 30);
		JLabel cpf = new LabelPequeno("CPF cliente:", 25, 230, 100, 30);
		JLabel completo = new LabelPequeno("Sabor:", 255, 170, 100, 30);
		JLabel status = new LabelPequeno("Status:", 255, 230, 100, 30);
		JLabel qtdPizzas = new LabelPequeno("Qtd Pizzas:", 255, 270,100, 80);
		add(id);
		add(qtdPizzas);
		add(cpf);
		add(completo);
		add(status);
	}
	public void adicionarJTextFields() {
		id= new JTextFieldPersonalizado(145, 170, 90, 30);
		cpf= new JTextFieldPersonalizado(135, 230, 100, 30);
		qtdPizzas= new JTextFieldPersonalizado(360, 295, 119, 30);
		add(id);
		add(cpf);
		add(qtdPizzas);
		
	}
	public void adicionarJComboBox() {
		ControladorPizza c = new ControladorPizza();
		String[] variedades= new String[c.listarP().size()];
		for(int i=0;i<c.listarP().size();i++) {
			variedades[i]= c.listarP().get(i).getTipo()+"-"+c.listarP().get(i).getTamanho();
		}
		
		saborTamanhos=new JComboBox<String>(variedades);
		saborTamanhos.setBounds(335,170,145,30);
		String[]opcoes= new String[]{"Pedido feito"};
		status=new JComboBox<String>(opcoes);
		status.setBounds(335,233,145,30);
		add(saborTamanhos);
		add(status);
	}
	

}
