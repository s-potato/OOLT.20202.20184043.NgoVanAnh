
public class Order {
	public static final int MAX_NUMBERS_ORDERED = 10;
	
	private DigitalVideoDisc[] itemOrdered = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
	private int qtyOrdered = 0;
	
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
}
