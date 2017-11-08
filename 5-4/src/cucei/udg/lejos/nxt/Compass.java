package cucei.udg.lejos.nxt;
import lejos.nxt.*;



public class Compass 
{
	public static void main(String[] args) throws InterruptedException{
		CompassCal robot = new CompassCal();
		robot.calibrate();
		robot.lecture();
		Button.ESCAPE.waitForPress();
	}

}
