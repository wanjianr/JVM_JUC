package com.douye.jvm;

class Person{
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
public class VariableDemo04 {
    private void add(int age) {
        age = 30;
    }

    private void changeName(Person p) {
        p.setName("xxx");
    }
    private void modify(String s) {
        s = "xxx";
    }
    public static void main(String[] args) {
        int age = 20;
        VariableDemo04 demo04 = new VariableDemo04();
        demo04.add(age);
        System.out.println(age);

        Person person = new Person("abc");
        demo04.changeName(person);
        System.out.println(person.getName());

        String str = "abc";  // 字符串存入常量池，当常量池中没有相应变量，则该引用会指向一个新的字符串
        demo04.modify(str);
        System.out.println(str);

        String str1 = new String("ABC");
        demo04.modify(str1);
        System.out.println(str1);

    }
}
