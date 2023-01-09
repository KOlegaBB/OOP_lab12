package task_1;

public class Handler20 extends Handler{
    @Override
    public void process(int price){
        Handler next = getNext();
        if (next != null){
            next.process(price % 20);
            System.out.println(price / 20 + " * 20");
        } else if (price % 20 != 0){
            throw new IllegalArgumentException();
        } else {
            System.out.println(price / 20 + " * 20");
        }
    }
}
