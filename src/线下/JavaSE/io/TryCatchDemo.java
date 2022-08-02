package io;

public class TryCatchDemo {
    public static void main(String[] args) {
        String message=null;
        String str="";
        try {
//            System.out.println(message.length());
            System.out.println(str.charAt(0));
        }catch (NullPointerException|IndexOutOfBoundsException e) {
            System.out.println(66);
        }
//        catch (NullPointerException e) {
//            message="555";
//        }catch (IndexOutOfBoundsException e) {
//            System.out.println(666);
//        }

    }
}
