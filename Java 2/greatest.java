import java.util.Scanner;

class Greatest
{

public static void main(String args[])
{
    Scanner sc = new Scanner(System.in);

        System.out.println("Enter First number:");
        int a = sc.nextInt();
    
        System.out.println("Enter Second number:");
        int b = sc.nextInt();

        System.out.println("Enter Third number:");
        int c = sc.nextInt();
    
        if(a>b && a>c)
        System.out.println("The largest number is First:"+a);

        else if(b>a && b>c)
        System.out.println("The largest number is Second:"+b);

        else
        System.out.println("The largest number is Third:"+c);
}
}
