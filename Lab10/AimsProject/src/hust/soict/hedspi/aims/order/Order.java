package hust.soict.hedspi.aims.order;

import java.util.ArrayList;
import java.util.List;

import javax.naming.LimitExceededException;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.utils.MyDate;

public class Order {
	public static final int MAX_NUMBERS_ORDERED = 10;
	public static final int MAX_LIMITED_ORDERS = 5;

	private List<Media> itemOrdered = new ArrayList<Media>();
	private MyDate dateOrdered;

	private static int nbOrders = 0;
	private int id;

	private Order() {
		nbOrders++;
		id = nbOrders;
		dateOrdered = new MyDate();
	}

	public static Order createOrder() throws LimitExceededException {
		if (nbOrders == MAX_LIMITED_ORDERS) {
			throw new LimitExceededException("ERROR: The number of orders has reached its limit!");
		}
		return new Order();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Media> getItemOrdered() {
		return this.itemOrdered;
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
		for (Media media : itemOrdered) {
			media.print();
		}
		System.out.printf("Total cost: %.2f$%n", totalCost());
		System.out.println("***************************************************");
	}

	public Media getALuckyItem(float requiredTotal, int requiredItem, float luckyRate, float discountRate) {
		if (this.totalCost() < requiredTotal) {
			return null;
		}
		if (this.getItemOrdered().size() < requiredItem) {
			return null;
		}
		double roll = Math.random();
		if (roll > luckyRate) {
			return null;
		}

		int discountAmount = (int) Math.floor(discountRate*this.totalCost());
		List<Media> validItem = new ArrayList<Media>();
		for (Media media : itemOrdered) {
			if (media.getCost() <= discountAmount) {
				validItem.add(media);
			}
		}
		if (validItem.size() == 0) {
			return null;
		}
		int i = (int) (validItem.size()* Math.random());
		itemOrdered.get(itemOrdered.indexOf(validItem.get(i))).discount();
		return validItem.get(i);
	}

	public List<Media> search(String term) {
		List<Media> result = new ArrayList<Media>();
		for (Media media : itemOrdered) {
			if (media.search(term)) {
				result.add(media);
			}
		}
		return result;
	}
}
