//
//package com.lib.utils.security;
//
//import com.lib.member.dto.LoginRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@RestController
//public class SecurityController {
//    @Autowired
//    private TokenProvider securityService;
///*
//    @PostMapping("/members/login")
//    public Map<String, Object> createToken(@RequestBody LoginRequest loginRequest) {
//        String token = securityService.createToken(loginRequest.getId(), (2 * 1000 * 60));
//        Map<String, Object> map = new LinkedHashMap<>();
//        map.put("result", token);
//        return map;
//    }
//
// */
//    @RequestMapping(value = {"/remmend/main","/records/**", "comments/**","/members/**","/bookshelves","/calender"})
//    public Map<String, Object> getSubject(@RequestParam(value = "token")String token) {
//        String subject = securityService.getSubject(token);
//        Map<String, Object> map = new LinkedHashMap<>();
//        map.put("result", subject);
//        return map;
//    }
//
//
//
//}
//
