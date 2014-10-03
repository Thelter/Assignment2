/* Student Names: Matthew Hickman and Erin Hannan
 * Lab Section: 16820
 */

import java.util.Scanner;
import java.util.Random;

//Runs the game from a main method
//Takes no inputs into main method, but configures game from UI
public class Driver
{
	//Secret code after code is generated
	private StringBuilder code =  new StringBuilder();
	
	//Number of pegs in the secret code, defaults to 4
	int codeLength = 4;
	
	//List of generated Peg objects from code
	//4 is default value, must be changed for larger list
	private Peg[] pegList = new Peg[codeLength];
	
	//Configuration value for debug mode
	private boolean showCode = false;
	
	
	//Main method which queries for debug mode and starts game
	public static void main(String args[])
	{
		//Configuration value for debug mode
		boolean configValue = false;
		
		//Number of pegs in code
		int codeLen = 4;
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Would you like to run in debug mode?(Secret code will be shown at each guess)[Enter true or false]:");
		configValue = scan.nextBoolean();
		start(configValue,codeLen);
	}
	
	
	//Sets configuration value (Obsolete Method)
	public void setConfig(boolean cValue)
	{
		this.showCode = cValue;
	}
	
	//Sets secret code value (Obsolete Method)	
	public void setCode(StringBuilder setter)
	{
		code = setter;
	}
	
	//Returns Secret Code value (Obsolete Method)
	public StringBuilder getSecretCode()
	{
		return code;
	}
	
	//Returns a StringBuilder object with the pegs and their locations in a string
	//Takes number of Pegs to be generated in code (set to 4 as default)
	public StringBuilder generateCode(int numPegs)
	{
		System.out.println("\nGenerating Secret Code...\n");
		StringBuilder code = new StringBuilder();
		Random gen = new Random();
		int numColors = 6; //Variable to change for different numbers of colors
		int count = 0;
		
		//Generates using a random integer value from 0-numColors
		while (count < numPegs)
		{
			int number;
			number = gen.nextInt(numColors);
			//Constructor in Peg object sets color value based on number
			//Peg methods have to be changed for values greater than 6
			Peg color = new Peg(number);
			pegList[count] = color;
			code.append(color.colorChar);
			
			count += 1;
		}
		
		return code;
	}
	
	//Runs the game after user has specified they would like to play
	//Returns results of game and queries to play again until N is entered
	public void runGame(StringBuilder code, boolean debug)
	{
		//Runs the mastermind game with the code, peg values, and debug config passed in as parameters
		Game mastermind = new Game(code,pegList,debug);
		
		Scanner scan = new Scanner(System.in);
		String answer =  new String();
		StringBuilder secretCode = new StringBuilder();
		
		//Queries and runs game again for Y and exits for N
		System.out.println("Are you ready for another game? (Y/N): ");
		answer = scan.nextLine();
		
		if (answer.equalsIgnoreCase("y"))
		{
			//Defaults to a 4 peg secret code
			secretCode = this.generateCode(this.codeLength);
			this.runGame(secretCode,debug);
		}
		else
		{
			System.out.println("Okay, exiting");
			System.exit(0);
		}
	}
	
	//Static method which runs the game with the Debug mode config and codeLength passed in
	//Outputs the introduction and queries to play game
	public static void start(boolean config, int codeLength)
	{
		Scanner scan = new Scanner(System.in);
		Driver runner =  new Driver();
		runner.setConfig(config);
		String answer = new String();
		StringBuilder secretCode = new StringBuilder();
		
		//Prints all output text and instructions
		System.out.println("Welcome to Mastermind.  Here are the rules.\n");
		System.out.println("This is a text version of the classic board game Mastermind.");
		System.out.println("The computer will think of a secret code. The code consists of 4 colored pegs.");
		System.out.println("The pegs MUST be one of six colors: blue, green, orange, purple, red, or yellow. A color may ");
		System.out.println("appear more than once in the code. You try to guess what colored pegs are in the code and what ");
		System.out.println("order they are in.   After you make a valid guess the result (feedback) will be displayed.");
		System.out.println("The result consists of a black peg for each peg you have guessed exactly correct (color and ");
		System.out.println("position) in your guess.  For each peg in the guess that is the correct color, but is out of position, ");
		System.out.println("you get a white peg.  For each peg, which is fully incorrect, you get no feedback. \n");
		System.out.println("Only the first letter of the color is displayed. B for Blue, R for Red, and so forth.");
		System.out.println("When entering guesses you only need to enter the first character of each color as a capital letter.");
		System.out.println("You may also enter in 'history' when asked for a guess for a list of past guess results.\n");
		System.out.println("You have 12 guesses to figure out the secret code or you lose the game.  Are you ready to play?");
		System.out.println("(Y/N): ");
		answer = scan.nextLine();
		
		//If Y, generates the code and begins the game
		//Also checks the configuration value and outputs the code if its set to true
		//If N, exits the program
		if (answer.equalsIgnoreCase("y"))
		{
			//Code length is set to 4 by default, set in main method
			secretCode = runner.generateCode(codeLength);
			if (config)
			{
				System.out.println("The Secret Code is: " + secretCode);
			}
			runner.runGame(secretCode,config);
		}
		else
		{
			System.out.println("Okay, exiting");
			System.exit(0);
		}

	}
}
