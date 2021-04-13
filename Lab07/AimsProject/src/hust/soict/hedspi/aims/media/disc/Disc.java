package hust.soict.hedspi.aims.media.disc;

import hust.soict.hedspi.aims.media.Media;

public class Disc extends Media {
	protected String director;
	protected int length;
	
	public Disc() {};
	public Disc(String title, String category, String director, int length, float cost) {
		super(title,category,cost);
		this.director = director;
		this.length = length;
	}

	public String getDirector() {
		return director;
	}
	public int getLength() {
		return length;
	}
	
	public void print() {
		System.out.printf("Type: %-10sTitle: %-40sCategory: %-20sCost: %10.2f$%n", "Disc", this.getTitle(),this.getCategory(),this.getCost());
		System.out.printf("%20Director: %-36sLength: %-10d%n","",this.getDirector(),this.getLength());
	}
}
