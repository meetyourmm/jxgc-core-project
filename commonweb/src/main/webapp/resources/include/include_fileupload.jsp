<%--
  Created by IntelliJ IDEA.
  User: Ann
  Date: 2017/6/23
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!-- The blueimp Gallery widget -->
<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div>
<!-- The template to display files available for upload -->
<!-- BEGIN:File Upload STYLES -->
<link href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css"
      rel="stylesheet"/>
<link href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css"
      rel="stylesheet"/>
<link href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css"
      rel="stylesheet"/>
<noscript>
    <link rel="stylesheet"
          href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-noscript.css">
</noscript>
<noscript>
    <link rel="stylesheet"
          href="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui-noscript.css">
</noscript>
<!-- END File Upload STYLES -->
<!-- BEGIN:File Upload Plugin JS files-->
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error text-danger"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="btn btn-primary start" disabled>
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>开始</span>
                </button>
            {% } %}
            {% if (!i) { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>取消</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}

</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="{%=logistics.common.basePath()+file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=logistics.common.basePath()+file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </td>
        <td>
            <p class="name">
                {% if (file.url) { %}
                    <a href="{%=logistics.common.basePath()+file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery>{%=file.name%}</a>
                {% } else { %}
                    <span>{%=file.name%}</span>
                {% } %}
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete delete-files" del-url="{%=file.deleteUrl%}" del-id="{%=file.deleteId%}">
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>删除</span>
                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>取消</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}

</script>
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/js/vendor/tmpl.min.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/js/vendor/load-image.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/js/vendor/canvas-to-blob.min.js"></script>
<!-- blueimp Gallery script -->
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/blueimp-gallery/jquery.blueimp-gallery.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-process.js"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-image.js"></script>
<!-- The File Upload audio preview plugin -->
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-audio.js"></script>
<!-- The File Upload video preview plugin -->
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-video.js"></script>
<!-- The File Upload validation plugin -->
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-validate.js"></script>
<!-- The File Upload user interface plugin -->
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-ui.js"></script>
<!--[if (gte IE 8)&(lt IE 10)]>
<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/js/cors/jquery.xdr-transport.js"></script>
<!-- The main application script -->
<%--<script src="//cdn.lihenginfo.com/resources/plugins/dist/assets/global/plugins/jquery-file-upload/js/main.js"></script>--%>

