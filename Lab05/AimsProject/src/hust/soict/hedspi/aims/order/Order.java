package hust.soict.hedspi.aims.order;

import hust.soict.hedspi.aims.disc.DigitalVideoDisc;
import hust.soict.hedspi.aims.utils.MyDate;

public class Order {
	public static final int MAX_NUMBERS_ORDERED = 10;
	
	private DigitalVideoDisc[] itemOrdered = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
	private int qtyOrdered = 0;
	private MyDate dateOrdered;
	public static final int MAX_LIMITED_ORDERS = 5;
	private static int nbOrders = 0;

	private Order(){
		nbOrders++;
		dateOrdered = new MyDate();
	}

	public static Order createOrder(){
		if(nbOrders == MAX_LIMITED_ORDERS) {
			System.out.println("Max orders reach!");
			return null;
		}
		return new Order();
	}

	public boolean addDigitalVideoDisc(DigitalVideoDisc disc) {
		if(qtyOrdered == MAX_NUMBERS_ORDERED) {
			System.out.println("The order is almost full.");
			return false;
		}
		itemOrdered[qtyOrdered] = disc;
		qtyOrdered++;
		System.out.println("The disc has been added.");
		return true;
	}

	public boolean addDigitalVideoDisc(DigitalVideoDisc... dvdList){
		if(qtyOrdered + dvdList.length > MAX_NUMBERS_ORDERED) {
			System.out.println("The order is almost full.");
			return false;
		}
		for (DigitalVideoDisc digitalVideoDisc : dvdList) {
			itemOrdered[qtyOrdered] = digitalVideoDisc;
			qtyOrdered++;
		}
		System.out.println("The disc has been added.");
		return true;
	}

	public boolean addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2){
		if(qtyOrdered == MAX_NUMBERS_ORDERED) {
			System.out.println("The order is almost full.");
			return false;
		}
		itemOrdered[qtyOrdered] = dvd1;
		qtyOrdered++;
		if(qtyOrdered == MAX_NUMBERS_ORDERED) {
			System.out.println("The dvd " + dvd2.getTitle() + " can't not be added.");
			return true;
		}
		itemOrdered[qtyOrdered] = dvd2;
		qtyOrdered++;
		return true;
	}
	
	public boolean removeDigitalVideoDisc(DigitalVideoDisc disc) {
		if(qtyOrdered == 0)	{
			System.out.println("The order is emply.");
			return false;
		}
		int i;
		for (i = 0; i < qtyOrdered; i++) {
			if (disc.getTitle().equals(itemOrdered[i].getTitle())) {
				break;
			}
		}
		if (i == qtyOrdered) {
			System.out.println("The order does not have that disc.");
			return false;
		}
		for (int j = i; j < qtyOrdered - 1; j++) {
			itemOrdered[j] = itemOrdered[j+1];
		}
		qtyOrdered--;
		System.out.println("The disc has been deleted.");
		return true;
	}
	
	public float totalCost() {
		float total = 0;
		for (int i = 0; i < qtyOrdered; i++) {
			total += itemOrdered[i].getCost();
		}
		return total;
	}

	public void print() {
		System.out.println("***********************Order***********************");
		System.out.print("Date: ");
		dateOrdered.print();
		System.out.println("Ordered items: ");
		for (int i = 0; i < qtyOrdered; i++) {
			System.out.printf("%d. %-50s%-20s%-30s%5ds%10.2f$%n",i+1,itemOrdered[i].getTitle(),itemOrdered[i].getCategory(),itemOrdered[i].getDirector(),itemOrdered[i].getLength(),itemOrdered[i].getCost());
		}
		System.out.printf("Total cost: %.2f$%n", totalCost());
		System.out.println("***************************************************");
	}

	public DigitalVideoDisc getALuckyItem() {
		int index = (int)(qtyOrdered * Math.random());
		itemOrdered[index].setCost(0);
		return itemOrdered[index];
	}
}
