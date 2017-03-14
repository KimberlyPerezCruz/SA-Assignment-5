package MancalaGame.util;

import org.sdmlib.models.pattern.PatternObject;
import MancalaGame.Pit;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import MancalaGame.Player;

public class PitPO extends PatternObject<PitPO, Pit>
{

    public PitSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PitSet matches = new PitSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Pit) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PitPO(){
      newInstance(null);
   }

   public PitPO(Pit... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PitPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PitPO createPebblesCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Pit.PROPERTY_PEBBLES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PitPO createPebblesCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Pit.PROPERTY_PEBBLES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PitPO createPebblesAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Pit.PROPERTY_PEBBLES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getPebbles()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pit) getCurrentMatch()).getPebbles();
      }
      return 0;
   }
   
   public PitPO withPebbles(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pit) getCurrentMatch()).setPebbles(value);
      }
      return this;
   }
   
   public PitPO createIsKalahCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Pit.PROPERTY_ISKALAH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PitPO createIsKalahAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Pit.PROPERTY_ISKALAH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getIsKalah()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pit) getCurrentMatch()).isIsKalah();
      }
      return false;
   }
   
   public PitPO withIsKalah(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pit) getCurrentMatch()).setIsKalah(value);
      }
      return this;
   }
   
   public PlayerPO createPlayerPO()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pit.PROPERTY_PLAYER, result);
      
      return result;
   }

   public PlayerPO createPlayerPO(String modifier)
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(modifier);
      super.hasLink(Pit.PROPERTY_PLAYER, result);
      
      return result;
   }

   public PitPO createPlayerLink(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_PLAYER);
   }

   public PitPO createPlayerLink(PlayerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_PLAYER, modifier);
   }

   public Player getPlayer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pit) this.getCurrentMatch()).getPlayer();
      }
      return null;
   }

}
