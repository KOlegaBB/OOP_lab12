package task_1;

public class Handler5 extends Handler{
    @Override
    public void process(int price){
        if (next != null){
            next.process(price % 5);
            System.out.println(price / 5 + " * 5");
        } else if (price % 5 != 0){
            throw new IllegalArgumentException();
        } else {
            System.out.println(price / 5 + " * 5");
        }
    }
}
