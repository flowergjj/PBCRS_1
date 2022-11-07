var path = getAppPath();
var idIndex = 0;
$(function() {
	var req = new Object();
	req = GetRequest();

	var noticeid = req['NOTICE_ID'];
	openUpdateDialog(noticeid);

});

// 公告查看页面
function openUpdateDialog(noticeid, bussCode) {
	$('#update_attachFileRow').hide();
	$.ajax({
		url : path + '/systemNoticeManage/getNoticeByID.html',
		type : "post",
		async : false,
		data : {
			NOTICE_ID : noticeid
		},
		success : function(data) {
			var title = '<span style="font-size:45px;">'+data.TITLE+'</span>';
			var content = '<span style="font-size:25px;">'+data.CONTENT+'</span>';
			var bussCode = data.BUSSINESS_CODE;
			$('#TITLE').html(title);
			$('#CONTENT').html(content);
			
			$('#updateAttachArea').html('');
			if (bussCode != null && bussCode != '') {
				showUpdateFile(bussCode);
			}
		}
	});
}

function showUpdateFile(bussinessCode) {
	var imglist = findImgList(bussinessCode);
	// alert('imgList=' + JSON.stringify(imglist));
	if (imglist != null && imglist.length > 0) {
		for ( var listindex = 0; listindex < imglist.length; listindex++) {
			var attachId = 'authImg_' + (idIndex += 1);
			// alert(imglist[listindex].imgName + "|" +
			// imglist[listindex].imgId);
			var str = '<div id="'
					+ attachId
					+ '" style="margin-top: 10px;"><input type="text" style="width:281px;height:20px;" readonly="readonly" value="'
					+ imglist[listindex].imgName
					+ '"/><b style="width:2px;"></b>';
			str += '<input type="button" value="查看" style="width:63px;height:19.8px;" onclick="openfile(\''
					+ imglist[listindex].imgId + '\');" /></div>';
			$('#updateAttachArea').append(str);
		}
		$('#update_attachFileRow').show();
	} else {
		$('#updateAttachArea').html("无附件");
	}
}

function findImgList(imgId) {
	var list = null;
	$.ajax({
		url : path + '/systemNoticeManage/findImgList.html',
		type : "post",
		async : false,
		data : {
			bussCode : imgId
		},
		success : function(data) {
			list = data;
		}
	});

	return list;
}

function openfile(imgid) {
	window.parent.open(path
			+ '/systemNoticeManage/fileDownload.html?acType=017002&imgId='
			+ imgid);
}

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
