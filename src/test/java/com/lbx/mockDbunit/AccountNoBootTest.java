package com.lbx.mockDbunit;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbx.mockDbunit.dto.AcctCancelNoticeFile;
import com.lbx.mockDbunit.mapper.dao.AccountManagerMapper;
import com.lbx.mockDbunit.mapper.dao.AcctCancelNoticeMapper;
import com.lbx.mockDbunit.service.account.impl.AccountManageServiceImpl;

@RunWith(SpringRunner.class)
public class AccountNoBootTest {

	@InjectMocks
	private AccountManageServiceImpl acctAccountManageService;
	@Mock
	private AcctCancelNoticeMapper acctCancelNoticeMapper;
	
	@Mock
	private AccountManagerMapper accountManagerMapper;
	
//	@Before 
//    public void setUp() {  
//		//MockitoAnnotations.initMocks(this);  
//    } 
	
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
	
	@Test
	public void testAccount() {
		AcctCancelNoticeFile accountFile = new AcctCancelNoticeFile();
		accountFile.setProductId("prodId1");
		accountFile.setAcctName("acctName1");
		String fid = "659852";
		//Mockito.doReturn(accountFile).when(acctCancelNoticeMapper).qryNoticeFileById(fid);
		Mockito.when(acctCancelNoticeMapper.qryNoticeFileById(fid)).thenReturn(accountFile);
		AcctCancelNoticeFile noticeFile = acctAccountManageService.qryNoticeByBid(fid);
		//Mockito.verify(acctCancelNoticeMapper).qryNoticeFileById(fid);
		assertEquals("prodId1", noticeFile.getProductId());
	}
	
	@Test
    public void testMockitoTest() {
    	AcctCancelNoticeMapper noticeMapper = Mockito.mock(AcctCancelNoticeMapper.class);
    	AcctCancelNoticeFile accountFile = new AcctCancelNoticeFile();
		accountFile.setProductId("prodId1");
		accountFile.setAcctName("acctName1");
		String fid = "659852";
		Mockito.when(noticeMapper.qryNoticeFileById(fid)).thenReturn(accountFile);
    //	System.out.println(noticeMapper.qryNoticeFileById(fid).getAcctName());
    	
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = Mockito.mock(LinkedList.class);
        
        // stubbing appears before the actual execution
        Mockito.when(mockedList.get(0)).thenReturn("first");

        // the following prints "first"
     //   System.out.println(mockedList.get(0));

        // the following prints "null" because get(999) was not stubbed
      //  System.out.println(mockedList.get(999));
    }
}
