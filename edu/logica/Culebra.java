
package edu.logica;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

import edu.logica.Bonos.Bono;
import edu.logica.Bonos.BonoDisparar;
import edu.presentacion.Tablero;

public class Culebra extends Objeto implements Runnable{

	private Vector bonos;
	private Cabeza cabeza;
	private Thread hilo;
	private boolean pausado;
	private boolean invulnerable;
	private boolean descontrolado;
	private int timeHilo;
	private boolean izq,der;
	private Tablero tablero;
	
	
	private int vida;
	
	private int timeParalizado;
	private int timeAcelerado;
	private int timeInvulnerable;
	private Color color;
	
	public Culebra(Tablero tablero, Color color) {
		this.tablero= tablero;
		bonos= new Vector();
		cabeza= new Cabeza();
		this.color= color;
		cabeza.setColor(color);

		cabeza.getMiPoligono().moverEsquinas();

		timeHilo=40;
		vida=500;
		hilo= new Thread(this);
		hilo.start();
	}

	public Cabeza getCabeza() {
		return cabeza;
	}
	public void setCabeza(Cabeza cabeza) {
		this.cabeza = cabeza;
	}
	
	public void run() {
		while(true){
			if(!pausado){
			
				if(der)
					cabeza.getMiPoligono().rotarDer();
				else if(izq)
					cabeza.getMiPoligono().rotarIzq();
			
				cabeza.mover();
				tablero.repaint();
				try {
					hilo.sleep(timeHilo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public void dibujar(Graphics g){
		cabeza.dibujar(g); 
		
	/*	for(int i=0; i<bonos.size(); i++)
			((Bono)bonos.elementAt(i)).dibujar(g);
		*/
	}

	public boolean isDer() {
		return der;
	}
	public void setDer(boolean der) {
		if(descontrolado)
			izq=der;
		else
			this.der = der;
	}
	public boolean isIzq() {
		return izq;
	}
	public void setIzq(boolean izq) {
		if(descontrolado)
			der=izq;
		else
			this.izq = izq;
	}

	/**
	 * @param r1
	 */
	public boolean isColision(Rectangle r) {
		return cabeza.isColision(r);
	}
	
	public void addObstaculo(){
		tablero.addObstaculo(this);
	}

	public void addBombas(){
		int max=(int) Math.round((Math.random())*(7)+1);
		for(int i=0; i<max; i++)
			tablero.addBomba();
	}
	
	/**
	 * @param i
	 */
	public void robarCola(int i) {
		tablero.robarCola(this, i);
	}
	public Thread getHilo() {
		return hilo;
	}
	public void setHilo(Thread hilo) {
		this.hilo = hilo;
	}

	public void paralizar() {
		pausado=true;
		timeParalizado=400;
	}
	public void disminuirTime(){
		
		if(timeParalizado>0){
			timeParalizado--;
			if(timeParalizado<=0&&pausado)
				pausado=false;
		}
		
		timeAcelerado--;
		if(timeAcelerado<0){
			timeHilo=40;
			descontrolado=false;
		}
		
		timeInvulnerable--;
		if(timeInvulnerable<0)
			setInvulnerable(false);
	}
	
	
	public boolean isPausado() {
		return pausado;
	}
	public void setPausado(boolean pausado) {
		this.pausado = pausado;
	}
	public int getTimeHilo() {
		return timeHilo;
	}
	public void setTimeHilo(int timeHilo) {
		timeAcelerado=500;
		this.timeHilo = timeHilo;
	}
	public boolean isInvulnerable() {
		return invulnerable;
	}
	public void setInvulnerable(boolean invulnerable) {
		if(invulnerable)
			timeInvulnerable=400;
		
		this.invulnerable = invulnerable;
	}
	public boolean isDescontrolado() {
		return descontrolado;
	}
	public void setDescontrolado(boolean descontrolado) {
		this.descontrolado = descontrolado;
		if(descontrolado){
			timeAcelerado=400;
			timeHilo=20;
		}
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
		if(this.vida>500)
			this.vida=500;
		else if(this.vida<0)
		{
			this.vida=0;
			tablero.perdio(this);
		}
	}

	/**
	 * 
	 */
	public void iniciarApocalipsis() {
		tablero.apocalipsis(this);
		
	}

	/**
	 * @param disparar
	 */
	public void addBono(Bono bono) {
		System.out.println("bono añadido");
		if(bonos.size()<3)
			bonos.add(bono);
	}
	
	public void activarBono(){
		System.out.println("bono activado");
		if(bonos.size()>0)
			if(((Bono)bonos.firstElement()).activarBono(this))
				bonos.removeElementAt(0);
	}

	/**
	 * 
	 */
	public void disparar() {
		System.out.println("bono disparado");
		tablero.addBala(new Bala(getCabeza().getMiPoligono().getAngle(),
				getCabeza().getMiPoligono().getX(),
				getCabeza().getMiPoligono().getY()));
	}

	/**
	 * 
	 */
	public void paralizarOponente() {
		tablero.paralizarOponente(this);
	}
}
