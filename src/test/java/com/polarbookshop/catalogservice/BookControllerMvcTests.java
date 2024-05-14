package com.polarbookshop.catalogservice;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.BookService;
import com.polarbookshop.catalogservice.web.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class) //src에 쓰고 있는 컨트롤러를 대상으로 테스트 진행 한다는 표시
public class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc; //mockMvc 객체 선언

    @MockBean
    private BookService bookService; //bean 객체로 service 호출 선언 @Service

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws  Exception {
        String isbn = "1231231231";

        given(bookService.viewBookDetails(isbn)).willThrow(BookNotFoundException.class); //서비스의 메소드를 호출을 정한다
        mockMvc.perform(get("/books" + isbn)).andExpect(status().isNotFound());

    }
}
