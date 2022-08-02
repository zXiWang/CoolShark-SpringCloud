package apiday02;

public class ReplaceDemo {
    public static void main(String[] args) {
        String line = "abc123def456ghi789";
        line = line.replaceAll("[0-9]+", "*");
        System.out.println(line);
    }
}
