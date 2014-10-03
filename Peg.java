//Object for processing peg values
public class Peg 
{
	//Variables to be set for each peg
	//Easily accessed by game and user
	public int colorNumber; //Number by order in introduction
	public char colorChar; //Char by first char in color name
	public String color = new String(); //Name of color
	
	//Constructor that takes in a colors number and returns a peg
	//Peg has correct color values based on number, only 6 values coded for
	//Must be changed to account for more colors
	public Peg(int number)
	{
		colorNumber = number;
		
		if (number == 0)
		{
			colorChar = 'B';
			color = "Blue";
		}
		else if (number == 1)
		{
			colorChar = 'G';
			color = "Green";
		}
		else if (number == 2)
		{
			colorChar = 'O';
			color = "Orange";
		}			
		else if (number == 3)
		{
			colorChar = 'P';
			color = "Purple";
		}			
		else if (number == 4)
		{
			colorChar = 'R';
			color = "Red";
		}
		else if (number == 5)
		{
			colorChar = 'Y';
			color = "Yellow";
		}
	}
	
	
	//Constructor that takes in a colors Char value and returns a peg
	//Peg has correct color value and number based on Char, only 6 values coded for
	//Must be changed to account for more colors
	public Peg(char colorC)
	{
		if (colorC == 'B')
		{
			colorNumber = 0;
			color = "Blue";
		}
		else if (colorC == 'G')
		{
			colorNumber = 1;
			color = "Green";
		}
		else if (colorC == 'O')
		{
			colorNumber = 2;
			color = "Orange";
		}			
		else if (colorC == 'P')
		{
			colorNumber = 3;
			color = "Purple";
		}			
		else if (colorC == 'R')
		{
			colorNumber = 4;
			color = "Red";
		}
		else if (colorC == 'Y')
		{
			colorNumber = 5;
			color = "Yellow";
		}
		
	}
	
	//Returns the pegs color Char based on number input, only 6 values coded for
	//Must be changed to account for more colors
	public char getColor(int number)
	{
		if (number == 0)
		{
			return 'B';
		}
		else if (number == 1)
		{
			return 'G';
		}
		else if (number == 2)
		{
			return 'O';
		}			
		else if (number == 3)
		{
			return 'P';
		}			
		else if (number == 4)
		{
			return 'R';
		}
		else if (number == 5)
		{
			return 'Y';
		}
		
		return '+';
	}
	
	//Returns the pegs color number based on color Char input, only 6 values coded for
	//Must be changed to account for more colors
	public int getColorNumber(char color)
	{
		if (color == 'B')
		{
			return 0;
		}
		else if (color == 'G')
		{
			return 1;
		}
		else if (color == 'O')
		{
			return 2;
		}			
		else if (color == 'P')
		{
			return 3;
		}			
		else if (color == 'R')
		{
			return 4;
		}
		else if (color == 'Y')
		{
			return 5;
		}
		
		return 100;
	}
}
