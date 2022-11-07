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
	//身份标识类型验证
	if (linkageType == 'EntCertType' || linkageType == 'IDType') {
		var regNumber;
		switch (linkageInputValue) {
		// 中征码
		case '10':
			regNumber = checkZZM(inputValue);
			break;
		// 统一社会信用代码
		case '20':
			regNumber = CheckSocialCreditCode(inputValue);
			break;
		// 组织机构代码
		case '30':
			regNumber = checkOrgCode(inputValue);
			break;
		}
		if (regNumber) {
			return;
		} else {
			return inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";

		}

	}
	//责任人身份标识类型验证
	if (linkageType == 'ArlpIDType' || linkageType == 'InfoIDType' ) {
		var regNumber;
		switch (linkageInputValue) {
		// 自然人
		case '1':
			var str = "1,2,5,6,8,9,A,B,C,X,10,20";
			if(str.indexOf(inputValue) >= 0){
				regNumber = true;
			}else{
				regNumber = false;
			}
			break;
		// 组织机构
		case '2':
			var str = "10,20,30";
			if(str.indexOf(inputValue) >= 0){
				regNumber = true;
			}else{
				regNumber = false;
			}
			break;
		}
		if (regNumber) {
			return;
		} else {
			return inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";

		}

	}
	
	//证件类型验证
	if (linkageType == 'ArlpCertType') {
		var ArlpIDType;
		if($('#InfoIDType').length > 0){
			ArlpIDType = $('#InfoIDType').combobox('getValue');
		}else{
		    ArlpIDType = $('#ArlpIDType').combobox('getValue');
		}		
		var reg;
		var regNumber;
		switch (linkageInputValue) {
		// 户口薄
		case '1':
			reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			if (inputValue.match(reg) != null) {
				regNumber = true ;
			}else{
				regNumber = false;
			}
			break;
		// 护照
		case '2':
			reg = /^([a-zA-z]|[0-9]){5,17}$/;
			if (inputValue.match(reg) != null) {
				regNumber = true ;
			}else{
				regNumber = false;
			}
			break;
		// 港澳居民来往内地通行证
		case '5':
			reg = /^([A-Z]\d{6,10}(\(\w{1}\))?)$/;
			if (inputValue.match(reg) != null) {
				regNumber = true ;
			}else{
				regNumber = false;
			}
			break;
		// 台湾同胞来往内地通行证
		case '6':
			reg = /^\d{8}|^[a-zA-Z0-9]{10}|^\d{18}$/;
			if (inputValue.match(reg) != null) {
				regNumber = true ;
			}else{
				regNumber = false;
			}
			break;
		// 外国人居留证
		case '8':
			reg = /^.*$/;
			if (inputValue.match(reg) != null) {
				regNumber = true ;
			}else{
				regNumber = false;
			}
			break;
		// 警官证
		case '9':
			reg = /^.*$/;
			if (inputValue.match(reg) != null) {
				regNumber = true ;
			}else{
				regNumber = false;
			}
			break;
		// 香港身份证
		case 'A':
			reg = /^.*$/;
			if (inputValue.match(reg) != null) {
				regNumber = true ;
			}else{
				regNumber = false;
			}
			break;
		// 港澳身份证
		case 'B':
			reg = /^([A-Z]\d{6,10}(\(\w{1}\))?)$/;
			if (inputValue.match(reg) != null) {
				regNumber = true ;
			}else{
				regNumber = false;
			}
			break;
		// 台湾身份证
		case 'C':
			reg = /^\d{8}|^[a-zA-Z0-9]{10}|^\d{18}$/;
			if (inputValue.match(reg) != null) {
				regNumber = true ;
			}else{
				regNumber = false;
			}
			break;
		// 其他证件
		case 'X':
			reg = /^.*$/;
			if (inputValue.match(reg) != null) {
				regNumber = true ;
			}else{
				regNumber = false;
			}
			break;
		// 身份证/中征码
		case '10':
			if(ArlpIDType == "1"){
				reg = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
				if (inputValue.match(reg) != null) {
					regNumber = true ;
				}else{
					regNumber = false;
				}
			}else if(ArlpIDType == "2"){
				regNumber = checkZZM(inputValue);
			}

			break;
		// 军人身份证/统一社会信用代码
		case '20':
			if(ArlpIDType == "1"){
				reg = /^.*$/;
				if (inputValue.match(reg) != null) {
					regNumber = true ;
				}else{
					regNumber = false;
				}
			}else{
				regNumber = CheckSocialCreditCode(inputValue);
			}

			break;
		// 组织机构代码
		case '30':
			regNumber = checkOrgCode(inputValue);
			break;
		}
		if (regNumber) {
			return;
		} else {
			return inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";

		}

	}
	
	//证件类型验证
	if (linkageType == 'MmbIDType') {
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
		//判断是否符合S条件
		if (linkageInputValue == '11' || linkageInputValue == '13' || linkageInputValue == '17' || linkageInputValue == '21' || linkageInputValue == '24' || linkageInputValue == '37'
				|| linkageInputValue == '91') {
			//符合就是true
			result.isTrue = true;
		} else {
			//不符合就是true
			result.isTrue = false;
		}
		result.msg = inputHtml + "栏位的取值应符合" + linkageInputHtml + "栏位的取值规则";
		return result;
	}
	
	// 账户类型不适配信用额度
	if (linkageType == 'AcctCredLine') {
		//console.info(bsSgmt);
		if(bsSgmt.acctType != "R1"){
			result.isTrue = false;
		}else{
			result.isTrue = true;
		}
		return result;
		

	}
	
	// 账户类型不适配借款金额
	if (linkageType == 'LoanAmt') {
		//console.info(bsSgmt);
		var str = "D1,D2,R4,C1";
		if(str.indexOf(bsSgmt.acctType) >= 0){
			result.isTrue = true;
		}else{
			result.isTrue = false;
		}
		return result;
		

	}
	// 账户类型不适配分次放款标志
	if (linkageType == 'Flag') {
		//console.info(bsSgmt);
		var str = "D1";
		if(str.indexOf(bsSgmt.acctType) >= 0){
			result.isTrue = true;
		}else{
			result.isTrue = false;
		}
		return result;
		

	}
	// 账户类型不适配到期日期
	if (linkageType == 'DueDate') {
		//console.info(bsSgmt);
		var str = "D1,D2,R4,R1";
		if(str.indexOf(bsSgmt.acctType) >= 0){
/*			if(bsSgmt.acctType == "R1"){
				if(inputValue != ""){}
			}*/
			result.isTrue = true;
		}else{
			result.isTrue = false;
		}
		return result;
		

	}
	// 账户类型不适配还款方式
	if (linkageType == 'RepayMode') {
		//console.info(bsSgmt);
		var str = "D1,R4,R1";
		if(str.indexOf(bsSgmt.acctType) >= 0){
/*			if(bsSgmt.acctType == "R1"){
				if(inputValue != ""){}
			}*/
			result.isTrue = true;
		}else{
			result.isTrue = false;
		}
		return result;
		

	}
	// 账户类型不适配还款频率
	if (linkageType == 'RepayFreqcy') {
		//console.info(bsSgmt);
		var str = "D1,R4,R1";
		if(str.indexOf(bsSgmt.acctType) >= 0){
			result.isTrue = true;
		}else{
			result.isTrue = false;
		}
		return result;
		

	}
	
	// 账户类型不适配借款期限分类
	if (linkageType == 'LoanTimeLimCat') {
		//console.info(bsSgmt);
		var str = "D1,R4";
		if(str.indexOf(bsSgmt.acctType) >= 0){
			result.isTrue = true;
		}else{
			result.isTrue = false;
		}
		return result;
		

	}
	
	// 账户类型不适配贷款实际投向
	if (linkageType == 'ActInvest') {
		//console.info(bsSgmt);
		var str = "D1,R1,R4";
		if(str.indexOf(bsSgmt.acctType) >= 0){
			result.isTrue = true;
		}else{
			result.isTrue = false;
		}
		return result;
		

	}
	
	// 账户类型不适配资产转让标志
	if (linkageType == 'AssetTrandFlag') {
		//console.info(bsSgmt);
		var str = "D1,R1,R4";
		if(str.indexOf(bsSgmt.acctType) >= 0){
			result.isTrue = true;
		}else{
			result.isTrue = false;
		}
		return result;
		

	}
	
	// 账户类型不适配剩余还款月数
	if (linkageType == 'PymtPrd') {
		//console.info(bsSgmt);
		var str = "R1";
		if(str.indexOf(bsSgmt.acctType) >= 0){
			result.isTrue = true;
		}else{
			result.isTrue = false;
		}
		return result;
		

	}

}

