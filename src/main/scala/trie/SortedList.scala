package trie

class SortedList[T <: Ordered[T]] {

  private var head: Option[Node[T]] = None

  def add(item: T): Unit = {
    head match {
      case Some(node) => node.add(item)
      case None => this.head = Some(Node(item))
    }
  }

}

private case class Node[T <: Ordered[T]](var item: T, var next: Option[Node[T]] = None) {

  def add(added: T): Unit = {

    if (added < item) {
      this.next = Some(Node(item, next))
      this.item = added
    } else {
      next match {
        case Some(node) => node.add(added)
        case None => this.next = Some(Node(added))
      }
    }

  }

}
