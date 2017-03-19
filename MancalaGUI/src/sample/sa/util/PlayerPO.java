package sa.util;

import sa.Board;
import sa.Pit;
import sa.Player;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;

public class PlayerPO extends PatternObject<PlayerPO, Player>
{

    public PlayerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PlayerSet matches = new PlayerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Player) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PlayerPO(){
      newInstance(null);
   }

   public PlayerPO(Player... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PlayerPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PlayerPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PlayerPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public PlayerPO createPebblesHoldingCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_PEBBLESHOLDING)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createPebblesHoldingCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_PEBBLESHOLDING)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createPebblesHoldingAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_PEBBLESHOLDING)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getPebblesHolding()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getPebblesHolding();
      }
      return 0;
   }
   
   public PlayerPO withPebblesHolding(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setPebblesHolding(value);
      }
      return this;
   }
   
   public PitPO createPitsIHavePO()
   {
      PitPO result = new PitPO(new Pit[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_PITSIHAVE, result);
      
      return result;
   }

   public PitPO createPitsIHavePO(String modifier)
   {
      PitPO result = new PitPO(new Pit[]{});
      
      result.setModifier(modifier);
      super.hasLink(Player.PROPERTY_PITSIHAVE, result);
      
      return result;
   }

   public PlayerPO createPitsIHaveLink(PitPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_PITSIHAVE);
   }

   public PlayerPO createPitsIHaveLink(PitPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_PITSIHAVE, modifier);
   }

   public PitSet getPitsIHave()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getPitsIHave();
      }
      return null;
   }

   public BoardPO createBoardPO()
   {
      BoardPO result = new BoardPO(new Board[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_BOARD, result);
      
      return result;
   }

   public BoardPO createBoardPO(String modifier)
   {
      BoardPO result = new BoardPO(new Board[]{});
      
      result.setModifier(modifier);
      super.hasLink(Player.PROPERTY_BOARD, result);
      
      return result;
   }

   public PlayerPO createBoardLink(BoardPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_BOARD);
   }

   public PlayerPO createBoardLink(BoardPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_BOARD, modifier);
   }

   public Board getBoard()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getBoard();
      }
      return null;
   }

}
