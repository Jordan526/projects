import java.util.Scanner;

//this class was originally made from back in CS 141 when we did group projects then, this was a class Jasper made
//it checks to see if you either enter yes or no to continue playing
//I decided to implement it into my game as I thought it would fit perfectly

public class ValidMove {
    public char scanForYN(Scanner s, String prompt) {
        String temp = "";
        char yN;
        System.out.print("\n\n" + prompt);
        while (true) {
            temp = s.next();
            yN = temp.toUpperCase().charAt(0);
            if ((yN == 'Y' || yN == 'N') && temp.length() == 1) break;
            else System.out.println("Please enter \"Y\" or \"N\".");
        }
        return yN;
    }
}
