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
<jsp:include page="/resources/include/include_dataTables.jsp"></jsp:include>
<div class="row">
    <div class="col-md-12">
        <div class="portlet box green">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-cogs"></i>${table.remarks}列表
                </div>
                <div class="tools">
                    <a href="javascript:;" class="collapse">
                    </a>
                    <a href="" class="fullscreen"></a>
                </div>
            </div>
            <div class="portlet-body">
                <table id="tableList" class="table table-bordered table-striped table-hover cell-border">
                    <thead>
                    <tr>
                        <th>选择</th>
                        <#list columns as x>
                            <th>${x.getRemarks()}</th>
                        </#list>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- 开始函数部分-->
<script type="text/javascript" src="<%=basePath%>/resources/js/${namespace}/${classNameAllLower}/${classNameAllLower}list.js"></script>
<script>
    jQuery(document).ready(function () {
        logistics.${namespace}.${classNameAllLower}list.initList();
    });
</script>