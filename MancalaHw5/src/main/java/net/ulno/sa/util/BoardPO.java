package net.ulno.sa.util;

import org.sdmlib.models.pattern.PatternObject;
import net.ulno.sa.Board;
import net.ulno.sa.Player;
import net.ulno.sa.Pit;
import net.ulno.sa.util.PlayerPO;
import net.ulno.sa.util.BoardPO;
import net.ulno.sa.util.PlayerSet;

public class BoardPO extends PatternObject<BoardPO, Board>
{

    public BoardSet allMatches()
   {
      this.setDoAllMatches(true);
      
      BoardSet matches = new BoardSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Board) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public BoardPO(){
      newInstance(null);
   }

   public BoardPO(Board... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public BoardPO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public boolean takeOppositePebbles(Player movingPlayer, Player otherPlayer, int curLocation)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Board) getCurrentMatch()).takeOppositePebbles(movingPlayer, otherPlayer, curLocation);
      }
      return false;
   }

   
   //==========================================================================
   
   public void ReDistributeCounterclockwise(Pit src, Player currentPlayer, Player otherPlayer)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Board) getCurrentMatch()).ReDistributeCounterclockwise(src, currentPlayer, otherPlayer);
      }
   }

   public PlayerPO createPlayersPO()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Board.PROPERTY_PLAYERS, result);
      
      return result;
   }

   public PlayerPO createPlayersPO(String modifier)
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(modifier);
      super.hasLink(Board.PROPERTY_PLAYERS, result);
      
      return result;
   }

   public BoardPO createPlayersLink(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Board.PROPERTY_PLAYERS);
   }

   public BoardPO createPlayersLink(PlayerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Board.PROPERTY_PLAYERS, modifier);
   }

   public PlayerSet getPlayers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Board) this.getCurrentMatch()).getPlayers();
      }
      return null;
   }

}
