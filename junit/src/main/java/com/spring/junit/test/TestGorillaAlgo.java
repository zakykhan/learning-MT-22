package com.spring.junit.test;

import java.util.ArrayList;
import java.util.List;

public class TestGorillaAlgo {

    public static void main(String[] args) {
        List<String> passwords = new ArrayList<>();
        passwords.add("P@SS6Word");
        passwords.add("Passwords");
        passwords.add("PassWord0");
        String checkString = "4dro9";

        String correctPassword = pickValidatedPassword(passwords, checkString);

        System.out.println(correctPassword);
    }

    private static String pickValidatedPassword(List<String> passwords, String checkString) {

        for (String password : passwords) {
            int upper = 0;
            int temp = 0;

            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
                    upper++;
                }

                if (Character.isDigit(password.charAt(i))){
                    temp = temp + Character.getNumericValue(password.charAt(i));
                }
            }
            String lastThreeChar = password.substring(password.length() - 3);
            String checkThreeChar = checkString.substring(1,4);
            StringBuilder str = new StringBuilder();
            str.append(checkThreeChar);

           // int value = Integer.parseInt(password.replaceAll("[^0-9]", ""));
            int lastNum = Integer.parseInt(checkString.substring(checkString.length() - 1));

            int sum = Integer.parseInt(String.valueOf(checkString.charAt(0)));
            if (upper == sum && lastThreeChar.equals(String.valueOf(str.reverse())) && temp == lastNum) {
                return password;
            } else {
                return "validation failed..!!";
            }
        }
        return null;
    }
}
