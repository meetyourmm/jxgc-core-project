package com.micolor.commoncore.statics;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.statics
 *
 * @Author: Evangoe
 * @Description:
 * @Date:10/06/17
 * @Modified:
 */
public class SmsTypeEnum {
    public enum SmsType {
        VERIFICATIONCODE {
            @Override
            public String toString (){ return "1";}
        },
        MESSAGE{
            @Override
            public String toString (){ return "2";}
        }
    }

    /**
     * 短信提供商代码
     */
    public enum SmsCompany{
        /**
         * 建周公司
         */
        JZKJ{
            @Override
            public String toString (){ return "JZKJ";}
        },
        /**
         * 阿里大于
         */
        ALDY{
            @Override
            public String toString (){ return "ALDY";}
        }
    }
}
