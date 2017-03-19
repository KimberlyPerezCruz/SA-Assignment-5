package sa.util;

import de.uniks.networkparser.IdMap;
import sa.Board;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class BoardPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new BoardPO(new Board[]{});
      } else {
          return new BoardPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return sa.util.CreatorCreator.createIdMap(sessionID);
   }
}
