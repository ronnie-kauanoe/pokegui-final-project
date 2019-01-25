/**
* Pokemon search tree.
* @author Ronnie Kauanoe
* @since 11/19/17
*/
public class PokeTree {
   
   private PokeNode root = null;

   /**
   * Blank PokeTree constructor.
   */
   public PokeTree() {
   }

   /**
   * @param p Pokemon to be added to node
   */
   public void add(Pokemon p) {
      root = add(root, p);
   }
   
   /**
   * @param node Node containing Pokemon
   * @param p Pokemon to be added to node
   */
   private PokeNode add(PokeNode node, Pokemon p) {
      if (node == null) {
         return new PokeNode(p, null, null);
      } else if (p.getNumber() - node.getPokemon().getNumber() == 0) {
         node.increaseNumCaught();
      } else if (p.getNumber() - node.getPokemon().getNumber() < 0) {
         node.setLeftChild(this.add(node.getLeftChild(), p));
         return node;
      } else {
         node.setRightChild(this.add(node.getRightChild(), p));
         return node;
      }
      return node;
   }
   
   /**
   * @param p Pokemon to be removed
   */
   public void remove(Pokemon p) {
      root = this.remove(root, p);
   }
   
   /**
   * @param node Node to have Pokemon removed from
   * @param p Pokemon to be removed
   * @return node
   */
   private PokeNode remove(PokeNode node, Pokemon p) {
      if (node == null) {
         throw new TreeException("Item not found!");
      } else if (p.getNumber() - node.getPokemon().getNumber() < 0) {
         node.setLeftChild(this.remove(node.getLeftChild(), p));
         return node;
      } else if (p.getNumber() - node.getPokemon().getNumber() > 0) {
         node.setRightChild(this.remove(node.getRightChild(), p));
         return node;
      } else {
         node = this.remove(node);
         return node;
      }
   }
   
   /*
   * @param node Node to be removed
   * @return node
   */
   private PokeNode remove(PokeNode node) {
      if (node.getNumCaught() > 1) {
         node.decreaseNumCaught();
         return node;
      } else if (node.getLeftChild() == null && node.getRightChild() == null) {
         return null;
      } else if (node.getLeftChild() == null) {
         return node.getRightChild();
      } else if (node.getRightChild() == null) {
         return node.getLeftChild();
      } else {
         Pokemon largestItemInLeftSubtree = 
            this.getItemWithLargestSearchKey(node
            .getLeftChild());
         node.setLeftChild(this.removeNodeWithLargestSearchKey(node
            .getLeftChild()));
         return node;
      }
   }
   
   /**
   * @param p Pokemon of a node
   * @return this.get(root,p)
   */
   public Pokemon get(Pokemon p) {
      return this.get(root, p);
   }
   
   private Pokemon get(PokeNode node, Pokemon searchKey) {
      if (node == null) {
         throw new TreeException("Item not found!");
      } else {
         if (searchKey.getNumber() - node.getPokemon().getNumber() == 0) {
            return node.getPokemon();
         } else if (searchKey.getNumber() - node.getPokemon().getNumber() < 0) {
            return this.get(node.getLeftChild(), searchKey);
         } else {
            return this.get(node.getRightChild(), searchKey);
         }
      }
   }   
   
   /**
   * Prints out PokeTree.
   */
   public void printTree() {
   }
   
   private void preorderTree(PokeNode node) {
      if (root != null) {
         System.out.println(" " + node.getPokemon().toString()
            + "\nCaught: " + root.getNumCaught());
         preorderTree(node.getLeftChild());
         preorderTree(node.getRightChild());
      }
   } 
   
   /**
   * @return Returns nodes in order style
   */
   public String toString() {
      return this.inOrder(root);
   }
   
   /**
   * @param node Displays nodes based on the in order style
   * @return Returns nodes displayed in in order style
   */
   private String inOrder(PokeNode node) {
      String displayNodes = "";
      if (node != null) {
         displayNodes = displayNodes
            + this.inOrder(node.getLeftChild());
         displayNodes = displayNodes + node.toString() + "\n";
         displayNodes = displayNodes
            + this.inOrder(node.getRightChild());
      }
      return displayNodes;
   }
   
   /**
   * @return Orders Pokemon in preorder style
   */
   public String preOrder() {
      return this.preOrder(root);
   }
   
   /**
   * @param node Displays nodes based on the preorder style
   * @return Returns nodes displayed in in preorder style
   */
   private String preOrder(PokeNode node) {
      String displayNodes = "";
      if (node != null) {
         displayNodes = displayNodes + node.toString() 
            + "\n";
         displayNodes = displayNodes 
            + this.preOrder(node.getLeftChild());
         displayNodes = displayNodes 
            + this.preOrder(node.getRightChild());
      }
      return displayNodes;
   }
   
   /**
   * @return Orders Pokemon in post order style
   */
   public String postOrder() {
      return this.postOrder(root);
   }
   
   /**
   * @param node Displays nodes based on the post order style
   * @return Returns nodes displayed in post order style
   */
   private String postOrder(PokeNode node) {
      String displayNodes = "";
      if (node != null) {
         displayNodes = displayNodes + this.postOrder(node.getLeftChild());
         displayNodes = displayNodes + this.postOrder(node.getRightChild());
         displayNodes = displayNodes + node + "\n";
      }
      return displayNodes;
   }   
   
   private Pokemon getItemWithLargestSearchKey(PokeNode node) {
      if (node.getRightChild() == null) {
         return node.getPokemon();
      } else {
         return this.getItemWithLargestSearchKey(node.getRightChild());
      }
   }

   private PokeNode removeNodeWithLargestSearchKey(PokeNode node) {
      if (node.getRightChild() == null) {
         return node.getLeftChild();
      } else {
         node.setRightChild(this.removeNodeWithLargestSearchKey(
            node.getRightChild()));
         return node;
      }
   }   
}