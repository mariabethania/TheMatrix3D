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

void addLetras() {
  letrasArray.add(new Letras());
}

void addParticle(float psx, float psy, float psz,char chr) {
  pArray.add(new Particle(psx,psy,psz,chr));  
}

void addSource() {//float x, float y, float z, int r) {
  sourceArray.add(new TheSource(random(-1,1),random(-1,1),random(-1,1),7));
}

void addSource1() {
  sourceArray1.add(new TheSource(random(-3,3),random(-3,3),random(-3,3),5));
}
void addSource2() {
  sourceArray2.add(new TheSource(random(-8,8),random(-8,8),random(-8,8),3));
}
void addSource3() {
  sourceArray3.add(new TheSource(random(-10,10),random(-10,10),random(-10,10),1));
}

void runSource3() {
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

void runSource2() {
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

void runSource1() {
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

void runSource() {
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

void runLetras() {
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

void run() {
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
