package hu.bme.mit.train.system;

import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;
import hu.bme.mit.train.user.TrainUserImpl;

import java.util.Timer;
import java.util.TimerTask;

public class TrainSystem {

	private TrainController controller = new TrainControllerImpl();
	private TrainUser user = new TrainUserImpl(controller);
	private TrainSensor sensor = new TrainSensorImpl(controller, user);
    
    public TrainSystem() {
        TimerTask task = new TimerTask() {
            public void run() {
                controller.followSpeed();
            }
        };

        Timer timer = new Timer("Joystick watcher");
        timer.schedule(task, 500);
    }

	public TrainController getController() {
		return controller;
	}

	public TrainSensor getSensor() {
		return sensor;
	}

	public TrainUser getUser() {
		return user;
	}
	
	public TrainSystem(){
		new Thread(() -> {
			controller.followSpeed();
			try{
				Thread.sleep(500);}
			catch(Exception e){
				e.printStackTrace();
			}
		
		});
		
		
	}
}
