package net.ulno.sa.util;

import org.sdmlib.models.pattern.PatternObject;
import net.ulno.sa.Student;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import net.ulno.sa.util.UniversityPO;
import net.ulno.sa.University;
import net.ulno.sa.util.StudentPO;

public class StudentPO extends PatternObject<StudentPO, Student>
{

    public StudentSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StudentSet matches = new StudentSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Student) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public StudentPO(){
      newInstance(null);
   }

   public StudentPO(Student... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public StudentPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public StudentPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StudentPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StudentPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_NAME)
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
         return ((Student) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public StudentPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Student) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public StudentPO createStudentIDCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_STUDENTID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StudentPO createStudentIDCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_STUDENTID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StudentPO createStudentIDAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_STUDENTID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getStudentID()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) getCurrentMatch()).getStudentID();
      }
      return null;
   }
   
   public StudentPO withStudentID(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Student) getCurrentMatch()).setStudentID(value);
      }
      return this;
   }
   
   public UniversityPO createAlmaMaterPO()
   {
      UniversityPO result = new UniversityPO(new University[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Student.PROPERTY_ALMAMATER, result);
      
      return result;
   }

   public UniversityPO createAlmaMaterPO(String modifier)
   {
      UniversityPO result = new UniversityPO(new University[]{});
      
      result.setModifier(modifier);
      super.hasLink(Student.PROPERTY_ALMAMATER, result);
      
      return result;
   }

   public StudentPO createAlmaMaterLink(UniversityPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_ALMAMATER);
   }

   public StudentPO createAlmaMaterLink(UniversityPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_ALMAMATER, modifier);
   }

   public University getAlmaMater()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getAlmaMater();
      }
      return null;
   }

}
