public class Lab3
{
    public static void testLightCandles1()
    {
        Robot.load("candles1.txt");
        Robot.setDelay(0.05);
        lightCandles();
    }

    public static void testLightCandles2()
    {
        Robot.load("candles2.txt");
        Robot.setDelay(0.05);
        lightCandles();
    }

    public static void lightCandles()
    {
        Robot.turnLeft();
        moveAndTurnRight();
        lightFiveCandles();
        lightFiveCandles();

    }
    public static void turnRight(){
        //makes robot turn right
        Robot.turnLeft();
        Robot.turnLeft();
        Robot.turnLeft();
    }

    public static void testCandle(){
        /* precondition: Robot is in the second row facing east
         * postCondition: Robot has lit the "candle" formerly in front of it
         * and has moved 2 units east (still facing east)
         */
        if(Robot.frontIsClear()){
            moveAndMakeDark();
            Robot.move();
        }else{
            Robot.turnLeft();
            moveAndTurnRight();
            moveAndMakeDark();
            moveAndTurnRight();
            Robot.move();
            Robot.turnLeft();
        }
    }

    public static void moveAndMakeDark(){
        //robot moves forward one unit and makes new space dark
        Robot.move();
        Robot.makeDark();
    }

    public static void moveAndTurnRight(){
        //robot moves forward one unit and turns right
        Robot.move();
        turnRight();
    }

    public static void lightFiveCandles(){
        /* precondition: Robot is in second row facing east
         * postcondition: Robot has lit 5 "candles" that were formerly in front
         * of it and has moved ten units east (still facing east)
         */
        testCandle();
        testCandle();
        testCandle();
        testCandle();
        testCandle();
    }

    //Run this method to test completeRoom on map room1.txt
    public static void testCompleteRoom1()
    {
        Robot.load("room1.txt");
        Robot.setDelay(0.05);
        completeRoom();
    }

    //Run this method to test completeRoom on map room2.txt
    public static void testCompleteRoom2()
    {
        Robot.load("room2.txt");
        Robot.setDelay(0.05);
        completeRoom();
    }

    //Complete this method.  You will need to write additional helper methods.
    public static void completeRoom()
    {
        fillSide();
        fillSide();
        fillSide();
        fillSide();

    }

    public static void fillWall(){
        /* precondition: Robot is on a space next to the wall, facing parallel
         * to the wall. If the wall next to it is not there/isn't already
         * darkened, the robot hasn't darkened it yet.
         * postcondition: Robot is facing the same direction and in the same
         * space as before and the wall has been darkened if not already
         * there/darkened
         */
        Robot.turnLeft();
        if(Robot.frontIsClear()){
            Robot.move();
            if(Robot.onDark()){
                exitWall();
            }else{
                Robot.makeDark();
                exitWall();
            }
        }else{
            turnRight();
        }
    }

    public static void exitWall(){
        /* precondition: Robot is in an outer space of window (wall), facing
         * away from the window
         * post condition: Robot moved one unit away from the edge of the
         * window and turned right
         */
        turnAround();
        Robot.move();
        Robot.turnLeft();
    }

    public static void turnAround(){
        //makes robot turn around
        Robot.turnLeft();
        Robot.turnLeft();
    }

    public static void fillSide(){
        /* precondition: Robot is at a corner of the inner square, facing
         * a side where the walls are not filled.
         * postcondition: Robot is at the other corner of that side of the
         * inner square, turned right, and all empty walls from that side are
         * darkened.
         */
        fillWall();
        Robot.move();
        fillWall();
        Robot.move();
        fillWall();
        Robot.move();
        fillWall();
        Robot.move();
        fillWall();
        turnRight();
    }

    //Run this method to test swapAll on map swap1.txt
    public static void testSwapAll1()
    {
        Robot.load("swap1.txt");
        Robot.setDelay(0.05);
        swapAll();
    }

    //Run this method to test swapAll on map swap2.txt
    public static void testSwapAll2()
    {
        Robot.load("swap2.txt");
        Robot.setDelay(0.05);
        swapAll();
    }

