<%--
  Created by IntelliJ IDEA.
  User: Evangoe
  Date: 23/05/17
  Time: 下午4:47
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
      href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet"
      href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet"
      href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/datatables/extensions/Buttons/css/buttons.dataTables.css">
<link rel="stylesheet"
      href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/datatables/extensions/Buttons/css/buttons.bootstrap.css">
<link rel="stylesheet"
      href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/datatables/extensions/Select/css/select.bootstrap.css">

<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/datatables/media/js/jquery.dataTables.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<script type="text/javascript"
        src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript"
        src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.zh-CN.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/select2/select2.min.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/select2/i18n/zh-CN.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/datatables/extensions/Buttons/js/dataTables.buttons.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/datatables/extensions/Buttons/js/buttons.bootstrap.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/datatables/extensions/Buttons/js/buttons.html5.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/datatables/extensions/Select/js/dataTables.select.js"></script>
<script type="text/javascript" src="//cdn.lihenginfo.com/resources/js/common/logistics.dataTables.js"></script>
<script type="text/javascript" src="//cdn.lihenginfo.com/resources/js/common/logistics.datePickers.js"></script>