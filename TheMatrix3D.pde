// mero mero

import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;
import ddf.minim.signals.*;
import ddf.minim.spi.*;
import ddf.minim.ugens.*;

import peasy.*;
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
boolean pause = true;

void setup() {
  //fullScreen();
  size(1024, 640,P3D);
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

void draw() {
  background(0,green,0);
//camera();
//lights();
  //pointLight(0,0,0,255,255,255);
//println(timer.getTime());
if (pause) {
} else {
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
  yRotate -= 0.02;
} else if (rotate[1]) {
  yRotate += 0.02;
}

if (rotate[2]) {
  zRotate += 0.02;
} else if (rotate[3]) { 
  zRotate -= 0.02;
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
    xRotate -= 0.02;
  } else if (upDown[1]) {
    xRotate += 0.02;
  }
  
  if (upDown[2]) {
    goY -= 5;
  } else if (upDown[3]) {
    goY += 5;
  }

}
}

void keyPressed() {
  if (keyCode == ENTER) {
    if (pause) {
  letras = new Letras();
  ps = new ParticleSystem();
  source = new TheSource(10,10,10/*random(-3,3),random(-3,3),random(-3,3)*/, 5);
  timer = new Timer(35);
    pause = false;
    song.play();
    } else {
    pause = true;
    song.pause();
    song.rewind();
  }
  }
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

void keyReleased() {
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

void pause() {
  
}
