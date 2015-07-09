public interface IList<E>{

  public E get(int index) throws Exception;
  public void add(E e);
  public void add(int index, E e);
  public int size();
  
}