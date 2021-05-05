package hust.soict.hedspi.aims.media.disc.track;

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
        if (obj instanceof Track){
            Track track = (Track)obj;
            if (this.title.equals(track.getTitle()) && this.length == track.getLength()) {
                return true;
            }
        }
        return false;
    }

    public void play() {
		System.out.println("Playing Track: " + this.getTitle());
		System.out.println("Track length: " + this.getLength());
	}

    @Override
    public int compareTo(Track track) {
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
