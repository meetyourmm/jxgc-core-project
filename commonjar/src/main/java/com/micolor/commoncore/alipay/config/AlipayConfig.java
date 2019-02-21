package com.micolor.commoncore.alipay.config;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.alipay.util.config
 *
 * @Author: Ann
 * @Description:
 * @Date:2017/7/11
 * @Modified:
 */
public class AlipayConfig {
    // 商户appid
    public static String APPID = "2017070907690574";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC5dIvVpGtl7RlELMlDhBbHG+tnI3TF8zIn69uIVBEibUFWuC2Gz1sLHIxANlTsLqMoY7omVMK/Iaw3o7qTIhosIEF+KEZIBMERrCfEJ+6P8xQnmccgMHFwx9qjUSEuZJ2nFDzU4ukGe2ImDukM2Qk2c/6+bTpHklvD4ZzBCe46DXr8i1sJYYtvyy7os6e22D+ZBScIKTtI9Pn8kYHQYFb73UwJrDUrHttE3tnL/0FYVtVpP8blLgoz2BJfzVMXVHa7riXz1bnE5bc8wRImMJdPhwc0jcJkq4wXC9fMuoJybrVtl83FyC0iJcWopF/4Vjcvb7YXxBtiyz1xc8im/cX/AgMBAAECggEBAKJ+ktOD+DJbt+q7TzHgN9EIDo/VSkjzmPvPthBSkJzYnApu4D0hzIUE44KT2H8FMiJ+lylk1SqBR5OM2zp2A/ar4Ja4lTQfLkfczw8ZafcpJny4SfhTWeJWyqSUvn0W3ef6XH6vvzvVuh7cq6h9NoxGwGt76zew7sTIFwAxQlIL5N3NLBdXsWwMD+vPXJAeFa9FOzyevzAJuSBZDaK5To7dzx/BmTyYys3I4aCVHs4EWspFdhv6Brrp4w05x0xLWVsJjuEosXkyOS59hz1EkSDQ0Dx0rp5Gq+RVtweB1Cfs34Pc6zpDQ0O0KexGmX4QpHGAOVTNHoeKT0iXJtxMnGECgYEA6nTL76/bEberkB5jneIGOcywcAFVJkilM9VJqlveknb314hsHmYkFrkJ/2PqCsLE7E9slWuKu2kGYIIla4DS2eCGH6ujY4dgtre2cagLpBqE7VjZROZbrFJ4hn90aVvvxx5Sy3ahpb89tjlTSVHlial/tD9Tjmb5fkDZmbsg0bECgYEAyn8Ut6zgLuvxXNrbBhoo0/s1a8T35k2ZMKUSIUYn5Qk674Lvz6lDXemnjVdNtIEA+0qxq7hPVeKsAXu7xtaLbl1lnl5PmOmPh82oNKfWd5OZ8xo1ZakNCpMveKu09KlWicQsbSlCMHUICSfi04Q24VOBCAiDpwMHY1SKuZJyzq8CgYEAh+S+t+S0E2bkGBqlHsP9W88ajTEYi6Ibrxl3IzkwnrA6I0E9ebk2Pb1u4Aqngyk1PssiYtD8jnfnbFTDH7aBgCu1UfIIOA4lroJ5SxNd+/YrCe8vr9HdXNpIZZzGerMBHeajkq7mLs3UFSTQ83R2mP4XuP/jjGx/PACWn5ZpBWECgYB7P8gLV5SH2qHoo2imGSv6gi7srlE4gsiXvBEB4aKMnAacGzT9Tweq1Hlf5OBt2gsP6FGltCPNoY6RxI7xIkpxXTVpo/uMlbP/s2QK0JEP7O3J8ozHmJJQem9tYg1c0OjUJ2vm8TLRLZo7gGRl21ZP5TRnAE+pSoWnpqBc/M8WDwKBgQDH+tN+LpjZ8YyAlB4x1rSkqJZ+a0nQNJZIdi4YvUSX32NTpIx8leE1cPFz2zIc9VNSVhId3ngEtArdlCqep5xT5XwkUbtic7cY5Ho7PYLRwMLwe8cEmLg3LJmK8VmBMdR8jlhJMCrPB1DMVEiVmBI647UakSP6gu9TLXhYwA6TKw==";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //public static String notify_url = "http://商户网关地址/alipay.trade.wap.pay-JAVA-UTF-8/notify_url.jsp";
    public static String notify_url = "http://www.lihenginfo.com:8080/logistics-interface/business/alipay/alipaynotifyurl";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    //public static String return_url = "http://商户网关地址/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp";
    public static String return_url = "http://www.lihenginfo.com:8080/logistics-interface/business/alipay/alipaynotifyurl";
    // 请求网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm9Emu4Shh+ZuUkvvVPjT2MCAmQwJ4fOpII0mlol02nKx7Oa+9NgFoS8ZtHD/+tkXPcysEKPfux2QhP6D28azX1ZPDDfOYDVDBAMR/WpmsQicyAVx8tZ9ey/7cZODWUHmHwghu4F5o6ASQECs8rrjU86rx8n04ONltYkRPHRJ8Sd+98Ku9lN7T2aEADNdvfMdXypvjAdbTFWIix0NTDnKgEiw2TOl9nMu8cTlK+IYqwzA1OZAggL9l/jGK4M9ogE2Nymk5kh172QU0Bqy1HhRWBPExRVTwK5zHcPufQ7eWU/d0NLnxoNK9MQelJT2fcEeVTg5Fv5/BbR64Y0d5KlVDwIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
