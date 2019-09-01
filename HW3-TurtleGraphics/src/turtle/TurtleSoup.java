// Alec Bird, Chao ZHai, Rui Tang, Jack Schroer
// CSE 211
// Homework 3
// 3/20/19



/* Copyright (c) 2007-2014 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	if (sideLength < 0) {
    		throw new RuntimeException("Negative forward movement");
    	}
    	for (int i = 0; i < 4; i++) {
    		turtle.forward(sideLength);
        	turtle.turn(90);
    	}
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	if (sides <= 2) {
    		throw new RuntimeException("Sides must be greater than 2");
    	}
    	double interiorAngle = (sides - 2) * 180.0 / sides;
    	
        return interiorAngle;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
    	
    	double exteriorAngle = 180 - angle;
    	double numSides = 360 / exteriorAngle;
    	
        return Math.round((float)numSides);
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
    	double angle = calculateRegularPolygonAngle(sides);
    	double turnAmount = 180 - angle;
    	for (int i = 0; i < sides; i++) {
    		turtle.forward(sideLength);
    		turtle.turn(turnAmount);
    	}
    	
    }
    
    /**
     * Helper method for calculateHeadingsToPoint
     * @param an
     * @return
     */
    public static double transfer(double an) {
		if (an==360) return 0;
		if (an>0) return an;
		if(an<0) return 360+an;
		
		else return 0;
	}

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX currentY current location
     * @param targetX targetY target point
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360.
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	  double an=0;
      	int relativeX =  targetX-currentX;
      int relativeY =  targetY-currentY;
      	if (relativeX ==0&&relativeY==0)  an =0;
      	else 	if(relativeX==0) {
      		if (relativeY>0) an = transfer(360-currentHeading); 
      		if (relativeY<0) an =transfer(180-currentHeading);
      	}
      	else if (relativeY==0) {
      	if (relativeX>0) an = transfer( 90-currentHeading); 
  		if (relativeX<0) an =transfer(270-currentHeading);
      	
      }
      	else if (relativeX>0) {
      		if (relativeY>0) an = transfer(90-currentHeading-Math.atan(relativeY/relativeX)/Math.PI*180);
      		if (relativeY<0) an = transfer(90+Math.atan(-1*relativeY/relativeX)/Math.PI*180 -currentHeading);
      	}
      	else if (relativeX<0) {
      		if (relativeY>0) an = transfer(270+Math.atan(relativeY/relativeX*-1)/Math.PI*180-currentHeading);
      		if (relativeY<0) an = transfer(270-Math.atan(-1*relativeY/relativeX*-1)/Math.PI*180 -currentHeading);
      		
      		
      	}
      	return an;
    	
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size (# of points) - 1.
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
    	
    	double currentHeading =0.0; 
   	 List<Double> an=new ArrayList<Double>();
   	 for (int i=0; i<xCoords.size()-1;i++) {
   		 
   		 currentHeading =calculateHeadingToPoint(currentHeading, xCoords.get(i), yCoords.get(i),
   				 xCoords.get(i+1), yCoords.get(i+1) ); 
   		 an.add(currentHeading);
   		 
   	 }
		return an;
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * We'll be peer-voting on the different images, and the highest-rated one will win a prize. 
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	 int x = 10;
         int y = 1;
         int z = 0;
         ArrayList<PenColor> array = new ArrayList<PenColor>();
         array.add(PenColor.BLACK);
         array.add(PenColor.GRAY);
         array.add(PenColor.RED);
         array.add(PenColor.PINK);
         array.add(PenColor.ORANGE);
         array.add(PenColor.CYAN);
         array.add(PenColor.MAGENTA);
         
         //Draw initial circle
         for(int o=0;o<36;o++) {
         	for(int i=0; i<36;i++) {
         		turtle.turn(5);	
         		turtle.forward(x);
         		turtle.color(array.get((int)(Math.random()*7)));
         	}
         	x-=1;
         	
         	turtle.color(array.get((int)(Math.random()*7)));
         	
         }
    }
	
	 /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
       
        // drawSquare(turtle, 40);
        // drawRegularPolygon(turtle, 8, 40);
        drawPersonalArt(turtle);

        // draw the window
        turtle.draw();
       
    }

}
