package com.spring.nong4;

import com.spring.nong4.user.model.UserEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthCheckIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String uri = request.getRequestURI();
        System.out.println("uri : " + uri);

//        if(Arrays.asList(needLoginUriArr).contains(uri)) { // 만약 needLoginUriArr의 주소값이 존재한다면 로그인이 필요하다는 뜻.
        UserEntity loginUser = (UserEntity) request.getSession().getAttribute("loginUser");

            if(loginUser == null) { //
                System.out.println("viewName  : " + modelAndView.getViewName()); // 원래 가려고 한 주소값이 담겨있다
                modelAndView.setViewName("/user/needLogin");
                // 담겨있는 주소값이 만약 로그인이 필요하다면 주소값을 가로채서 다른 주소값으로 보내버리는 방식
            }
        }
    // view의 처리가 끝난 후 처리하는 구간
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
