/*
 * Copyright (c) 2015. MiColor
 */

/**
 * @Title: MESSAGE.java
 * @Package ierp.common
 * @Description: 用于各个方法返回的参数或者一些静态常量。
 * @author 钰鹏
 * @date 2013年10月9日 下午1:43:00
 * @version V1.0
 */
package com.micolor.commoncore.statics;

/**
 * @author 钰鹏
 * @ClassName: MESSAGE
 * @Description: 用于各个方法返回的参数或者一些静态常量。
 * @date 2013年10月9日 下午1:43:00
 */

public class EnumUtil {

    /**
     * 消息状态
     */
    public enum MessageStatus {
        OK {
            @Override
            public int getName() {
                return 1;
            }
        },
        ERROR {
            @Override
            public int getName() {
                return 0;
            }
        },
        WARN{
            @Override
            public int getName() {
                return 2;
            }
        },
        NOFOUND{
            @Override
            public int getName() {
                return 3;
            }
        },
        ERRORSTATION{
            @Override
            public int getName() {
                return 4;
            }
        },
        TIMEOUT{
            @Override
            public int getName() {
                return 408;
            }
        };

        public abstract int getName();
    }
    public enum OperationMsg{
        OP_OK{
            @Override
            public String toString (){ return "操作成功！";}
        },
        OP_ERROR{
            @Override
            public String toString (){ return "操作失败！";}
        }
    }

    /**
     * 接口消息状态枚举类
     */
    public enum ApiMessageStatus{
        Success,
        Error
    }
}
