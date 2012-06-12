import javax.swing.*;
import java.awt.*;

/** Class that represents a player. Can be a computer or human-controlled character.
 * 
 *  @author Daniel Neel */
public class Player {

    /** Name of this player. */
    private String name;

    /** Player's jail status.
     *  0 = not in jail.
     *  1 = last week in jail.
     *  2 = second week in jail.
     *  3 = first week in jail.
     *  Other values not allowed. */
    private int inJail; // TODO: This needed? Couldn't I just use board position?

    /** Amount of one dollar bills this player has. */
    private int ones;

    /** Amount of five dollar bills this player has. */
    private int fives;

    /** Amount of ten dollar bills this player has. */
    private int tens;

    /** Amount of twenty dollar bills this player has. */
    private int twenties;

    /** Amount of fifty dollar bills this player has. */
    private int fifties;

    /** Amount of hundred dollar bills this player has. */
    private int hundreds;

    /** Total amount of money this player has. */
    private int totalMoney;

    /** Amount of five hundred dollar bills this player has. */
    private int fiveHundreds;

    /** Amount of properties this player owns. */
    private int totalPropertiesOwned;

    /** Amount of houses this player owns. */
    private int totalHousesOwned;

    /** Amount of hotels this player owns. */
    private int totalHotelsOwned;

    /** Number of get out of jail free cards this player owns. */
    private int GOOJFCards;

    /** This player's current Board Cell, from 0-39. */
    private BoardCell currentCell;

    /** Image associated with this player. Used as an icon to indicate board position. */
    private Icon image;

    /** An index that represents which element of BoardProperties[] this Player is standing on. */
    private int cellIndex;

    // TODO: Make add/remove functions for ownedOutlets. Use something other than an array for ease?

    /** Actions to take when a player object is initially created. Players
     *  are only created at the start of the game. Different players start
     *  at different positions on the board (Same cell, different walking space.) */
    public Player(int playerNumber, IdeopolyGUI gui) {

	inJail = 0; // TODO: This needed? Couldn't I just use board position?

	// Initial cash values.
	ones	      = 5;
	fives	      = 5;
	tens	      = 5;
	twenties      = 6;
	fifties	      = 2;
	hundreds      = 2;
	fiveHundreds  = 2;
	totalMoney    = (ones + (fives * 5) + (tens * 10) + (twenties * 20)
	                + (fifties * 50) + (hundreds * 100) + (fiveHundreds * 500) );

	totalPropertiesOwned = 0;
	totalHousesOwned     = 0;
	totalHotelsOwned     = 0;

	// TODO: Create an empty array? Or is it already created?
	//	ownedOutlets[] = ;

	GOOJFCards = 0;

	currentCell = gui.boardProperties[0];
	cellIndex   = 0;
	switch (playerNumber) {
	    case 1: name = "Player 1 (H)";
		image = new ImageIcon("images/p1Present.jpg");
		break;
	    case 2: name = "Player 2 (C)";
		image = new ImageIcon("images/p2Present.jpg");
		break;
	    case 3: name = "Player 3 (C)";
		image = new ImageIcon("images/p3Present.jpg");
		break;
	    case 4: name = "Player 4 (C)";
		image = new ImageIcon("images/p4Present.jpg");
		break;

	    default: System.out.println("Error: tried to create a non-standard player.");
	}
    }

    /** Get the amount of bills of the given type for this player. */
    // TODO: Make this return an int instead? Would make much more sense.
    //       ^-- Do that. returning a string is unintuitive, pointless complexity.
    public String getCash(String billType) {
	// TODO: Have the string parameter refer to the actual value. So i could just say "return billType" 
	// rather than switch

	switch (billType) {
	    case "ones":
		return Integer.toString(ones);
	    case "fives":
		return Integer.toString(fives);
	    case "tens":
		return Integer.toString(tens);
	    case "twenties":
		return Integer.toString(twenties);
	    case "fifties":
		return Integer.toString(fifties);
	    case "hundreds":
		return Integer.toString(hundreds);
	    case "fiveHundreds": // TODO: Replace this with 'fiveHundreds'
		return Integer.toString(fiveHundreds);
            case "total":
		return Integer.toString(totalMoney);
	    default:
		return "Error! Incorrect argument."; // TODO: Make this an error code thing.
	}
    }

    /** Give this player a new property. */
    public void addProperty(BoardCell property) {
	//TODO: Implement this. add the property, and increment number of properties owned.
    }
    /** Remove a property from this player. */
    public void removeProperty(BoardCell property) {
	//TODO: Implement this. remove the property, decrement number of properties owned.
    }

    /** Have this player roll the dice. */
    public void roll() {
	// TODO: Implement this
    }

