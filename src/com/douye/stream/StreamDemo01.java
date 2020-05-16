package com.douye.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class User{
    private Integer id;
    private String name;
    private int age;

    public User(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class StreamDemo01 {
    public static void main(String[] args) {
        User user1 = new User(1,"a",23);
        User user2 = new User(2,"b",24);
        User user3 = new User(3,"c",22);
        User user4 = new User(4,"d",28);
        User user5 = new User(6,"e",26);
        List<User> list = Arrays.asList(user1,user2,user3,user4,user5);
        list.stream().filter(user -> user.getId()%2==0).forEach(user -> System.out.println(user.toString()));
        System.out.println("-----------------------");
        list.stream().filter(user -> user.getId()%2==0).filter(user -> user.getAge()>25).forEach(user -> System.out.println(user.toString()));
        System.out.println("-----------------------");
        list.stream().filter(user -> user.getId()%2==0).filter(user -> user.getAge()>25).map(user -> user.getName().toUpperCase()).sorted((u1,u2)->u2.compareTo(u1)).forEach(user -> System.out.println(user.toString()));
        //demo0();
    }

    private static void demo0() {
        List<Integer> list1 = Arrays.asList(1,2,3);
        list1.stream().map(n -> {
            return n * 2;
        }).collect(Collectors.toList()).forEach(System.out::println);
    }
}
