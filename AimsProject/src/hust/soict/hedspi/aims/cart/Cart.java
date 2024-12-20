package hust.soict.hedspi.aims.cart;

import java.util.ArrayList;
import java.util.Collections; // Để sắp xếp danh sách
import java.util.Iterator;    // Để duyệt danh sách bằng Iterator
import java.util.List;        // Để xử lý danh sách cơ bản
import javafx.collections.FXCollections; // Để khởi tạo danh sách observable
import javafx.collections.ObservableList; // Để sử dụng danh sách observable trong JavaFX
import javax.swing.JOptionPane; // Để hiển thị các thông báo dạng pop-up (dialog)

import hust.soict.hedspi.aims.media.Media;


public class Cart {
	public static final int MAX_NUMBERS_ORDERED = 20;
	
	private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();
	
	public void addMedia(Media media) {
		if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
			// Hiển thị thông báo giỏ hàng đầy
	        JOptionPane.showMessageDialog(null, 
	            "The cart is almost full.", 
	            "Cart Full", 
	            JOptionPane.WARNING_MESSAGE);
		} else if (itemsOrdered.contains(media)){
			// Hiển thị thông báo sản phẩm đã có trong giỏ hàng
			JOptionPane.showMessageDialog(null, 
		            media.getTitle() + " is already in the cart.", 
		            "Duplicate Item", 
		            JOptionPane.INFORMATION_MESSAGE);
		} else {
			itemsOrdered.add(media);
			// Hiển thị thông báo sản phẩm đã được thêm vào giỏ hàng
	        JOptionPane.showMessageDialog(null, 
	            media.getTitle() + " has been added.", 
	            "Item Added", 
	            JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public void removeMedia(Media media) {
		if (itemsOrdered.size() == 0) {
			System.out.println("Nothing to remove.");
		} else {
			if (itemsOrdered.remove(media)) {
				System.out.println(media.getTitle() + "has been remove from the cart.");
			} else {
				System.out.println("Media not found in cart.");
			}
		}
	}
	
	// tinh tong gia tien
	public float totalCost() {
        float sum = 0;
        for(Media media : itemsOrdered) {
            sum += media.getCost();
        }
        return sum;
    }
	
	// hien thong tin dia
	public void displayDigitalVideoDisc() {
		System.out.println("**************************CART***********************");
    	System.out.println("Ordered Items:");
        for(Media media : itemsOrdered) {
            System.out.println(media.getId() + ". " + media);
        }
        System.out.println("Total cost: " + totalCost());
    	System.out.println("*****************************************************");
	}
	
	// tim kiem boi ID
	public void searchById(int id) {
		boolean found = false;
    	for (Media media : itemsOrdered) {
    		if (media.getId() == id) {
    			System.out.println("Found " + media);
    			found = true;
    		}
    	}
    	if (found==false) {
    		System.out.println("Sorry, no DVDs were found that match the ID provided.");
    	}
    }
	
	// tim kiem bang title
	public void searchByTitle(String title) {
		boolean found = false;
    	for (Media media : itemsOrdered) {
    		if (media.isMatch(title)) {
    			System.out.println("Found" + media);
    			found = true;
    		}
    	}
    	if (found == false) {
    		System.out.println("Sorry, no DVDs were found with \"" + title +"\" in the title!");
    	}
    }
	
	public void searchByCategory(String category) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getCategory().equalsIgnoreCase(category)) {
                System.out.println("Found" + media);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Sorry, no DVDs matching the \"" + category + "\" category were found!");
        }
    }
    
    public void searchByPrice(float maxCost) {
        boolean matchFound = false;
        for (Media media : itemsOrdered) {
            if (media.getCost() <= maxCost) {
                System.out.println("Found" + media);
                matchFound = true;
            }
        }
        if (!matchFound) {
            System.out.println("Sorry, no DVDs were found that match the maximum cost provided!");
        }
    }
    
    public void searchByPrice(float minCost, float maxCost) {
        boolean matchFound = false;
        for (Media media : itemsOrdered) {
            if (media.getCost() >= minCost && media.getCost() <= maxCost) {
                System.out.println("Found" + media);
                matchFound = true;
            }
        }
        if (!matchFound) {
            System.out.println("Sorry, no DVDs were found that match the cost range between your specified minimum and maximum!");
        }
    }
    
    public void empty() {
        if (itemsOrdered.size() == 0) {
            System.out.println("Nothing to remove!");
        } else {
            itemsOrdered.clear();
            System.out.println("Order created.");
            System.out.println("Now your current cart will be empty!");
            System.out.println();
        }
    }
    
    // Sort media in cart
    public void sortMediaByTitle() {
        Collections.sort((List<Media>)itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        Iterator<Media> iterator = itemsOrdered.iterator();
        iterator = itemsOrdered.iterator();
    
        while (iterator.hasNext()) {
            System.out.println(((Media)iterator.next()).toString());
        }
    }
    public void sortMediaByCost() {
        Collections.sort((List<Media>)itemsOrdered, Media.COMPARE_BY_COST_TITLE);
        Iterator<Media> iterator = itemsOrdered.iterator();
        iterator = itemsOrdered.iterator();
    
        while (iterator.hasNext()) {
            System.out.println(((Media)iterator.next()).toString());
        }
    }
    
    public Media searchToRemove(String title) {
		for (Media media : itemsOrdered) {
			if (media.getTitle().equals(title)) {
				return media;
			}
		}
		return null;
	}
    
    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

}
