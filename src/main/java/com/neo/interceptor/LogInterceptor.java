package com.neo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.neo.entity.SysLog;
import com.neo.repository.SysLogRepo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInterceptor implements HandlerInterceptor {
    //请求开始时间标识
    private static final String LOGGER_SEND_TIME = "_send_time";
    //请求日志实体标识
    private static final String LOGGER_ENTITY = "_logger_entity";

    /**
     * 进入SpringMVC的Controller之前开始记录日志实体
     * @param request 请求对象
     * @param response 响应对象
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        //创建日志实体
        SysLog sysLog = new SysLog();

        //获取请求参数信息
        String param = JSONObject.toJSONString(request.getParameterMap(),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);

        //设置请求参数
        sysLog.setParams(param);

        // 设置IP地址
//        sysLog.setIp(AddressUtils.getIpAddr(request));

//        sysLog.setLocation(AddressUtils.getCityInfo(sysLog.getIp()));

        //设置请求方法,GET,POST...
        sysLog.setMethod(request.getMethod());

        //设置请求路径
        sysLog.setUrl(request.getRequestURI());

        //设置请求开始时间
        request.setAttribute(LOGGER_SEND_TIME,System.currentTimeMillis());

        //设置请求实体到request内，方便afterCompletion方法调用
        request.setAttribute(LOGGER_ENTITY,sysLog);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        int status = httpServletResponse.getStatus();

        //根据不同状态码，跳转不同页面，如
        if(status==404){
//            modelAndView.setViewName("/404");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

        //得到bean
//        SysLogRepo sysLogRepo = SpringContextUtils.getBean("sysLogRepo",SysLogRepo.class);

        //获取请求错误码，根据需求存入数据库，这里不保存
        int status = response.getStatus();

        System.out.println(status);

        //当前时间
        long currentTime = System.currentTimeMillis();

        //请求开始时间
        long time = Long.valueOf(request.getAttribute(LOGGER_SEND_TIME).toString());

        //获取本次请求日志实体
        SysLog sysLog = (SysLog) request.getAttribute(LOGGER_ENTITY);

        //设置访问者
        sysLog.setUsername("admin");

        //设置请求时间差
        sysLog.setTime(Integer.valueOf((currentTime - time)+""));

        //执行将日志写入数据库，可以根据实际需求进行保存
        if(!sysLog.getMethod().equals("GET")){

        }
//        sysLogRepo.save(sysLog);
    }
}
