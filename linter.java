/**
 * @author Abi Sislo
 * Linter for CS 3861-04
 * 1444789
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;


public class linter {
	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter a file name to be read: ");
	    String inputFile= scanner.next();
		File file = new File(inputFile);
		try {
			Scanner in = new Scanner(file);
			String line = in.nextLine();
			int ln = 1;
			while(in.hasNextLine()){
				line = in.nextLine();
				if(!in.hasNextLine())
					newline(line, ln);
				else
					check(line, ln);
				ln++;
			}
			in.close();
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	/**
	 * Checks a string for all possible errors with semicolons, brackets, trailing whitespace,
	 * quotation marks, and multiple statements. Prints out the error on the specific line when
	 * an error is found
	 * @param str string to be checked
	 * @param ln line number being checked
	 */
	public static void check(String str, int ln){
		if(!str.matches(".*"+Pattern.quote(";")+"$")){
			if(str.matches(Pattern.quote("{")+"$"))
				System.out.printf(ln +". An opening brace can not stand alone\n");
			else if(str.matches(".*"+Pattern.quote("}")+"$"))
				System.out.printf(ln + ". A closing brace must stand alone\n");
			else if(str.matches("/s*" + Pattern.quote("}")+"$"))
				System.out.printf(ln + ". A closing brace must stand alone\n");
			else
				System.out.printf(ln + ". Should end with a semicolon\n");
		}else if(str.matches("^.*\\s$"))
			System.out.printf(ln + ". Trailing whitespace\n");
		else if(str.matches(".*==.*") && !str.matches(".*===.*"))
			System.out.printf(ln + ". Should only use strict equality\n");
		else if(str.matches(".*\".*\".*") && !str.matches(".*'.*"))
			System.out.printf(ln + ". Should use single quotes\n");
		else if(str.matches(".*;.*;"))
			System.out.printf(ln + ". Use only one statement per line");
		else if(str.length() > 80)
			System.out.printf(ln + ". Lines should not be longer than 80 characters\n");
	}
	/**
	 * Checks to see if the line ends with a newline character. Prints out a statement if error is found
	 * @param str string to be checked
	 * @param ln line number being checked
	 */
	public static void newline(String str, int ln){
		if(!str.matches("^$"))
			System.out.printf(ln +". Program should end with a new line");
	}
}