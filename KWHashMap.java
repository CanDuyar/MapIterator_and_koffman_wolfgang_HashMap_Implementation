
/** KWHashMap Interface from the textbook
 *  @author Can Duyar - 171044075
 */

public interface KWHashMap < K, V > {

  V get(Object key);

  V put(K key, V value);

  V remove(Object key);

  int size();

  boolean isEmpty();

}