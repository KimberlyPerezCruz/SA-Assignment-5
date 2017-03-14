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
import net.ulno.sa.Player;
import de.uniks.networkparser.IdMap;
import net.ulno.sa.Pit;
import net.ulno.sa.Board;

public class PlayerCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Player.PROPERTY_NAME,
      Player.PROPERTY_PEBBLESHOLDING,
      Player.PROPERTY_PITSIHAVE,
      Player.PROPERTY_PLAYERIBELONGTO,
      Player.PROPERTY_PLAYER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Player();
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

      if (Player.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getName();
      }

      if (Player.PROPERTY_PEBBLESHOLDING.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getPebblesHolding();
      }

      if (Player.PROPERTY_PITSIHAVE.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getPitsIHave();
      }

      if (Player.PROPERTY_PLAYERIBELONGTO.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getPlayerIBelongTo();
      }

      if (Player.PROPERTY_PLAYER.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getPlayer();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Player.PROPERTY_PEBBLESHOLDING.equalsIgnoreCase(attrName))
      {
         ((Player) target).setPebblesHolding(Integer.parseInt(value.toString()));
         return true;
      }

      if (Player.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Player) target).setName((String) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Player.PROPERTY_PITSIHAVE.equalsIgnoreCase(attrName))
      {
         ((Player) target).withPitsIHave((Pit) value);
         return true;
      }
      
      if ((Player.PROPERTY_PITSIHAVE + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Player) target).withoutPitsIHave((Pit) value);
         return true;
      }

      if (Player.PROPERTY_PLAYERIBELONGTO.equalsIgnoreCase(attrName))
      {
         ((Player) target).withPlayerIBelongTo((Pit) value);
         return true;
      }
      
      if ((Player.PROPERTY_PLAYERIBELONGTO + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Player) target).withoutPlayerIBelongTo((Pit) value);
         return true;
      }

      if (Player.PROPERTY_PLAYER.equalsIgnoreCase(attrName))
      {
         ((Player) target).withPlayer((Board) value);
         return true;
      }
      
      if ((Player.PROPERTY_PLAYER + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Player) target).withoutPlayer((Board) value);
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
      ((Player) entity).removeYou();
   }
}