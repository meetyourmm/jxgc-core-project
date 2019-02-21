<%--
  Created by IntelliJ IDEA.
  User: Evangoe
  Date: 31/05/17
  Time: 上午11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<link rel="stylesheet" href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/select2/select2.css"/>
<link href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/css/plugins.css" rel="stylesheet"/>
<link rel="stylesheet"
      href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>

<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/select2/select2.min.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/select2/i18n/zh-CN.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.zh-CN.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-serialize-object/jquery.serialize-object.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-validation/js/jquery.validate.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-validation/js/localization/messages_zh.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/admin/pages/scripts/form-validate-expand.js"></script>
<!-时间控件->
<script src="//cdn.lihenginfo.com/resources/js/common/logistics.datePickers.js"></script>
<script src="//cdn.lihenginfo.com/resources/js/common/logistics.form.js"></script>
<script>
    jQuery(document).ready(function() {
        Metronic.init(); // init metronic core components
    });
</script>