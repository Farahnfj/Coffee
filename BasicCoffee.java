
public class BasicCoffee implements Coffee{

    @Override
    public void addTopping(Coffee coffee) {

    }

    @Override
    public double cost() {
        return 0.85;
    }

    @Override
    public String printCoffee() {
        return "Black coffee";
    }
}
