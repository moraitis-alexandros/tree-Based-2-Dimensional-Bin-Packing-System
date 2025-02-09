public class Node {
   public     int x, y, w, h;
      public  boolean used;
       public Node right, up;
       public String color;
       public boolean firstinShelf=false;
       public Item itm;
       
        public Node(int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.used = false;
            this.right = null;
            this.up = null;
            this.color=null;
            if (this.x==0 || this.x==200 || this.x==400 || this.x==600) {
            firstinShelf=true;
            }
           
            
         
        }
        
        public String getItemColor(){
        return this.color;
        }
        
        public void setItemColor(String color){
        this.color = color;
        }
        
        
        
        
    }