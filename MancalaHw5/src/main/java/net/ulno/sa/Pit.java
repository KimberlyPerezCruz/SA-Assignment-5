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
import net.ulno.sa.util.PlayerSet;
import net.ulno.sa.Player;
import net.ulno.sa.util.PitSet;

/**
    * 
    * @see <a href='../../../../../../src/main/java/net/ulno/sa/Model.java'>Model.java</a>
 */
   public  class Pit implements SendableEntity
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
      withoutPlayerIBelongTo(this.getPlayerIBelongTo().toArray(new Player[this.getPlayerIBelongTo().size()]));
      withoutOppositeOf(this.getOppositeOf().toArray(new Pit[this.getOppositeOf().size()]));
      setSuccessor(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_PEBBLESIN = "pebblesIn";
   
   private int pebblesIn;

   public int getPebblesIn()
   {
      return this.pebblesIn;
   }
   
   public void setPebblesIn(int value)
   {
      if (this.pebblesIn != value) {
      
         int oldValue = this.pebblesIn;
         this.pebblesIn = value;
         this.firePropertyChange(PROPERTY_PEBBLESIN, oldValue, value);
      }
   }
   
   public Pit withPebblesIn(int value)
   {
      setPebblesIn(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getPebblesIn());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_ISKALAH = "isKalah";
   
   private boolean isKalah;

   public boolean isIsKalah()
   {
      return this.isKalah;
   }
   
   public void setIsKalah(boolean value)
   {
      if (this.isKalah != value) {
      
         boolean oldValue = this.isKalah;
         this.isKalah = value;
         this.firePropertyChange(PROPERTY_ISKALAH, oldValue, value);
      }
   }
   
   public Pit withIsKalah(boolean value)
   {
      setIsKalah(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Pit ----------------------------------- Player
    *              pitsIHave                   playerIBelongTo
    * </pre>
    */
   
   public static final String PROPERTY_PLAYERIBELONGTO = "playerIBelongTo";

   private PlayerSet playerIBelongTo = null;
   
   public PlayerSet getPlayerIBelongTo()
   {
      if (this.playerIBelongTo == null)
      {
         return PlayerSet.EMPTY_SET;
      }
   
      return this.playerIBelongTo;
   }

   public Pit withPlayerIBelongTo(Player... value)
   {
      if(value==null){
         return this;
      }
      for (Player item : value)
      {
         if (item != null)
         {
            if (this.playerIBelongTo == null)
            {
               this.playerIBelongTo = new PlayerSet();
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

   public Pit withoutPlayerIBelongTo(Player... value)
   {
      for (Player item : value)
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

   public Player createPlayerIBelongTo()
   {
      Player value = new Player();
      withPlayerIBelongTo(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Pit ----------------------------------- Pit
    *              oppositeOf                   oppositeOf
    * </pre>
    */
   
   public static final String PROPERTY_OPPOSITEOF = "oppositeOf";

   private PitSet oppositeOf = null;
   
   public PitSet getOppositeOf()
   {
      if (this.oppositeOf == null)
      {
         return PitSet.EMPTY_SET;
      }
   
      return this.oppositeOf;
   }
   public PitSet getOppositeOfTransitive()
   {
      PitSet result = new PitSet().with(this);
      return result.getOppositeOfTransitive();
   }


   public Pit withOppositeOf(Pit... value)
   {
      if(value==null){
         return this;
      }
      for (Pit item : value)
      {
         if (item != null)
         {
            if (this.oppositeOf == null)
            {
               this.oppositeOf = new PitSet();
            }
            
            boolean changed = this.oppositeOf.add (item);

            if (changed)
            {
               item.withOppositeOf(this);
               firePropertyChange(PROPERTY_OPPOSITEOF, null, item);
            }
         }
      }
      return this;
   } 

   public Pit withoutOppositeOf(Pit... value)
   {
      for (Pit item : value)
      {
         if ((this.oppositeOf != null) && (item != null))
         {
            if (this.oppositeOf.remove(item))
            {
               item.withoutOppositeOf(this);
               firePropertyChange(PROPERTY_OPPOSITEOF, item, null);
            }
         }
      }
      return this;
   }

   public Pit createOppositeOf()
   {
      Pit value = new Pit();
      withOppositeOf(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Pit ----------------------------------- Pit
    *              successor                   successor
    * </pre>
    */
   
   public static final String PROPERTY_SUCCESSOR = "successor";

   private Pit successor = null;

   public Pit getSuccessor()
   {
      return this.successor;
   }
   public PitSet getSuccessorTransitive()
   {
      PitSet result = new PitSet().with(this);
      return result.getSuccessorTransitive();
   }


   public boolean setSuccessor(Pit value)
   {
      boolean changed = false;
      
      if (this.successor != value)
      {
         Pit oldValue = this.successor;
         
         if (this.successor != null)
         {
            this.successor = null;
            oldValue.setSuccessor(null);
         }
         
         this.successor = value;
         //Commented this because otherwise it only two
         // pits can have a successor.
//         if (value != null)
//         {
//            value.withSuccessor(this);
//         }
         
         firePropertyChange(PROPERTY_SUCCESSOR, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Pit withSuccessor(Pit value)
   {
      setSuccessor(value);
      return this;
   } 

   public Pit createSuccessor()
   {
      Pit value = new Pit();
      withSuccessor(value);
      return value;
   } 
}
