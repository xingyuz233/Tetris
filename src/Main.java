import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = Game.getInstance();
        game.initial(10,10);
        game.nextBlock();
        while(true) {
            if (game.isOver()) {
                System.out.println("Game over!");
                break;
            }
            game.print();

            String command = scanner.next();
            game.executeCommand(command);

        }
    }
}
