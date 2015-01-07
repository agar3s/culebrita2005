/*
 * Created on 17/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.logica;

public class Punto {

	private float x, y;
	private double angulo;
	
	public Punto(float x, float y, double angulo) {
		this.x= x;
		this.y= y;
		this.angulo= angulo;
	}

	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public double getAngulo() {
		return angulo;
	}
	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}
}
