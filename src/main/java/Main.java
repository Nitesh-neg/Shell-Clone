import java.io.File;
import java.util.Objects;
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

             System.out.println(type(input.substring(5)));


           }else{

             System.out.println(input + ": command not found");
             
           }

           System.out.print("$ ");
           
       }
        
    }

    public static String type(String command){
        String[] commands = {"exit", "echo", "type"};
        String path_commands = System.getenv("PATH");// taking PATH from operating system
        String[] paths = path_commands.split(":");// different directories

        for(int i=0;i<commands.length;i++){
            if(Objects.equals(commands[i], command)){
                return command + " is a shell builtin";
            }
        }

       for (String path : paths) {
           File file = new File(path, command); // checking if command exist in path or not
           if (file.exists() && file.canExecute()) {
                  System.out.println(command + " is " + file.getAbsolutePath());
                  break;
               }
           }

        return command +": not found";


    }
}
