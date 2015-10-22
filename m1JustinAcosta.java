//Justin Acosta
//CST 112 eve
//Midterm M1


/*  display 3 different colored balls each with a number
Move balls and have them collide

screen size should be 640 x 480, table must be centered

add wall in the middle that makes ball stay on the right of the screen

add keys 1, 2, 3 to reset balls to the right of table.  "r" resets all balls

"w" removes wall

"p" changes table color to pink

"m" makes animated mouse run across bottom of table

*/

//ball globals
float jX, jY, jDX, jDY;
float kX, kY, kDX, kDY;
float aX, aY, aDX, aDY;
float top, bottom, left, right, middle;
boolean wall = true;
boolean mouse= false;


//setup, window 640x480
void setup(){
  size(640, 480);
  reset();
  
}
// starting positions and speeds
void reset(){
  top= height-305;              //top was 175
  bottom= height-75;
  left= width/2-260;
  right=width/2+260;
  middle= left + (right-left)/2;
  //ball positions
  jX= random(middle, right);      jY=random(top, bottom);
  kX= random(middle, right);      kY=random(top, bottom);
  aX= random(middle, right);      aY=random(top, bottom);
  //ball speed
  jDX= random(-6,6);                    jDY= random(-6,6);
  kDX= random(-6,6);                    kDY= random(-6,6);
  aDX= random(-6,6);                    aDY= random(-6,6);
  
}
  
  void draw(){
   background(#21FAEA); 
   table();
   ball();
   action();
  }
  
  //pool table
  void table(){
    rectMode(CENTER);  // rect mode is center
    fill(#432805);
    rect(width/2, height/2 + 50, 600, 300);     //boarder
    fill(100, 200, 100);
    rect(width/2, height/2 + 50, 550, 250);     //felt
    strokeWeight(20);                          //stroke weight of wall
    stroke(150, 0, 255);                       // wall color
    
    if(wall){                                  //wall
      line(width/2, top, width/2, bottom);
    }
    
    strokeWeight(1);                           //reset stroke weight and color
    stroke(0);
    
  }
  // show ball
  void ball(){
    fill(255, 0, 0);
    ellipse(jX, jY, 30, 30);     //red ball
    fill(0, 0, 255);
    ellipse(kX, kY, 30, 30);     // blue ball
    fill(255, 255, 0);
    ellipse(aX, aY, 30, 30);     //yellow ball
    
  }
  
  void action(){   //bounce balls off walls
  
  
  jX += jDX;
  if(jX > right || jX < left) jDX *= -1;
 
  jY += jDY;
  if(jY < top   || jY > bottom) jDY *= -1;
  
  kX += kDX;
  if(kX > right || kX < left) kDX *= -1;
 
  kY += kDY;
  if(kY < top   || kY > bottom) kDY *= -1;
  
  aX += aDX;
  if(aX > right || aX < left) aDX *= -1;
  
  aY += aDY;
  if(aY < top   || aY > bottom) aDY *= -1;
  
  
    //if(wall){
    //}
  }
  
  
    
    
  
