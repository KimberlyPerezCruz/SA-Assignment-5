package sa.util;

import de.uniks.networkparser.IdMap;
import sa.Player;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

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
      return sa.util.CreatorCreator.createIdMap(sessionID);
   }
}
