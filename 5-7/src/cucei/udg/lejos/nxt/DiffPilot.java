package cucei.udg.lejos.nxt;
import java.lang.Math;
import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;


public class DiffPilot 
{
	private DifferentialPilot pilot = new DifferentialPilot(5.5f, 16f, Motor.A, Motor.B);
	double X;
	double Y;
	
	public void travelTest(double x, double y) {
		x = x- X;
		y = y - Y;
		
		double radius = 0;
		double straight = 0;
		double grads = 0;
		double rotation = 0;
		
		if(x > 0 & y > 0) 
		{
			if(Math.abs(x) <= Math.abs(y)) 
			{
				rotation = -90;
				radius = Math.abs(x);
				straight = Math.abs(y) - Math.abs(x);
				grads = 90;
			}
			else 
			{
				rotation = 0;
				radius = -Math.abs(y);
				straight = Math.abs(x) - Math.abs(y);
				grads = -90;
			}
		}
		if(x < 0 & y > 0) 
		{
			if(Math.abs(x) <= Math.abs(y)) 
			{
				rotation = -90;
				radius = -Math.abs(x);
				straight = Math.abs(y) - Math.abs(x);
				grads = -90;
			}
			else 
			{
				rotation = -180;
				radius = Math.abs(y);
				straight= Math.abs(x) - Math.abs(y);
				grads = 90;
			}
		}
		if(x < 0 & y < 0) 
		{
			if(Math.abs(x) <= Math.abs(y))
			{
				rotation = -270;
				radius = Math.abs(x);
				straight = Math.abs(x) - Math.abs(y);
				grads = 90;
			}
			else 
			{
				rotation = -180;
				radius = -Math.abs(y);
				straight = Math.abs(y) - Math.abs(y);
				grads = -90;
			}
		}
		if(x > 0 & y < 0) 
		{
			if(Math.abs(x) <= Math.abs(y)) 
			{			
				rotation = -270;
				radius = -Math.abs(x);
				straight = Math.abs(y) - Math.abs(x);
				grads = -90;
			}
			else 
			{
				rotation = 0;
				radius = Math.abs(y);
				straight = Math.abs(x) - Math.abs(y);
				grads = -90;
			}
		}
		pilot.setRotateSpeed(50);
		pilot.rotate(rotation);
		pilot.travel(straight);
		pilot.arc(radius, grads);
	}
}






