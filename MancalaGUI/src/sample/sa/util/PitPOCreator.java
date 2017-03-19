package sa.util;

import de.uniks.networkparser.IdMap;
import sa.Pit;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

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
      return sa.util.CreatorCreator.createIdMap(sessionID);
   }
}
