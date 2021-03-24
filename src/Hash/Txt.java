
package Hash;

public class Txt {

    public static String append(String s1, String s2) {
//        System.out.println( s2.concat(s1) );
        return s2.concat(s1);
    }
    public static String prepend(String s1, String s2) {
//        System.out.println( new StringBuilder("").append(s1).append(s2).toString() );
        return new StringBuilder("").append(s1).append(s2).toString();
    }
}
