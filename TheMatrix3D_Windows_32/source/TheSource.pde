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
    acceleration = new PVector(1.1,1.1,1.1);
    col = new PVector(255,255,255);
    radio = r;
    //}
  }
  
  void update(){
    velocity.mult(1.01);
    location.add(velocity);

    lifeSpan -= 3;// fft.getBand(15);
    //println(abs(velocity.x));
   // col.x -= 7;
    col.y -= 1;
    col.z -= 7;
    //ps.addParticle();
  }
  
  
  boolean isDead() {
    if (lifeSpan <= 0) {
     return true;
    } else {
      return false;
    }
  }
  
  void display() {
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
    text(char(int( random(65382, 65439))),location.x,location.y,location.z);
//}
//    textSize(10);
    popMatrix();
//println(letras.chr);
 // ps.addSource();
}
  
}  
  
