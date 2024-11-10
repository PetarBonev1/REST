import java.util.Scanner;
import java.lang.Math;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter weight in kilograms:");
        int weight = input.nextInt();

        System.out.println("Enter height in centimeters:");
        int height = input.nextInt();

        System.out.println("Enter your gender:");
        String gender = input.next();
        double bMI = 10000 * weight / Math.pow(height, 2);
        if(gender.equals("Male"))
        {
            double iBWM = 50 + 2.3*(height/2.54 - 60);
            if( iBWM < 18.5)
            {
                System.out.printf("Underweight\n");
            }
            else if (iBWM >= 18.5 && bMI < 25)
            {
                System.out.printf("Normal\n");
            }
            else if (iBWM >= 25 && bMI < 30)
            {
                System.out.printf("Overweight\n");
            }
            else {
                System.out.printf("Obese\n");
            }
        }
        else if(gender.equals("Female"))
        {
            double iBWW = 1.07*weight - 148*(Math.pow(weight, 2) / Math.pow(height, 2));
            if( iBWW < 18.5)
            {
                System.out.printf("Underweight\n");
            }
            else if (iBWW >= 18.5 && bMI < 25)
            {
                System.out.printf("Normal\n");
            }
            else if (iBWW >= 25 && bMI < 30)
            {
                System.out.printf("Overweight\n");
            }
            else
            {
                System.out.printf("Obese\n");
            }
        }
        else
        {
            System.out.println("Sorry,no such gender");
        }

    }
}