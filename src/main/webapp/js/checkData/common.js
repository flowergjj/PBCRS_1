function checkData(tableName) {
	var checkResult = true;
	// 提交前校验
	$('#' + tableName + ' :input[checkId]').each(
			function() {
				// 解析input框
				var result = analyzeInput(this,tableName);

				if (result.DataRestrict.indexOf('A') >= 0) {

					if (result.DataRestrict == 'AM') {
						if (result.value == null || result.value == '') {
							$.messager.alert('错误', result.htmlMsg+"栏位的值不能为空", 'error');
							checkResult = false;
							return false;
						}
						var errorMsg;
						if ((errorMsg = checkRules(result.DataType, result.value, result.htmlMsg)) != null) {
							$.messager.alert('错误', errorMsg, 'error');
							checkResult = false;
							return false;
						}
					} else {
						// AO为空不需要检验
						if (result.value == null || result.value == '') {

						} else {
							var errorMsg;
							if ((errorMsg = checkRules(result.DataType, result.value, result.htmlMsg)) != null) {
								$.messager.alert('错误', errorMsg, 'error');
								checkResult = false;
								return false;
							}
						}
					}
					if (result.linkageList != null) {
						for ( var i = 0; i < result.linkageList.length; i++) {
							var linkage = result.linkageList[i];
							if (linkage.linkageAS == 'A') {
								var errorMsg = linkageA(linkage.linkageType, result.value, result.htmlMsg, linkage.linkageInputValue,
										linkage.linkageInputHtml);
								if (errorMsg != null) {
									$.messager.alert('错误', errorMsg, 'error');
									checkResult = false;
									return false;
								}
							}
						}

					}
				}

				if (result.DataRestrict.indexOf('S') >= 0) {
					var flag = true;
					// 如果S没有联动条件，则只进行数据类型和约束判断
					if (result.linkageList == null) {
						/*
						 * if (result.DataRestrict == 'SM') { if (result.value ==
						 * null || result.value == '') { $.messager.alert('错误',
						 * result.htmlMsg + "栏位必须出现值!", 'error');
						 * checkResult=false; return false; }
						 * 
						 * if ((errorMsg = checkRules(result.DataType,
						 * result.value, result.htmlMsg)) != null) {
						 * $.messager.alert('错误', errorMsg, 'error');
						 * checkResult=false; return false; } } else {
						 */
						// SO等可以为空的数据为空时不需要检验
						if (result.value == null || result.value == '') {

						} else {
							if ((errorMsg = checkRules(result.DataType, result.value, result.htmlMsg)) != null) {
								$.messager.alert('错误', errorMsg, 'error');
								checkResult = false;
								return false;
							}
						}
						/* } */

					} else {
						for ( var i = 0; i < result.linkageList.length; i++) {
							var linkage = result.linkageList[i];

							if (linkage.linkageAS == 'S') {
								var msg = linkageS(linkage.linkageType, result.value, result.htmlMsg, linkage.linkageInputValue,
										linkage.linkageInputHtml);
								flag = msg.isTrue;
								// 满足S条件
								if (flag == true) {
									if (result.DataRestrict == 'SM') {
										if (result.value == null || result.value == '') {
											$.messager.alert('错误', result.htmlMsg + "栏位必须出现值!", 'error');
											checkResult = false;
											return false;
										}

										if ((errorMsg = checkRules(result.DataType, result.value, result.htmlMsg)) != null) {
											$.messager.alert('错误', errorMsg, 'error');
											checkResult = false;
											return false;
										}
									} else {
										// SO等可以为空的数据为空时不需要检验
										if (result.value == null || result.value == '') {

										} else {
											if ((errorMsg = checkRules(result.DataType, result.value, result.htmlMsg)) != null) {
												$.messager.alert('错误', errorMsg, 'error');
												checkResult = false;
												return false;
											}
										}
									}
								} else {
									// 不满足S条件则该栏位不应该出现值
									if (result.value != null && result.value != '') {
										$.messager.alert('错误', result.htmlMsg + "栏位不该出现值!", 'error');
										checkResult = false;
										return false;
									}
								}
								// 只有当满足S条件的时候才能进行A条件联动(默认flag是true,如果前面的S条件不满足则设置为false)
							} else if (linkage.linkageAS == 'A' && flag == true) {
								var errorMsg = linkageA(linkage.linkageType, result.value, result.htmlMsg, linkage.linkageInputValue,
										linkage.linkageInputHtml);
								if (errorMsg != null) {
									$.messager.alert('错误', errorMsg, 'error');
									checkResult = false;
									return false;
								}
							}
						}
					}

				}

			});
	return checkResult;
}
function checkRules(rule, value, msg) {
	// ANC100
	var reg1 = /(^ANC)(\d+$)/;
	// ANC..100
	var reg2 = /(^ANC..)(\d+$)/;
	// N100
	var reg3 = /(^N)(\d+$)/;
	// N..100
	var reg4 = /(^N..)(\d+$)/;
	// AN100
	var reg5 = /(^AN)(\d+$)/;
	// AN..100
	var reg6 = /(^AN..)(\d+$)/;

	// uInt..5
	var reg7 = /(^uInt..)(\d+$)/;
	// Int..5
	var reg8 = /(^Int..)(\d+$)/;
	// Float(2,5)
	var reg9 = /(^Float\()(\d+)(,)(\d+)(\)$)/;
	var r;
	if (rule == 'Enum') {
		return;
	}
	if (rule == 'Date') {
		return;
	}
	if (rule == 'Year') {
		return;
	}
	if ((r = rule.match(reg1)) != null) {
		// console.log(r+"--"+reg1);
		if (value.length == r[2]) {
			return;
		}
		return "\"" + msg + "\"栏位内容长度应该为" + r[2];

	}
	if ((r = rule.match(reg2)) != null) {
		// console.log(r+"--"+reg2);
		if (value.length <= r[2] && value.length > 0) {
			return;
		}
		return "\"" + msg + "\"栏位内容长度应该小于等于" + r[2];
	}
	if ((r = rule.match(reg3)) != null) {
		// console.log(r+"--"+reg3);
		var regNumber = /^[1-9]\d*$/;
		if (value.length == r[2] && value.match(regNumber) != null) {
			return;
		}
		return "\"" + msg + "\"栏位内容长度应该等于" + r[2] + ",并且为数字！";

	}
	if ((r = rule.match(reg4)) != null) {
		// console.log(r+"--"+reg4);
		var regNumber = /^[1-9]\d*$/;
		if (value.length <= r[2] && value.match(regNumber) != null && value.length > 0) {
			return;
		}
		return "\"" + msg + "\"栏位内容长度应该小于等于" + r[2] + ",并且为数字！";
	}
	if ((r = rule.match(reg5)) != null) {
		// console.log(r+"--"+reg5);
		var regAN = /^[a-zA-Z0-9]*$/;
		if (value.length == r[2] && value.match(regAN) != null) {
			return;
		}
		return "\"" + msg + "\"栏位内容长度应该等于" + r[2] + ",并且为数字和英文字母！";
	}
	if ((r = rule.match(reg6)) != null) {
		var regAN = /^[a-zA-Z0-9]*$/;
		if (value.length <= r[2] && value.match(regAN) != null && value.length > 0) {
			return;
		}
		return "\"" + msg + "\"栏位内容长度应该小于等于" + r[2] + ",并且为数字和英文字母！";
	}
	if ((r = rule.match(reg7)) != null) {
		// console.log(r+"--"+reg7);
		var regNumber = /^([1-9]\d*)|(\d)$/;
		if (value.match(regNumber) != null && value <= pow(10, r[2], 1) && value >= 0) {
			return;
		}
		return "\"" + msg + "\"栏位内容取值应该在0到" + pow(10, r[2], 1) + "之间";
	}
	if ((r = rule.match(reg8)) != null) {
		// console.log(r+"--"+reg8);
		var regNumber = /^-?([1-9]\d*)|(\d)$/;
		if (value.match(regNumber) != null && value <= pow(10, r[2], 1) && value >= pow(10, r[2], -1)) {
			return;
		}
		return "\"" + msg + "\"栏位内容取值应该在" + pow(10, r[2], -1) + "到" + pow(10, r[2], 1) + "之间";
	}
	if ((r = rule.match(reg9)) != null) {
		var regFloat = new RegExp('^-?[0-9]\\d*\\.[0-9]{' + r[4] + '}$', 'g');
		console.info(value.match(regFloat));
		if (value.match(regFloat) != null && value < pow(10, r[2], 1) && value > pow(10, r[2], -1)) {
			return;
		}
		return "\"" + msg + "\"栏位内容取值应该在" + pow(10, r[2], -1) + "到" + pow(10, r[2], 1) + "之间,并且保留小数点后" + r[4] + "位！";
	}
}
function pow(a, b, c) {
	var inta = parseInt(a);
	var intb = parseInt(b);
	var result = 1;
	for ( var i = 0; i < intb; i++) {
		result = result * inta;
	}
	return c * result;
}

