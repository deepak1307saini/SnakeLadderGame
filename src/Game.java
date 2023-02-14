import java.util.*;

public class Game {
    private Player winner;
    private int cellCount =100;
    private int ladderCount=10;
    private int snakeCount=10;
    private int playerCount=2;
    private Ladder ladders;
    private Snake snakes;
    private Dice dice;

    private Map<Player,Integer> playerPosition;

    private ArrayDeque<Player> players;
    Scanner input=new Scanner(System.in);


    public Game(int CellCount, int ladderCount, int snakeCount, int playerCount) {
        playerPosition=new HashMap<>();
        players=new ArrayDeque<>();
        ladders=new Ladder(CellCount,ladderCount);
        snakes =new Snake(CellCount,snakeCount,ladders);
        dice=new Dice();

        this.cellCount=CellCount;
        this.ladderCount=ladderCount;
        this.snakeCount=snakeCount;
        this.playerCount=playerCount;

        createPlayers(playerCount);
    }

    private void createPlayers(int playerCount){
        System.out.println(".....Enter "+playerCount+" players name......");

        for (int i = 0; i < playerCount; i++) {
            Player player=new Player();
            player.setName(input.next());
            addPlayer(player);
        }
    }


    private void addPlayer(Player player){
        players.offer(player);
        playerPosition.put(player,0);
    }

    public Player play(){
        System.out.println("\nCells = "+ cellCount + " : Ladders = "+ladderCount+" : Snakes = "+snakeCount);
        while (true)
        {
            System.out.println("\n---------------------------------------");
            System.out.println(players.peek().getName()+"'s turn");
            System.out.println("Press 'r' to roll Dice (🎲)");

            if(!input.next().equals("exit"))
            {
                System.out.println("\n");
                int random= new Random().nextInt(7);
                int move= dice.diceRoll();

                movePlayer(players.peek(),move);

                if (playerPosition.get(players.peek())== cellCount)
                {
                    winner=players.peek();
                    return players.peek();
                }

                printPositions();

                if(move!=6){
                    players.offer(players.peek());
                    players.poll();
                }

            }
            else {
                   System.out.println("exit.....");
                   System.exit(0);
            }
        }
    }

    private void printPositions(){

        for (Player p:playerPosition.keySet()){
            System.out.println(p.getName()+" at position "+playerPosition.get(p));
        }

        System.out.println("---------------------------------------\n");
    }

    private void movePlayer(Player player, int move)
    {
        int newPos=playerPosition.get(player)+move;
        System.out.println("You rolled "+move);
        if(newPos> cellCount)
        {
            System.out.println("You rolled more than you required to reach 100!");
            System.out.println("You can't move ahead!!");
              return;
        }

        if (ladders.ladderStartContain(newPos))
        {

            System.out.println("😲! you landed on a ladder!!!");
            System.out.println("You climb "+playerPosition.get(player)+ " to "+ladders.getLadderValue(newPos)+" !!");
            playerPosition.replace(player,ladders.getLadderValue(newPos));
            
            return;
        }

        if (snakes.snakeStartContain(newPos))
        {

            System.out.println("👎 You landed on a snake!!!");
            System.out.println("You slip "+playerPosition.get(player)+" to "+snakes.getSnakeValue(newPos)+" !!");
            playerPosition.replace(player,snakes.getSnakeValue(newPos));

            return;
        }


        playerPosition.replace(player,newPos);
        
        return;
    }
}






















