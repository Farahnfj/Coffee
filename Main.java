/**
 *  Coffee Order Machine Project
 *  CS 160L
 *  June 30, 2022
 *  Farah Farah
 */
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

///////////////////////////////////////////////////////Main//////////////////////////////////////////////////////
public class Main {
    static Stack<String> stackOfItems = new Stack<>();
    static int checker = 0;

    /**
     *The main function is used to start and exit the application
     * There are 5 methods that are run in the switch/case
     * With each function doing something different.
     * @throws IOException
     * Throwing the exception is neccessary in case something is wrong
     * with the file or it cannot be found.
     */
    public static void main(String[] args) throws IOException {
        int input = 0;
        int [] inventory = new int[6];
        while(input != 1){
            Scanner CaferApplication = new Scanner(System.in);
            System.out.println("Cafe Application Running...");
            System.out.println("Press 1 : Read Inventory");
            System.out.println("Press 2 : Create Coffee Order");
            System.out.println("Press 3 : Update Inventory");
            System.out.println("Press 4 : Update log file");
            System.out.println("Press 5: Suprise Me");
            System.out.println("Press 6 : Exit Application");
            switch(CaferApplication.nextLine()){									//Taking user input for the tasks
                case "1":
                    inventory= inventoryReader("inventory.txt");					//Reading inventory
                    break;
                case "2":
                    inventory = CreateOrder(inventory, stackOfItems);				//Creating order
                    break;
                case "3":
                    inventoryWriter(inventory);										//Writing Inventory
                    break;
                case "4":
                    logwriter(stackOfItems);										//Writing to the Log
                    break;
                case "5":
                    SupriseMe();
                    break;
                case "6":
                    inventoryWriter(inventory);
                    if(!stackOfItems.empty())
                        logwriter(stackOfItems);
                    else
                        System.out.println("Nothing to log. Stack is empty");
                    input = 1;														//Exit
                    break;
                default:
                    System.out.println("Invalid Selection. Please try again");
            }

        }

    }
/////////////////////////////////////////////////End of main/////////////////////////////////////////////////////////



    //////////////////////////////////////////////Create Order Function//////////////////////////////////////////////////

