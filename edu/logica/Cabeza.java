/*
 * Created on 15/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.logica;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

import edu.presentacion.Tablero;


/**
 * @author cReSpiis
 * 
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class Cabeza extends Objeto {

	private Cola cola;
	private Vector huella;
	//(radio*2)/velocidad de vector2D
	public static final int contadorHuella=8;
	private Color color;
	
	private int colas;
	
	
	public Cabeza() {
		setMiPoligono(new Poligono2D(Tablero.ANCHO, Tablero.LARGO,5));
		huella= new Vector();
		for(int i=0; i<contadorHuella; i++)
			addHuella();
	}
	
	public boolean mover(){
		boolean b=getMiPoligono().mover();
		addHuella();
		moverColas();
		return b;
	}

	private void addHuella() {
		Punto punto= new Punto(getMiPoligono().getX(),
				getMiPoligono().getY(),
				getMiPoligono().getAngle());
		huella.insertElementAt(punto,0);
			
	}

	public void addCola(){
		
		if(this.cola==null){
			cola= new Cola();
			double angulo= getMiPoligono().getAngle()+180;
			angulo= Math.toRadians(angulo);
			float x= getMiPoligono().getX();
			float y= getMiPoligono().getY();
			x= (float) (x+2*Vector2D.RADIO*Math.cos(angulo));
			y= (float) (y+2*Vector2D.RADIO*Math.sin(angulo));
			cola.getMiPoligono().setX(x);
			cola.getMiPoligono().setY(y);
			cola.getMiPoligono().setAngle(Math.toDegrees(angulo)+180);
			cola.getMiPoligono().moverEsquinas();
		}else{
			cola.addCola();
		}
		colas++;
	}
	
	public void removerCola(){
		if(cola!=null){	
			if(cola.getCola()!=null)
				cola.removerCola();
			else
				cola=null;
			colas--;
		}
	}
	
	public void moverColas(){
		
		if(cola!=null)
			cola.seguirHuella((Punto)huella.lastElement());
		
		huella.remove(huella.lastElement());
	}

	public void dibujar(Graphics g){
		g.setColor(color);
		if(cola!=null)
			cola.dibujar(g);
		getMiPoligono().dibujar(g);
	}


	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @param r
	 * @return
	 */
	public boolean isColision(Rectangle r) {
		boolean colision=false;
		if(r.intersects(getMiPoligono().getRectangulo())){
			colision=true;
		}else if(cola!=null)
			colision= cola.isColision(r);
		
		return colision;
	}

	
	public int getColas() {
		return colas;
	}
	public void setColas(int colas) {
		this.colas = colas;
	}
}
