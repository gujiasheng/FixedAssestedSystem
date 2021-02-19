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


    //tab选项卡jq
    element.on('tab(myTab)', function (data) {//每次切换tab监听,用于强调当前选项卡颜色
        // $('.layui-tab-title-li').each(function () {//遍历tab里的li
        //     $(this).click(function () {//点击选项卡变色
        $('.layui-tab-title-li').find('i').css('color', 'grey');
        $('.layui-tab-title-li').find('span').css('color', 'grey');
        $(this).find('i').css('color', 'green');//改用CSS的方法
        $(this).find('span').css('color', 'black');

        //     })
        // })
    })
    $('.layui-nav-child').find('a').each(function () {//点击左侧导航栏，添加选项卡

        $(this).click(function () {
            let title = $(this).text();//获取左侧导航抬头
            let id = $(this).attr('data-id');
            let dataUrl = $(this).attr('data-url');
            let iClass = $(this).find('i').attr('class');//获取左侧导航列表图标
            let spanText = $(this).find('span').text();//获取左侧导航列表的名字

            let iframelist = $(".iframelist");//所有tab下的子页面
            //判断tab栏是否存在标签
            let check = false;
            let checkId;//保存侧边导航id
            let tab = {
                title: '<i class="' + iClass + '"></i><span >' + spanText + '</span>',
                content: '<iframe class="iframelist"  tab-id="' + id + '"  src="' + dataUrl + '" width="100%" height="100%"frameborder="0"></iframe>',
                id: id
            };
            for (let i = 0; i < iframelist.length; i++) {
                let iframeId = $(iframelist[i]).attr('tab-id');
                if (iframeId == id) {
                    check = true;
                    checkid = iframeId;
                }
            }
            if (check) {//如果已经存在就切换到该页面
                element.tabChange('myTab', id);

            } else {
                element.tabAdd('myTab', tab);
                $('.layui-tab-title').find('li').each(function () {
                    $(this).addClass("layui-tab-title-li");
                })
                element.tabChange('myTab', id);
            }
        })
    })
});
