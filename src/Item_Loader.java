import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Item_Loader {

    Prep_Bin prepBin = new Prep_Bin();
    public  void loadDataFromFile(String fileName) {
        try {

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                String[] dimensions = line.split(",");
                double width = Double.parseDouble(dimensions[0]);
                double height = Double.parseDouble(dimensions[1]);
                String color = dimensions[2];
                int priority = Integer.parseInt(dimensions[3]);
                Item item = new Item(width, height, color, priority);
                prepBin.addItemtoTheList(item);
                counter++;
    
            }
         //  prepBin.orderbyPriorityAndHeight();
         // prepBin.orderbyHeight();
         
 //    prepBin.orederbyArea();
     //   prepBin.orderbyPriority();
         //prepBin.orderbyAreaAndPriority();
            System.out.println("Total Items loaded: "+counter);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public Prep_Bin getprepBin() {
    return this.prepBin;
    }
    
    

}//class


