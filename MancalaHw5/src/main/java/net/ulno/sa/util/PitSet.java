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
   
package net.ulno.sa.util;

import de.uniks.networkparser.list.SimpleSet;
import net.ulno.sa.Pit;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.BooleanList;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import net.ulno.sa.util.PlayerSet;
import net.ulno.sa.Player;
import net.ulno.sa.util.PitSet;

public class PitSet extends SimpleSet<Pit>
{
	protected Class<?> getTypClass() {
		return Pit.class;
	}

   public PitSet()
   {
      // empty
   }

   public PitSet(Pit... objects)
   {
      for (Pit obj : objects)
      {
         this.add(obj);
      }
   }

   public PitSet(Collection<Pit> objects)
   {
      this.addAll(objects);
   }

   public static final PitSet EMPTY_SET = new PitSet().withFlag(PitSet.READONLY);


   public PitPO createPitPO()
   {
      return new PitPO(this.toArray(new Pit[this.size()]));
   }


   public String getEntryType()
   {
      return "net.ulno.sa.Pit";
   }


   @Override
   public PitSet getNewList(boolean keyValue)
   {
      return new PitSet();
   }


   public PitSet filter(Condition<Pit> condition) {
      PitSet filterList = new PitSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public PitSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Pit>)value);
      }
      else if (value != null)
      {
         this.add((Pit) value);
      }
      
      return this;
   }
   
   public PitSet without(Pit value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Pit objects and collect a list of the pebblesIn attribute values. 
    * 
    * @return List of int objects reachable via pebblesIn attribute
    */
   public NumberList getPebblesIn()
   {
      NumberList result = new NumberList();
      
      for (Pit obj : this)
      {
         result.add(obj.getPebblesIn());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pit objects and collect those Pit objects where the pebblesIn attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Pit objects that match the parameter
    */
   public PitSet filterPebblesIn(int value)
   {
      PitSet result = new PitSet();
      
      for (Pit obj : this)
      {
         if (value == obj.getPebblesIn())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pit objects and collect those Pit objects where the pebblesIn attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Pit objects that match the parameter
    */
   public PitSet filterPebblesIn(int lower, int upper)
   {
      PitSet result = new PitSet();
      
      for (Pit obj : this)
      {
         if (lower <= obj.getPebblesIn() && obj.getPebblesIn() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pit objects and assign value to the pebblesIn attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Pit objects now with new attribute values.
    */
   public PitSet withPebblesIn(int value)
   {
      for (Pit obj : this)
      {
         obj.setPebblesIn(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Pit objects and collect a list of the isKalah attribute values. 
    * 
    * @return List of boolean objects reachable via isKalah attribute
    */
   public BooleanList getIsKalah()
   {
      BooleanList result = new BooleanList();
      
      for (Pit obj : this)
      {
         result.add(obj.isIsKalah());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pit objects and collect those Pit objects where the isKalah attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Pit objects that match the parameter
    */
   public PitSet filterIsKalah(boolean value)
   {
      PitSet result = new PitSet();
      
      for (Pit obj : this)
      {
         if (value == obj.isIsKalah())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pit objects and assign value to the isKalah attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Pit objects now with new attribute values.
    */
   public PitSet withIsKalah(boolean value)
   {
      for (Pit obj : this)
      {
         obj.setIsKalah(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Pit objects and collect a set of the Player objects reached via playerIBelongTo. 
    * 
    * @return Set of Player objects reachable via playerIBelongTo
    */
   public PlayerSet getPlayerIBelongTo()
   {
      PlayerSet result = new PlayerSet();
      
      for (Pit obj : this)
      {
         result.with(obj.getPlayerIBelongTo());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Pit objects and collect all contained objects with reference playerIBelongTo pointing to the object passed as parameter. 
    * 
    * @param value The object required as playerIBelongTo neighbor of the collected results. 
    * 
    * @return Set of Player objects referring to value via playerIBelongTo
    */
   public PitSet filterPlayerIBelongTo(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      PitSet answer = new PitSet();
      
      for (Pit obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPlayerIBelongTo()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Pit object passed as parameter to the PlayerIBelongTo attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their PlayerIBelongTo attributes.
    */
   public PitSet withPlayerIBelongTo(Player value)
   {
      for (Pit obj : this)
      {
         obj.withPlayerIBelongTo(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Pit object passed as parameter from the PlayerIBelongTo attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PitSet withoutPlayerIBelongTo(Player value)
   {
      for (Pit obj : this)
      {
         obj.withoutPlayerIBelongTo(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Pit objects and collect a set of the Pit objects reached via oppositeOf. 
    * 
    * @return Set of Pit objects reachable via oppositeOf
    */
   public PitSet getOppositeOf()
   {
      PitSet result = new PitSet();
      
      for (Pit obj : this)
      {
         result.with(obj.getOppositeOf());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Pit objects and collect all contained objects with reference oppositeOf pointing to the object passed as parameter. 
    * 
    * @param value The object required as oppositeOf neighbor of the collected results. 
    * 
    * @return Set of Pit objects referring to value via oppositeOf
    */
   public PitSet filterOppositeOf(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      PitSet answer = new PitSet();
      
      for (Pit obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getOppositeOf()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow oppositeOf reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Pit objects reachable via oppositeOf transitively (including the start set)
    */
   public PitSet getOppositeOfTransitive()
   {
      PitSet todo = new PitSet().with(this);
      
      PitSet result = new PitSet();
      
      while ( ! todo.isEmpty())
      {
         Pit current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getOppositeOf()).minus(result);
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Pit object passed as parameter to the OppositeOf attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their OppositeOf attributes.
    */
   public PitSet withOppositeOf(Pit value)
   {
      for (Pit obj : this)
      {
         obj.withOppositeOf(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Pit object passed as parameter from the OppositeOf attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PitSet withoutOppositeOf(Pit value)
   {
      for (Pit obj : this)
      {
         obj.withoutOppositeOf(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Pit objects and collect a set of the Pit objects reached via successor. 
    * 
    * @return Set of Pit objects reachable via successor
    */
   public PitSet getSuccessor()
   {
      PitSet result = new PitSet();
      
      for (Pit obj : this)
      {
         result.with(obj.getSuccessor());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Pit objects and collect all contained objects with reference successor pointing to the object passed as parameter. 
    * 
    * @param value The object required as successor neighbor of the collected results. 
    * 
    * @return Set of Pit objects referring to value via successor
    */
   public PitSet filterSuccessor(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      PitSet answer = new PitSet();
      
      for (Pit obj : this)
      {
         if (neighbors.contains(obj.getSuccessor()) || (neighbors.isEmpty() && obj.getSuccessor() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow successor reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Pit objects reachable via successor transitively (including the start set)
    */
   public PitSet getSuccessorTransitive()
   {
      PitSet todo = new PitSet().with(this);
      
      PitSet result = new PitSet();
      
      while ( ! todo.isEmpty())
      {
         Pit current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getSuccessor()))
            {
               todo.with(current.getSuccessor());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Pit object passed as parameter to the Successor attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Successor attributes.
    */
   public PitSet withSuccessor(Pit value)
   {
      for (Pit obj : this)
      {
         obj.withSuccessor(value);
      }
      
      return this;
   }

}
