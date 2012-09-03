import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Hanoi {
    static Stack<Integer> stack1, stack2, stack3;

    // Move disks from 0 -> 1
    public static void hanoi(List<Stack<Integer>> posts, int numDisks) {
        stack1 = posts.get(0);
        stack2 = posts.get(1);
        stack3 = posts.get(2);

        int diskToMove = numDisks;

        moveTower(diskToMove, stack1, stack2, stack3);
    }

    public static void moveTower(int disk, Stack<Integer> src, Stack<Integer> dest, Stack<Integer> buf) {
        System.out.println(stack1);
        System.out.println(stack2);
        System.out.println(stack3);
        System.out.println();

        if (disk == 1) {
            dest.push(src.pop());
        } else {
            moveTower(disk - 1, src, buf, dest);

            dest.push(src.pop());

            moveTower(disk - 1, buf, dest, src);
        }
    }

    public static void main(String[] args) {
        if (1 != args.length) {
            System.out.println("usage: ./class num_disks");
            return;
        }

        int numPosts = 3;
        int numDisks = Integer.parseInt(args[0]);

        List<Stack<Integer>> posts = new ArrayList<Stack<Integer>>(numPosts);

        for (int i = 0; i < numPosts; i++) {
            posts.add(new Stack<Integer>());
        }

        for (int i = numDisks; i > 0; i--) {
            posts.get(0).push(i);
        }

        hanoi(posts, numDisks);

        for (int i = 0; i < numPosts; i++) {
            System.out.println(posts.get(i));
        }
    }
}
