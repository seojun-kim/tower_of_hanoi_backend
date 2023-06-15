package kr.hs.dgsw.towerofhanoi.global.error;

public class LoginException extends RuntimeException{

    public LoginException(String username) {
        super("잘못된 아이디 또는 비밀번호 입니다. username : " + username);
    }
}
