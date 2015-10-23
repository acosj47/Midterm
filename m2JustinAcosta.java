//Justin Acosta
//CST 112 eve
//Midterm M1


/*  Tasks
Display 3 different colored balls each with a number
Move balls and have them collide
Screen size should be 640 x 480, table must be centered
Add wall in the middle that makes ball stay on the right of the screen
Add keys 1, 2, 3 to reset balls to the right of table.  "r" resets all balls
"w" removes wall
"p" changes table color to pink
"m" makes animated mouse run across bottom of table
Make buttons that do the same things as the w, p, m keys
Make balls clickable taht reset them the same way keys 1,2,3 work
*/

//Globals
String name = "Justin Acosta";
String title = "M2";
String toggle= "press 'w' or click here to toggle the wall!";
String clear= "press 'r' or click here to reset the table!";
String pink= "press 'p' or click here for pink table!";
String click= "click a ball to reset its position!";
String press= "press key 1, 2, or 3 to reset respective ball!";
String rat= "press 'm' or click here to call mouse!";
float jX, jY, jDX, jDY;  //red ball
float kX, kY, kDX, kDY;  //blue ball
float aX, aY, aDX, aDY;  // yellow ball
float top, bottom, left, right, middle;  // screen postitons
float r, g, b;   // color for felt
float button1X, button1Y, button1W, button1H;      //buttons to click
float button2X, button2Y, button2W, button2H;
float button3X, button3Y, button3W, button3H;
float button4X, button4Y, button4W, button4H;
float miceX, miceY, miceDX;  //globals for rat
boolean wall;
boolean mouse;
int count;


//setup, initial size 640x480  
//made most coordinates relative to height and width
//most things should stay centered if size is changed
 void setup(){
   size(640, 480);
   reset();
}

