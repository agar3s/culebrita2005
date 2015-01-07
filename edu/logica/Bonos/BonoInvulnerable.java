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

public class BonoInvulnerable extends BonoTemporal {
	public BonoInvulnerable() {
		super();
	}
	public void adicionarComportamiento(Culebra culebra) {
		culebra.setInvulnerable(true);
		
	}
	public void dibujar(Graphics g) {
		super.dibujar(g);
	}
	/* (non-Javadoc)
	 * @see edu.logica.Bonos.Bono#activarBono(edu.logica.Culebra)
	 */
	public boolean activarBono(Culebra culebra) {
		// TODO Auto-generated method stub
		return false;
	}

}
