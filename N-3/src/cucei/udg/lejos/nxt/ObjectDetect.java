package cucei.udg.lejos.nxt;
import lejos.nxt.*;
import lejos.robotics.objectdetection.*;


public class ObjectDetect implements FeatureListener{
	public static int MaxRange = 80;
	
	public static void main(String[] args) throws Exception{
		
		ObjectDetect od = new ObjectDetect();
		UltrasonicSensor us = new UltrasonicSensor(SensorPort.S1);
		RangeFeatureDetector fd = new RangeFeatureDetector(us, MaxRange, 500);
		fd.addListener(od);
		Button.ENTER.waitForPressAndRelease();
	}
	
	public void featureDetected(Feature feature, FeatureDetector detector) {
		int range = (int)feature.getRangeReading().getRange();
		Sound.playTone(1200 - (range * 10), 100);
		System.out.println("Range: " + range);
	}

}
