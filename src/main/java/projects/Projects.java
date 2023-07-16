package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.dao.DbConnection;
import projects.exception.DbException;
import projects.service.ProjectService;

public class Projects {

// scanner is used so that the user can interact with the program.
	private Scanner scanner = new Scanner(System.in);
	private ProjectService projectService = new ProjectService();
	
	//@formatter:off
	 private List <String> operations= List.of(
			 "1) Add a project" 
			 );
			 //@formatter:on

	public static void main(String[] args) {
		DbConnection.getConnection();

		new Projects().processUserSelections();

	}// end main
//Because we are allowing the user to enter information real time, we need to use a try loop 
	//with a catch. This looks for correct answers and catches incorrect answers so that the
	//program knows what to do with that incorrect answer that doesn't fit.
	private void processUserSelections() {
		boolean done = false;

		while (!done) {
			try {
				int selection = getUserSelection();
				switch (selection) {
				case -1:
					done=exitMenu();
					break;
				case 1:
					createProject();
					break;
				default:
					System.out.println("\n"+selection+"is not a valid selection. Try again.");
					break;
			}	
			}
			catch (Exception e) {
				System.out.println("\nError" + e + "Try Again");
			}
		}
}// end class
//This method creates the "questions" which the user needs to answer.
	private void createProject() {
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimatedHours=getDecimalInput("Enter the estimated hours");
		BigDecimal actualHours=getDecimalInput("Enter the actual hours");
		Integer difficulty=getIntInput("Enter the project difficulty (1-5)");
		String notes=getStringInput("Enter the project notes");
	
		Project project= new Project();
		
	
	}

//This method creates a path to exit out of the program.
	private boolean exitMenu() {
		System.out.println("Exiting the menu.");
		return true;
	}
//The following methods explain to the computer what type of answer it is looking for, be it
	//integer, boolean, plus give directional prompts to the user when they are entering info.
	
	private int getUserSelection() {
		printOperations();
		Integer input = getIntInput("Enter new menu selection");
		return Objects.isNull(input) ? -1 : input;
	}

	private void printOperations() {
		System.out.println("/These are the available selections. Press the enter key to quit:");
 operations.forEach(line->System.out.println(" "+line));
	}
	private BigDecimal getDecimalInput(String prompt) {
		String input = getStringInput(prompt);
		if (Objects.isNull(input)) {
			return null;
		}
		try {
			return new BigDecimal(input).setScale(2);
		} 
		catch (NumberFormatException e) {
			throw new DbException(input + "is not a valid number. Try again");
		}
	}

	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);
		if (Objects.isNull(input)) {
			return null;
		}
		try {
			return Integer.valueOf(input);
		} catch (NumberFormatException e) {
			throw new DbException(input + "is not a valid number. Try again");
		}
	}

	private String getStringInput(String prompt) {
		System.out.print(prompt + ":");
		String input = scanner.nextLine();
		return input.isBlank() ? null : input.trim();
	}

}// end package
