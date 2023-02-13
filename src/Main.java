import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String ladderCount;
        String snakeCount;
        String PlayerCount;
        String cellCount;



        Scanner input = new Scanner(System.in);
        System.out.println("-----------Snake Ladder Game----------");

        System.out.println(".....Enter the cells count in board.....");
        cellCount= String.valueOf(input.next());

        System.out.println(".....Enter how many ladder(🪜) you want.....");
        ladderCount= String.valueOf(input.next());


        System.out.println(".....Enter how may snakes(🐍) you want.....");
        snakeCount= String.valueOf(input.next());


        System.out.println("--------Enter players(👶) count------");
        PlayerCount=String.valueOf(input.next());

        Game game=new Game(cellCount,ladderCount,snakeCount,PlayerCount);


        System.out.println();
        System.out.println("------Start the Game by Enter 'start'(🎬)-----");
        String startGame=input.next();

        if(startGame.equals("start")||startGame.equals("'start'"))
            System.out.println("Winner 🏆 : "+game.play().getName());
        else
            System.out.println("exit...");

    }
}
