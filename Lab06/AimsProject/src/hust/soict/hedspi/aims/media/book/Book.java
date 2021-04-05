package hust.soict.hedspi.aims.media.book;

import java.util.ArrayList;
import java.util.List;

import hust.soict.hedspi.aims.media.Media;

public class Book extends Media {
    private List<String> authors = new ArrayList<String>();
    

    public Book() {
    }
    public Book(String title) {
        super(title);
    }
    public Book(String title, String category) {
        super(title, category);
    }
    public Book(String title, String category, List<String> authors) {
        super(title, category);
        this.authors = authors;
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public boolean addAuthor(String author) {
        if(authors.indexOf(author) == -1) {
            System.out.println("Not exist.");
            return false;
        }
        authors.add(author);
        return true;
    }

    public boolean removeAuthor(String author) {
        if(authors.size() == 0) {
            System.out.println("Empty.");
            return false;
        }
        if(authors.indexOf(author) == -1) {
            System.out.println("Not exist.");
            return false;
        }
        authors.remove(author);
        return true;
    }
}