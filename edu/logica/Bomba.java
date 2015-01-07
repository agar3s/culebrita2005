/*
 * Created on 20/10/2005
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
public class Bomba extends Objeto{
	
	private int cambio;
	private Color color;
	
	public Bomba(){
		
		float x=(int) Math.round((Math.random())*(Tablero.ANCHO-100));
		float y=(int) Math.round((Math.random())*(Tablero.LARGO-50));
		
		setMiPoligono(new Poligono2D(Tablero.ANCHO,Tablero.LARGO,14));
		getMiPoligono().setX(x);
		getMiPoligono().setY(y);
		getMiPoligono().moverEsquinas();
	}
	
	public void incrementarCambio(){
		cambio++;
		if(cambio%25==0)
			color= Color.YELLOW;
		if(cambio%25==12)
			color=Color.BLACK;
	}
		
	public void dibujar(Graphics g){
		g.setColor(color);
		getMiPoligono().dibujar(g);
	}

}
