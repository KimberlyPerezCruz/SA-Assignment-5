package net.ulno.sa.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import net.ulno.sa.Board;

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
      return net.ulno.sa.util.CreatorCreator.createIdMap(sessionID);
   }
}
