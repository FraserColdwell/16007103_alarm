package clock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;



/**
 * Implementation of the PriorityQueue ADT using a sorted array for storage.
 *
 * Because Java does not allow generic arrays (!), this is implemented as an
 * array of Object rather than of PriorityItem&lt;T&gt;, which would be natural.
 * Array elements accessed then have to be cast to PriorityItem&lt;T&gt; before
 * using their getItem() or getPriority() methods.
 * 
 * This is an example of Java's poor implementation getting in the way. Java
 * fanboys will no doubt explain at length why it has to be this way, but note
 * that Eiffel allows it because Eiffel generics were done right from the start,
 * rather than being tacked on as an afterthought and limited by issues of
 * backward compatibility. Humph!
 * 
 * @param <T> The type of things being stored.
 */
public class SortedArrayPriorityQueue<T> implements PriorityQueue<T> {
    
    /**
     * Where the data is actually stored.
     */
    private final Object[] storage;

    /**
     * The size of the storage array.
     */
    private final int capacity;

    /**
     * The index of the last item stored.
     *
     * This is equal to the item count minus one.
     */
    private int tailIndex;

    /**
     * Create a new empty queue of the given size.
     *
     * @param size
     */
    public SortedArrayPriorityQueue(int size) {
        storage = new Object[size];
        capacity = size;
        tailIndex = -1;
    }

    
    
    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            return ((PriorityItem<T>) storage[0]).getItem();
        }
    }

    //adds a new item using the datetime as the item and the epochtime as the priority
    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        tailIndex = tailIndex + 1;
        if (tailIndex >= capacity) {
            /* No resizing implemented, but that would be a good enhancement. */
            tailIndex = tailIndex - 1;
            throw new QueueOverflowException();
        } else {
            /* Scan backwards looking for insertion point */
            int i = tailIndex;
            while (i > 0 && ((PriorityItem<T>) storage[i - 1]).getPriority() > priority) {
                storage[i] = storage[i - 1];
                i = i - 1;
            }
            storage[i] = new PriorityItem<>(item, priority);
        }
    }

    
    //removes the item the user selects
    @Override
    public void remove(int epochtime) throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            
            int highest_pri = highest_priority(epochtime);
            System.out.println("Highest Pri");
            System.out.println(highest_pri);
            System.out.println("Storage Length");
            System.out.println(storage.length - 1);
            for (int i = highest_pri; i < storage.length - 1; i++) {
                storage[i] = storage[i + 1];
            }
            tailIndex = tailIndex - 1;
        }
    }

    @Override
    public boolean isEmpty() {
        return tailIndex < 0;
    }
    
    //find the location of the priority we want to remove
    public int highest_priority(int epochtime)
    {      
        int i =0;
        int Pos=0;
     
        
                    System.out.println("GetPriority");
                   System.out.println(((PriorityItem<T>) storage[i]).getPriority());
                   System.out.println("epochtime");
                   System.out.println(epochtime);
           while(i <= tailIndex){
               System.out.println("GetPriority");
                   System.out.println(((PriorityItem<T>) storage[i]).getPriority());
                   System.out.println("epochtime");
                   System.out.println(epochtime);
               if(((PriorityItem<T>) storage[i]).getPriority() == epochtime){
                   
                   Pos = i;
                
               }
               
               i++;
            }
            return Pos;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i <= tailIndex; i++) {
            if (i > 0) {
                result = result + ", ";
            }
            result = result + storage[i];
        }
        return result;
    }
    
    //returns the length of the array to the viewclass
    public int count()
    {
        return tailIndex;
    }
    
    //returns the priority the the view class
    public int returnPriority()
    {
        return ((PriorityItem<T>) storage[0]).getPriority();
    }
    
    //returns an array containing all the priorities to the view class
    public int[] returnPriorityLoop()
    {
        int numberofalarms = tailIndex + 1;
        int[] priorities = new int[numberofalarms];
        for (int i=0; i<=tailIndex;i++)
        {
            int priority = ((PriorityItem<T>) storage[i]).getPriority();
            priorities[i] = priority;
        }
        return priorities;
    }
    
    
    //returns the tailIndex to the viewclass
    public int tailIndex()
    {
        return tailIndex;
    }
    
    }
    
