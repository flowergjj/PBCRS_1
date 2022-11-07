var path = getAppPath();

/*var checkFunc = function(){
	var funcdivs = $("div[id^='div_func_btn']");

	$.ajax(
			{
				url : path + '/menuPowerController/userfunc.html',*/


var rightFunc =function(userid,funcid){
	var rightflag=false;
	$.ajax(
			{
				url : path + '/menuPowerController/userRightfunc.html',
				method : 'post',
				data:{userid:userid,funcid:funcid},
				async : false
			}).done(function(ret) {
				if(ret['RET_CODE'] === 'SUCCESS')
					rightflag=true;
		});
   return rightflag;
	
};

function openTab(url, title) {
	//console.log($('#center_tab').tabs);
	//console.log($('#center_tab')[0].tabs[0]);
	if($('#center_tab').tabs){
	if (!$('#center_tab').tabs('exists', title)) {
		$('#center_tab').tabs(
				'add',
				{
					title : title,
					content : '<iframe scrolling="auto" frameborder="0"  src="'
							+ url
							+ '" style="width:100%;height:100%;"></iframe>',
					closable : true,
					icon : icon
				});
	} else {
		$('#center_tab').tabs('select', title);
		var tab = $('#center_tab').tabs('getSelected');
		$('#center_tab')
				.tabs(
						'update',
						{
							tab : tab,
							options : {
								title : title,
								content : '<iframe scrolling="auto" frameborder="0"  src="'
										+ url
										+ '" style="width:100%;height:100%;"></iframe>',
								closable : true,
								icon : icon
							}
						});
	}
	}
}
function getQueryString(name) {
	//alert(name);
	//alert(window.location.search.substr(1));
	var url=decodeURI(window.location.search.substr(1));
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = url.match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

function openSubTab(url,title){
	if (!parent.$('#center_tab').tabs('exists', title)) {
		parent.$('#center_tab').tabs(
				'add',
				{
					title : title,
					content : '<iframe scrolling="auto" frameborder="0"  src="'
							+ url
							+ '" style="width:100%;height:100%;"></iframe>',
					closable : true,
					icon : ""
				});
	} else {
/*		parent.$('#center_tab').tabs('select', title);
		var tab = parent.$('#center_tab').tabs('getSelected');
		console.log(tab);
		parent.$('#center_tab')
				.tabs(
						'update',
						{
							tab : tab,
							options : {
								title : title,
								content : '<iframe scrolling="auto" frameborder="0"  src="'
										+ url
										+ '" style="width:100%;height:100%;"></iframe>',
								closable : true,
								icon : ""
							}
						});*/
		parent.$('#center_tab').tabs('close',title);
		parent.$('#center_tab').tabs(
				'add',
				{
					title : title,
					content : '<iframe scrolling="auto" frameborder="0"  src="'
							+ url
							+ '" style="width:100%;height:100%;"></iframe>',
					closable : true,
					icon : ""
				});
		
	}
}

function reflashTab(url,title){
	if (parent.$('#center_tab').tabs('exists', title)) {
		parent.$('#center_tab').tabs('close',title);
		parent.$('#center_tab').tabs(
				'add',
				{
					title : title,
					selected: false,
					content : '<iframe scrolling="auto" frameborder="0"  src="'
							+ url
							+ '" style="width:100%;height:100%;"></iframe>',
					closable : true,
					icon : ""
				});
		
	}
	
}