    /**
     *This function is sued to create a new coffee order. It creates the order, pushes it to
     * the stack and uodates the inventory accordingly.
     * @param inventory
     * Its an array that indicates how much of each item is left
     * @param stackOfItems
     * It is a stack that containes all the poosible coffee objects an individual would
     * like to use.
     * @return inventory
     * Returns an updated inventory array with new values dependednt on what is used when
     * CreateOrder is called
     */
    public static int[] CreateOrder(int [] inventory, Stack<String> stackOfItems){
        Scanner userFeedback = new Scanner(System.in);
        ArrayList<String> coffeeOrder = new ArrayList<String> ();
        String s = "";
        Coffee basicCoffee = new BasicCoffee();
        int in = 0;
        if(inventory[0]>0){
            stackOfItems.push("Cost");
        }
        while (in != 1) {
            do{
                if(inventory[0] > 0){
                    System.out.println("Enter the following values to add toppings: 1) milk, 2) hot water, 3.) espresso, 4.) sugar, 5.) whipped cream, e - to complete order");
                    s=userFeedback.nextLine();					//Taking user input on the toppings
                    switch(s){
                        case "1":
                            if(inventory[1] != 0){
                                basicCoffee = new Milk(basicCoffee);
                                inventory[1] = inventory[1]-1;
                                stackOfItems.push("Milk");
                            }
                            else{
                                System.out.println("Out of milk. Try a different topping.");
                            }
                            break;
                        case "2":
                            if(inventory[2] != 0){
                                basicCoffee = new HotWater(basicCoffee);
                                inventory[2] = inventory[2]-1;
                                stackOfItems.push("HotWater");
                            }
                            else{
                                System.out.println("Out of hot water. Try a different topping.");
                            }
                            break;
                        case "3":
                            if(inventory[3] != 0){
                                basicCoffee = new Espresso(basicCoffee);
                                inventory[3] = inventory[3]-1;
                                stackOfItems.push("Espresso");
                            }
                            else{
                                System.out.println("Out of Espresso. Try a different topping.");
                            }
                            break;
                        case "4":
                            if(inventory[4] != 0){
                                basicCoffee = new Sugar(basicCoffee);
                                inventory[4] = inventory[4]-1;
                                stackOfItems.push("Sugar");
                            }
                            else{
                                System.out.println("Out of Sugar. Try a different topping.");
                            }
                            break;
                        case "5":
                            if(inventory[5] != 0){
                                basicCoffee = new WhippedCream(basicCoffee);
                                inventory[5] = inventory[5]-1;
                                stackOfItems.push("WhippedCream");
                            }
                            else{
                                System.out.println("Out of WhippedCream. Try a different topping.");
                            }
                            break;
                        case "e":
                            basicCoffee = new BlackCoffee(basicCoffee);
                            inventory[0] = inventory[0]-1;
                            stackOfItems.push("BlackCoffee");
                            if(inventory[0] == 0){
                                System.out.println("Order Completed. No more coffees.");
                                stackOfItems.push("Order");
                                in = 1;
                            }
                            break;
                        default:
                            System.out.println("Invalid Input");
                    }
                }
                else {
                    System.out.println("Out of Coffee. Visit us later.");
                    in = 1;
                    break;
                }
            }while(!(s.equals("e")));
            if(in!=1){
                System.out.println("Do you want to add another coffee to this order? - yes or no");
                Scanner userOrders = new Scanner(System.in);
                switch(userOrders.nextLine()){
                    case "yes":
                        stackOfItems.push("Cost");
                        break;
                    case "no":
                        stackOfItems.push("Order");
                        in = 1;
                        break;
                    default:
                        stackOfItems.push("Cost");
                        in = 0;
                }
            }
        }
        return inventory;
    }

/////////////////////////////////////////////////End of Create Order/////////////////////////////////////////////////////////


    //////////////////////////////////////////////////Inventory Read////////////////////////////////////////////////////////////

    /**
     * This function reads in a file that ocntains te inventory of each item
     * and puts it into an array that later fucntions can use
     * @param fileName
     * This the file that contains the inventory of each item.
     * @return contents
     * An array that contains the amount of each item that is left.
     * Returns the contents of the file
     */
    public static int[] inventoryReader(String fileName){
        BufferedReader objReader = null;
        int[] contents = new int[6];
        int counter = 0;
        try{
            String currentLine;
            objReader = new BufferedReader(new FileReader(fileName));
            while((currentLine = objReader.readLine()) != null){
                System.out.println(currentLine);
                int lastNumberCast = Integer.parseInt(currentLine.replaceAll("\\D", ""));
                contents[counter] = lastNumberCast;
                counter++;
            }
        }
        catch(Exception e) {
            e.getStackTrace();
        }
        return contents;
    }
//////////////////////////////////////////////End of Inventory Read/////////////////////////////////////////////////////////


    /////////////////////////////////////////////Log Writer/////////////////////////////////////////////////////////////////////

