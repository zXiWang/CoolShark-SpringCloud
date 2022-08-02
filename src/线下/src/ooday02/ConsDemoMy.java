package ooday02;

public class ConsDemoMy {
    public static void main(String[] args) {
        StudentMy zs = new StudentMy("李天昊", 123, "广东省肇庆市四会市大沙镇");
        StudentMy ls = new StudentMy("袁慧嘉", 222, "海南省省直辖县级行政区划万宁市南桥镇");
        zs.sayHi();
        ls.sayHi();
    }
}