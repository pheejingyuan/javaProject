
public class Test {
    public static void main(String[] args) {
        String query = String.format("insert into user values(%d,%s,%s,%s,%s);", 1,
                "abcdef", "getName", "getPhoneNumber", "Student");

                System.out.println(query);
    }
}
