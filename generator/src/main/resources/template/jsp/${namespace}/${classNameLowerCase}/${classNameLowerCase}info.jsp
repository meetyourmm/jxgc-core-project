<#assign sqlName = table.sqlName>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameAllLower = className?lower_case>
<#assign columns=table.columns>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>Metronic | Form Stuff - Form Validation</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <jsp:include page="/resources/include/include_form.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>/resources/js/admin/role/roleinfo.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/${namespace}/${classNameAllLower}/${classNameAllLower}info.js"></script>
</head>
<body>
<div class="page-container">
    <div class="page-content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet light">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-cogs font-green-sharp"></i>
                                <span class="caption-subject font-green-sharp bold uppercase">${table.remarks}信息</span>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="javascript:;" id="form_sample_1" class="form-horizontal" novalidate="novalidate">
                                <div class="form-body">
                                    <div class="alert alert-danger display-hide">
                                        <button class="close" data-close="alert"></button>
                                        你的数据有些问题。 请检查。
                                    </div>
                                    <div class="alert alert-success display-hide">
                                        <button class="close" data-close="alert"></button>
                                        数据通过验证。
                                    </div>
                                    <#list columns as x>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">${x.getRemarks()}
                                        </label>
                                        <div class="col-md-7">
                                            <input name="Entity[${x.getColumnName()}]" type="text" id ="${x.getColumnName()}" value="${r'${'}Entity.${x.getColumnName()}}" class="form-control">
                                        </div>
                                    </div>
                                    </#list>
                                </div>
                                <div class="form-actions">
                                    <div class="row">
                                        <div class="col-md-offset-3 col-md-9">
                                            <button id="btn_form_submit" type="submit" class="btn green">确定</button>
                                            <button id="btn_form_cancel" type="button" class="btn default">取消</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    jQuery(document).ready(function () {
        logistics.${namespace}.${classNameAllLower}info.initInfo();
    });
</script>
</body>
</html>