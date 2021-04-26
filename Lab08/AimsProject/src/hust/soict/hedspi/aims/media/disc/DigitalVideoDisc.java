package hust.soict.hedspi.aims.media.disc;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.disc.playable.Playable;

public class DigitalVideoDisc extends Disc implements Playable{

    public DigitalVideoDisc() {};	
	public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
		super(id,title,category,director,length, cost);
	}

	public void play() {
		System.out.println("Playing DVD: " + this.getTitle());
		System.out.println("DVD length: " + this.getLength());
	}

	@Override
	public void print() {
		System.out.printf("Type: %-10sID: %-5dTitle: %-40sCategory: %-20sCost: %10.2f$%n", "DVD", this.getId(), this.getTitle(),this.getCategory(),this.getCost());
		System.out.printf("%20sDirector: %-36sLength: %-10d%n","",this.getDirector(),this.getLength());
	}

	@Override
    public int compareTo(Media media) {
        if (media instanceof DigitalVideoDisc) {
            DigitalVideoDisc disc = (DigitalVideoDisc) media;
            if (this.getCost() != disc.getCost()) {
                if (this.getCost() > disc.getCost()) {
					return 1;
				}
				return -1;
            }
            if (this.getLength() != disc.getLength()) {
                return this.getLength() - disc.getLength();
            }
            return this.getTitle().compareTo(disc.getTitle());
        }
        return 1;
    }
}
