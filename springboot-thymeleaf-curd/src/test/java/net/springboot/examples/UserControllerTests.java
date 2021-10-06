package net.springboot.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public class UserControllerTests {
    private static UserController userController;
    private static UserRepository mockedUserRepository;
    private static BindingResult mockedBindingResult;
    private static Model mockedModel;

    @BeforeAll
    public static void setUpUserControllerInstance() {
        mockedUserRepository = mock(UserRepository.class);
        mockedBindingResult = mock(BindingResult.class);
        mockedModel = mock(Model.class);
        userController = new UserController(mockedUserRepository);
    }

    @Test
    public void whenCalledIndex_thenCorrect() {
        assertEquals("index", userController.index(mockedModel));
    }

    @Test
    public void whenCalledshowSignUpForm_thenCorrect() {
        User user = new User("John", "john@domain.com");

        assertEquals("add-user", userController.signup(user));
    }

    @Test
    public void whenCalledaddUserAndValidUser_thenCorrect() {
        User user = new User("John", "john@domain.com");

        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertEquals("redirect:/index", userController.add(user, mockedBindingResult, mockedModel));
    }

    @Test
    public void whenCalledaddUserAndInValidUser_thenCorrect() {
        User user = new User("John", "john@domain.com");

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertEquals("add-user", userController.add(user, mockedBindingResult, mockedModel));
    }

    @Test
    public void whenCalledshowUpdateForm_thenIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            assertEquals("update-user", userController.get(0, mockedModel));
        });
    }

    @Test
    public void whenCalledupdateUserAndValidUser_thenCorrect() {
        User user = new User("John", "john@domain.com");

        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertEquals("redirect:/index", userController.update(1l, user, mockedBindingResult, mockedModel));
    }

    @Test
    public void whenCalledupdateUserAndInValidUser_thenCorrect() {
        User user = new User("John", "john@domain.com");

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertEquals("update-user", userController.update(1l, user, mockedBindingResult, mockedModel));
    }

    @Test
    public void whenCalleddeleteUser_thenIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            assertEquals("redirect:/index", userController.deleteUser(1l, mockedModel));
        });

    }
}