/**
 * 解析input框
 * 
 * @param obj
 * @returns {___param0}
 */
function analyzeInput(obj,tableName) {
	var param = new Object();
	// 得到input框的值
	param.value = getInputValue(obj);
	// input框对应的中文文本
	param.htmlMsg = $(obj).parent().prev().html().replace(/：|:|&nbsp;/g, '');
	// 解析checkId参数
	var array = $(obj).attr('checkId').split('_');
	if (array[0] != null) {
		// input框的数据类型
		param.DataType = array[0];
	}
	if (array[1] != null) {
		// input框的约束
		param.DataRestrict = array[1];
	}
	if (array[2] != null) {
		// input框的联动规则
		var arrList = array[2].split(',');
		var arrlinkageList = new Array();
		var SorA;
		var num = 1;
		for ( var i = 0; i < arrList.length; i++) {

			var linkage = arrList[i];

			if (linkage.indexOf('A:') >= 0) {
				SorA = 'A';
			} else if (linkage.indexOf('S:') >= 0) {
				SorA = 'S';
			}
			linkage = linkage.substring(3, linkage.length - 1);
			var list = linkage.split(';');
			for ( var j = 0; j < list.length; j++) {
				var o = new Object();
				o.linkageAS = SorA;
				var arr = list[j].split(':');
				o.linkageType = arr[0];
				o.linkageInputHtml = $('#' + arr[1]).parent().prev().html().replace(/：|:|&nbsp;/g, '');
				o.linkageInputValue = getInputValue('#'+tableName+' :input[id="' + arr[1]+'"]');
				arrlinkageList[arrlinkageList.length] = o;
			}
			// var arr = linkage.split(':');
			// var o = new Object();
			/*
			 * o.linkageType = arr[0]; o.linkageInputHtml = $('#' +
			 * arr[1]).parent().prev().html().replace(/：|:|&nbsp;/g, '');
			 * o.linkageInputValue = getInputValue('#' + arr[1]);
			 * arrlinkageList[i] = o;
			 */
		}
		param.linkageList = arrlinkageList;
	}
	return param;
}
function getInputValue(obj) {
	if ($(obj).attr('class').indexOf("easyui-combobox") >= 0) {
		return $(obj).combobox('getValue');
	} else if ($(obj).attr('class').indexOf("easyui-textbox") >= 0) {
		return $(obj).textbox('getValue');
	} else if($(obj).attr('class').indexOf("easyui-datebox") >= 0){
		return $(obj).datebox('getValue');
	}
	else{
		return $(obj).val();
	}
}