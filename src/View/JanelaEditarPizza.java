package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Controll.ControladorPizza;

public class JanelaEditarPizza extends JanelaPadraoEditarcadastrarPizza{
	private JanelaGerenciarPizza janela;
	public JanelaEditarPizza(JanelaGerenciarPizza janela) {
		this.janela=janela;
		this.setTitle("Editar pizza");
		adicionarComponentes();
		this.setVisible(true);
	}
	
	public void adicionarComponentes() {
		JLabel interrogacao=new JLabel(new ImageIcon("Interrogacao.png"));
		interrogacao.setBounds(20,10,30,30);
		interrogacao.setToolTipText("Digite o Id da pizza que você deseja editar.");
		JButton cadastrar = new JButtonPersonalizado("Salvar", 80, 390, 160, 30);
		cadastrar.setIcon(new ImageIcon("Salvar.png"));
		cadastrar.addActionListener(new OuvinteBtCadastrar(this,janela));
		JButton cancelar = new JButtonPersonalizado("Cancelar", 270, 390, 160, 30);
		cancelar.setIcon(new ImageIcon("Cancelar.png"));
		cancelar.addActionListener(new OuvinteCancelar(this));
		add(cadastrar);
		add(cancelar);
		add(interrogacao);
		
	}
	private class OuvinteBtCadastrar implements ActionListener{
		private JanelaEditarPizza janela;
		private JanelaGerenciarPizza janelaPizza;
		
		public OuvinteBtCadastrar(JanelaEditarPizza janela,JanelaGerenciarPizza janelaPizza) {
			this.janela=janela;
			this.janelaPizza=janelaPizza;
		}
		public void actionPerformed(ActionEvent e) {
			ControladorPizza controlador= new ControladorPizza();
			try {
				String prep=(String)preparo.getSelectedItem();
				float preco=(Float.parseFloat(precoCompleto.getText()));
				String tam=(String) tamanhos.getSelectedItem();
				controlador.editarP(id.getText(),prep,igredientes.getText(),preco,tam,tipo.getText());
				JOptionPane.showMessageDialog(null,"Pizza editada com sucesso !");
				janela.dispose();
				janelaPizza.dispose();
				new JanelaGerenciarPizza().setLocationRelativeTo(null);
			} catch (DadosNaoPreenchidosException | IdInexistenteException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			
		}
		
	}

}
