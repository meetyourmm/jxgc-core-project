package com.micolor.commoncore.jwt;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.jwt
 *
 * @Author: Evangoe
 * @Description:
 * @Date:31/05/17
 * @Modified:
 */
public class LoginPara {
    private String clientId;
    private String userName;
    private String password;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
