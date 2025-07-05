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

           if(input.equals("exit 0")){

            System.exit(0);

           }else if(input.equals("pwd")){

                System.out.println(new File("").getAbsolutePath());// empty current directory

           }else if(input.substring(0,4).equals("echo")){

             System.out.println(input.substring(5));

           }else if(input.substring(0,4).equals("type")){

             System.out.println(type(input.substring(5)));


           }else if((input != null && input.split("\\s+").length >= 2)){

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
                 return command + " is " + file.getAbsolutePath();
               }
           }

        return command +": not found";


    }

}
