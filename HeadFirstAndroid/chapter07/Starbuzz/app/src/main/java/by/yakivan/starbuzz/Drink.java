package by.yakivan.starbuzz;

public class Drink {
    private String name;
    private String description;
    private int imageResourseId;

    public static final Drink[] drinks = {
            new Drink("Latte", "A couple of espresson shots with steamed milk", R.drawable.latte),
            new Drink("Cappuccino", "Espresso, hot milk, and a steamed milk foam", R.drawable.cappuccino),
            new Drink("Filter", "Highest quality beans roasted and brewed fresh", R.drawable.filter)
    };

    public Drink(String name, String description, int imageResourseId) {
        this.name = name;
        this.description = description;
        this.imageResourseId = imageResourseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourseId() {
        return imageResourseId;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
