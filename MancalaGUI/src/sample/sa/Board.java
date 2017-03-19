/*
   Copyright (c) 2017 Salonika

   Permission is hereby granted, free of charge, to any person obtaining a copy of this software
   and associated documentation files (the "Software"), to deal in the Software without restriction,
   including without limitation the rights to use, copy, modify, merge, publish, distribute,
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
   furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in all copies or
   substantial portions of the Software.

   The Software shall be used for Good, not Evil.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package sa;

import de.uniks.networkparser.interfaces.SendableEntity;
import sa.util.PlayerSet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
    *
    * @see <a href='../../../../../../src/main/java/net/ulno/sa/Model.java'>Model.java</a>
 */
   public  class Board implements SendableEntity
{


   //==========================================================================

   protected PropertyChangeSupport listeners = null;

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }

   public boolean addPropertyChangeListener(PropertyChangeListener listener)
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }

   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }

   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }


   //==========================================================================


     /*
     *  Take all pebbles from pit opposite from the current player's pit by emptying the opposite pit.
     *  Place these pebbles in calling player's Kalah:
     *      store number of pebbles in current player's Kalah
     *      add the number of pebbles in opposite pit to current player's Kalah
     */
   public boolean takeOppositePebbles( Player movingPlayer, Player otherPlayer, int curLocation ) {
       // Same implementation as hw4 just made work for this version.
       int pebblesInOpp = 0;
       int pebblesOtherP;
       int pebblesMovingP;
       int old;
       //Checks if it is really landing on an empty pit.
       if(movingPlayer.getPitsIHave().get(curLocation).getPebblesIn()==0) {
           pebblesOtherP = otherPlayer.getPebblesHolding();
           pebblesMovingP = movingPlayer.getPebblesHolding();
           pebblesInOpp = otherPlayer.getPitsIHave().get(5-curLocation).getPebblesIn();
           old = movingPlayer.getPitsIHave().get(6).getPebblesIn();
           System.out.println("Pebbles in " + otherPlayer.getName() + "'s pit " + (5-curLocation) + " is " + pebblesInOpp + "\n");

           //Setting both pits to 0;
           movingPlayer.getPitsIHave().get(curLocation).setPebblesIn(0);
           otherPlayer.getPitsIHave().get(5-curLocation).setPebblesIn(0);

           // Adding opposite player plus 1 pebble because of the last pebble that was dropped on empty pit.
           movingPlayer.getPitsIHave().get(6).setPebblesIn(pebblesInOpp + 1 + old);

           // Adding, subtracting all holding pebbles.
           movingPlayer.setPebblesHolding(pebblesInOpp + pebblesMovingP);
           otherPlayer.setPebblesHolding(pebblesOtherP - pebblesInOpp);
           return true;
       }
       //It did not land on an empty pit.
       else return false;
   }

   /*
    *  Redistributes pebbles from currentPlayer (src pit), until no pits are left.
    *  If it gets to the currentPlayer's Kalah, it starts distributing pebbles on
    *  otherPlayer's pits.
    *  If it gets to the otherPlayer's Kalah, it starts distributing pebbles on
    *  currentPlayer's pits.
    */
    public Pit ReDistributeCounterclockwise(Pit src, Player currentPlayer, Player otherPlayer){
       // Same implementation as hw4 just made work for this version.
        int pebbles = src.getPebblesIn();
        int i = 0;
        int p = 0;

        Pit landindPit = new Pit();
        Pit temp = src.getSuccessor();
        src.setPebblesIn(0);
        currentPlayer.setPebblesHolding(currentPlayer.getPebblesHolding()-pebbles);// Players pebbles are been redistributed.
        while(i<pebbles){

            Player player = temp.getPlayerIBelongTo().get(0);//Adding one more pebble to player.
            player.setPebblesHolding(player.getPebblesHolding()+1);

            // If this happens we need to start from start of other player's pits
            if(temp.isIsKalah() && temp.getPlayerIBelongTo().get(0).equals(currentPlayer))
            {
                int pebblesin = temp.getPebblesIn();
                temp.setPebblesIn(pebblesin+1);
                landindPit = temp;
                temp =  otherPlayer.getPitsIHave().get(0);// Next that follows its otherPlayer's pit.
            }
            // If this happens we need to start from start of current player's pits
            else if(temp.isIsKalah() && temp.getPlayerIBelongTo().get(0).equals(otherPlayer))
            {
                //Because this method adds a pebble to this players at the beginning not matter what,
                //and can't add one to opposite players Kalah, need to reduce one.
                player.setPebblesHolding(player.getPebblesHolding()-1);
                landindPit = temp;
                temp =  currentPlayer.getPitsIHave().get(0);// Next that follows its currentPlayer's pit.
            }
            else {
                p = temp.getPebblesIn();
                temp.setPebblesIn(p + 1);
                landindPit = temp;
                temp = temp.getSuccessor();
            }
            i++;
        }
        return landindPit;
    }


   //==========================================================================
   
   
   public void removeYou()
   {
      withoutPlayers(this.getPlayers().toArray(new Player[this.getPlayers().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Board ----------------------------------- Player
    *              board                   players
    * </pre>
    */
   
   public static final String PROPERTY_PLAYERS = "players";

   private PlayerSet players = null;
   
   public PlayerSet getPlayers()
   {
      if (this.players == null)
      {
         return PlayerSet.EMPTY_SET;
      }
   
      return this.players;
   }

   public Board withPlayers(Player... value)
   {
      if(value==null){
         return this;
      }
      for (Player item : value)
      {
         if (item != null)
         {
            if (this.players == null)
            {
               this.players = new PlayerSet();
            }
            
            boolean changed = this.players.add (item);

            if (changed)
            {
               item.withBoard(this);
               firePropertyChange(PROPERTY_PLAYERS, null, item);
            }
         }
      }
      return this;
   } 

   public Board withoutPlayers(Player... value)
   {
      for (Player item : value)
      {
         if ((this.players != null) && (item != null))
         {
            if (this.players.remove(item))
            {
               item.setBoard(null);
               firePropertyChange(PROPERTY_PLAYERS, item, null);
            }
         }
      }
      return this;
   }

   public Player createPlayers()
   {
      Player value = new Player();
      withPlayers(value);
      return value;
   } 
}
