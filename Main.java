import java.util.Map;
import java.util.Random;

/**Main class to test all of the methods*/
public class Main{

/**driver method*/
 public static void main(String[] args){

		long start; //start label for to calculate operation time
		long end; //end label for to calculate operation time
		char ch; //character(for iterator's value)
		int in; //integer(for iterator's key)

	 	System.out.println("********************TEST - MapIterator****************************");

        MapIterator<Integer, Character> testIter = new MapIterator<>();
        
            for(ch='a',in = 0; ch<='z' && in < 10 ; ch++,in++){            
                testIter.put(in,ch);
            }

            /*	KEY VALUE
				 0    a 
				 1    b
				 2 	  c
				 3    d
				.
				.
				.

            */
            @SuppressWarnings("rawtypes")
        MapIterator.IteratorMap it = testIter.MapIterator();
     
        System.out.println("TEST of hasNext() and next()");
        while(it.hasNext())
       		System.out.println(it.next());
        	
        System.out.println("TEST of prev()");
        for(int t = 0; t < testIter.size();t++)
        	System.out.println(it.prev());


        System.out.println("TEST of MapIterator (K key)");
        @SuppressWarnings("rawtypes")
     	MapIterator.IteratorMap it2 = testIter.MapIterator(5);
     	System.out.println(it2.prev());
     	System.out.println(it2.prev());


//////////////////////////////////////////////////////////////////////////////////



		/*TEST OF PART-2.1   hashing by using linked lists*/
		System.out.println("********************TEST OF PART-2.1 - Hashing by using linked lists****************************");
        LinkedListChaining<Integer, Integer> ht1_small = new LinkedListChaining<Integer,Integer>();
        LinkedListChaining<Integer, Integer> ht1_medium = new LinkedListChaining<Integer,Integer>();
        LinkedListChaining<Integer, Integer> ht1_large = new LinkedListChaining<Integer,Integer>();
      
      	int u;
      	int t;

      	System.out.println("\n************Testing with small-sized data(10 elements)********");
      	start = System.nanoTime();
      	for(t = 0,u = 0; t < 10 && u > -10; t++,u--)
      		ht1_small.put(t,u);
      	end = System.nanoTime();

      	System.out.println("ht1_small.get(1) for small-sized data : " + ht1_small.get(1));
        System.out.println("ht1_small.get(5) for small-sized data : " + ht1_small.get(5));
      	System.out.println("after ht1_small.put(18,-18)");
      	ht1_small.put(18,-18);
      	ht1_small.getNumberOfCollisions();

        System.out.println("after the ht1_small.remove(2):  " + ht1_small.remove(2));
        System.out.println("ht1_small.get(3):  " + ht1_small.get(3));
        System.out.println("size of ht1_small =  " + ht1_small.size());
        System.out.println("TEST - isEmpty?");
        System.out.println("ht1_small.isEmpty() =  " + ht1_small.isEmpty());
        System.out.println("Performance(according to put() method in small-sized data) =  " + (end-start) + "ns");

      	System.out.println("\n***********Testing with medium-sized data(100 elements)*******");
      	start = System.nanoTime();
      	for(t = 0,u = 0; t < 100 && u > -100; t++,u--)
      		ht1_medium.put(t,u);
      	end = System.nanoTime();

      	System.out.println("ht1_medium.get(23) for medium-sized data : " + ht1_medium.get(23));
        System.out.println("ht1_medium.get(96) for medium-sized data : " + ht1_medium.get(96));

        System.out.println("after the ht1_medium.remove(13):  " + ht1_medium.remove(13));
        System.out.println("after the ht1_medium.remove(41):  " + ht1_medium.remove(41));
        System.out.println("ht1_medium.get(13):  " + ht1_medium.get(13));
        System.out.println("size of ht1_medium =  " + ht1_medium.size());
        System.out.println("TEST - isEmpty?");
        System.out.println("ht1_medium.isEmpty() =  " + ht1_medium.isEmpty());
        System.out.println("Performance(according to put() method in medium-sized data) =  " + (end-start) + "ns");

      	System.out.println("\n***********Testing with large-sized data(1000 elements)*******");
      	start = System.nanoTime();
      	for(t = 0,u = 0; t < 1000 && u > -1000; t++,u--)
      		ht1_large.put(t,u);

      	end = System.nanoTime();

      	System.out.println("ht1_large.get(313) for large-sized data : " + ht1_large.get(313));
        System.out.println("ht1_large.get(912) for large-sized data : " + ht1_large.get(912));

        System.out.println("after the ht1_large.remove(928):  " + ht1_large.remove(928));
        System.out.println("after the ht1_large.remove(22):  " + ht1_large.remove(22));
        System.out.println("ht1_large.get(22):  " + ht1_large.get(22));
        System.out.println("size of ht1_large =  " + ht1_large.size());
        System.out.println("TEST - isEmpty?");
        System.out.println("ht1_large.isEmpty() =  " + ht1_large.isEmpty());


      	System.out.println("Performance(according to put() method in large-sized data) =  " + (end-start) + "ns");

		/*TEST OF PART-2.2   hashing by using TreeSet(instead of linkedlist)*/
		System.out.println("\n\n********************TEST OF PART-2.2 - Hashing by using TreeSet(instead of linkedlist)****************************");
        
  
        TreeSetChaining<Integer, Integer> ht2_small = new TreeSetChaining<Integer, Integer>();
        TreeSetChaining<Integer, Integer> ht2_medium = new TreeSetChaining<Integer, Integer>();
        TreeSetChaining<Integer, Integer> ht2_large = new TreeSetChaining<Integer, Integer>();
  
    	System.out.println("\n************Testing with small-sized data(10 elements)********");
      	start = System.nanoTime();
      	for(t = 0,u = 0; t < 10 && u > -10; t++,u--)
      		ht2_small.put(t,u);
      	end = System.nanoTime();


      	System.out.println("ht2_small.get(1) for small-sized data : " + ht2_small.get(1));
        System.out.println("ht2_small.get(5) for small-sized data : " + ht2_small.get(5));
      	System.out.println("after ht2_small.put(11,-11) and ht2_small.put(22,-22)");
      	ht2_small.put(11,-11);
      	ht2_small.put(22,-22);
      	ht2_small.getNumberOfCollisions();

        System.out.println("after the ht2_small.remove(2):  " + ht2_small.remove(2));
        System.out.println("ht2_small.get(3):  " + ht2_small.get(3));
        System.out.println("size of ht1_small =  " + ht2_small.size());
        System.out.println("TEST - isEmpty?");
        System.out.println("ht2_small.isEmpty() =  " + ht2_small.isEmpty());
        System.out.println("Performance(according to put() method in small-sized data) =  " + (end-start) + "ns");

      	System.out.println("\n***********Testing with medium-sized data(100 elements)*******");
      	start = System.nanoTime();
      	for(t = 0,u = 0; t < 100 && u > -100; t++,u--)
      		ht2_medium.put(t,u);
      	end = System.nanoTime();

      	System.out.println("ht2_medium.get(23) for medium-sized data : " + ht2_medium.get(23));
        System.out.println("ht2_medium.get(96) for medium-sized data : " + ht2_medium.get(96));

        System.out.println("after the ht2_medium.remove(13):  " + ht2_medium.remove(13));
        System.out.println("after the ht2_medium.remove(41):  " + ht2_medium.remove(41));
        System.out.println("ht2_medium.get(13):  " + ht2_medium.get(13));
        System.out.println("size of ht2_medium =  " + ht2_medium.size());
        System.out.println("TEST - isEmpty?");
        System.out.println("ht2_medium.isEmpty() =  " + ht2_medium.isEmpty());
        System.out.println("Performance(according to put() method in medium-sized data) =  " + (end-start) + "ns");

      	System.out.println("\n***********Testing with large-sized data(1000 elements)*******");
      	start = System.nanoTime();
      	for(t = 0,u = 0; t < 1000 && u > -1000; t++,u--)
      		ht2_large.put(t,u);

      	end = System.nanoTime();

      	System.out.println("ht2_large.get(313) for large-sized data : " + ht2_large.get(313));
        System.out.println("ht2_large.get(912) for large-sized data : " + ht2_large.get(912));

        System.out.println("after the ht2_large.remove(928):  " + ht2_large.remove(928));
        System.out.println("after the ht2_large.remove(22):  " + ht2_large.remove(22));
        System.out.println("ht2_large.get(22):  " + ht2_large.get(22));
        System.out.println("size of ht2_large =  " + ht2_large.size());
        System.out.println("TEST - isEmpty?");
        System.out.println("ht2_large.isEmpty() =  " + ht2_large.isEmpty());

      	System.out.println("Performance(according to put() method in large-sized data) =  " + (end-start) + "ns");


        /*TEST OF PART-2.3  Open Adressing*/

        System.out.println("\n\n********************TEST OF PART-2.3 - Open Addressing****************************");
      
  
        OpenAddressing<Integer, Integer> ht3_small = new OpenAddressing<Integer, Integer>();
        OpenAddressing<Integer, Integer> ht3_medium = new OpenAddressing<Integer, Integer>();
        OpenAddressing<Integer, Integer> ht3_large = new OpenAddressing<Integer, Integer>();
  
 		System.out.println("\n************Testing with small-sized data(10 elements)********");
      	start = System.nanoTime();
      	for(t = 0,u = 0; t < 10 && u > -10; t++,u--)
      		ht3_small.put(t,u);
      	end = System.nanoTime();

      	System.out.println("ht3_small.get(1) for small-sized data : " + ht3_small.get(1));
        System.out.println("ht3_small.get(5) for small-sized data : " + ht3_small.get(5));
 
        System.out.println("after the ht3_small.remove(2):  " + ht3_small.remove(2));
        System.out.println("ht3_small.get(3):  " + ht3_small.get(3));
        System.out.println("size of ht3_small =  " + ht3_small.size());
        System.out.println("TEST - isEmpty?");
        System.out.println("ht2_small.isEmpty() =  " + ht3_small.isEmpty());
        System.out.println("Performance(according to put() method in small-sized data) =  " + (end-start) + "ns");


      	System.out.println("\n***********Testing with medium-sized data(100 elements)*******");
      	start = System.nanoTime();
      	for(t = 0,u = 0; t < 100 && u > -100; t++,u--)
      		ht3_medium.put(t,u);
      	end = System.nanoTime();

      	System.out.println("ht3_medium.get(23) for medium-sized data : " + ht3_medium.get(23));
        System.out.println("ht3_medium.get(96) for medium-sized data : " + ht3_medium.get(96));

        System.out.println("after the ht3_medium.remove(13):  " + ht3_medium.remove(13));
        System.out.println("after the ht3_medium.remove(41):  " + ht3_medium.remove(41));
        System.out.println("ht3_medium.get(1800):  " + ht3_medium.get(1800));
        System.out.println("size of ht3_medium =  " + ht3_medium.size());
        System.out.println("TEST - isEmpty?");
        System.out.println("ht3_medium.isEmpty() =  " + ht3_medium.isEmpty());
        System.out.println("Performance(according to put() method in medium-sized data) =  " + (end-start) + "ns");

      	System.out.println("\n***********Testing with large-sized data(1000 elements)*******");
      	start = System.nanoTime();
      	for(t = 0,u = 0; t < 1000 && u > -1000; t++,u--)
      		ht3_large.put(t,u);

      	end = System.nanoTime();

      	System.out.println("ht3_large.get(313) for large-sized data : " + ht3_large.get(313));
        System.out.println("ht3_large.get(912) for large-sized data : " + ht3_large.get(912));

        System.out.println("after the ht3_large.remove(928):  " + ht3_large.remove(928));
        System.out.println("after the ht3_large.remove(22):  " + ht3_large.remove(22));
        System.out.println("ht3_large.get(25):  " + ht3_large.get(25));
        System.out.println("size of ht3_large =  " + ht3_large.size());
        System.out.println("TEST - isEmpty?");
        System.out.println("ht3_large.isEmpty() =  " + ht3_large.isEmpty());

      	System.out.println("Performance(according to put() method in large-sized data) =  " + (end-start) + "ns");

    }


}
