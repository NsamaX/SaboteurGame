import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Card {
    private String id;
    private String name;
    private String ability;
    private int numOfCardsInDeck;
    // Create card
    public Card(String id, String name, String ability, int numOfCardsInDeck) {
        this.id = id;
        this.name = name;
        this.ability = ability;
        this.numOfCardsInDeck = numOfCardsInDeck;
    }
    // Getter
    public String getCardId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getAbility(){
        return ability;
    }
    public int getNumOfCardsInDeck(){
        return numOfCardsInDeck;
    }
}

class Deck {
    private List<Card> cards = new ArrayList<>();
    public Deck() {
        initializeDeck();
    }
    private void initializeDeck() {
        // Add cards to the deck with their details
        cards.add(new Card("001", "Route 0", "?", 3));
        cards.add(new Card("002", "Route 1", "?", 3));
        cards.add(new Card("003", "Route 2", "?", 3));
        cards.add(new Card("004", "Route 3", "?", 3));
        cards.add(new Card("005", "Route 4", "?", 3));
        cards.add(new Card("006", "Route 5", "?", 3));
        cards.add(new Card("007", "Route 6", "?", 3));
        cards.add(new Card("008", "Route 7", "?", 3));
        cards.add(new Card("009", "Route 8", "?", 3));
        cards.add(new Card("010", "Route 9", "?", 3));
        cards.add(new Card("011", "Route 10", "?", 3));
        cards.add(new Card("012", "Route 11", "?", 3));
        cards.add(new Card("013", "Route 12", "?", 3));
        cards.add(new Card("014", "Route 13", "?", 3));
        cards.add(new Card("015", "Route 14", "?", 3));
        cards.add(new Card("016", "Route 15", "?", 3));
        cards.add(new Card("017", "Route 16", "?", 3));
        cards.add(new Card("002", "Route 17", "?", 3));
        cards.add(new Card("002", "Route 18", "?", 3));
        cards.add(new Card("002", "Route 19", "?", 3));
        cards.add(new Card("002", "Rockfall", "?", 3));
        cards.add(new Card("002", "Broken shovel", "?", 3));
        cards.add(new Card("002", "Find a way out", "?", 3));
        cards.add(new Card("002", "Get a Fire Club", "?", 3));
        cards.add(new Card("002", "Get a new shovel", "?", 3));
        cards.add(new Card("002", "Get a new shovel and a fire club", "?", 1));
        cards.add(new Card("002", "Get a new shovel and find a way out", "?", 1));
        cards.add(new Card("002", "Get the Fire Club and find the exit", "?", 1));
        cards.add(new Card("002", "Blast the way", "?", 3));
        cards.add(new Card("002", "Found map", "?", 3));
        // Add more cards with their details
        // ...
    }

    String drawCard() {
        if (cards.isEmpty()) {
            return "No cards left in the deck.";
        }

        Random random = new Random();
        int randomIndex = random.nextInt(cards.size());
        Card selectedCard = cards.remove(randomIndex);
        
        // Return the name of the drawn card
        return selectedCard.getName();
    }
}