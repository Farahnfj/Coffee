
public class WhippedCream extends CoffeeDecorator {
    public WhippedCream(Coffee coffee) {
        super(coffee);
    }
    double WhippedCream;

    void setWhippedCream(){
        this.WhippedCream =  0.10;
    }
    void getWhippedCream(){

        return;
    }
    @Override
    public void addTopping(Coffee coffee) {

    }
    @Override
    public String printCoffee() {
        return "";
    }

    @Override
    public double cost() {
        return this.coffee.cost() + 0.10;
    }
}
