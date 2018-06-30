package com.jt.web.intercept;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.util.CookieUtils;
import com.jt.common.util.RedisUtils;
import com.jt.web.pojo.User;
import com.jt.web.util.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName WebInterceptor
 * @Description TODO
 * @Author PS
 * @Date 2018/6/26 13:51
 **/
//定义SpringMVC中的拦截器
public class WebInterceptor implements HandlerInterceptor {

    @Qualifier("redisPool")
    @Autowired
    private RedisUtils redisUtils;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    //不要定义静态的成员变量  因为是公用的有线程安全性问题


	/*private static User user = null;


	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		WebInterceptor.user = user;
	}*/

    /**
     * boolean: true 表示拦截器放行
     *          false 表示拦截器继续拦截  应该指定页面的跳转路径,否则程序卡死了
     */

    //请求执行之前拦截的操作
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        /**
         * 1.先获取ticket信息      2.获取userJSON串      3.将数据进行转化为User对象
         */
        String ticket = CookieUtils.getCookieValue(request, "JT_TICKET");

        if(!StringUtils.isEmpty(ticket)){
            //如果ticket信息不为null则进行取值工作
            String userJSON = redisUtils.get(ticket);

            if(!StringUtils.isEmpty(userJSON)){
                //从redis中获取ticket信息,如果不为空,则进行转化工作
                User user = objectMapper.readValue(userJSON, User.class);

                //将数据存入到ThreadLocal中进行保存
                UserThreadLocal.setUser(user);

                return true;  //表示拦截器放行
            }
        }

        //转向用户的登陆页面即可
        response.sendRedirect("/user/login.html");
        return false;  //false表示不能跳转到程序制定页面,应该按照设定进行跳转
    }

    //请求执行之后拦截
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    //最终都要拦截
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

}