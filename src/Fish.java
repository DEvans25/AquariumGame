public class Fish {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;               //name of the hero
    public int xpos;                  //the x position
    public int ypos;                  //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;                 //the width of the hero image
    public int height;                //the height of the hero image
    public boolean isAlive;           //a boolean to denote if the hero is alive or dead


    //This is a constructor that takes 3 parameters.
    // This allows us to specify the hero's name and position when we build it.
    public Fish(String pName, int pXpos, int pYpos, int pWidth, int pHeight) { // Fish constructor
        name = pName;
        xpos = pXpos;
        ypos = pYpos;
        dx = 20;
        dy = 0;
        width = pWidth;
        height = pHeight;
        isAlive = true;

    } // end Astronaut constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() { // move
        xpos = xpos + dx;
        ypos = ypos + dy;

    } // end move
    public void bounce() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        if(xpos >= 500 || xpos <= 0) {   //right or left wall
            dx = -dx;
        }
        if(ypos >= 1000 || ypos <= 0) {   //bottom or top wall
            dy = -dy;
        }
    }
    public void wrap() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        if(xpos >= 1000 || xpos <= 0) {   //right or left wall
            width=-width;
            dx = -dx;
        }
        if(ypos >= 1000 || ypos <= 0) {   //bottom or top wall
            dy = -dy;
        }
    }
}
