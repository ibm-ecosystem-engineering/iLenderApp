package com.ilender.micro.util;


import java.util.*;

public class Test {

    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);

        System.out.print("Enter Mark 1 : ");
        int m1 = sc.nextInt();
        System.out.print("Enter Mark 2 : ");
        int m2 = sc.nextInt();
        System.out.print("Enter Mark 3 : ");
        int m3 = sc.nextInt();

        int total = m1 + m2 + m3;
        float avg = total / 3;


    }
}
