//iframe自适应
$(window).on('resize', function() {
	var $content = $('.content');
	$content.height($(this).height() - 120);
	$content.find('iframe').each(function() {
		$(this).height($content.height());
	});
}).resize();


var vm = new Vue({
	el:'#rrapp',
	data:{
		main:"main.html",
        navTitle:"欢迎页"
	},
    methods: {
        donate: function () {
			layer.open({
				type: 1,
				skin: 'layui-layer-rim', //加上边框
				area: ['810px', '500px'], //宽高
				content: '<div id="wrap">' +
				'<img width="405" height="500" src="https://cdn.staticaly.com/gh/bu0207/picx-images-hosting@master/weixin.6fcoy0m238w0.jpg">' +
				'<img width="405" height="500" src="https://cdn.staticaly.com/gh/bu0207/picx-images-hosting@master/alipay.32urgir2fyo0.webp">' +
				'</div>'
			});
        }
    }
});

//路由
var router = new Router();
var menus = ["main.html","generator.html"];
routerList(router, menus);
router.start();

function routerList(router, menus){
	for(var index in menus){
		router.add('#'+menus[index], function() {
			var url = window.location.hash;

			//替换iframe的url
			vm.main = url.replace('#', '');

			//导航菜单展开
			$(".treeview-menu li").removeClass("active");
			$("a[href='"+url+"']").parents("li").addClass("active");

			vm.navTitle = $("a[href='"+url+"']").text();
		});
	}
}
