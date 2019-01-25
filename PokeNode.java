/**
* Pokemon node.
* @author Ronnie Kauanoe
* @since 11/19/17
*/
public class PokeNode {
   private Pokemon poke;
   private int numCaught = 0;
   private PokeNode leftChild;
   private PokeNode rightChild;
   
   /**
   * Pokemon node constructor.
   * @param data Address of the Pokemon
   * @param lChild Address of the left child
   * @param rChild Address of the right child
   */
   public PokeNode(Pokemon data, PokeNode lChild, PokeNode rChild) {
      
      poke = data;
      numCaught = 1;
      leftChild = lChild;
      rightChild = rChild;
   }
   /**
   * @return Pokemon's address
   */
   public Pokemon getPokemon() {
      return poke;
   }
   
   /**
   * @return Prints out amount of Pokemon caught
   */
   public String toString() {
      String s = "Caught " + numCaught + "\n";
      return s + poke.toString();
   }
   
   /**
   * @return Pokemon's Pokedex number
   */
   public int getKey() {
      return poke.getNumber();
   }
   
   /**
   * @return Returns amount of a Pokemon that's caught
   */
   public int getNumCaught() {
      return numCaught;
   }
   
   /**
   * @return the leftChild child's address
   */
   public PokeNode getLeftChild() {
      return leftChild;
   }
   /**
   * @return the right child's address
   */
   public PokeNode getRightChild() {
      return rightChild;
   }
   
   /**
   * Increases amount of Pokemon caught.
   */
   public void increaseNumCaught() {
      numCaught++;
   }
   
   /**
   * Decreases amount of Pokemon caught.
   * @throws PokemonException Throws pe if user tries to
      decrease number caught to 0
   */
   public void decreaseNumCaught() throws PokemonException {
      if (numCaught  == 1) {
         throw new PokemonException("Error: There must be at least one"
            + " Pokemon caught");
      } else {
         numCaught--;
      }
   }
   
   /**
   * @param newLeftChild is the left child's address
   * @return leftChild
   */
   public PokeNode setLeftChild(PokeNode newLeftChild) {
      leftChild = newLeftChild;
      return leftChild;
   }
   
   /**
   * @param newRightChild is the right child's address
   * @return rightChild
   */
   public PokeNode setRightChild(PokeNode newRightChild) {
      rightChild = newRightChild;
      return rightChild;
   }
}