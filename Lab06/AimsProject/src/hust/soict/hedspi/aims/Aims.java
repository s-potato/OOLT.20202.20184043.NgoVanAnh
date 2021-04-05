package hust.soict.hedspi.aims;

import java.util.Scanner;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.book.Book;
import hust.soict.hedspi.aims.media.disc.DigitalVideoDisc;
import hust.soict.hedspi.aims.order.Order;

public class Aims {
	public static void main(String[] args) {
		int selected = 0;
		Scanner scanner = new Scanner(System.in);
		Order order = null;
		do {
			System.out.println("Order Management Application: ");
			System.out.println("--------------------------------");
			System.out.println("1. Create new order");
			System.out.println("2. Add item to the order");
			System.out.println("3. Delete item by id");
			System.out.println("4. Display the items list of order");
			System.out.println("0. Exit");
			System.out.println("--------------------------------");
			System.out.println("Please choose a number: 0-1-2-3-4");
			selected = scanner.nextInt();
			switch (selected) {
				case 1:
					Order temp = Order.createOrder();
					if (temp != null) {
						order = temp;
						System.out.println("Order created.");
					}
					break;
				case 2:
					if (order == null) {
						System.out.println("Order is not be created yet.");
						break;
					}
					int type = 0;
					Media media = null;
					do {
						System.out.println("Order Management Application: ");
						System.out.println("--------------------------------");
						System.out.println("Please choose type of item");
						System.out.println("1. Digital Video Disc");
						System.out.println("2. Book");
						System.out.println("0. Cancel");
						System.out.println("--------------------------------");
						System.out.println("Please choose a number: 0-1-2");
						type = scanner.nextInt();
						switch (type) {
							case 1:
								media = new DigitalVideoDisc();
								break;
							case 2:
								media = new Book();
								break;
							case 0:
								break;
							default:
								System.out.println("Invalid.");
								break;
						}
					} while (type != 0 && type != 1 && type != 2);
					if (type != 0) {
						String title;
						int id;
						float cost;
						String category;
						System.out.println("ID: ");
						id = scanner.nextInt();
						scanner.nextLine();
						System.out.println("Title: ");
						title = scanner.nextLine();
						System.out.println("Category: ");
						category = scanner.nextLine();
						System.out.println("Cost: ");
						cost = scanner.nextFloat();
						media.setId(id);
						media.setTitle(title);
						media.setCategory(category);
						media.setCost(cost);
						order.addMedia(media);
					}
					break;
				case 3:
					if (order == null) {
						System.out.println("Order is not be created yet.");
						break;
					}
					System.out.println("What item do you want to delete? ");
					int id = scanner.nextInt();
					order.removeMedia(id);
					break;
				case 4:
					if (order == null) {
						System.out.println("Order is not be created yet.");
						break;
					}
					order.print();
					break;
				case 0:
					break;
				default:
					System.out.println("Invalid.");
					break;
			}
		} while (selected != 0);
		scanner.close();
	}
}
