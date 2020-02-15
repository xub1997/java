package com.xub.utils.spring;

public class CommonUtil {

    private CommonUtil() {
    }

    /**
     * 通过Thread的方法getStackTrace()获取堆栈信息
     * @return
     */
    public static String getProgramName() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        String clazzName = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        String programName = new StringBuilder().append(clazzName).append(".").append(methodName).toString();
        return programName;
    }

    /**
     * 通过Throwable的方法getStackTrace()获取堆栈信息
     *
     * @param throwable
     * @return
     */
    public static String getProgramName(Throwable throwable) {
        StackTraceElement stackTraceElement = throwable.getStackTrace()[0];
        String clazzName = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        String programName = new StringBuilder().append(clazzName).append(".").append(methodName).toString();
        return programName;
    }


}
