public class Numeric {
    public static Boolean isNumeric(String string){
        try {
            Integer num = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
