package com.kumanoit.others;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 11/13/21 IST 8:54 PM
 */
public class WakingRobot {
    public static void main(String[] args) {
//        Robot robot = new Robot(6, 3);
//        robot.move(2, "");
//        robot.move(2, "");
//        int[] dir = robot.getPos();
//        System.out.println(dir[0] + ", " + dir[1]);
//        System.out.println(robot.getDir());
//        robot.move(2, "");
//        robot.move(1, "");
//        robot.move(4, "");
//        dir = robot.getPos();
//        System.out.println(dir[0] + ", " + dir[1]);
//        System.out.println(robot.getDir());

        Robot robot = new Robot(2, 2);
        for(int i = 0; i < 5; i++) {
//            robot.move(3, "");
            int[] dir = robot.getPos();
            System.out.println(dir[0] + ", " + dir[1]);
            System.out.println(robot.getDir());
        }
    }
}
class Robot {

    private static final String EAST = "East";
    private static final String WEST = "West";
    private static final String NORTH = "North";
    private static final String SOUTH = "South";

    int[][] grid;
    int[] botLocation;
    String direction;
    int height;
    int width;

    public Robot(int width, int height) {
        this.grid = new int[width][height];
        this.botLocation = new int[]{0, 0};
        this.direction = EAST;
        this.width = width;
        this.height = height;
    }

    public void move(int num) {//, String prefix) {
//        System.out.println(prefix + direction + " " + this.botLocation[0] + ": " + this.botLocation[1] + " : " + num);
        if (num == 0) {
            return;
        }
        int i = this.botLocation[0];
        int j = this.botLocation[1];
        if (this.direction == EAST) {
            if (j + num < width) {
                this.botLocation = new int[]{i, j + num};
            } else {
                this.botLocation = new int[]{i, width - 1};
                this.direction = NORTH;
                move(num - (width - j - 1));//, prefix + "\t");
            }
        } else if (this.direction == NORTH) {
            if (i + num < height) {
                this.botLocation = new int[]{i + num, j};
            } else {
                this.botLocation = new int[]{height - 1, j};
                this.direction = WEST;
                move(num - (height - 1 - i));//, prefix + "\t");
            }
        } else if (this.direction == WEST) {
            if (j - num >= 0) {
                this.botLocation = new int[]{i, j - num};
            } else {
                this.botLocation = new int[]{i, 0};
                this.direction = SOUTH;
                move(num - j);//, prefix + "\t");
            }
        } else if (this.direction == SOUTH) {
            if (i - num > 0) {
                this.botLocation = new int[]{i - num, j};
            } else {
                this.botLocation = new int[]{0, j};
                this.direction = EAST;
                move(num - i);//, prefix + "\t");
            }
        }
//        System.out.println(prefix + direction + " " + this.botLocation[0] + ": " + this.botLocation[1] + " : " + num);
    }

    public int[] getPos() {
        int i = this.botLocation[0];
        int j = this.botLocation[1];

        return new int[] {j, i};
    }

    public String getDir() {
        return this.direction;
    }
}
