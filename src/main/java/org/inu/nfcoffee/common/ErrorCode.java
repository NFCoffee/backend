package org.inu.nfcoffee.common;

public enum ErrorCode {
    DUPLICATE_SIGN("E001", "이미 가입한 이력이 있는 사용자입니다"),
    NOT_YET_AUTHENTICATED("E002", "아직 이메일 인증을 하지않은 사용자입니다."),
    EMPTY_EMPLOYEE_INFO("E003", "사원 DB에 등록되지 않은 정보입니다."),
    NOT_MATCH_AUTH_CODE("E004", "인증코드가 일치하지 않습니다"),
    INTERNAL_SERVER_ERROR("S001", "내부 서버 에러"),
    EXPIRED_AUTH_CODE("E005", "인증코드가 만료되었습니다.");

    private final String errCode;
    private final String description;

    ErrorCode(String errCode, String description) {
        this.errCode = errCode;
        this.description = description;
    }

    public String errCode() {
        return errCode;
    }

    public String description() {
        return description;
    }
}
