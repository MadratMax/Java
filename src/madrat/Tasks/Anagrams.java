package madrat.Tasks;

import java.util.HashMap;

public class Anagrams {

    public static boolean areAnagrams(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;

        HashMap<Character, Integer> ar1 = new HashMap<Character, Integer>();
        HashMap<Character, Integer> ar2 = new HashMap<Character, Integer>();

        for (int i = 0; i < str1.length(); i++) {
            if (ar1.containsKey(str1.charAt(i)))
                ar1.put(str1.charAt(i), ar1.get(str1.charAt(i)) + 1);
            else
                ar1.put(str1.charAt(i), 1);

            if (ar2.containsKey(str2.charAt(i)))
                ar2.put(str2.charAt(i), ar1.get(str2.charAt(i)) + 1);
            else
                ar2.put(str2.charAt(i), 1);
        }

        return ar1.equals(ar2);
    }
}
