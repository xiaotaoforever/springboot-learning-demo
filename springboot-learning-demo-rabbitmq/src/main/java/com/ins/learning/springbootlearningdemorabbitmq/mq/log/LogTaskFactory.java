package com.ins.learning.springbootlearningdemorabbitmq.mq.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by administrator on 2018/1/10.
 */
public class LogTaskFactory {
    private static Logger logger = LoggerFactory.getLogger(LogTaskFactory.class);
/*
    public static TimerTask bussinessLog(final OperationLogBean bean, Consumer<OperationLogBean> insertLog) {
        return new TimerTask() {
            @Override
            public void run() {
                bean.setLogtype(LogType.BUSSINESS.getMessage()).setSucceed(LogSucceed.SUCCESS.getMessage());
                try {
                    insertLog.accept(bean);
                    logger.info("operation log record : "+ bean.toString());
                } catch (Exception e) {
                    logger.error("创建业务日志异常!", e);
                }
            }
        };

    }*/
}
