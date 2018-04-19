package simplerace.b;
import simplerace.*;
import java.lang.*;
import java.awt.geom.*;
import java.awt.*;

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


	private int speedControll(SensorModel inputs, Double radian) {
		/*
		currentSpeedは車のスピード
		radianは３点間の角度
		0: 後退
		1: 前進
		2: 維持
		3: スピード0の時やから、進む
		*/

		double currentSpeed = inputs.getSpeed();
		double nextDistance = inputs.getDistanceToNextWaypoint();
		if (currentSpeed > 4) {
			if ( 140 < radian && radian <= 180) {
				if (nextDistance < 0.1) {
					System.out.println("1");
					return 2;
				} else {
					System.out.println("2");
					return 1;
				}
			} else if (110 < radian && radian <= 140) {
				if (nextDistance < 0.1) {
					System.out.println("3");
					return 2;
				} else if (40 < radian && radian <= 110) {
					System.out.println("4");
					return 2;
				} else {
					return 0;
				}
			} else if (80 < radian && radian <= 110) {
				if (nextDistance < 0.1) {
					System.out.println("79");
					return 2;
				} else if (50 < radian && radian <= 80) {
					System.out.println("78");
					return 2;
				} else {
					return 0;
				}
			} else if (50 < radian && radian <= 80) {
				if (nextDistance < 0.06) {
					System.out.println("77");
					return 2;
				} else if (30 < radian && radian <= 50) {
					System.out.println("76");
					return 2;
				} else {
					return 0;
				}
			} else {
				if (nextDistance < 0.1) {
					System.out.println("5");
					return 0;
				} else if (nextDistance < 0.25) {
					System.out.println("6");
					return 2;
				} else {
					System.out.println("7");
					return 1;
				}
			}

		} else if (3.5 < currentSpeed && currentSpeed <= 4 ) {
			if ( 120 < radian && radian <= 180) {
				if (nextDistance < 0.1) {
					System.out.println("8");
					return 2;
				} else {
					System.out.println("9");
					return 1;
				}
			} else if ( 70 < radian && radian <= 120) {
				if (nextDistance < 0.15) {
					System.out.println("10");
					return 2;
				} else {
					System.out.println("11");
					return 1;
				}
			} else if (50 < radian && radian <= 70) {
				if (nextDistance < 0.07) {
					System.out.println("99");
					return 2;
				} else if (nextDistance < 0.8) {
					return 1;
				} else {
					System.out.println("98");
					return 0;
				}
			} else if (30 < radian && radian <= 50) {
				if (nextDistance < 0.15) {
					System.out.println("99");
					return 2;
				} else {
					System.out.println("98");
					return 1;
				}
			} else {
				if (nextDistance < 0.1) {
					System.out.println("88");
					return 0;
				} else if (nextDistance < 0.2) {
					System.out.println("87");
					return 2;
				} else {
					System.out.println("86");
					return 1;
				}
			}

		} else if (3 < currentSpeed && currentSpeed <= 3.5 ) {
			if ( 120 < radian && radian <= 180) {
				if (nextDistance < 0.1) {
					System.out.println("8");
					return 2;
				} else {
					System.out.println("9");
					return 1;
				}
			} else if ( 70 < radian && radian <= 120) {
				if (nextDistance < 0.06) {
					System.out.println("10");
					return 2;
				} else {
					System.out.println("11");
					return 1;
				}
			} else if (50 < radian && radian <= 70) {
				if (nextDistance < 0.2) {
					System.out.println("85");
					return 2;
				} else if (nextDistance < 0.8) {
					return 1;
				} else {
					System.out.println("84");
					return 0;
				}
			} else if (40 < radian && radian <= 50) {
				if (nextDistance < 0.16) {
					System.out.println("83");
					return 2;
				} else {
					System.out.println("82");
					return 1;
				}
			} else if (10 < radian && radian <= 40) {
				if (nextDistance < 0.2) {
					System.out.println("80");
					return 2;
				} else {
					System.out.println("81");
					return 1;
				}
			} else {
				if (nextDistance < 0.2) {
					System.out.println("12");
					return 2;
				} else if (nextDistance < 0.7) {
					System.out.println("13");
					return 1;
				} else {
					System.out.println("14");
					return 0;
				}
			}

		} else if (2.5 < currentSpeed && currentSpeed <= 3 ) {
			if ( 100 < radian && radian <= 120) {
				if (nextDistance < 0.09) {
					System.out.println("15");
					return 2;
				} else {
					System.out.println("16");
					return 1;
				}
			} else if ( 10 < radian && radian <= 100) {
				if (nextDistance < 0.08) {
					System.out.println("17");
					return 2;
				} else {
					System.out.println("18");
					return 1;
				}
			} else {
				if (nextDistance < 0.1) {
					System.out.println("19");
					return 0;
				} else if (nextDistance < 0.13) {
					System.out.println("20");
					return 2;
				} else {
					System.out.println("21");
					return 1;
				}
			}

		} else if (2 < currentSpeed && currentSpeed <= 2.5) {
			if ( 80 < radian && radian <= 180) {
				if (nextDistance < 0.05) {
					System.out.println("22");
					return 2;
				} else {
					System.out.println("23");
					return 1;
				}
			} else if (50 < radian && radian <= 80) {
				if (nextDistance < 0.08) {
					System.out.println("24");
					return 2;
				} else {
					System.out.println("25");
					return 1;
				}
			} else if (20 < radian && radian <= 50) {
				if (nextDistance < 0.1) {
					System.out.println("26");
					return 2;
				} else {
					System.out.println("27");
					return 1;
				}
			} else {
				if (nextDistance < 0.1) {
					System.out.println("28");
					return 0;
				} else if (nextDistance < 0.2) {
					System.out.println("29");
					return 2;
				} else {
					System.out.println("30");
					return 1;
				}
			}
//導入済み
		} else if (1.5 < currentSpeed && currentSpeed <= 2 ) {
			if ( 40 < radian && radian <= 180) {
				if (nextDistance < 0.08) {
					System.out.println("31");
					return 2;
				} else {
					System.out.println("32");
					return 1;
				}
			} else if ( 5 < radian && radian <= 40) {
				if (nextDistance < 0.05) {
					System.out.println("33");
					return 2;
				} else {
					System.out.println("34");
					return 1;
				}
			} else {
				if (nextDistance < 0.05) {
					System.out.println("35");
					return 2;
				} else if (0.05 <= nextDistance && nextDistance < 0.1) {
					System.out.println("36");
					return 1;
				} else {
					System.out.println("37");
					return 0;
				}
			}
		} else if (1 < currentSpeed && currentSpeed <= 1.5 ) {
			if ( 20 < radian && radian <= 180) {
				if (nextDistance < 0.03) {
					System.out.println("38");
					return 2;
				} else {
					System.out.println("39");
					return 1;
				}
			} else if ( 2 < radian && radian <= 20) {
				if (nextDistance < 0.07) {
					System.out.println("40");
					return 2;
				} else {
					System.out.println("41");
					return 1;
				}
			} else {
				if (nextDistance < 0.03) {
					System.out.println("42");
					return 0;
				} else if (0.03 <= nextDistance && nextDistance < 0.14) {
					System.out.println("43");
					return 2;
				} else {
					System.out.println("44");
					return 1;
				}
			}
		} else {
			System.out.println("45");
			return 1;
		}
	}

	// private int distanceControll(SensorModel inputs, Double radian) {
	// 	double nextDistance = inputs.getDistanceToNextWaypoint();
	// 	int speedCheck = speedControll(inputs, radian);
	// 	if (speedCheck == 1) {
	// 		if (nextDistance < 0.04) {
	// 			return 0;
	// 		} else if (nextDistance < 0.1) {
	// 			return 2;
	// 		} else {
	// 			return 1;
	// 		}
	// 	} else if (speedCheck == 2) {
	// 		if (nextDistance < 0.01) {
	// 			return 0;
	// 		} else if (nextDistance < 0.6) {
	// 			return 1;
	// 		} else {
	// 			return 2;
	// 		}
	// 	} else if (speedCheck == 3) {
	// 		return 1;
	// 	} else {
	// 		return 0;
	// 	}
	// }

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
			com = speedControll(inputs, (360 - radian));
		} else {
			com = speedControll(inputs, radian);
		}

		if(inputs.getAngleToNextWaypoint() > 0) {
			if (com == 0) {
				command = backwardleft;
			} else if (com == 1) {
				command = forwardleft;
			} else {
				command = left;
			}
		} else {
			if (com == 0) {
				command = backwardright;
			} else if (com == 1) {
				command = forwardright;
			} else {
				command = right;
			}
		}
		return command;
	}
}
