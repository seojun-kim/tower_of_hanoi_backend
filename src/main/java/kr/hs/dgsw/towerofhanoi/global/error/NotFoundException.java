package kr.hs.dgsw.towerofhanoi.global.error;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String clazz) {
        super("존재하지 않는 " + clazz);
    }
}
