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
  
  void update(){
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
  
  boolean isDead() {
    if (lifeSpan <= random(50)) {
     return true;
    } else {
      return false;
    }
  }
  
  void display() {
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
    text(char(int( random(33,126))),location.x,location.y - (letras.yspeed * random(1,17)),location.z);
    } else {
      //ch = temp;
      text(ch,location.x,location.y,location.z);
    }
    //popMatrix();
//println(letras.chr);
}
  
}
