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
   
package sa.util;

import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import sa.Board;
import sa.Pit;
import sa.Player;

import java.util.Collection;
import java.util.Collections;

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
      else if (value instanceof Collection)
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

   
   //==========================================================================
   
   public de.uniks.networkparser.list.BooleanList takeOppositePebbles(Player movingPlayer, Player otherPlayer, int curLocation)
   {
      
      de.uniks.networkparser.list.BooleanList result = new de.uniks.networkparser.list.BooleanList();
      
      for (Board obj : this)
      {
         result.add( obj.takeOppositePebbles(movingPlayer, otherPlayer, curLocation) );
      }
      return result;
   }

   
   //==========================================================================
   
   public BoardSet ReDistributeCounterclockwise(Pit src, Player currentPlayer, Player otherPlayer)
   {
      return BoardSet.EMPTY_SET;
   }

   /**
    * Loop through the current set of Board objects and collect a set of the Player objects reached via players. 
    * 
    * @return Set of Player objects reachable via players
    */
   public PlayerSet getPlayers()
   {
      PlayerSet result = new PlayerSet();
      
      for (Board obj : this)
      {
         result.with(obj.getPlayers());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Board objects and collect all contained objects with reference players pointing to the object passed as parameter. 
    * 
    * @param value The object required as players neighbor of the collected results. 
    * 
    * @return Set of Player objects referring to value via players
    */
   public BoardSet filterPlayers(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getPlayers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Board object passed as parameter to the Players attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Players attributes.
    */
   public BoardSet withPlayers(Player value)
   {
      for (Board obj : this)
      {
         obj.withPlayers(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Board object passed as parameter from the Players attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public BoardSet withoutPlayers(Player value)
   {
      for (Board obj : this)
      {
         obj.withoutPlayers(value);
      }
      
      return this;
   }

}
