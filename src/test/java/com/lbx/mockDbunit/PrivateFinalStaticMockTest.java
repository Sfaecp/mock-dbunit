package com.lbx.mockDbunit;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbx.mockDbunit.config.EncryptPlaceholderConfig;
import com.lbx.mockDbunit.mapper.dao.AcctCancelNoticeMapper;
import com.lbx.mockDbunit.service.account.impl.AccountManageServiceImpl;

/**
 * mock私有方法，静态方法
 * https://www.cnblogs.com/ljw-bim/p/9391770.html
 * @author T18
 *
 */
@RunWith(value = PowerMockRunner.class)
@PrepareForTest(PSFService.class)
//@PowerMockRunnerDelegate(SpringRunner.class)
//@SpringBootTest
//@PowerMockIgnore(value = { "javax.management.*", "javax.net.ssl.*"})
public class PrivateFinalStaticMockTest {
	
	@InjectMocks
	private PSFService psfService;
	
	/**
	 * mock 静态方法
	 */
	@Test
	public void testStatic() {
		PowerMockito.mockStatic(PSFService.class);
		PowerMockito.when(PSFService.getStaticString(ArgumentMatchers.anyString())).thenReturn("static mock success");
		String str = PSFService.getStaticString("AAA");
		Assert.assertEquals(str, "static mock success");
	}
	
	/**
	 * mock 静态方法
	 */
	@Test
	public void testStatico() {
		PowerMockito.mockStatic(PSFService.class);
		PowerMockito.when(PSFService.getStaticString(ArgumentMatchers.anyString())).thenReturn("static mock success");
		String str = psfService.getStr("AAA");
		Assert.assertEquals(str, "static mock success");
	}
	
	/**
	 * mock 私有方法
	 */
	@Test
	public void testPrivate() throws Exception {
		PSFService spy = PowerMockito.spy(psfService);
		PowerMockito.when(spy, "getPrivateVal", ArgumentMatchers.any()).thenReturn(true);
		String val = spy.getValue("SSS");
		Assert.assertEquals(val, "mock private function SSS");
	}
}
