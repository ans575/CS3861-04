package spec1;

/*Abi Sislo
CS 3861-04
*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class spec1{
  public static void main(String[] args) {
    Scanner console=new Scanner(System.in);
    System.out.print("Please enter a file name to be read: ");
    String inputFile= console.next();
    try{
      File tempFile = new File(inputFile);
      Scanner tempScan=new Scanner( tempFile );
      int ln=1;
      while(tempScan.hasNextLine()){
        String line = tempScan.nextLine();
        if(patternCheck(line)){
        	System.out.println("No syntax errors found.");
        }else{
        	System.out.println("Found syntax on line " + ln + ".");
        }
        ln++;
      }
      tempScan.close();
      console.close();
    } catch (FileNotFoundException e) {
	  System.out.println("File not found");
    }
  }
  public static boolean patternCheck(String str){
    return str.endsWith(";")||str.endsWith("{")||str.trim()=="}"||str.trim()==""||str.endsWith("{ }");
  }
}
