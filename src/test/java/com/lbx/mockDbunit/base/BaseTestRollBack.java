package com.lbx.mockDbunit.base;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;

@RunWith(SpringRunner.class)
@ContextConfiguration
@TestExecutionListeners({  DependencyInjectionTestExecutionListener.class, 
	DirtiesContextTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class})
@Transactional
@Rollback
@SpringBootTest
public class BaseTestRollBack {

}
