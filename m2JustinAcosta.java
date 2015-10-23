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
"m" makes animated rat run across bottom of table
Make buttons that do the same things as the w, p, m keys
Make balls clickable that reset them the same way keys 1,2,3 work
*/

//Globals
String name = "Justin Acosta";
String title = "M2  CST 112 EVE";
String toggle= "press 'w' or click here to toggle the wall!";
String clear= "press 'r' or click here to reset the table!";
String pink= "press 'p' or click here for pink table!";
String click= "click a ball to reset its position!";
String press= "press key 1, 2, or 3 to reset respective ball!";
String rodent= "press 'm' or click here to call rat!";
float jackX, jackY, jackDX, jackDY;  // red ball
float kingX, kingY, kingDX, kingDY;  // blue ball
float aceX, aceY, aceDX, aceDY;      // yellow ball
float top, bottom, left, right, middle;  // boarders and middle of table
float r, g, b;   // color for felt
float button1X, button2X, button3X, button4X;  // button  x, y, w, h globals
float buttonY;
float buttonW, buttonH;     
float ratX, ratY, ratDX;  //globals for rat
boolean wall;  // true or false for wall, defined in reset()
boolean rat; // true or false for rat, defined in reset()
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
    rat = false;              // rat does not start on screen
    top= height*38/100;       // top boarder of table      
    bottom= height*85/100;    // bottom boarder of table
    left= width*10/100;       // left boarder of table
    right=width*91/100;       // right oboarder of table
    middle= left + (right-left)/2;  //center of table
    r= 100;  g= 200;  b= 100;       //starting color of felt
    //ball positions
    jackX= random(middle + 15, right);      jackY=random(top, bottom);  // red ball
    kingX= random(middle + 15, right);      kingY=random(top, bottom);  // blue ball
    aceX= random(middle + 15, right);       aceY=random(top, bottom);   // yellow ball
    //ball speed
    jackDX= random(1,6);                    jackDY= random(1,6);        // red speed
    kingDX= random(1,6);                    kingDY= random(1,6);        // blue speed
    aceDX= random(1,6);                     aceDY= random(1,6);         // yellow speed
    
    //animated rat position and speed   
    ratX = 0;  
    ratY = height*82/100;  
    ratDX = 3;  // rat speed

    // button x,y coordinates &  width, height
    button1X = left - 40;       // button1 controls wall
    button2X = button1X +110;    //button2 resets table
    button3X = button1X + 220;   // button 3 makes table pink
    button4X = button1X + 330;   // button 4 summons animated rat
    buttonY = top - 145;         
    buttonW = 100;
    buttonH = 100;
    
}
  
  void draw(){
   count += 1; // count for animation
   background(#21FAEA); 
   table();
   ball();
   action();
   collisions();
   showButton(button1X, buttonY, buttonW, buttonH);
   showButton(button2X, buttonY, buttonW, buttonH);
   showButton(button3X, buttonY, buttonW, buttonH);
   showButton(button4X, buttonY, buttonW, buttonH);
   info();
   showRat();
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
      text("W", middle-10, height/2);         // wall text
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
    ellipse(jackX, jackY, 30, 30);     //red ball
    fill(50, 50, 255);
    ellipse(kingX, kingY, 30, 30);     // blue ball
    fill(255, 255, 0);
    ellipse(aceX, aceY, 30, 30);     //yellow ball
    
    fill(0);                     // numbered balls
    textSize(20);
    text("1", jackX-5, jackY+5);    //  red is 1
    text("2", kingX-5, kingY+5);    // blue is 2
    text("3", aceX-5,  aceY+5);     // yellow is 3
    textSize(12);
 }
 
  //move and bounce balls off walls
  void action(){ 
    jackX += jackDX;  jackY += jackDY;  //move red ball
    kingX += kingDX;  kingY += kingDY;  //move blue ball
    aceX += aceDX;    aceY += aceDY;   //move yellow ball
      
//bounce off middle if wall is there
    if(wall){           
   //red ball bounce with wall
      if(jackX < middle + 25 || jackX > right) jackDX *= -1;   
      if(jackY < top || jackY > bottom) jackDY *= -1;
   //blue ball bounce with wall
        if(kingX < middle + 25 || kingX > right) kingDX *= -1; 
        if(kingY < top || kingY > bottom) kingDY *= -1;
   //yellow ball bounce with wall
          if(aceX < middle + 25 || aceX > right) aceDX *= -1;  
          if(aceY < top || aceY > bottom) aceDY *= -1;
   
  }else{  //bounce off left if wall is gone 
    
    //red ball bounce, no wall
       if(jackX < left  || jackX > right ) jackDX *= -1;         
       if(jackY < top || jackY > bottom) jackDY *= -1;
    // blue ball bounce, no wall
         if(kingX < left || kingX > right) kingDX *= -1;          
         if(kingY < top || kingY > bottom) kingDY *= -1;
    // yellow ball bounce, no wall
           if(aceX < left || aceX > right) aceDX *= -1;         
           if(aceY < top || aceY > bottom) aceDY *= -1;
      
    }
}
  
  // makes balls collide and swap speeds
  void collisions(){  
    float tmp;
 
   if (dist(jackX, jackY, kingX, kingY) < 30){                //red and blue
     tmp= kingDX;  kingDX = jackDX;  jackDX = tmp;
     tmp= kingDY;  kingDY = jackDY;  jackDY= tmp;
   } 
   
   
   if (dist(jackX, jackY, aceX, aceY) < 30){                //red and yellow
     tmp= aceDX;  aceDX = jackDX;  jackDX = tmp;
     tmp= aceDY;  aceDY = jackDY;  jackDY= tmp;
      
    }
    
    if (dist(kingX, kingY, aceX, aceY) < 30){                //blue and yellow
     tmp= aceDX;  aceDX = kingDX;  kingDX = tmp;
     tmp= aceDY;  aceDY = kingDY;  kingDY= tmp;
    
    }
}
 
  
  // displays buttons to be clicked
  void showButton(float x, float y, float w, float h){
     rectMode(CORNER);  // set rect mode to corner
     fill(0);
     rect(x, y, w, h);   // button 

     
}
  
  
   // displays name, title and button text
  void info(){   
    fill(0);
    textSize(20);
    text(name, left*1/3, height*98/100);  //name 
    text(title, left*1/3, height*5/100);   //title
    textSize(15);
    text(click, middle, height*96/100);  // click ball text
    text(press, middle, height*99/100);       // press 1,2,3 text
    textSize(12);  // reset text size to default
    
    //text for buttons
     textSize(15);
     fill(255, 255, 0);
     text(toggle, button1X + 5, buttonY, buttonW, buttonH);  //  button 1 toggle wall text
     text(clear, button2X + 5, buttonY, buttonW, buttonH);   //  button 2 clear table text
     text(pink, button3X + 5, buttonY, buttonW, buttonH);    //  button 3 pink table text
     text(rodent, button4X + 5, buttonY, buttonW, buttonH);  //  button 4 call rat text
     textSize(12);
  
}
//shows animated rat at bottom of screen 
void showRat(){
  
  if(rat == true){
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
      
      if(count/30 % 2 == 0){           //leg animation
        line(ratX - 15, ratY + 10, ratX - 10, ratY + 25);    //legs
        line(ratX + 15, ratY + 10, ratX + 10, ratY + 25);
      }else{
        line(ratX - 15, ratY + 10, ratX - 25, ratY + 25);    //legs
        line(ratX + 15, ratY + 10, ratX + 25, ratY + 25);
        
   }
   
   if(ratX > width + 200){    //rat starts again at 0 if it goes off screen
       ratX = 0;
    }
    
    stroke(255, 200, 200);
    line(ratX - 20, ratY - 10, ratX - 30, ratY - 30);   // tail
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
      jackX= random(middle + 15, right);      jackY=random(top, bottom); 
   }  
     
   if(key == '2'){     // 2 key resets blue to the right
       kingX= random(middle + 15, right);      kingY=random(top, bottom);
   }
   
   if (key == '3'){      // 3 key resets yellow to the right       
       aceX= random(middle + 15, right);      aceY=random(top, bottom);
   }
   
   if(key == 'p'){       // p key changes felt color to pink
       r=255;
       g=200;
       b=200;
   }
   
   if(key == 'm'){      // m key summons rat
     rat= !rat;
   }
}
     
     //left click buttons and balls to perform various actions
  void mousePressed(){
   if(mouseButton == LEFT  &&           // left click button1 to remove wall
      mouseX > button1X &&
      mouseX < button1X + buttonW &&
      mouseY > buttonY &&
      mouseY < buttonY + buttonH){
        wall = !wall;
      }
      
   if(mouseButton == LEFT  &&         // left click button2 to reset table
      mouseX > button2X &&
      mouseX < button2X + buttonW &&
      mouseY > buttonY &&
      mouseY < buttonY + buttonH){
        reset();
      }
      
   if(mouseButton == LEFT  &&         // left click button 3 to turn table pink
      mouseX > button3X &&
      mouseX < button3X + buttonW &&
      mouseY > buttonY &&
      mouseY < buttonY + buttonH){
        r=255;
        g=200;
        b=200;
      }
   
   if(mouseButton == LEFT &&     // left click button 4 to call rat
      mouseX > button4X &&
      mouseX < button4X + buttonW &&
      mouseY > buttonY &&
      mouseY < buttonY + buttonH){
        rat = !rat;
        
      }
    
    if(mouseButton == LEFT)     // left click red ball to reset it
       if(dist(jackX, jackY, mouseX, mouseY) < 50){
         jackX= random(middle + 15, right);      jackY=random(top, bottom);
       }
       
    if(mouseButton == LEFT)   // left click blue ball to reset it
       if(dist(kingX, kingY, mouseX, mouseY) < 50){
         kingX= random(middle + 15, right);      kingY=random(top, bottom);
       }
       
    if(mouseButton == LEFT)   // left click yellow ball to reset it
       if(dist(aceX, aceY, mouseX, mouseY) < 50){
          aceX= random(middle + 15, right);      aceY=random(top, bottom);
       }
      
}

    
    
  
