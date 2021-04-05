package hust.soict.hedspi.aims.media.disc;

import hust.soict.hedspi.aims.media.Media;

public class DigitalVideoDisc extends Media {
	private String director;
	private int length;
	
	public DigitalVideoDisc() {};	
	public DigitalVideoDisc(String title) {
		super(title);
	}	
	public DigitalVideoDisc(String title, String category) {
		super(title,category);
	}
	public DigitalVideoDisc(String title, String category, String director) {
		super(title,category);
		this.director = director;
	}
	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
		super(title,category,cost);
		this.director = director;
		this.length = length;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

	public boolean search(String str) {
		String[] terms = str.split("\\s+");
		for (String term : terms) {
			if (this.title.toUpperCase().indexOf(term.toUpperCase()) == -1) {
				return false;
			}
		}
		return true;
	}
}
