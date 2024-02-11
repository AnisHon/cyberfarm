package team.light.cyberfarm.service;

public interface CheckUserService {

    int checkUser(String tel, String password);

    boolean isTelExists(String tel);
}
