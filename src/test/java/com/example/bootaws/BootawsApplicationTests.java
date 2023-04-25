package com.example.bootaws;

import com.example.bootaws.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableJpaAuditing
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class BootawsApplicationTests {

    @Autowired
    private MockMvc mvc; //웹 api를 테스트할 때 사용, Http get, post 등에 대한 api테스트를 할 수 있다
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함
                .andExpect(status().isOk()) //mvc.perform의 결과를 검증 (404, 500등 상태 )
                .andExpect(content().string(hello)); //Cortroller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name="hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name",name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect( jsonPath("$.amount",is(amount)));

    }

}
