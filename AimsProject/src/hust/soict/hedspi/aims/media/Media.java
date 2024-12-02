package hust.soict.hedspi.aims.media;

public abstract class Media {

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
		this.id = nbMedia++;
	}

	public Media(String title, String category) {
		this.title = title;
		this.category = category;
		this.id = nbMedia++;
	}

	public Media(String title, String category, float cost) {
		this.title = title;
		this.category = category;
		this.cost = cost;
		this.id = nbMedia++;
	}
	
	public boolean isMatch(String title) {
        return this.getTitle().toLowerCase().contains(title.toLowerCase());
    }
	
	public boolean equals(Object obj) {
    	if (obj == this) {
    		return true;
    	}
    	if (!(obj instanceof Media)) {
    		return false;
    	}
    	return ((Media)obj).getTitle() == this.getTitle();
    }
	
	public String toString() {
    	return "Media: " + this.getTitle() + 
    			" - Category: " + this.getCategory() + 
    			" - Cost: " + this.getCost() + "$";
    }

}