    /**
     *It updates the Logwriter with the new receipts created after the order
     * @param stackOfItems
     *I used this in the Create Order method to push the orders onto the stack
     * In here I am simply adding what was pushed onto the stack to the LogFile
     * @throws IOException
     * Throwing the exception is neccessary in case something is wrong
     * with the file or it cannot be found.
     */
    public static void logwriter(Stack<String> stackOfItems) throws IOException {
        ArrayList<Double> price = new ArrayList<>();
        FileWriter mywriter = new FileWriter("Logfile.txt", true);
        double cost = 0;
        double total_cost = 0;
        Coffee basicCoffee = new BasicCoffee();
        int count = 1;
        try{
            if(!stackOfItems.empty()){

                if(checker == 0){
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH-mm-ss z") ;
                    Date date = new Date(System.currentTimeMillis());
                    mywriter.write("\n\nWriting orders from stack " + formatter.format(date));
                    checker = 1;
                }
                while(!stackOfItems.empty()){
                    String s1 = stackOfItems.pop();
                    switch(s1){
                        case "Order":
                            if(count!=1)
                                mywriter.write("TOTAL COST OF ORDER:" + total_cost);
                            mywriter.write("\nRECEIPT\n");
                            total_cost = 0;
                            count = 1;
                            break;
                        case "BlackCoffee":
                            basicCoffee = new BasicCoffee();
                            basicCoffee = new BlackCoffee(basicCoffee);
                            cost = basicCoffee.cost();
                            mywriter.write("Item " + count +": Black coffee");
                            count++;
                            break;
                        case "Milk":
                            basicCoffee = new Milk(basicCoffee);
                            cost = basicCoffee.cost();
                            mywriter.write("-milk");
                            break;
                        case "HotWater":
                            basicCoffee = new HotWater(basicCoffee);
                            cost = basicCoffee.cost();
                            mywriter.write("-Hot Water");
                            break;
                        case "Espresso":
                            basicCoffee = new Espresso(basicCoffee);
                            cost = basicCoffee.cost();
                            mywriter.write("-Espresso Shot");
                            break;
                        case "Sugar":
                            basicCoffee = new Sugar(basicCoffee);
                            cost = basicCoffee.cost();
                            mywriter.write("-sugar");
                            break;
                        case "WhippedCream":
                            basicCoffee = new WhippedCream(basicCoffee);
                            cost = basicCoffee.cost();
                            mywriter.write("-whipped cream");
                            break;
                        case "Cost":
                            mywriter.write(" | cost:" + cost + "\n");
                            total_cost+=cost;
                            break;
                        default:
                            break;
                    }
                }
                mywriter.write("TOTAL COST OF ORDER:" + total_cost);

                mywriter.flush();
                mywriter.close();
                System.out.println("Successfully wrote to the file.");
            }
            else
                System.out.println("Nothing to log. Stack is empty");
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }
//////////////////////////////////End of Log Writer////////////////////////////////////////////////////////////////////


    /////////////////////////////////////Inventory Writer//////////////////////////////////////////////////////////////////

    /**
     *The purpose of this function is to update the inventory
     * to account for the new orders that were created
     * @param inventory
     * The inventory array is updated in the createorder method
     * and here we just check what the new inventory amount is
     * and overwrite the file to show the new inventory.
     * @throws IOException
     * Throwing the exception is neccessary in case something is wrong
     * with the file or it cannot be found.
     */
    public static void inventoryWriter(int[] inventory) throws IOException {
        FileWriter out = new FileWriter("inventory.txt");
        try{
            out.write("Black Coffee = "+ inventory[0] + "\n");
            out.write("Milk = "+ inventory[1] + "\n");
            out.write("HotWater = "+ inventory[2] + "\n");
            out.write("Espresso = "+ inventory[3] + "\n");
            out.write("Sugar = "+ inventory[4] + "\n");
            out.write("WhippedCream = "+ inventory[5]);
            out.flush();
            out.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        System.out.println("Successfully updated the inventory.");
    }
//////////////////////////////////End of Inventory Writer/////////////////////////////////////////////////////////////////

    /**
     * The purpose of this function is pretty simple.
     * It is meant to give you a random order to choose from
     * each time you call this function
     */
    public static void SupriseMe(){
        ArrayList<String> randomOrders = new ArrayList<String>();
        randomOrders.add("Black Coffee with Milk");
        randomOrders.add("Black Coffee with Whipped Cream");
        randomOrders.add("Black Coffee with Sugar");
        randomOrders.add("Black Coffee with Espresso");
        randomOrders.add("Black Coffee with Hot Water");
        Random rand = new Random();
        int int_random = rand.nextInt(randomOrders.size()-1);
        System.out.println(randomOrders.get(int_random));

    }

}