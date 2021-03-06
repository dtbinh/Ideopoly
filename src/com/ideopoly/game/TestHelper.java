package com.ideopoly.game;

import junit.framework.TestCase;
import org.junit.*;

/** Class to provide helper methods for use in my testing classes. */
public class TestHelper extends TestCase {
    // TODO: Make a TestHelperTester class to test these methods? Overkill?
    /** Have each Player draw a card. */
    // TODO: Rather than using two separate methods, try to find a way to allow the first
    //       argument to be of type Chance or CommunityChest.
    public static void doActionsAllPlayers(ChanceCards card, GameBoard board, Player p1, Player p2, Player p3, Player p4) {
        // TODO: Do I need the two arguments? They're the same for each time I call them here.
        card.doActions(p1, board);
        card.doActions(p2, board);
        card.doActions(p3, board);
        card.doActions(p4, board);
    }

    public static void doActionsAllPlayers(CommunityChestCards card, GameBoard board, Player p1, Player p2, Player p3, Player p4) {
        // TODO: Is there a way to allow the first argument to have a variable type, so I don't have to
        //       make two separate methods for it?
        // TODO: Do I need the two arguments? They're the same for each time I call them here.
        // TODO: Can I get rid of the Player arguments, and derive them from gui?
        card.doActions(p1, board);
        card.doActions(p2, board);
        card.doActions(p3, board);
        card.doActions(p4, board);
    }

    /** Check that the total cash value v is the same for every Player.*/
    public static void assertCash(int v, Player p1, Player p2, Player p3, Player p4) {
        assertEquals(p1.getCash(CASH_TYPES.total), v);
        assertEquals(p2.getCash(CASH_TYPES.total), v);
        assertEquals(p3.getCash(CASH_TYPES.total), v);
        assertEquals(p4.getCash(CASH_TYPES.total), v);
    }

    /** Make sure the unique total cash values v1/2/3/4 for each 
     *  individual Player are correct. */
    public static void assertCash(int v1, int v2, int v3, int v4, Player p1, Player p2, Player p3, Player p4) {
        assertEquals(p1.getCash(CASH_TYPES.total), v1);
        assertEquals(p2.getCash(CASH_TYPES.total), v2);
        assertEquals(p3.getCash(CASH_TYPES.total), v3);
        assertEquals(p4.getCash(CASH_TYPES.total), v4);
    }


    /** Move all players to the same BoardCell on the gui. */
    public static void changeCellAllPlayers(BoardCell cell, Player p1, Player p2, Player p3, Player p4) {
        p1.setCell(cell);
        p2.setCell(cell);
        p3.setCell(cell);
        p4.setCell(cell);
    }

    /** Ensure that all four players are standi ng on the same cell. */
    public static void assertSameCell(int cell, GameBoard board, Player p1, Player p2, Player p3, Player p4) {
        assertEquals(p1.getCell(), board.boardProperties.get(cell));
        assertEquals(p2.getCell(), board.boardProperties.get(cell));
        assertEquals(p3.getCell(), board.boardProperties.get(cell));
        assertEquals(p4.getCell(), board.boardProperties.get(cell));
    }

    /** Make sure players p1, p2, p3, and p4 have the same jail status s. */
    public static void assertSameJailStatus(int s, Player p1, Player p2, Player p3, Player p4) {
        assertEquals(p1.getJailStatus(), s);
        assertEquals(p2.getJailStatus(), s);
        assertEquals(p3.getJailStatus(), s);
        assertEquals(p4.getJailStatus(), s);
    }
}