function checkZZM(value){
	var reg = /^[A-Z0-9]{3}[0-9]{4,16}$/;
	var re = new RegExp(reg);
	if(!re.test(value)){
		//return false;
		return true;
	}
	var code = value.substring(14,16);
	var idCode = value.substring(0,14);
	var weight_factor = [1,3,5,7,11,2,13,1,1,17,19,97,23,29];
	var arr = idCode.split("");
	var len = arr.length;
	var num = 0;
	for(var i = 0;i<len;i++){
		if(arr[i] >= "A" && arr[i] <= "Z"){
			arr[i] = enToNumberFun(arr[i]);
		
		}
		num = num + arr[i] * weight_factor[i];
		
	}
	var resisue = num%97 +1;
	if(resisue < 10){
		resisue = "0" + resisue;
		
	}
	if(resisue == code){
		return true;
	}
	//return false;
	return true;
}
//将大写字母的值转换为机器处理用代码字符数值（只考虑大写）
function enToNumberFun(val){
	var b = val.charCodeAt() - 55;
	return b;
}

//组织机构代码
function checkOrgCode(code) {
    var ws = [3, 7, 9, 10, 5, 8, 4, 2];
    var str = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    var reg = /^([0-9A-Z])+$/;// /^[A-Za-z0-9]{8}-[A-Za-z0-9]{1}$/
    if(!reg.test(code)){
    	return false;
    }
    var sum = 0;
    for (var i = 0; i < 8; i++){
        sum += str.indexOf(code.charAt(i)) * ws[i];
    }
    var c9 = 11 - (sum % 11);
    if(c9 == 10){
    	c9 = "X";
    }else if(c9 == 11){
    	c9 = '0';
    }
   // console.info(code.charAt(8));
    if (c9 != code.charAt(8)) {
        return false;
    }
    return true;
}


