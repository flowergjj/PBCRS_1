/**
 * 上传文件相关公共处理
 */

/**
 * 上传文件大小检查
 */
function fileSizeCheck(maxsize, obj_file) {
	// var maxsize = 2 * 1024 * 1024;// 2M
	var browserCfg = {};
	var ua = window.navigator.userAgent;
	if (ua.indexOf("MSIE") >= 1) {
		browserCfg.ie = true;
	} else if (ua.indexOf("Firefox") >= 1) {
		browserCfg.firefox = true;
	} else if (ua.indexOf("Chrome") >= 1) {
		browserCfg.chrome = true;
	}
	var checkfileflag = checkfile(browserCfg, maxsize, obj_file);
	return checkfileflag;
}

function checkfile(browserCfg, maxsize, obj_file) {
	var filemaxsize_str = (maxsize / 1024 / 1024);
	var errMsg = "上传的附件文件不能超过" + filemaxsize_str + "M！";
	try {
		// var obj_file = document.getElementById("fileuploade");
		// if (obj_file.value == "") {
		// alert("请先选择上传文件");
		// return;
		// }
		var filesize = 0;
		if (browserCfg.ie) {
			// IE6 有效，如果出现问题，尝试使用ActiveX获取文件大小，仍然失败时，采用后台校验方式进行校验
			try {
				var obj_img = document.getElementById('tempimg');
				obj_img.dynsrc = obj_file.value;
				filesize = obj_img.fileSize;
			} catch (e) {
				try {
					// IE6 以后版本尝试使用ActiveX进行文件获取
					var fso = new ActiveXObject('Scripting.FileSystemObject');
					var file = fso.GetFile(obj_file.value);
					filesize = file.Size;
				} catch (e) {
					// 前台无法校验，转入后台校验
					return true;
				}
			}
		} else if (browserCfg.firefox || browserCfg.chrome) {
			filesize = obj_file.files[0].size;
		} else {
			// 前台无法校验，转入后台校验
			return true;
		}
		if (filesize == -1) {
			// 前台无法校验，转入后台校验
			return true;
		} else if (filesize > maxsize) {
			// alert(errMsg);
			$.messager.alert('Title', errMsg, 'error');
			return false;
		} else {
			// alert("文件大小符合要求");
			return true;
		}
	} catch (e) {
		// $.messager.alert('Title', e.name + ":" + e.message, 'error');
		// 前台无法校验，转入后台校验
		return true;
	}
	return true;
}
