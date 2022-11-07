var filetype = '.pdf,.ps,.jpg,.jpeg,.jpe,.jfif,.bmp,.dib,.png,.gif,.tif,.tiff';
var reg_filetype = !/\.(pdf|ps|jpg|jpeg|jpe|jfif|bmp|dib|png|gif|tif|tiff)$/

$(function() {
	jQuery.download = function(url, data, method) {
	    if( url && data ){ 
	        var inputs = '';
	        jQuery.each(data, function(id, item){ 
	            inputs+='<input type="hidden" name="'+ id +'" value="'+ item +'" />'; 
	        });
	        jQuery('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>')
	        .appendTo('body').submit().remove();
	    };
	};
	
	/**
	 * validatebox规则扩展 
	 */
	$.extend($.fn.validatebox.defaults.rules, {
		authfile:{
			validator : function(value) {
				return reg_filetype.test(value);
			},
			message : '允许的文件格式:' + filetype
		}
	});
	
	
	/**
	 * Messager扩展
	 */
	$.extend($.messager, {
		password:function(title,msg,fn){
			var content="<div class=\"messager-icon messager-question\"></div>"+"<div>"+msg+"</div>"+"<br/>"+"<div style=\"clear:both;\"/>"+"<div><input class=\"messager-input\" type=\"password\"/></div>";
			var buttons={};
			buttons[$.messager.defaults.ok]=function(){
				win.window("close");
				if(fn){
					fn($(".messager-input",win).val());
					return false;
				}
			};
			buttons[$.messager.defaults.cancel]=function(){
				win.window("close");
			};
			var win=createWindow(title,content,buttons);
			win.find("input.messager-input").focus();
			return win;
		},
		perlogin:function(title,msg,fn){
            var cruserid = null;
            $.ajax({
                url: path + '/pbcUserModify/pbcUserModify_cruser.html',
                async: false,
                data: {
                    CRSYSTEMID: '02',
                    USERID: null
                },
                success: function(ret) {
                    cruserid = ret.CRUSERID;
                    
                    if(typeof(cruserid) == 'undefined') {
                        cruserid = '';
                    }
                }
            });
                        
			var content=
				"<table style=\"width: 100%; height: 100%\" collpadding='5'>" +
				"<tr>" +
				"<td collspan='2'>人行个人征信系统用户名:</td>" + 
				"</tr>" +
				"<tr>" +
				"<td collspan='2'><input class=\"messager-id \" type=\"text\" readonly=\"readonly\" value=\"" + cruserid + "\" style=\"width: 100%;\"/></td>" +
				"</tr>" +
				"<tr></tr>" +
				"<tr>" +
				"<td collspan='2'>人行个人征信系统密码:</td>" +
				"</tr>" + 
				"<tr>" +
				"<td collspan='2'><input class=\"messager-pass\" type=\"password\" style=\"width: 100%;\"/></td>" +
				"</tr>" +
				"</table>";
				
			var buttons={};
			buttons[$.messager.defaults.ok]=function(){
				win.window("close");
				if(fn){
					fn($(".messager-id",win).val(), $(".messager-pass",win).val());
					return false;
				}
			};
			buttons[$.messager.defaults.cancel]=function(){
				win.window("close");
			};
			var win=createWindow(title,content,buttons);
			win.find("input.messager-id").focus();
			return win;
		},
		entlogin:function(title,msg,fn){
            var crorgid = null;
            var cruserid = null;
            $.ajax({
                url: path + '/pbcUserModify/pbcUserModify_cruser.html',
                async: false,
                data: {
                    CRSYSTEMID: '01',
                    USERID: null
                },
                success: function(ret) {
                    crorgid = ret.CRORGID;
                    cruserid = ret.CRUSERID;
                    
                    if(typeof(crorgid) == 'undefined') {
                        crorgid = '';
                    }
                    
                    if(typeof(cruserid) == 'undefined') {
                        cruserid = '';
                    }
                }
            });
            
			var content=
				"<table style=\"width: 100%; height: 100%\" collpadding='5'>" +
				"<tr>" +
				"<td collspan='2'>人行企业征信系统机构编号:</td>" + 
				"</tr>" +
				"<tr>" +
				"<td collspan='2'><input class=\"messager-org \" type=\"text\" readonly=\"readonly\" value=\"" + crorgid + "\" style=\"width: 100%;\"/></td>" +
				"</tr>" +
				"<tr></tr>" +
				"<tr>" +
				"<td collspan='2'>人行企业征信系统用户名:</td>" +
				"</tr>" + 
				"<tr>" +
				"<td collspan='2'><input class=\"messager-id\" type=\"text\" readonly=\"readonly\" value=\"" + cruserid + "\" style=\"width: 100%;\"/></td>" +
				"</tr>" +
				"<tr></tr>" +
				"<tr>" +
				"<td collspan='2'>人行企业征信系统密码::</td>" +
				"</tr>" + 
				"<tr>" +
				"<td collspan='2'><input class=\"messager-pass\" type=\"password\" style=\"width: 100%;\"/></td>" +
				"</tr>" +
				"</table>";
		var buttons={};
		buttons[$.messager.defaults.ok]=function(){
			win.window("close");
			if(fn){
				fn($(".messager-org",win).val(),$(".messager-id",win).val(), $(".messager-pass",win).val());
				return false;
			}
		};
		buttons[$.messager.defaults.cancel]=function(){
			win.window("close");
		};
		var win=createWindow(title,content,buttons);
		win.find("input.messager-org").focus();
		return win;
	}
	});
})

