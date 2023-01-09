import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import task_1.Handler20;
import task_1.Handler5;
import task_1.Handler50;


public class ATM_Test {
    Handler5 handler5 = new Handler5();
    Handler20 handler20 = new Handler20();
    Handler50 handler50 = new Handler50();

    @BeforeEach
    public void init() {
        handler20.setNext(handler5);
        handler50.setNext(handler20);
    }

    @Test
    public void Error_Test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> handler50.process(126));
    }

    @Test
    public void Not_Error_Test(){
        Assertions.assertDoesNotThrow(() -> handler50.process(125));
    }
}
