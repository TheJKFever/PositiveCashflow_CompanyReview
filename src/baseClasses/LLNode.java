package baseClasses;

public class LLNode<T>
{
  private LLNode<T> link;
  private T data;
  
  public LLNode(T info)
  {
    this.data = info;
    link = null;
  }
 
  public void setData(T info)
  // Sets info of this LLNode.
  {
    this.data = info;
  }

  public T getData()
  // Returns info of this LLONode.
  {
    return data;
  }
 
  public void setLink(LLNode<T> link)
  // Sets link of this LLNode.
  {
    this.link = link;
  }

  public LLNode<T> getLink()
  // Returns link of this LLNode.
  {
    return link;
  }
}
 
 