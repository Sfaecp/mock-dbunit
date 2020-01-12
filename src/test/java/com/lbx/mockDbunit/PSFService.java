package com.lbx.mockDbunit;

/**
 * 用于静态，私有方法mock测试的样例类
 * @author lbx
 *
 */
public class PSFService {

	public String getStr(String str) {
		return getStaticString(str);
	}
	
	public static String getStaticString(String str) {
		return "myStatic test" + str;
	}
	
	
	public String getValue(String str) {
		if (getPrivateVal(str)) {
			return "mock private function " + str;
		}
		return "getValue test" + str;
	}
	
	private boolean getPrivateVal(String flag) {
		
		return "true".equals(flag);
	}
	
}
