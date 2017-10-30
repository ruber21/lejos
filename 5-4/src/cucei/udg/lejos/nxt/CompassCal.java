package cucei.udg.lejos.nxt;

import lejos.nxt.*;
import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class CompassCal {
	private DifferentialPilot pilot = new DifferentialPilot(5.5f, 16f, Motor.A, Motor.B);
	private CompassHTSensor compass = new CompassHTSensor(SensorPort.S1);
	
	public void calibrate() {
		pilot.setRotateSpeed(18);
		compass.startCalibration();
		pilot.rotate(720, true);
		compass.stopCalibration();
	}
	
	public void lecture() {
		int grados = (int)compass.getDegrees();
		LCD.clear();
		LCD.drawString("Grados", 0, 0);
		LCD.drawInt(grados, 0, 2);				
	}
}
