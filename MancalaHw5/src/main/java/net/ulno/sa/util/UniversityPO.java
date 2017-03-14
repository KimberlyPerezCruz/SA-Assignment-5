package net.ulno.sa.util;

import org.sdmlib.models.pattern.PatternObject;
import net.ulno.sa.University;
import net.ulno.sa.util.StudentPO;
import net.ulno.sa.Student;
import net.ulno.sa.util.UniversityPO;
import net.ulno.sa.util.StudentSet;

public class UniversityPO extends PatternObject<UniversityPO, University>
{

    public UniversitySet allMatches()
   {
      this.setDoAllMatches(true);
      
      UniversitySet matches = new UniversitySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((University) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public UniversityPO(){
      newInstance(null);
   }

   public UniversityPO(University... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public UniversityPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public StudentPO createStudentPO()
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(University.PROPERTY_STUDENT, result);
      
      return result;
   }

   public StudentPO createStudentPO(String modifier)
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(modifier);
      super.hasLink(University.PROPERTY_STUDENT, result);
      
      return result;
   }

   public UniversityPO createStudentLink(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, University.PROPERTY_STUDENT);
   }

   public UniversityPO createStudentLink(StudentPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, University.PROPERTY_STUDENT, modifier);
   }

   public StudentSet getStudent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((University) this.getCurrentMatch()).getStudent();
      }
      return null;
   }

}
