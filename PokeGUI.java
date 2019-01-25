import javax.swing.JFrame;
/**
* PokeGUI driver.
* @since 11/28/17
* @author Ronnie Kauanoe
*/
public class PokeGUI {
   public static void main(String[] args) {
      JFrame frm = new JFrame("PokeGUI");
      frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frm.getContentPane().add(new PokePanel());
      frm.pack();
      frm.setVisible(true);
   }
}