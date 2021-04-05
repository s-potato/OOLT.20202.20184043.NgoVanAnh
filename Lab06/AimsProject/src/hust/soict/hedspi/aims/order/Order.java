package hust.soict.hedspi.aims.order;

import java.util.ArrayList;
import java.util.List;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.utils.MyDate;

public class Order {
	public static final int MAX_NUMBERS_ORDERED = 10;
	public static final int MAX_LIMITED_ORDERS = 5;

	List<Media> itemOrdered = new ArrayList<Media>();
	private MyDate dateOrdered;

	private static int nbOrders = 0;
	private int id;

	private Order() {
		nbOrders++;
		id = nbOrders;
		dateOrdered = new MyDate();
	}

	public static Order createOrder() {
		if (nbOrders == MAX_LIMITED_ORDERS) {
			System.out.println("Max orders reach!");
			return null;
		}
		return new Order();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean addMedia(Media media) {
		if (itemOrdered.size()== MAX_NUMBERS_ORDERED) {
			System.out.println("The order is almost full.");
			return false;
		}
		itemOrdered.add(media);
		System.out.println("The media has been added.");
		return true;
	}

	public boolean addMedia(Media... mediaList) {
		if (itemOrdered.size()+ mediaList.length > MAX_NUMBERS_ORDERED) {
			System.out.println("The order is almost full.");
			return false;
		}
		for (Media media : mediaList) {
			itemOrdered.add(media);
		}
		System.out.println("The media has been added.");
		return true;
	}

	public boolean addMedia(Media media1, Media media2) {
		if (itemOrdered.size()== MAX_NUMBERS_ORDERED) {
			System.out.println("The order is almost full.");
			return false;
		}
		itemOrdered.add(media1);
		
		if (itemOrdered.size()== MAX_NUMBERS_ORDERED) {
			System.out.println("The dvd " + media2.getTitle() + " can't not be added.");
			return true;
		}
		itemOrdered.add(media2);
		
		return true;
	}

	public boolean removeMedia(Media media) {
		try {
			if(itemOrdered.remove(media)) {
				System.out.println("Media has been deleted.");
				return true;
			}
			else {
				System.out.println("The order does not have that media.");
			};
		}
		catch (Exception e) {
			System.out.println("Error");
		}
		return false;
	}

	public boolean removeMedia(int mediaID) {
		for(int i = 0; i< itemOrdered.size(); i++) {
			if (itemOrdered.get(i).getId() == mediaID) {
				itemOrdered.remove(i);
				System.out.println("Media has been deleted.");
				return true;
			}
		}
		System.out.println("The order does not have that media.");
		return false;
	}

	public float totalCost() {
		float total = 0;
		for (int i = 0; i < itemOrdered.size(); i++) {
			total += itemOrdered.get(i).getCost();
		}
		return total;
	}

	public void print() {
		System.out.println("***********************Order***********************");
		System.out.print("Date: ");
		dateOrdered.print();
		System.out.println("Ordered items: ");
		System.out.printf("%-5s%-8s%-50s%-20s%10s%n","STT","ID","Title","Category","Cost");
		for (int i = 0; i < itemOrdered.size(); i++) {
			
			System.out.printf("%-5d%-8d%-50s%-20s%10.2f$%n", i + 1,itemOrdered.get(i).getId(), itemOrdered.get(i).getTitle(),
					itemOrdered.get(i).getCategory(),
					itemOrdered.get(i).getCost());
		}
		System.out.printf("Total cost: %.2f$%n", totalCost());
		System.out.println("***************************************************");
	}

	public Media getALuckyItem() {
		int i = (int) (itemOrdered.size()* Math.random());
		itemOrdered.get(i).setCost(0);
		return itemOrdered.get(i);
	}
}
