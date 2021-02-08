layui.use(['element', 'layer', 'jquery'], function () {
    var element = layui.element,
        layer = layui.layer,
        $ = layui.jquery;

    /*缩进控件jq*/
    var isShow = true;//判断是否显示
    $('.layuimini-tool').click(function () {
        $('.layui-side-scroll span').each(function () {//遍历所有右侧span是否隐藏
            if ($(this).is(':hidden')) {
                $(this).css('display', '');
            } else {
                $(this).css('display', 'none');
            }
        });
        $('.layui-header .head-logo').each(function () {//同上
            if ($(this).is(':hidden')) {
                $(this).show();
            } else {
                $(this).hide();
            }
        })
        //判断isshow状态
        if (isShow) {
            $('.layui-layout-admin .layui-side').width(60);//设置左侧栏宽度
            $('.layui-side-scroll').width(60);
            $('.layui-nav-tree').width(60);
            $('.layui-layout-admin .layui-logo').width(60);//设置logo宽度
            $('.layui-footer').css('left', 60 + 'px');//把底部拉长
            $('.layuimini-tool').css('left', 80 + 'px');//向左移动缩进控件
            $('.layui-layout-left').css('left', 110 + 'px');
            $('.layui-nav-item span').css('display', 'none');//下拉列表中列表显示隐藏设置
            $('.layui-header .layui-nav-more').css('display', '');
            $('.layui-body').css('left', 60 + 'px');
            isShow = false;
        } else {
            $('.layui-layout-admin .layui-side').width(200);
            $('.layui-side-scroll').width(200);
            $('.layui-nav-tree').width(200);
            $('.layui-layout-admin .layui-logo').width(200);
            $('.layui-footer').css('left', 200 + 'px');
            $('.layuimini-tool').css('left', 225 + 'px');//移动缩进工具
            $('.layui-layout-left').css('left', 255 + 'px');
            $('.layui-nav-item span').css('display', '');
            $('.layui-body').css('left', 200 + 'px');
            isShow = true;
        }


    });

    $('.layui-nav-item-span').each(function () {//如果左侧导航栏隐藏鼠标悬浮在隐藏图标上提示文字
        // if($(this).css('display')=='none'){
        $(this.parentNode).mouseenter(function () {
            var spanText = $(this).text();
            //可以用$(this).text()和 $(this).val()获取span内的值但 val()获取的是当前的值如果span被隐藏获取到的会是空
            layer.tips(spanText, this, {time: 900});//提示框停留时间
        })
        // }
    })

    $('.layui-tab-title-li').each(function () {//遍历tab里的li
        $(this).click(function () {//点击选项卡变色
            $('.layui-tab-title-li').find('i').css('color', 'grey');
            $('.layui-tab-title-li').find('span').css('color', 'grey');
            $(this).find('i').css('color', 'green');
            $(this).find('span').css('color', 'black');

        })
    })

    element.on('tab(myTab)', function (data) {//每次切换tab监听
        // console.log("djkashdjka")
    })

    $('.layui-nav-child').find('a').each(function () {//点击右侧导航栏，添加选项卡,第一次打开选项卡，>1次刷新页面

        $(this).click(function () {
            var tabCount = 1;//tab点击次数计数器
            if (tabCount == 1) {
                tabCount = tabCount + 1;
                let tab = {
                    title: $(this).find('span').text(),
                    content: 'login.html'
                };
                element.tabAdd('myTab', tab);

            } else {
                location.reload();
            }
        })
    })
});
