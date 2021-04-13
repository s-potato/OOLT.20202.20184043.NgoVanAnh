package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.media.book.Book;
import hust.soict.hedspi.aims.media.disc.CompactDisc;
import hust.soict.hedspi.aims.media.disc.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.disc.track.Track;
import hust.soict.hedspi.aims.order.Order;

public class DiskTest {
    public static void main(String[] args) {
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King","Animation","Roger Allers", 87, 19.95f);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars","Science Fiction","George Lucas",124,24.95f);
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", "John Musker", 90, 18.99f);
		DigitalVideoDisc dvd4 = new DigitalVideoDisc("Cinderella","Animation","John Cena",120,100.99f);
		DigitalVideoDisc dvd5 = new DigitalVideoDisc("Jungle","Animation","John Cena",95,28.99f);
        Book book = new Book("Jungle","Animation",28.99f);
        book.addAuthor("John Cena");
        book.addAuthor("David");
        assert dvd1.search("Lion the") == true : "Wrong search method";
        assert dvd1.search("lione") == false : "Wrong search method for false";
        CompactDisc cd1 = new CompactDisc("Jungle","Animation",9.99f,"John Cena");
        Track track = new Track("Hello", 10);
        cd1.addTrack(track);
        Order order = Order.createOrder();
        order.addMedia(dvd1,cd1);
        order.addMedia(book);
        order.print();
    }
}
