/*
 * Created on 15/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.logica;

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

public class Cola extends Objeto {
	
	private Cola cola;
	private Vector huella;

	public Cola() {
		setMiPoligono(new Poligono2D(Tablero.ANCHO, Tablero.LARGO,4));
		huella= new Vector();
		for(int i=0; i<Cabeza.contadorHuella; i++)
			addHuella();
	}
	
	private void addHuella() {
		Punto punto= new Punto(getMiPoligono().getX(),
				getMiPoligono().getY(),
				getMiPoligono().getAngle());
		
		huella.insertElementAt(punto,0);
		
	}
	public void moverColas(){
		
		if(cola!=null)
			cola.seguirHuella((Punto)huella.lastElement());
		
		huella.remove(huella.lastElement());
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
	}
	
	public void seguirHuella(Punto punto){
		
		getMiPoligono().setX(punto.getX());
		getMiPoligono().setY(punto.getY());
		getMiPoligono().setAngle(punto.getAngulo());
		getMiPoligono().moverEsquinas();
		addHuella();
		
		moverColas();
		
	}
	
	
	public void dibujar(Graphics g){
		if(cola!=null)
			cola.dibujar(g);
		getMiPoligono().dibujar(g);
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

	public Cola getCola() {
		return cola;
	}
	public void setCola(Cola cola) {
		this.cola = cola;
	}

	/**
	 * 
	 */
	public void removerCola() {
		if(cola!=null){	
			if(cola.getCola()!=null)
				cola.removerCola();
			else
				cola=null;
		}
	}
}
