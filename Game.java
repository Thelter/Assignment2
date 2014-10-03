import java.util.ArrayList;
import java.util.Scanner;

//Runs the actual game Mastermind
public class Game
{
	//Number of guesses allowed before user losses, defaults to 12
	private int numberOfGuesses = 12;
	
	//Number of colors possible, defaults to 6
	//Methods in Peg must be added to for variables larger than 6
	int numColors = 6;
	
	//Number of Pegs in secret code, defaults to 4
	int codeLength = 4;
	
	//The secret code to be guessed
	private StringBuilder secretCode = new StringBuilder();
	
	//The number of pegs for each color stored in an array
	private int[] colorsInCode = new int[numColors];
	
	//The Peg objects of the secret code stored in an array
	private Peg[] pegList = new Peg[codeLength];
	
	//The guess history of the user
	private ArrayList<String> history = new ArrayList<String>();
	
	//The Debug mode configuration value (defaults to false)
	private boolean showCode = false;
	
	
	//The Constructor of the game object, takes in the secret code, peg array for the code, and Debug mode config
	//Saves configuration and game values and runs game
	public Game(StringBuilder code, Peg[] pegs, boolean config)
	{
		secretCode = code;
		colorsInCode = processColors(code);
		pegList = pegs;
		showCode = config;
		this.runGame();
	}
	
	//Processes a StringBuilder parameter of peg color character values
	//Finds color and stores colors based on number assigned to them by the generator
	//Number is based on order of colors in introduction
	//Returns an array of values with numColors int values for the number of pegs of each color
	//For numbers of colors greater than 6, must be added to
	public int[] processColors(StringBuilder guess)
	{
		int count = 0;
		char color;
		int[] values = new int[numColors];
		
		while (count < this.codeLength)
		{
			color = guess.charAt(count);
			
			if (color == 'B')
			{
				values[0] += 1;
			}
			else if (color == 'G')
			{
				values[1] += 1;
			}
			else if (color == 'O')
			{
				values[2] += 1;
			}		
			else if (color == 'P')
			{
				values[3] += 1;
			}		
			else if (color == 'R')
			{
				values[4] += 1;
			}		
			else if (color == 'Y')
			{
				values[5] += 1;
			}		
			
			count += 1;
		}
		return values;
		
	}
	
	
	//Runs the Mastermind game as a central game runner
	//Handles guess processing and output
	public void runGame()
	{
		Scanner scan = new Scanner(System.in);
		StringBuilder guessCode;
		String guess = new String();
		boolean win = false;
		
		//Checks the boolean value which is set when a user guesses the correct code
		//Also checks number of remaining guesses until they run out
		//Continues asking for valid guesses or 'history' until either value is set
		while ((this.numberOfGuesses > 0) && (!win))
		{
			//Prints code in debug mode
			if (this.showCode)
			{
				System.out.println("Secret Code: " + this.secretCode);
			}
			//Outputs information for guessing
			System.out.println("You have " + numberOfGuesses + " guesses left.");
			System.out.println("What is your next guess?");
			System.out.println("Type in characters for your guess and press enter.");
			System.out.println("Enter guess:");
			guess = scan.nextLine();
			int[] guessValues = new int[6];
			int[] returnPegs = new int[2];
			
			//Checks for history input and outputs history
			if (guess.matches("[Hh]istory"))
			{
				this.guessHistory();
			}
			
			//Checks for valid guess and checks for guess values
			else if (guess.matches("[BGOPRY][BGOPRY][BGOPRY][BGOPRY]"))
			{
				guessCode = new StringBuilder();
				//Copies guess code and creates int value array for Peg colors
				guessCode.append(guess);
				guessValues = this.processColors(guessCode);
				//Checks the guess
				returnPegs = this.checkGuess(guessValues,guessCode);
				
				//Archives guess results and prints them to user
				String output = new String();
				output = (guess + " -> Result: "+ returnPegs[0] + " black pegs, and " + returnPegs[1] + " white pegs.\n\n");
				this.history.add(output);
				System.out.print(output);
				
				//Checks if guess is correct
				if (returnPegs[0] == 4)
				{
					win = true;
				}
				else
				{
					numberOfGuesses -= 1;
				}
			}
			else
			{
				System.out.print(guess + "-> INVALID GUESS\n\n");
			}
		}
		
		//Outputs for win or lose scenarios
		if (win)
		{
			System.out.println("Congrats you won!");
		}
		else
		{
			System.out.println("Out of guesses! You lose, loser.");
		}
	}
	
	
	//Checks the guesses based on passed in guess value and map
	public int[] checkGuess(int[] guessValues, StringBuilder guessCode)
	{
		//peg[0] is number of black pegs, peg[1] is number of whites pegs
		int[] pegs = new int[2];
		StringBuilder codeCopy = new StringBuilder();
		Peg[] guessPegs = new Peg[this.codeLength];
		int [] valueCopy = new int[this.numColors];
		int count = 0;
		
		//Copy all values of original array to copy for safety reasons
		valueCopy[0] = this.colorsInCode[0];
		valueCopy[1] = this.colorsInCode[1];
		valueCopy[2] = this.colorsInCode[2];
		valueCopy[3] = this.colorsInCode[3];
		valueCopy[4] = this.colorsInCode[4];
		valueCopy[5] = this.colorsInCode[5];


		
		codeCopy.append(this.secretCode);
		
		//Finds number of black pegs by running through guess and secret code
		//Runs through char by char checking, changes maps for found values
		while (count < codeCopy.length())
		{
			char guessChar = guessCode.charAt(count);
			
			if ((guessChar) == codeCopy.charAt(count))
			{
				Peg newPeg = new Peg(guessChar);
				guessPegs[count] = newPeg;
				pegs[0] += 1;
				valueCopy[newPeg.colorNumber] -= 1;
				guessValues[newPeg.colorNumber] -= 1;
			}
			count += 1;
		}
		
		//Checks for white pegs
		pegs[1] = checkWhitePegs(guessValues,valueCopy);
		
		return pegs;
	}
	
	//Checks for white pegs using color code maps
	//Returns number of white pegs
	public int checkWhitePegs(int[] guess, int[] code)
	{
		int whitePegs = 0;
		int index = 0;
		
		//Runs through each color value and checks for values of that color in both codes
		while (index < this.numColors)
		{
			if(guess[index] < code[index])
			{
				whitePegs += guess[index];
			}
			else if(code[index] <= guess[index])
			{
				whitePegs += code[index];
			}
			index += 1;
		}
		
		return whitePegs;
	}
	
	//Outputs the guess history of the user
	public void guessHistory()
	{
		int count = 0;
		System.out.println("\nPast Guess results:\n");
		while (count < history.size())
		{
			System.out.println(history.get(count));
			count += 1;
		}
		
		System.out.println("You have " + this.numberOfGuesses + " guesses left");
	}
}
