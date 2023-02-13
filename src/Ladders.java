import java.security.SecureRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Ladders {
    private int ladderCount;

    private static Map<Integer, Integer> ladders = new HashMap<>();

    private static Set<Integer> ladderStart=new HashSet<>();
    private static Set<Integer> ladderEnd=new HashSet<>();
    SecureRandom secureRandom = new SecureRandom();


    public void createLadders(int cells,int ladderCount){
        this.ladderCount=ladderCount;

        int min=2;
        int max=cells-20;

        for (int i = 0; i < ladderCount; i++) {
            int start=0;
            int dest=0;

            while(true){
                start = secureRandom.nextInt(max - min) + min;
                if (!ladderEnd.contains(start) && !ladderStart.contains(start)) {
                    ladderStart.add(start);
                    break;
                }
            }
            while (true){
                dest = secureRandom.nextInt(cells-4-(start+10)) + start+10;
                if(!ladderEnd.contains(dest) && !ladderStart.contains(dest)){
                    ladderEnd.add(dest);
                    break;
                }
            }

           ladders.put(start,dest);
        }
    }

    public boolean ladderStartContain(int k)
    {
        return ladderStart.contains(k);
    }

    public boolean ladderDestContain(int k)
    {
        return ladderEnd.contains(k);
    }

    public int getLadderValue(int start){
        return ladders.get(start);
    }
}
