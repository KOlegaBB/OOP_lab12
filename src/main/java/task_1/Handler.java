package task_1;

import lombok.Getter;
import lombok.Setter;

public abstract class Handler {
    @Setter @Getter
    public Handler next;
    public abstract void process(int price);
}
