package com.polarbookshop.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.polarbookshop.catalogservice.domain.Book;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
public class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json; //잭슨 테스터 빈 주입

    @Test
    void testSerialize() throws Exception {
        var book = new Book("1234567891", "Title",  "Author", 9.90); //객체생성
        var jsonContent = json.write(book); //json으로 변환

        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
            {
                "isbn" : "1234567891",
                "title" : "Title",
                "author" : "Author",
                "price" : 9.90
            }
        """;

        assertThat(json.parse(content)).usingRecursiveComparison().isEqualTo(new Book("1234567891", "Title",  "Author", 9.90));
    }
}
