package 项目.danei.src.first.oo.day02;

public class OverloadDemo {
    public static void main(String[] args) {
        Boo b = new Boo();
        b.show();
    }
}

class Aoo{
    void show() {

    }
}

class Boo extends Aoo{
    void show(int a) {

    }
}