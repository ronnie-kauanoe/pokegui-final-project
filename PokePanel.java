import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.*;

/**
* PokeGUI panel.
* @since 11/28/17
* @author Ronnie Kauanoe
*/
public class PokePanel extends JPanel {

   private Random rand = new Random();
   
   private JPanel titles = new JPanel(new GridLayout(1, 2));
   private JPanel center = new JPanel(new GridLayout(1, 2));
   private JPanel consoles = new JPanel(new GridLayout(1, 2));
   private JPanel leftTitle = new JPanel();
   private JPanel leftScreen = new JPanel();
   private JPanel leftConsole = new JPanel();
   private JPanel rightTitle = new JPanel();
   private JPanel rightScreen = new JPanel();
   private JPanel rightConsole = new JPanel();
   
   private JLabel leftLabel = new JLabel("Gotta catch 'em all!");
   private JLabel rightLabel = new JLabel("Inventory");
   private JLabel leftText = new JLabel("Hello!");
   private JLabel rightText = new JLabel("Sort Pokemon by ");
   private JLabel leftResult = new JLabel(" ");
   private JLabel stats = new JLabel("");
   
   private static Choice sortOptions = new Choice();
   
   private int currPoke;
   private int catchClicks = -1;
   private int caughtRate;
   private boolean caught = true;;
   private Pokemon hunted = new Bulbasaur();
   private ArrayList<Pokemon> pokeList = new ArrayList<>();
   private PokeTree pokeTree = new PokeTree();
   
   private JButton huntPoke = new JButton(" Hunt ");
   private JButton catchPoke = new JButton(" Catch ");
   private JButton pokedex = new JButton(" Pokedex ");
   private JButton backpack = new JButton(" Backpack ");

   private Font font = new Font("Times New Roman", Font.BOLD, 30);
   
   private JTextArea area = new JTextArea("Your inventory is empty");
   
   private JTextField name = new JTextField(15);
   
   private JScrollPane scrollPane = new JScrollPane(area);
   
   private GUIListener listener = new GUIListener();
   
   /**
   * PokePanel constructor.
   */
   public PokePanel() {
      setPreferredSize(new Dimension(1400, 800));
      setLayout(new BorderLayout());
      add("North", titles);
      add("Center", center);
      add("South", consoles);
      
      titles.add(leftTitle);
      leftTitle.add(leftLabel);
      leftTitle.setBackground(new Color(0, 191, 255));
      leftLabel.setFont(font);
      
      titles.add(rightTitle);
      rightTitle.add(rightLabel);
      rightTitle.setBackground(new Color(255, 69, 0));
      rightLabel.setFont(font);
      
      center.add(leftScreen);
      leftScreen.setBackground(Color.WHITE);
      leftScreen.setLayout(new BoxLayout(leftScreen, BoxLayout.Y_AXIS));  
      leftScreen.setAlignmentX(Component.CENTER_ALIGNMENT); 
      
      leftScreen.add(leftText);
      leftText.setAlignmentX(Component.CENTER_ALIGNMENT);
      leftText.setFont(font);
      
      leftScreen.add(leftResult);
      leftResult.setAlignmentX(Component.CENTER_ALIGNMENT);
      leftResult.setFont(font);
      
      leftScreen.add(stats);
      stats.setAlignmentX(Component.CENTER_ALIGNMENT);
      stats.setHorizontalAlignment(JLabel.CENTER);
      stats.setFont(new Font("Times New Roman", Font.PLAIN, 20));
      
      center.add(rightScreen);
      rightScreen.setLayout(new BoxLayout(rightScreen, BoxLayout.Y_AXIS));
      rightScreen.setBackground(Color.WHITE);
      rightScreen.add(rightText);
      rightText.setAlignmentX(Component.CENTER_ALIGNMENT);
      rightScreen.add(sortOptions); 
      rightScreen.add(scrollPane);
      scrollPane.setPreferredSize(new Dimension(300, 650));
      
      consoles.add(leftConsole);
      leftConsole.setBackground(new Color(0, 191, 255));
      leftConsole.add(huntPoke);
      leftConsole.add(catchPoke);
      huntPoke.setFont(font);
      catchPoke.setFont(font);
      
      consoles.add(rightConsole);
      rightConsole.setBackground(new Color(255, 69, 0));
      rightConsole.add(pokedex);
      rightConsole.add(backpack);
      pokedex.setFont(font);
      backpack.setFont(font);
      
      sortOptions.setFont(new Font("Courier", Font.PLAIN, 25));
      
      area.setEditable(false);
      area.setFont(new Font("Courier", Font.PLAIN, 15));
      
      sortOptions.add("Recent");
      sortOptions.add("Number");
      sortOptions.add("Species");
      sortOptions.add("HP");
      sortOptions.add("CP");
   
      huntPoke.addActionListener(listener);
      catchPoke.addActionListener(listener);
      pokedex.addActionListener(listener);
      backpack.addActionListener(listener);
   }
   
