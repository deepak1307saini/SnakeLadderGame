import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Constant costants = new Constant();

        int ladderCount = costants.getLadderCount();
        int snakeCount = costants.getSnakeCount();
        int PlayerCount = costants.getMinPlayerCount();
        int cellCount = costants.getCellCount();


        Scanner input = new Scanner(System.in);
        System.out.println("-----------Snake Ladder Game----------");

        System.out.println(".....Enter the cells count in board.....");
        int inputCells=inputUser();
        if (inputCells>cellCount)
            cellCount=inputCells;

        System.out.println(".....Enter how many ladder(🪜) you want.....");
        int inputLadderCount=inputUser();
        if (inputLadderCount<=cellCount/4)
            ladderCount=cellCount/4;

        System.out.println(".....Enter how may snakes(🐍) you want.....");
        int inputSnakeCount= inputUser();
        if (inputSnakeCount<=cellCount/4)
            snakeCount=inputSnakeCount;

        System.out.println("--------Enter players(👶) count------");
        int inputPlayerCount= inputUser();
        if (inputPlayerCount>costants.getMaxPlayerCount())
            PlayerCount=costants.getMaxPlayerCount();
        else if (inputPlayerCount>PlayerCount) {
            PlayerCount=inputPlayerCount;
        }

        Game game = new Game(cellCount, ladderCount, snakeCount, PlayerCount);

        System.out.println();
        System.out.println("------Start the Game by Enter 'start'(🎬)-----");
        String startGame = input.next();

        if (startGame.equals("start") || startGame.equals("'start'"))
            System.out.println("Winner 🏆 : " + game.play().getName());
        else
            System.out.println("exit...");

    }

    public static int inputUser() {
        Scanner input = new Scanner(System.in);
        int inputValue=0;
        while(true) {
            try {
                inputValue = input.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Enter a int value ");
                input.nextLine();
            }
        }
        return inputValue;
    }



}
