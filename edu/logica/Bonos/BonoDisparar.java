/*
 * Created on 15/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.logica.Bonos;

import java.awt.Graphics;

import edu.logica.Culebra;

/**
 * @author cReSpiis
 * 
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class BonoDisparar extends Bono {
	
	private int balas;
	
	public BonoDisparar() {
		super();
		balas=5;
	}
	public void adicionarComportamiento(Culebra culebra) {
		culebra.addBono(this);
	}
	
	public boolean activarBono(Culebra culebra){
		boolean finalizado=false;
		balas--;
		culebra.disparar();
		if(balas==0)
			finalizado=true;
		return finalizado;
	}
	
	public void dibujar(Graphics g) {
		super.dibujar(g);
	}
}
