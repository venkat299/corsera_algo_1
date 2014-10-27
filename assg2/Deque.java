import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Node next;
        private Node prev;
        private Item item;
    }
    private int size;
    private Node first;
    private Node last;
    public Deque()           {
        reintialize();

    }                // construct an empty deque
    public boolean isEmpty()   {
        return size == 0;
    }              // is the deque empty?
    public int size()            {
        return size;
    }            // return the number of items on the deque
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node node = new Node();
        node.item = item;
        if (size == 0) {
            // adding first item
            node.next = null;
            node.prev = null;
            first = node;
            last = node;
        } else {
            node.next = first;
            node.prev = null;
            first.prev = node;
            first = node;
        }
        size++;
    }         // insert the item at the front
    public void addLast(Item item)   {
        if (item == null) throw new NullPointerException();

        Node node = new Node();
        node.item = item;
        if (size == 0) {
            // adding first item
            node.next = null;
            node.prev = null;
            first = node;
            last = node;
        } else {
            node.next = null;
            node.prev = last;
            last.next = node;
            last = node;
        }
        size++;
    }        // insert the item at the end
    public Item removeFirst()          {
        if (size == 0) throw new NoSuchElementException();

        Node node = first;
        first = first.next;
        if(first!=null)
        first.prev = null;
        size--;
        if (size == 0) reintialize();
        return node.item;
    }      // delete and return the item at the front
    public Item removeLast()             {

        if (size == 0) throw new NoSuchElementException();
        Node node = last;
        last = last.prev;
        if(last!=null)
        last.next = null;
        size--;
        if (size == 0) reintialize();
        return node.item;
    }    // delete and return the item at the end
    private void reintialize() {
        size = 0;
        first = null;
        last = null;
    }
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  {
            return current != null;
        }
        public void remove()      {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {
        Deque<String> s = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.addLast(item);
            else if (!s.isEmpty()) StdOut.print(s.removeFirst() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");

    }  // unit testing
}