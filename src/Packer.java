
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Packer {
    public Node root;
    List<Node> inorderList = new ArrayList<>();

    public Packer(int w, int h, int x, int y) {
        init(w, h, x, y);
    }

    private void init(int w, int h, int x, int y) {
        this.root = new Node(x, y, w, h);
    }




    public int fit(List<Item> items) {
        int area=0;
        int item_index=0;
        int counter=0;
        Node newRoot=null;

        for (Item item : items) {


            Node node = findNode(root, (int)item.getItemWidth(), (int)item.getItemHeight());

//        if (item.getItemPriority() == 2 && this.checkforclosedLines(this.traverseNodes(root))!=null) {
//        if (counter==0) {
//            newRoot = this.checkforclosedLines(this.traverseNodes(node)).up;
//            counter++;
//            System.out.println("1111111111111111");
//        } else {
//        newRoot =newRoot.right;
//            System.out.println("2222222222");
//        }
//        }
//





            if (newRoot != null) {
                // Merge the new binary tree with the existing tree
                node= mergeTrees(root, newRoot);
            }





            if (node != null) {
                boolean placed = false;


                if (node.firstinShelf) {
                    System.out.println("Item "+item+"is first in shelf");
                    Placement(node, item);
                    placed = true;
                    item_index++;
                    node.itm=item;
                }// First, try placing the item upright

                else if (item.itemisUpright()==true && placed!=true) {

                    if (tryUprightPlacement(node, item)) {

                        placed = true;
                        item_index++;
                        node.itm=item;
                    }

                    //Κάνει το split από πριν δηλαδή μέσα στη συνθήκη if
                    // If upright placement failed, try sideways placement
                    else if (trySidewaysPlacement(node, item)) {
                        placed = true;

                        item_index++;
                        node.itm=item;
                    }

                }

                else if (item.itemisSideways()==true && placed!=true) {



                    if (trySidewaysPlacement(node, item)) {

                        placed = true;
                        item_index++;
                        node.itm=item;
                    }

                    //Κάνει το split από πριν δηλαδή μέσα στη συνθήκη if
                    // If upright placement failed, try sideways placement
                    else if (tryUprightPlacement(node, item)) {
                        placed = true;

                        item_index++;
                        node.itm=item;
                    }

                }



                // If neither upright nor sideways placement succeeds, skip this item
                if (placed==true) {
                    node.used = true; // Mark the item as fitted only if it was successfully placed
                    area+=(int)item.getItemHeight()*(int)item.getItemWidth();
                    item_index++;
                    node.itm=item;
                }


            } else {


                break;
            }
            System.out.println("**********Total Bin Occupied Area is: "+area+"*************\n");
        }
        return item_index;
    }


    private void Placement(Node node, Item item) {
        if (item.itemisUpright()) {
            item.rotate();
        }
        node.setItemColor(item.getItemColor());
        splitNode(node, (int)item.getItemWidth(), (int)item.getItemHeight());

    }





    private Node mergeTrees(Node root1, Node root2) {
        // If either root is null, return the other root
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        // Add the nodes of root2 to root1
        mergeNodes(root1, root2.right);
        mergeNodes(root1, root2.up);

        // Find the leftmost node in the merged tree
        Node upmost = root2;
        while (upmost.right != null) {
            upmost = upmost.right;
        }

        // Return the leftmost node
        return upmost;
    }





    private void mergeNodes(Node root1, Node node) {
        if (node != null) {
            // Add the node to root1
            root1.used = true;
            root1.setItemColor(node.getItemColor());


            if (node.right != null) {
                if (root1.right == null) {
                    root1.right = new Node(node.x, node.y, node.w, node.h);
                } else {
                    mergeNodes(root1.right, node.right);
                }
            }
            if (node.up != null) {
                if (root1.up == null) {
                    root1.up = new Node(node.x, node.y, node.w, node.h);
                } else {
                    mergeNodes(root1.up, node.up);
                }
            }
        }}


    private boolean tryUprightPlacement(Node node, Item item) {
        if (item.getItemWidth() <= node.w && item.getItemHeight() <= node.h) {
            node.setItemColor(item.getItemColor());
            splitNode(node, (int)item.getItemWidth(), (int)item.getItemHeight());
            return true;
        }
        return false;
    }

    private boolean trySidewaysPlacement(Node node, Item item) {

        if (item.getItemHeight() <= node.w && item.getItemWidth() <= node.h) {
            node.setItemColor(item.getItemColor());
            splitNode(node, (int)item.getItemHeight(), (int)item.getItemWidth()); // Swap width and height
            return true;
        }
        return false;
    }

    public Node findNode(Node root, int w, int h) {
        if (root.used) {
            Node right = findNode(root.right, w, h);
            if (right != null) {
                return right;
            } return findNode(root.up, w, h);
        } else if ((w <= root.w && h <= root.h) || (h <= root.w && w <= root.h)) {
            return root;
        } else {
            return null;
        }
    }


    public List<Node> traverseNodes(Node root) {
        if (root!=null) {
            if(root.used) {
                inorderList.add(root);
            }
            traverseNodes(root.right);
            traverseNodes(root.up);
        }
        return inorderList;
    }



    public Node checkforclosedLines (List<Node> inorderList) {
        Node NodeClosed=null;
        for (Node node : inorderList) {
            if (node.w == 180)
                NodeClosed=node;
        }
        return NodeClosed;
    }

    public Node splitNode(Node node, int w, int h) {
        node.used = true;
        node.up = new Node(node.x, node.y + h, node.w, node.h - h);

        node.right = new Node(node.x + w, node.y, node.w - w, h);



        System.out.println("Split Node:");
        System.out.println("Original Node: x=" + node.x + ", y=" + node.y + ", w=" + node.w + ", h=" + node.h);
        System.out.println("Up Node: x=" + node.up.x + ", y=" + node.up.y + ", w=" + node.up.w + ", h=" + node.up.h);
        System.out.println("Right Node: x=" + node.right.x + ", y=" + node.right.y + ", w=" + node.right.w + ", h=" + node.right.h);
        return node;
    }


    public static void main(String[] args) {



        String fileName = "C:\\Users\\mypc\\Desktop\\Data\\case_1.txt";

//Φόρτωνω κλάση
        Item_Loader loader = new Item_Loader();
        loader.loadDataFromFile(fileName);
        System.out.println("Items that Loaded: " + loader.getprepBin().getItemList());

//Δημιουργώ λίστα με ονομασία itmList στην οποία αποθηκεύω τα imported items
//για να μην τα καλώ συνέχεια
        List<Item> items = new ArrayList<>();


        for (int i=0;i<=loader.getprepBin().getItemList().size()-1;i++){
            items.add(loader.getprepBin().getItemList().get(i));
        }



        Packer packer = new Packer(180, 300,0,0);

        int k = packer.fit(items)/2 ;
        System.out.println("Bin 1 is full with total items inside: "+k);

        for (int l=0;l<=k-1;l++) {

            items.remove(0);
        }


        System.out.println("Items remained: "+items+"\n");

        Packer packer2 = new Packer(180, 300,200,0);


        k = packer2.fit(items) ;
        k=k/2;
        System.out.println("Bin 2 is full with total items inside: "+k);
        for (int l=0;l<=k-1;l++) {
            items.remove(0);
        }


        System.out.println("Items remained: "+items+"\n");
        Packer packer3 = new Packer(180, 300,400,0);
        k = packer3.fit(items) ;
        k=k/2;
        System.out.println("Bin 3 is full with total items inside: "+k+"\n");
        for (int l=0;l<=k-1;l++) {
            items.remove(0);
        }
        System.out.println("Items remained: "+items+"\n");
        Packer packer4 = new Packer(180, 300,600,0);
        packer4.fit(items);

        Node rootNode = packer.root;

        System.out.println(packer.checkforclosedLines(packer.traverseNodes(rootNode)));

        JFrame fr = new JFrame();
        fr.setBounds(500, 500, 500, 500);
        fr.setDefaultCloseOperation(3);
        JPanel pn = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
                g.setColor(Color.BLACK);

                Graphics2D g2d = (Graphics2D) g;

                // save the "old" transform
                AffineTransform old = g2d.getTransform();

                // update graphics object with the inverted y-transform
                g2d.translate(0, getHeight() - 1);
                g2d.scale(1, -1);

                packer.drawNodeRectangles(packer.root, g2d);
                packer2.drawNodeRectangles(packer2.root, g2d);
                packer3.drawNodeRectangles(packer3.root, g2d);
                packer4.drawNodeRectangles(packer4.root, g2d);

                // restore the old transform
                g2d.setTransform(old);

            }

        };
        fr.add(pn);
        fr.setVisible(true);

    }//main

    public  Color getColorByName(String colorName) {
        try {
            // Get the field with the specified name from the Color class
            Field field = Color.class.getField(colorName);

            // Return the value of the field, which is a Color object
            return (Color) field.get(null);
        } catch (Exception e) {
            // If the color name is not found or an error occurs, return default color (black)
            return Color.WHITE;
        }
    }


    private static void drawBin(Graphics2D g2d, Bin bn) {
        int X = (int) (bn.pointX);
        int Y = (int) (bn.pointY);
        int W = (int) (bn.Bwidth);
        int H = (int) (bn.Bheight);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(X, Y, W, H);
    }


    public void drawNodeRectangles(Node node, Graphics2D g2d) {
        if (node != null) {
            // Draw rectangle for the current node

            String colorName = node.getItemColor();

            Color color = getColorByName(colorName);
            g2d.setColor(color);
            g2d.fillRect(node.x, node.y, node.w,node.h); // Fill rectangle with color
            g2d.setColor(Color.BLACK);
            g2d.drawRect(node.x, node.y, node.w, node.h);


// Recursively draw rectangles for the left and right child nodes
            drawNodeRectangles(node.up, g2d);
            drawNodeRectangles(node.right, g2d);

        }
    }







    public class Bin {
        public double Bwidth, Bheight, pointX, pointY, totalArea, capacity;
        //total area είναι το εμβαδόν, capacity είναι η υπολοιπόμενη χωρητικότητα
        public int Bid;
        //Θέλω λίστα που θα αποθηκεύω τα assigned items στο bin
        public ArrayList<Item> assigned_items_list;
        //Θέλω λίστα που θα αποθηκεύω τα lines για κάθε self
        private final ArrayList<Double> refLinesXList;
        private final ArrayList<Double> refLinesYList;


        public Bin(int Bid, double Bwidth, double Bheight, double pointX, double pointY) {
            this.Bid=Bid;
            this.Bwidth = Bwidth;
            this.Bheight=Bheight;
            this.pointX=pointX;
            this.pointY=pointY;
            this.totalArea=Bwidth*Bheight;
            assigned_items_list = new ArrayList<>();
            refLinesXList = new ArrayList<>();
            refLinesYList = new ArrayList<>();
        }//constructor

        public ArrayList<Item> getAssignedItems() {
            return assigned_items_list;
        }

        public ArrayList<Double> getrefLinesX() {
            return refLinesXList;
        }

        public ArrayList<Double> getrefLinesY() {
            return refLinesYList;
        }


    }//Bin















}

