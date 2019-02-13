package se.indpro.mvprxjavadagger.login;

public interface LoginRepository {

    User getUser();

    void saveUser(User user);

}