//统一社会信用编码
function CheckSocialCreditCode(Code) {
  var patrn = /^[0-9A-Z]+$/;
  //18位校验及大写校验
  if ((Code.length != 18) || (patrn.test(Code) == false)) {
    return false;
  }
  else {
    var Ancode;//统一社会信用代码的每一个值
    var Ancodevalue;//统一社会信用代码每一个值的权重 
    var total = 0;
    var weightedfactors = [1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28];//加权因子 
    var str = '0123456789ABCDEFGHJKLMNPQRTUWXY';
    //不用I、O、S、V、Z 
    for (var i = 0; i < Code.length - 1; i++) {
      Ancode = Code.substring(i, i + 1);
      Ancodevalue = str.indexOf(Ancode);
      total = total + Ancodevalue * weightedfactors[i];
      //权重与加权因子相乘之和 
    }
    var logiccheckcode = 31 - total % 31;
    if (logiccheckcode == 31) {
      logiccheckcode = 0;
    }
    var Str = "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,J,K,L,M,N,P,Q,R,T,U,W,X,Y";
    var Array_Str = Str.split(',');
    logiccheckcode = Array_Str[logiccheckcode];
 
 
    var checkcode = Code.substring(17, 18);
    if (logiccheckcode != checkcode) {
      return false;
    }
    return true;
  }
}
