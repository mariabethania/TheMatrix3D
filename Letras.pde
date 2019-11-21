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

  void update() {
    //xspeed *= 0.99802031;
    //x += xspeed;
    y += yspeed;
  }

  boolean isDead() {
    if (y > height+600) {
      return true;
    } else {
      return false;
    }
  }

  void show() {
    //stroke(255,255,0);
    //pushMatrix();
    //translate(x,y,-z);
    //sphere(rad);
    //num = int( random(33,126));
   // textSize(10);
    chr = char(int( random(65382, 65439)));// japanese fonts 12449, 12534 */* 65382, 65439
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
