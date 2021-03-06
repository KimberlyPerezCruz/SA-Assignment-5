package MancalaGame.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import MancalaGame.Player;

public class PlayerPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PlayerPO(new Player[]{});
      } else {
          return new PlayerPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return MancalaGame.util.CreatorCreator.createIdMap(sessionID);
   }
}
