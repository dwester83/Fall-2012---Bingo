/**
 * LinkedSet represents a linked implementation of a set.
 *
 * @author Dr. Chase
 * @author Dr. Lewis
 * @version 1.0, 9/21/2008
 */

package jss2;
import jss2.exceptions.*;
import java.util.*;

public class LinkedSet<T> implements SetADT<T>, Iterable<T>
{
  private static Random rand = new Random();
  private int count;  // the current number of elements in the set 
  private LinearNode<T> contents; 

  /**
   * Creates an empty set.
   */
  public LinkedSet()
  {
    count = 0;
    contents = null;
  }

  /**
   * Adds the specified element to this set if it is not already
   * present.
   *
   * @param element  the element to be added to this set
   */
  public void add (T element)
  {
    if (!(contains(element)))
    {
      LinearNode<T> node = new LinearNode<T> (element);
      node.setNext(contents);
      contents = node;
      count++;
    }
  }

  /**
   * Adds the contents of the parameter to this set.
   *
   * @param set  the set whose contents are to be added to this set
   */
  public void addAll (SetADT<T> set)
  {
    Iterator<T> scan = set.iterator();

    while (scan.hasNext())
    add (scan.next());
  }

  /**
   * Removes and returns a random element from this set. Throws
   * an EmptySetException if the set contains no elements.
   *
   * @return                    a random element from this set
   * @throws EmptyCollectionException  if an empty set exception occurs
   */
  public T removeRandom() throws EmptyCollectionException
  {
    LinearNode<T> previous, current;
    T result = null;

    if (isEmpty())
      throw new EmptyCollectionException("Stack");
    
    int choice = rand.nextInt(count) + 1;
    
    if (choice == 1)
    {
      result = contents.getElement();
      contents = contents.getNext();
    }
    else
    {
      previous = contents;
      for (int skip=2; skip < choice; skip++)
        previous = previous.getNext();
      current = previous.getNext();
      result = current.getElement();
      previous.setNext(current.getNext());
    }
      
    count--;

    return result;
  }

  /**
   * Removes and returns the specified element from this set.
   * Throws an EmptySetException if the set is empty and a
   * NoSuchElemetnException if the target is not in the set.
   *
   * @param target                   the element being sought in this set
   * @return                         the element just removed from this set
   * @throws EmptyCollectionException       if an empty set exception occurs
   * @throws NoSuchElementException  if a no such element exception occurs
   */
  public T remove (T target) throws EmptyCollectionException,
                                     NoSuchElementException
  {
    boolean found = false;
    LinearNode<T> previous, current;
    T result = null;

    if (isEmpty())
      throw new EmptyCollectionException("Set");

    if (contents.getElement().equals(target))
    {
      result = contents.getElement();
      contents = contents.getNext();
    }
    else
    {
      previous = contents;
      current = contents.getNext();
      for (int look=0; look < count && !found; look++)
        if (current.getElement().equals(target))
          found = true;
        else
        {
          previous = current;
          current = current.getNext();
        }

      if (!found)
        throw new NoSuchElementException();

        result = current.getElement();
        previous.setNext(current.getNext());
    
    }
    
    count--;
    
    return result;
  }
  
  /**
   * Returns a new set that is the union of this set and the
   * parameter.
   *
   * @param set  the set to be unioned with this set
   * @return     a new set that is a union of this set and the parameter
   */
  public SetADT<T> union (SetADT<T> set)
  {
    LinkedSet<T> both = new LinkedSet<T>();
    LinearNode<T> current = contents;
    
    while (current != null)
    {
      both.add (current.getElement());
      current = current.getNext();
    }
    
    Iterator<T> scan = set.iterator();
    while (scan.hasNext())
      both.add (scan.next());
    
    return both;
  }
  
  /**
   * Returns true if this set contains the specified target
   * element.
   *
   * @param target  the element being sought in this list
   * @return        true if the set contains the target element
   */
  public boolean contains (T target)
  {
    boolean found = false;
    LinearNode<T> current = contents;
    
    for (int look=0; look < count && !found; look++)
      if (current.getElement().equals(target))
        found = true;
      else
        current = current.getNext();
    
    return found;
  }
  
  /**
   * Returns true if this set contains exactly the same elements
   * as the parameter.
   *
   * @param set  the set to be compared with this set
   * @return     true if this set contains exactly the same elements
   *             as the parameter
   */
  public boolean equals (SetADT<T> set)
  {
    boolean result = false;
    LinkedSet<T> temp1 = new LinkedSet<T>();
    LinkedSet<T> temp2 = new LinkedSet<T>();
    T obj;

    if (size() == set.size())
    { 
      temp1.addAll(this);
      temp2.addAll(set);

      Iterator<T> scan = set.iterator();

      while (scan.hasNext())
      {
        obj = scan.next();
        if (temp1.contains(obj))
        {
          temp1.remove(obj);
          temp2.remove(obj);
        }
      }
      result = (temp1.isEmpty() && temp2.isEmpty());
    }

    return result;
  }

  /**
   * Returns true if this set is empty and false otherwise. 
   *
   * @return  true if this set is empty
   */
  public boolean isEmpty()
  {
    return (size() == 0);
  }
 
  /**
   * Returns the number of elements currently in this set.
   *
   * @return  the number of elements in this set
   */
  public int size()
  {
    return count;
  }

  /**
   * Returns an iterator for the elements currently in this set.
   *
   * @return  an iterator for the elements in this set
   */
  public Iterator<T> iterator()
  {
    return new LinkedIterator<T> (contents, count);
  }

  /**
   * Returns a string representation of this set. 
   *
   * @return  a string representation of this set
   */
  public String toString()
  {
    String result = "";

    LinearNode<T> current = contents;

    while (current != null)
    {
      result += current.getElement().toString() + "\n";
      current = current.getNext();
    }

    return result;
  }
  
  /**
   * Returns a set of the intersection of two sets.
   * 
   * @param set Set to see where they intersect
   * @return set The intersection of the two sets
   */
  public LinkedSet<T> intersection(LinkedSet<T> set){
	    LinkedSet<T> intersect = new LinkedSet<T>();
	    LinearNode<T> current = contents;
	    
	    while (current != null)
	    {
	    	if (set.contains(current.getElement()))
	    		intersect.add(current.getElement());
	      current = current.getNext();
	    }
	    
	    return intersect;
  }
  
  /**
   * 
   * 
   * @param set
   * @return
   */
  public LinkedSet<T> difference(LinkedSet<T> set){
	    LinkedSet<T> differ = new LinkedSet<T>();
	    LinearNode<T> current = contents;
	    
	    Iterator<T> scan = set.iterator();
	    while (scan.hasNext())
	      differ.add (scan.next());
	    
	    while (current != null)
	    {
	    	if (differ.contains(current.getElement()))
	    		differ.remove(current.getElement());
	    	else
	    		differ.add(current.getElement());
	      current = current.getNext();
	    }
	    
	    return differ;
  }
}

