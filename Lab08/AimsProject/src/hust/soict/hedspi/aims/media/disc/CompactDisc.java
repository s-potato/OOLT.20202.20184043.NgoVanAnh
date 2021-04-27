package hust.soict.hedspi.aims.media.disc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.book.Book;
import hust.soict.hedspi.aims.media.disc.playable.Playable;
import hust.soict.hedspi.aims.media.disc.track.Track;

public class CompactDisc extends Disc implements Playable{
    private String artist;
    private List<Track> tracks = new ArrayList<Track>();

    public CompactDisc() {};
    public CompactDisc(int id, String title, String category, float cost, String artist) {
		super(id,title,category,"",0, cost);
        this.artist = artist;
	}
    public CompactDisc(int id, String title, String category, float cost, String artist, ArrayList<Track> tracks) {
		super(id,title,category,"",0, cost);
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
        Collections.sort(tracks);
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
		System.out.printf("Type: %-10sID: %-5dTitle: %-40sCategory: %-20sCost: %10.2f$%n", "CD", this.getId(), this.getTitle(),this.getCategory(),this.getCost());
		System.out.printf("%20sArtist: %-38sTotal length: %-10d%n","",this.getArtist(),this.getLength());
        
        System.out.printf("%20sTracks: %d tracks.%n","",this.getTracks().size());
        if (tracks.size() == 0) {
            return;
        }
        for (Track track : tracks) {
            System.out.printf("%25sTrack: %-30sLength:%-10d%n","",track.getTitle(), track.getLength());
        }
	}

    @Override
    public int compareTo(Media media) {
        if (media instanceof Book) {
            return 1;
        }
        if (media instanceof CompactDisc) {
            CompactDisc disc = (CompactDisc) media;
            if (this.getTracks().size() != disc.getTracks().size()) {
                return this.getTracks().size() - disc.getTracks().size();
            }
            if (this.getLength() != disc.getLength()) {
                return this.getLength() - disc.getLength();
            }
            return this.getTitle().compareTo(disc.getTitle());
        }
        return -1;
    }
}