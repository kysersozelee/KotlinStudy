package kysersozelee.part1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnableExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.print("TEST1");
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.print("TEST2");
        });

        Thread t3 = new Thread(() -> System.out.print("TEST3"));


        t1.run();
        t2.run();
        t3.run();
    }
}
