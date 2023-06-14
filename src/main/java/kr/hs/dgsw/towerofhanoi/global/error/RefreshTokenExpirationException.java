package kr.hs.dgsw.towerofhanoi.global.error;

public class RefreshTokenExpirationException extends RuntimeException {

    public RefreshTokenExpirationException(String token) {
        super("만료된 refreshToken입니다. token : " + token);
    }
}
