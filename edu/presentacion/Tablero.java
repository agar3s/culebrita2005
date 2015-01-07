/*
 * Created on 17/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.presentacion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.logica.Bala;
import edu.logica.Bomba;
import edu.logica.Culebra;
import edu.logica.FabricaBonus;
import edu.logica.Obstaculo;
import edu.logica.Bonos.Bono;

/**
 * @author Invitado
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Tablero extends JPanel
	implements KeyListener, MouseListener,Runnable{
	
	private Culebra culebra1, culebra2;
	public final static int ANCHO=1000;
	public final static int LARGO= 550;
	
	private Thread miHilo;
	private FabricaBonus fabrica;
	private Bono bono1;
	private Bono bono2;
	private Bono bono3;
	private int timeBono;
	
	private Vector obstaculos;
	private Vector bombas;
	private Vector balas;
	
	private int timeEfecto;
	
	private Color fondo;
	
	
	public Tablero(){
		obstaculos= new Vector();
		bombas= new Vector();
		balas= new Vector();
		iniciarCulebra1();
		iniciarCulebra2();
		
		miHilo= new Thread(this);
		miHilo.start();
		fabrica= new FabricaBonus();

		fondo=Color.GRAY;
		
		addKeyListener(this);
		addMouseListener(this);
		setDoubleBuffered(true);
	}

	public void iniciarCulebra1(){
		culebra1= new Culebra(this, Color.CYAN);
		culebra1.getCabeza().getMiPoligono().setAngle(180);
		culebra1.getCabeza().getMiPoligono().setX(400);
		culebra1.getCabeza().getMiPoligono().setY(300);
	}
	public void iniciarCulebra2(){
		culebra2= new Culebra(this, Color.GREEN);
		culebra2.getCabeza().getMiPoligono().setAngle(90);
		culebra2.getCabeza().getMiPoligono().setX(700);
		culebra2.getCabeza().getMiPoligono().setY(300);
	}
	
	public void addObstaculo(Culebra culebra){
		obstaculos.add(new Obstaculo(culebra,
				culebra.getCabeza().getMiPoligono().getX(),
				culebra.getCabeza().getMiPoligono().getY()));
	}
	
	
	public void addBomba(){
		bombas.add(new Bomba());
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		requestFocus();
		
		g.setColor(fondo);
		g.fillRect(0,0,ANCHO,LARGO);
		
		
		culebra1.dibujar(g);
		culebra2.dibujar(g);
		if(bono1!=null){
			bono1.dibujar(g);
		}
		if(bono2!=null){
			bono2.dibujar(g);
		}
		if(bono3!=null){
			bono3.dibujar(g);
		}
		for(int i=0; i<obstaculos.size(); i++)
			((Obstaculo)obstaculos.elementAt(i)).dibujar(g);

		for(int i=0; i<bombas.size(); i++)
			((Bomba)bombas.elementAt(i)).dibujar(g);
		
		for(int i=0; i<balas.size(); i++)
			((Bala)balas.elementAt(i)).dibujar(g);
		
		g.setColor(Color.black);
		g.drawRect(1,1,ANCHO-2,LARGO-2);
		
		
		g.setColor(Color.red);
		g.fill3DRect(10,LARGO+4,250,40,true);
		g.fill3DRect(ANCHO/2,LARGO+4,250,40,true);
		
		g.setColor(Color.BLUE);
		g.fill3DRect(10,LARGO+4,culebra1.getVida()/2,40,true);
		g.fill3DRect(ANCHO/2,LARGO+4,culebra2.getVida()/2,40,true);
		
	}
	
	public void verificarColision(){

		verificaCulebra1();
		verificaCulebra2();

	}
	
	public void verificaCulebra1(){
		Rectangle r1= culebra1.getCabeza().
		getMiPoligono().getRectangulo();
		if(culebra1.isInvulnerable()){
			culebra1.getCabeza().setColor(new Color(0,128,0));
		}else if(culebra2.isColision(r1)){
			culebra1.setVida(culebra1.getVida()-1);
			culebra1.getCabeza().getMiPoligono().setAngle(
					culebra1.getCabeza().getMiPoligono().getAngle()+180);
			culebra1.getCabeza().setColor(Color.red);
		}else if(isTropezo(culebra1, r1)){
			culebra1.getCabeza().setColor(Color.red);
		}else
			culebra1.getCabeza().setColor(Color.cyan);
		if(bono1!=null&&r1.intersects(bono1.getRectangulo())){
			//culebra1.
			System.out.println("culebra1 cogio bono: "+bono1);
			bono1.adicionarComportamiento(culebra1);
			bono1=null;
		}
		if(bono2!=null&&r1.intersects(bono2.getRectangulo())){
			//culebra1.
			System.out.println("culebra1 cogio bono: "+bono2);
			bono2.adicionarComportamiento(culebra1);
			bono2=null;
		}
		if(bono3!=null&&r1.intersects(bono3.getRectangulo())){
			//culebra1.
			System.out.println("culebra1 cogio bono: "+bono3);
			bono3.adicionarComportamiento(culebra1);
			bono3=null;
		}
	}

	private boolean isTropezo(Culebra culebra, Rectangle r) {
		
		boolean colision=false;
		for(int i=0; i<obstaculos.size(); i++){
			if(((Obstaculo)obstaculos.elementAt(i)).getCulebra()!=culebra){
				if(r.intersects(
						((Obstaculo)obstaculos.elementAt(i)).
						getMiPoligono().getRectangulo())){
					culebra.setVida(culebra.getVida()-3);
					colision=true;
					break;
					}
			}
		}
		for(int i=0; i<bombas.size(); i++){
			if(r.intersects(
					((Bomba)bombas.elementAt(i)).
					getMiPoligono().getRectangulo())){
				culebra.setVida(culebra.getVida()-125);
				culebra.getCabeza().removerCola();
				culebra.getCabeza().removerCola();
				culebra.getCabeza().removerCola();
				bombas.removeElementAt(i);
				colision=true;
				break;
			}
		}		
		
		return colision;
	}

	public void verificaCulebra2(){
		Rectangle r2= culebra2.getCabeza().
		getMiPoligono().getRectangulo();
		
		if(culebra2.isInvulnerable()){
			culebra2.getCabeza().setColor(new Color(0,128,255));
		}else if(culebra1.isColision(r2)){
			culebra2.setVida(culebra2.getVida()-1);
			culebra2.getCabeza().getMiPoligono().setAngle(
					culebra2.getCabeza().getMiPoligono().getAngle()+180);
			culebra2.getCabeza().setColor(Color.red);
		}else if(isTropezo(culebra2, r2)){
			culebra2.getCabeza().setColor(Color.red);
		}else
			culebra2.getCabeza().setColor(Color.green);	
		
		if(bono1!=null&&r2.intersects(bono1.getRectangulo())){
			System.out.println("culebra2 cogio bono: "+bono1);
			bono1.adicionarComportamiento(culebra2);
			bono1=null;
		}
		if(bono2!=null&&r2.intersects(bono2.getRectangulo())){
			System.out.println("culebra2 cogio bono: "+bono2);
			bono2.adicionarComportamiento(culebra2);
			bono2=null;
		}
		if(bono3!=null&&r2.intersects(bono3.getRectangulo())){
			System.out.println("culebra2 cogio bono: "+bono3);
			bono3.adicionarComportamiento(culebra2);
			bono3=null;
		}
		
	}
	
	public void generarBono(){
		timeBono++;
		if(timeBono%250==0){
			bono1= fabrica.crearBono();
		}
		if((timeBono+80)%250==0){
			bono2= fabrica.crearBono();
		}
		if((timeBono+160)%250==0){
			bono3= fabrica.crearBono();
		}
	}

	public static void main(String[] args) {

		JFrame ventana= new JFrame("Snake");
		ventana.setSize(ANCHO+50,LARGO+100);
		ventana.getContentPane().add(new Tablero());
		
		ventana.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		ventana.setVisible(true);
	}

	public void keyTyped(KeyEvent arg0) {
	
	}

	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode()== KeyEvent.VK_LEFT){
			culebra1.setDer(true);
			culebra1.setIzq(false);
		}else if(ke.getKeyCode()== KeyEvent.VK_RIGHT){
			culebra1.setDer(false);
			culebra1.setIzq(true);
		}

	}

	public void keyReleased(KeyEvent ke) {
		if(ke.getKeyCode()== KeyEvent.VK_LEFT){
			culebra1.setDer(false);
			culebra1.setIzq(false);
		}else if(ke.getKeyCode()== KeyEvent.VK_RIGHT){
			culebra1.setDer(false);
			culebra1.setIzq(false);
		}else if(ke.getKeyCode()== KeyEvent.VK_UP){
			culebra1.activarBono();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent me) {
		if(me.getButton()==1){
			//culebra1.setRotando(true);
			culebra2.setDer(true);
			culebra2.setIzq(false);
		}else if(me.getButton()==3){
			culebra2.setDer(false);
			culebra2.setIzq(true);
		}
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent me) {
		if(me.getButton()==1){
			//culebra1.setRotando(true);
			culebra2.setDer(false);
			culebra2.setIzq(false);
		}else if(me.getButton()==3){
			culebra2.setDer(false);
			culebra2.setIzq(false);
		}else if(me.getButton()==2){
			culebra2.activarBono();
		}
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while(true){
			verificarColision();
			generarBono();
			culebra1.disminuirTime();
			culebra2.disminuirTime();
			
			actualizar();
			try {
				miHilo.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void actualizar(){
		
		for(int i=balas.size()-1; i>=0; i--){
			if(((Bala)balas.elementAt(i)).mover()){
				balas.removeElementAt(i);
			}else{
				((Bala)balas.elementAt(i)).verificarColision(culebra1);
				((Bala)balas.elementAt(i)).verificarColision(culebra2);
			}
		}
		
		
		for(int i=0; i<bombas.size(); i++)
			((Bomba)bombas.elementAt(i)).incrementarCambio();
		
		if(timeEfecto>0)
			decrementarEfecto();
	}

	/**
	 * @param culebra
	 */
	public void robarCola(Culebra culebra, int i) {
		if(culebra==culebra1){
			while(culebra2.getCabeza().getColas()!=0&&i>0)
			{	culebra1.getCabeza().addCola();
				culebra2.getCabeza().removerCola();
				i--;
			}
		}else{
			while(culebra1.getCabeza().getColas()!=0&&i>0){
				culebra2.getCabeza().addCola();
				culebra1.getCabeza().removerCola();
				i--;
			}
		}
			
	}

	/**
	 * @param culebra
	 */
	public void perdio(Culebra culebra) {
		if(culebra==culebra1){
			System.out.println("GANO CULEBRA 2");
		}else {
			System.out.println("GANO CULEBRA 1");
		}
		culebra1.setPausado(true);
		culebra2.setPausado(true);
		miHilo.stop();
	}

	/**
	 * @param culebra
	 */
	public void decrementarEfecto(){
		timeEfecto--;
		System.out.println("efecto: "+timeEfecto);
		if(timeEfecto%10==0)
			cambiarColor();
		if(timeEfecto<=0){
			aplicarApocalipsis();
		}
	}
	
	/**
	 * 
	 */
	private void aplicarApocalipsis() {
		System.out.println("APOCALIPSIS!!!!!!!");
		Culebra culebra=null;
		if(culebra1.isInvulnerable())
			culebra= culebra1;
		else
			culebra= culebra2;
		
		for(int i=obstaculos.size()-1; i>=0; i--){
			if(((Obstaculo)obstaculos.elementAt(i)).getCulebra()!=culebra)
				obstaculos.removeElementAt(i);
		}
		
		if(culebra==culebra1){
			while(culebra2.getCabeza().getColas()>0)
				culebra2.getCabeza().removerCola();
		}else {
			while(culebra1.getCabeza().getColas()>0)
				culebra1.getCabeza().removerCola();
		}
		
		culebra.setInvulnerable(false);
	}

	/**
	 * 
	 */
	private void cambiarColor() {
		if(fondo==Color.GRAY){
			fondo= Color.RED;
		}else 
			fondo=Color.GRAY;
	}

	public void apocalipsis(Culebra culebra) {
		timeEfecto=200;
		culebra1.setInvulnerable(false);
		culebra2.setInvulnerable(false);
		if(culebra1==culebra)
			culebra1.setInvulnerable(true);
		else
			culebra2.setInvulnerable(true);

	}

	/**
	 * @param bala
	 */
	public void addBala(Bala bala) {
		balas.add(bala);
	}

	/**
	 * @param culebra
	 */
	public void paralizarOponente(Culebra culebra) {
		if(culebra1==culebra)
			culebra2.paralizar();
		else
			culebra1.paralizar();
		
	}
}
