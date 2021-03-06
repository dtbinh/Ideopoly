package com.ideopoly.game;

// TODO: Is this class even needed?
// TODO: Also, should these be made schools rather than railroads?

import javax.swing.*;
import java.awt.*;

/** Represents any of the four railroads.
 *
 *  @author Daniel Neel */
public class Railroad extends BoardCell implements Ownable {
    /** The cost to buy this cell, unimproved. */
    private static final int COST = 200;

    /** The rent for this cell, with no other Railroads owned. */
    private static final int INITIALRENT = 25;

    /** How much money a player can mortgage this Railroad for. */
    private static final int MORTGAGEVALUE = 100;

    /** Status of this Railroad's ownership. If true, a Player owns the property. */
    private boolean owned = false;

    /** Create a new Railroad with a given name, image, and no owner. */
    public Railroad(String newName, String imagePath, Point coordinates, final GameBoard board) {
        super(newName, new ImageIcon("res/images/" + imagePath), coordinates, Color.BLACK, board);
    }

    /** Returns the cost for a Player to buy this 
     *  property, unimproved. */
    public int getCost() {
        return COST;
    }

    /** Returns the rent charged to a Player who lands
     *  on this property, depending on how many Railroads are owned. */
    @Override
    public int getRent() {
        // TODO: This is not correct. This should return different values depending
        // on the # of railroads owned.
        return INITIALRENT;
    }

    /** Return whether or not this UtilityCell is 
     *  currently owned by a Player. */
    @Override
    public boolean isOwned() {
        return owned;
    }

    /** Returns the value this Railroad can be
     *  mortgaged for. */
    public int getMortgage() {
        return MORTGAGEVALUE;
    }
}