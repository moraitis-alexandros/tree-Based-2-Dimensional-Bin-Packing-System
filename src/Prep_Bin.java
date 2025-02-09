import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Prep_Bin {
  private List<Item> ItemList;
 
public Prep_Bin() {
        ItemList = new ArrayList<>();
    }//constructor
    
     /*Προσθήκη στοιχείων στην Array List*/
  public void addItemtoTheList(Item itm_listed) {
    ItemList.add(itm_listed);
  }//addItemtotheList
//    
//    
//      /*Ταξινόμηση κατά ύψος φθίνουσα*/
    public void orderbyHeight() {
   ItemList.sort(Comparator.comparing(Item::getItemHeight).reversed());

       }//order by height
   
   /*Ταξινόμηση κατά προτεραιότητα αύξουσα*/
    public void orderbyPriority() {
    ItemList.sort(Comparator.comparing(Item::getItemPriority));
    }
    
    public void orederbyArea() {
    ItemList.sort(Comparator.comparing(Item::getItemArea).reversed());
    }
    
    
    /*Ταξινόμηση κατά πλάτος φθίνουσα*/
   public void orderbyWidth() {
ItemList.sort(Comparator.comparing(Item::getItemWidth).reversed());
        }//order by width
    
   
//    Εκτύπωση ItemList
    public List<Item> getItemList() {
      return ItemList;
   }
//
//    
    public void orderbyPriorityAndHeight() {
                ItemList.sort(Comparator.comparing(Item::getItemPriority)
                               .thenComparing(Item::getItemHeight, Comparator.reverseOrder()));
   }
    
      public void orderbyHeightAndPriority() {
                ItemList.sort(Comparator.comparing(Item::getItemHeight)
                               .thenComparing(Item::getItemPriority, Comparator.reverseOrder()));
   }
       public void orderbyAreaAndPriority() {
                 ItemList.sort(Comparator.comparing(Item::getItemArea).reversed()
                               .thenComparing(Item::getItemPriority));
   }
    
    
}//class prepBin
