import java.awt.*;

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
    public Rectangle hitbox;
    //public Rectangle rightBox;
    //public Rectangle leftBox;
    //public Rectangle upBox;
    //public Rectangle downBox;



    //This is a constructor that takes 3 parameters.
    // This allows us to specify the hero's name and position when we build it.
    public Fish(String pName, int pXpos, int pYpos, int pWidth, int pHeight, int pdx, int pdy) { // Fish constructor
        name = pName;
        xpos = pXpos;
        ypos = pYpos;
        dx = pdx;
        dy = pdy;
        width = pWidth;
        height = pHeight;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);
        //rightBox = new Rectangle(xpos, ypos, width, height);
        //leftBox = new Rectangle(xpos, ypos, width, height);
        //upBox = new Rectangle(xpos, ypos, width, 10);
        //downBox = new Rectangle(xpos, ypos, width, 10);

    } // end Astronaut constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() { // move
        xpos = xpos + dx;
        ypos = ypos + dy;

    } // end move
    public void bounce() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        if(xpos >= 1000-width || xpos <= 0) {   //right or left wall
            dx = -dx;
        }
        if(ypos >= 900-height || ypos <= 0) {   //bottom or top wall
            dy = -dy;
        }
        hitbox = new Rectangle(xpos, ypos, width, height);
        //rightBox = new Rectangle(xpos, ypos, width, height);
        //leftBox = new Rectangle(xpos, ypos, width, height);
        //upBox = new Rectangle(xpos, ypos, width, height);
        //downBox = new Rectangle(xpos, ypos, width, height);
    }
    public void wrap() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if(xpos >= 1000+width && dx>0) {   //right to left wall
            xpos = -width;
        }
        if(xpos <= -width && dx<0) {   //left to right wall
            xpos = 1000+width;
        }
        if(ypos >= 1000+height && dy>0) {   //bottom to top wall
            ypos = -height;
        }
        if(ypos <= -height && dy<0) {   //top to bottom wall
            ypos = 1000+height;
        }
        hitbox = new Rectangle(xpos, ypos, width, height);
        //rightBox = new Rectangle(xpos, ypos, width, height);
        // leftBox = new Rectangle(xpos, ypos, width, height);
        //upBox = new Rectangle(xpos, ypos, width, height);
        //downBox = new Rectangle(xpos, ypos, width, height);

    }
}
