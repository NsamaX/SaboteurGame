import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList(
            "player1", "player2", "player3"
        );
        Player player = new Player(names);
    }
}