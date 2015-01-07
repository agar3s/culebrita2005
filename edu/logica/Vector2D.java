
package edu.logica;

public class Vector2D {

	private float x, y;
	private double angle;
	
	public static final float SPEED=5f;
	public static final int RADIO=20;
	public static final double GIRO= 135/RADIO;
	
	private boolean rotando;
	private int ancho, largo;
	
	private int xRex;
	private int yRex;
	
	public Vector2D(int ancho, int largo) {
		this.ancho=ancho;
		this.largo= largo;
	}
	
	public boolean mover(){
		
		x= (float) (x+Math.cos(Math.toRadians(angle))*SPEED);
		y= (float) (y+Math.sin(Math.toRadians(angle))*SPEED);
		
		
		boolean reboto=false;
		boolean borde=false;
		if(x-RADIO<=0||x+RADIO>=ancho){
			if(angle!=0&&angle!=180&&angle!=-180)
			{	setAngle(180-angle);
				borde=true;
			}
			else if(!reboto){
				setAngle(angle-90);
				reboto=true;
				borde=true;
			}
			
		}
		if(y-RADIO<0||y+RADIO>largo){
			if(angle!=90&&angle!=-90)
			{	setAngle(360-angle);
				borde=true;
			}
			else if(!reboto){
				setAngle(angle-90);
				reboto=true;
				borde=true;
			}
		}
		
		return borde;

	}
	
	public void cambioX(boolean algo){
		if(angle<=0){
			if(algo)
				setAngle(angle+90);
			else
				setAngle(angle-90);
		}else if(angle>0){
			if(algo)
				setAngle(angle-90);
			else
				setAngle(angle+90);
		}
	}
	
	public Vector2D puntoPartido(double angulo){
		Vector2D pDer= new Vector2D(ancho, largo);
		pDer.setAngle(angulo+angle);
		pDer.setX((float) (getX()+RADIO*Math.cos(Math.toRadians(angle+angulo))));
		pDer.setY((float) (getY()+RADIO*Math.sin(Math.toRadians(angle+angulo))));
		return pDer;
	}

	
	public void rotarDer(){
		setAngle(angle-GIRO);
		Vector2D der=puntoPartido(0);
		x=(float) (-RADIO*Math.cos(Math.toRadians(der.getAngle()))+der.getX());
		y=(float) (-RADIO*Math.sin(Math.toRadians(der.getAngle()))+der.getY());
		
	}
	
	public void rotarIzq(){
		setAngle(angle+GIRO);
		Vector2D izq=puntoPartido(180);
		x=(float) (-RADIO*Math.cos(Math.toRadians(izq.getAngle()))+izq.getX());
		y=(float) (-RADIO*Math.sin(Math.toRadians(izq.getAngle()))+izq.getY());
		
	}

	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
		if(this.angle>180){
			this.angle= this.angle-360;
		}
		if(this.angle<-180){
			this.angle=this.angle+360;
		}
	}
	public boolean isRotando() {
		return rotando;
	}
	public void setRotando(boolean rotando) {
		this.rotando = rotando;
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

}
