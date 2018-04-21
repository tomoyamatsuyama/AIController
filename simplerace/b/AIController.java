package simplerace.b;
import simplerace.*;
import java.lang.*;
import java.awt.geom.*;
import java.awt.*;
import java.util.*;

public class AIController implements Controller, Constants {
	private SensorModel inputs;
	public void reset() { }
	private double x;
	private double y;
	private boolean isNextNext;
	private boolean isBack;
	private int count;
	private int countOfFlagGet;
	private boolean isGet = true;

	public int control (SensorModel inputs) {
		count ++;
		int command = neutral;
		int com = 0;
		this.inputs = inputs;

		this.isNextNext = isNextNextCar();

		breakControll();
		isFlagGet();

		command = defaultControll();
		if (isNextNext) {
			double distance = inputs.getDistanceToNextNextWaypoint();
			double speed = inputs.getSpeed();
			if (distance < 0.1) {
				if (speed < 0.4) {
					command = neutral;
				} else {
					command = backward;
				}
			}
		}
		return command;
	}

	private void missFlag() {
		if ((count - countOfFlagGet) > 100) {
			isGet = false;
		}
	}

	private void isFlagGet() {
		double distance = inputs.getDistanceToNextWaypoint();
		double nextNextDistance = inputs.getDistanceToNextNextWaypoint();
		if ((distance < 0.038) || (nextNextDistance < 0.038)) {
			isNextNext = false;
			countOfFlagGet = count;
			isGet = true;
		}
	}

	private int defaultControll() {
		double currentAngle = isNextNext ? inputs.getAngleToNextNextWaypoint() : inputs.getAngleToNextWaypoint();
		double safeArea = safeArea();

		if(Math.abs(currentAngle) <= safeArea){
			return isBack ? backward : forward;
		} else {
			return forwardControll();
		}
	}

	private int forwardControll() {
		double currentAngle = isNextNext ? inputs.getAngleToNextNextWaypoint() : inputs.getAngleToNextWaypoint();
		if((int)(Math.random()*100.0) % 3 == 0){
			if(currentAngle >= 0) {
				return left;
			}
			return right;
		}
		if(currentAngle >= 0) {
			return forwardleft;
		}
		return forwardright;
	}

	private void breakControll() {
		double currentSpeed = inputs.getSpeed();
		double safeSpeed = safeSpeed();
		if (currentSpeed > safeSpeed) {
			this.isBack = true;
		} else {
			this.isBack = false;
		}
	}

	private double safeSpeed() {
		double distance = isNextNext ? inputs.getDistanceToNextNextWaypoint() : inputs.getDistanceToNextWaypoint();
		if (distance < 0.04) {
			return 2.5;
		} else if (distance < 0.12) {
			return 4;
		} else {
			return 6;
		}
	}

	private double safeArea() {
		double distance = isNextNext ? inputs.getDistanceToNextNextWaypoint() : inputs.getDistanceToNextWaypoint();
		if (distance < 0.2) {
			return 0.1;
		} else if (distance < 0.5) {
			return 0.23;
		} else if (distance < 1) {
			return 0.25;
		} else {
			return 0.28;
		}
	}

	private boolean isNextNextCar() {
		double nextX = inputs.getNextWaypointPosition().x;
		double nextY = inputs.getNextWaypointPosition().y;

		double nextNextX = inputs.getNextNextWaypointPosition().x;
		double nextNextY = inputs.getNextNextWaypointPosition().y;
		//自分の車
		double carPositionX = inputs.getPosition().x;
		double carPositionY = inputs.getPosition().y;
		double carDistanceToNext = distanceFromPosition(carPositionX, carPositionY, nextX, nextY);
		double carDistanceToNextNext =distanceFromPosition(carPositionX, carPositionY, nextNextX, nextNextY);

		//以下、相手の車とターゲットの距離を測る
		double otherCarPositionX = inputs.getOtherVehiclePosition().x;
		double otherCarPositionY = inputs.getOtherVehiclePosition().y;
		double otherCarDistanceToNext = distanceFromPosition(otherCarPositionX, otherCarPositionY, nextX, nextY);

		if (!isGet) {
			return false;
		}
		if (carDistanceToNext < otherCarDistanceToNext) {
			return false;
		}
		return true;
	}

	private double radianToDegree(double rad) {
		double degree = rad * 180d / Math.PI;
		return degree;
	}

	private double distanceFromPosition(Double x1, Double y1, Double x2, Double y2) {
		return Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
	}
}
