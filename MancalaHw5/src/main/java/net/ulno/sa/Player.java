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
import de.uniks.networkparser.EntityUtil;
import net.ulno.sa.util.PitSet;
import net.ulno.sa.Pit;
import net.ulno.sa.util.BoardSet;
import net.ulno.sa.Board;
   /**
    * 
    * @see <a href='../../../../../../src/main/java/net/ulno/sa/Model.java'>Model.java</a>
 */
   public  class Player implements SendableEntity
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
      withoutPitsIHave(this.getPitsIHave().toArray(new Pit[this.getPitsIHave().size()]));

      withoutPlayerIBelongTo(this.getPlayerIBelongTo().toArray(new Pit[this.getPlayerIBelongTo().size()]));
      withoutPlayer(this.getPlayer().toArray(new Board[this.getPlayer().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }


   //==========================================================================

   public static final String PROPERTY_NAME = "name";

   private String name;

   public String getName()
   {
      return this.name;
   }

   public void setName(String value)
   {
      if ( ! EntityUtil.stringEquals(this.name, value)) {

         String oldValue = this.name;
         this.name = value;
         this.firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }

   public Player withName(String value)
   {
      setName(value);
      return this;
   }


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();

      result.append(" ").append(this.getName());
      result.append(" ").append(this.getPebblesHolding());
      return result.substring(1);
   }



   //==========================================================================

   public static final String PROPERTY_PEBBLESHOLDING = "pebblesHolding";

   private int pebblesHolding;

   public int getPebblesHolding()
   {
      return this.pebblesHolding;
   }

   public void setPebblesHolding(int value)
   {
      if (this.pebblesHolding != value) {

         int oldValue = this.pebblesHolding;
         this.pebblesHolding = value;
         this.firePropertyChange(PROPERTY_PEBBLESHOLDING, oldValue, value);
      }
   }

   public Player withPebblesHolding(int value)
   {
      setPebblesHolding(value);
      return this;
   }


   /********************************************************************
    * <pre>
    *              many                       many
    * Player ----------------------------------- Pit
    *              playerIBelongTo                   pitsIHave
    * </pre>
    */

   public static final String PROPERTY_PITSIHAVE = "pitsIHave";

   private PitSet pitsIHave = null;

   public PitSet getPitsIHave()
   {
      if (this.pitsIHave == null)
      {
         return PitSet.EMPTY_SET;
      }

      return this.pitsIHave;
   }

   public Player withPitsIHave(Pit... value)
   {
      if(value==null){
         return this;
      }
      for (Pit item : value)
      {
         if (item != null)
         {
            if (this.pitsIHave == null)
            {
               this.pitsIHave = new PitSet();
            }

            boolean changed = this.pitsIHave.add (item);

            if (changed)
            {
               item.withPlayerIBelongTo(this);
               firePropertyChange(PROPERTY_PITSIHAVE, null, item);
            }
         }
      }
      return this;
   }

   public Player withoutPitsIHave(Pit... value)
   {
      for (Pit item : value)
      {
         if ((this.pitsIHave != null) && (item != null))
         {
            if (this.pitsIHave.remove(item))
            {
               item.withoutPlayerIBelongTo(this);
               firePropertyChange(PROPERTY_PITSIHAVE, item, null);
            }
         }
      }
      return this;
   }



   public Pit createPitsIHave()
   {
      Pit value = new Pit();
      withPitsIHave(value);
      return value;
   }


   /********************************************************************
    * <pre>
    *              many                       many
    * Player ----------------------------------- Pit
    *              pitsIHave                   playerIBelongTo
    * </pre>
    */

   public static final String PROPERTY_PLAYERIBELONGTO = "playerIBelongTo";

   private PitSet playerIBelongTo = null;

   public PitSet getPlayerIBelongTo()
   {
      if (this.playerIBelongTo == null)
      {
         return PitSet.EMPTY_SET;
      }

      return this.playerIBelongTo;
   }

   public Player withPlayerIBelongTo(Pit... value)
   {
      if(value==null){
         return this;
      }
      for (Pit item : value)
      {
         if (item != null)
         {
            if (this.playerIBelongTo == null)
            {
               this.playerIBelongTo = new PitSet();
            }

            boolean changed = this.playerIBelongTo.add (item);

            if (changed)
            {
               item.withPitsIHave(this);
               firePropertyChange(PROPERTY_PLAYERIBELONGTO, null, item);
            }
         }
      }
      return this;
   }

   public Player withoutPlayerIBelongTo(Pit... value)
   {
      for (Pit item : value)
      {
         if ((this.playerIBelongTo != null) && (item != null))
         {
            if (this.playerIBelongTo.remove(item))
            {
               item.withoutPitsIHave(this);
               firePropertyChange(PROPERTY_PLAYERIBELONGTO, item, null);
            }
         }
      }
      return this;
   }

   public Pit createPlayerIBelongTo()
   {
      Pit value = new Pit();
      withPlayerIBelongTo(value);
      return value;
   }


   /********************************************************************
    * <pre>
    *              one                       many
    * Player ----------------------------------- Board
    *              boardIBelongTo                   player
    * </pre>
    */

   public static final String PROPERTY_PLAYER = "player";

   private BoardSet player = null;

   public BoardSet getPlayer()
   {
      if (this.player == null)
      {
         return BoardSet.EMPTY_SET;
      }

      return this.player;
   }

   public Player withPlayer(Board... value)
   {
      if(value==null){
         return this;
      }
      for (Board item : value)
      {
         if (item != null)
         {
            if (this.player == null)
            {
               this.player = new BoardSet();
            }

            boolean changed = this.player.add (item);

            if (changed)
            {
               item.withBoardIBelongTo(this);
               firePropertyChange(PROPERTY_PLAYER, null, item);
            }
         }
      }
      return this;
   }

   public Player withoutPlayer(Board... value)
   {
      for (Board item : value)
      {
         if ((this.player != null) && (item != null))
         {
            if (this.player.remove(item))
            {
               item.setBoardIBelongTo(null);
               firePropertyChange(PROPERTY_PLAYER, item, null);
            }
         }
      }
      return this;
   }

   public Board createPlayer(Board board)
   {
//      Board value = new Board();
      withPlayer(board);
      // Same implementation as hw4 made it work for this version.
      this.name = name;
      this.pebblesHolding = 0;
      this.pitsIHave = new PitSet();
      for(int i = 0; i < 7; i++){


         if(i==6){
            this.pitsIHave.add(i, new Pit());
            this.pitsIHave.get(i).setPebblesIn(0);
            this.pitsIHave.get(i).setIsKalah(true);
         }
         else {
            this.pitsIHave.add(i, new Pit());
            this.pitsIHave.get(i).setPebblesIn(4);
            this.pitsIHave.get(i).setIsKalah(false);
         }
         if(i>0 && i<7){
            this.pitsIHave.get(i-1).setSuccessor(this.pitsIHave.last());
         }

      }

      return board;
   } 

   public Board createPlayer()
   {
      Board value = new Board();
      withPlayer(value);
      return value;
   } 
}
