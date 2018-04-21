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

	public int control (SensorModel inputs) {
		int command = neutral;
		int com = 0;
		this.inputs = inputs;

		this.isNextNext = isNextNextCar();
		System.out.println(isNextNext);
		isWait();
		breakControll();
		isFlagGet();

		command = defaultControll();
		return command;
	}

	private void isFlagGet() {
		double distance = inputs.getDistanceToNextWaypoint();
		if (distance < 0.038) {
			System.out.println("get");
		}
	}

	private void isWait() {
		if (!isNextNext) {
			double distance = inputs.getDistanceToNextWaypoint();
			if (distance < 0.05) {
				this.isBack = true;
			}
		} else {
			this.isBack = false;
		}
	}

	private int defaultControll() {
		double currentAngle = isNextNext ? inputs.getAngleToNextNextWaypoint() : inputs.getAngleToNextWaypoint();
		double degree = radianToDegree(currentAngle);
		double safeArea = safeArea();

		if(Math.abs(degree) <= safeArea){
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
		if (distance < 0.05) {
			return 1.5;
		} else if (distance < 0.07) {
			return 1.9;
		} else if (distance < 0.1) {
			return 2.3;
		} else if (distance < 0.12) {
			return 2.4;
		} else if (distance < 0.15) {
			return 3;
		} else if (distance < 0.25) {
			return 4;
		} else {
			return 6;
		}
	}

	private double safeArea() {
		double distance = isNextNext ? inputs.getDistanceToNextNextWaypoint() : inputs.getDistanceToNextWaypoint();
		if (distance < 0.2) {
			return 5.0;
		} else if (distance < 0.5) {
			return 2.5;
		} else if (distance < 1) {
			return 0.6;
		} else {
			return 0.1;
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
		System.out.println(carDistanceToNext);
		System.out.println(otherCarDistanceToNext);
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

	// private int speedControll(Double radian) {
	// 	// 0: back, 1: neutral, 2: forward
	// 	double distance = isNextNext ? inputs.getDistanceToNextWaypoint() : inputs.getDistanceToNextNextWaypoint();
	// 	double currentSpeed = inputs.getSpeed();
  //
	// 	if (distance < 0.2) {
	// 		if (currentSpeed < 0.13) {
	// 			System.out.println("1");
	// 			return 2;
	// 		} else if (currentSpeed < 0.23) {
	// 			System.out.println("2");
	// 			return 1;
	// 		} else {
	// 			System.out.println("3");
	// 			return 0;
	// 		}
	// 	} else if (distance < 0.4) {
	// 		if (currentSpeed < 0.2) {
	// 			System.out.println("4");
	// 			return 2;
	// 		} else if (currentSpeed < 0.3) {
	// 			System.out.println("5");
	// 			return 1;
	// 		} else {
	// 			System.out.println("6");
	// 			return 0;
	// 		}
	// 	} else {
	// 		if (currentSpeed < 0.3) {
	// 			System.out.println("7");
	// 			return 2;
	// 		} else if (currentSpeed < 0.35) {
	// 			System.out.println("8");
	// 			return 1;
	// 		} else {
	// 			System.out.println("9");
	// 			return 0;
	// 		}
	// 	}
	// }

	// private double getRadian() {
	// 	double carX = inputs.getPosition().x;
	// 	double carY = inputs.getPosition().y;
	// 	double nextX = inputs.getNextWaypointPosition().x;
	// 	double nextY = inputs.getNextWaypointPosition().y;
	// 	double nextNextX = inputs.getNextNextWaypointPosition().x;
	// 	double nextNextY = inputs.getNextNextWaypointPosition().y;
  //
	// 	Point2D.Double p1 = new Point2D.Double(carX, carY);
	// 	Point2D.Double p2 = new Point2D.Double(nextX, nextY);
	// 	Point2D.Double p3 = new Point2D.Double(nextNextX, nextNextY);
  //
	// 	return calculateExternalAngle(p1, p2, p3);
	// }

	// public static double calculateExternalAngle(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
	// 	Point2D.Double v1 = subtract(p2, p1);
	// 	Point2D.Double v2 = subtract(p2, p3);
	// 	double angleRadian = Math.acos(innerProduct(v1, v2) / (magnitude(v1) * magnitude(v2)));
	// 	double angleDegree = angleRadian * 180 / Math.PI;
	// 	if (outerProduct(v1, v2) > 0) {
	// 		return 180 - (angleDegree - 180);
	// 	} else {
	// 		return 180 - (180 - angleDegree);
	// 	}
	// }
  //
	// public static Point2D.Double subtract(Point2D.Double p1, Point2D.Double p2) {
	// 	return new Point2D.Double(p1.x - p2.x, p1.y - p2.y);
	// }
  //
	// public static double magnitude(Point2D.Double point) {
	// 	return Math.sqrt(point.x * point.x + point.y * point.y);
	// }
  //
	// public static double innerProduct(Point2D.Double p1, Point2D.Double p2) {
	// 	return p1.x * p2.x + p1.y * p2.y;
	// }
  //
	// public static double outerProduct(Point2D.Double p1, Point2D.Double p2) {
	// 	return p1.x * p2.y - p1.y * p2.x;
	// }
  //
	// public static double otherDistance(Double x1, Double y1, Double x2, Double y2) {
	// 	return Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
	// }
}
