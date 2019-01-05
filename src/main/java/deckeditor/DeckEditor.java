package deckeditor;

import XML.XMLParse;
import cards.type.Color;
import cards.type.Format;
import cards.type.SubType;
import cards.type.SuperType;
import gui.GUI;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;


/**
 * This class is the main class to run the program. The main priortiy of this class is to call
 * external classes and ensure the program executes in the proper order. Another function is
 * this class contains utility methods such as the println and print functions which act as a
 * shortcut to the System.out.println() method with some added date format printing.
 */
//TODO Create a method/fields that keep track of time it takes to run program and print it

public class DeckEditor{
	public static void main(String[] args){
		new DeckEditor().run();
	}
	//TODO make the program download the file if it is missing from DropBox Shared folder or domain
	private void run(){
		XMLParse xmlParse = new XMLParse("Set.xml");
		xmlParse.parse();
		new GUI(xmlParse);
		printErrorTypes();
	}
	
	/**
	 * This utility methods provides a way to accurately check the equality of two ArrayLists.
	 * It does this by checking basic information about each list, such as if the lists are
	 * null and the sizes of each. If these conditions are true, the method will return false.
	 * <p>
	 * If both methods are null, the method returns true
	 * <p>
	 * The method creates a copy of each of the ArrayLists, then sorts them. This is because
	 * this method is checking values of each Array, rather than the order.
	 * <p>
	 * Since this method checks values, and not order, the order in which the arrays are passed
	 * do not matter as the result.
	 *
	 * @param one the first list to compare
	 * @param two the second list to compare
	 * @return a boolean containing the value if lists are the same
	 */
	//TODO make this method allow any number of arrayLists as parameters
	public static boolean equalLists(ArrayList<Format> one, ArrayList<Format> two){
		if (one == null && two == null){
			return true;
		}
		if (one == null || two == null || one.size() != two.size()){
			return false;
		}
		one = new ArrayList<>(one);
		two = new ArrayList<>(two);
		Collections.sort(one);
		Collections.sort(two);
		return one.equals(two);
	}
	
	/**
	 * This method gathers the error data from each of the Enums provided in the program and
	 * calls the findErrorTypes for each. The data comes from the Enums. As the data is gathered,
	 * some values of the Enum will not be present. This method is to print each one of the values.
	 * On a completed build of the project, this method should print nothing.
	 */
	//TODO privitize the enums Erorr type Lists
	private void printErrorTypes(){
		findErrorTypes(Format.errorFormatTypes, "Format");
		findErrorTypes(Color.errorColors, "Color");
		findErrorTypes(SubType.errorSubTypes, "SubType");
		findErrorTypes(SuperType.errorTypes, "SuperType");
	}
	
	/**
	 * This method completes the actual printing of the error types. It scans the array and uses a
	 * StringBuilder to add the data and print said data.
	 *
	 * @param error the list from the Enum containing missing values
	 * @param name  the name of the enum
	 */
	//TODO find a way to make both this method and printErrorTypes() dynamic
	private void findErrorTypes(ArrayList<String> error, String name){
		if (error.size() != 0){
			StringBuilder build = new StringBuilder();
			build.append("Error parsing data: Unknown ").append(name).append(": ").append(
					"\n");
			error.forEach(f -> build.append(f).append("\n"));
			println(build.toString(), Level.INFO);
		}
	}
	public static void printException(String cause, Exception e){
		DeckEditor.println(cause + " - Caused: " + ExceptionUtils.getRootCause(e) + " in ", Level.ERROR);
		for (StackTraceElement stackTraceElement : e.getStackTrace()) {
			DeckEditor.println("\t" + stackTraceElement.getClassName() + " (" + stackTraceElement.getLineNumber() + ")", Level.ERROR);
		}
	}
	public static void print(Object obj){
		System.out.print(obj);
	}
	
	/**
	 * Provides a shortcut to the System.out.println() method as well as attatch the time and
	 * date for acccurate execution analysis.
	 *
	 * @param obj the object to print. This argument is the same as the System.out.println() mehtod
	 * @param level
	 */
	public static void println(Object obj, Level level){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("[" + level + "]" + dtf.format(now) + " : " + obj);
	}

}
