/**
 * Created by Evangoe on 21/07/17.
 */
<#assign sqlName = table.sqlName>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameAllLower = className?lower_case>
<#assign columns=table.columns>
logistics.${namespace}.${classNameAllLower}list ={
    initList:function(){
        var obj = {
            //list页面表格初始化option
            //待初始化表格 id string
            tableId : "tableList",
            //待初始化表格 数据源url string
            url : logistics.common.basePath()+"/${namespace}/${classNameAllLower}/pagedata",
            aoData : function(aoData){
                var reqData = {};
                reqData['dtJson'] = JSON.stringify(aoData);
                return reqData;
            },
            //待初始化表格 默认显示列 array
            columnsArr : [
                {"data": null, "width": "30px"},
                <#list columns as x>
                {"data": "${x.getColumnName()}"},
                </#list>
            ],
            //待初始化表格 实际显示列 array
            columnShowArr : [],
            columnDefsArr:[{
                "render": function (data, type, row) {
                    return '';
                },
                orderable: false,
                className: 'select-checkbox',
                targets: 0
            },{
                targets: 1 ,
                visible: false
            }]
        };
        //从公共初始化方法中获得datatable
        var table = logistics.common.dataTables.crudDatatable.initDataTableFun(obj);
        //表格删除数据按钮事件
        logistics.common.dataTables.crudDatatable.btnDeleteClick= function(){
            var rowData = table.rows({selected: true}).data().toArray();
            var count = rowData.length;
            if (count == 0) {
                layer.alert('请选择<span class="label label-danger">一条</span>数据进行操作.', {
                    icon: 2,
                    skin: 'layui-layer-molv' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
                });
            } else {
                layer.confirm('确认删除选择项目?', {
                    skin: 'layui-layer-molv',
                    btn: ['确定','取消'] //按钮
                },  function(index, layero){
                    //确定按钮
                    var selectObjs = "";
                    for(var i = 0; i < rowData.length; i++){
                        selectObjs = selectObjs + rowData[i]['<#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list>'] + ",";
                    }
                    $.ajax({
                        "url" : logistics.common.basePath()+"/${namespace}/${classNameAllLower}/delete${classNameAllLower}",
                        "contentType": "application/x-www-form-urlencoded",
                        "async": false,
                        "type": "POST",
                        "data": {
                            "<#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list>s": selectObjs
                        },
                        "dataType": "json",
                        "success": function (data, callback, settings) {
                            if (data['status'] == "OK") {
                                toastr.info("删除成功!", "信息")
                            } else {
                                toastr.info("删除失败!", "信息")
                            }
                        },
                        "error": function (data, callback, settings) {
                            toastr.error("连接服务器出错!", "错误")
                        }
                    });
                    table.ajax.reload();
                    layer.close(index);
                }, function(index, layero){
                    //取消按钮，不做任何操作。
                });
            }
        };
        //表格新增按钮事件
        logistics.common.dataTables.crudDatatable.btnNewClick=function(){
            layer.open({
                type: 2,
                maxmin: true,
                title: '',
                shadeClose: true,
                shade: 0.8,
                area: ['1080px', '90%'],
                content: logistics.common.basePath()+'/${namespace}/${classNameAllLower}/${classNameAllLower}info?<#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list> ='//iframe的url
            });
        };
        //表格修改按钮事件
        logistics.common.dataTables.crudDatatable.btnEditClick=function(){
            var rowData = table.rows({selected: true}).data().toArray();
            var count = rowData.length;
            if (count != 1) {
                layer.alert('请选择<span class="label label-danger">一条</span>数据进行操作.', {
                    icon: 2,
                    skin: 'layui-layer-molv' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
                });
            } else {
                layer.open({
                    type: 2,
                    maxmin: true,
                    title: '',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['1080px', '90%'],
                    content: logistics.common.basePath() + '/${namespace}/${classNameAllLower}/${classNameAllLower}info?<#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list> ='+rowData[0]['<#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list>'] //iframe的url
                });
            }
        };
    }
}