package model.cycling.iterators;

import controller.commands.CommandType;
import model.cycling.type.Type;
import utilities.id.IdType;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Jonathen on 3/11/2017.
 */
public abstract class TypeIterator {

   private Map<IdType, Type> types = new TreeMap<>();

   private Type currentType;

   /**
    * Precondition: keys and values MUST have the same length, and key[i] will be paired with values[i]
    * @param keys
    * @param values
    */
   protected TypeIterator(IdType[] keys, Type[] values) {
      for (int i = 0; i < keys.length; i++) {
         types.put(keys[i], values[i]);
      }
   }

   public void cycleType() {

   }

   public Type getCurrentType() {
      return currentType;
   }

   public CommandType getCurrentCommand() {
      return currentType.getCurrentCommand();
   }
}
