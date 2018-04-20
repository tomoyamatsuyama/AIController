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
		double nextNextDistace = inputs.getDistanceToNextNextWaypoint();

		if (currentSpeed > 4) {
			if (160 < radian && radian <= 180) {
				if (nextDistance < 0.07) {
					System.out.println("1");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.18) {
					System.out.println("1");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("2");
					if (nextNextDistace < 0.009) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.03) {
						System.out.println("139");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.println("138");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if (140 < radian && radian <= 160) {
				if (nextDistance < 0.02) {
					System.out.println("3");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.28) {
					System.out.println("137");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					if (nextNextDistace < 0.01) {
						System.out.println("136");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.06) {
						System.out.println("135");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.println("133");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if (110 < radian && radian <= 140) {
				if (nextDistance < 0.03) {
					System.out.println("4");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.08) {
					System.out.println("112");
					if (nextNextDistace < 0.01) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else {
						System.out.println("178");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					}
				} else {
					System.out.print("113");
					if (nextNextDistace < 0.046) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.17) {
						System.out.println("134");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							System.out.println("131");
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if (80 < radian && radian <= 110) {
				if (nextDistance < 0.02) {
					System.out.println("79");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.08) {
					System.out.println("78");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					if (nextNextDistace < 0.03) {
						System.out.println("130");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.06) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if (50 < radian && radian <= 80) {
				if (nextDistance < 0.1) {
					System.out.println("77");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.25) {
					System.out.println("76");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					if (nextNextDistace < 0.03) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.06) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if (30 < radian && radian <= 50) {
				if (nextDistance < 0.13) {
					System.out.println("77");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.25) {
					System.out.println("76");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					if (nextNextDistace < 0.03) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.06) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else {
				if (nextDistance < 0.12) {
					System.out.println("5");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.17) {
					System.out.println("6");
					if (nextNextDistace < 0.04) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else {
						System.out.println("182");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					}
				} else {
					System.out.println("7");
					if (nextNextDistace < 0.03) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.19) {
						System.out.println("144");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.println("143");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			}
		} else if (3.5 < currentSpeed && currentSpeed <= 4) {
			if ( 120 < radian && radian <= 180) {
				if (nextDistance < 0.07) {
					System.out.println("110");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.12) {
					System.out.println("8");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("9");
					if (nextNextDistace < 0.03) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.06) {
						System.out.println("141");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.println("142");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if ( 80 < radian && radian <= 120) {
				if (nextDistance < 0.03) {
					System.out.println("88");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.15) {
					System.out.println("10");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("11: ");
					if (nextNextDistace < 0.06) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.09) {
						System.out.print("167");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.print("166");
						if (nextNextDistace < 0.046) {
							if(inputs.getAngleToNextWaypoint() > 0) {
								return backwardleft;
							} else if (inputs.getAngleToNextWaypoint() < 0) {
								return backwardright;
							} else {
								return backward;
							}
						} else if (nextNextDistace < 0.14) {
							System.out.println("170");
							if(inputs.getAngleToNextWaypoint() > 0) {
								return left;
							} else if (inputs.getAngleToNextWaypoint() < 0) {
								return right;
							} else {
								return neutral;
							}
						} else {
							System.out.println("171");
							if(inputs.getAngleToNextWaypoint() > 0) {
								return forwardleft;
							} else if (inputs.getAngleToNextWaypoint() < 0) {
								return forwardright;
							} else {
								return forward;
							}
						}
					}
				}
			} else if ( 60 < radian && radian <= 80) {
				if (nextDistance < 0.03) {
					System.out.println("170");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.13) {
					System.out.println("169");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("168: ");
					if (nextNextDistace < 0.03) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.08) {
						System.out.println("169: ");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							System.out.println("170: ");
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if (40 < radian && radian <= 60) {
				if (nextDistance < 0.03) {
					System.out.println("139");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.13) {
					System.out.println("99");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					if (nextNextDistace < 0.015) {
						System.out.print("164");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.03) {
						System.out.print("163");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.print("162");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if (30 < radian && radian <= 40) {
				if (nextDistance < 0.03) {
					System.out.println("88");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.15) {
					System.out.println("99");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("98");
					if (nextNextDistace < 0.02) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.04) {
						System.out.print("161");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.print("160");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else {
				if (nextDistance < 0.03) {
					System.out.println("140");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.1) {
					System.out.println("87");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("86");
					if (nextNextDistace < 0.03) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.print("159");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			}

		} else if (3 < currentSpeed && currentSpeed <= 3.5 ) {
			if ( 120 < radian && radian <= 180) {
				if (nextDistance < 0.2) {
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.15) {
					System.out.println("69");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("68");
					if (nextNextDistace < 0.03) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.print("158");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if (70 < radian && radian <= 120) {
				if (nextDistance < 0.06) {
					System.out.println("67");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("66");
					if (nextNextDistace < 0.03) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.06) {
						System.out.println("172");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.println("173");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if (50 < radian && radian <= 70) {
				if (nextDistance < 0.2) {
					System.out.println("85");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else if (nextDistance < 0.8) {
					System.out.print("156");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return forwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return forwardright;
					} else {
						return forward;
					}
				} else {
					System.out.println("84");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				}
			} else if (40 < radian && radian <= 50) {
				if (nextDistance < 0.1) {
					System.out.println("83");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("82");
					if (nextNextDistace < 0.03) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.print("155");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if (10 < radian && radian <= 40) {
				if (nextDistance < 0.12) {
					System.out.println("80");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("81");
					if (nextNextDistace < 0.02) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.print("154");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else {
				if (nextDistance < 0.17) {
					System.out.println("12");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.4) {
					System.out.println("13");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("14");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return forwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return forwardright;
					} else {
						return forward;
					}
				}
			}
		} else if (2.5 < currentSpeed && currentSpeed <= 3 ) {
			if ( 100 < radian && radian <= 120) {
				if (nextDistance < 0.1) {
					System.out.println("15");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("16");
					if (nextNextDistace < 0.04) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.print("154");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if ( 60 < radian && radian <= 100) {
				if (nextDistance < 0.05) {
					System.out.println("17");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("18");
					if (nextNextDistace < 0.03) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.12) {
						System.out.println("175");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.println("176");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if ( 10 < radian && radian <= 60) {
				if (nextDistance < 0.09) {
					System.out.println("55");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("54");
					if (nextNextDistace < 0.06) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.print("152");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else {
				if (nextDistance < 0.1) {
					System.out.println("19");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.2) {
					System.out.println("20");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("21");
					if (nextNextDistace < 0.04) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.print("151");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			}

		} else if (2 < currentSpeed && currentSpeed <= 2.5) {
			if ( 130 < radian && radian <= 180) {
				if (nextDistance < 0.04) {
					System.out.println("125");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.07) {
					System.out.println("22");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("23");
					if (nextNextDistace < 0.03) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.print("150");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if ( 80 < radian && radian <= 130) {
				if (nextDistance < 0.06) {
					System.out.println("125");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.16) {
					System.out.println("22");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("23");
					if (nextNextDistace < 0.04) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							System.out.print("149");
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if (50 < radian && radian <= 80) {
				if (nextDistance < 0.08) {
					System.out.println("24");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("25");
					if (nextNextDistace < 0.03) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.06) {
						System.out.println("147");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.println("148");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if (20 < radian && radian <= 50) {
				if (nextNextDistace < 0.03) {
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.1) {
					System.out.println("26");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("27");
					if (nextNextDistace < 0.07) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else if (nextNextDistace < 0.07) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else {
				if (nextDistance < 0.07) {
					System.out.println("28");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.13) {
					System.out.println("29");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("30");
					if (nextNextDistace < 0.05) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			}
		} else if (1.5 < currentSpeed && currentSpeed <= 2 ) {
			if ( 110 < radian && radian <= 180) {
				if (nextDistance < 0.02) {
					System.out.println("31");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (0.02 <= nextDistance && nextDistance < 0.12) {
					System.out.println("114");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("32");
					if (nextNextDistace < 0.03) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if ( 40 < radian && radian <= 110) {
				if (nextDistance < 0.02) {
					System.out.println("120");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.05) {
					System.out.println("117");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("118");
					if (nextNextDistace < 0.03) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.07) {
						System.out.println("188");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							System.out.println("189");
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if ( 10 < radian && radian <= 40) {
				if (nextDistance < 0.03) {
					System.out.println("123");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.1) {
					System.out.println("119");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("120");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return forwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return forwardright;
					} else {
						return forward;
					}
				}
			} else {
				if (nextDistance < 0.02) {
					System.out.println("35");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (0.02 <= nextDistance && nextDistance < 0.26) {
					System.out.println("36");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("37");
					if (nextNextDistace < 0.03) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.07) {
						System.out.println("185");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							System.out.println("186");
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			}
		} else if (1 < currentSpeed && currentSpeed <= 1.5 ) {
			if (120 < radian && radian <= 180) {
				if (nextDistance < 0.03) {
					System.out.println("187");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.03) {
					System.out.println("38");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("39");
					if (nextNextDistace < 0.05) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.18) {
						System.out.println("145");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						System.out.println("146");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if (2 < radian && radian <= 20) {
				if (nextDistance < 0.02) {
					System.out.println("107");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.07) {
					System.out.println("40");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("41");
					if (nextNextDistace < 0.003) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.005) {
						System.out.println("181");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							System.out.println("180");
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else {
				if (nextDistance < 0.02) {
					System.out.println("42");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (0.02 <= nextDistance && nextDistance < 0.06) {
					System.out.println("43");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("44");
					if (nextNextDistace < 0.008) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.018) {
						System.out.println("181");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							System.out.println("180");
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			}
		} else {
			if (120 < radian && radian <= 180) {
				if (nextDistance < 0.02) {
					System.out.println("106");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (nextDistance < 0.04) {
					System.out.println("103");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("101");
					if (nextNextDistace < 0.009) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.06) {
						System.out.println("181");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							System.out.println("180");
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else if (2 < radian && radian <= 20) {
				if (nextDistance < 0.03) {
					System.out.println("102");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("100");
					if (nextNextDistace < 0.009) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.01) {
						System.out.println("194");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							System.out.println("193");
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
			} else {
				if (nextDistance < 0.018) {
					System.out.println("105");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return backwardleft;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return backwardright;
					} else {
						return backward;
					}
				} else if (0.018 <= nextDistance && nextDistance < 0.034) {
					System.out.println("106");
					if(inputs.getAngleToNextWaypoint() > 0) {
						return left;
					} else if (inputs.getAngleToNextWaypoint() < 0) {
						return right;
					} else {
						return neutral;
					}
				} else {
					System.out.println("190");
					if (nextNextDistace < 0.009) {
						if(inputs.getAngleToNextWaypoint() > 0) {
							return backwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return backwardright;
						} else {
							return backward;
						}
					} else if (nextNextDistace < 0.06) {
						System.out.println("191");
						if(inputs.getAngleToNextWaypoint() > 0) {
							return left;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return right;
						} else {
							return neutral;
						}
					} else {
						if(inputs.getAngleToNextWaypoint() > 0) {
							System.out.println("192");
							return forwardleft;
						} else if (inputs.getAngleToNextWaypoint() < 0) {
							return forwardright;
						} else {
							return forward;
						}
					}
				}
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

		// if(inputs.getAngleToNextWaypoint() > 0) {
		// 	if (com == 0) {
		// 		command = backwardleft;
		// 	} else if (com == 1) {
		// 		command = forwardleft;
		// 	} else {
		// 		command = left;
		// 	}
		// } else {
		// 	if (com == 0) {
		// 		command = backwardright;
		// 	} else if (com == 1) {
		// 		command = forwardright;
		// 	} else {
		// 		command = right;
		// 	}
		// }



		System.out.print(command);
		System.out.print(": ");
		return command;
	}
}
