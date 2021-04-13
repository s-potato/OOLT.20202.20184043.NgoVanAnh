package hust.soict.hedspi.aims.media.disc;

import java.util.ArrayList;
import java.util.List;

import hust.soict.hedspi.aims.media.disc.playable.Playable;
import hust.soict.hedspi.aims.media.disc.track.Track;

public class CompactDisc extends Disc implements Playable{
    private String artist;
    private List<Track> tracks = new ArrayList<Track>();

    public CompactDisc() {};
    public CompactDisc(String title, String category, float cost, String artist) {
		super(title,category,"",0, cost);
        this.artist = artist;
	}
    public CompactDisc(String title, String category, float cost, String artist, ArrayList<Track> tracks) {
		super(title,category,"",0, cost);
        this.artist = artist;
        this.tracks = tracks;
        this.length = this.getLength();
	}

    public String getArtist() {
        return this.artist;
    }
    public List<Track> getTracks() {
        return this.tracks;
    }


    public boolean addTrack(Track track) {
        for (Track t : tracks) {
            if (t.equals(track)){
                System.out.println("Existed.");
                return false;
            }
        }
        tracks.add(track);
        this.length = this.getLength();
        return true;
    }

    public boolean removeTrack(Track track) {
        if(tracks.size() == 0) {
            System.out.println("Empty.");
            return false;
        }
        for (Track t : tracks) {
            if (t.equals(track)){
                tracks.remove(track);
                this.length = this.getLength();
                return true;
            }
        }
        System.out.println("Not exist.");
        return false;
    }

    @Override
    public int getLength() {
        int sum = 0;
        for (Track track : tracks) {
            sum += track.getLength();
        }
        return sum;
    }

    public void play() {
		System.out.println("Playing CD: " + this.getTitle());
		System.out.printf("CD has %d tracks. Length: %d.%n", this.getTracks().size(), this.getLength());
        for (Track track : tracks) {
            track.play();
        }
	}

    @Override
	public void print() {
		System.out.printf("Type: %-10sTitle: %-40sCategory: %-20sCost: %10.2f$%n", "CD", this.getTitle(),this.getCategory(),this.getCost());
		System.out.printf("%20sArtist: %-38sTotal length: %-10d%n","",this.getArtist(),this.getLength());
        
        System.out.printf("%20sTracks: %d tracks.%n","",this.getTracks().size());
        if (tracks.size() == 0) {
            return;
        }
        for (Track track : tracks) {
            System.out.printf("%25sTrack: %-30sLength:%-10d%n","",track.getTitle(), track.getLength());
        }
	}
}