<%--
  Created by IntelliJ IDEA.
  User: Ann
  Date: 2017/11/16
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="shortcut icon" href="//cdn.lihenginfo.com/resources/imgs/favicon.ico"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/font-awesome/css/font-awesome.min.css"
      rel="stylesheet" type="text/css"/>
<link href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/bootstrap/css/bootstrap.min.css"
      rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css"
      href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/bootstrap-toastr/toastr.min.css"/>
<link href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/css/components.css" id="style_components"
      rel="stylesheet" type="text/css"/>
<link href="//cdn.lihenginfo.com/resources/plugins/dist/assets/admin/layout/css/layout.css" rel="stylesheet"
      type="text/css"/>
<link href="//cdn.lihenginfo.com/resources/plugins/dist/assets/admin/layout3/css/custom.css" rel="stylesheet"
      type="text/css"/>
<link id="style_color" href="//cdn.lihenginfo.com/resources/plugins/dist/assets/admin/layout/css/themes/darkblue.css"
      rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<style>
    .page-header.navbar .top-menu .navbar-nav > li.dropdown-extended .dropdown-menu {
        width: 400px;
        max-width: 400px;
    }

    .page-header.navbar .page-logo .logo-default {
        margin: 8px 0 0 0;
        width: 113px;
    }
</style>

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 10]>
<script language="JavaScript">
if(confirm("浏览器版本过低，推荐下载Chrome,确定下载?")){
window.location.href = '//sw.bos.baidu.com/sw-search-sp/software/49313156f695c/ChromeStandalone_60.0.3112.101_Setup.exe';
}else{
window.location.href ='//www.lihenginfo.com/low_version.html';
}
</script>
<![endif]-->
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery.min.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-migrate.min.js"></script>
<script src="//cdn.lihenginfo.com//resources/js/common/logistics.js"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery.cokie.min.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/scripts/metronic.js"></script>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/admin/layout/scripts/layout.js"></script>
<script src="//cdn.lihenginfo.com//resources/plugins/dist/assets/global/plugins/layer/layer.js"></script>
<script src="//cdn.lihenginfo.com//resources/plugins/dist/assets/global/plugins/bootstrap-toastr/toastr.js"></script>
<script src="//cdn.lihenginfo.com//resources/plugins/mq/stomp.js"></script>