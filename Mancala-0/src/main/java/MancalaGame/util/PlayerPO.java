package MancalaGame.util;

import org.sdmlib.models.pattern.PatternObject;
import MancalaGame.Player;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import MancalaGame.Pit;

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
   
   public PlayerPO createPebbles_holdingCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_PEBBLES_HOLDING)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createPebbles_holdingCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_PEBBLES_HOLDING)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createPebbles_holdingAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_PEBBLES_HOLDING)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getPebbles_holding()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getPebbles_holding();
      }
      return 0;
   }
   
   public PlayerPO withPebbles_holding(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setPebbles_holding(value);
      }
      return this;
   }
   
   public PitPO createPiotPO()
   {
      PitPO result = new PitPO(new Pit[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_PIOT, result);
      
      return result;
   }

   public PitPO createPiotPO(String modifier)
   {
      PitPO result = new PitPO(new Pit[]{});
      
      result.setModifier(modifier);
      super.hasLink(Player.PROPERTY_PIOT, result);
      
      return result;
   }

   public PlayerPO createPiotLink(PitPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_PIOT);
   }

   public PlayerPO createPiotLink(PitPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_PIOT, modifier);
   }

   public PitSet getPiot()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getPiot();
      }
      return null;
   }

}
