function GetRequest() {

	var url = this.location.search; // 获取url中"?"符后的字串
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for ( var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
		}
	}
	return theRequest;
}

function textboxClear(name) {
	$('#' + name).textbox('clear');
}

function comboboxClear(name) {
	$('#' + name).combobox('clear');
}

function setAllTextboxValidation(dlgName, valid) {
	$('#' + dlgName + ' .easyui-textbox').each(function(index, obj) {
		$(obj).textbox(valid);
	});
}

function setAllComboboxValidation(dlgName, valid) {
	$('#' + dlgName + ' .easyui-combobox').each(function(index, obj) {
		$(obj).combobox(valid);
	});
}

function setFieldReadonly(dlgName, flag) {
	$('#' + dlgName + ' input').attr('readonly', flag);
	$('#' + dlgName + ' .easyui-combobox').each(function(index, obj) {
		$(obj).combobox('readonly', flag);
	});
}

function setAllTextboxValue(dlgName, value) {
	$('#' + dlgName + ' .easyui-textbox').each(function(index, obj) {
		$(obj).textbox('setValue', value);
	});
}

//只能输入数字和小数  
function clearNoNum(obj){
    //先把非数字的都替换掉，除了数字和.
	//console.log(obj);
    obj.value = obj.value.replace(/[^\d.]/g,"");
    //必须保证第一个为数字不是.
    obj.value = obj.value.replace(/^\./g,"");
    //保证只有出现一个.而没有多个
    obj.value = obj.value.replace(/\.{2,}/g,".");
    //保证.至出现一次，而不是出现两次以上
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}
function clearNoInt(obj){
	obj.value = obj.value.replace(/[^\d]/g,"");
}

