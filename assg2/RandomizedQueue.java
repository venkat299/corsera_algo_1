
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[]  array;
    private int size;

    public RandomizedQueue()  {
        array = (Item[]) new Object[10];
        size = 0;
    }               // construct an empty randomized queue
    public boolean isEmpty()    {
        return size == 0;
    }             // is the queue empty?
    public int size()           {
        return size;
    }             // return the number of items on the queue
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();

        if (size == array.length) {
            Item[]  temp = (Item[]) new Object[array.length * 2];
            System.arraycopy( array, 0, temp, 0, array.length);
            array = temp;
            temp = null;
        }
         array[size] = item;
        size++;
        if(size>1) { 
            int x=StdRandom.uniform(size);
            Item temp=array[x];
            array[x]=array[size-1];
            array[size-1]=temp;
        }

    }          // add the item
    public Item dequeue()         {

        if (size == 0) throw new NoSuchElementException();
        if (size < array.length / 3) {
            Item[]  temp = (Item[]) new Object[array.length / 2];
            System.arraycopy(array, 0, temp , 0, size);
            array = temp;
            temp = null;
        }
        
        size--;
        Item out = array[size];
        array[size] = null;
        return out;
    }           // delete and return a random item
    public Item sample()          {
         if (size == 0) throw new NoSuchElementException();
        return array[StdRandom.uniform(size)];
    }           // return (but do not delete) a random item
    public Iterator<Item> iterator()  {
        Item[] temp = (Item[]) new Object[size];
        System.arraycopy(array, 0, temp, 0, size);
        return (new RandomIterator(temp));
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomIterator implements Iterator<Item> {
        private Item[] ls_arr;
        private int remaining_count;
        RandomIterator(Item[] arr) {
            int N = arr.length;
            for (int i = 0; i < N; i++) {
                int r = i + StdRandom.uniform(N - i);   // between i and N-1
                Item temp = arr[i];
                arr[i] = arr[r];
                arr[r] = temp;
            }
            ls_arr = arr;
            remaining_count= arr.length;
        }
        public boolean hasNext()  {
            return remaining_count > 0;
        }
        public void remove()      {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            
            remaining_count--;
            return ls_arr[remaining_count];   

        }
    }      // return an independent iterator over items in random order
    public static void main(String[] args) {}  // unit testing
}

