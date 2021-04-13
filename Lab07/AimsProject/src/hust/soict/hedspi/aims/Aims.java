package hust.soict.hedspi.aims;

import java.util.List;
import java.util.Scanner;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.book.Book;
import hust.soict.hedspi.aims.media.disc.CompactDisc;
import hust.soict.hedspi.aims.media.disc.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.disc.track.Track;
import hust.soict.hedspi.aims.order.Order;

public class Aims {

	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int selected = 0;
		Order order = null;
		do {
			System.out.println("Order Management Application: ");
			System.out.println("--------------------------------");
			System.out.println("1. Create new order");
			System.out.println("2. Add item to the order");
			System.out.println("3. Search and delete item");
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
						System.out.println("2. Compact Disc");
						System.out.println("3. Book");
						System.out.println("0. Cancel");
						System.out.println("--------------------------------");
						System.out.println("Please choose a number: 0-1-2");
						type = scanner.nextInt();
						if (type < 0 || type > 3) {
							System.out.println("Invalid.");
						}
					} while (type < 0 || type > 3);
					if (type != 0) {
						String title;
						float cost;
						String category;
						scanner.nextLine();
						System.out.println("Title: ");
						title = scanner.nextLine();
						System.out.println("Category: ");
						category = scanner.nextLine();
						System.out.println("Cost: ");
						cost = scanner.nextFloat();
						scanner.nextLine();
						switch (type) {
							case 1:
								media = createDVD(title, category, cost);
								do {
									System.out.println("Do you want to play this DVD?");
									System.out.println("1. Yes");
									System.out.println("0. No");
									int c = scanner.nextInt();
									if (c == 1) {
										((DigitalVideoDisc)media).play();
										break;
									} else if (c == 0) {
										break;
									}
									System.out.println("Invalid.");
								} while (true);
								break;
							case 2:
								media = createCD(title, category, cost);
								do {
									System.out.println("Do you want to play this DVD?");
									System.out.println("1. Yes");
									System.out.println("0. No");
									int c = scanner.nextInt();
									if (c == 1) {
										((CompactDisc)media).play();
										break;
									} else if (c == 0) {
										break;
									}
									System.out.println("Invalid.");
								} while (true);
								break;
							case 3:
								media = createBook(title, category, cost);
								break;
						}
						order.addMedia(media);
					}
					break;
				case 3:
					if (order == null) {
						System.out.println("Order is not be created yet.");
						break;
					}
					System.out.println("What item do you want to delete? ");
					scanner.nextLine();
					String term = scanner.nextLine();
					List<Media> items = order.search(term);
					Media item = chooseMedia(items);
					if (item != null) {
						order.removeMedia(item);
					}
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

	public static Media createDVD(String title, String category, float cost){
		System.out.println("Director: ");
		String director = scanner.nextLine();
		System.out.println("Length: ");
		int length = scanner.nextInt();
		Media dvd = new DigitalVideoDisc(title, category, director, length, cost);
		return dvd;
	}

	public static Media createCD(String title, String category, float cost){
		System.out.println("Artist: ");
		String artist = scanner.nextLine();
		Media cd = new CompactDisc(title, category, cost, artist);
		int selected;
		do{
			System.out.println("Do you want to add tracks?");
			System.out.println("1. Yes");
			System.out.println("0. No");
			selected = scanner.nextInt();
			switch (selected) {
				case 1:
					System.out.println("Number of tracks: ");
					int n = scanner.nextInt();
					if (n < 1) {
						break;
					}
					for (int i = 0; i < n; i++) {
						System.out.println("Track " + (i+1) + ":");
						System.out.println("Track's name:");
						scanner.nextLine();
						String trackTitle = scanner.nextLine();
						System.out.println("Track's length:");
						int trackLength = scanner.nextInt();
						Track track = new Track(trackTitle, trackLength);
						((CompactDisc)cd).addTrack(track);
					}
					break;
				case 0:
					break;
				default:
					System.out.println("Invalid.");
			}
		} while (selected != 0 && selected !=1);
		return cd;
	}

	public static Media createBook(String title, String category, float cost){
		Media book = new Book(title, category, cost);
		int selected;
		do{
			System.out.println("Do you want to add authors?");
			System.out.println("1. Yes");
			System.out.println("0. No");
			selected = scanner.nextInt();
			switch (selected) {
				case 1:
					System.out.println("Number of authors: ");
					int n = scanner.nextInt();
					if (n < 1) {
						break;
					}
					scanner.nextLine();
					for (int i = 0; i < n; i++) {
						System.out.println("Track " + (i+1) + ":");
						System.out.println("Author's name:");
						String author = scanner.nextLine();
						((Book)book).addAuthor(author);
					}
					break;
				case 0:
					break;
				default:
					System.out.println("Invalid.");
			}
		} while (selected != 0 && selected !=1);
		return book;
	}

	public static Media chooseMedia(List<Media> items) {
		if (items.size() == 0) {
			System.out.println("Not found.");
			return null;
		}
		int selected;
		do {
			System.out.println("0: Cancel");
			for (int i = 0; i < items.size(); i++) {
				System.out.print((i+1) + ": ");
				System.out.print(items.get(i).getTitle() + ", ");
				if(items.get(i) instanceof DigitalVideoDisc) {
					System.out.println("type: DVD");
				} else if(items.get(i) instanceof CompactDisc) {
					System.out.println("type: CD");
				} else if(items.get(i) instanceof Book) {
					System.out.println("type: Book");
				}
			}
			System.out.println("Choose media: ");
			selected = scanner.nextInt();
			if (selected == 0) {
				return null;
			}
			if (selected < 0 || selected > items.size()) {
				System.out.println("Invalid.");
			} else {
				return items.get(selected-1);
			}
		} while(true);
	}
}
