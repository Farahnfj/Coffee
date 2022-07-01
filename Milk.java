
public class Milk extends CoffeeDecorator {
    public Milk(Coffee coffee) {
        super(coffee);

    }
    double Milkcost;

    void setMilk(){
        this.Milkcost =  0.15;
    }
    void getMilk(){

    }

    @Override
    public void addTopping(Coffee coffee) {

    }

    @Override
    public String printCoffee() {

        return this.coffee.printCoffee() + " with milk";
    }

    @Override
    public double cost() {
        return this.coffee.cost()+0.15;
    }
}