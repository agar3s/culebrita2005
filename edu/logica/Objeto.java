
package edu.logica;


public abstract class Objeto {

	private Poligono2D miPoligono;

	public Objeto() {
		
	}	
	
	public boolean mover(){
		boolean b=miPoligono.mover();
		return b;
	}
	public void rotarIzq(){
		miPoligono.rotarIzq();
	}
	public void rotarDer(){
		miPoligono.rotarDer();
	}


	public Poligono2D getMiPoligono() {
		return miPoligono;
	}
	public void setMiPoligono(Poligono2D miPoligono) {
		this.miPoligono = miPoligono;
	}
}
