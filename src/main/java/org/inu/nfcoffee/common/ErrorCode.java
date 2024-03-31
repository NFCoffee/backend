package org.inu.nfcoffee.common;

public enum ErrorCode {
    DUPLICATE_SIGN("EMPL_001", "이미 가입한 이력이 있는 사용자입니다"),
    NOT_YET_AUTHENTICATED("EMPL_002", "아직 이메일 인증을 하지않은 사용자입니다."),
    EMPTY_EMPLOYEE_INFO("EMPL_003", "사원 DB에 등록되지 않은 정보입니다."),
    NOT_MATCH_AUTH_CODE("EMPL_004","인증코드가 일치하지 않습니다");

    private String errCode;
    private String description;

    ErrorCode(String errCode, String description) {
        this.errCode = errCode;
        this.description = description;
    }

    public String description() {
        return description;
    }
}
