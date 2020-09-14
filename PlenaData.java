import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class CountIndex {
    
    int count;
    int index;
    
    // first occurence of character in a string
    public CountIndex(int index) {
        this.count = 1;
        this.index = index;
    }
    
    // update the count
    public void incrementCount () {
        this.count++;
    }
    
    
    
}

public class PlenaData {
    
    static final int MAP_SIZE = 256;
    static String inputString = "";
    static String nonRepeatingOutputString = "";
    static String repeatingOutputString = "";
    static Map<Character, CountIndex> hashMap  = new LinkedHashMap<>(MAP_SIZE);
    
    public static String getString() {
        Scanner input = new Scanner(System.in);
        System.out.println("1. Enter a string");
        System.out.println("2. Exit"); 
        System.out.print("Enter 1 or 2: ");  
        int option = input.nextInt();
        if(option > 1) {
           System.out.print("Program Exited!!");
           System.exit(0);
        } 
        System.out.println("Please enter the input string:");
        input.nextLine();
        String str = input.nextLine();
        return str;
    } 
    
     public static int printFirstNonRepeatingCharacterAndNewString(String s) {
        if(s.length() == 0) {
            return -1;
        } else {
            getCharactersCount(s);
            int result = Integer.MAX_VALUE;
            boolean first = true;
            for(Map.Entry<Character,CountIndex> map : hashMap.entrySet()) {
                if(map.getValue().count == 1) { 
                    result = map.getValue().index;
                    if(first) {
                        System.out.println("The First Non-Repeating Character is: " + inputString.charAt(result));
                        first = false;
                    }
                    nonRepeatingOutputString += inputString.charAt(result);
                } else {
                    int i = map.getValue().index;
                    for (int c=0; c < map.getValue().count; c++) {
                        if( c == 0 && i == 0) {
                            repeatingOutputString += inputString.charAt(i);
                        } else {
                            repeatingOutputString += Character.toLowerCase(inputString.charAt(i));
                        }
                    }
                }
                System.out.println(inputString.charAt(map.getValue().index) + ":" + map.getValue().count );
            }
            
            if( nonRepeatingOutputString != "") {
                String newString = nonRepeatingOutputString + repeatingOutputString;
                System.out.println("\n New string generated: \"" + newString + "\"");
            }  
            
            return result;
            
            
        }
    }
   
    public static void getCharactersCount(String s) {
        for(int i = 0; i < s.length(); i++ ) {
            
          if(hashMap.containsKey(s.charAt(i))) {
            // increment occurence count  
            hashMap.get(s.charAt(i)).incrementCount();
          } else {
            // first occurence of character   
             if(Character.isUpperCase(s.charAt(i))) {
                // System.out.print("HI"+ Character.toLowerCase(s.charAt(i)));
                hashMap.put(Character.toLowerCase(s.charAt(i)), new CountIndex(i));  
             }  else hashMap.put(s.charAt(i), new CountIndex(i));   
          }
        }
    }
   
   public static void PlenaDataAssesment() {
       try {
            inputString = getString();
            int result = printFirstNonRepeatingCharacterAndNewString(inputString); 
            if(result == Integer.MAX_VALUE ) {
                System.out.println("\nEmpty String or All the characterers in the String \"" + inputString + "\" are repeated more than once. Try Again!!\n");
            } else {
                nonRepeatingOutputString = "";
            }
            inputString = "";
            repeatingOutputString = "";
            hashMap.clear();
            PlenaDataAssesment();
           
        } catch (Exception e) {
            System.out.println("Execption caugtht : " + e);
            System.exit(0);
        }
    }

    public static void main(String []args){
       PlenaDataAssesment();
    }
    
}
