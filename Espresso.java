public class Espresso extends CoffeeDecorator {
    public Espresso(Coffee coffee) {
        super(coffee);
    }
    double Espresso;

    void setEspresso(){
        this.Espresso = 0.35;
    }
    void getEspresso(){

        return;
    }
    @Override
    public void addTopping(Coffee coffee) {

    }
    @Override
    public String printCoffee() {
        return this.coffee.printCoffee() + " espresso";
    }

    @Override
    public double cost() {
        return this.coffee.cost()+0.35;
    }
}
