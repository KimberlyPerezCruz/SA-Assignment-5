/*
   Copyright (c) 2017 kimberly_93pc
   
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
   
package MancalaGame;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import de.uniks.networkparser.EntityUtil;
import MancalaGame.util.PitSet;
import MancalaGame.Pit;

/**
    * 
    * @see <a href='../../../../src/main/java/MancalaGame/Model.java'>Model.java</a>
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
      withoutPiot(this.getPiot().toArray(new Pit[this.getPiot().size()]));
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
      result.append(" ").append(this.getPebbles_holding());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_PEBBLES_HOLDING = "pebbles_holding";
   
   private int pebbles_holding;

   public int getPebbles_holding()
   {
      return this.pebbles_holding;
   }
   
   public void setPebbles_holding(int value)
   {
      if (this.pebbles_holding != value) {
      
         int oldValue = this.pebbles_holding;
         this.pebbles_holding = value;
         this.firePropertyChange(PROPERTY_PEBBLES_HOLDING, oldValue, value);
      }
   }
   
   public Player withPebbles_holding(int value)
   {
      setPebbles_holding(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Player ----------------------------------- Pit
    *              player                   piot
    * </pre>
    */
   
   public static final String PROPERTY_PIOT = "piot";

   private PitSet piot = null;
   
   public PitSet getPiot()
   {
      if (this.piot == null)
      {
         return PitSet.EMPTY_SET;
      }
   
      return this.piot;
   }

   public Player withPiot(Pit... value)
   {
      if(value==null){
         return this;
      }
      for (Pit item : value)
      {
         if (item != null)
         {
            if (this.piot == null)
            {
               this.piot = new PitSet();
            }
            
            boolean changed = this.piot.add (item);

            if (changed)
            {
               item.withPlayer(this);
               firePropertyChange(PROPERTY_PIOT, null, item);
            }
         }
      }
      return this;
   } 

   public Player withoutPiot(Pit... value)
   {
      for (Pit item : value)
      {
         if ((this.piot != null) && (item != null))
         {
            if (this.piot.remove(item))
            {
               item.setPlayer(null);
               firePropertyChange(PROPERTY_PIOT, item, null);
            }
         }
      }
      return this;
   }

   public Pit createPiot()
   {
      Pit value = new Pit();
      withPiot(value);
      return value;
   } 
}
