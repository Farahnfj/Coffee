public class Sugar extends CoffeeDecorator {
    public Sugar(Coffee coffee) {
        super(coffee);
    }
    double Sugar;
    void setSugar(){
        this.Sugar =  0.05;
    }
    void getSugar(){

        return;
    }
    @Override
    public void addTopping(Coffee coffee) {
    }

    @Override
    public String printCoffee() {
        return this.coffee.printCoffee() + " and sugar";
    }

    @Override
    public double cost() {
        return this.coffee.cost()+0.05;
    }
}
