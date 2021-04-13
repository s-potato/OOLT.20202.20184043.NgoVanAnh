package hust.soict.hedspi.aims.media.book;

import java.util.ArrayList;
import java.util.List;

import hust.soict.hedspi.aims.media.Media;

public class Book extends Media {
    private List<String> authors = new ArrayList<String>();
    

    public Book() {
    }
    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }
    public Book(String title, String category, float cost, List<String> authors) {
        super(title, category, cost);
        this.authors = authors;
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public boolean addAuthor(String author) {
        if(authors.indexOf(author) != -1) {
            System.out.println("Existed.");
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

    @Override
	public void print() {
		System.out.printf("Type: %-10sTitle: %-40sCategory: %-20sCost: %10.2f$%n", "Book", this.getTitle(),this.getCategory(),this.getCost());
        if (authors.size() == 0) {
            return;
        }
		System.out.printf("%20sAuthors: ","");
        for (int i = 0; i < authors.size(); i++) {
            System.out.print(authors.get(i));
            if (i != authors.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
	}
}