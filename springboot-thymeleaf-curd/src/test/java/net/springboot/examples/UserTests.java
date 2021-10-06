package net.springboot.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTests {
    @Test
    public void whenCalledGetName_thenCorrect() {
        User user = new User("Julie", "julie@domain.com");
        
        assertEquals("Julie", user.getName());
    }
    
    @Test
    public void whenCalledGetEmail_thenCorrect() {
        User user = new User("Julie", "julie@domain.com");
        
        assertEquals("julie@domain.com", user.getEmail());
    }
    
    @Test
    public void whenCalledSetName_thenCorrect() {
        User user = new User("Julie", "julie@domain.com");
        
        user.setName("John");
        
        assertEquals("John", user.getName());
    }
    
    @Test
    public void whenCalledSetEmail_thenCorrect() {
        User user = new User("Julie", "julie@domain.com");
        
        user.setEmail("john@domain.com");
        
        assertEquals("john@domain.com", user.getEmail());
    }
    
    @Test
    public void whenCalledtoString_thenCorrect() {
        User user = new User("Julie", "julie@domain.com");
        assertEquals("User(id=null, name=Julie, email=julie@domain.com)", user.toString());
    }    
}
