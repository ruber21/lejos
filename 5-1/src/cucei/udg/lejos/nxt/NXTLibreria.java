package cucei.udg.lejos.nxt;

import lejos.nxt.*;

public class NXTLibreria {
	public static void avanza(int ms) throws InterruptedException
	{
		Motor.B.getSpeed();
		Motor.B.forward();
		Motor.C.getSpeed();
		Motor.C.forward();
		Thread.sleep(ms);
	}
	
	public static void retrocede(int ms) throws InterruptedException{
		Motor.B.backward();
		Motor.C.backward();
		Thread.sleep(ms);		
	}
	
	public static void izquierda(int ms) throws InterruptedException{
		Motor.B.forward();
		Motor.C.stop();
		Thread.sleep(ms);
	}
	
	public static void derecha(int ms) throws InterruptedException{
		Motor.B.stop();
		Motor.C.forward();
		Thread.sleep(ms);
	}
	
	public static void alto() {
		Motor.B.stop();
		Motor.C.stop();
	}
}
