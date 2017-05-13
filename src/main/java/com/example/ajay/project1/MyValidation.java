package com.example.ajay.project1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ajay on 17-01-2017.
 */

public class MyValidation {

    static boolean  isEmpty(String s)
    {
        if(s.equals(""))
        {
            return true;
        }
        return false;
    }
    static boolean isValidEmail(String email)
    {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    static boolean isValidPassword(String pass)
    {
        if(pass!=null && pass.length()>6)
        {
            return true;
        }
        return false;
    }

}
