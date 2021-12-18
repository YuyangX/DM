import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args){

        String str = "2021-12-31";
//
        if (test1(str)){
            System.out.println("success");
        }else {
            System.out.println("E");
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
