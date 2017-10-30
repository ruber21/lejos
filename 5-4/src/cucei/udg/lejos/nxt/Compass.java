package cucei.udg.lejos.nxt;



public class Compass 
{
	public static void main(String[] args) throws InterruptedException{
		CompassCal robot = new CompassCal();
		robot.calibrate();
		while(true)
			robot.lecture();
	}

}
