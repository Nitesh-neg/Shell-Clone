import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {


       System.out.print("$ ");

       Scanner scanner = new Scanner(System.in);

       while(true){

           String input =scanner.nextLine();

           // exit the program immediately and give the status code as 0

           if(input.equals("exit 0")){

            System.exit(0);

            }else if(input.length() >= 4 && input.substring(0,4).equals("echo")){

             System.out.println(input.substring(5));

           }else if(input.length() >= 4 && input.substring(0,4).equals("type")){

             System.out.println(type(input.substring(5)));


           }else if(input.equals("pwd")){

               //File f = new File("nitsh");  means f is the reference to nitsh that is in nitsh. it does not check if nitsh exist or not.

                System.out.println(new File("").getAbsolutePath());
           
           
            } else if((input != null && input.split("\\s+").length >= 2)){

            try {
                    String[] paths = input.split("\\s+"); // split by whitespace
                    ProcessBuilder pb = new ProcessBuilder(paths);
                    Process process = pb.start();

                    // Get both stdout and stderr
                    InputStream stdout = process.getInputStream();
                    InputStream stderr = process.getErrorStream();

                    // Wait for the command to complete
                    int exitCode = process.waitFor();

                    if (exitCode == 0) {
                        stdout.transferTo(System.out);
                    } else {
                        System.err.println("Error running command:");
                        stderr.transferTo(System.err);
                    }

                } catch (IOException | InterruptedException e) {
                    System.err.println("Exception: " + e.getMessage());
                }

          }else {

             System.out.println(input + ": command not found");
             
           }

           System.out.print("$ ");
           
       }
        
    }

    public static String type(String command){
        String[] commands = {"exit", "echo", "type","pwd"};

        // Path is the environment 
        //colon-separated list of directories.
        String path_commands = System.getenv("PATH");
        String[] paths = path_commands.split(":");// different directories

        for(int i=0;i<commands.length;i++){
            // using objects.equals , so that it can handle null
            // if one of them is null 
            // use .equals() --> only on those that you are sure they are not null , else it will give nullPointerException.
            if(Objects.equals(commands[i], command)){
                return command + " is a shell builtin";
            }
        }

       for (String path : paths) {
           
          // creates a Java File object that represents a file or directory located at: path + "/" + command
          // just represents the location of the file --> does not execute and also does not if it exist or not.

           File file = new File(path, command); 
           if (file.exists() && file.canExecute()) {
                 return command + " is " + file.getAbsolutePath();
               }
           }

        return command +": not found";


    }

}