    //Complete this method.  You will need to write additional helper methods.
    public static void swapAll()
    {
        turnRight();
        Robot.move();
        Robot.turnLeft();
        switchAllRows();
    }

    public static void moveTwice(){
        //robot moves forward 2 units
        Robot.move();
        Robot.move();
    }

    public static void turnAroundAndCrossRow(){
        /* precondition: robot is on a space at the end of a row facing away
         * from the window
         * postcondition: robot is at the other end of the row (moved two
         * units backward) and is facing the opposite direction
         */
        turnAround();
        moveTwice();
    }

    public static void testOppositeLight(){
        /* Tests whether the space is dark or light. If dark, the robot makes
         * the space light and makes the opposite space dark (this only runs
         * under the condition that that opposite space is light). If light,
         * the robot turns around and goes back to the other space, not
         * changing the color of either space.
         */

        if(Robot.onDark()){
            Robot.makeLight();
            turnAroundAndCrossRow();
            Robot.makeDark();
        }else{
            turnAroundAndCrossRow();
        }
    }

    public static void testOppositeDark(){
        /* Tests whether the space is dark or light. If dark, the robot turns
         * around and goes back to the opposite space, not changing the color
         * of either space. If light, the robot makes the space dark and makes
         * the opposite space light (this only runs under the condition that
         * that opposite space is dark).
         */
        if(Robot.onDark()){
            turnAroundAndCrossRow();
        }else{
            Robot.makeDark();
            turnAroundAndCrossRow();
            Robot.makeLight();
        }
    }

    public static void switchColors(){
        /* Tests whether the space is dark or light. If the space is dark, the
         * robot moves to the opposite space and if it's also dark returns to
         * the original space, else makes the space dark and makes the original
         * space light. If the space is light, the robot moves to the opposite
         * space and if it's also light returns to the original space, else
         * makes the space light and makes the original space dark.
         */
        if(Robot.onDark()){
            Robot.turnLeft();
            moveTwice();
            testOppositeDark();
        }else{
            Robot.turnLeft();
            moveTwice();
            testOppositeLight();
        }
    }

    public static void switchColorsAndMoveUp(){
        /* precondition: Robot is facing north on a row where the sides have not
         * been switched.
         * postcondition: Robot is facing north, the sides have been switched
         * and has moved one unit north from its previous position.
         */
        switchColors();
        Robot.turnLeft();
        Robot.move();
    }

    public static void switchAllRows(){
        /* precondition: Robot is in bottom right corner space facing north.
         * None of the rows have had the colors switched.
         * post condition: Robot is in the top right corner space facing east.
         * All of the rows have had the colors switched.
         */
        switchColorsAndMoveUp();
        switchColorsAndMoveUp();
        switchColorsAndMoveUp();
        switchColorsAndMoveUp();
        switchColorsAndMoveUp();
        switchColorsAndMoveUp();
        switchColorsAndMoveUp();
        switchColorsAndMoveUp();
        switchColorsAndMoveUp();
        switchColors();
    }
    //Don't run these. I will!
    public static void testLightCandles3()
    {
        Robot.load("candles3.txt");
        Robot.setDelay(0.05);
        lightCandles();
    }

    public static void testLightCandles4()
    {
        Robot.load("candles4.txt");
        Robot.setDelay(0.05);
        lightCandles();
    }

    public static void testCompleteRoom3()
    {
        Robot.load("room3.txt");
        Robot.setDelay(0.05);
        completeRoom();
    }

    public static void testCompleteRoom4()
    {
        Robot.load("room4.txt");
        Robot.setDelay(0.05);
        completeRoom();
    }

    public static void testSwapAll3()
    {
        Robot.load("swap3.txt");
        Robot.setDelay(0.05);
        swapAll();
    }

    //Run this method to test swapAll on map swap2.txt
    public static void testSwapAll4()
    {
        Robot.load("swap4.txt");
        Robot.setDelay(0.05);
        swapAll();
    }
}
