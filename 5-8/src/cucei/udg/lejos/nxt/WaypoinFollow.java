package cucei.udg.lejos.nxt;

import java.io.IOException;
import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.navigation.Pose;
import lejos.robotics.localization.OdometryPoseProvider;

public class WaypoinFollow {

	Navigator nav;
	
	public static void main(String[] args) 
	{
		System.out.println("Any button to start");
		Button.waitForAnyPress();
		
		DifferentialPilot pilot = new DifferentialPilot(5.5f, 16f, Motor.A, Motor.B);
		OdometryPoseProvider poseP = new OdometryPoseProvider(pilot);
		pilot.addMoveListener(poseP);
		Navigator wpNav = new Navigator(pilot);
		
		wpNav.addWaypoint(new Waypoint(20,0));
		wpNav.addWaypoint(new Waypoint(20,20));
		wpNav.addWaypoint(new Waypoint(40,40));
		wpNav.addWaypoint(new Waypoint(-40,40));
		wpNav.addWaypoint(new Waypoint(0,0));
		wpNav.followPath();
		
		Waypoint wpts;
		while(wpNav.isMoving()) {
			wpts = wpNav.getWaypoint();
			LCD.clear();
			System.out.println("Moving to waypoint: ");
			System.out.println(wpts);
		}
		
		Pose poseC = poseP.getPose();
		LCD.clear();
		System.out.println("Final pose is: ");
		System.out.println(poseC);
		System.out.println("Any button to halt");
		Button.waitForAnyPress();
	}
	
}
