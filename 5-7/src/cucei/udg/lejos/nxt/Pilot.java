package cucei.udg.lejos.nxt;

import lejos.nxt.LCD;

public class Pilot {
	public static void main(String[] args) throws InterruptedException{
		DiffPilot robot = new DiffPilot();
		LCD.clear();
		LCD.drawString("Traveling", 0, 2);
		robot.travelTest(100, 50);
	}
}
