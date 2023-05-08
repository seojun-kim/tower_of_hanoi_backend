package kr.hs.dgsw.towerofhanoi.global.error;

public class DuplicateNameException extends RuntimeException {

    public DuplicateNameException() {
        super("이미 존재하는 이름입니다.");
    }
}
