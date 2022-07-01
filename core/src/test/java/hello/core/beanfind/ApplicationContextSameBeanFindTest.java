package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복오류가 발생한다. ")
    void findBeanByTypeDeplicate(){
//        MemberService memberService = ac.getBean("memberService", MemberService.class);
//        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        MemberRepository bean = ac.getBean(MemberRepository.class);

        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    }

//    @Test
//    @DisplayName("타입으로 조회시 같은타입이 둘있으면, 빈 이름을 지정하면 된다.  ")
//    void findBeanByTypeDeplicate(){
////        MemberService memberService = ac.getBean("memberService", MemberService.class);
////        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
//        MemberRepository bean = ac.getBean("memberRepository1",MemberRepository.class);
//
//    }


    @Configuration
    static class SameBeanConfig{


        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }


        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
