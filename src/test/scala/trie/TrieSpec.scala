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

    "say that a word is contained" in {
      val trie = new Trie()
      trie.append("João Pessoa")

      trie.contains("João Pessoa") must beTrue
    }

    "say that a word is not contained" in {
      val trie = new Trie()
      trie.append("João Pessoa")

      trie.contains("João") must beFalse
    }

    "provide the path to a word" in {
      val trie = new Trie()
      trie.append("San Francisco")
      trie.append("San Diego")

      val list = trie.pathTo("San Diego").get
      println(list)
      list.length === 9
      list(0).children.get('s').isDefined must beTrue
    }

  }

}
