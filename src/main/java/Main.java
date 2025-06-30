import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {


       System.out.print("$ ");
       Scanner scanner = new Scanner(System.in);

       while(true){
           String input =scanner.nextLine();
           if(input.substring(0,4).equals("echo")){
             System.out.println(input.substring(5));
             System.out.print("$ ");
           }
          
          
           
       }
        
    }
}
