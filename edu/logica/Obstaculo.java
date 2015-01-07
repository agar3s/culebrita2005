/*
 * Created on 17/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.logica;

import java.awt.Color;
import java.awt.Graphics;

import edu.presentacion.Tablero;

/**
 * @author Invitado
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Obstaculo extends Objeto {
	
	private Culebra culebra;

	public Obstaculo(Culebra culebra, float x, float y){
	
		this.culebra= culebra;
		setMiPoligono(new Poligono2D(Tablero.ANCHO,Tablero.LARGO,6));
		getMiPoligono().setX(x);
		getMiPoligono().setY(y);
		getMiPoligono().moverEsquinas();
	}
	
	public void dibujar(Graphics g){
		g.setColor(Color.darkGray);
		getMiPoligono().dibujar(g);
	}
	
	
	public Culebra getCulebra() {
		return culebra;
	}
	public void setCulebra(Culebra culebra) {
		this.culebra = culebra;
	}
}
