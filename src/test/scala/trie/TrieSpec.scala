package trie

import org.specs2.mutable.Specification

class TrieSpec extends Specification {

  "trie" should {

    "include a word" in {
      val trie = new TrieNode()
      trie.append("Maurício")

      trie must contain("Maurício")
    }

    "find by prefix" in {
      val trie = new TrieNode()
      trie.append("San Francisco")
      trie.append("San Diego")
      trie.append("Santo Domingo")
      trie.append("São Paulo")

      val result = trie.findByPrefix("san")

      result.length === 3
      result must contain("San Francisco", "San Diego", "Santo Domingo")
    }

    "not find by prefix if it does not exist" in {
      val trie = new TrieNode()
      trie.append("São Paulo")
      trie.append("Rio de Janeiro")
      trie.append("Philadelphia")

      trie.findByPrefix("bos") must beEmpty
    }

    "say that a word is contained" in {
      val trie = new TrieNode()
      trie.append("João Pessoa")

      trie.contains("João Pessoa") must beTrue
    }

    "say that a word is not contained" in {
      val trie = new TrieNode()
      trie.append("João Pessoa")

      trie.contains("João") must beFalse
    }

    "provide the path to a word" in {
      val word = "San Diego"

      val trie = new TrieNode()
      trie.append("San Francisco")
      trie.append(word)

      val list = trie.pathTo(word).get
      println(list)
      list.length === 10
      (0 until 9).map {
        index =>
          list(index).children.get(word.charAt(index).toLower).isDefined must beTrue
      }
    }

    "remove an item" in {
      val trie = new TrieNode()
      trie.append("San Francisco")
      trie.append("San Antonio")

      trie.remove("San Francisco") must beTrue
      trie.contains("San Francisco") must beFalse
      trie.contains("San Antonio") must beTrue
    }

  }

}