// starting positions and speeds
 void reset(){
    wall = true;              // wall exists on start and reset
    mouse = false;            // mouse does not start on screen
    top= height*38/100;       //top of center      
    bottom= height*85/100;    //bottom of center
    left= width*10/100;       //left of center
    right=width*91/100;       // right of center
    middle= left + (right-left)/2;  //center
    r= 100;  g= 200;  b= 100;       //starting color of felt
    //ball positions
    jX= random(middle + 15, right);      jY=random(top, bottom);
    kX= random(middle + 15, right);      kY=random(top, bottom);
    aX= random(middle + 15, right);      aY=random(top, bottom);
    //ball speed
    jDX= random(1,6);                    jDY= random(1,6);
    kDX= random(1,6);                    kDY= random(1,6);
    aDX= random(1,6);                    aDY= random(1,6);
   
    //animated mouse position and speed   
    miceX = 0;  
    miceY = height*82/100;  
    miceDX = 3;  //speed

    
    // button coordinates
    button1X = left - 40;       // button1 controls wall
    button1Y = top - 145; 
    button1W = 100;
    button1H = 100;
    
    button2X = button1X +110;    //button2 resets table
    button2Y = top - 145;
    button2W = 100;
    button2H = 100;
    
    button3X = button1X + 220;   // button 3 makes table pink
    button3Y = top - 145;
    button3W = 100;
    button3H = 100;
    
    button4X = button1X + 330;   // button 4 summons animated mouse
    button4Y = top - 145;
    button4W = 100;
    button4H = 100;

}
  
  void draw(){
   count += 1; // count for animation
   background(#21FAEA); 
   table();
   ball();
   action();
   collisions();
   showButton();
   info();
   showMouse();
  }
  
  //shows pool table and wall
  void table(){
    rectMode(CENTER);  // rect mode is center
    fill(#432805);
    rect(width/2, height*62/100, 600, 300);     //boarder
    fill(r, g, b);
    rect(width/2, height*62/100, 550, 250);     //felt
    strokeWeight(20);                          //stroke weight of wall
    stroke(150, 0, 255);                       // wall color
    
    if(wall){                                  //wall
      line(width/2, top, width/2, bottom);
      fill(0);
      textSize(20);
      text("W", middle-10, height/2);
      text("A", middle-8, height/2 + 30);
      text("L", middle-8, height/2 + 60);
      text("L", middle-8, height/2 + 90);
      textSize(12);
    }
    strokeWeight(1);                           //reset stroke weight and color
    stroke(0);
}
  
  // shows 3 different colored, numbered balls
  void ball(){
    
    fill(255, 0, 0);
    ellipse(jX, jY, 30, 30);     //red ball
    fill(50, 50, 255);
    ellipse(kX, kY, 30, 30);     // blue ball
    fill(255, 255, 0);
    ellipse(aX, aY, 30, 30);     //yellow ball
    
    fill(0);                     // numbered balls
    textSize(20);
    text("1", jX-5, jY+5); 
    text("2", kX-5, kY+5);
    text("3", aX-5, aY+5);
    textSize(12);
 }
 
  //move and bounce balls off walls
  void action(){ 
    jX += jDX;  jY += jDY;  //move red ball
    kX += kDX;  kY += kDY;  //move blue ball
    aX += aDX;  aY += aDY;  //move yellow ball
      
//bounce off middle if wall is there
    if(wall){           
   //red ball bounce with wall
      if(jX < middle + 25 || jX > right) jDX *= -1;   
      if(jY < top || jY > bottom) jDY *= -1;
   //blue ball bounce with wall
        if(kX < middle + 25 || kX > right) kDX *= -1; 
        if(kY < top || kY > bottom) kDY *= -1;
   //yellow ball bounce with wall
          if(aX < middle + 25 || aX > right) aDX *= -1;  
          if(aY < top || aY > bottom) aDY *= -1;
   
  }else{  //bounce off left if wall is gone 
    
    //red ball bounce, no wall
       if(jX < left  || jX > right ) jDX *= -1;         
       if(jY < top || jY > bottom) jDY *= -1;
    // blue ball bounce, no wall
         if(kX < left || kX > right) kDX *= -1;          
         if(kY < top || kY > bottom) kDY *= -1;
    // yellow ball bounce, no wall
           if(aX < left || aX > right) aDX *= -1;         
           if(aY < top || aY > bottom) aDY *= -1;
      
    }
}
  
  // makes balls collide and swap speeds
  void collisions(){  
    float tmp;
 
   if (dist(jX, jY, kX, kY) < 30){                //red and blue
     tmp= kDX;  kDX = jDX;  jDX = tmp;
     tmp= kDY;  kDY = jDY;  jDY= tmp;
   } 
   
   
   if (dist(jX, jY, aX, aY) < 30){                //red and yellow
     tmp= aDX;  aDX = jDX;  jDX = tmp;
     tmp= aDY;  aDY = jDY;  jDY= tmp;
      
    }
    
    if (dist(kX, kY, aX, aY) < 30){                //blue and yellow
     tmp= aDX;  aDX = kDX;  kDX = tmp;
     tmp= aDY;  aDY = kDY;  kDY= tmp;
    
    }
}
 
  // displays buttons to be clicked, and their texts
  void showButton(){
     rectMode(CORNER);  // set rect mode to corner
     fill(0);
     rect(button1X, button1Y, button1W, button1H);   // button1
     rect(button2X, button2Y, button2W, button2H);   // button2
     rect(button3X, button3Y, button3W, button3H);   // button3
     rect(button4X, button4Y, button4W, button4H);   // button4
     //text for buttons
     textSize(15);
     fill(255, 255, 0);
     text(toggle, button1X +8, button1Y +5, 100, 100);  //toggle wall text
     text(clear, button2X +8, button2Y +5, 100, 100);   // clear table text
     text(pink, button3X +4 , button3Y +15, 100, 100);  // pink table text
     text(rat, button4X +4, button4Y +10, 100, 100);    //call rat text
     textSize(12);
}
  
   // displays name, title, text information
  void info(){   
    fill(0);
    textSize(20);
    text(name, left*1/3, height*98/100);  //name 
    text(title, left*1/3, height*5/100);   //title
    textSize(15);
    text(click, middle, height*96/100);  // click ball text
    text(press, middle, height*99/100);       // press 1,2,3 text
    textSize(12);  // reset text size to default
  
}
//shows animated mouse at bottom of screen 
void showMouse(){
  
  if(mouse == true){
      miceX += miceDX;
      fill(150);
      ellipse(miceX, miceY, 50, 30);     //body
      fill(255, 200, 200);
      ellipse(miceX + 30, miceY - 15, 30, 10);  //nose
      fill(150);
      ellipse(miceX + 20, miceY - 20, 10, 30);  // ear
      ellipse(miceX + 30, miceY - 20, 10, 30);  //ear
      fill(255, 200, 200);
      ellipse(miceX + 20, miceY - 20, 5, 20);  // inner ear
      ellipse(miceX + 30, miceY - 20, 5, 20);  // inner ear
      fill(150);
      ellipse(miceX + 25, miceY - 15, 30, 20);  // head
      stroke(0);
      strokeWeight(4);
      point(miceX + 23, miceY - 18);   //eye
      point(miceX + 33, miceY - 18);   //eye
      
      if(count/30 % 2 == 0){           //leg animation
        line(miceX - 15, miceY + 10, miceX - 10, miceY + 25);    //legs
        line(miceX + 15, miceY + 10, miceX + 10, miceY + 25);
      }else{
        line(miceX - 15, miceY + 10, miceX - 25, miceY + 25);    //legs
        line(miceX + 15, miceY + 10, miceX + 25, miceY + 25);
        
   }
   
   if(miceX > width){    
       miceX = 0;
    }
    
    stroke(255, 200, 200);
    line(miceX - 20, miceY - 10, miceX - 30, miceY - 30);   // tail
  }
    strokeWeight(1);  // reset stroke weight and color
    stroke(0);
    
    
}

   // gives controlls to keys r, m, w, p, 1, 2 and 3
  void keyPressed(){
   if(key == 'w'){      //w key removes wall, wall becomes false
      wall=!wall;
   }
   if(key == 'r'){      //r key resets scene, puts wall back
      reset();
   }

   if(key == '1'){      // 1 key resets red to right side of screen
      jX= random(middle + 15, right);      jY=random(top, bottom); 
   }  
     
   if(key == '2'){     // 2 key resets blue to the right
       kX= random(middle + 15, right);      kY=random(top, bottom);
   }
   
   if (key == '3'){      // 3 key resets yellow to the right       
       aX= random(middle + 15, right);      aY=random(top, bottom);
   }
   
   if(key == 'p'){       // p key changes felt color to pink
       r=255;
       g=200;
       b=200;
   }
   
   if(key == 'm'){      // m key summons rat
     mouse= !mouse;
   }
}
     
     //left click mouseButton to perform various actions
  void mousePressed(){
   if(mouseButton == LEFT  &&           // left click button1 to remove wall
      mouseX > button1X &&
      mouseX < button1X + button1W &&
      mouseY > button1Y &&
      mouseY < button1Y + button1H){
        wall = !wall;
      }
      
   if(mouseButton == LEFT  &&         // left click button2 to reset table
      mouseX > button2X &&
      mouseX < button2X + button2W &&
      mouseY > button2Y &&
      mouseY < button2Y + button2H){
        reset();
      }
      
   if(mouseButton == LEFT  &&         // left click button 3 to turn table pink
      mouseX > button3X &&
      mouseX < button3X + button3W &&
      mouseY > button3Y &&
      mouseY < button3Y + button3H){
        r=255;
        g=200;
        b=200;
      }
   
   if(mouseButton == LEFT &&     // left click button 4 to call mouse
      mouseX > button4X &&
      mouseX < button4X + button4W &&
      mouseY > button4Y &&
      mouseY < button4Y + button4H){
        mouse = !mouse;
        
      }
    
    if(mouseButton == LEFT)     // left click red ball to reset it
       if(dist(jX, jY, mouseX, mouseY) < 50){
         jX= random(middle + 15, right);      jY=random(top, bottom);
       }
       
    if(mouseButton == LEFT)   // left click blue ball to reset it
       if(dist(kX, kY, mouseX, mouseY) < 50){
         kX= random(middle + 15, right);      kY=random(top, bottom);
       }
       
    if(mouseButton == LEFT)   // left click yellow ball to reset it
       if(dist(aX, aY, mouseX, mouseY) < 50){
          aX= random(middle + 15, right);      aY=random(top, bottom);
       }
      
}

    
    
  
