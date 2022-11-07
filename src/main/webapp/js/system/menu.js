var path = getAppPath();
var count = 0;
var lazy = false;
var menulist = new Array();
$(function() {
	$('#center_tab').tabs({
		border : false,
		onSelect : function(title) {
		}
	});
	getMenuTree();
	$('body').css('visibility', 'visible');
});

function getMenuTree() {
	var userid = "";
	$
			.ajax({
				url : path + '/menuPowerController/userMenu.html',
				data : {
					userid : userid
				},
				type : 'POST',
				dateType : 'json',
				success : function(data) {

					var list = new Array();

					var str = "";
					/*一级菜单*/
					for ( var k = 0; k < data.length; k++) {
						var map = new Object();
						list.push(map);

						map.title = data[k].menu_NAME;

						if(data[k].children && data[k].children.length>0){
							/*二级菜单循环*/
							$.each(data[k].children,function(index,item) {
								/*有三级菜单判断*/
								if(item.children && item.children.length>0){
									/*三级菜单循环*/
									var strChildren = ""
									$.each(item.children,function(index,itemChildren) {
										strChildren += "<li><a href=\"javascript:void (0)\"  onclick=\"openTab('" +itemChildren.menu_ADDRESS+ "','" + itemChildren.menu_NAME + "');\">" +itemChildren.menu_NAME + "</a></li>";
									})
									strChildren ="<ul>"+strChildren+"</ul>"
									strChildren="<li data-options=\"state:'closed'\"><span>"+item.menu_NAME+"</span>"+strChildren+"</li>"
									str+=strChildren
								}else{
									/*没有三级菜单的二级菜单循环*/
									str += "<li><a href=\"javascript:void (0)\"  onclick=\"openTab('" +item.menu_ADDRESS+ "','" + item.menu_NAME + "');\">" +item.menu_NAME + "</a></li>";

								}
							});
							str="<div class=\"easyui-panel\" data-options='fit:true,border:false'>  <ul id='tt"+k+"'class='easyui-tree tree' data-options='animate:true'>"+str + "</ul></div>";
							map.contents = str
							str=""
						}
					}
					for ( var l = 0; l < list.length; l++) {
							$('#nav').accordion('add', {
								title : list[l].title,
								content : list[l].contents,
								selected : false
							});
						}
				}
			});
}
