package hust.soict.hedspi.aims.media.disc.track;


import hust.soict.hedspi.aims.PlayerException;
import hust.soict.hedspi.aims.media.disc.playable.Playable;

public class Track implements Playable, Comparable<Track>{
    private String title;
    private int length;


    public Track() {
    }

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return this.title;
    }

    public int getLength() {
        return this.length;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            throw new NullPointerException();
        }
        if (obj instanceof Track){
            Track track = (Track)obj;
            if (this.title.equals(track.getTitle()) && this.length == track.getLength()) {
                return true;
            }
        }
        throw new ClassCastException("Can't cast to Track");
    }

    public void play() throws PlayerException {
        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: This track has no length");
        }
		System.out.println("Playing Track: " + this.getTitle());
		System.out.println("Track length: " + this.getLength());
	}

    @Override
    public int compareTo(Track track) {
        if (track == null) {
            throw new NullPointerException();
        }
        if (this.getTitle().equals(track.getTitle())) {
            return this.getLength() - track.getLength();
        }
        return this.getTitle().compareTo(track.getTitle());
    }

    @Override
    public String toString() {
        String string = String.format("Track: %-30sLength:%-10d",this.getTitle(), this.getLength());
        string = "<html>" + string.replaceAll("<","&lt;").replaceAll(" ", "&nbsp;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>";
		return string;
    }
}
