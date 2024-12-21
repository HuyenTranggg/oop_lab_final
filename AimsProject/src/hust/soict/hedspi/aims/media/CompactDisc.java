package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

import hust.soict.hedspi.aims.exception.PlayerException;

public class CompactDisc extends Media implements Playable{
	
	private String artist;
	private List<Track> tracks = new ArrayList<Track>();
	
	public String getArtist() {
		return artist;
	}
	
	public CompactDisc(String title) {
		super(title);
	}
	
	public CompactDisc(String title, String category) {
		super(title, category);
	}
	
	public CompactDisc(String title, String category, float cost) {
		super(title, category, cost);
	}
	
	public CompactDisc(String title, String category, String artist, float cost) {
        super(title, category, cost);
        this.artist = artist;
    }
	
	public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println(track.getTitle() + " has been added.");
        } else {
            System.out.println("Track already exists in the list of tracks.");
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println(track.getTitle() + " has been removed.");
        } else {
            System.out.println("Track does not exist in the list of tracks.");
        }
    }
	
	public int getLength() {
		int totalLength = 0;
		for (Track track : tracks) {
			totalLength += track.getLength();
		}
		return totalLength;
	}

/*	
	public String play() {
		StringBuilder playInfo = new StringBuilder();
	    playInfo.append("Playing CD: ").append(this.getTitle()).append("\n");
	    playInfo.append("CD length: ").append(this.getLength()).append(" minutes\n");
	    for (Track track : tracks) {
	        playInfo.append(track.play()); // track.play() sẽ cần trả về String
	    }
	    return playInfo.toString();
	}
*/
	@Override
	public String play() throws PlayerException{
		if(this.getLength() > 0) {
			// Create a StringBuilder to store the play information
	        StringBuilder playInfo = new StringBuilder();
	        playInfo.append("Playing CD: ").append(this.getTitle()).append("\n");
	        playInfo.append("CD length: ").append(this.getLength()).append(" minutes\n");

	        // Iterate through the tracks
	        java.util.Iterator<Track> iter = tracks.iterator();
	        while (iter.hasNext()) {
	            Track nextTrack = iter.next();
	            try {
	                playInfo.append(nextTrack.play());
	            } catch (PlayerException e) {
	                throw new PlayerException("Error while playing track: " + nextTrack.getTitle(), e);
	            }
	        }
	        return playInfo.toString();
		}
		else {
			throw new PlayerException("ERROR: CD length is non-positive!");
		}
	}
	
	public String toString() {
		return "CD: " + this.getTitle() + 
				" - Category: " + this.getCategory() + 
				" - Artist: " + this.getArtist() + 
				" - Length: " + this.getLength() + 
				" - Cost: " + this.getCost() + "$";
	}

}
