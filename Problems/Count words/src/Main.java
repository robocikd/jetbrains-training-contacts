import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        final String[] split = reader.readLine().trim().split("\\s+");
        reader.close();
        if (split.length > 0 && !"".equals(split[0])) {
            System.out.println(split.length);
        } else {
            System.out.println("0");
        }
    }

}
