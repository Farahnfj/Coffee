
public class BlackCoffee extends CoffeeDecorator {
    public BlackCoffee(Coffee coffee) {
        super(coffee);
    }
    //double BlackCoffee;

    /*void setBlackCoffee(){
        this.BlackCoffee = 0.85;
    }
    void getBlackCoffee(){

        return;
    }*/

    @Override
    public void addTopping(Coffee coffee) {
        instructions();
    }

    @Override
    public String printCoffee() {
        return this.coffee.printCoffee();
    }

    @Override
    public double cost() {
        return this.coffee.cost();
    }

    public void instructions() {
        System.out.println("Pour coffee from pot into cup");
    }
}
