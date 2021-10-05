package Views;


import java.util.Scanner;

/**
 * This is run from the driver
 * It employs a boolean value to run a while-loop
 * Scanner is used for the case statements
 */

public class MainMenu {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean running = true;


    public static void run() {

        while (running) {
            int option = loginScreen();

            switch (option) {
                case 1:
                    RegisterMenu.run();
                    break;
                case 2:
                    LoginMenu.run();
                    break;
                case 3:
                    running = false;
                    GoodbyeScreen();
                    break;
                default: System.out.println("Input invalid. Please enter a number between 1 and 3.");
                    break;
            }
        }
    }

    /**
     * This is used to print a welcome message
     */

    public static void homeScreen() {

        System.out.printf("\n------------------------------------\n"
                + "\nWelcome to The Bank!\n"
                + "\n------------------------------------\n\n");

    }

    /**
     * This returns the scanner values
     * The try-catch block allows for the scanner to parse the Int after it is typed into the scanner
     * The catch will catch if the scanned value isn't an integer
     * @return
     */
    public static int loginScreen() {

        homeScreen();

        System.out.printf("\n1. Register for account \n"
                + "\n2. Login to account \n"
                + "\n3. Quit\n\n");

        int option = 0;

        try {
            String text = scanner.nextLine();
            option = Integer.parseInt(text);
        }catch(Exception ex){
            System.out.println("Exception has been caught");;
        }

        return option;
    }

    /**
     * For when you are done and want to quit the program
     */
    public static void GoodbyeScreen() {
        System.out.printf("\n\n\n------------------------------------"
                + "\n\n"
                + "Thank you for using The Bank!"
                + "\n\n"
                + "------------------------------------\n\n\n");
    }
}
