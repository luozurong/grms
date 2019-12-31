/**  
 * 验证电话号码  
 * @param phoneValue 要验证的电话号码  
 * @returns 匹配返回true 不匹配返回false  
 */  
function validatePhone(phoneValue) {  
    var reg = /^[1][0-9]{10}$/;  
    return reg.test(phoneValue);  
} 

/**  
 * 验证密码  
 * @param password 要验证的验证密码  
 * @returns 匹配返回true 不匹配返回false  
 */  
function validatePasswrod(password) {  
    var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$/  
    return reg.test(password);  
} 
/**  
 * 验证邮箱  
 * @param emailValue 要验证的邮箱  
 * @returns 匹配返回true 不匹配返回false  
 */  
function validateEmail(emailValue){  
    var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;  
    return reg.test(emailValue);  
}
/*
检查输入字符串是否只由英文字母和数字和下划线组成
输入：
s：字符串
返回：
如果通过验证返回true,否则返回false
*/
function isNumberOr_Letter(s) {//判断是否是数字或字母
	var regu = /^[0-9a-zA-Z\_]+$/;
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}
/**  
 * 验证姓名
 * @param name 要验证的姓名 
 * @returns 匹配返回true 不匹配返回false  
 */  
function validateName(name){  
    var reg = /^[\u4e00-\u9fa5]{2,4}$/;  
    return reg.test(name);  
}
/**  
 * 验证用户名
 * @param name 验证用户名
 * @returns 匹配返回true 不匹配返回false  
 */  
function checkUser(userAccount){
   var re = /^[a-zA-z]\w{3,8}$/;
   return re.test(str);
           
}
/*
输入：str
返回：
如果全是空返回true,否则返回false
*/
function isNull(str) {
if (str == "") return true;
var regu = /^[ ]+$/;
var re = new RegExp(regu);
return re.test(str);
}

/*
输入：str
返回：50个字符内
*/
function isLength(str) {
if (str.length>50){
	return true;

}else{
	return false;

} 
}