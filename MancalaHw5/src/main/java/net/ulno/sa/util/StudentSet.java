/*
   Copyright (c) 2017 Salonika
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package net.ulno.sa.util;

import de.uniks.networkparser.list.SimpleSet;
import net.ulno.sa.Student;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import net.ulno.sa.util.UniversitySet;
import net.ulno.sa.University;

public class StudentSet extends SimpleSet<Student>
{
	protected Class<?> getTypClass() {
		return Student.class;
	}

   public StudentSet()
   {
      // empty
   }

   public StudentSet(Student... objects)
   {
      for (Student obj : objects)
      {
         this.add(obj);
      }
   }

   public StudentSet(Collection<Student> objects)
   {
      this.addAll(objects);
   }

   public static final StudentSet EMPTY_SET = new StudentSet().withFlag(StudentSet.READONLY);


   public StudentPO createStudentPO()
   {
      return new StudentPO(this.toArray(new Student[this.size()]));
   }


   public String getEntryType()
   {
      return "net.ulno.sa.Student";
   }


   @Override
   public StudentSet getNewList(boolean keyValue)
   {
      return new StudentSet();
   }


   public StudentSet filter(Condition<Student> condition) {
      StudentSet filterList = new StudentSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public StudentSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Student>)value);
      }
      else if (value != null)
      {
         this.add((Student) value);
      }
      
      return this;
   }
   
   public StudentSet without(Student value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Student objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (Student obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet filterName(String value)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet filterName(String lower, String upper)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Student objects now with new attribute values.
    */
   public StudentSet withName(String value)
   {
      for (Student obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Student objects and collect a list of the studentID attribute values. 
    * 
    * @return List of String objects reachable via studentID attribute
    */
   public ObjectSet getStudentID()
   {
      ObjectSet result = new ObjectSet();
      
      for (Student obj : this)
      {
         result.add(obj.getStudentID());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the studentID attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet filterStudentID(String value)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (value.equals(obj.getStudentID()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the studentID attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet filterStudentID(String lower, String upper)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (lower.compareTo(obj.getStudentID()) <= 0 && obj.getStudentID().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and assign value to the studentID attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Student objects now with new attribute values.
    */
   public StudentSet withStudentID(String value)
   {
      for (Student obj : this)
      {
         obj.setStudentID(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Student objects and collect a set of the University objects reached via almaMater. 
    * 
    * @return Set of University objects reachable via almaMater
    */
   public UniversitySet getAlmaMater()
   {
      UniversitySet result = new UniversitySet();
      
      for (Student obj : this)
      {
         result.with(obj.getAlmaMater());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Student objects and collect all contained objects with reference almaMater pointing to the object passed as parameter. 
    * 
    * @param value The object required as almaMater neighbor of the collected results. 
    * 
    * @return Set of University objects referring to value via almaMater
    */
   public StudentSet filterAlmaMater(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      StudentSet answer = new StudentSet();
      
      for (Student obj : this)
      {
         if (neighbors.contains(obj.getAlmaMater()) || (neighbors.isEmpty() && obj.getAlmaMater() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Student object passed as parameter to the AlmaMater attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their AlmaMater attributes.
    */
   public StudentSet withAlmaMater(University value)
   {
      for (Student obj : this)
      {
         obj.withAlmaMater(value);
      }
      
      return this;
   }

}
