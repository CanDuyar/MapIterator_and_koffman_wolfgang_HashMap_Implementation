import java.util.Iterator;
import java.util.LinkedList;

/** PART-2.1
 * Use the chaining technique for hashing by using linked lists to chain 
 * items on the same table slot.
 * @author Can Duyar - 171044075
 */

/**ChainingClass that implements the KWHashMap interface*/
public class LinkedListChaining<K, V> implements KWHashMap<K, V> {

    /**array to keep HashEntries as Linkedlist*/
    private LinkedList<HashEntry<K, V>>[] hashLinked;
    /**it keeps total number of keys*/
    public int total_keys = 0;
    /**the initial total size of hashLinked*/
    public int total_size = 11;
     /**maximum load factor*/
    public double maxLoadFactor = 5.0;
    /**it keeps number of collisions*/
    public int numberOfCollisions = 0;
    

    /**HashEntry class to keep value,key and their operations*/
    private static class HashEntry<K, V> {
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
    } //End of the HashEntry class

    /**Zero parameter constructor for LinkedListChaining class*/
    public LinkedListChaining() {
        this.hashLinked = new LinkedList[total_size];
    }

    /**
    *Two-parameters constructor for LinkedListChaining class
    * @param total_size total size value for hash table
    * @param loadFact load factor value which is given as parameter
    */
    public LinkedListChaining(int total_size, double loadFact) {
        this.hashLinked = new LinkedList[total_size];
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
        LinkedList<HashEntry<K, V>>[] oldhashLinked;
        total_size = (total_size * 2) + 1; // total size is increased
        oldhashLinked = hashLinked;
        hashLinked = new LinkedList[total_size];
        HashEntry<K, V> t;
        for(int i = 0; i < oldhashLinked.length; i++) {
            if (oldhashLinked[i] != null) {
                for(int j = 0; j < oldhashLinked[i].size(); j++) {
                t = oldhashLinked[i].get(j);
                put(t.getKey(), t.getValue());
            }
            }
        }
    }


    /**
    * This method gets the value of a specific key
    * @param key key object
    * @return it returns the value of a specific key which is given as parameter
    */
    @SuppressWarnings("unchecked")
    @Override
    public V get(Object key) {
        int indexVal = hashing(key,hashLinked.length);
        if (hashLinked[indexVal] == null) {
            return null;
        }
        HashEntry t;
        for(int u = 0; u < hashLinked[indexVal].size(); u++) {
            t = hashLinked[indexVal].get(u);
            if (t.getKey().equals(key)) {
                return (V) t.getValue();
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
    @SuppressWarnings("unchecked")
    @Override
    public V put(K key, V value) {
        int indexVal = hashing(key, hashLinked.length);
        V val = null;
 
        if(hashLinked[indexVal] != null){
            numberOfCollisions += 1; /* number of collision is increased when
             we try to add element that has same index with elements in the table*/
            for(int i = 0; i < hashLinked[indexVal].size(); i++) {
                HashEntry t = hashLinked[indexVal].get(i);
                if (t.getKey().equals(key)) {
                    val = (V) t.setvalue(value);
                    return val;
                }
            }
            hashLinked[indexVal].add(new HashEntry<>(key, value));
            total_keys += 1;
            
            if ((total_keys) > (maxLoadFactor * hashLinked.length)) {
                rehashing();
            }
            return value;
        }

        else{
            LinkedList ll = new LinkedList();
            ll.add(new HashEntry(key, value));
            hashLinked[indexVal] = ll;
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
    @Override
    public V remove(Object key){
        if(key != null){
            int indexVal = hashing(key, hashLinked.length);
            LinkedList<HashEntry<K,V>> temp = hashLinked[indexVal];
            
            if(temp!=null){
                for(int t=0 ; t<temp.size() ; t++){
                    if(temp.get(t).key.equals(key)){
                        V keep = temp.get(t).value;
                        temp.remove(t);
                        numberOfCollisions--;
                        total_size--;
                        return keep;
                    }
                }
                return null;
            }
            else{
                return null;  // if temp is null then returns false
            }
        }
        else{
            throw new NullPointerException("We couldn't remove because key is null");
        }
    }


    /** This method prints the number of collisions*/
    public void getNumberOfCollisions() {
        System.out.println("Number of collisions is " + numberOfCollisions);
    }


    /**
    * This method checks if hashLinked is full or not
    *@return if hashLinked is empty then it returns true otherwise returns false
    */
    public boolean isEmpty() {
        return (size() < 1);
    }

   /** this method returns the size of hashLinked
    * @return total number of keys
    */
    @Override
    public int size() {
        int counter = 0;
        Iterator<HashEntry<K, V>> iter;
        HashEntry<K, V> temp;
        for (LinkedList<HashEntry<K, V>> ll : this.hashLinked) {
            if (ll != null) {
                iter = ll.listIterator(0);
                while (iter.hasNext()) {
                    temp = iter.next();
                    if (temp.key != null) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }
}
