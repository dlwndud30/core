package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        /*
        //스레드A : A사용자가 10000원 주문
        statefulService1.order("userA", 10000);

        //스레드B : B사용자가 20000원 주문
        statefulService1.order("userB", 20000);

        //스레드A : A사용자가 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); //10000원을 기대하지만 20000원 출력

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
*/

        //스레드A : A사용자가 10000원 주문
        int price1 = statefulService1.order("userA", 10000);

        //스레드B : B사용자가 20000원 주문
        int price2 = statefulService1.order("userB", 20000);

        System.out.println("price = " + price1);
        System.out.println("price = " + price2);

        Assertions.assertThat(price1).isEqualTo(10000);

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}