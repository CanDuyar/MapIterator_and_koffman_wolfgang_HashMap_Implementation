import java.util.HashMap;
import java.util.Iterator;

/** Implementation of the MapIterator 
 *  @author Can Duyar - 171044075
 */

//Generic MapIterator class that extends the HashMap class
public class MapIterator<K, V> extends HashMap<K,V>{

//Generic IteratorMap class that implements Iterator class
    public class IteratorMap<K,V> implements Iterator<K> {
        /** it keeps position of our iterator*/
        int iterPosition; 
        /** This array keeps keys*/
        Object[] mapArray; 
        /** It keeps size value of the map*/
        int sizeOfMap; 

       /** One parameter constructor of the IteratorMap class.The provide that the iterator
       * should start from the given key and still iterate though all the keys in the Map
       * @param key key value
       */
        public IteratorMap(K key){
            mapArray = MapIterator.this.keySet().toArray();
            sizeOfMap = MapIterator.this.size();
            for(int t=0 ; t<sizeOfMap ; t++){ 
                if(mapArray[t].equals(key)){
                    iterPosition = t;
                }
            }
        }
       
       /**zero parameter constructor - It starts from the beginning*/
        public IteratorMap(){
            mapArray = MapIterator.this.keySet().toArray();
            sizeOfMap = MapIterator.this.size();
            int iterPosition = 0;
        }


        /** getter for sizeOfMap
        *@return getSizeOfMap
        */
        public int getSizeOfMap(){
            return sizeOfMap;
        }


        /** getter for iterPosition
        *@return iterPosition
        */
        public int getIterPosition(){
            return iterPosition;
        }


        /** getter for mapArray
        *@return mapArray
        */
        public Object[] getMapArray(){
            return mapArray;
        }


        /**
         * This method checks the next is available or not in the map
         * @return returns true if next is available otherwise returns false
         */
        @Override
        public boolean hasNext() {
            return iterPosition != sizeOfMap;
        }


        /**
         *@return it iterates for previous and returns the data
         */
        public K prev(){
            
            if(iterPosition == 0){   /*if we try to find the prev() at the 0. index 
                then we iterate turn back the last index*/
                iterPosition = sizeOfMap-1;  
                return (K) mapArray[sizeOfMap-1]; 
            }
            else if(iterPosition != 0){
                return (K) mapArray[--iterPosition];
            }

            return null;

        }

        /**
         * @return it iterates for next and returns the data
         */
        @Override
        public K next() {
            if(mapArray.length == 1)
                return (K) mapArray[iterPosition];

            else if(hasNext()){
                return (K) mapArray[iterPosition++];
            }

            iterPosition = 0; /*if iterator's hasNext() value 
            is equal to false then it turns back to the beginning*/
            return (K) mapArray[iterPosition++];
        }

    }

     /**
     * we can reach our IteratorMap class's methods with using this method
     * @param key
     * @return iteratorMap-iterator that starts from the given index
     */
    public IteratorMap<K,V> MapIterator(K key){
        return new IteratorMap(key);
    }

    /**
     * we can reach our IteratorMap class's methods with using this method
     * @return iteratorMap-iterator
     */
    public IteratorMap<K,V> MapIterator(){
        return new IteratorMap();
    }
}