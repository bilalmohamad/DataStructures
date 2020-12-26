package edu.ncsu.csc316.dsa.set;

/**
 * Implements the Set interface to create an abstract set
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <E> the object type
 */
public abstract class AbstractSet<E> implements Set<E> {

    @Override
    public void addAll(Set<E> other) {
        for(E element : other) {
            add(element);
        }
    }

    @Override
    public void retainAll(Set<E> other) {
        for(E element : this) {
            if(!other.contains(element)) {
                remove(element);
            }
        }
    }

    @Override
    public void removeAll(Set<E> other) {
        for(E element : other) {
            remove(element);
        }
    }   
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
