package trie

import org.specs2.mutable.Specification

class TrieSpec extends Specification {

  "trie" should {

    "include a word" in {
      val trie = new Trie()
      trie.append("Maurício")

      trie must contain("Maurício")
    }

    "find by prefix" in {
      val trie = new Trie()
      trie.append("San Francisco")
      trie.append("San Diego")
      trie.append("Santo Domingo")
      trie.append("São Paulo")

      val result = trie.findByPrefix("san")

      result.length === 3
      result must contain("San Francisco", "San Diego", "Santo Domingo")
    }

    "not find by prefix if it does not exist" in {
      val trie = new Trie()
      trie.append("São Paulo")
      trie.append("Rio de Janeiro")
      trie.append("Philadelphia")

      trie.findByPrefix("bos") must beEmpty
    }

  }

}
