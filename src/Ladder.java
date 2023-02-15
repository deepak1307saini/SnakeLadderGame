import java.lang.*;

import java.util.*;
import java.security.SecureRandom;

public class Ladder {
    private int ladderCount;

    private Map<Integer, Integer> ladders = new HashMap<>();

    private Set<Integer> ladderStart;
    private Set<Integer> ladderEnd;
    SecureRandom secureRandom = new SecureRandom();
    Constant constants;
    public Ladder(){}

    public Ladder(int cells,int ladderCount){
        constants=new Constant();
        ladderStart=new HashSet<>();
        ladderEnd=new HashSet<>();

        this.ladderCount=ladderCount;
        int minLadderStartPos= constants.getMinLadderStartPosition();
        int maxLadderStartPos=cells- constants.getMaxStartLadderDiffToLastCell();
        int maxDestPoint=cells- constants.getDifferenceToLastCell();
        int minDestToCover= constants.getMinLadderCoverDist();
        int start=0;
        int dest=0;

        for (int i = 0; i < ladderCount; i++) {

            while(true){
                start = secureRandom.nextInt(maxLadderStartPos - minLadderStartPos) + minLadderStartPos;
                if (!ladderEnd.contains(start) && !ladderStart.contains(start)) {
                    ladderStart.add(start);
                    break;
                }
            }
            while (true){
                dest = secureRandom.nextInt(maxDestPoint-(start+minDestToCover)) + (start+minDestToCover);
                if(!ladderEnd.contains(dest) && !ladderStart.contains(dest)){
                    ladderEnd.add(dest);
                    break;
                }
            }

           ladders.put(start,dest);
        }
    }

    public boolean ladderStartContain(int cell)
    {
        return ladderStart.contains(cell);
    }

    public boolean ladderDestContain(int cell)
    {
        return ladderEnd.contains(cell);
    }

    public int getLadderValue(int start){
        return ladders.get(start);
    }
}
