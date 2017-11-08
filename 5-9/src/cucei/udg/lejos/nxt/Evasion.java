package cucei.udg.lejos.nxt;

import java.lang.Math;
import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Pose;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Evasion {
	
	static DifferentialPilot pilot = new DifferentialPilot(5.5f, 16f, Motor.B, Motor.C);
	static UltrasonicSensor us = new UltrasonicSensor(SensorPort.S1);
	static Pose pose = new Pose();
	static OdometryPoseProvider pp = new OdometryPoseProvider(pilot);
	
	static double XT = 100;
	static double YT = 75;
	static double x;
	static double y;
	static double detect = 25;
	static boolean start = true;
	static boolean rot = false;
	static boolean curve = true;
	static boolean endpose = false;
	
	public static void main(String[] args) throws Exception
	{
		pilot.addMoveListener(pp);
		Behavior [] behaviourList = {Travel, Evade, Halt};
		(new Arbitrator(behaviourList)).start();
	}
	
	static Behavior Travel = new Behavior() 
	{
		public boolean takeControl() 
		{
			if(start == true) 
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		public void action() 
		{
			LCD.clear();
			LCD.drawString("Travel ", 0, 0);
			System.out.println("\n Traveling to " + XT + ", " + YT);
			double angle = Math.atan2(YT, XT) * (180 / 3.1416);
			double distance = Math.sqrt((Math.pow(XT, 2) + Math.pow(YT, 2)));
			pilot.rotate(-angle);
			rot = true;
			pilot.travel(distance);
			start = false;
			curve = false;
			endpose = true;
		}
		
		public void suppress() 
		{
			pilot.stop();
			pose = pp.getPose();
			endpose = false;
		}
	};
	
	static Behavior Evade = new Behavior() 
	{
		public boolean takeControl() 
		{
			if(rot == true && curve == true) 
			{
				return us.getDistance() <= detect;
			}
			else 
			{
				return false;
			}
		}
		
		public void action() 
		{
			x = pose.getX();
			y = pose.getY();
			
			LCD.clear();
			LCD.drawString("Evade", 0, 0);
			System.out.println("\n Object Detected at" + x + ", " + -y);
			x = XT - x;
			y = YT + y;
			
			double D = Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
			double radius = D/2;
			double choice = Math.random();
			
			if(choice > 5) 
			{
				pilot.rotate(-90);
				pilot.arc(radius, 180);
			}
			else 
			{
				pilot.rotate(90);
				pilot.arc(-radius, -180);
			}
			
			start = false;
			curve = false;
			endpose = true;
		}
		
		public void suppress() {}
	};
	
	static Behavior Halt = new Behavior() 
	{
		public boolean takeControl() 
		{
			if(endpose == true) 
			{
				return true;
			}
			else 
			{
				return false;				
			}
		}		
		public void action() 
		{
			Runtime.getRuntime().exit(0);
		}
		public void suppress() {}
	};

}
