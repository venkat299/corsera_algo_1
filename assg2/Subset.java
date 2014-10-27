import java.util.*;
public class Subset {
    public static void main(String[] args) {
    	 int loop=Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty() ) {
            String item = StdIn.readString();
            q.enqueue(item);
           
        }
        Iterator<String> i = q.iterator();
        while (i.hasNext() && loop>0 ) {
            StdOut.println((String)i.next());
             loop--;
        }
   /*     StdOut.println(q.size());
        StdOut.println(q.toString());*/

    }
}
