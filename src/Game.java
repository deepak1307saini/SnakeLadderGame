import java.util.*;

public class Game {
    private Player winner;
    private int minCells=100;
    private int ladderCount=10;
    private int snakeCount=10;
    private int playerCount=2;
    private Ladders ladders;
    private Snakes snakes;

    private Map<Player,Integer> playerPosition=new HashMap<>();

    private ArrayDeque<Player> players=new ArrayDeque<>();
    Scanner input=new Scanner(System.in);


    public Game(String minCells, String ladderCount, String snakeCount, String playerCount) {
        ladders=new Ladders();
        snakes =new Snakes();
        if(Numeric.isNumeric(minCells) && Integer.parseInt(minCells)>this.minCells){
            this.minCells=Integer.parseInt(minCells);
        }

        if (Numeric.isNumeric(ladderCount))
        {
            if (Integer.parseInt(ladderCount)<=this.minCells/4) {
                this.ladderCount=Integer.parseInt(ladderCount);
            }
            else
                this.ladderCount=this.minCells/10;
        }

        ladders.createLadders(this.minCells,this.ladderCount);
        if (Numeric.isNumeric(snakeCount)){

            if (Integer.parseInt(snakeCount)<=this.minCells/4) {
                this.snakeCount=Integer.parseInt(snakeCount);
            }
            else
                this.snakeCount=this.minCells/10;
        }
        snakes.createSnakes(this.minCells,this.snakeCount);

        if (Numeric.isNumeric(playerCount)){
            int PC=Integer.parseInt(playerCount);
            if (PC>6){
                this.playerCount=6;
            } else if (PC<2) {
                this.playerCount=2;
            }
            else this.playerCount=PC;

        }

        createPlayers(this.playerCount);
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
        System.out.println("\nCells = "+minCells+ " : Ladders = "+ladderCount+" : Snakes = "+snakeCount);
        while (true)
        {
            System.out.println("\n---------------------------------------");
            System.out.println(players.peek().getName()+"'s turn");
            System.out.println("Press 'r' to roll Dice (🎲)");

            if(!input.next().equals("exit"))
            {
                System.out.println("\n");
                int random= new Random().nextInt(7);
                int move= random==0?1:random;

                movePlayer(players.peek(),move);

                if (playerPosition.get(players.peek())==minCells)
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

        System.out.println("You rolled "+move);
        if(playerPosition.get(player)+move>minCells)
        {
            System.out.println("You rolled more than you required to reach 100!");
            System.out.println("You can't move ahead!!");
              return;
        }

        if (ladders.ladderStartContain(playerPosition.get(player)+move))
        {

            System.out.println("😲! you landed on a ladder!!!");
            System.out.println("You climb "+playerPosition.get(player)+ " to "+ladders.getLadderValue(playerPosition.get(player)+move)+" !!");
            playerPosition.replace(player,ladders.getLadderValue(playerPosition.get(player)+move));
            
            return;
        }

        if (snakes.snakeStartContain(playerPosition.get(player)+move))
        {

            System.out.println("👎 You landed on a snake!!!");
            System.out.println("You slip "+playerPosition.get(player)+" to "+snakes.getSnakeValue(playerPosition.get(player)+move)+" !!");
            playerPosition.replace(player,snakes.getSnakeValue(playerPosition.get(player)+move));

            return;
        }


        playerPosition.replace(player,playerPosition.get(player)+move);
        
        return;
    }
}






















