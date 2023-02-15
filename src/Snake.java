import java.security.SecureRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Snake {
    private int snakeCount;

    private Map<Integer, Integer> snakes = new HashMap<>();

    private Set<Integer> snakeStart;
    private Set<Integer> snakeEnd ;
    private Constant constants;


    Snake(int cells,int snakeCount,Ladder ladders){
        SecureRandom secureRandom = new SecureRandom();
        snakeStart =new HashSet<>();
        snakeEnd =new HashSet<>();
        this.snakeCount=snakeCount;
        constants=new Constant();

        int start=0;
        int dest=0;
        int minEndPos= constants.getMinSnakeEndPosition();
        int minStartPos= constants.getMinSnakeStartPosition();
        int maxStartPos=cells- constants.getDifferenceToLastCell();
        int minSlip= constants.getMinSnakeCoverDis();

        for (int i = 0; i < snakeCount; i++){

            while(true){
                start = secureRandom.nextInt(maxStartPos - minStartPos) + minStartPos;;
                if (!snakeEnd.contains(start) && !snakeStart.contains(start) && !ladders.ladderStartContain(start) && !ladders.ladderDestContain(start)) {
                    snakeStart.add(start);
                    break;
                }
            }
            while (true){
                dest = secureRandom.nextInt(start-minSlip - minEndPos) + minEndPos;
                if(!snakeEnd.contains(dest) && !snakeStart.contains(dest) && !ladders.ladderStartContain(dest)){
                    snakeEnd.add(dest);
                    break;
                }
            }

            snakes.put(start,dest);
        }
    }

    public boolean snakeStartContain(int cell)
    {
        return snakeStart.contains(cell);
    }

    public boolean snakeDestContain(int cell)
    {
        return snakeEnd.contains(cell);
    }

    public int getSnakeValue(int start){
        return snakes.get(start);
    }
}
