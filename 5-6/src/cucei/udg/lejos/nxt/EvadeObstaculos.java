package cucei.udg.lejos.nxt;

import lejos.nxt.*;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class EvadeObstaculos {

	static RegulatedMotor MotorIzquierdo = Motor.B;
	static RegulatedMotor MotorDerecho = Motor.C;
	
	public static void main (String[] args) {
		MotorIzquierdo.setSpeed(400);
		MotorDerecho.setSpeed(400);
		Behavior b1 = new Avanza();
		Behavior b2 = new DetectaObstaculoFrente();
		Behavior b3 = new DetectaObstaculoAtras();
		Behavior [] behaviorList = {b1, b2, b3};
		Arbitrator arbitro = new Arbitrator(behaviorList);
		LCD.drawString("Evade obstaculos", 0, 1);
		Button.waitForAnyPress();
		arbitro.start();
	}
	
}

class Avanza implements Behavior
{
	private boolean _suppressed = false;
	public boolean takeControl() {
		return true;
	}
	public void suppress() {
		_suppressed = true;
	}
	public void action() {
		LCD.drawString("Avanza", 0, 2);
		_suppressed = false;
		EvadeObstaculos.MotorIzquierdo.forward();
		EvadeObstaculos.MotorDerecho.forward();
		while(!_suppressed) 
		{
			Thread.yield();
		}
		EvadeObstaculos.MotorIzquierdo.stop();
		EvadeObstaculos.MotorDerecho.stop();
	}
}

class DetectaObstaculoFrente implements Behavior
{
	public DetectaObstaculoFrente() 
	{
		sonar = new UltrasonicSensor(SensorPort.S4);
	}
	public boolean takeControl() {
		sonar.ping();
		return sonar.getDistance() < 25;
	}
	public void suppress() 
	{		
	}
	public void action() {
		LCD.drawString("Frente   " , 0, 2);
		EvadeObstaculos.MotorIzquierdo.rotate(-180, true);
		EvadeObstaculos.MotorDerecho.rotate(-360);
	}
	private UltrasonicSensor sonar;	
}

class DetectaObstaculoAtras implements Behavior
{
	int aux;
	public DetectaObstaculoAtras()
	{
		touch = new TouchSensor(SensorPort.S1);
	}
	public boolean takeControl() 
	{
		return touch.isPressed();
	}
	public void suppress() 
	{		
	}
	public void action() 
	{
		LCD.drawString("Atras   ", 0, 2);
		for(aux = 0; aux < 200; aux++) {
			EvadeObstaculos.MotorIzquierdo.forward();
			EvadeObstaculos.MotorDerecho.forward();
		}
		EvadeObstaculos.MotorIzquierdo.rotate(180, true);
		EvadeObstaculos.MotorDerecho.rotate(360);
	}
	
	private TouchSensor touch;
}

















