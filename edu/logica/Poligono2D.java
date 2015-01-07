/*
 * Created on 15/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.logica;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * @author cReSpiis
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Poligono2D extends Vector2D{
	
	private Vector2D[] esquinas;

	public Poligono2D(int ancho, int largo, int n) {
		super(ancho, largo);
		esquinas= new Vector2D[n];
		for(int i=0; i<esquinas.length; i++)
			esquinas[i]= new Vector2D(ancho, largo);
		moverEsquinas();
		
	}
	
	public void dibujar(Graphics g){

		
		int x[]= new int[esquinas.length+1];
		int y[]= new int[esquinas.length+1];

		for(int i=0; i<esquinas.length; i++){
			x[i]=(int) esquinas[i].getX();
			y[i]=(int) esquinas[i].getY();
			g.fillOval((int) esquinas[i].getX()-2,
					(int) esquinas[i].getY()-2,
					4,4);

		}
		g.fillOval((int) esquinas[0].getX()-2,
				(int) esquinas[0].getY()-2,
				4,4);
		
		x[esquinas.length]=(int) esquinas[0].getX();
		y[esquinas.length]=(int) esquinas[0].getY();

		g.fillPolygon(x,y,esquinas.length+1);

		
	}
	
	public void moverEsquinas(){
		
		for(int i=0; i<esquinas.length; i++)
			esquinas[i]= puntoPartido((360/esquinas.length)*i);
		
	}
	
	public boolean mover(){
		boolean b= super.mover();
		moverEsquinas();
		
		return b;
	}
	public void rotarIzq(){
		super.rotarIzq();
	}
	
	public void rotarDer(){		
		super.rotarDer();
	}
	
	public Rectangle getRectangulo(){
		return new Rectangle((int)(getX()-RADIO*0.707),
				(int)(getY()-RADIO*0.707),
				(int)(RADIO*1.414),(int) (RADIO*1.414));
	}
	
	
}
