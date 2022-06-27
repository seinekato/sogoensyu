package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import com.example.util.XlsDataSetLoader;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@DbUnitConfiguration(dataSetLoader = XlsDataSetLoader.class)

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, // このテストクラスでDIを使えるように指定
		TransactionDbUnitTestExecutionListener.class // @DatabaseSetupや@ExpectedDatabaseなどを使えるように指定
})

@ActiveProfiles("test")
class ForResisterControllerTest {
	private AutoCloseable closeable;
	private static final UUID uuid = UUID.fromString("$2a$10$57o8HGavMZY6TbfCkkU.S.5OnyPLBVW8AcuA2T4LnY.01fyagmtn6");
	private static final LocalDateTime datetime = LocalDateTime.of(2022, 6, 27, 18, 0, 0, 0);

	@InjectMocks
	private ForResisterController forResisterController;

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		closeable = MockitoAnnotations.openMocks(this);
		MockedStatic<UUID> mock = Mockito.mockStatic(UUID.class);
		mock.when(UUID::randomUUID).thenReturn(uuid);
		MockedStatic<LocalDateTime> mock2 = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS);
		mock2.when(LocalDateTime::now).thenReturn(datetime);

	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	@DisplayName("/forresister遷移テスト")
	void test1() throws Exception {
		mockMvc.perform(get("/forresister")).andExpect(view().name("user_request"));
	}

	@Test
	@DisplayName("/sent遷移テスト")
	void test2() throws Exception {
		mockMvc.perform(get("/forresister/sent")).andExpect(view().name("sent_mail"));
	}

	@Test
	@DisplayName("/mailエラーテスト。notメール形式")
	void test3() throws Exception {
		mockMvc.perform(post("/forresister/mail").param("mail", "aaaaaaaaaa")).andExpect(model().hasErrors())
				.andExpect(view().name("user_request"));
	}

	@Test
	@DisplayName("/mail forresister!=null,duration24以内")
	@DatabaseSetup("classpath:forresister1.xlsx")
	void test4() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(post("/forresister/mail").param("mail", "seine.kato@rakus-partners.co.jp"))
				.andExpect(model().attributeHasErrors("forResisterForm")).andExpect(view().name("user_request"))
				.andReturn();

		BindingResult bindResult = (BindingResult) mvcResult.getModelAndView().getModel()
				.get(BindingResult.MODEL_KEY_PREFIX + "forResisterForm");
		String msg = bindResult.getFieldError().getDefaultMessage();
		assertEquals("有効期限内の本登録用メールが存在します。送信済みメールの本登録URLからご登録ください", msg);

	}

	@Test
	@DisplayName("/mail forresister!=null,duration24以上　user==null")
	@DatabaseSetup("classpath:forresister2.xlsx")
	@ExpectedDatabase(value = "classpath:expect1.xlsx", assertionMode = DatabaseAssertionMode.NON_STRICT)
//	。★keyが新しいもの、日時も新しくなっていることを確認！！！
	void test5() throws Exception {
		mockMvc.perform(post("/forresister/mail").param("mail", "seine.kato@rakus-partners.co.jp"))
				.andExpect(view().name("redirect:/forresister/sent")).andReturn();
	}

	@Test
	@DisplayName("/mail forresister!=null,duration24以上　user!=null")
	@DatabaseSetup("classpath:forresister3.xlsx")
//	★セットアップ側の現在日付！！
	void test6() throws Exception {
		mockMvc.perform(post("/forresister/mail").param("mail", "seine.kato@rakus-partners.co.jp"))
				.andExpect(view().name("redirect:/forresister/sent"));
	}
	
	@Test
	@DisplayName("/mail forresister==null user==null")
	void test7() throws Exception {
		mockMvc.perform(post("/forresister/mail").param("mail", "seine.kato@rakus-partners.co.jp"))
				.andExpect(view().name("redirect:/forresister/sent"));
//		★メール送信、データベースインサート
	}

	@Test
	@DisplayName("/mail forresister==null user!=null　遷移のみ")
	@DatabaseSetup("classpath:forresister4.xlsx")
//	resister_date null不可

	void test8() throws Exception {
		mockMvc.perform(post("/forresister/mail").param("mail", "seine.kato@rakus-partners.co.jp"))
				.andExpect(view().name("redirect:/forresister/sent"));
	}



}
