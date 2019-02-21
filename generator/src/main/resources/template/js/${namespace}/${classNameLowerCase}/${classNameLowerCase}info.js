/**
 * Created by Evangoe on 21/07/17.
 */
<#assign sqlName = table.sqlName>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameAllLower = className?lower_case>
<#assign columns=table.columns>
logistics.${namespace}.${classNameAllLower}info = {
    initInfo: function (obj) {
        logistics.${namespace}.${classNameAllLower}info.initFormValidate();
        $("#btn_form_cancel").on('click', function () {
            parent.layer.iframeAuto(index);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
    },
    initFormValidate:function(){
        var form1 = $('#form_sample_1');
        var error1 = $('.alert-danger', form1);
        var success1 = $('.alert-success', form1);
        form1.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input
            messages: {
                select_multi: {
                    maxlength: jQuery.validator.format("Max {0} items allowed for selection"),
                    minlength: jQuery.validator.format("At least {0} items must be selected")
                }
            },
            rules: {
                <#list columns as x>
                    "Entity[${x.getColumnName()}]": {required: true},
                </#list>
            },

            invalidHandler: function (event, validator) { //display error alert on form submit
                success1.hide();
                error1.show();
                Metronic.scrollTo(error1, -200);
            },

            highlight: function (element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error'); // set success class to the control group
            },
            submitHandler: function (form) {
                success1.show();
                error1.hide();
                logistics.${namespace}.${classNameAllLower}info.formSubmit();
            }
        });
    },
    formSubmit:function(){
        var <#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list> = $("#<#list table.getPkColumns() as y>${y.getColumnName()}</#list>").val();
        var url;
        if ( <#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list> != "" &&  <#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list> != null) {
            url = logistics.common.basePath() + "/${namespace}/${classNameAllLower}/update${classNameAllLower}";
        } else {
            url = logistics.common.basePath() + "/${namespace}/${classNameAllLower}/save${classNameAllLower}";
        }
        var json_data = $('#form_sample_1').serializeJSON();
        var data = JSON.stringify(json_data);
        $.ajax({
            "contentType": "text/plain",
            async: false,
            type: "POST",
            url: url,
            dataType: "text",
            data: data,
            success: function (data) {
                parent.$('#tableList').DataTable().ajax.reload();
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            },
            error: function () {

            }
        });
    }
}
