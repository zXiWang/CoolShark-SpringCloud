package ooday04;

//重写的演示
public class OverrideDemo {
    public static void main(String[] args) {

    }
}

//超类大，派生类小---------爸爸大，儿子小
class Coo {
    void show() {
    }

    double test() {
        return 0.0;
    }

    Student say() {
        return null;
    }

    Person sayHi() {
        return null;
    }
}

class Doo extends Coo {
    //int show(){ return 1; } //编译错误，void时必须相等
    //int test(){ return 0; } //编译错误，基本类型时必须相等
    //Person say(){ return null; } //编译错误，引用类型时必须小于或等于
    Student sayHi() {
        return null;
    } //正确，Student小于Person
}

















