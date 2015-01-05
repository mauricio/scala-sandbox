package trie

import scala.annotation.tailrec
import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Trie(var word: Option[String] = None) extends Traversable[String] {

  private[trie] val children: mutable.Map[Char, Trie] = new java.util.TreeMap[Char, Trie]().asScala

  def append(key: String) = {

    @tailrec def appendHelper(node: Trie, currentIndex: Int): Unit = {
      if (currentIndex == key.length) {
        node.word = Some(key)
      } else {
        val result = node.children.getOrElseUpdate(key.charAt(currentIndex).toLower, {
          new Trie()
        })

        appendHelper(result, currentIndex + 1)
      }
    }

    appendHelper(this, 0)
  }

  override def foreach[U](f: String => U): Unit = {

    @tailrec def foreachHelper(nodes: Trie*): Unit = {
      if (nodes.size != 0) {
        nodes.foreach(node => node.word.foreach(f))
        foreachHelper(nodes.flatMap(node => node.children.values): _*)
      }
    }

    foreachHelper(this)
  }

  def findByPrefix(prefix: String): scala.collection.Seq[String] = {

    @tailrec def helper(currentIndex: Int, node: Trie, items: ListBuffer[String]): ListBuffer[String] = {
      if (currentIndex == prefix.length) {
        items ++ node
      } else {
        node.children.get(prefix.charAt(currentIndex).toLower) match {
          case Some(child) => helper(currentIndex + 1, child, items)
          case None => items
        }
      }
    }

    helper(0, this, new ListBuffer[String]())
  }

  def contains(word: String): Boolean =
    !findByPrefix(word).isEmpty

  def remove(word : String) : Boolean = {

    //def helper(node)

    ???
  }

  private[trie] def pathTo( word : String ) : Option[ListBuffer[Trie]] = {

    def helper(buffer : ListBuffer[Trie], currentIndex : Int, node : Trie) : Option[ListBuffer[Trie]] = {
      if ( currentIndex == word.length && node.word.isDefined ) {
        buffer += node
        Some(buffer)
      } else {
        node.children.get(word.charAt(currentIndex).toLower) match {
          case Some(found) => {
            buffer += node
            helper(buffer, currentIndex + 1, found)
          }
          case None => None
        }
      }
    }

    helper(new ListBuffer[Trie](), 0, this)
  }

  override def toString() : String = s"Trie(${word})"

}

