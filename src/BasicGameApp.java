//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 1000;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;
    public Image NemoPic;
    public Image DoryPic;
    public Image BrucePic;
    public Image speechBubblePic;
    public Image backgroundPic;

    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    public Fish Nemo;
    public Fish Dory;
    public Fish Bruce;
    public SpeechBubble yum;

    public int timer;
    public int timer2;
    public boolean timerIsRunning;
    public boolean  timer2IsRunning;


    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }

    public int crashes;
    public boolean upCrash;

    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor

        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game and load up
        NemoPic = Toolkit.getDefaultToolkit().getImage("clipart589489.png"); //load the picture
        DoryPic = Toolkit.getDefaultToolkit().getImage("findingdory.png");
        BrucePic = Toolkit.getDefaultToolkit().getImage("brucetheshark.png");
        speechBubblePic = Toolkit.getDefaultToolkit().getImage("Speech-bubble-yum.png");
        backgroundPic = Toolkit.getDefaultToolkit().getImage("cartoon_ocean3.jpg");

        Nemo = new Fish("Nemo",200,100, 100, 70, 20, 10); //construct the fish
        Dory = new Fish("Dory",450,500, 100, 70, 20, 10);
        Bruce = new Fish("Fish", 2000, 2000, 200, 200, 0, 0);
        yum = new SpeechBubble(false,560,440,100,100);


    } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        timerIsRunning = false;
        timer2IsRunning = true;
        timer = 0;
        timer2 = 0;
        Dory.isAlive = true;
        Nemo.isAlive = true;
        yum.isTalking = false;
        System.out.println("Nemo.dx = "+Nemo.dx);
        System.out.println("Nemo.dy = "+Nemo.dy);
        System.out.println("Dory.dx = "+Dory.dx);
        System.out.println("Dory.dy = "+Dory.dy);
        //for the moment we will loop things forever.

        while (true) {
            moveThings();  //move all the game objects
            crash();
            eat();
            runTimer();
            Bruce.hitbox.x = Bruce.xpos;
            Bruce.hitbox.y = Bruce.ypos;
            render();  // paint the graphics
            pause(20); // sleep for 10 ms
        }
    }

    public void moveThings() {
        //calls the move( ) code in the objects
            Nemo.bounce();
            Dory.wrap();
    }
    public void crash() {
        if(Nemo.hitbox.intersects(Dory.hitbox)) {
            Nemo.dx = -Nemo.dx;
            Nemo.dy = -Nemo.dy;
            Dory.dx = -Dory.dx;
            Dory.dy = -Dory.dy;
        }
    }
    public void runTimer() {
        if(timerIsRunning == true) {
            timer = timer + 1;
            System.out.println("Nemo: " + timer);
        }
        if(timer2IsRunning == true) {
            timer2 = timer2 + 1;
            System.out.println("Bruce: " + timer2);
        }
    }
    public void eat() {
        if(timer2 >= 300) {
            timer2 = 0;
            timer2IsRunning = false;
            Bruce.xpos = (int)(Math.random()*500+100);
            Bruce.ypos = (int)(Math.random()*500+100);
        }
        if(Nemo.hitbox.intersects(Bruce.hitbox)) {
            Nemo.isAlive = false;
            yum.isTalking = true;
            timerIsRunning = true;

            if (timer >= 200) {
                Nemo.isAlive = true;
                yum.isTalking = false;
                timerIsRunning = false;
                Nemo.xpos = 200;
                Nemo.ypos = 100;
                Nemo.dx = Math.abs(Nemo.dx);
                Nemo.dy = Math.abs(Nemo.dy);
                timer = 0;
                Bruce.xpos = 2000;
                Bruce.ypos = 2000;
                timer2IsRunning = true;
            }
        }
    }


    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the image of the fish
        g.drawRect(Nemo.hitbox.x, Nemo.hitbox.y, Nemo.hitbox.width, Nemo.hitbox.height);
        g.drawRect(Dory.hitbox.x, Dory.hitbox.y, Dory.hitbox.width, Dory.hitbox.height);
        g.drawRect(Bruce.hitbox.x, Bruce.hitbox.y, Bruce.hitbox.width, Bruce.hitbox.height);

        g.drawImage(backgroundPic, 0, 0, WIDTH, HEIGHT, null);
        if (Nemo.isAlive == true) {
            g.drawImage(NemoPic, Nemo.xpos, Nemo.ypos, Nemo.width, Nemo.height, null);
        }
        if (Dory.isAlive == true){
            g.drawImage(DoryPic, Dory.xpos, Dory.ypos, Dory.width, Dory.height, null);
        }

        g.drawImage(BrucePic, Bruce.xpos, Bruce.ypos, Bruce.width, Bruce.height, null);
        if(yum.isTalking == true) {
            g.drawImage(speechBubblePic, Bruce.xpos + 65, Bruce.ypos - 60, yum.width, yum.height, null);
        }

        //g.drawRect(Nemo.leftBox.x, Nemo.leftBox.y, 1, Nemo.leftBox.height);
        //g.drawRect(Dory.leftBox.x, Dory.leftBox.y, 1, Dory.leftBox.height);
        //g.drawRect(Nemo.rightBox.x + Nemo.width, Nemo.rightBox.y, 10, Nemo.rightBox.height);
        //g.drawRect(Dory.rightBox.x + Dory.width, Dory.rightBox.y, 10, Dory.rightBox.height);
        //g.drawRect(Nemo.upBox.x, Nemo.upBox.y, Nemo.upBox.width, 10);
        //g.drawRect(Dory.upBox.x, Dory.upBox.y, Nemo.upBox.width, 10);
        //g.drawRect(Nemo.downBox.x, Nemo.downBox.y + Nemo.height, Nemo.downBox.width, 10);
        //g.drawRect(Dory.downBox.x, Dory.downBox.y + Dory.height, Nemo.downBox.width, 10);





        g.dispose();
        bufferStrategy.show();
    }
}