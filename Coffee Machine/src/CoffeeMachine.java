import java.util.Scanner;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int cash;
    private Scanner scanner;

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.selectAction();
    }

    public CoffeeMachine() {
        water = 400;
        milk = 540;
        coffeeBeans = 120;
        cups = 9;
        cash = 550;
        scanner = new Scanner(System.in);
    }

    private void status() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water %n", water);
        System.out.printf("%d ml of milk%n", milk);
        System.out.printf("%d g of coffee beans %n", coffeeBeans);
        System.out.printf("%d disposable cups %n", cups);
        System.out.printf("$%d of money", cash);

        selectAction();
    }

    private void selectAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = scanner.next();

        switch (action) {
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
            case "remaining":
                status();
                break;
            case "exit":
                break;
            default:
                System.out.println("Wrong input");
                selectAction();
        }
    }

    private void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String choice = scanner.next();

        switch (choice) {
            case "1":
                Espresso espresso = new Espresso();
                reduceIngredients(espresso);
                break;
            case "2":
                Latte latte = new Latte();
                reduceIngredients(latte);
                break;
            case "3":
                Cappuccino cappuccino = new Cappuccino();
                reduceIngredients(cappuccino);
                break;
            case "back":
                selectAction();
                break;
            default:
                System.out.println("Wrong input");
                buy();
        }
    }

    private void reduceIngredients(Coffee coffee) {
        if (water >= coffee.getWater()){
            water -= coffee.getWater();
        } else {
            System.out.println("Sorry, not enough water!");
            selectAction();
            return;
        }

        if (milk >= coffee.getMILK()){
            milk -= coffee.getMILK();
        } else {
            System.out.println("Sorry, not enough milk!");
            selectAction();
            return;
        }

        if (coffeeBeans >= coffee.getCOFFEE_BEANS()) {
            coffeeBeans -= coffee.getCOFFEE_BEANS();
        } else {
            System.out.println("Sorry, not enough milk!");
            selectAction();
            return;
        }

        if (cups > 0) {
            cups--;
        } else {
            System.out.println("Sorry, not enough cups!");
            selectAction();
            return;
        }

        cash += coffee.getCOST();

        selectAction();
    }

    private void fill() {
        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeBeans += scanner.nextInt();

        System.out.println("Write how many disposable cups of coffee you want to add:");
        cups += scanner.nextInt();

        selectAction();
    }

    private void take(){
        System.out.printf("I gave you $%d", cash);
        cash = 0;
        selectAction();
    }
}
