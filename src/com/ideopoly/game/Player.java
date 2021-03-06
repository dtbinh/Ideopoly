package com.ideopoly.game;

import javax.swing.*;
import java.awt.*;
import java.util.*;

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
    private int inJail;

    // TODO Thoroughly review uses of the bill variables to make improvements.
    /** A Map of the amounts of all bills belonging to this Player. */
    private Map<CASH_TYPES, Integer> bills = new LinkedHashMap<>();

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
    private ImageIcon image;

    /** Color associated with this Player. Player1 = green, 2 = yellow,
     *  3 = orange, 4 = blue. */
    private Color color;

    // TODO: Make add/remove functions for ownedOutlets. Use something other than an array for ease?
    /** Actions to take when a player object is initially created. Players
     *  are only created at the start of the game. Different players start
     *  at different positions on the board (Same cell, different walking space.) */
    public Player(int playerNumber, Color color, GameBoard board) {

        inJail = 0; // TODO: This needed? Couldn't I just use board position?

        // Dole out initial cash values to this Player.
        for (CASH_TYPES billType : CASH_TYPES.values()) {
            if (billType != CASH_TYPES.total)
                bills.put(billType, billType.getInitialAmount());
        }

        totalPropertiesOwned = 0;
        totalHousesOwned     = 0;
        totalHotelsOwned     = 0;

        this.color = color;

        GOOJFCards = 0;

        currentCell = board.boardProperties.get(0);

        if (playerNumber >= 1 && playerNumber <= 4) {
            name = "Player " + playerNumber + " (" +  (playerNumber == 1 ? "H" : "C")  + ")";
            image = new ImageIcon("res/images/p" + playerNumber + "Present.jpg");
        }
        else
            System.out.println("Error: tried to create a non-standard player.");
    }

    /** Get the amount of bills of the given type for this player. */
    public int getCash(CASH_TYPES billType) {
        return billType.equals(CASH_TYPES.total) ? getTotal() : bills.get(billType);
    }

    /** Return the image associated with this player. */
    public ImageIcon getImage() {
        return image;
    }

    /** Get this player's current position. */
    public BoardCell getCell() {
        return currentCell;
    }

    /** Set this Player's currency of type billType to a given amount a. */
    public void setCash(CASH_TYPES billType, int a) {
        // TODO: Make sure this handles negative values appropriately. Add tests for that?
        bills.put(billType, a);
    }

    /** Get the total amount of money that belongs to this Player. */
    private int getTotal() {
        int totalMoney = 0;
        for (CASH_TYPES billType : CASH_TYPES.values()) {
            if (billType != CASH_TYPES.total)
                totalMoney += (bills.get(billType) * billType.asInt());
        }
        return totalMoney;
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

    /** Get the name of this Player. */
    public String getName() {
        return name;
    }

    /** Get the number of properties this Player owns. */
    public int getNumOwnedProperties() {
        return totalPropertiesOwned;
    }

    /** Set the number of properties owned by this Player to n. */
    public void setNumOwnedProperties(int n) {
        totalPropertiesOwned = n;
    }

    /** Have this player roll the dice. */
    public void roll() {
        // TODO: Implement this
    }

    /** Move this player to a given position p. p refers to the cell the Player wants
     *  to move to. For example, changePosition(3) will move any player to the 4th
     *  BoardCell on the board. */
    public void setCell(BoardCell cell) { // TODO: Better function name.
        currentCell = cell;
    }

    /** Change this Player's amount a of currency type billType. */
    public void addCash(CASH_TYPES billType, int a) {
        // TODO: This results in the Player having negative cash values. That's not allowed...
        // TODO: Make sure addCash handles negative values appropriately.
        if (bills.containsKey(billType))
            bills.put(billType, bills.get(billType) + a);
        else
            System.out.println("Invalid currency amount.");
    }

    /** Give this player a get out of jail free card. */
    public void giveGOOJF() {
        GOOJFCards++;
    }

    /** Have this player spend one of their get out of jail free cards. */
    public void spendGOOJF(GameBoard board) {
        if (GOOJFCards <= 0)
            board.printStatusAndLog("Error! You can't spend a Get Out of Jail Free card if you have 0 or less.");
        else { //TODO: Don't allow this when the person's not in jail.
            GOOJFCards--;
            inJail = 0;
        }
    }

    /** Determine if the given amount will bankrupt this Player
     *  if (s)he has to pay it.
     *  Return True if Player will be bankrupted, False otherwise. */
    public Boolean willBankrupt(int amount) {
        return getTotal() < amount;
    }

    /** Convert this Player's cash into bill type desiredBill.
     *  For example, if a Player needs more 5s, this will convert all the Player's
     *  cash to 5s. */
    public void spreadCash(CASH_TYPES desiredBill) {
        int billsNotSpread = getTotal();
        int numBillsSpread;

        for (CASH_TYPES type : bills.keySet()) {
            bills.put(type, 0);
        }

        int billValues[]   = {500, 100, 50, 20, 10, 5, 1};
        // TODO Can I replace this with a method of bills? Such as toArray or something?
        int newValues[] = {bills.get(CASH_TYPES.fiveHundreds), bills.get(CASH_TYPES.hundreds), bills.get(CASH_TYPES.fifties),
                bills.get(CASH_TYPES.twenties), bills.get(CASH_TYPES.tens), bills.get(CASH_TYPES.fives), bills.get(CASH_TYPES.ones)};

        switch (desiredBill) {
            case ones: swapValues(billValues, newValues, bills.get(desiredBill), bills.get(CASH_TYPES.ones), 6); break;
            case fives: swapValues(billValues, newValues, bills.get(desiredBill), bills.get(CASH_TYPES.fives), 5); break;
            case tens: swapValues(billValues, newValues, bills.get(desiredBill), bills.get(CASH_TYPES.tens), 4); break;
            case twenties: swapValues(billValues, newValues, bills.get(desiredBill), bills.get(CASH_TYPES.twenties), 3); break;
            case fifties: swapValues(billValues, newValues, bills.get(desiredBill), bills.get(CASH_TYPES.fifties), 2); break;
            case hundreds: swapValues(billValues, newValues, bills.get(desiredBill), bills.get(CASH_TYPES.hundreds), 1); break;
        }

        for (int i = 0; i < billValues.length; i++) {
            if ( (billsNotSpread / billValues[i]) != 0) {
                numBillsSpread  = billsNotSpread / billValues[i];
                newValues[i]   += numBillsSpread;
                billsNotSpread -= (billValues[i] * numBillsSpread);
            }
        }

        bills.put(CASH_TYPES.fiveHundreds, newValues[0]);
        bills.put(CASH_TYPES.hundreds, newValues[1]);
        bills.put(CASH_TYPES.fifties, newValues[2]);
        bills.put(CASH_TYPES.twenties, newValues[3]);
        bills.put(CASH_TYPES.tens, newValues[4]);
        bills.put(CASH_TYPES.fives, newValues[5]);
        bills.put(CASH_TYPES.ones, newValues[6]);

        if (desiredBill == CASH_TYPES.ones) {
            bills.put(CASH_TYPES.fiveHundreds, newValues[6]);
            bills.put(CASH_TYPES.ones, newValues[0]);
        }
        else if (desiredBill == CASH_TYPES.fives) {
            bills.put(CASH_TYPES.fiveHundreds, newValues[5]);
            bills.put(CASH_TYPES.fives, newValues[0]);
        }
        else if (desiredBill == CASH_TYPES.tens) {
            bills.put(CASH_TYPES.fiveHundreds, newValues[4]);
            bills.put(CASH_TYPES.tens, newValues[0]);
        }
        else if (desiredBill == CASH_TYPES.twenties) {
            bills.put(CASH_TYPES.fiveHundreds, newValues[3]);
            bills.put(CASH_TYPES.twenties, newValues[0]);
        }
        else if (desiredBill == CASH_TYPES.fifties) {
            bills.put(CASH_TYPES.fiveHundreds, newValues[2]);
            bills.put(CASH_TYPES.fifties, newValues[0]);
        }
        else if (desiredBill == CASH_TYPES.hundreds) {
            bills.put(CASH_TYPES.fiveHundreds, newValues[1]);
            bills.put(CASH_TYPES.hundreds, newValues[0]);
        }
    }

    // LEFTOFFHERE: Check over this. I'm not sure if it really makes sense or not. Tired.
    /** I'm not really sure what this method does, other 
     *  than simplifying some above code. */
    private void swapValues(int[] billVals, int[] newVals, int firstValue, int first2Value, int second) {
        billVals[0]      = firstValue;
        newVals[0]       = first2Value;
        billVals[second] = 500;
        newVals[second]  = bills.get(CASH_TYPES.fiveHundreds);
    }

    /** Bankrupt this player. */
    public void bankruptPlayer(GameBoard board) {
        // TODO: Do more than just set cash values. The player can still
        // be considered alive, given money, etc. in this state.
        // TODO: Maybe give the player an isBankrupt field, that can be used elsewhere.
        for (CASH_TYPES type : bills.keySet()) {
            bills.put(type, 0);
        }
        image = null;

        // TODO: There's probably a better way of doing this whole thing.
        // TODO: Add tests and things for this. Haven't made sure it works yet.
        board.playerRowLabels.get(Integer.parseInt(Character.toString(name.charAt(7))) - 1).setForeground(Color.red);
    }

    /** Put the given Player p in jail, and enable the "Use get
     *  out of jail free card" button if the Player is the human player. */
    public void putInJail(GameBoard board) {
        // TODO: Make a visual indicator for when a person's in jail.
        // IE: put a little colored square on their portrait that indicates the week they're in.
        // Or change the color of the text by their name.

        // Allow the main player to use their cards.
        if (this == board.player1 && GOOJFCards > 0)
            board.useGOOJFCard.setEnabled(true);

        setCell(board.boardProperties.get(10));
        setJailStatus(3);
    }

    /** Have this Player pay the bank amount dollars.
     *  The Player will bankrupt if charged an amount higher than
     *  his/her totalMoney value. */
    public void makePayment(int amount, GameBoard board) {
        if (willBankrupt(amount)) // Player will be bankrupt.
            bankruptPlayer(board);
        else { // Amount is ok.
            int[] paymentAmounts = board.getCashDistribution(amount);

            // TODO: Make this array shared between the two methods somehow?
            CASH_TYPES[] cashTypes = {CASH_TYPES.ones, CASH_TYPES.fives, CASH_TYPES.tens, CASH_TYPES.twenties,
                    CASH_TYPES.fifties, CASH_TYPES.hundreds, CASH_TYPES.fiveHundreds};
            for (int i = 0; i < cashTypes.length; i++) {
                this.spreadCash(cashTypes[i]);
                this.addCash(cashTypes[i], - paymentAmounts[i]);
            }
        }
    }

    /** Have this Player pay Player p amount dollars. This Player
     *  will bankrupt if amount is larger than this Player's totalMoney
     *  value. */
    // TODO: Have any tests transferred over to these new methods.
    public void makePayment(Player p, int amount, GameBoard board) {
        if (this.willBankrupt(amount))
            this.bankruptPlayer(board);
        else {
            // First, get a distribution of what bills to pay.
            int[] paymentAmounts = board.getCashDistribution(amount);

            // Then, for each bill, transfer the correct amount from this Player to p.
            CASH_TYPES[] cashTypes = {CASH_TYPES.ones, CASH_TYPES.fives, CASH_TYPES.tens, CASH_TYPES.twenties,
                    CASH_TYPES.fifties, CASH_TYPES.hundreds, CASH_TYPES.fiveHundreds};

            for (int i = 0; i < cashTypes.length; i++) {
                this.spreadCash(cashTypes[i]);
                this.addCash(cashTypes[i], - paymentAmounts[i]);
                p.addCash(cashTypes[i], paymentAmounts[i]);
            }

            // And set cash back to sensible values.
            p.spreadCash(CASH_TYPES.fiveHundreds);
        }
    }
}
