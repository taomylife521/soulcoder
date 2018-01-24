<%--被包含的jsp，要与当前页<%@ page/>内容要一致，不允许contentType="text/html; charset=UTF-8",有空格。--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript" src="${path}/statics/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="${path}/statics/adminlte/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${path}/statics/libs/vue.min.js"></script>
<script type="text/javascript" src="${path}/statics/libs/global.js"></script>

<%--<link rel="stylesheet" type="text/css" href="${path}/statics/adminlte/plugins/sweetalert/sweetalert.min.js">--%>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="${path}/statics/adminlte/plugins/select2/select2.full.min.js"></script>
<script src="${path}/statics/adminlte/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${path}/statics/adminlte/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${path}/statics/adminlte/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="${path}/statics/adminlte/plugins/daterangepicker/daterangepicker.js"></script>
<script src="${path}/statics/adminlte/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${path}/statics/adminlte/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script src="${path}/statics/adminlte/plugins/timepicker/bootstrap-timepicker.min.js"></script>
<script src="${path}/statics/adminlte/plugins/iCheck/icheck.min.js"></script>
<!-- FastClick -->
<script src="${path}/statics/adminlte/plugins/fastclick/fastclick.min.js"></script>
<!-- AdminLTE App -->
<script src="${path}/statics/adminlte/dist/js/app.min.js"></script>
<!-- SlimScroll 1.3.0 -->
<script src="${path}/statics/adminlte/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${path}/statics/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${path}/statics/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${path}/statics/adminlte/plugins/tooltips/tipso.js"></script>
<link href="${path}/statics/adminlte/plugins/bootstrap-switch/js/bootstrap-switch.min.js" rel="stylesheet" />
<script>

    var oLanguageData = {
        //国际化配置
        "sProcessing": "正在获取数据，请稍后...",
        "sLengthMenu": "显示 _MENU_ 条",
        "sZeroRecords": "抱歉,没有您要搜索的内容!",
        "sInfo": "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
        "sInfoEmpty": "记录数为0",
        "sInfoFiltered": "(全部记录数 _MAX_ 条)",
        "sInfoPostFix": "",
        "sSearch": "搜索",
        "sUrl": "",
        "oPaginate": {
            "sFirst": "第一页",
            "sPrevious": "上一页",
            "sNext": "下一页",
            "sLast": "最后一页"
        },
        "oAria": {
            "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }


    };

    $(function(){
        $(".select2").select2();
        $('#reservation').daterangepicker(
            {
                applyClass: 'btn-sm btn-success',
                cancelClass: 'btn-sm btn-default',
                locale: {
                    applyLabel: '确认',
                    cancelLabel: '取消',
                    fromLabel: '起始时间',
                    toLabel: '结束时间',
                    customRangeLabel: '自定义',
                    firstDay: 1,
                    format: 'YYYY-MM-DD',
                    separator: '/',
                },
                maxDate: moment(), //最大时间
                startDate: moment().subtract(1, 'year'),
                endDate: moment(),
                ranges: {
                    '今天': [moment(), moment()],
                    '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                    '最近7天': [moment().subtract(6, 'days'), moment()],
                    '最近30天': [moment().subtract(29, 'days'), moment()],
                    '最近三个月': [moment().subtract(3, 'month').startOf('month'), moment()]
                }
            }
        );
        //Date picker
        $('#datepicker').datepicker({
            autoclose: true,
            format:'yyyy-mm-dd'
        });
        $('input[type=radio]').iCheck({
            checkboxClass: 'icheckbox_flat-blue',
            radioClass: 'iradio_flat-blue'
        });

      //  $("input[name=switchCheckBox]").bootstrapSwitch();
    });

</script>


