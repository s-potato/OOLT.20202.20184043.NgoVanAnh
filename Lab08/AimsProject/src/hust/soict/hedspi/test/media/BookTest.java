package hust.soict.hedspi.test.media;

import hust.soict.hedspi.aims.media.book.Book;

public class BookTest {
    public static void main(String[] args) {
        Book book = new Book(8,"Magic","Fiction",99f);
        book.setContent("The central character in the series is Harry Potter, a boy who lives in the fictional town of Little Whinging, Surrey with his aunt, uncle, and cousin - the Dursleys - and discovers at the age of eleven that he is a wizard, though he lives in the ordinary world of non-magical people known as Muggles.");
        System.out.println(book);
    }
}