package kysersozelee.part4;

import java.util.function.Consumer;

public class InterfaceTest {

    interface Welcome<T>{
        T welcome();
    }

    public static void main(String[] args) {
        doAction((Welcome<String>)()->"kiss", consumer->{
            consumer.welcome();
        });
    }

    public static <T> void doAction(T t, Consumer<T> consumer){
        consumer.accept(t);
    }


}