package com.lib.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lib.member.Member;
import com.lib.member.MemberRepository;
import com.lib.member.dto.LoginResponse;
import com.lib.openFeign.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.logging.Handler;

import static java.lang.System.out;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //헤더에서 토큰을 추출해서 -> 유효한지 검증
        String token = jwt.extractJwtTokenFromHeader(request);

        if (ObjectUtils.isEmpty(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        Member user = MemberRepository.findByMemberLoginId(componentCommonUtils.getMemberLoginId());

        if (ObjectUtils.isEmpty(user)) {
            throw new BadRequestException("등록되지 않은 사용자");
        }

        return true;
    }
//        log.debug("==========================");
//        log.debug("==========BEGIN========");
//        log.debug("Request URI ===> " + request.getRequestURI());
//        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
@Override
public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

}

@Override
public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
}
    /*@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String jwt=request.getHeader(HttpHeaders.AUTHORIZATION);
        HandlerMethod method=(HandlerMethod)handler;

        String id = JwtUtil.getSubject(jwt, SCRET_KEY)
                .get("id")
                .toString();
        String access_ip=Utils.getUserIp();
        String action=request.getMethod();
        String service = method.getMethod().getName();
        userManaageDAO.postUserHistory(id,access_ip,action,service);*/
}
}
