/** Represents the common behaviors of the
 *  Water Works and Electric Company cells. */
public class UtilityCell extends BoardCell {
    // TODO: Add a test class and tests.
    /** The cost to buy this cell, unimproved. */
    private int cost;

    /** Create a new UtilityCell with a given name,
     *  image, and x/y coordinates on the board.*/
    public UtilityCell(String newName, String imagePath, int xPos, int yPos) {
	super(newName, imagePath, xPos, yPos); // Use the BoardCell class' constructor.
    }

    /** Returns the cost for a Player to buy this 
     *  property, unimproved. */
    public int getCost() {
	return cost;
    }
}