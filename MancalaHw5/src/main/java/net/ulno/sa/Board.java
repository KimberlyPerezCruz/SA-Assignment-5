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

package net.ulno.sa;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import net.ulno.sa.Player;
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


   public void removeYou()
   {
      setBoardIBelongTo(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }


   /********************************************************************
    * <pre>
    *              many                       one
    * Board ----------------------------------- Player
    *              player                   boardIBelongTo
    * </pre>
    */

   public static final String PROPERTY_BOARDIBELONGTO = "boardIBelongTo";

   private Player boardIBelongTo = null;

   public Player getBoardIBelongTo()
   {
      return this.boardIBelongTo;
   }

   public boolean setBoardIBelongTo(Player value)
   {
      boolean changed = false;

      if (this.boardIBelongTo != value)
      {
         Player oldValue = this.boardIBelongTo;

         if (this.boardIBelongTo != null)
         {
            this.boardIBelongTo = null;
            oldValue.withoutPlayer(this);
         }

         this.boardIBelongTo = value;

         if (value != null)
         {
            value.withPlayer(this);
         }

         firePropertyChange(PROPERTY_BOARDIBELONGTO, oldValue, value);
         changed = true;
      }

      return changed;
   }

   public Board withBoardIBelongTo(Player value)
   {
      setBoardIBelongTo(value);
      return this;
   }

   public Player createBoardIBelongTo()
   {
      Player value = new Player();
      withBoardIBelongTo(value);
      return value;
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
       int old = 0;
       if(movingPlayer.getPitsIHave().get(curLocation).getPebblesIn()==0) {
           pebblesInOpp = otherPlayer.getPitsIHave().get(5-curLocation).getPebblesIn();
           System.out.println("Pebbles in " + otherPlayer.getName() + "'s pit " + (5-curLocation) + " is " + pebblesInOpp + "\n");
           movingPlayer.getPitsIHave().get(curLocation).setPebblesIn(0);
           otherPlayer.getPitsIHave().get(5-curLocation).setPebblesIn(0);
           old = movingPlayer.getPitsIHave().get(6).getPebblesIn();
           // Adding 1 because of the last pebble that was dropped on empty pit.
           movingPlayer.getPitsIHave().get(6).setPebblesIn(pebblesInOpp + old + 1);
           return true;
       }
       else return false;
   }

    public void ReDistributeCounterclockwise(Pit src, Player currentPlayer, Player otherPlayer){
       // Same implementation as hw4 just made work for this version.
       //**I am having problems because tem.getPlayerIBelongTo() is null and I haven't figure out how to set it.


        int pebbles = src.getPebblesIn();
        int i = 0;
        int p = 0;

        Pit temp = src.getSuccessor();
        src.setPebblesIn(0);
        while(i<pebbles){
//            temp.toString();

            // If this happens we need to start from start of other player's pits
            if(temp.isIsKalah() && temp.getPlayerIBelongTo().equals(currentPlayer))
                temp =  otherPlayer.getPitsIHave().get(0);// Next that follows its otherPlayer's pit.
            // If this happens we need to start from start of current player's pits
            else if(temp.isIsKalah() && temp.getPlayerIBelongTo().equals(otherPlayer))
                temp =  currentPlayer.getPitsIHave().get(0);// Next that follows its currentPlayer's pit.

            p = temp.getPebblesIn();
            temp.setPebblesIn(p+1);
            temp = temp.getSuccessor();
            i++;
        }
    }
}
