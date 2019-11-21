import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 
import ddf.minim.analysis.*; 
import ddf.minim.effects.*; 
import ddf.minim.signals.*; 
import ddf.minim.spi.*; 
import ddf.minim.ugens.*; 
import peasy.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class TheMatrix3D extends PApplet {









float xRotate = 0;
float yRotate = 0;
float zRotate = 0;
float zoom = -500;
float green = 0;
float goX = 0;
float goY = 0;

boolean[] dir = new boolean[4];
boolean[] rotate = new boolean[4];
boolean[] upDown = new boolean[4];
Minim minim;

AudioPlayer song;
AudioInput input;
FFT         fft;
PFont theMatrix;
PFont theSource;

Timer timer;

Letras letras;
PeasyCam cam;
ParticleSystem ps;
TheSource source;

//int W = 800;
//int H = 600;

float yR ;

public void setup() {
  //fullScreen();
  
  frameRate(24);
  //cam = new PeasyCam(this, 400, 300, 0, -500);
  minim = new Minim(this);
  input = minim.getLineIn();
  fft = new FFT(input.bufferSize(), input.sampleRate());
  song = minim.loadFile("The Matrix Opening Scene HD.mp3", 1024);
  fft = new FFT(song.bufferSize(), input.sampleRate());
  letras = new Letras();
  ps = new ParticleSystem();
  source = new TheSource(10,10,10/*random(-3,3),random(-3,3),random(-3,3)*/, 5);
  timer = new Timer(35);
//theMatrix = new PFont();
theMatrix = createFont("MS Gothic", 24, true);//Yu Gothic UI Semibold
theSource = createFont("MS Gothic", 10, true);//Yu Gothic UI Semibold
textAlign(CENTER,CENTER);
}

public void draw() {
  background(0,green,0);
//camera();
//lights();
  //pointLight(0,0,0,255,255,255);
//println(timer.getTime());
  fft.forward(input.mix);
  song.play();
  fft.forward(song.mix);
 // green = fft.getBand(2);
  translate(width/2,height/2);
  rotateX(xRotate);
  rotateY(yRotate);
  rotateZ(zRotate);
  noFill();
  pushMatrix();//letras.update();
  translate(goX, goY, zoom);
  //source.update();
  //source.display();
//for (int i = 1; i < 5; i++) {
  //ps.addSource(random(-1*i,1*i),random(-1*i,1*i),random(-1*i,1*i),i);
  //ps.runSource();
//}
  timer.countDown();
  if (timer.getTime() <=11) { ps.addSource(); ps.runSource(); stroke(255); sphere(2+fft.getBand(12)); }
  if (timer.getTime() <= 7) { ps.addSource1(); ps.runSource1(); }
  if (timer.getTime() <= 4) { ps.addSource2(); ps.runSource2(); }
  if (timer.getTime() <= 3) { ps.addSource3(); ps.runSource3();   }

  //ps.addSource();
  //ps.runSource();
  //ps.addSource1();
  //ps.runSource1();
  //ps.addSource2();
  //ps.runSource2();
  //ps.addSource3();
  //ps.runSource3();
  
  ps.addLetras();
  ps.runLetras();
  ps.run();
  popMatrix();
if (rotate[0]) {
  yRotate -= 0.02f;
} else if (rotate[1]) {
  yRotate += 0.02f;
}

if (rotate[2]) {
  zRotate += 0.02f;
} else if (rotate[3]) { 
  zRotate -= 0.02f;
}

if (dir[0]) {
  goX += 5;
} else if (dir[1]) {
  goX -= 5;
}

if (dir[2]) {
  zoom += 5;
} else if (dir[3]) { 
  zoom -= 5;
}

  if (upDown[0]) {
    xRotate -= 0.02f;
  } else if (upDown[1]) {
    xRotate += 0.02f;
  }
  
  if (upDown[2]) {
    goY -= 5;
  } else if (upDown[3]) {
    goY += 5;
  }

}

public void keyPressed() {
  if (keyCode == LEFT) {
    dir[0] = true;
  }
  if (keyCode == RIGHT) {
    dir[1] = true;
  }

  if (keyCode == UP) {
    dir[2] = true;
  }
  if (keyCode == DOWN) {
    dir[3] = true;
  }

  if (key == 'a') {
    rotate[0] = true;
  }
  if (key == 'd') {
    rotate[1] = true;
  }

  if (key == 'w') {
    rotate[2] = true;
  }
  if (key == 's') {
    rotate[3] = true;
  }

  if (key == 'z') {
    upDown[0] = true;
  } else if (key == 'x') {
    upDown[1] = true;
  }
  
  if (key == 'y') {
    upDown[2] = true;
  } else if (key == 'u') {
    upDown[3] = true;
  }

}

public void keyReleased() {
  if (keyCode == LEFT) {
    dir[0] = false;
  }
  if (keyCode == RIGHT) {
    dir[1] = false;
  }

  if (keyCode == UP) {
    dir[2] = false;
  }
  if (keyCode == DOWN) {
    dir[3] = false;
  }
  
  if (key == 'a') {
    rotate[0] = false;
  }
  if (key == 'd') {
    rotate[1] = false;
  }

  if (key == 'w') {
    rotate[2] = false;
  }
  if (key == 's') {
    rotate[3] = false;
  }
  
  if (key == 'z') {
    upDown[0] = false;
  } else if (key == 'x') {
    upDown[1] = false;
  }
  
  if (key == 'y') {
    upDown[2] = false;
  } else if (key == 'u') {
    upDown[3] = false;
  }

}
class Letras {
  float x;
  float y;
  float xspeed = 0;
  float yspeed = 20;//random(-2,2);
  float rad = 8;
  float z = rad;
  char chr;
  int num;
  //float acc = 0.2019;

  Letras() {  // 3D style
    x = map(random(800), 0, 800, -width, width);
    y = -600;//random(-900,0);
    z = map(random(600), 0, 600, -800, 800);
  }

  //  Letras() {  // 2D style
  //  x =random(800);
  //  y = -200;//random(-900,0);
  //  //z = map(random(600), 0, 600, -900, 600);
  //}

  public void update() {
    //xspeed *= 0.99802031;
    //x += xspeed;
    y += yspeed;
  }

  public boolean isDead() {
    if (y > height+600) {
      return true;
    } else {
      return false;
    }
  }

  public void show() {
    //stroke(255,255,0);
    //pushMatrix();
    //translate(x,y,-z);
    //sphere(rad);
    //num = int( random(33,126));
   // textSize(10);
    chr = PApplet.parseChar(PApplet.parseInt( random(65382, 65439)));// japanese fonts 12449, 12534 */* 65382, 65439
    fill(255,255,255,50);
    textFont(theMatrix,36);
    text(chr, x, y, z);
    fill(255,255,255,75);
    textFont(theMatrix,30);
    text(chr, x, y, z);
    fill(255,255,255,255);
    textFont(theMatrix,24);
    text(chr, x, y, z);

    ps.addParticle(x, y-yspeed, z, chr);
    
    //popMatrix();
  }
}
class Particle {
  
  PVector location;
  PVector velocity;
  PVector acceleration;
  PVector col;
  char ch;
  char temp;
  
  float lifeSpan = 205;
  //char chr;

  Particle(float px, float py,float pz,char chr) {
    for(int i = 0; i < 1;i++) {
    location = new PVector(px,py,pz);
    velocity = new PVector(0,0,0);
    acceleration = new PVector(0,0,0);
    col = new PVector(220,255,220);
    temp = chr;
    ch = chr;
    }
  }
  
  public void update(){
    //acceleration.x = random(-0.1,0.1);
    //velocity.add(acceleration);
    //location.add(velocity);
    lifeSpan -= 5;
    
    col.x -= 30;
    //col.y -= 0;
    col.z -= 30;
    //ps.addParticle();
  }
  
  //void substract() {
  //  velocity.sub(acceleration);
  //  location.sub(velocity);
    
  //}
  
  public boolean isDead() {
    if (lifeSpan <= random(50)) {
     return true;
    } else {
      return false;
    }
  }
  
  public void display() {
    //stroke(0,lifeSpan);
    //strokeWeight(2);
    //pushMatrix();
    noStroke();
    //fill(255,255,0);
    fill(col.x,col.y,col.z,lifeSpan);
    //translate(location.x,location.y,location.z);
    //sphere(6);
    //chr = char(int( random(0,127)));
    //textSize(10);
    textFont(theMatrix);

    if (lifeSpan == 155) {
    //temp = ch;
    //ch = char(int( random(33,126)));
    text(PApplet.parseChar(PApplet.parseInt( random(33,126))),location.x,location.y - (letras.yspeed * random(1,17)),location.z);
    } else {
      //ch = temp;
      text(ch,location.x,location.y,location.z);
    }
    //popMatrix();
//println(letras.chr);
}
  
}
class ParticleSystem {
  int r;
  ArrayList<Particle> pArray;
  ArrayList<Letras> letrasArray;
  ArrayList<TheSource> sourceArray;
  ArrayList<TheSource> sourceArray1;
  ArrayList<TheSource> sourceArray2;
  ArrayList<TheSource> sourceArray3;

  ParticleSystem(){
  pArray = new ArrayList<Particle>();
  letrasArray = new ArrayList<Letras>();
  sourceArray = new ArrayList<TheSource>();
  sourceArray1 = new ArrayList<TheSource>();
  sourceArray2 = new ArrayList<TheSource>();
  sourceArray3 = new ArrayList<TheSource>();
  }

public void addLetras() {
  letrasArray.add(new Letras());
}

public void addParticle(float psx, float psy, float psz,char chr) {
  pArray.add(new Particle(psx,psy,psz,chr));  
}

public void addSource() {//float x, float y, float z, int r) {
  sourceArray.add(new TheSource(random(-1,1),random(-1,1),random(-1,1),7));
}

public void addSource1() {
  sourceArray1.add(new TheSource(random(-3,3),random(-3,3),random(-3,3),5));
}
public void addSource2() {
  sourceArray2.add(new TheSource(random(-8,8),random(-8,8),random(-8,8),3));
}
public void addSource3() {
  sourceArray3.add(new TheSource(random(-10,10),random(-10,10),random(-10,10),1));
}

public void runSource3() {
  for (int i = sourceArray3.size()-1; i >= 0 ; i--) {
    TheSource s3 = sourceArray3.get(i); 
    s3.update();
    s3.display();
  
    if (s3.isDead()) {
      sourceArray3.remove(i);
      //textSize(32);
      //text(inc1,width/2,height/2);
      //textSize(16);
//println(letrasArray.size());
    }
  }
}

public void runSource2() {
  for (int i = sourceArray2.size()-1; i >= 0 ; i--) {
    TheSource s2 = sourceArray2.get(i); 
    s2.update();
    s2.display();
  
    if (s2.isDead()) {
      sourceArray2.remove(i);
      //textSize(32);
      //text(inc1,width/2,height/2);
      //textSize(16);
//println(letrasArray.size());
    }
  }
}

public void runSource1() {
  for (int i = sourceArray1.size()-1; i >= 0 ; i--) {
    TheSource s1 = sourceArray1.get(i); 
    s1.update();
    s1.display();
  
    if (s1.isDead()) {
      sourceArray1.remove(i);
      //textSize(32);
      //text(inc1,width/2,height/2);
      //textSize(16);
//println(letrasArray.size());
    }
  }
}

public void runSource() {
  for (int i = sourceArray.size()-1; i >= 0 ; i--) {
    TheSource s = sourceArray.get(i); 
    s.update();
    s.display();
  
    if (s.isDead()) {
      sourceArray.remove(i);
      //textSize(32);
      //text(inc1,width/2,height/2);
      //textSize(16);
//println(letrasArray.size());
    }
  }
}

public void runLetras() {
  for (int i = letrasArray.size()-1; i >= 0 ; i--) {
    Letras l = letrasArray.get(i); 
    l.update();
    l.show();
  
    if (l.isDead()) {
      letrasArray.remove(i);
      //textSize(32);
      //text(inc1,width/2,height/2);
      //textSize(16);
//println(letrasArray.size());
    }
  }

  
}

public void run() {
  for (int i = pArray.size()-1; i >= 0 ; i--) {
    Particle p = pArray.get(i); 
    p.update();
    p.display();
  
    if (p.isDead()) {
      pArray.remove(i);
      //textSize(32);
      //text(inc,width/2,height/2);
      //textSize(16);

    }
  }
  
}
}
class TheSource {
  
  PVector location;
  PVector velocity;
  PVector acceleration;
  PVector col;
  
  float lifeSpan = 255;
  int radio;

  TheSource(float px, float py,float pz,int r) {
    //for(int i = 0; i < 1;i++) {
    location = new PVector(0,0,0);
    velocity = new PVector(px,py,pz);////random(px),random(py),random(pz));
    acceleration = new PVector(1.1f,1.1f,1.1f);
    col = new PVector(255,255,255);
    radio = r;
    //}
  }
  
  public void update(){
    velocity.mult(1.01f);
    location.add(velocity);

    lifeSpan -= 3;// fft.getBand(15);
    //println(abs(velocity.x));
   // col.x -= 7;
    col.y -= 1;
    col.z -= 7;
    //ps.addParticle();
  }
  
  
  public boolean isDead() {
    if (lifeSpan <= 0) {
     return true;
    } else {
      return false;
    }
  }
  
  public void display() {
    //stroke(0,lifeSpan);
    //strokeWeight(2);
    pushMatrix();
    noStroke();
    //lifeSpan = fft.getBand(15);
    //fill(255,255,0);
    fill(col.x,col.y,col.z,lifeSpan);
    translate(location.x,location.y,location.z);
    sphere(radio);
    //chr = char(int( random(0,127)));
    //textSize(5);
//    fill(100,255,100);
//if (timer.getTime() <= 3) {
  textFont(theSource);
    text(PApplet.parseChar(PApplet.parseInt( random(65382, 65439))),location.x,location.y,location.z);
//}
//    textSize(10);
    popMatrix();
//println(letras.chr);
 // ps.addSource();
}
  
}  
  
class Timer {

float Time;

Timer(float set) {
Time = set;
}

public float getTime() {
 return(Time); 
}

public void setTime(float set) {
 Time = set; 
}

public void countUp() {
 Time += 1/frameRate; 
}

public void countDown() {
 Time -= 1/frameRate; 
}

}
  public void settings() {  size(1024, 640,P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "TheMatrix3D" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
