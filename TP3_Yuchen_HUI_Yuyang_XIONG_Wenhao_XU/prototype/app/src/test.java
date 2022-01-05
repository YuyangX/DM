import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args){

        String str = "21211";
        System.out.println(isNum(str));
        System.out.println(verifierChiffre(5,str));

    }

    public static boolean verifierChiffre(int numChiffre,String entry){
        if (!isNum(entry)){
            return false;
        }
        if (entry.length()!=numChiffre){
            return false;
        }
        return true;
    }

    public static boolean isNum(String str){
        try {
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean test1(String str){
        String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
        Pattern pattern = Pattern. compile(regex);
        Matcher m = pattern.matcher(str);
        boolean dateFlag = m.matches();
        if (!dateFlag) {
            System.out.println("Error");
            return false;
        }
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd" ) ;
        formatter.setLenient(false);
        try{
            Date date = formatter.parse(str);
            System.out.println(date);
            return true;
        }catch(Exception e){
            System.out.println("Error");
            return false;
        }
    }
}
