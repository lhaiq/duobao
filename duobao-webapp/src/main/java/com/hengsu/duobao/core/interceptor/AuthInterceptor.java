package com.hengsu.duobao.core.interceptor;

import com.google.common.cache.Cache;
import com.hengsu.duobao.ErrorCode;
import com.hengsu.duobao.core.annotation.IgnoreAuth;
import com.hengsu.duobao.core.annotation.Permission;
import com.hengsu.duobao.core.model.AuthModel;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by haiquanli on 15/11/20.
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier("sessionCache")
    private Cache<String, AuthModel> sessionCache;

    private final static String AUTHORIZATION = "Authorization";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //放过只过滤已sure下的请求
//        if(!request.getRequestURI().startsWith("/sure")){
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//
//        boolean isIgnore = checkIgnore(handler);
//
//        if (!isIgnore) {
//            //取得auth token
//            String authToken = request.getHeader(AUTHORIZATION);
//            if (StringUtils.isEmpty(authToken)) {
//                ErrorCode.throwBusinessException(ErrorCode.AUTH_TOKEN_MUST);
//            }
//
//            AuthModel authModel = sessionCache.getIfPresent(authToken);
//            if (null == authModel) {
//                ErrorCode.throwBusinessException(ErrorCode.AUTH_TOKEN_INVALID);
//            }
//
//            //检查权限
//            checkPermission(authModel.getRole(), handler);
//
//            //将UserId设置到request里面
            request.setAttribute("userId", 1L);
//        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private boolean checkIgnore(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        IgnoreAuth ignoreAuth = handlerMethod.getMethodAnnotation(IgnoreAuth.class);
        if (null != ignoreAuth) {
            return true;
        }

        return false;
    }

    private void checkPermission(Integer role, Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Permission permission = handlerMethod.getMethodAnnotation(Permission.class);
        if (null != permission) {
            if (!ArrayUtils.contains(permission.roles(), role)) {
                ErrorCode.throwBusinessException(ErrorCode.NO_PERMISSION);
            }
        }
    }

}
