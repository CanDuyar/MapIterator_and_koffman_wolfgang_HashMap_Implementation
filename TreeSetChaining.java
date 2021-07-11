import java.util.Iterator;
import java.util.TreeSet;


/** PART-2.2 Use the chaining technique for hashing by using TreeSet (instead of TreeSet) to chain
items on the same table slot.
*@author Can Duyar - 171044075
*/

/**TreeSetChaining class that extends Comparable class and implements the KwHashMap interface */
public class TreeSetChaining<K extends Comparable<K>,V extends Comparable<V>>  implements KWHashMap<K, V> {

    /**array to keep HashEntries as TreeSet*/
    private TreeSet<HashEntry<K, V>>[] hashTreeSet;
    /**it keeps total number of keys*/
    public int total_keys = 0;
    /**the initial total size of hashTreeSet*/
    public int total_size = 11;
    /**maximum load factor*/
    public double maxLoadFactor = 5.0;
    /**it keeps number of collisions*/
    public int numberOfCollisions = 0;

    /**HashEntry class that extends and implements the Comparable class*/
    private static class HashEntry<K extends Comparable<K>,V extends Comparable<V>> implements Comparable<HashEntry<K,V>> {
        /**value*/
        private V value;
        /**key*/
        private final K key;

        /**
        * Two-parameters constructor for HashEntry to initialize value and key
        * @param key
        * @param value
        */
        public HashEntry(K key, V value) {
            this.value = value;
            this.key = key;
        }

        /**getter for values
        * @return value
        */
        public V getValue() {
            return value;
        }
        
        /**getter for keys
        * @return key
        */
        public K getKey() {
            return key;
        }


        /**setter for values
        * @param val value
        */
        public V setvalue(V val) {
            V temp = value;
            value = val;
            return temp;
        }

        /**
        *compareTo method to compare key and HashEntry's key which is given as parameter.
        *@param param a HashEntry object
        *@return value of compareTo
        */
        @Override
        public int compareTo(HashEntry<K, V> param) {
            HashEntry<K,V> object = (HashEntry<K, V>) param;
            return key.compareTo(object.getKey());
        }

        /**
         * compares two object key
         * @param param other object that needs to be compared
         * @return if objects are equal then it returns true otherwise returns false
         */
        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object param) {
            HashEntry<K,V> object = (HashEntry<K, V>) param;
            return (key.compareTo(object.getKey()) == 0); 
        }

    } // End of the HasEntry class

    /**zero parameter constructor for TreeSetChaining class*/
    public TreeSetChaining() {
        this.hashTreeSet = new TreeSet[total_size];
    }


    /**
    *Two-parameters constructor for TreeSetChaining class
    * @param total_size total size value for hash table
    * @param loadFact load factor value which is given as parameter
    */
    public TreeSetChaining(int total_size, double loadFact) {
        this.hashTreeSet = new TreeSet[total_size];
        this.total_size = total_size;
        this.maxLoadFactor = loadFact;
    }

    /**
    * it gives the specific index for the hasTreeSet
    * @param key address of a specific value
    * @param sizeParam total length
    * @return index value
    */    
    public int hashing(Object key, int sizeParam) {
        int indexVal = key.hashCode() % sizeParam;
        if (indexVal < 0) {
            indexVal += sizeParam;
        }
            return indexVal;
    }



    /**rehashing means hashing again, total size is increased and also number of collisions set to zero */
    public void rehashing() {
        numberOfCollisions = 0; // number of collisions set to zero!!!
        total_keys = 0;
        TreeSet<HashEntry<K, V>>[] oldhashTreeSet;
        oldhashTreeSet = hashTreeSet;
        total_size = (total_size * 2) + 1; // total size is increased
        hashTreeSet = new TreeSet[total_size];
        for(int i = 0; i < oldhashTreeSet.length; i++) {
            TreeSet<HashEntry<K,V>> keep = oldhashTreeSet[i];
            if (keep != null) {
                Iterator<HashEntry<K, V>> iter = keep.iterator();
                while(iter.hasNext()){
                    HashEntry<K, V> forward = iter.next();
                    put(forward.getKey(), forward.getValue());
            }
            }
        }
    }

    /**
    * This method gets the value of a specific key
    * @param key key object
    * @return it returns the value of a specific key which is given as parameter
    */
    @Override
    public V get(Object key) {
        int indexVal = hashing(key, hashTreeSet.length);
        TreeSet<HashEntry<K,V>> temp = hashTreeSet[indexVal];
        if (temp == null) {
            return null;
        }
            
        Iterator<HashEntry<K,V>> iter = temp.iterator();
        HashEntry<K,V> keep;
        
        while(iter.hasNext()) {
            keep = iter.next();
            if (keep.getKey().equals(key)) {
                return (V) keep.getValue();
            }
        }
        return null;
    }


    /**
    * This method adds the new key-value pair to hash table
    *@param key key
    *@param value value
    *@return value which is added
    */
    
    @Override
    public V put(K key, V value) {
        int indexVal = hashing(key, hashTreeSet.length);
        TreeSet<HashEntry<K,V>> temp = hashTreeSet[indexVal];
        V val = null;

        if(temp != null){
            numberOfCollisions += 1;
             Iterator<HashEntry<K,V>> iter = temp.iterator();
             HashEntry<K,V> keep;
            
             while(iter.hasNext()){
                keep = iter.next();   
                if (keep.equals(new HashEntry<>(key, value))) {
                    val = (V) keep.setvalue(value);
                    return val;
                }
            }
            hashTreeSet[indexVal].add(new HashEntry<>(key, value));
            
            total_keys += 1;
            
            if ((total_keys) > (maxLoadFactor * hashTreeSet.length)) {
                rehashing();
            }
            return value;
        }        

        else{
            TreeSet ts = new TreeSet();
            ts.add(new HashEntry(key, value));
            hashTreeSet[indexVal] = ts;
            total_keys++;

            if ((total_keys) > (maxLoadFactor * total_size)) {
                rehashing();
            }
            return (V) val;
        } 
    }


    /**
    * This method removes the key-value according to given key parameter
    *@param key key
    *@return value which is removed
    */
    public V remove(Object key){
        if(key != null){        
           int indexVal = hashing(key,hashTreeSet.length);
           TreeSet<HashEntry<K,V>> temp = hashTreeSet[indexVal];
           if(temp!=null){
                Iterator<HashEntry<K,V>> it = temp.iterator();
                HashEntry<K,V> keep;
                for(int i=0 ; i<temp.size() ; i++){
                    keep = it.next();
                    if(keep.getKey().equals(key)){
                        V returnVal = keep.value;
                        total_keys--;
                        temp.remove(new HashEntry<>(keep.key,keep.value));
                        return returnVal;
                    }
                }
            }
            else{
                return null;
            }
            return null;

         }
         else{
            throw new NullPointerException("We couldn't remove because key is null");
         }
    }



   /** This method prints the number of collisions*/
    public void getNumberOfCollisions() {
        System.out.println("collision number is " + numberOfCollisions);
    }


    /**
    * This method checks if hashTreeSet is full or not
    *@return if hashTreeSet is empty then it returns true otherwise returns false
    */
    public boolean isEmpty() {
        return (size() < 1);
    }


    /** this method returns the size of hashTreeSet
    * @return total number of keys
    */
    @Override
    public int size() {
        return total_keys;
    }

}