    /** Return the image associated with this player. */
    public Icon getImage() {
	return image;
    }

    /** Get this player's current position. */
    public BoardCell getCell() {
	return currentCell;
    }

    /** Move this player to a given position q. q refers to the cell the Player wants 
     *  to move to. For example, changePosition(3) will move any player to the 4th
     *  BoardCell on the board. */
    public void changeCell(int index, IdeopolyGUI gui) { // TODO: Better function name.
	// Only allow valid indexes.
	if (index < 0 || index > 39) {
	    System.out.println("Error! That cell is outside the bounds of valid cells!");
	}
	else {
	    currentCell = gui.boardProperties[index];
	    cellIndex = index;
	}
    }

    /** Change this player's amount a of currency type t. */
    // TODO: Rename this setCash() ? Seems more consistent with getters/setters, and 
    //       is more accurate to its purpose.
    public void addCash(String t, int a) {
	// TODO: This results in the Player having negative cash values. That's not allowed...
	// TODO: Make sure addCash handles negative values appropriately.
	switch (t) {
	case "ones":         ones  += a;
	    break;
	case "fives":        fives += a;
	    break;
	case "tens":         tens  += a;
	    break;
	case "twenties":     twenties += a;
	    break;
	case "fifties":      fifties  += a;
	    break;
	case "hundreds":     hundreds += a;
	    break;
	case "fiveHundreds": fiveHundreds += a;
	    break;
	default: System.out.println("Invalid currency amount.");
	    break;
	}

	// After money total changes, update the totalMoney field accordingly.
	totalMoney = ( ones + (fives * 5) + (tens * 10) + (twenties * 20) + (fifties * 50) + (hundreds * 100) + (fiveHundreds * 500) );

    }

    /** Set this players currency of type t to a given amount a.*/
    // TODO: With this, is addCash() now obsolete? For example, I could setCash("x", p.getCash("x") + 5)
    public void setCash(String t, int a) {
	// TODO: Make sure this handles negative values appropriately.
	switch (t) {
	case "ones":         ones  = a;
	    break;
	case "fives":        fives = a;
	    break;
	case "tens":         tens  = a;
	    break;
	case "twenties":     twenties = a;
	    break;
	case "fifties":      fifties  = a;
	    break;
	case "hundreds":     hundreds = a;
	    break;
	case "fiveHundreds": fiveHundreds = a;
	    break;
	default: System.out.println("Invalid currency amount.");
	    break;
	}

	// After money total changes, update the totalMoney field accordingly.
	// TODO: Make a method for this? Is reused a bit. updateTotalMoney() ?
	totalMoney = ( ones + (fives * 5) + (tens * 10) + (twenties * 20) + (fifties * 50) + (hundreds * 100) + (fiveHundreds * 500) );
    }

    /** Give this player a get out of jail free card. */
    public void giveGOOJF() {
	GOOJFCards++;
    }

    /** Have this player spend one of their get out of jail free cards. */
    public void spendGOOJF() {
	if (GOOJFCards <= 0) {
	    // TODO: Display this--v in status message area.
	    System.out.println("Error! You can't spend a Get Out of Jail Free card if you have 0 or less.");
	}
	else {
	    //TODO: Don't allow this when the person's not in jail.
	    GOOJFCards--;
	    inJail = 0;
	}
    }

    /** Return the number of GOOJF cards this player owns. */
    public int getNumGOOJFCards() {
	return GOOJFCards;
    }

    /** See what week of jail this person is in. */
    public int getJailStatus() {
	return inJail;
    }

    /** Set the value of this Player's inJail property. */
    public void setJailStatus(int weeks) {
	if (weeks != 0 && weeks != 1 && weeks != 2 && weeks != 3)
	    System.out.println("Invalid weeks value. Must be either 0, 1, 2, or 3.");
	else
	    inJail = weeks;
    }

    /** Get the number of houses this player owns. */
    public int getNumHouses() {
	return totalHousesOwned;
    }

    /** Get the number of hotels this player owns. */
    public int getNumHotels() {
	return totalHotelsOwned;
    }

    /** Set the number of houses this player owns. */
    public void setNumHouses(int num) {
	totalHousesOwned = num;
    }

    /** Set the number of hotels this player owns. */
    public void setNumHotels(int num) {
	totalHotelsOwned = num;
    }

    /** Determine if the given amount will bankrupt this Player
     *  if (s)he has to pay it.
     *  Return True if Player will be bankrupted, False otherwise. */
    public Boolean willBankrupt(int amount) {
	if ( totalMoney < amount )
	    return true;
	else
	    return false;
    }

