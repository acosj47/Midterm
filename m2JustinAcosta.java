//Justin Acosta
//CST 112 eve
//Midterm M2


/*  Tasks
 Display 3 different colored balls each with a number
 Move balls and have them collide
 Screen size should be 640 x 480, table must be centered
 Add wall in the middle that makes ball stay on the right of the screen
 Add keys 1, 2, 3 to reset balls to the right of table.  "r" resets all balls
 "w" removes wall
 "p" changes table color to pink
 "m" makes animated rat run across bottom of table
 Make buttons that do the same things as the w, p, m keys
 Make balls clickable that reset them the same way keys 1,2,3 work
 */

//Globals
//Strings
String name = "Justin Acosta";                                 // My name
String title = "M2  CST 112 EVE";                              // Title Midterm 2
String toggle= "press 'w' or click here to toggle the wall!";  // buttonWall text
String clear= "press 'r' or click here to reset the table!";   // buttonReset text
String pink= "press 'p' or click here for pink table!";        // buttonPink text
String rodent= "press 'm' or click here to call rat!";         // buttonRat text
String click= "click a ball to reset its position!";           // click ball text
String press= "press key 1, 2, or 3 to reset respective ball!";// key 1,2,3 text
//Balls
float jackX, jackY, jackDX, jackDY;  // red ball
float kingX, kingY, kingDX, kingDY;  // blue ball
float aceX, aceY, aceDX, aceDY;      // yellow ball
//table
float top, bottom, left, right, middle;  // edges and middle of felt
float r, g, b;   // color for felt
//buttons for wall, reset, rat and table color change
float buttonWallX1, buttonResetX1, buttonPinkX1, buttonRatX1;  // button  x, y globals
float buttonWallX2, buttonResetX2, buttonPinkX2, buttonRatX2;
float buttonY1;
float buttonY2;     
//rat, wall and count
float ratX, ratY, ratDX;  //globals for rat
boolean wall;  // true or false for wall, defined in reset()
boolean rat;   // true or false for rat, defined in reset()
int count;     // count for animation, defined in draw


//setup, initial size was 640x480  
//made most coordinates relative to height and width
//most things should stay centered if size is changed
void setup() {
  size(700, 500);
  reset();
}

// starting positions and speeds
void reset() {
  wall = true;              // wall exists on start and reset
  rat = false;              // rat does not start on screen
  top= height*34/100;       // top edge of felt     
  bottom= height*88/100;    // bottom edge of felt
  left= width*8/100;        // left edge of felt
  right=width*89/100;       // right edge of felt
  middle= left + (right-left)/2;  //center of felt
  r= 100;  
  g= 200;  
  b= 100;       //starting color of felt

  //ball positions
  jackX= random(middle + 50, right - 50);      jackY=random(top + 50, bottom - 50);  // red ball
  kingX= random(middle + 50, right - 50);      kingY=random(top + 50, bottom - 50);  // blue ball
  aceX= random(middle + 50, right - 50);       aceY=random(top + 50, bottom - 50);   // yellow ball
  //ball speed
  jackDX= random(-6, 6);                       jackDY= random(-6, 6);        // red speed
  kingDX= random(-6, 6);                       kingDY= random(-6, 6);        // blue speed
  aceDX= random(-6, 6);                        aceDY= random(-6, 6);         // yellow speed

  //animated rat position and speed   
  ratX = 0;  
  ratY = height*82/100;  
  ratDX = 3;  // rat speed
  
  // button x,y coordinates
  // buttons are made in rectMode CORNERS, requires (x1, y1) and (x2, y2)
  buttonWallX1 = width*4/100;       // buttonWall X1 and X2 
  buttonWallX2 = width*20/100;        

  buttonResetX1 = width*21/100;      // buttonReset X1 and X2
  buttonResetX2 = width*37/100;

  buttonPinkX1 = width*38/100;       // buttonPink X1 and X2
  buttonPinkX2 = width*54/100;

  buttonRatX1 = width*55/100;        // buttonRat X1 and X2
  buttonRatX2 = width*71/100; 
  
  buttonY1 = height*6/100;              // Y1 and Y2 coodinates for buttons
  buttonY2 = height*26/100;               // all 4 buttons have the same y1 and y2 coordinates
}

