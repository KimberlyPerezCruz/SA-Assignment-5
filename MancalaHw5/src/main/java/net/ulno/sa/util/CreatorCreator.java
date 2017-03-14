package net.ulno.sa.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new UniversityCreator());
      jsonIdMap.with(new UniversityPOCreator());
      jsonIdMap.with(new StudentCreator());
      jsonIdMap.with(new StudentPOCreator());
      jsonIdMap.with(new PlayerCreator());
      jsonIdMap.with(new PlayerPOCreator());
      jsonIdMap.with(new PitCreator());
      jsonIdMap.with(new PitPOCreator());
      jsonIdMap.with(new BoardCreator());
      jsonIdMap.with(new BoardPOCreator());
      return jsonIdMap;
   }
}
