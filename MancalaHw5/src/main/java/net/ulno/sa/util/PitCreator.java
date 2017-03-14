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

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import net.ulno.sa.Pit;
import de.uniks.networkparser.IdMap;
import net.ulno.sa.Player;

public class PitCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Pit.PROPERTY_PEBBLESIN,
      Pit.PROPERTY_ISKALAH,
      Pit.PROPERTY_PLAYERIBELONGTO,
      Pit.PROPERTY_PITSIHAVE,
      Pit.PROPERTY_OPPOSITEOF,
      Pit.PROPERTY_SUCCESSOR,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Pit();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (Pit.PROPERTY_PEBBLESIN.equalsIgnoreCase(attribute))
      {
         return ((Pit) target).getPebblesIn();
      }

      if (Pit.PROPERTY_ISKALAH.equalsIgnoreCase(attribute))
      {
         return ((Pit) target).isIsKalah();
      }

      if (Pit.PROPERTY_PLAYERIBELONGTO.equalsIgnoreCase(attribute))
      {
         return ((Pit) target).getPlayerIBelongTo();
      }

      if (Pit.PROPERTY_PITSIHAVE.equalsIgnoreCase(attribute))
      {
         return ((Pit) target).getPitsIHave();
      }

      if (Pit.PROPERTY_OPPOSITEOF.equalsIgnoreCase(attribute))
      {
         return ((Pit) target).getOppositeOf();
      }

      if (Pit.PROPERTY_SUCCESSOR.equalsIgnoreCase(attribute))
      {
         return ((Pit) target).getSuccessor();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Pit.PROPERTY_ISKALAH.equalsIgnoreCase(attrName))
      {
         ((Pit) target).setIsKalah((Boolean) value);
         return true;
      }

      if (Pit.PROPERTY_PEBBLESIN.equalsIgnoreCase(attrName))
      {
         ((Pit) target).setPebblesIn(Integer.parseInt(value.toString()));
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Pit.PROPERTY_PLAYERIBELONGTO.equalsIgnoreCase(attrName))
      {
         ((Pit) target).withPlayerIBelongTo((Player) value);
         return true;
      }
      
      if ((Pit.PROPERTY_PLAYERIBELONGTO + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Pit) target).withoutPlayerIBelongTo((Player) value);
         return true;
      }

      if (Pit.PROPERTY_PITSIHAVE.equalsIgnoreCase(attrName))
      {
         ((Pit) target).withPitsIHave((Player) value);
         return true;
      }
      
      if ((Pit.PROPERTY_PITSIHAVE + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Pit) target).withoutPitsIHave((Player) value);
         return true;
      }

      if (Pit.PROPERTY_OPPOSITEOF.equalsIgnoreCase(attrName))
      {
         ((Pit) target).withOppositeOf((Pit) value);
         return true;
      }
      
      if ((Pit.PROPERTY_OPPOSITEOF + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Pit) target).withoutOppositeOf((Pit) value);
         return true;
      }

      if (Pit.PROPERTY_SUCCESSOR.equalsIgnoreCase(attrName))
      {
         ((Pit) target).setSuccessor((Pit) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return net.ulno.sa.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Pit) entity).removeYou();
   }
}