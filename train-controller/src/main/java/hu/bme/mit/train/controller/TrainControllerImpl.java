package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

    //Steps
    //Reference Speed
	private int referenceSpeed = 0;
    //Speed Limit
	private int speedLimit = 0;

    //Automatically calculate follow speed
	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

    //Getter for referenceSpeed
	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

    //Setter for speedLimit
	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

    //Automatically limit speed
	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

    //Automatically set joystick position
	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}
}
