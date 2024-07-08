package com.lib.member.dto;

import lombok.Getter;

@Getter
public class MemberRegisterRequest {
    private String id;
    private String password;
    private String nickname;
    private String category;
    private Integer resetCycle;
    private Integer goalNumber;

}
