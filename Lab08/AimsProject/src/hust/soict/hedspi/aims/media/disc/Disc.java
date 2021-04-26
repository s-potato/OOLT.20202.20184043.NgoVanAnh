package hust.soict.hedspi.aims.media.disc;

import hust.soict.hedspi.aims.media.Media;

public abstract class Disc extends Media {
	protected String director;
	protected int length;
	
	public Disc() {};
	public Disc(int id, String title, String category, String director, int length, float cost) {
		super(id,title,category,cost);
		this.director = director;
		this.length = length;
	}

	public String getDirector() {
		return director;
	}
	public int getLength() {
		return length;
	}
	
	public abstract void print();
}
