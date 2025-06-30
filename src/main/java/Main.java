import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {


       System.out.print("$ ");

       Scanner scanner = new Scanner(System.in);

       while(true){

           String input =scanner.nextLine();

           if(input.equals("exit 0")){

            System.exit(0);

           }else if(input.substring(0,4).equals("echo")){

             System.out.println(input.substring(5));

           }else if(input.substring(0,4).equals("type")){

             if(input.substring(5,9).equals("echo") || input.substring(5,9).equals("exit") || input.substring(5,9).equals("type")){
            
                System.out.println(input.substring(5,9)+" is a shell builtin");

             }else{
                System.out.println(input.substring(5)+": not found");
             }


           }else{

             System.out.println(input + ": command not found");
             
           }

           System.out.print("$ ");
           
       }
        
    }
}
