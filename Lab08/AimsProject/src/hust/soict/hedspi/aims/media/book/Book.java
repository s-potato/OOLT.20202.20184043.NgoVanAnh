package hust.soict.hedspi.aims.media.book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import hust.soict.hedspi.aims.media.Media;

public class Book extends Media{
    private List<String> authors = new ArrayList<String>();
    private String content;
    private List<String> contentTokens = new ArrayList<String>();
    private Map<String,Integer> wordFrequency = new TreeMap<>();

    public Book() {
    }
    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }
    public Book(int id, String title, String category, float cost, List<String> authors) {
        super(id, title, category, cost);
        this.authors = authors;
    }

    public Book(int id, String title, String category, float cost, List<String> authors, String content) {
        super(id, title, category, cost);
        this.authors = authors;
        this.content = content;
        this.processContent();
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
        this.processContent();
    }

    public List<String> getContentTokens() {
        return this.contentTokens;
    }

    public Map<String,Integer> getWordFrequency() {
        return this.wordFrequency;
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

    private void processContent() {
        String[] tokens = this.getContent().toLowerCase().split("[\\p{Punct}\\s]+");
        this.contentTokens.clear();
        this.contentTokens.addAll(Arrays.asList(tokens));
        Collections.sort(contentTokens);
        for (String string : this.contentTokens) {
            Integer i = this.wordFrequency.get(string);
            this.wordFrequency.put(string, i == null ? 1 : i + 1);
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Id: "+this.id+"\n");
        sb.append("Title: " + this.title + "\n");
        sb.append("Category: " + this.category + "\n");
        sb.append("Authors: ");
        for (String author : this.authors) {
            sb.append(author + ", ");
        }
        sb.append("\n");
        sb.append("Cost: " + this.cost + "$\n");
        sb.append("Number of tokens: " + this.contentTokens.size() + "\n");
        for (Map.Entry<String, Integer> val : this.wordFrequency.entrySet()) {
            sb.append(val.getKey() + ": " + val.getValue() + " times\n");
        }
        String string = sb.toString();
        return string;
    }

    @Override
	public void print() {
		System.out.printf("Type: %-10sID: %-5dTitle: %-40sCategory: %-20sCost: %10.2f$%n", "Book", this.getId(), this.getTitle(),this.getCategory(),this.getCost());
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

    @Override
    public int compareTo(Media media) {
        if (media instanceof Book) {
            return this.getTitle().compareTo(media.getTitle());
        }
        return -1;
    }
}