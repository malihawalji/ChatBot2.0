import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ChatBot implements KeyListener {

    protected final JFrame frame = new JFrame("ChatBot");
    protected String quote;

    protected  JPanel p = new JPanel();
    protected  JTextArea dialog = new JTextArea(20, 50);
    protected  JTextArea input = new JTextArea(1, 50);
    protected JScrollPane scroll = new JScrollPane(
            dialog,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    String[][] MainchatBot = {
            {"hi", "hello", "hey"},
            {"hi", "hello", "hey"},
            {"how are you", "how are u", "how r u", "how's it going"},
            {"good", "doing well", "feelin peachy"},
            {"what's up", "whats up"},
            {"not much just talking to you", "the ceiling haha", "the sky", "waiting for you to ask a question"},
            {"what are you"},
            {"a chatbot!"},
            //default
            {"i don't understand?", "ask me a different question", "pardon me?", "You can ask me about java",
                    "ask me for a movie suggestion", "I can give a restaurant suggestion"}};

    String[][] MoviechatBot = {
            {"watch","genre","movie", "Do you have any movie suggestions?", "Movie suggestion", "give me a movie suggestion"},
            {"What kind of movies do you like? \n\tromance, action, adventure, comedy?",
                    "Do you have a preferance in genre \n\tromance, action, adventure?"},

            {"romance"},
            {"A Walk to Remember", "Love Actually", "The Fault in our Stars"},

            {"action", "adventure"},
            {"21 Jumpstreet", "Fast and Furious", "Justice League", "Men in Black"},

            {"comedy"},
            {"The Dictator"},
            //default
            {"You can ask me for a movie suggestion", "ask me a different question", "pardon me?"}};

    String[][] javaChatBot = {
            {"what is java", "what else","what do you know about java?", "java"},
            {"A coding language","An island","Coffee"},
            {"is java a coding language","is java an island", "is java a coffee"},
            {"yes"},
            {"tell me something about coding", "tell me something", "what do you like about java"},
            {"java is a pretty cool language that allows you to create different things", "coding is now fundamental for many careers"},
            //default
            {"i don't understand?", "ask me a different question", "pardon me?", "i don't think thats right"}};

    String[][] restaurantChatBot={
            {"Will you help me select a restaurant?","give me restaurant choices", "restaurant", "choices","Do you have any restaurant suggestions?"},
            {"I can give you suggestions in the minneapolis area. \n Is anyone in your party a vegetarian?"},

            {"yes"},
            {"Trio Plant Based is a good option","Vegan East", "Namaste Cafe"},

            {"no"},
            {"World Street Kitchen", "Olive Garden", "Punch Pizza", "PF Chang's"},
            //default
            {"I don't understand?", "ask me a different question", "pardon me?"}};

    public ChatBot() {
        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        dialog.setEditable(false);
        input.addKeyListener(this);

        p.add(scroll);
        p.add(input);
        p.setBackground(new

                Color(105, 60, 230, 153));
        frame.add(p);
        frame.setVisible(true);
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            input.setEditable(false);

            quote = input.getText();
            input.setText("");
            addText("-->You:\t"+quote);
            //quote.trim();
            while(
                    quote.charAt(quote.length()-1)=='!' ||
                            quote.charAt(quote.length()-1)=='.' ||
                            quote.charAt(quote.length()-1)=='?'
            ){
                quote=quote.substring(0,quote.length()-1);
            }
            //quote.trim();
            byte response=0;
            int j=0;
            while(response==0){
                if(inArray(quote.toLowerCase(),MainchatBot[j*2])){
                    response=2;
                    int r=(int)Math.floor(Math.random()*MainchatBot[(j*2)+1].length);
                    addText("\n-->Bot\t"+MainchatBot[(j*2)+1][r]);
                } else if (inArray(quote.toLowerCase(),MoviechatBot[j*2])){
                    response=2;
                    int r=(int)Math.floor(Math.random()*MoviechatBot[(j*2)+1].length);
                    addText("\n-->Bot\t"+MoviechatBot[(j*2)+1][r]);
                } else if(inArray(quote.toLowerCase(),javaChatBot[j*2])){
                    response=2;
                    int r=(int)Math.floor(Math.random()*javaChatBot[(j*2)+1].length);
                    addText("\n-->Bot\t"+javaChatBot[(j*2)+1][r]);
                } else if(inArray(quote.toLowerCase(),restaurantChatBot[j*2])){
                    response=2;
                    int r=(int)Math.floor(Math.random()*restaurantChatBot[(j*2)+1].length);
                    addText("\n-->Bot:\t"+restaurantChatBot[(j*2)+1][r]);
                }
                j++;
                if(j*2==MainchatBot.length-1 && response==0){
                    response=1;
                }
            }
            if(response==1){
                int r=(int)Math.floor(Math.random()*MainchatBot[MainchatBot.length-1].length);
                addText("\n-->Bot\t"+MainchatBot[MainchatBot.length-1][r]);
                addText("\n");
            }
            addText("\n");
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            input.setEditable(true);
        }
    }

    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER) {
            input.setEditable(true);
        }
    }

    public void addText(String str) {
        dialog.setText(dialog.getText()+str);
    }

    public boolean inArray(String in, String[] str) {
        boolean match=false;
        for (String s : str) {
            if (s.equals(in)) {
                match = true;
            }
        }
        return match;
    }
}
