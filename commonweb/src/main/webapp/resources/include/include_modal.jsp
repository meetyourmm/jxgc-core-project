<%--
  Created by IntelliJ IDEA.
  User: Evangoe
  Date: 07/04/16
  Time: 下午5:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 这是一些拟态窗口的DIV定义, 来自/include/include_modal.jsp ,引用开始-->
<!-- 删除确认的拟态窗口 -->
<div id="deletecheck" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="deletecheckModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="deletecheckModalLabel"><i class="fa fa-question-circle-o"></i> 操作确认</h4>
            </div>
            <div class="modal-body">
                <p class="text-red" >确认删除选择项目?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-undo"></i> 取消</button>
                <button type="button" class="btn btn-primary" id="deleteok"><i class="fa fa-check-circle-o"></i> 确定</button>
            </div>
        </div>
    </div>
</div>
<!-- 单条选择的拟态窗口 -->
<div id="onecheck" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="onecheckModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="onecheckModalLabel2"><i class="fa fa-info-circle"></i> 消息</h4>
            </div>
            <div class="modal-body">
                请选择<span class="label label-danger">一条</span>数据进行操作.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="onechecok" data-dismiss="modal"><i class="fa fa-check-circle-o"></i> 确定</button>
            </div>
        </div>
    </div>
</div>
<!-- 这是一些拟态窗口的DIV定义, 来自/include/include_modal.jsp ,引用结束-->