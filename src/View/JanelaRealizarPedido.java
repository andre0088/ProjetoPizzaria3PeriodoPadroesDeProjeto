package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Controll.ControladorPedido;
import Controll.ControladorPizza;

public class JanelaRealizarPedido extends JanelaPadraoEditarRealizarPedido{
	private JanelaGerenciarPedidos j;
	
	public JanelaRealizarPedido(JanelaGerenciarPedidos j) {
		this.j=j;
		this.setTitle("Realizar pedido");
		adicionarJButtons();
		this.setVisible(true);
	}
	public void adicionarJButtons() {
		JButton concluir = new JButtonPersonalizado("Concluir", 90, 390, 150, 30);
		concluir.setIcon(new ImageIcon("OK.png"));
		concluir.addActionListener(new OuvinteBtConcluir(this, j));
		JButton cancelar = new JButtonPersonalizado("Cancelar", 280, 390, 150, 30);
		cancelar.setIcon(new ImageIcon("Cancelar.png"));
		cancelar.addActionListener(new OuvinteCancelar(this));
		add(concluir);
		add(cancelar);
	}
	
	private class OuvinteBtConcluir implements ActionListener{
		private JanelaRealizarPedido janela;
		private JanelaGerenciarPedidos j;
		
		public OuvinteBtConcluir(JanelaRealizarPedido janela,JanelaGerenciarPedidos j) {
			this.janela=janela;
			this.j=j;
		}
		
		public void actionPerformed(ActionEvent e) {
			ControladorPedido controlador= new ControladorPedido();
			ControladorPizza control = new ControladorPizza();
			String stats=(String)status.getSelectedItem();
			try {
				float preco=0;
				String saborT=(String) saborTamanhos.getSelectedItem();
				String[] st= saborT.split("-");
				String sabor = st[0];
				String tamanho=st[1];
				preco=control.retornarPrecoPizza(sabor, tamanho);
				int qtd=0;
				if(qtdPizzas.getText().equals("")==false) {
					qtd=Integer.parseInt(qtdPizzas.getText());
				}
				controlador.realizarP(id.getText(),cpf.getText(),qtd,sabor,tamanho,stats,preco*qtd);
				JOptionPane.showMessageDialog(null,"Pedido realizado com sucesso !");
				janela.dispose();
				j.dispose();
				new JanelaGerenciarPedidos();
			} catch (DadosNaoPreenchidosException | PedidoJaExistenteException | CpfInvalidoException | SaborInexistenteException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			
		}
		
	}
}
