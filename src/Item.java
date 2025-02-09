public class Item {
    double Iwidth, Iheight, IpointX, IpointY, itemArea; 
    String IColor;
    int priority;
  public Node fit;
  public Orientation orientation;
    
    
       public enum Orientation {
        UPRIGHT,
        SIDEWAYS
    }
    
    
    
    public Item(double width, double height, String color, int priority) {
            this.Iwidth = width;
            this.Iheight=height;
            this.IColor=color;
            this.priority=priority;
            itemArea=width*height;
            this.IpointX=-1000;
            this.IpointY=-1000;
         this.fit=null;
          this.orientation = Orientation.UPRIGHT;
    }//constructor
    
  /*Συνάρτηση για rotation*/
  public void rotateItem() {
  double temp;
  temp=Iwidth;
  Iwidth=Iheight;
  Iheight=temp;
  } 
  
  //Έλεγχος αν το αντικείμενο είναι upright
    public boolean itemisUpright() {
        return this.Iheight>=this.Iwidth; 
  }
    
      public boolean itemisSideways() {
        return this.Iwidth>=this.Iheight; 
  }
   
  //Τα παρακάτω χρησιμεύουν για να κάνει το prepBin το ordering
 public double getItemHeight() {
return this.Iheight;
}
 
 public int getItemPriority(){
 return this.priority;
 }
 
 
  public String getItemColor() {
return this.IColor;
}

  public double getItemWidth() {
return this.Iwidth;
}
 public double getItemArea() {
 return this.Iwidth*this.Iheight;
 }   

//Συνάρτηση για τοποθέτηση αντικειμένου
  public void setItemStartingCoords(double x, double y) {
  this.IpointX=x;
  this.IpointY=y;
  }
  
  //Συνάρτηση για καθάρισμα συντεταγμένων αντικειμένου 
  public void clearItemStartingCoords () {
  this.IpointX=-1000;
  this.IpointY=-1000;
  }
  
  public double rotatedheight() {
  return this.Iwidth;
  }
  
    public double rotatewidth() {
  return this.Iheight;
  }

    String getColor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public void rotate() {
        double temp = Iwidth;
        Iwidth = Iheight;
        Iheight = temp;
        // Update the orientation accordingly
        if (this.orientation == Orientation.UPRIGHT) {
            this.orientation = Orientation.SIDEWAYS;
        } else {
            this.orientation = Orientation.UPRIGHT;
        }
    }

    // Getter method for item orientation
    public Orientation getOrientation() {
        return orientation;
    }

    // Setter method for item orientation
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
    
    public void setUpright() {
    if (this.Iheight<this.Iwidth) {
    this.rotateItem();
    }
    }
    
    public void setSideways() {
    if (this.Iwidth<this.Iheight) {
    this.rotateItem();
    }
    }
    
    
}//item class
