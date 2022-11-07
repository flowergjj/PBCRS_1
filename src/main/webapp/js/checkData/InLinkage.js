/**
 * 
 * @param linkageType 联动类型：自定义
 * @param inputValue  input框的值
 * @param inputHtml   input框对应的中文html文本
 * @param linkageInputValue input框的联动对象的值
 * @param linkageInputHtml input框的联动对象的的html文本
 * @returns {String}
 */
function linkageA(linkageType, inputValue, inputHtml,linkageInputValue,linkageInputHtml) {

	
	//证件类型验证
	if (linkageType == 'IDType') {
		var regNumber;
		switch (linkageInputValue) {
		// 身份证
		case '10':
			regNumber = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
			break;
		// 户口薄
		case '1':
			regNumber = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			break;
		// 护照
		case '2':
			regNumber = /^([a-zA-z]|[0-9]){5,17}$/;
			break;
		// 港澳居民来往内地通行证
		case '5':
			regNumber = /^([A-Z]\d{6,10}(\(\w{1}\))?)$/;
			break;
		// 台湾同胞来往内地通行证
		case '6':
			regNumber = /^\d{8}|^[a-zA-Z0-9]{10}|^\d{18}$/;
			break;
		// 外国人居留证
		case '8':
			regNumber = /^.*$/;
			break;
		// 警官证
		case '9':
			regNumber = /^.*$/;
			break;
		// 香港身份证
		case 'A':
			regNumber = /^.*$/;
			break;
		// 港澳身份证
		case 'B':
			regNumber = /^([A-Z]\d{6,10}(\(\w{1}\))?)$/;
			break;
		// 台湾身份证
		case 'C':
			regNumber = /^\d{8}|^[a-zA-Z0-9]{10}|^\d{18}$/;
			break;
		// 其他证件
		case 'X':
			regNumber = /^.*$/;
			break;
		// 军人身份证
		case '20':
			regNumber = /^.*$/;
			break;
		}
		if (inputValue.match(regNumber) != null) {
			return;
		} else {
			return inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";

		}

	}

	// 邮箱验证
	if (linkageType == 'Email') {
		var regNumber;

	}
	// 学位和学历联动
	if (linkageType == 'eduLevel') {
		switch (linkageInputValue) {
		// 研究生
		case '10':
			if ('1,2,3,4,5,9'.indexOf(inputValue) < 0) {
				return inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";
			}
			break;
		// 本科
		case '20':
			if ('4,5,9'.indexOf(inputValue) < 0) {
				return inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";
			}
			break;
		//其他
		default:
			if ('5,9'.indexOf(inputValue) < 0) {
				return inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";
			}
			break;
		}

	}
	// 当一个input框有值时，另一个input框也必须有值
	if (linkageType == 'sameStatus') {
		if(linkageInputValue.length>0 && inputValue.length>0 ){
			return ;
		}else if(linkageInputValue.length==0 && inputValue.length==0){
			return ;
		}else{
			return inputHtml + "栏位与" + linkageInputHtml + "栏位应该同时有值或者同时没值";
		}

	}
	//借贷月度表现中账户状态为3 ，4时，账户关闭日期不能为空
	if (linkageType == 'closeDate') {
		if('3,4'.indexOf(linkageInputValue)>=0){
			if(inputValue==null || inputValue=='')
				return inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";
		}
	}
	//借贷非月度表现中账户状态为2时，账户关闭日期不能为空
	if (linkageType == 'notCloseDate') {
		if('3,4'.indexOf(linkageInputValue)>=0){
			if(inputValue==null || inputValue=='')
				return inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";
		}
	}
	
	
	// 借贷业务种类联动
	if (linkageType == 'busiLines') {
		switch (linkageInputValue) {
		
		case '1':
			if ('11,12,13,21,32,33,41,52,53,91,99'.indexOf(inputValue) < 0) {
				return inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";
			}
			break;
		
		case '2':
			if ('71,81,82'.indexOf(inputValue) < 0) {
				return inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";
			}
			break;
		case '3':
			if ('61,62,63,64'.indexOf(inputValue) < 0) {
				return inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";
			}
			break;
		case '4':
			if ('92'.indexOf(inputValue) < 0) {
				return inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";
			}
			break;
		case '5':
			if ('A1'.indexOf(inputValue) < 0) {
				return inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";
			}
			break;
		case '6':
			if ('B1'.indexOf(inputValue) < 0) {
				return inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";
			}
			break;
		
		
		}
		

	}
	// 账户类型不适配
	if (linkageType == 'accountType') {
		if(inputValue!=null && inputValue!='')
		
			return inputHtml + "栏位不适用于此类账户";
		

	}
	// 个人借贷还款责任金额判断
	if (linkageType == 'arlpAmt') {
		switch (linkageInputValue) {
	
		case '2':
			if (inputValue==null || inputValue=='') {
				return inputHtml + "栏位应该出现值！";
			}
			break;
	
		default:
			if (inputValue!=null && inputValue!='') {
				return inputHtml + "栏位不应该出现值！";
			}
			break;
		}
		

	}
	// 个人借贷联保标志判断
	if (linkageType == 'wartySign') {
		switch (linkageInputValue) {
	
		case '2':
			if (inputValue==null || inputValue=='') {
				return inputHtml + "栏位应该出现值！";
			}
			break;
	
		default:
			if (inputValue!=null && inputValue!='') {
				return inputHtml + "栏位不应该出现值！";
			}
			break;
		}
		

	}
	
	// 个人借贷联保标志判断
	if (linkageType == 'maxGuarMcc') {
		switch (linkageInputValue) {
	
		case '2':
			if (inputValue==null || inputValue=='') {
				return inputHtml + "栏位应该出现值！";
			}
			break;
	
		default:
			if (inputValue!=null && inputValue!='') {
				return inputHtml + "栏位不应该出现值！";
			}
			break;
		}
		

	}
	
	
	

}

/**
 * 
 * @param linkageType 联动类型：自定义
 * @param inputValue  input框的值
 * @param inputHtml   input框对应的中文html文本
 * @param linkageInputValue input框的联动对象的值
 * @param linkageInputHtml input框的联动对象的的html文本
 * @returns {String}
 */
function linkageS(linkageType, inputValue, inputHtml,linkageInputValue,linkageInputHtml) {
	var result = new Object();
	// 就业状况
	if (linkageType == 'empStatus') {

		if (linkageInputValue == '11' || linkageInputValue == '13' || linkageInputValue == '17' || linkageInputValue == '21' || linkageInputValue == '24' || linkageInputValue == '37'
				|| linkageInputValue == '91') {
			result.isTrue = true;
		} else {
			result.isTrue = false;
		}
		result.msg = inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";
		return result;
	}
	
	// 婚姻状况
	if (linkageType == 'mariStatus') {

		if (linkageInputValue == '20' || linkageInputValue == '21' || linkageInputValue == '22' || linkageInputValue == '23') {
			result.isTrue = true;
		} else {
			result.isTrue = false;
		}
		return result;
	}
	

	// 
	if (linkageType == 'acctType') {
		if(bsSgmt.acctType == 'D1' && linkageInputValue=='11'){
			result.isTrue = true;
		} else {
			result.isTrue = false;
		}
		return result;
	}
	
	// 
	if (linkageType == 'flag') {
		if(bsSgmt.acctType == 'D1'){
			result.isTrue = true;
		} else {
			result.isTrue = false;
		}
		return result;
	}
}
