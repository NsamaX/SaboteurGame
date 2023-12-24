import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Player {
    // To store player names & cards
    List<String> player_names = new ArrayList<>();
    Map<String, List<String>> playerCards = new HashMap<>(); 
    List<Boolean> saboteurs_list = new ArrayList<>();
    int saboteurs = 0;
    // Start the game and assign roles
    Player(List<String> players) {
        player_names.addAll(players);
        for (String playerName : players) {
            // Initialize each player's card list
            playerCards.put(playerName, new ArrayList<>()); 
        }
        // Check game role
        int number_players = players.size();
        if (number_players < 3 || number_players > 10) {
            System.out.println("The number of players doesn't meet the conditions. Can't play this game");
        } else {
            int saboteurs;
            if (number_players < 5) {
                saboteurs = 1;
            } else if (number_players < 7) {
                saboteurs = 2;
            } else if (number_players < 10) {
                saboteurs = 3;
            } else {
                saboteurs = 4;
            }
            List<Boolean> assignedSaboteurs = new ArrayList<>(Collections.nCopies(number_players, false));
            for (int i = 0; i < saboteurs; i++) {
                assignedSaboteurs.set(i, true);
            }
            Collections.shuffle(assignedSaboteurs);
            for (int i = 0; i < player_names.size(); i++) {
                String role = assignedSaboteurs.get(i) ? "Saboteur" : "Digger";
                System.out.println(player_names.get(i) + ": " + role);
            }
            playGame();
        }
    }

    public void playGame() {
        System.out.println("\nGame start\n**********");
        // Prepare the need for the game
        Game_Map gm = new Game_Map();
        Deck deck = new Deck();
        Scanner scanner = new Scanner(System.in);
        String drawnCard;
        // Draw 5 cards for each player
        for(int i = 0; i < 5; i++){
            for (String player : player_names) {
                drawnCard = deck.drawCard();
                storeCard(player, drawnCard);
            }
        }
        gm.showMap();

        for (String player : player_names) {
            System.out.println("\nPlayer: " + player);
            System.out.println("Select action:");
            System.out.print(
                """
                0: Show cards
                1: Play path card
                2: Play action card
                3: Discard & Draw a new card
                5: Exit game
                
                """
            );
            int action = -1; // Initialize action outside the loop

            while (action != 5) { // Loop until action is 5
                action = scanner.nextInt();

                if (action == 0) {
                    showCards(player);
                } else if (action == 1) {
                    gm.showMap();
                    System.out.println("Select area by row & col like: KK = s");
                    String point = scanner.next();
                    if (gm.checkPath(point.charAt(0), point.charAt(1))) {
                        System.out.println("Select path card");
                        action = scanner.nextInt();
                        useCardAndDraw(player, action, deck);
                    } else {
                        System.out.println("Can't do that");
                    }
                } else if (action == 2) {
                    System.out.println("Action card");
                } else if (action == 3) {
                    System.out.println("Discard a card & Draw a new card");
                    action = scanner.nextInt();
                    discardAndDraw(player, action, deck);
                } else if (action == 5) {
                    break; // Exit the loop if action is 5
                } else {
                    System.out.println("Invalid action number");
                }
            }
        }
        System.out.println("Game ended. Thanks for playing!");
    }

    // Method to store a card for a player
    public void storeCard(String playerName, String card) {
        List<String> cards = playerCards.getOrDefault(playerName, new ArrayList<>());
        cards.add(card);
        playerCards.put(playerName, cards);
    }

    // Method to display cards held by a specific player
    public void showCards(String playerName) {
        List<String> cards = playerCards.getOrDefault(playerName, new ArrayList<>());
        System.out.println(playerName + " has: " + cards);
    }
    
    // Method to check if a player has reached the maximum card limit
    private boolean hasMaxCards(String playerName) {
        return playerCards.getOrDefault(playerName, new ArrayList<>()).size() >= 5;
    }
    
    // Method for a player to use a card by its index in hand and draw a new one
    public void useCardAndDraw(String playerName, int cardIndex, Deck deck) {
        List<String> cards = playerCards.getOrDefault(playerName, new ArrayList<>());

        if (cardIndex < 0 || cardIndex >= cards.size()) {
            System.out.println("Invalid card index for " + playerName);
            return;
        }

        String cardToUse = cards.get(cardIndex);

        if (hasMaxCards(playerName)) {
            System.out.println(playerName + " already has the maximum number of cards (5) in hand.");
            return;
        }

        // Using the card
        System.out.println(playerName + " uses the card: " + cardToUse);
        cards.remove(cardIndex); // Remove the used card

        // Drawing a new card
        String drawnCard = deck.drawCard();
        if (!drawnCard.equals("No cards left in the deck.")) {
            System.out.println(playerName + " draws a new card: " + drawnCard);
            cards.add(drawnCard); // Adding the drawn card to player's hand
            playerCards.put(playerName, cards);
        } else {
            System.out.println("No cards left in the deck.");
        }
    }
    
    // Method for a player to discard a card by its index in hand and draw a new one
    public void discardAndDraw(String playerName, int cardIndex, Deck deck) {
        List<String> cards = playerCards.getOrDefault(playerName, new ArrayList<>());

        if (cardIndex < 0 || cardIndex >= cards.size()) {
            System.out.println("Invalid card index for " + playerName);
            return;
        }

        String cardToDiscard = cards.get(cardIndex);

        // Discarding the card
        System.out.println(playerName + " discards the card: " + cardToDiscard);
        cards.remove(cardIndex); // Remove the discarded card

        // Drawing a new card
        String drawnCard = deck.drawCard();
        if (!drawnCard.equals("No cards left in the deck.")) {
            System.out.println(playerName + " draws a new card: " + drawnCard);
            cards.add(drawnCard); // Adding the drawn card to player's hand
            playerCards.put(playerName, cards);
        } else {
            System.out.println("No cards left in the deck.");
        }
    }
}