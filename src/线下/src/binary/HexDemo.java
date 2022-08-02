package binary;

public class HexDemo {
    public static void main(String[] args) {
        int n = 0x2fd47b;
        int m = 0b0010_1111_1101_0100_0111_1011;
        System.out.println(Integer.toBinaryString(n) + "\n" + Integer.toBinaryString(m));
        System.out.println(n + "\n" + m);
//        0000_0001_0010_0011_0100_0101_0110_0111_1000_1001_1010_1011_1100_1101_1110_1111;
    }
}
