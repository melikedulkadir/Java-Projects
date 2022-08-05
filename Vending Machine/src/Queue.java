import java.util.ArrayList;
import java.util.Comparator;

public class Queue {
    ArrayList<Token> tokens = new ArrayList<>();                            // Arraylist for keeping tokens

    public void enqueue(Token token){                                       // Enqueue method for Queue class in order to add token
        this.tokens.add(token);
        tokens.sort(Comparator.comparing(Token::getTokenValue).reversed());
    }
    public void dequeue(String partName, int quantity){                     // Dequeue method for Queue class in order to using token
        for (Token token:tokens) {
           if (token.getTokenPart().equals(partName)){
               if (token.getTokenValue()==quantity){                        // If token's value equal to quantity in task
                   tokens.remove(token);
                   return;
               }else if(token.getTokenValue()<quantity){                    // If token's value less than quantity in task
                   quantity = quantity - token.getTokenValue();
                   tokens.remove(token);
                   dequeue(partName,quantity);                              // Calling dequeue method for remaining quantity
                   return;
               }else if (token.getTokenValue()>quantity){
                   token.setTokenValue(token.getTokenValue()-quantity);
                   Token current = token;
                   tokens.remove(token);
                   enqueue(current);                                        // Calling enqueue method for adding token with it's new value
                   return;
               }
           }
        }
    }
}
