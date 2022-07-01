public class HotWater extends CoffeeDecorator {
    public HotWater(Coffee coffee) {
        super(coffee);
    }
    double HotWater;
    void setHotWater(){
        this.HotWater =  0.00;
    }
    void getHotWater(){

        return;
    }
    @Override
    public void addTopping(Coffee coffee) {

    }
    @Override
    public String printCoffee() {
        return this.coffee.printCoffee() + " and hot water";
    }

    @Override
    public double cost() {
        return 0.0;
    }
}
