package hust.soict.hedspi.test.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.book.Book;
import hust.soict.hedspi.aims.media.disc.CompactDisc;
import hust.soict.hedspi.aims.media.disc.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.disc.track.Track;

public class TestMediaCompareTo {
    public static void main(String[] args) {
        List<Media> medias = new ArrayList<Media>();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1,"The Lion King","Animation","Roger Allers", 87, 19.95f);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc(2,"Star Wars","Science Fiction","George Lucas",124,24.95f);
		DigitalVideoDisc dvd3 = new DigitalVideoDisc(3,"Aladdin", "Animation", "John Musker", 90, 18.99f);
		DigitalVideoDisc dvd4 = new DigitalVideoDisc(4,"Cinderella","Animation","John Cena",120,100.99f);
		DigitalVideoDisc dvd5 = new DigitalVideoDisc(5,"Jungle","Animation","John Cena",95,28.99f);
        Book book1 = new Book(6,"Aladdin","Animation",12f);
        Book book2 = new Book(8,"Magic","Fiction",99f);
        Book book3 = new Book(11,"Jungle","Animation",28.9f);
        CompactDisc cd1 = new CompactDisc(7,"Hello","Pop",9.99f,"David");
        CompactDisc cd3 = new CompactDisc(12,"Hello2","Pop",9.99f,"David");
        CompactDisc cd2 = new CompactDisc(10,"Nightmare","Horror",9.99f,"Student");
        Track track1 = new Track("Hello", 10);
        Track track2 = new Track("Hello World", 14);
        Track track3 = new Track("Hello my friend!", 13);
        Track track6 = new Track("Hello", 9);
        Track track4 = new Track("Camera project", 10);
        Track track5 = new Track("Compiler", 12);
        cd1.addTrack(track1);
        cd1.addTrack(track2);
        cd1.addTrack(track3);
        cd1.addTrack(track6);
        cd2.addTrack(track4);
        cd2.addTrack(track5);
        cd3.addTrack(track1);
        cd3.addTrack(track2);
        cd3.addTrack(track4);
        cd3.addTrack(track6);
        medias.add(dvd1);
        medias.add(dvd2);
        medias.add(dvd3);
        medias.add(dvd4);
        medias.add(dvd5);
        medias.add(book1);
        medias.add(book2);
        medias.add(book3);
        medias.add(cd1);
        medias.add(cd2);
        medias.add(cd3);
        Collections.sort(medias);
        for (Media media : medias) {
            media.print();
        }
    }
}
