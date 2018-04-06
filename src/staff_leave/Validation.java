
package staff_leave;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation 
{
    public static boolean email_validate(String email)
    {
        boolean status = false;
        String email_add = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        Pattern pattern = Pattern.compile(email_add);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches())
        {
            status = true;
        }
        else
        {
            status = false;
        }
        return status;
    }
    public static boolean name_validate(String fname, String lname)
    {
        boolean flag = false;
        String names = "[A-Za-z]+\\.?";
        Pattern pattern = Pattern.compile(names);
        Matcher matcher = pattern.matcher(fname);
        Matcher matcher1 = pattern.matcher(lname);
        
        if(matcher.matches() && matcher1.matches())
        {
            flag = true;
        }
        else
        {
            flag = false;
        }
        return flag;
    }
    
}
