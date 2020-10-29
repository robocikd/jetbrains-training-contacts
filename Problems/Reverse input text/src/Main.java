import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        char[] chars = reader.readLine().toCharArray();
        for(int i = chars.length-1; i>=0; i--){
            System.out.print(chars[i]);
        }
        reader.close();
    }
}
