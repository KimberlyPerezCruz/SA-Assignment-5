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
   
package net.ulno.sa.util;

import de.uniks.networkparser.list.SimpleSet;
import net.ulno.sa.Board;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import net.ulno.sa.util.PlayerSet;
import net.ulno.sa.Player;

public class BoardSet extends SimpleSet<Board>
{
	protected Class<?> getTypClass() {
		return Board.class;
	}

   public BoardSet()
   {
      // empty
   }

   public BoardSet(Board... objects)
   {
      for (Board obj : objects)
      {
         this.add(obj);
      }
   }

   public BoardSet(Collection<Board> objects)
   {
      this.addAll(objects);
   }

   public static final BoardSet EMPTY_SET = new BoardSet().withFlag(BoardSet.READONLY);


   public BoardPO createBoardPO()
   {
      return new BoardPO(this.toArray(new Board[this.size()]));
   }


   public String getEntryType()
   {
      return "net.ulno.sa.Board";
   }


   @Override
   public BoardSet getNewList(boolean keyValue)
   {
      return new BoardSet();
   }


   public BoardSet filter(Condition<Board> condition) {
      BoardSet filterList = new BoardSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public BoardSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Board>)value);
      }
      else if (value != null)
      {
         this.add((Board) value);
      }
      
      return this;
   }
   
   public BoardSet without(Board value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Board objects and collect a set of the Player objects reached via boardIBelongTo. 
    * 
    * @return Set of Player objects reachable via boardIBelongTo
    */
   public PlayerSet getBoardIBelongTo()
   {
      PlayerSet result = new PlayerSet();
      
      for (Board obj : this)
      {
         result.with(obj.getBoardIBelongTo());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Board objects and collect all contained objects with reference boardIBelongTo pointing to the object passed as parameter. 
    * 
    * @param value The object required as boardIBelongTo neighbor of the collected results. 
    * 
    * @return Set of Player objects referring to value via boardIBelongTo
    */
   public BoardSet filterBoardIBelongTo(Object value)
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
      
      BoardSet answer = new BoardSet();
      
      for (Board obj : this)
      {
         if (neighbors.contains(obj.getBoardIBelongTo()) || (neighbors.isEmpty() && obj.getBoardIBelongTo() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Board object passed as parameter to the BoardIBelongTo attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their BoardIBelongTo attributes.
    */
   public BoardSet withBoardIBelongTo(Player value)
   {
      for (Board obj : this)
      {
         obj.withBoardIBelongTo(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public BoardSet takeOppositePebbles(Player player, int curLocation)
   {
      return BoardSet.EMPTY_SET;
   }

   
   //==========================================================================
   
   public BoardSet takeOppositePebbles(Player movingPlayer, Player otherPlayer, int curLocation)
   {
      return BoardSet.EMPTY_SET;
   }

//   /**
//    * Loop through current set of ModelType objects and remove the Board object passed as parameter from the BoardIBelongTo attribute of each of it.
//    *
//    * @return The original set of ModelType objects now without the old neighbor.
//    */
//   public BoardSet withoutBoardIBelongTo(Player value)
//   {
//      for (Board obj : this)
//      {
//         obj.withoutBoardIBelongTo(value);
//      }
//
//      return this;
//   }

}