/**
 * 创建messagebox框扩展
 * @param title
 * @param content
 * @param buttons
 * @returns
 */
function createWindow(title,content,buttons){
		var win=$("<div class=\"messager-body\"></div>").appendTo("body");
		win.append(content);
		if(buttons){
			var tb=$("<div class=\"messager-button\"></div>").appendTo(win);
			for(var btn in buttons){
				$("<a></a>").attr("href","javascript:void(0)").text(btn).css("margin-left",10).bind("click",eval(buttons[btn])).appendTo(tb).linkbutton();
			}
		}
		win.window({
			title:title,
			noheader:(title?false:true),
			width:300,
			height:"auto",
			modal:true,
			collapsible:false,
			minimizable:false,
			maximizable:false,
			resizable:false,
			onClose:function(){
				setTimeout(function(){
					win.window("destroy");
				},100);
			}
		});
		win.window("window").addClass("messager-window");
		win.children("div.messager-button").children("a:first").focus();
		return win;
	};

/**
 * 是否为日期
 * @param dateString
 * @returns {Boolean}
 */
function isDate(dateString)   {
	if(typeof(dateString) == "undefined" || !jQuery.trim(dateString)){
		return true;
	}
	var reg = /^(\d{4})-(\d{2})-(\d{2})$/;     
	var str = dateString;     
	var arr = reg.exec(str);     
	if (!reg.test(str)){
		return false;
	}
		return true;
} 

/**
 * 是否是日期时间
 * @param dateTimeString
 * @returns {Boolean}
 */
function isDateTime(dateTimeString)   {
	if(typeof(dateTimeString) == "undefined" || !jQuery.trim(dateTimeString)){
		return true;
	}
	var reg = /^(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2}):(\d{2})$/;     
	var str = dateTimeString;     
	var arr = reg.exec(str);     
	if (!reg.test(str)){
		return false;
	}
		return true;
}

function isAuthFileLegal(value) {
	return reg_filetype.test(value);
}

/*  ---------------------------------------------javascript对象扩展方法------------------------------------------------------- */
/**
 * 格式化日期
 * @param format 日期格式
 * @returns
 */
Date.prototype.format = function(format) {
	var o={"M+":this.getMonth()+1,"d+":this.getDate(),"h+":this.getHours(),"m+":this.getMinutes(),"s+":this.getSeconds(),"q+":Math.floor((this.getMonth()+3)/3),"S":this.getMilliseconds()};
	if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o) if (new RegExp("(" + k + ")").test(format)) format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

/**
* 删除左右两端的空格
*/
String.prototype.trim=function()
{
     return this.replace(/(^\s*)|(\s*$)/g, '');
}
/**
* 删除左边的空格
*/
String.prototype.ltrim=function()
{
     return this.replace(/(^\s*)/g,'');
}
/**
* 删除右边的空格
*/
String.prototype.rtrim=function()
{
     return this.replace(/(\s*$)/g,'');
}

