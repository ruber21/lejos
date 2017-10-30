package cucei.udg.lejos.nxt;

import lejos.nxt.*;

public class NXTDemo extends NTXLibreria{
	
	public static void main (String[] args) throws InterruptedException{
		LCD.drawString("Probando NXT",  0, 0);
		avanza(4000);
		retrocede(3000);
		derecha(3000);
		avanza(1000);
		izquierda(2000);
		alto();
	}
}