function filechange(Obj){
	//alert(10);
	var filename=$(Obj).val().split('\\');
			
	$('#'+Obj.id+'name').html(filename[filename.length-1]);
}
//显示年和季度
function setDateBoxTwo(obj){
	
	$(obj).datebox({
	       //显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
	       onShowPanel: function () {
	    	
	          //触发click事件弹出月份层
	          span.trigger('click'); 
	          
	          if (!tds)
	            //延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
	            setTimeout(function() { 
	                tds = p.find('div.calendar-menu-month-inner td');
	                var i=1;
	                tds.each(function(){
	                	if(i==1){
	                		$(this).html("上半年");
	                		
	                		$(this).css("width","40%");
	                		
	                	}if(i==2){
	                		$(this).html("下半年");
	                		
	                		$(this).css("width","40%");
	                	}if(i==3||i==4||i==7||i==8||i==9||i==10||i==11||i==12){
	                		$(this).remove();
	                	}
	                	if(i==5){
	                		$(this).remove();
	                		$(this).html("第三季度");
	                		$(this).attr('abbr',3);
	                		$(this).css("width","40%");
	                	}
	                	if(i==6){
	                		$(this).remove();
	                		$(this).html("第四季度");
	                		$(this).attr('abbr',4);
	                		$(this).css("width","40%");
	                	}
	                	i++;
	                });
	                tds.click(function(e) {
	                	
	                   //禁止冒泡执行easyui给月份绑定的事件
	                   e.stopPropagation(); 
	                   //得到年份
	                 
	                   var year = /\d{4}/.exec(span.html())[0] ;
	                  // var year = 2018 ,
	                   //月份
	                   //之前是这样的month = parseInt($(this).attr('abbr'), 10) + 1; 
	                   month = parseInt($(this).attr('abbr'), 10);  
	                 
	         //隐藏日期对象                     
	         $(obj).datebox('hidePanel') 
	           //设置日期的值
	           .datebox('setValue', year + '-' +month ); 
	                        });
	                    }, 0);
	            },
	            //配置parser，返回选择的日期
	            parser: function (s) {
	            	//alert(s)
	                if (!s) return new Date();
	                var arr = s.split('-');
	                if(arr[1]=="上半年"){
	                	arr[1]=1;
	                } if(arr[1]=="下半年"){
	                	arr[1]=2;
	                } if(arr[1]=="第三季度"){
	                	arr[1]=3;
	                } if(arr[1]=="第四季度"){
	                	arr[1]=4;
	                }
	                
	                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
	            },
	            //配置formatter，只返回年月 之前是这样的d.getFullYear() + '-' +(d.getMonth()); 
	            formatter: function (d) { 
	                var currentMonth = (d.getMonth()+1);
	                var currentMonthStr = currentMonth < 10 ? ('0' + currentMonth) : (currentMonth + '');
	                if(currentMonthStr=="01"){
	                	currentMonthStr="上半年";
	                }
	                if(currentMonthStr=="02"){
	                	currentMonthStr="下半年";
	                }
	                if(currentMonthStr=="03"){
	                	currentMonthStr="第三季度";
	                }
	                if(currentMonthStr=="04"){
	                	currentMonthStr="第四季度";
	                }
	                return d.getFullYear() + '-' + currentMonthStr; 
	            }
	        });

	        //日期选择对象
	        var p = $(obj).datebox('panel'), 
	        //日期选择对象中月份
	        tds = false, 
	        //显示月份层的触发控件
	        span = p.find('span.calendar-text'); 
	       
	        //由于发现打开日期面板时候滚动条会自动向下移动一段距离，造成页面展示不好看(主要是因为用户可能会觉得这是一个bug)
	        //所以在初始化的时候将日期面板显示一下然后再关闭，就可以避免再次打开日期面板滚动条会移动(滚动条移动在ie中，谷歌不会出现)
	        $(obj).datebox("showPanel");
	        $(obj).datebox("hidePanel");
}
//显示年和月
function setDateBox(obj){
	
	$(obj).datebox({
	       //显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
	       onShowPanel: function () {
	    	   
	          //触发click事件弹出月份层
	          span.trigger('click'); 
	          if (!tds)
	            //延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
	            setTimeout(function() { 
	                tds = p.find('div.calendar-menu-month-inner td');
	                tds.click(function(e) {
	                	
	                   //禁止冒泡执行easyui给月份绑定的事件
	                   e.stopPropagation(); 
	                   //得到年份
	                 
	                   var year = /\d{4}/.exec(span.html())[0] ;
	                  // var year = 2018 ,
	                   //月份
	                   //之前是这样的month = parseInt($(this).attr('abbr'), 10) + 1; 
	                   month = parseInt($(this).attr('abbr'), 10);  

	         //隐藏日期对象                     
	         $(obj).datebox('hidePanel') 
	           //设置日期的值
	           .datebox('setValue', year + '-' + month); 
	                        });
	                    }, 0);
	            },
	            //配置parser，返回选择的日期
	            parser: function (s) {
	                if (!s) return new Date();
	                var arr = s.split('-');
	                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
	            },
	            //配置formatter，只返回年月 之前是这样的d.getFullYear() + '-' +(d.getMonth()); 
	            formatter: function (d) { 
	                var currentMonth = (d.getMonth()+1);
	                var currentMonthStr = currentMonth < 10 ? ('0' + currentMonth) : (currentMonth + '');
	                return d.getFullYear() + '-' + currentMonthStr; 
	            }
	        });

	        //日期选择对象
	        var p = $(obj).datebox('panel'), 
	        //日期选择对象中月份
	        tds = false, 
	        //显示月份层的触发控件
	        span = p.find('span.calendar-text'); 
	       
}
/**
 * 判断修改类型做更新限制
 * sub：只允许修改当天日期-1的报送数据
 * updSub:只允许修改除了当前日期-1天以外的数据
 * @param updType
 */
function checkUpdateType(updType,rptDate){
	var date = new Date();
	var year =date.getFullYear();
	var month = date.getMonth()+1;
	month = month <10 ? ('0'+month) : month;
	var day = date.getDate()-1;
	day = day <10 ? ('0' + day) :day;
	//console.log(year+"-"+month+"-"+day);
	var time = year+"-"+month+"-"+day;
	if(updType =='sub'){
		if(rptDate != time){
			$.messager.alert('错误', '信息已经生成文件,不允许进行修改!', 'error');
			return false;
		}
	}else
	if(updType =='updSub'){
		if(rptDate == time){
			$.messager.alert('错误', '信息还未生成文件,不允许进行更正!', 'error');
			return false;
		}
	}else{
		$.messager.alert('错误', '无效的修改!', 'error');
		return false;
	}
	return true;
}