void draw() {
  count += 1; // count for animation
  background(#21FAEA); 
  table();
  showBall(jackX, jackY, 30, 30, color(255, 0, 0), "1");   // Red ball numbered 1
  showBall(kingX, kingY, 30, 30, color(50, 50, 255), "2"); // Blue ball numbered 2 
  showBall(aceX,  aceY, 30, 30, color(255, 255, 0), "3");  // Yellow ball numbered 3
  action();
  collisions();
  showButton(buttonWallX1, buttonY1, buttonWallX2, buttonY2, toggle);  // buttonWall toggles wall
  showButton(buttonResetX1, buttonY1, buttonResetX2, buttonY2, clear); // buttonReset resets table
  showButton(buttonPinkX1, buttonY1, buttonPinkX2, buttonY2, pink);    // buttonPink turns table pink
  showButton(buttonRatX1, buttonY1, buttonRatX2, buttonY2, rodent);    // buttonRat  summons rat
  info();
  showRat();
}

//shows pool table and wall
void table() {
  rectMode(CORNERS);  // rect mode is corners
  fill(#432805);
  rect( width*4/100, height*29/100, width*93/100, height*93/100);     //boarder
  fill(r, g, b);
  rect(left, top, right, bottom);            //felt

  strokeWeight(20);                          //stroke weight of wall
  stroke(150, 0, 255);                       // wall color
  if (wall) {                                  //wall
    line(middle, top + 6, middle, bottom - 6);
    fill(0);
    textSize(20);
    text("W", middle - 7, height/2);         // wall text
    text("A", middle - 7, height/2 + 30);
    text("L", middle - 7, height/2 + 60);
    text("L", middle - 7, height/2 + 90);
    textSize(12);
  }
  strokeWeight(1);                           //reset stroke weight and color
  stroke(0);
}

// show different colored, numbered balls
void showBall(float x, float y, float w, float h, color c, String z) {
  fill(c);                   
  ellipse(x, y, w, h);       // ball shape
  fill(0);                   // ball text color
  textSize(20);              // ball text size
  text(z, x - 5, y + 5);     // ball text position
  textSize(12);              // reset text size to 12
}

//move and bounce balls off walls
void action() { 
  jackX += jackDX;    jackY += jackDY;  // move red ball
  kingX += kingDX;    kingY += kingDY;  // move blue ball
  aceX += aceDX;      aceY += aceDY;    // move yellow ball

  //bounce off middle if wall is there
  if (wall) {           
    //red ball bounce with wall
    if (jackX < middle + 35 || jackX > right - 15) jackDX *= -1;   
    if (jackY < top + 15 || jackY > bottom - 15) jackDY *= -1;
    //blue ball bounce with wall
    if (kingX < middle + 35 || kingX > right - 15) kingDX *= -1; 
    if (kingY < top + 15|| kingY > bottom - 15) kingDY *= -1;
    //yellow ball bounce with wall
    if (aceX < middle + 35 || aceX > right - 15) aceDX *= -1;  
    if (aceY < top + 15  || aceY > bottom - 15) aceDY *= -1;
    
  } else {  //bounce off left if wall is gone 

    //red ball bounce, no wall
    if (jackX < left + 15  || jackX > right - 15) jackDX *= -1;         
    if (jackY < top + 15 || jackY > bottom - 15) jackDY *= -1;
    // blue ball bounce, no wall
    if (kingX < left + 15|| kingX > right - 15) kingDX *= -1;          
    if (kingY < top + 15 || kingY > bottom - 15) kingDY *= -1;
    // yellow ball bounce, no wall
    if (aceX < left + 15 || aceX > right - 15) aceDX *= -1;         
    if (aceY < top + 15  || aceY > bottom - 15) aceDY *= -1;
  }
}

// makes balls swap speeds when they collide
void collisions() {  
  float tmp;

  if (dist(jackX, jackY, kingX, kingY) < 30) {                //red and blue
    tmp= kingDX;   kingDX = jackDX;   jackDX = tmp;
    tmp= kingDY;   kingDY = jackDY;   jackDY= tmp;
  } 


  if (dist(jackX, jackY, aceX, aceY) < 30) {                //red and yellow
    tmp= aceDX;   aceDX = jackDX;    jackDX = tmp;
    tmp= aceDY;   aceDY = jackDY;    jackDY= tmp;
  }

  if (dist(kingX, kingY, aceX, aceY) < 30) {                //blue and yellow
    tmp= aceDX;   aceDX = kingDX;    kingDX = tmp;
    tmp= aceDY;   aceDY = kingDY;    kingDY= tmp;
  }
}


// displays buttons to be clicked and their text
void showButton(float x1, float y1, float x2, float y2, String z) {
  rectMode(CORNERS);  // set rect mode to corners
  fill(0);
  rect(x1, y1, x2, y2);        // button shape
  textSize(15);                // button text size is 15
  fill(255, 255, 0);           // button text color is yellow
  text(z, x1 + 5, y1, x2, y2); // button text position, the last 2 parameters (x2,y2) determine text wrap-around
  textSize(12);                // reset text size to 12       
}


// displays name, title and button text
void info() {   
  fill(0);
  textSize(20);
  text(name, width*4/100, height*98/100);       //name 
  text(title, width*4/100, height*4/100);       //title
  textSize(15);
  text(click, middle, height*96/100);       // click ball text
  text(press, middle, height*99/100);       // press 1,2,3 text
  textSize(12);  // reset text size to default
}

//shows animated rat at bottom of screen 
void showRat() {
  if (rat == true) {
    ratX += ratDX;
    fill(150);
    ellipse(ratX, ratY, 50, 30);     //body
    fill(255, 200, 200);
    ellipse(ratX + 30, ratY - 15, 30, 10);  //nose
    fill(150);
    ellipse(ratX + 20, ratY - 20, 10, 30);  // ear
    ellipse(ratX + 30, ratY - 20, 10, 30);  //ear
    fill(255, 200, 200);
    ellipse(ratX + 20, ratY - 20, 5, 20);  // inner ear
    ellipse(ratX + 30, ratY - 20, 5, 20);  // inner ear
    fill(150);
    ellipse(ratX + 25, ratY - 15, 30, 20);  // head
    stroke(0);
    strokeWeight(4);
    point(ratX + 23, ratY - 18);   //eye
    point(ratX + 33, ratY - 18);   //eye
    stroke(255, 200, 200);
    line(ratX - 20, ratY - 10, ratX - 30, ratY - 30);   // tail

    stroke(0); // leg color
    if (count/30 % 2 == 0) {           //leg animation
      line(ratX - 15, ratY + 10, ratX - 10, ratY + 25);    //legs
      line(ratX + 15, ratY + 10, ratX + 10, ratY + 25);
    } else {
      line(ratX - 15, ratY + 10, ratX - 25, ratY + 25);    //legs
      line(ratX + 15, ratY + 10, ratX + 25, ratY + 25);
    }

    if (ratX > width + 200) {    //rat starts again at 0 if it goes off screen
      ratX = 0;
    }
  }
  strokeWeight(1);  // reset stroke weight and color
  stroke(0);
}

// gives controlls to keys r, m, w, p, 1, 2 and 3
void keyPressed() {
  if (key == 'w') {      //w key removes wall, wall becomes false
    wall=!wall;
  }
  if (key == 'r') {      //r key resets scene, puts wall back
    reset();
  }

  if (key == '1') {      // 1 key resets red to right side of screen
    jackX= random(middle + 50, right - 50);      
    jackY= random(top + 50, bottom - 50);
  }  

  if (key == '2') {     // 2 key resets blue to the right
    kingX= random(middle + 50, right - 50);      
    kingY= random(top + 50, bottom - 50);
  }

  if (key == '3') {      // 3 key resets yellow to the right       
    aceX= random(middle + 50, right - 50);      
    aceY= random(top + 50, bottom - 50);
  }

  if (key == 'p') {       // p key changes felt color to pink
    r=255;
    g=200;
    b=200;
  }

  if (key == 'm') {      // m key summons rat
    rat= !rat;
  }
}

//left click buttons and balls to perform various actions
void mousePressed() {
  if (mouseButton == LEFT  &&           // left click buttonWall to remove wall
    mouseX > buttonWallX1 &&
    mouseX < buttonWallX2 &&
    mouseY > buttonY1 &&
    mouseY < buttonY2) {
    wall = !wall;
  }

  if (mouseButton == LEFT  &&         // left click buttonReset to reset table
    mouseX > buttonResetX1 &&
    mouseX < buttonResetX2 &&
    mouseY > buttonY1 &&
    mouseY < buttonY2) {
    reset();
  }

  if (mouseButton == LEFT  &&         // left click buttonPink to turn table pink
    mouseX > buttonPinkX1 &&
    mouseX < buttonPinkX2 &&
    mouseY > buttonY1 &&
    mouseY < buttonY2) {
    r=255;
    g=200;
    b=200;
  }

  if (mouseButton == LEFT &&     // left click buttonRat to call rat
    mouseX > buttonRatX1 &&
    mouseX < buttonRatX2 &&
    mouseY > buttonY1 &&
    mouseY < buttonY2) {
    rat = !rat;
  }

  if (mouseButton == LEFT)     // left click red ball to reset it
    if (dist(jackX, jackY, mouseX, mouseY) < 50) {
      jackX= random(middle + 50, right - 50);      
      jackY= random(top + 50, bottom - 50);
    }

  if (mouseButton == LEFT)   // left click blue ball to reset it
    if (dist(kingX, kingY, mouseX, mouseY) < 50) {
      kingX= random(middle + 50, right - 50);      
      kingY= random(top + 50, bottom - 50);
    }

  if (mouseButton == LEFT)   // left click yellow ball to reset it
    if (dist(aceX, aceY, mouseX, mouseY) < 50) {
      aceX= random(middle + 50, right - 50);      
      aceY= random(top + 50, bottom - 50);
    }
}
