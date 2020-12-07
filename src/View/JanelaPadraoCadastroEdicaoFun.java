package View;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controll.ControladorFuncionario;

public abstract class JanelaPadraoCadastroEdicaoFun extends JanelaPadrao{
	
	JTextField nome;
	JTextField login;
	JTextField id;
	JPasswordField senha;
	JComboBox<String>cargoMultiplo;
	
	public JanelaPadraoCadastroEdicaoFun() {
		this.setTitle("Cadastro e edição de funcionários");
		adicionarJLabel();
		adicionarJTextFields();
		adicionarJComboBox();
	}
	public void adicionarJLabel() {
		JLabel nome= new LabelPequeno("Nome:", 25, 140, 80, 50);
		JLabel login= new LabelPequeno("Login:",25 , 210, 80, 50);
		JLabel cargo= new LabelPequeno("Cargo:",25, 280, 80, 50);
		JLabel senha= new LabelPequeno("ID:",265, 140, 80, 50);
		JLabel id= new LabelPequeno("Senha:",265, 210, 80, 50);
		JLabel figura1=new JLabel(new ImageIcon("Funcionario.png"));
		figura1.setBounds(285, 295, 40, 40);
		JLabel figura2=new JLabel(new ImageIcon("Funcionaria.png"));
		figura2.setBounds(335, 295, 40, 40);
		add(nome);
		add(login);
		add(cargo);
		add(id);
		add(senha);
		add(figura1);
		add(figura2);
	}

	public void adicionarJTextFields() {
		nome= new JTextFieldPersonalizado(115, 155, 130, 30);
		login= new JTextFieldPersonalizado(115, 225, 130, 30);
		id= new JTextFieldPersonalizado(300, 155, 155, 30);
		senha= new JPasswordField();
		senha.setBounds(325, 225, 130, 30);
		senha.setHorizontalAlignment(JPasswordField.CENTER);
		add(nome);
		add(login);
		add(id);
		add(senha);
	}
	public void adicionarJComboBox() {
		ControladorFuncionario control = new ControladorFuncionario();
		String[] opcoesGerais= {"Gerente","Limpeza","Atendente","Pizzaiollo","Motoboy"};
		String[] unicaOpcao= {"Gerente"};
		if(control.listarF().size()==1) {
			cargoMultiplo=new JComboBox<String>(unicaOpcao);
		}else {
			cargoMultiplo=new JComboBox<String>(opcoesGerais);
		}
		cargoMultiplo.setBounds(115,290,130,60);
		cargoMultiplo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String opcao= (String)cargoMultiplo.getSelectedItem();
				if(opcao.equals("Limpeza")) {
					login.setText("");
					senha.setText("");
					login.setEditable(false);
					senha.setEditable(false);
				}else {
					login.setEditable(true);
					senha.setEditable(true);
				}
			}
		});

		add(cargoMultiplo);

	}

	
}
