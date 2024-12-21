package hust.soict.hedspi.aims.media;

import java.util.Comparator;

import hust.soict.hedspi.aims.exception.PlayerException;

public abstract class Media implements Comparable<Media>{
	
	public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();


	private int id;
	private String title;
	private String category;
	private float cost;
	private static int nbMedia = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public Media() {
		// TODO Auto-generated constructor stub
	}

	public Media(String title) {
		this.title = title;
		this.id = ++nbMedia;
	}

	public Media(String title, String category) {
		this.title = title;
		this.category = category;
		this.id = ++nbMedia;
	}

	public Media(String title, String category, float cost) {
		this.title = title;
		this.category = category;
		this.cost = cost;
		this.id = ++nbMedia;
	}
	
	public boolean isMatch(String title) {
        return this.getTitle().toLowerCase().contains(title.toLowerCase());
    }
	
	@Override
	public boolean equals(Object obj) {
	    // Kiểm tra nếu obj là null
	    if (obj == null) {
	        return false;
	    }
	    // Kiểm tra nếu obj là cùng đối tượng
	    if (obj == this) {
	        return true;
	    }
	    // Kiểm tra kiểu của obj
	    if (!(obj instanceof Media)) {
	        return false;
	    }
	    // Ép kiểu obj về Media
	    Media otherMedia = (Media) obj;

	    // So sánh title, cần xử lý null-safe
	    if (this.title == null || otherMedia.getTitle() == null) {
	        return false;
	    }

	    return this.title.equalsIgnoreCase(otherMedia.getTitle());
	}

	
	public String toString() {
    	return "Media: " + this.getTitle() + 
    			" - Category: " + this.getCategory() + 
    			" - Cost: " + this.getCost() + "$";
    }
	
	public int compareTo(Media other) {
        int titleComparison = this.getTitle().compareTo(other.getTitle());
        if (titleComparison != 0) {
            return titleComparison;
        } else {
            return Double.compare(this.getCost(), other.getCost());
        }
    }


}

