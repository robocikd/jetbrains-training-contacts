import java.io.InputStream;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        int i = inputStream.read();
        while (i != -1) {
            System.out.print((i));
            i = inputStream.read();
        }
        inputStream.close();
    }
}
