package com.ukgfreak;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 3/4/22 IST 5:48 PM
 */
public class Question1 {
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    private String lastName;

    public Question1(String fn, String ln) {
        this.firstName = fn;
        this.lastName = ln;
    }
public String toString()  {
        return "First Name:" + this.firstName + ", ln " + this.lastName;
}
    public static void main(String[] args) {
        MyAdd<Number> myadd = new MyAdd<>();
        myadd.add(new Integer(1));
        myadd.add(new Double(1.0));
    }
}

class A {
    {
        System.out.println("A");
    }
}
class B extends A {
    {
        System.out.println("B");
    }
}
class C extends B {
    {
        System.out.println("C");
    }
}
