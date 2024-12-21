package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable{
    
	public DigitalVideoDisc(String title) {
		super(title);
	}

	public DigitalVideoDisc(String title, String category, float cost) {
		super(title, category, cost);
	}

	
	public DigitalVideoDisc(String title, String category, String director, float cost) {
		super(title, category, director, cost);
	}

	// Constructor by all attributes
	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
		super(title, category, director, length, cost);
	}
	
	@Override
	public String play() throws PlayerException {
		if(this.getLength() > 0) {
			return "Playing DVD: " + this.getTitle() + ". Length: " + this.getLength()+ "\n";
		}
		else {
			throw new PlayerException("ERROR: DVD length is non-positive!");
		}
	}
	
	public String toString()
	{
		return "DVD: " + this.getTitle() +
				" - Category: " + this.getCategory() +
				" - Director: " + this.getDirector() +
				" - DVD length: " + this.getLength() +
				" - Cost: " + this.getCost() + "$";
	}

}

