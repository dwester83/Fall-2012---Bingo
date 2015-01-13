package jss2;
/**
 * BingoBall represents a ball used in a Bingo game.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 9/21/2008
 */

public class BingoBall implements Comparable<BingoBall> {
	
	private char letter;
	private int number;
	private final int STARTING_NUMBER = 1;
	private final int ENDING_NUMBER = 75;
  
  /**
   * Sets up this Bingo ball with the specified number and the
   * appropriate letter.
   *
   * @param num  the number to be applied to the new bingo ball
   */
	public BingoBall (int num) {
		setNumber(num);
    }
	
	/**
	 * Get the letter of the ball.
	 * 
	 * @return char letter of the ball
	 */
	public char getLetter() {
		return letter;
	}
	
	/**
	 * Get the number of the ball.
	 * 
	 * @return int number of the ball
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * Sets the ball to a number.
	 * 
	 * @param num Number the ball would be set to
	 */
	public void setNumber(int num) {
		
		validate(num);
		
		number = num;
	    
	    if (num <= 15)
	      letter = 'B';
	    else
	      if (num <= 30)
	        letter = 'I';
	      else
	        if (num <= 45)
	          letter = 'N';
	        else
	          if (num <= 60)
	            letter = 'G';
	          else
	            letter = 'O';
	}
	
	/**
	 * Checks if the number entered is valid.
	 * 
	 * @param num Number that is being tested
	 */
	private void validate(int num){
		if (num < STARTING_NUMBER || num > ENDING_NUMBER)
			throw new NumberFormatException(num + " is not a vaild number. Valid numbers are " + STARTING_NUMBER + " through " + ENDING_NUMBER + ".");
	}
	
	/**
	 * Checks if two BingoBalls are equal.
	 * 
	 * @param ball Ball checked if it's equal
	 */
	public boolean equals(Object ball) {
		if ((ball != null) && (this.getClass().equals(ball.getClass()))) {
			BingoBall otherBall = (BingoBall) ball;
			return (otherBall.getNumber() == this.number);
		} else 
			return false;
	}
	
	/**
	 * Compares two different BingoBalls.
	 * 
	 * @param ball Ball to compare if it's before, after, or equal.
	 */
	public int compareTo(BingoBall ball) {
	    return this.number - ball.getNumber();
	}
	
	/**
	* Returns a string representation of this bingo ball.
	*
	* @return  a string representation of the bingo ball
	*/
	public String toString () {
		return (letter + " " + number);
	}
}
