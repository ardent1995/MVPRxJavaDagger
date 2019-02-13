package se.indpro.mvprxjavadagger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.indpro.mvprxjavadagger.login.LoginActivityMVP;
import se.indpro.mvprxjavadagger.login.LoginActivityPresenter;
import se.indpro.mvprxjavadagger.login.User;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PresenterTests {

    LoginActivityMVP.Model mockModel;
    LoginActivityMVP.View mockView;
    LoginActivityPresenter presenter;
    User user;

    @Before
    public void setUp(){

        mockModel = mock(LoginActivityMVP.Model.class);

        user = new User("Tousif","Ikbal");

        mockView = mock(LoginActivityMVP.View.class);

        presenter = new LoginActivityPresenter(mockModel);
        presenter.setView(mockView);
    }

    @After
    public void tearDown(){
        mockModel = null;
        mockView = null;
        presenter = null;
        user = null;
    }

    /*@Test
    public void noInteractionWithView(){
        presenter.getCurrentUser();
        verifyZeroInteractions(mockView);
    }*/

    @Test
    public void loadTheUserFromTheRepositoryWhenValidUserIsPresent(){

        when(mockModel.getUser()).thenReturn(user);

        presenter.getCurrentUser();

        verify(mockModel,times(1)).getUser();

        verify(mockView, never()).showUserNotAvailable();

        verify(mockView,times(1)).setFirstName("Tousif");
        verify(mockView,times(1)).setLastName("Ikbal");

    }

    @Test
    public void shouldShowErrorMessageWhenUserIsNull(){

        when(mockModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        verify(mockModel,times(1)).getUser();

        verify(mockView,times(1)).showUserNotAvailable();

        verify(mockView,never()).setFirstName("Tousif");
        verify(mockView,never()).setLastName("Ikbal");
    }

    @Test
    public void shouldCreateErrorMessageIfFieldAreEmpty(){
        when(mockView.getFirstName()).thenReturn("");
        presenter.loginButtonClicked();

        verify(mockView,times(1)).getFirstName();
        verify(mockView,never()).getLastName();

        verify(mockView,times(1)).showInputError();

        verify(mockModel,never()).createUser("Tousif","Ikbal");
        verify(mockView,never()).showUserSavedMessage();

        when(mockView.getFirstName()).thenReturn("Tousif");
        when(mockView.getLastName()).thenReturn("");
        presenter.loginButtonClicked();

        verify(mockView,times(2)).getFirstName();
        verify(mockView,times(1)).getLastName();

        verify(mockView,times(2)).showInputError();

        verify(mockModel,never()).createUser("Tousif","Ikbal");
        verify(mockView,never()).showUserSavedMessage();
    }

    @Test
    public void shouldBeAbleToSaveAValidUser(){
        when(mockView.getFirstName()).thenReturn("Tousif");
        when(mockView.getLastName()).thenReturn("Ikbal");

        presenter.loginButtonClicked();

        verify(mockView,times(2)).getFirstName();
        verify(mockView,times(2)).getLastName();
        verify(mockView,never()).showInputError();

        verify(mockModel,times(1)).createUser("Tousif","Ikbal");
        verify(mockView,times(1)).showUserSavedMessage();
    }
}
