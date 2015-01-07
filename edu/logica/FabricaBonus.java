
package edu.logica;

import edu.logica.Bonos.Bono;
import edu.logica.Bonos.BonoAcelerar;
import edu.logica.Bonos.BonoApocalipsis;
import edu.logica.Bonos.BonoBomba;
import edu.logica.Bonos.BonoCola;
import edu.logica.Bonos.BonoDescontrol;
import edu.logica.Bonos.BonoDisparar;
import edu.logica.Bonos.BonoGanaVida;
import edu.logica.Bonos.BonoInvulnerable;
import edu.logica.Bonos.BonoObstaculo;
import edu.logica.Bonos.BonoParalizado;
import edu.logica.Bonos.BonoParalizar;
import edu.logica.Bonos.BonoRobaCola;

public class FabricaBonus {

	public FabricaBonus() {
	}
	
	public Bono crearBono(){
		Bono bono= null;
		int p=(int) Math.round((Math.random())*105);
		
		if(p<Bono.P_ACELERAR)
			bono= new BonoAcelerar();
		else if(p<Bono.P_APOCALIPSIS)
			bono= new BonoApocalipsis();
		else if(p<Bono.P_BOMBA)
			bono= new BonoBomba();
		else if(p<Bono.P_COLA)
			bono= new BonoCola();
		else if(p<Bono.P_DESCONTROL)
			bono= new BonoDescontrol();
		else if(p<Bono.P_DISPARAR)
			bono= new BonoDisparar();
		else if(p<Bono.P_INVULNERABLE)
			bono= new BonoInvulnerable();
		else if(p<Bono.P_OBSTACULO)
			bono= new BonoObstaculo();
		else if(p<Bono.P_PARALIZADO)
			bono= new BonoParalizado();
		else if(p<Bono.P_PARALIZAR)
			bono= new BonoParalizar();
		else  if(p<Bono.P_ROBA_COLA)
			bono= new BonoRobaCola();
		else 
			bono= new BonoGanaVida();

		return bono;
	}

}
