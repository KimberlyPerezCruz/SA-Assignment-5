package HanoiGame.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new PitCreator());
      jsonIdMap.with(new PitPOCreator());
      jsonIdMap.with(new PlayerCreator());
      jsonIdMap.with(new PlayerPOCreator());
      return jsonIdMap;
   }
}
