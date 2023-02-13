import java.security.SecureRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Snakes {
    private int snakeCount;

    private static Map<Integer, Integer> snakes = new HashMap<>();

    private static Set<Integer> snakeStart =new HashSet<>();
    private static Set<Integer> snakeEnd =new HashSet<>();
    SecureRandom secureRandom = new SecureRandom();


    public void createSnakes(int cells,int snakeCount){
        this.snakeCount=snakeCount;

        Ladders ladders=new Ladders();
        for (int i = 0; i < snakeCount; i++) {
            int start=0;
            int dest=0;

            int min=20;
            int max=cells-2;
            while(true){
                start = secureRandom.nextInt(max - min) + min;;
                if (!snakeEnd.contains(start) && !snakeStart.contains(start) && !ladders.ladderStartContain(start) && !ladders.ladderDestContain(start)) {
                    snakeStart.add(start);
                    break;
                }
            }
            while (true){
                dest = secureRandom.nextInt(start-10 - 2) + 2;
                if(!snakeEnd.contains(dest) && !snakeStart.contains(dest) && !ladders.ladderStartContain(dest)){
                    snakeEnd.add(dest);
                    break;
                }
            }

            snakes.put(start,dest);
        }
    }

    public boolean snakeStartContain(int k)
    {
        return snakeStart.contains(k);
    }

    public boolean snakeDestContain(int k)
    {
        return snakeEnd.contains(k);
    }

    public int getSnakeValue(int start){
        return snakes.get(start);
    }
}
