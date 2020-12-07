package Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import BD.CentralPedidos;
import BD.CentralPizzas;
import BD.PersistenciaPedido;
import BD.PersistenciaPizza;
import View.NaoHaPedidosException;

public class Contabilidade {
	
	public void gerarContabilidade() throws NaoHaPedidosException{
		PersistenciaPizza perPiz = new PersistenciaPizza();
		CentralPizzas centralPizzas = perPiz.recuperarCentral();
		PersistenciaPedido perPed = new PersistenciaPedido();
		CentralPedidos centralPedidos = perPed.recuperarCentral();
		
		if(centralPedidos.getHistoricoDePedidos().isEmpty()) {
			throw new NaoHaPedidosException();
		}
		
		int cont=0;
		
		for(Pedido pedido:centralPedidos.getHistoricoDePedidos()) {
			if(pedido.getStatus().equals("Entregue")) {
				cont++;
				break;
			}
		}
		
		if(cont==0) {
			throw new NaoHaPedidosException();
		}
		
		int lucroMensal=0;
		int qtdPizzaMes=0;
		
		Date dataRelatorio = new Date();
		GregorianCalendar dataCalRelatorio= new GregorianCalendar();
		dataCalRelatorio.setTime(dataRelatorio);
		Format format= new SimpleDateFormat("MM");
		int mesRelatorio= Integer.parseInt(format.format(dataCalRelatorio.getTime()));
		
		for(int i=0;i<centralPedidos.getHistoricoDePedidos().size();i++) {
			if(centralPedidos.getHistoricoDePedidos().get(i).getStatus().equals("Entregue")) {
				GregorianCalendar dataCalPedido= new GregorianCalendar();
				dataCalPedido.setTime(centralPedidos.getHistoricoDePedidos().get(i).getData());
				Format formata= new SimpleDateFormat("MM");
				int mesPedido= Integer.parseInt(formata.format(dataCalPedido.getTime()));
				if(mesRelatorio==mesPedido) {
					lucroMensal+=centralPedidos.getHistoricoDePedidos().get(i).getPrecoFinal();
					qtdPizzaMes+=centralPedidos.getHistoricoDePedidos().get(i).getQtdPizzas();
				}
			}
		}
		
		int quantidade=0;
		int primeiroLugar=0;
		String saborMaisVendido="";
		int contador=0;
		for(Pizza p: centralPizzas.getPizzas()) {
	    	for(Pedido ped:centralPedidos.getHistoricoDePedidos()) {
	    		GregorianCalendar dataCalPedido= new GregorianCalendar();
				dataCalPedido.setTime(ped.getData());
				Format formata= new SimpleDateFormat("MM");
				int mesPedido= Integer.parseInt(formata.format(dataCalPedido.getTime()));
	    		if(mesPedido==mesRelatorio && p.getTipo().equals(ped.getPizza().getTipo())) {
	    			contador+=ped.getQtdPizzas();
	    			quantidade=contador;
	    		}
	    	}
	    	if(primeiroLugar<quantidade) {
	    		primeiroLugar=quantidade;
	    		saborMaisVendido=p.getTipo();
	    		
	    	}
	    	contador=0;
	    }
		
		Document doc = new Document(PageSize.A4, 72, 72, 72, 72);
		try{
			PdfWriter.getInstance(doc, new FileOutputStream("Contabilidade Mensal.pdf"));
			doc.open();
			doc.add(new Paragraph("Relatorio de Contabilidade: Mês ("+mesRelatorio+")"));
			doc.add(new Paragraph("Lucro Mensal: "+ lucroMensal+" R$."));
			doc.add(new Paragraph("Quantidades de pizzas vendidas no mês: Total de "+qtdPizzaMes+" Pizzas."));
			doc.add(new Paragraph("Pizza mais vendida do mês: "+saborMaisVendido+"."));
			doc.add(new Paragraph("--------------------------------------------------------"));
			doc.close();
		}catch(FileNotFoundException | DocumentException e) {}
		
		
	}
	
}
