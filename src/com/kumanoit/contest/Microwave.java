package com.kumanoit.contest;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 2/5/22 IST 8:40 PM
 */
public class Microwave {
    public static void main(String[] args) {
//        System.out.println(minCostSetTime(0,1,2,76));
        System.out.println(minCostSetTime(5,15,20,365));
//        System.out.println(minCostSetTime(9,1,2,5840));
//        System.out.println(minCostSetTime(0,1,2,5841));
//        System.out.println(minCostSetTime(9,1,2,6000));
//        System.out.println(minCostSetTime(0,1,4,9));
    }

    public static int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int min = targetSeconds / 60;
        int sec = targetSeconds % 60;

        if (min > 99) {
            min--;
            sec += 60;
        }
        System.out.println("\n\nInput = " + targetSeconds);
        int[] text = new int[4];
        text[0] = min / 10;
        text[1] = min % 10;
        text[2] = sec / 10;
        text[3] = sec % 10;
        print(text);

        int index = 0;
        while(index < text.length && text[index] == 0) {
            index++;
        }
        System.out.println();
        int count = process(text, startAt, moveCost, pushCost, index, "");

        System.out.println("Cost = " + count);
        if (min > 0 && sec < 40) {
            min--;
            sec += 60;
            text[0] = min / 10;
            text[1] = min % 10;
            text[2] = sec / 10;
            text[3] = sec % 10;

            print(text);
            index = 0;
            while(index < text.length && text[index] == 0) {
                index++;
            }
            System.out.println();
            int c2 = process(text, startAt, moveCost, pushCost, index, "");
            count = Math.min(count, c2);
            System.out.println("Cost = " + c2);
        }
        return count;
    }

    private static void print(int[] array) {
        System.out.println("Key to press");
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
    }
    private static int process(int[] keys, int startAt, int moveCost, int pushCost, int index, String prefix) {
        if (index == keys.length) {
            return 0;
        }
        if (keys[index] == startAt) {
            System.out.println(prefix + startAt + ": PushCost : " + pushCost);
            return pushCost + process(keys, startAt, moveCost, pushCost, index + 1, prefix + "\t");
        }

        System.out.println(prefix + "moving from " + startAt + " to = " + keys[index] + " : MoveCost : " + moveCost);
        return moveCost + process(keys, keys[index], moveCost, pushCost, index, prefix + "\t");
    }
}
