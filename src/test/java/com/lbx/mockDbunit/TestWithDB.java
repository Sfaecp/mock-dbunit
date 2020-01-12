package com.lbx.mockDbunit;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.yss.fsip.lifecycle.api.vo.process.ProcAuditRecordVO;
import com.lbx.mockDbunit.base.BaseTestRollBack;
import com.lbx.mockDbunit.controller.process.ActivitiCommonController;
import com.lbx.mockDbunit.entity.AcctCancelNotice;
import com.lbx.mockDbunit.mapper.dao.AcctCancelNoticeMapper;
import com.lbx.mockDbunit.service.process.impl.ActivitiCommonServiceImpl;

/**
 * 使用spring-test-dbunit 向数据库插入数据，
 * @author lbx
 *
 */
//@ActiveProfiles("mock")
public class TestWithDB extends BaseTestRollBack{
	
	@Autowired
	private AcctCancelNoticeMapper acctCancelNoticeMapper;
	
	@Autowired
	private ActivitiCommonServiceImpl activitiCommonService;
	
	
	@Test
	@DatabaseSetup(value ="/AcctCancelNotice.xml", type = DatabaseOperation.REFRESH)
	public void testPreDbData() {
		int count = acctCancelNoticeMapper.deleteByPrimaryKey("7");
		Assert.assertEquals(1, count);
		
		AcctCancelNotice acctc = acctCancelNoticeMapper.selectByPrimaryKey("10");		
		Assert.assertEquals("101010", acctc.getFileId());
		
		AcctCancelNotice record = new AcctCancelNotice();
		record.setId("12");
		record.setFileId("121212");
		acctCancelNoticeMapper.insertSelective(record);
		AcctCancelNotice acct = acctCancelNoticeMapper.selectByPrimaryKey("12");
		Assert.assertEquals("121212", acct.getFileId());
	}
	
	
}
