package org.training.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import javafx.beans.binding.BooleanBinding;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.training.model.Book;
import org.training.repository.BookRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    Book record1 = new Book(1L,"Java","Core Java Programming",5);
    Book record2 = new Book(2L,"C#","C# Programming",5);
    Book record3 = new Book(3L,"Spring","Spring Framework",5);
    Book record4 = new Book(3L,"Python","Python Programming",4);
    Book record5 = new Book(3L,"ReactJS","v Programming",3);

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void getAllRecords_Success() throws Exception {
        List<Book> records = new ArrayList<>(Arrays.asList(record1,record2,record3,record4,record5));
        Mockito.when(bookRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/book/getAllBooks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", is("Spring")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[4].name", is("ReactJS")));
    }

    @Test
    public void getBookById_Success() throws Exception{
        Mockito.when(bookRepository.findById(record1.getBookId())).thenReturn(java.util.Optional.of(record1));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/book/getBookById/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Java")));
    }

    @Test
    public void createRecords_success() throws Exception{

        Book record = Book.builder()
                .bookId(6L)
                .name("DotNet")
                .summary("DotNet Framework Programming")
                .rating(5)
                .build();

        String content = objectWriter.writeValueAsString(record);

        Mockito.when(bookRepository.save(record)).thenReturn(record);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/book/createBook")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("DotNet")));
    }

}