   /**
   * @return choice menu index
   */
   public static int getChoice() {
      return sortOptions.getSelectedIndex();
   }
   
   /**
   * GUIListener class to assign commands to buttons.
   */
   private class GUIListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         if (event.getSource() == huntPoke) {
            leftResult.setText(" ");
            stats.setText(" ");
            int i = rand.nextInt(9);
            caught = false;
            if (i == currPoke) {
               while (i == currPoke) {
                  i = rand.nextInt(9);
               }
            }
            
            switch (i) {
               case 0:
                  hunted = new Bulbasaur();
                  break;
               case 1:
                  hunted = new Ivysaur();
                  break;
               case 2:
                  hunted = new Venusaur();
                  break;
               case 3:
                  hunted = new Charmander();
                  break;
               case 4:
                  hunted = new Charmeleon();
                  break;
               case 5:
                  hunted = new Charizard();
                  break;
               case 6:
                  hunted = new Squirtle();
                  break;
               case 7:
                  hunted = new Wartortle();
                  break;
               case 8:
                  hunted = new Blastoise();
                  break;
               default:
                  hunted = null;
                  break;
            }
            currPoke = i;
            catchClicks = 0;
            leftText.setText("A wild " + hunted.getSpecies()
               + " has appeared!");
         }
         if (event.getSource() == catchPoke) {
            leftResult.setText("");
            if (catchClicks > 0) {
               if (caught) {
                  leftResult.setText("You already caught"
                     + " this Pokemon. Good job!");
               } else {
                  leftResult.setText("The Pokemon already "
                     + "got away. Better luck next time!");
               }
            } else {
               if (catchClicks == -1) {
                  leftText.setText("There's no Pokemon to catch. Go hunting!"); 
               } else {
                  caughtRate = rand.nextInt(100);
                  if (caughtRate >= 60) {
                     caught = true;
                     leftResult.setText("You caught the " 
                        + hunted.getSpecies() + "!");
                     stats.setText(hunted.toStringHTML());
                     pokeList.add(hunted);
                     pokeTree.add(hunted);
                  } else {
                     caught = false;
                     leftResult.setText("The " + hunted.getSpecies() 
                        + " escaped!");
                  }
                  catchClicks++;
               }
            }
         }
         
         if (event.getSource() == pokedex) {
            if (!(pokeList.size() == 0)) { 
               area.setText(pokeTree.toString());
            }
         }
         
         if (event.getSource() == backpack) {
            String temp = "";
            if (sortOptions.getSelectedIndex() == 0) {
               for (int i = pokeList.size() - 1; i >= 0; i--) {
                  temp = temp + pokeList.get(i).toString() + "\n";
                  area.setText(temp);
               }
            } else {
               PriorityQueue<Pokemon> pq = new PriorityQueue<>();
               for (int i = 0; i < pokeList.size(); i++) {
                  pq.add(pokeList.get(i));
               }
               while (pq.size() > 0) {
                  temp = temp + pq.poll().toString() + "\n";
                  area.setText(temp);
               }
            }
         }
      }
   }
}