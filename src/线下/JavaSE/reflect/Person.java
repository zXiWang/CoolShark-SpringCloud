package reflect;


@AutoRunClass
public class Person {

    @AutoRunClass
    private String name = "张三";
    private int age = 18;
    public Person(){}

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Person( String name, int age) {
        this.name = name;
        this.age = age;
    }

    @AutoRunMethod(4)
    public void sayHello(){
        System.out.println(name+"说:hello!");
    }


    public void watchTV(){
        System.out.println(name+":正在看电视");
    }
    public void sing(){
        System.out.println(name+":正在唱歌");
    }
    @AutoRunMethod
    public void playGame(){
        System.out.println(name+":正在打游戏");
    }

    public void say(String info){
        System.out.println(name+"说:"+info);

    }

    public void say(String info,int count){
        for(int i=1;i<=count;i++){
            System.out.println(name+"第"+i+"次说:"+info);
        }
    }
    private void heihei(){
        System.out.println("Heihei");
    }

}
