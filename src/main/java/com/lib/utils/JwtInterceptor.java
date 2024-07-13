package com.lib.utils;
import com.lib.member.MemberRepository;
import com.lib.utils.security.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final MemberRepository memberRepository;
    private TokenProvider tokenProvider;

    @Autowired
    public JwtInterceptor(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.tokenProvider = new TokenProvider();
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(JwtRequired.class)|| handlerMethod.getBeanType().isAnnotationPresent(JwtRequired.class)) {
            String token= request.getHeader("Authorization");
            if (token == null) {
                return false;
            }
        }
        return true;

    }
}

/*
//React와의 연동으로 인한 CORS 정책 판단 조건
        if(HttpMethod.OPTIONS.matches(request.getMethod())) {
        return true;
        }
// 인가 요청 여부확인
String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
// JWT 여부 확인
String token = authorization.replaceAll("Bearer ", "");

// token의 값이 정상적인지 확인
        if (token != null && token.length() > 10) {
        log.debug("토큰 상태:: " + token);

// token 유효성 검증
            if (validateToken.vaildToken(token)) {
ObjectMapper objectMapper = new ObjectMapper();

// Payload 내 Member 객체 정보 추출
String member = objectMapper.writeValueAsString(jwtProvider.getMemberInfo(token).get("member"));
Member accessMember = objectMapper.readValue(member, Member.class);

// 추출한 정보 request 객체에 적재
                request.setAttribute("member", accessMember);
                return true;
                        }
                        } else {
                        throw new CustomException(ErrorCode.VALID_MEMBER);
        }
                return false;

 */