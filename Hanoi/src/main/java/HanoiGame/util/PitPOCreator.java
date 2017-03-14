package HanoiGame.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import HanoiGame.Pit;

public class PitPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PitPO(new Pit[]{});
      } else {
          return new PitPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return HanoiGame.util.CreatorCreator.createIdMap(sessionID);
   }
}
