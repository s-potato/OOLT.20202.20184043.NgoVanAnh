package hust.soict.hedspi.aims.media;

public abstract class Media implements Comparable<Media>{
    protected int id;
    protected String title;
    protected String category;
    protected float cost;

    public Media() {
    }
    public Media(int id,String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public int getId() {
        return this.id;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        if (obj instanceof Media){
            Media media = (Media)obj;
            if (this.cost == media.cost && this.title.equals(media.title)) {
                return true;
            }
            else return false;
        }
        throw new ClassCastException("Can't cast to Media");
    }

    public abstract void print();
}
