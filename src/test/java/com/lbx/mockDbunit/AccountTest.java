package com.lbx.mockDbunit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lbx.mockDbunit.dto.AcctCancelNoticeFile;
import com.lbx.mockDbunit.mapper.dao.AccountManagerMapper;
import com.lbx.mockDbunit.mapper.dao.AcctCancelNoticeMapper;
import com.lbx.mockDbunit.service.account.impl.AccountManageServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AccountTest {

	@Autowired
	private AccountManageServiceImpl acctAccountManageService;
	
	@MockBean
	private AcctCancelNoticeMapper acctCancelNoticeMapper;
	
	@MockBean
	private AccountManagerMapper accountManagerMapper;

//	@Before 
//    public void setUp() {  
//		MockitoAnnotations.initMocks(this);  
//		try {
//            ReflectionTestUtils.setField(
//            		acctAccountManageService,
//                    "accountManagMapper", accountManagerMapper);
//        } catch (Exception e) {
//
//
//        }
//    } 
	
	/**
	 * https://www.jianshu.com/p/ecbd7b5a2021
	 * 方法名	描述
		Mockito.mock(classToMock)	模拟对象
		Mockito.verify(mock)	验证行为是否发生
		Mockito.when(methodCall).thenReturn(value1).thenReturn(value2)	触发时第一次返回value1，第n次都返回value2
		Mockito.doThrow(toBeThrown).when(mock).[method]	模拟抛出异常。
		Mockito.mock(classToMock,defaultAnswer)	使用默认Answer模拟对象
		Mockito.when(methodCall).thenReturn(value)	参数匹配
		Mockito.doReturn(toBeReturned).when(mock).[method]	参数匹配（直接执行不判断）
		Mockito.when(methodCall).thenAnswer(answer))	预期回调接口生成期望值
		Mockito.doAnswer(answer).when(methodCall).[method]	预期回调接口生成期望值（直接执行不判断）
		Mockito.spy(Object)	用spy监控真实对象,设置真实对象行为
		Mockito.doNothing().when(mock).[method]	不做任何返回
		Mockito.doCallRealMethod().when(mock).[method] //等价于Mockito.when(mock.[method]).thenCallRealMethod();	调用真实的方法
		reset(mock)	重置mock
	 */
	@Test
	public void testMockMarket02() {
		List<Map<String, String>> result = new ArrayList<>();
		String aTypeCode = "JHLB02";
		Map<String, String> aMarket1 = new HashMap<>();
		aMarket1.put("MARKETCODE", "JYSC04");
		aMarket1.put("MARKETNAME", "市场名JYSC04");
		result.add(aMarket1);
		Map<String, String> aMarket2 = new HashMap<>();
		aMarket2.put("MARKETCODE", "JYSC05");
		aMarket2.put("MARKETNAME", "市场名JYSC05");
		result.add(aMarket2);
		Map<String, String> aMarket3 = new HashMap<>();
		aMarket3.put("MARKETCODE", "JYSC06");
		aMarket3.put("MARKETNAME", "市场名JYSC06");
		result.add(aMarket3);
		Mockito.when(accountManagerMapper.qryMarkets()).thenReturn(result);
		List<Map<String, String>> markets = acctAccountManageService.qryMarketsByAccount(aTypeCode);
		assertEquals(1, markets.size());
	}
	
	/**
	 * "JYSC21".equals(list.get(i).get("MARKETCODE"))
	 * ||"JYSC_OTC_JN".equals(list.get(i).get("MARKETCODE"))
	 * ||"JYSC_OTC_JW".equals(list.get(i).get("MARKETCODE"))
	 * ||"JYSC25".equals(list.get(i).get("MARKETCODE")))
	 */
	@Test
	public void testMockMarket03() {
		List<Map<String, String>> result = new ArrayList<>();
		String aTypeCode = "JHLB03";
		Map<String, String> aMarket1 = new HashMap<>();
		aMarket1.put("MARKETCODE", "JYSC21");
		aMarket1.put("MARKETNAME", "市场名JYSC21");
		result.add(aMarket1);
		Map<String, String> aMarket2 = new HashMap<>();
		aMarket2.put("MARKETCODE", "JYSC_OTC_JN");
		aMarket2.put("MARKETNAME", "市场名JYSC_OTC_JN");
		result.add(aMarket2);
		Map<String, String> aMarket3 = new HashMap<>();
		aMarket3.put("MARKETCODE", "JYSC_OTC_JW");
		aMarket3.put("MARKETNAME", "市场名JYSC_OTC_JW");
		result.add(aMarket3);
		Map<String, String> aMarket4 = new HashMap<>();
		aMarket4.put("MARKETCODE", "JYSC25");
		aMarket4.put("MARKETNAME", "市场名JYSC25");
		result.add(aMarket4);
		Map<String, String> aMarket5 = new HashMap<>();
		aMarket5.put("MARKETCODE", "JYSC04");
		aMarket5.put("MARKETNAME", "市场名JYSC04");
		result.add(aMarket5);
		Mockito.when(accountManagerMapper.qryMarkets()).thenReturn(result);
		List<Map<String, String>> markets = acctAccountManageService.qryMarketsByAccount(aTypeCode);
		assertEquals(4, markets.size());
	}
	
	@Test
	public void testAccount() {
		AcctCancelNoticeFile accountFile = new AcctCancelNoticeFile();
		accountFile.setProductId("prodId1");
		accountFile.setAcctName("acctName1");
		String fid = "659852";
		
		Mockito.when(acctCancelNoticeMapper.qryNoticeFileById(fid)).thenReturn(accountFile);
		AcctCancelNoticeFile noticeFile = acctAccountManageService.qryNoticeByBid(fid);
		//Mockito.verify(acctCancelNoticeMapper).qryNoticeFileById(fid);
		assertEquals("prodId1", noticeFile.getProductId());
	}
	
	@Test
    public void testMockitoTest() {
//		List<AccTypeVO> typeA = new ArrayList<AccTypeVO>();
//		AccTypeVO type = new AccTypeVO();
//		type.setId("0001");
//		type.setAccTypeName("AAAA01");
//		type.setAccSuperTypeCode("super01");
//		type.setAccTypeCode("AA001"); 
//		AccTypeVO type1 = new AccTypeVO();
//		type1.setId("0002");
//		type1.setAccTypeName("AAAA02");
//		type1.setAccSuperTypeCode("super02");
//		type1.setAccTypeCode("AA002"); 
//		String accSuperTypeCode = AccountConstant.FUNDSUPERTYPE;
//		String ftype = "账户类型";
//		typeA.add(type);
//		typeA.add(type1);
//		Mockito.when(accountManagerMapper.qryAccTypes("99", Mockito.anyString(), Mockito.any())).thenReturn(typeA);
//		Mockito.doReturn(accountFile).when(acctCancelNoticeMapper).qryNoticeFileById(fid);
//		Mockito.verify(accountManagerMapper).qryAccTypes(null, accSuperTypeCode, ftype);
		
		
    	AcctCancelNoticeMapper noticeMapper = Mockito.mock(AcctCancelNoticeMapper.class);
    	AcctCancelNoticeFile accountFile = new AcctCancelNoticeFile();
		accountFile.setProductId("prodId1");
		accountFile.setAcctName("acctName1");
		String fid = "659852";
		Mockito.when(noticeMapper.qryNoticeFileById(fid)).thenReturn(accountFile);
    	System.out.println(noticeMapper.qryNoticeFileById(fid).getAcctName());
    	
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = Mockito.mock(LinkedList.class);
        
        // stubbing appears before the actual execution
        Mockito.when(mockedList.get(0)).thenReturn("first");

        // the following prints "first"
        System.out.println(mockedList.get(0));

        // the following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));
    }
}
