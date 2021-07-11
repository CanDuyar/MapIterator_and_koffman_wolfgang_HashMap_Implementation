/** Hashing using open addressing with quadratic probing.
 *  @author Can Duyar - 171044075
 */

/**OpenAddressing class that implements KWHashMap iterface*/
public class OpenAddressing < K, V > implements KWHashMap < K, V > {
  /**to keep hashEntries*/
  private HashEntry < K, V > [] hashOpen;
  /**creating a hash entry to define removed element*/
  private final HashEntry < K, V > defineRemoved = new HashEntry < K, V > (null, null);
  /**it keeps initial capacity value*/
  private static final int initialCapacity = 101;
   /**it keeps value for the quadratic probing operation*/
  private int findQuadratic = 1;
  /**it keeps number of removed elements for loadFactor operations*/
  private int countRemoved;
  /**it keeps load factor value*/
  private double loadFactor = 0.75;
  /**it keeps the size*/
  private int size;
  /**it returns the last index*/
  private int lastIndexOfKey;
  

  /**HashEntry class that keeps next-value-key information of the Entry*/
  private static class HashEntry < K, V > {

    /**next of hash table*/
    private int next;
    /**value*/
    private V value;
    /**key*/
    private K key;

    /**
    * Two-parameters constructor for the HashEntry class
    *@param key key
    *@param value value
    */
    public HashEntry(K key, V value) {
      this.next = -1;
      this.value = value;
      this.key = key;
    }

    /**
    * getter for keys
    *@return key 
    */
    public K getKey() {
      return key;
    }

    /**
    * getter for next
    *@return next 
    */
    public int getNext() {
      return next; 
   }

   
    /**
    * setter for next
    *@param next 
    */    
    public void setNext(int next) {
     this.next = next; 
   }


    /**
    * getter for values
    *@return value
    */
    public V getValue() {
      return value;
    }



    /**setter for the value
    *@param val value
    *@return it returns the old value
    */
    public V setValue(V val) {
      V temp = value;
      value = val;
      return temp;
    }
  } // End of the HashEntry class


  /**Zero parameter constructor for OpenAddressing class*/
  public OpenAddressing() {
    hashOpen = new HashEntry[initialCapacity];
  }


  /**to get the size value
  * @return size
  */
  public int size() {
    return size;
  }

  /**
  * This method checks if hashOpen is full or not
  *@return if hashOpen is empty then it returns true otherwise returns false
  */
  public boolean isEmpty() {
    if(size == 0)
      return true;
    else
      return false;
  }


   /**
     * it finds a index value
     * @param key key
     * @return a specific index value
     */
   public int getIndex(K key){
      int indexVal = key.hashCode() % size;
      if(indexVal < 0){
       indexVal = indexVal + size;
      }
      return indexVal;
    }

  /**
  * This method finds the specific index of a key
  *@param key key
  *@return it returns the index of a specified key if it's in the table otherwise returns the 
  *available slot according to quadratic probing 
  */
   private int find(Object key) {
    findQuadratic = 1; //quadratic probing value

    int indexVal = key.hashCode() % hashOpen.length;
    int init = indexVal;
    if (indexVal < 0)
      indexVal += hashOpen.length; 

    while ( (hashOpen[indexVal] != null)
           && (!key.equals(hashOpen[indexVal].key))) {
      
      if(indexVal == getIndex(hashOpen[init].key)){
        lastIndexOfKey = indexVal; 
      }

      indexVal = indexVal + (findQuadratic*findQuadratic);   //QUADRATIC PROBING
      findQuadratic++; //increasing the value for quadratic probing operation

      if (indexVal >= hashOpen.length)
        indexVal = 0; 
      }

    return indexVal;
  }


  /**
  * Getter fot the last index key
  *@return it returns the last index of the key
  */
  public int getLastIndexOfKey(){
      return lastIndexOfKey;
    }


  /**
  * Getter fot the last index key
  *@param key key
  *@return it returns the value of the key which is given as parameter
  */
  public V get(Object key) {

    int indexVal = find(key);

    if (hashOpen[indexVal] == null)
      return null; 
    else
      return hashOpen[indexVal].value;
  }

  /**
  * This method adds the new key-value pair to hash table
  *@param key key
  *@param value value
  *@return value which is added
  */
  public V put(K key, V value) {
    lastIndexOfKey = -1;
    int indexVal = find(key);
    
    if(hashOpen[indexVal] != null){
      V tempVal= hashOpen[indexVal].value;
      hashOpen[indexVal].value = value;
      return tempVal;
    }

    else{
      hashOpen[indexVal] = new HashEntry < K, V > (key, value);
    
      if(lastIndexOfKey != -1){     
        hashOpen[lastIndexOfKey].setNext(indexVal);
    }
      size++;
      double loadFactorVal = (double) (size + countRemoved) / hashOpen.length;
      if (loadFactorVal > loadFactor)
        rehash();
      return null;
    }
  }

  /**rehashing means hashing again, and hashEntry is reallocated */
  private void rehash() {
    
    HashEntry < K, V > [] oldHashOpen = hashOpen;
    int tempSize = 2 * oldHashOpen.length + 1;
    hashOpen = new HashEntry[tempSize];
    size = 0;
    countRemoved = 0;
    for (int t = 0; t < oldHashOpen.length; t++) {
      if ( (oldHashOpen[t] != null) && (oldHashOpen[t] != defineRemoved)) {
        put(oldHashOpen[t].key, oldHashOpen[t].value);
      }
    }
  }


  /**
  * This method removes the key-value according to given key parameter
  *@param key key
  *@return value which is removed
  */
  public V remove(Object key) {
 
    int indexVal = find(key);
    
    if (hashOpen[indexVal] == null)
      return null;
    V tempVal = hashOpen[indexVal].value;
    hashOpen[indexVal] = defineRemoved;
    size--;
    countRemoved++;
    return tempVal;
  }
}
