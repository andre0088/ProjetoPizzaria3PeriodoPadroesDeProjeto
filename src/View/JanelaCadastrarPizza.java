package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Controll.ControladorPizza;


public class JanelaCadastrarPizza extends JanelaPadraoEditarcadastrarPizza{
	private JanelaGerenciarPizza janela;
	public JanelaCadastrarPizza(JanelaGerenciarPizza janela) {
		this.janela=janela;
		this.setTitle("Cadastrar pizza");
		adicionarJButtons();
		this.setVisible(true);
	}
	
	public void adicionarJButtons() {
		JButton cadastrar = new JButtonPersonalizado("Cadastrar", 80, 390, 160, 30);
		cadastrar.setIcon(new ImageIcon("Salvar.png"));
		cadastrar.addActionListener(new OuvinteBtCadastrar(this,janela));
		JButton cancelar = new JButtonPersonalizado("Cancelar", 270, 390, 160, 30);
		cancelar.setIcon(new ImageIcon("Cancelar.png"));
		cancelar.addActionListener(new OuvinteCancelar(this));
		add(cadastrar);
		add(cancelar);
		
	}
	private class OuvinteBtCadastrar implements ActionListener{
		private JanelaCadastrarPizza janela;
		private JanelaGerenciarPizza janelaPizza;
		
		public OuvinteBtCadastrar(JanelaCadastrarPizza janela,JanelaGerenciarPizza janelaPizza) {
			this.janela=janela;
			this.janelaPizza=janelaPizza;
		}
		public void actionPerformed(ActionEvent e) {
			ControladorPizza controlador= new ControladorPizza();
			try {
				String preparacao=(String)preparo.getSelectedItem();
				String tamanho = (String)tamanhos.getSelectedItem();
				float preco=(Float.parseFloat(precoCompleto.getText()));
				controlador.cadastrarP(tipo.getText(),tamanho,id.getText(),preparacao,igredientes.getText(),preco);
				JOptionPane.showMessageDialog(null,"Pizza cadastrada com sucesso !");
				janela.dispose();
				janelaPizza.dispose();
				new JanelaGerenciarPizza().setLocationRelativeTo(null);
			} catch (DadosNaoPreenchidosException | PizzaJaExistenteException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
		
	}


}
