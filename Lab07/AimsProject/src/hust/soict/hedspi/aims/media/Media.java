package hust.soict.hedspi.aims.media;

public abstract class Media {
    protected String title;
    protected String category;
    protected float cost;

    public Media() {
    }
    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCategory() {
        return this.category;
    }

    public float getCost() {
        return this.cost;
    }

    public void discount() {
        this.cost = 0;
    }

    public boolean search(String str) {
		String[] terms = str.split("\\s+");
		for (String term : terms) {
			if (this.title.toUpperCase().indexOf(term.toUpperCase()) == -1) {
				return false;
			}
		}
		return true;
	}

    public abstract void print();
}
