package com.coding.google;

public class P420 {

    //Question link : https://leetcode.com/problems/strong-password-checker/?envType=featured-list&envId=top-google-questions?envType=featured-list&envId=top-google-questions
        public boolean isPasswordStrong(String password){
            if ( password.length() < 6 || password.length() > 20){
                return false;
            }

            boolean lowerCasePresent = (password.charAt(0) >= 'a' && password.charAt(0) <= 'z') || (password.charAt(1) >= 'a' && password.charAt(1) <= 'z') ;
            boolean upperCasePresent = (password.charAt(0) >= 'A' && password.charAt(0) <= 'Z') || (password.charAt(1) >= 'A' && password.charAt(1) <= 'Z') ;
            boolean oneDigitPresent = (password.charAt(0) >= '0' && password.charAt(0) <= '9') || (password.charAt(1) >= '0' && password.charAt(1) <= '9') ;
            boolean threeRepCharacters = false;

            for(int i = 2 ; i < password.length() && !threeRepCharacters ; i++){
                lowerCasePresent = lowerCasePresent || password.charAt(i) >= 'a' && password.charAt(i) <= 'z';
                upperCasePresent = upperCasePresent || (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z');
                oneDigitPresent = oneDigitPresent ||  (password.charAt(i) >= '0' && password.charAt(i) <= '9');
                threeRepCharacters = threeRepCharacters || ((password.charAt(i-2) == password.charAt(i-1) && password.charAt(i-1) == password.charAt(i)));
            }

            return lowerCasePresent && upperCasePresent && oneDigitPresent && !threeRepCharacters;
        }

        public int strongPasswordChecker(String password) {
            /*
                First thing first. Check if password is strong return 0;
            */

            if ( isPasswordStrong(password)){
                return 0;
            }

            // Otherwise we have three operations
            /*
                1. Insert - We will insert only if length is less than 6
                2. Delete -
                    length > 20 : length - 20 : deletion is anyway required.
                    length <= 20 : We can either delete the character or replace it, such that it helps make the password stronger
                3. Replace - Replace the character such that it helps make the password stronger (no length change)

                case 1: -- Length < 6
                case 2: -- Length >= 6 && Length <= 20
                case 3: -- Length > 20

             */



            return 10;
        }

    public static void main(String[] args) {
        P420 solution = new P420();
        String [] passwords = {"Baaabb0", "Baaba0", "a", "1337C0d3", "aA1"};
        for (String password : passwords) {
            System.out.println("Strong password " + password + " : " + solution.isPasswordStrong(password));
        }

    }
}
