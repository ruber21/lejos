package cucei.udg.lejos.nxt;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.RotateMoveController;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.PilotProps;

public class SigueLinea {
	
	private static int lightSens = 42; 
	
	public static void main(String[] args) throws Exception{
		PilotProps pp = new PilotProps();
		pp.loadPersistentValues();
		
		float Diametro = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "4.96"));
		float ancho = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "13.0"));
		
		RegulatedMotor MotorIzquierdo = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "B"));
		RegulatedMotor MotorDerecho = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "C"));
		
		boolean reversa = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE, "false"));
		
		final RotateMoveController piloto = new DifferentialPilot(Diametro, ancho, MotorIzquierdo, MotorDerecho, reversa);
		
		final LightSensor luz = new LightSensor(SensorPort.S3);

		piloto.setRotateSpeed(30);
		
		Behavior Avanza = new Behavior() {
			public boolean takeControl() {
				return luz.readValue() <= lightSens;
			}
			public void suppress() {
				piloto.stop();
			}
			public void action() {
				piloto.forward();
				while(luz.readValue() <= lightSens)
					Thread.yield();
			}
		};
		
		Behavior fueraLinea = new Behavior() {
			public boolean suppress = false;
			public boolean takeControl() {
				if(Button.ESCAPE.isDown())
					return true;
				return luz.readValue() > lightSens;
			}
			public void suppress() {
				suppress = true;
				if(Button.ESCAPE.isDown())
					return;
			}
			public void action() {
				int barrido = 10;
				while(!suppress) 
				{
					piloto.rotate(barrido, true);
					while(!suppress && piloto.isMoving())
						Thread.yield();
					barrido *= -2;
				}
				piloto.stop();
				suppress = false;
				if(Button.ESCAPE.isDown())
					return;
			}
		};
		
		Behavior[] inicializa = {fueraLinea, Avanza};
		LCD.drawString("Sigue Lineas", 0, 1);
		Button.waitForAnyPress();
		Arbitrator ar = new Arbitrator(inicializa);
		ar.start();
		
		
		
	}
}
