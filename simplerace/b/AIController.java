package simplerace.b;
import simplerace.*;
import java.lang.*;
import java.awt.geom.*;
import java.awt.*;
import java.util.*;

public class AIController implements Controller, Constants {
	public void reset() { }
	private double x;
	private double y;

	public static double calculateExternalAngle(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
		Point2D.Double v1 = subtract(p2, p1);
		Point2D.Double v2 = subtract(p2, p3);
		double angleRadian = Math.acos(innerProduct(v1, v2) / (magnitude(v1) * magnitude(v2)));
		double angleDegree = angleRadian * 180 / Math.PI;
		if (outerProduct(v1, v2) > 0) {
			return 180 - (angleDegree - 180);
		} else {
			return 180 - (180 - angleDegree);
		}
	}

	public static Point2D.Double subtract(Point2D.Double p1, Point2D.Double p2) {
		return new Point2D.Double(p1.x - p2.x, p1.y - p2.y);
	}

	public static double magnitude(Point2D.Double point) {
		return Math.sqrt(point.x * point.x + point.y * point.y);
	}

	public static double innerProduct(Point2D.Double p1, Point2D.Double p2) {
		return p1.x * p2.x + p1.y * p2.y;
	}

	public static double outerProduct(Point2D.Double p1, Point2D.Double p2) {
		return p1.x * p2.y - p1.y * p2.x;
	}

	private int speedOmomi(SensorModel inputs) {
		double currentSpeed = inputs.getSpeed();
		if (currentSpeed < 0.6) {
			return 0;
		} else if (currentSpeed < 1.2) {
			return 1;
		} else if (currentSpeed < 2) {
			return 2;
		} else if (currentSpeed < 2.5) {
			return 3;
		} else if (currentSpeed < 3) {
			return 4;
		} else {
			return 5;
		}
	}

	private int radianOmomi(SensorModel inputs, Double radian){
		int omomi = speedOmomi(inputs);
		if (radian < 10) {
			return omomi + 6;
		} else if (radian < 30) {
			return omomi + 5;
		} else if (radian < 50) {
			return omomi + 4;
		} else if (radian < 60) {
			return omomi + 3;
		} else if (radian < 80) {
			return omomi + 2;
		} else if (radian < 140) {
			return omomi + 1;
		} else {
			return omomi + 0;
		}
	}

	private int nextDistanceOmomi(SensorModel inputs, Double radian) {
		int omomi = radianOmomi(inputs, radian);
		double nextDistance = inputs.getDistanceToNextWaypoint();
		if (nextDistance < 0.008) {
			return omomi + 5;
		} else if (nextDistance < 0.012) {
			return omomi + 4;
		} else if (nextDistance < 0.1) {
			return omomi + 3;
		} else if (nextDistance < 0.18) {
			return omomi + 2;
		} else if (nextDistance < 0.4) {
			return omomi + 1;
		} else {
			return omomi + 0;
		}
	}

	private int nextNextOmomi(SensorModel inputs, Double radian) {
		int omomi = nextDistanceOmomi(inputs, radian);
		double nextNextDistace = inputs.getDistanceToNextNextWaypoint();
		if (nextNextDistace < 0.01) {
			return omomi + 5;
		} else if (nextNextDistace < 0.08) {
			return omomi + 4;
		} else if (nextNextDistace < 0.16) {
			return omomi + 3;
		} else if (nextNextDistace < 0.3) {
			return omomi + 2;
		} else if (nextNextDistace < 0.4) {
			return omomi + 1;
		} else {
			return omomi + 0;
		}
	}

	private int speedControll(SensorModel inputs, Double radian) {
		/*
		currentSpeedは車のスピード
		radianは３点間の角度
		0: 後退
		1: 前進
		2: 維持
		3: スピード0の時やから、進む
		*/

		int omomi = nextNextOmomi(inputs, radian);
		if(inputs.getAngleToNextWaypoint() > 0) {
			if (0 <= omomi && omomi <= 10) {
				return forwardleft;
			} else if (11 <= omomi && omomi <= 19) {
				return left;
			} else {
				return backwardleft;
			}
		} else if (inputs.getAngleToNextWaypoint() < 0) {
			if (0 <= omomi && omomi <= 10) {
				return forwardright;
			} else if (11 <= omomi && omomi <= 19) {
				return right;
			} else {
				return backwardright;
			}
		} else {
			if (0 <= omomi && omomi <= 10) {
				return forward;
			} else if (11 <= omomi && omomi <= 19) {
				return neutral;
			} else {
				return backward;
			}
		}
	}

	public int control (SensorModel inputs) {
		int command = neutral;
		int com = 0;
		double carX = inputs.getPosition().x;
		double carY = inputs.getPosition().y;
		double nextX = inputs.getNextWaypointPosition().x;
		double nextY = inputs.getNextWaypointPosition().y;
		double nextNextX = inputs.getNextNextWaypointPosition().x;
		double nextNextY = inputs.getNextNextWaypointPosition().y;

		Point2D.Double p1 = new Point2D.Double(carX, carY);
		Point2D.Double p2 = new Point2D.Double(nextX, nextY);
		Point2D.Double p3 = new Point2D.Double(nextNextX, nextNextY);

		double radian = calculateExternalAngle(p1, p2, p3);

		if(radian > 180.0) {
			radian = 360 - radian;
		}
		command = speedControll(inputs, radian);
		System.out.print(command);
		System.out.print(": ");
		return command;
	}
}
