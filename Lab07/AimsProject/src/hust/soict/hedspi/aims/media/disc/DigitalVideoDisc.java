package hust.soict.hedspi.aims.media.disc;

import hust.soict.hedspi.aims.media.disc.playable.Playable;

public class DigitalVideoDisc extends Disc implements Playable{

    public DigitalVideoDisc() {};	
	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
		super(title,category,director,length, cost);
	}

	public void play() {
		System.out.println("Playing DVD: " + this.getTitle());
		System.out.println("DVD length: " + this.getLength());
	}

	@Override
	public void print() {
		System.out.printf("Type: %-10sTitle: %-40sCategory: %-20sCost: %10.2f$%n", "DVD", this.getTitle(),this.getCategory(),this.getCost());
		System.out.printf("%20sDirector: %-36sLength: %-10d%n","",this.getDirector(),this.getLength());
	}
}
