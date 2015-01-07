/*
 * Created on 20/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.logica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import edu.presentacion.Tablero;

/**
 * @author Invitado
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Bala extends Objeto {
	
	
	private Image imagen;
	
	public Bala(double angulo, float x, float y){
		setMiPoligono(new Poligono2D(Tablero.ANCHO,Tablero.LARGO,4));
		getMiPoligono().setAngle(angulo);
		getMiPoligono().setX(x);
		getMiPoligono().setY(y);
		x=getMiPoligono().puntoPartido(0).getX();
		y=getMiPoligono().puntoPartido(0).getY();
		getMiPoligono().setX(x);
		getMiPoligono().setY(y);
		getMiPoligono().moverEsquinas();
		imagen= imagen= Toolkit.getDefaultToolkit().getImage("edu/imagenes/bala.gif");
	}

	
	public Rectangle getRectangulo(){
		return new Rectangle((int)getMiPoligono().getX()-5,
					(int)getMiPoligono().getY()-5, 10, 10);
	}
	
	public void dibujar(Graphics g){
		g.setColor(Color.blue);
		g.fillOval((int)getMiPoligono().getX()-5,(int)getMiPoligono().getY()-5,10,10);
		g.drawImage(imagen,(int)getMiPoligono().getX()-5,(int)getMiPoligono().getY()-5,null);
	}


	/**
	 * @param culebra1
	 */
	public void verificarColision(Culebra culebra) {
		Rectangle r= culebra.getCabeza().
		getMiPoligono().getRectangulo();
		
		if(culebra.isInvulnerable()){
		
		}else if(culebra.isColision(getRectangulo())){
			culebra.setVida(culebra.getVida()-2);
		}
		
	}

}
