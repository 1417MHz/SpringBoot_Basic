package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    // @Around() = 공통 관심 사항을 어디에 적용할 것인가
    // 여기서는 hello.hellospring 패키지 하위에 있는 모든 클래스에 적용시킨다
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString()); // 어떤 메소드를 call 하는지 보여줌
        try {
            // 로직이 실행한 후의 결과 값들이 리턴된다
            return joinPoint.proceed(); // 다음 메소드로 진행시킴(공통 관심사항 동작 후 핵심 관심사항 메소드로 이동)
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END" + joinPoint.toString() + " " + timeMs + "ms");
            // 어떠한 메소드가 동작시간이 얼마나 걸렸는지 나타내줌
        }
    }
}