    /** Convert this Player's cash into bill type desiredBill.
     *  For example, if a Player needs more 5s, this will convert all the Player's
     *  cash to 5s. By default (with an invalid argument), converts to 500s. */
    public void spreadCash(int desiredBill) {
	// The amount still left to be spread out.
	int amountNotSpread = totalMoney;
	// The number of a given type of bill spread.
	int numBillsSpread;

	fiveHundreds = 0;
	hundreds     = 0;
	fifties	     = 0;
	twenties     = 0;
	tens	     = 0;
	fives	     = 0;
	ones	     = 0;

	int billValues[]   = {500, 100, 50, 20, 10, 5, 1};
	int newValues[] = {fiveHundreds, hundreds, fifties, twenties, tens, fives, ones};

	// TODO: Clean up this mess of code.
	//	if input is x, set item[0] to x, and item[x] to fiveHundreds
	//      And update the rest of the behavior accordingly.

	if (desiredBill == 1)
	    swapValues(billValues, newValues, 1, ones, 6);

	else if (desiredBill == 5)
	    swapValues(billValues, newValues, 5, fives, 5);

	else if (desiredBill == 10)
	    swapValues(billValues, newValues, 10, tens, 4);

	else if (desiredBill == 20)
	    swapValues(billValues, newValues, 20, twenties, 3);

	else if (desiredBill == 50)
	    swapValues(billValues, newValues, 50, fifties, 2);

	else if (desiredBill == 100)
	    swapValues(billValues, newValues, 100, hundreds, 1);

	for (int i = 0; i <= 6; i++) {
	    if ( (amountNotSpread / billValues[i]) != 0) {
		numBillsSpread   = amountNotSpread / billValues[i];
		newValues[i]    += numBillsSpread;
		amountNotSpread -= (billValues[i] * numBillsSpread);
	    }
	}

	fiveHundreds = newValues[0];
	hundreds     = newValues[1];
	fifties      = newValues[2];
	twenties     = newValues[3];
	tens         = newValues[4];
	fives	     = newValues[5];
	ones         = newValues[6];

	if (desiredBill == 1) {
	    fiveHundreds = newValues[6];
	    ones         = newValues[0];
	}
	else if (desiredBill == 5) {
	    fiveHundreds = newValues[5];
	    fives        = newValues[0];
	}
	else if (desiredBill == 10) {
	    fiveHundreds = newValues[4];
	    tens	 = newValues[0];
	}
	else if (desiredBill == 20) {
	    fiveHundreds = newValues[3];
	    twenties     = newValues[0];
	}
	else if (desiredBill == 50) {
	    fiveHundreds = newValues[2];
	    fifties      = newValues[0];
	}
	else if (desiredBill == 100) {
	    fiveHundreds = newValues[1];
	    hundreds     = newValues[0];
	}
    }

    // LEFTOFFHERE: Check over this. I'm not sure if it really makes sense or not. Tired.
    /** I'm not really sure what this method does, other than simplifying some above code. */
    private void swapValues(int[] billVals, int[] newVals, int firstValue, int first2Value, int second) {
	billVals[0]      = firstValue;
	newVals[0]       = first2Value;
	billVals[second] = 500;
	newVals[second]  = fiveHundreds;
    }

    /** Bankrupt this player. */
    public void bankruptPlayer() {
	// TODO: Do more than just set cash values. The player can still 
	// be considered alive, given money, etc. in this state.
	// TODO: Set the player's text to red when this happens maybe also?
	// TODO: Maybe give the player an isBankrupt field, that can be used elsewhere.
	ones         = 0;	
	fives	     = 0;
	tens	     = 0;
	twenties     = 0;
	fifties	     = 0;
	hundreds     = 0;
	fiveHundreds = 0;
	totalMoney   = 0;
	image = null;
    }

    /** Get the name of this Player. */
    public String getName() {
	return name;
    }

    /** Get the number of properties this Player owns. */
    public int getNumOwnedProperties() {
	return totalPropertiesOwned;
    }

    /** Get the index of the BoardCell in boardProperties[] this Player 
     *  is standing on. */
    public int getIndex() {
	return cellIndex;
    }

    /** Put the given Player p in jail, and enable the "Use get 
     *  out of jail free card" button if the Player is the human player. */
    public void putInJail(IdeopolyGUI gui) {
	// TODO: Make a visual indicator for when a person's in jail. 
	// IE: put a little colored square on their portrait that indicates the week they're in.
	// Or change the color of the text by their name.

	// Allow the main player to use their cards.
	if (name == "Player 1 (H)" && GOOJFCards > 0) {
	    gui.useGOOJFCard.setEnabled(true);
	}

	changeCell(10, gui);
	setJailStatus(3);
    }
}
