package net.ulno.sa.util;

import org.sdmlib.models.pattern.PatternObject;
import net.ulno.sa.Pit;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import net.ulno.sa.util.PlayerPO;
import net.ulno.sa.Player;
import net.ulno.sa.util.PitPO;
import net.ulno.sa.util.PlayerSet;
import net.ulno.sa.util.PitSet;

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
   public PitPO createPebblesInCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Pit.PROPERTY_PEBBLESIN)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PitPO createPebblesInCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Pit.PROPERTY_PEBBLESIN)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PitPO createPebblesInAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Pit.PROPERTY_PEBBLESIN)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getPebblesIn()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pit) getCurrentMatch()).getPebblesIn();
      }
      return 0;
   }
   
   public PitPO withPebblesIn(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pit) getCurrentMatch()).setPebblesIn(value);
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
   
   public PlayerPO createPlayerIBelongToPO()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pit.PROPERTY_PLAYERIBELONGTO, result);
      
      return result;
   }

   public PlayerPO createPlayerIBelongToPO(String modifier)
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(modifier);
      super.hasLink(Pit.PROPERTY_PLAYERIBELONGTO, result);
      
      return result;
   }

   public PitPO createPlayerIBelongToLink(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_PLAYERIBELONGTO);
   }

   public PitPO createPlayerIBelongToLink(PlayerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_PLAYERIBELONGTO, modifier);
   }

   public PlayerSet getPlayerIBelongTo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pit) this.getCurrentMatch()).getPlayerIBelongTo();
      }
      return null;
   }

   public PlayerPO createPitsIHavePO()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pit.PROPERTY_PITSIHAVE, result);
      
      return result;
   }

   public PlayerPO createPitsIHavePO(String modifier)
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(modifier);
      super.hasLink(Pit.PROPERTY_PITSIHAVE, result);
      
      return result;
   }

   public PitPO createPitsIHaveLink(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_PITSIHAVE);
   }

   public PitPO createPitsIHaveLink(PlayerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_PITSIHAVE, modifier);
   }

   public PlayerSet getPitsIHave()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pit) this.getCurrentMatch()).getPitsIHave();
      }
      return null;
   }

   public PitPO createOppositeOfPO()
   {
      PitPO result = new PitPO(new Pit[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pit.PROPERTY_OPPOSITEOF, result);
      
      return result;
   }

   public PitPO createOppositeOfPO(String modifier)
   {
      PitPO result = new PitPO(new Pit[]{});
      
      result.setModifier(modifier);
      super.hasLink(Pit.PROPERTY_OPPOSITEOF, result);
      
      return result;
   }

   public PitPO createOppositeOfLink(PitPO tgt)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_OPPOSITEOF);
   }

   public PitPO createOppositeOfLink(PitPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_OPPOSITEOF, modifier);
   }

   public PitSet getOppositeOf()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pit) this.getCurrentMatch()).getOppositeOf();
      }
      return null;
   }

   public PitPO createSuccessorPO()
   {
      PitPO result = new PitPO(new Pit[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pit.PROPERTY_SUCCESSOR, result);
      
      return result;
   }

   public PitPO createSuccessorPO(String modifier)
   {
      PitPO result = new PitPO(new Pit[]{});
      
      result.setModifier(modifier);
      super.hasLink(Pit.PROPERTY_SUCCESSOR, result);
      
      return result;
   }

   public PitPO createSuccessorLink(PitPO tgt)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_SUCCESSOR);
   }

   public PitPO createSuccessorLink(PitPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_SUCCESSOR, modifier);
   }

   public Pit getSuccessor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pit) this.getCurrentMatch()).getSuccessor();
      }
      return null;
   }

}
