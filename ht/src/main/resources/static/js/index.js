    
/**
 * 系统主管理JS
 */

$(function(){
	
	//点击左面功能菜单处理
	$("ul.sidebar-menu li.treeview ul li a").on("click",function(event){
		var url=$(this).attr("href");
		
		$("section#main-content").load(url); //载入对应的对象管理主页
		
		event.preventDefault();  //停止对象的默认行为。

	});
})

