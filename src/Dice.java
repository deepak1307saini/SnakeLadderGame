import java.security.SecureRandom;

public class Dice {
    private Constant constants =new Constant();
    SecureRandom random=new SecureRandom();
    public int diceRoll(){
        int minDiceVal= constants.getMinDicValue();
        int maxDiceVal= constants.getMaxDicValue();
        return random.nextInt(maxDiceVal-minDiceVal)+minDiceVal;
    }
}
