/*
 * Created on 15/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.logica.Bonos;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import edu.logica.Culebra;
import edu.presentacion.Tablero;

/**
 * @author cReSpiis
 * 
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public abstract class Bono {
	

	public final static int P_ACELERAR=5;
	public final static int P_APOCALIPSIS=7;
	public final static int P_BOMBA=12;
	public final static int P_COLA=51;
	public final static int P_DESCONTROL=55;
	public final static int P_DISPARAR=63;
	public final static int P_INVULNERABLE=68;
	public final static int P_OBSTACULO=76;
	public final static int P_PARALIZADO=84;
	public final static int P_PARALIZAR=92;
	public final static int P_ROBA_COLA=100;
	public final static int P_GANA_VIDA=105;
	
	private int x, y;
	private Image imagen;
	
	public Bono() {
		x=(int) Math.round((Math.random())*(Tablero.ANCHO-100));
		y=(int) Math.round((Math.random())*(Tablero.LARGO-50));
		imagen= Toolkit.getDefaultToolkit().getImage("edu/imagenes/BonoTablero.gif");
	}

	public abstract void adicionarComportamiento(Culebra culebra);
	public abstract boolean activarBono(Culebra culebra);
	
	public void dibujar(Graphics g){
		g.drawImage(imagen,x,y,null);
	}
	public Rectangle getRectangulo(){
		return new Rectangle(x,y,100,50);
	}

	public Image getImagen() {
		return imagen;
	}
	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
