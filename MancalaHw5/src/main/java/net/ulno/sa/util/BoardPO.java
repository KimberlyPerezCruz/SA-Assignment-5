package net.ulno.sa.util;

import org.sdmlib.models.pattern.PatternObject;
import net.ulno.sa.Board;
import net.ulno.sa.util.PlayerPO;
import net.ulno.sa.Player;
import net.ulno.sa.util.BoardPO;
import net.ulno.sa.Pit;

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
   public PlayerPO createBoardIBelongToPO()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Board.PROPERTY_BOARDIBELONGTO, result);
      
      return result;
   }

   public PlayerPO createBoardIBelongToPO(String modifier)
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(modifier);
      super.hasLink(Board.PROPERTY_BOARDIBELONGTO, result);
      
      return result;
   }

   public BoardPO createBoardIBelongToLink(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Board.PROPERTY_BOARDIBELONGTO);
   }

   public BoardPO createBoardIBelongToLink(PlayerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Board.PROPERTY_BOARDIBELONGTO, modifier);
   }

   public Player getBoardIBelongTo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Board) this.getCurrentMatch()).getBoardIBelongTo();
      }
      return null;
   }

   
   //==========================================================================
   
   public void takeOppositePebbles(Player movingPlayer,Player otherPlayer, int curLocation)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Board) getCurrentMatch()).takeOppositePebbles(movingPlayer, otherPlayer, curLocation);
      }
   }

   
   //==========================================================================
   
   public void ReDistributeCounterclockwise(Pit src,  Player currentPlayer, Player otherPlayer)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Board) getCurrentMatch()).ReDistributeCounterclockwise(src, currentPlayer, otherPlayer);
      }
   }

}
