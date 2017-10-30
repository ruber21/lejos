package cucei.udg.lejos.nxt;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.SoundSensor;
import lejos.nxt.Motor;

public class SonidoMotor implements SensorPortListener {
	SoundSensor sonido = new SoundSensor(SensorPort.S2);
	
	public static void main(String[] args) throws Exception
	{
		SonidoMotor motor = new SonidoMotor();
		LCD.drawString("Sonido Motor", 0, 0);
		Button.waitForAnyPress();
		motor.arranca();
		Button.ESCAPE.waitForPressAndRelease();
		LCD.clear();
		LCD.drawString("Terminado", 3, 4);
		Thread.sleep(2000);
	}
	
	public void stateChanged(SensorPort port, int valor, int valorant) {
		LCD.drawString("Probando Motores ", 0, 1);
		LCD.drawInt(sonido.readValue(), 0, 2);
		LCD.clear();
		int aux;
		if(port == SensorPort.S2 && sonido.readValue() > 39 && sonido.readValue() < 50) 
		{
			LCD.drawString("Adelante", 0, 3);
			LCD.refresh();
			for(aux =0; aux < 500; aux++) {
				Motor.B.getSpeed();
				Motor.B.forward();
				Motor.C.getSpeed();
				Motor.C.forward();
			}
			alto();
		}
		
		if(port == SensorPort.S2 && sonido.readValue() > 49 && sonido.readValue() < 60) {
			LCD.drawString("Atras    ", 0, 3);
			LCD.refresh();
			for(aux = 0; aux < 500; aux++) {
				Motor.B.backward();
				Motor.C.backward();
			}
			alto();
		}
		
		if(port == SensorPort.S2 && sonido.readValue() > 59) {
			LCD.drawString("Giro Negativo ", 0, 3);
			LCD.refresh();
			Motor.B.rotate(-360);
			alto();
			LCD.drawString("Giro Positivo ", 0, 3);
			LCD.refresh();
			Motor.C.rotate(360);
			alto();
		}
	}
	
	private void arranca() throws InterruptedException{
		SensorPort.S2.addSensorPortListener(this);
	}
	
	private static void alto() {
		Motor.B.stop();
		Motor.C.stop();
	}
	
	
	
}
