package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class Track implements Playable{
	
	private String title;
	private int length;
	
	public String getTitle() {
		return title;
	}

	public int getLength() {
		return length;
	}

	public Track(String title, int length) {
		this.title = title;
		this.length = length;
	}
	
	public String play() throws PlayerException {
		if(this.getLength() > 0) {
			return "Playing Track: " + this.getTitle() + ". Length: " + this.getLength()+ "\n";
		}
		else {
			throw new PlayerException("ERROR: DVD length is non-positive!");
		}
	}
	
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if(!(obj instanceof Track)) {
			return false;
		}
		return ((Track)obj).getTitle() == this.getTitle() && ((Track)obj).getLength() == this.getLength();
 	}
	
}
