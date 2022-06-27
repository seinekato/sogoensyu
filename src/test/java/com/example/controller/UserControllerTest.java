/**
 * 
 */
package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.example.util.XlsDataSetLoader;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

/**
 * @author seine
 *
 */

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@DbUnitConfiguration(dataSetLoader = XlsDataSetLoader.class)

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, // このテストクラスでDIを使えるように指定
		TransactionDbUnitTestExecutionListener.class // @DatabaseSetupや@ExpectedDatabaseなどを使えるように指定
})

@ActiveProfiles("test")
class UserControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("/resister遷移テスト forresister==null")
	@DatabaseSetup("classpath:forresister5.xlsx")
	void test() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/user/resister").param("key", "aaaaa"))
				.andExpect(view().name("forward:/forresister")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String message = (String) mav.getModel().get("urlError");
		assertEquals("本登録用URLが無効です。登録用URLリクエスト画面から再度お試しください", message);

	}

	@Test
	@DisplayName("/resister遷移テスト forresister!=null 24時間以内")
	@DatabaseSetup("classpath:forresister1.xlsx")
	void test2() throws Exception {
		MvcResult mvcResult  = mockMvc.perform(post("/user/resister").param("key", "aaaaa"))
		.andExpect(view().name("user_resister")).andReturn();
	 MockHttpSession session = (MockHttpSession) mvcResult.getRequest().getSession();
	 String key = (String)session.getAttribute("key");
		assertEquals("aaaaa", key);
	}
	
	@Test
	@DisplayName("/resister遷移テスト forresister!=null 24時間以上")
	@DatabaseSetup("classpath:forresister2.xlsx")
	void test3() throws Exception {
//		duration
		MvcResult mvcResult = mockMvc.perform(post("/user/resister").param("key", "aaaaa"))
				.andExpect(view().name("forward:/forresister")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String message = (String) mav.getModel().get("urlError");
		assertEquals("本登録用URLが無効です。登録用URLリクエスト画面から再度お試しください", message);
	}

	@Test
	@DisplayName("/complete 遷移テスト")
	void test4() throws Exception {
		mockMvc.perform(post("/user/complete")).andExpect(view().name("user_complete"));
		
	}

	@Test
	@DisplayName("/forcomplete バリデーションテスト ふりがな")
	void test5() throws Exception {
		mockMvc.perform(
				post("/user/forcomplete").param("name", "加藤").param("hurigana", "kato").param("zipCode", "111-1111")
						.param("address", "テスト町").param("tel", "000-0000-0000").param("password", "aiueo")
						.param("confirm", "aiueo"))
				.andExpect(view().name("user_resister"));
	}

	@Test
	@DisplayName("/forcomplete バリデーションテスト 確認パスワード")
	void test6() throws Exception {
		mockMvc.perform(post("/user/forcomplete").param("name", "加藤").param("hurigana", "かとう")
				.param("zipCode", "111-1111").param("address", "テスト町").param("tel", "000-0000-0000")
				.param("password", "aiueo").param("confirm", "oeuia")).andExpect(view().name("user_resister"));
	}

	@Test
	@DisplayName("/forcomplete ユーザー登録テスト")
	@DatabaseSetup("classpath:forresister6.xlsx")
	@ExpectedDatabase(value = "classpath:expect2.xlsx", assertionMode = DatabaseAssertionMode.NON_STRICT)
//	★登録日付など
	void test7() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/user/forcomplete").param("name", "加藤").param("hurigana", "かとう")
				.param("zipCode", "111-1111").param("address", "テスト町").param("tel", "111-1111-1111")
				.param("password", "aaaa").param("confirm", "aaaa").sessionAttr("key", "aaaaa"))
				.andExpect(view().name("redirect:/user/complete")).andReturn();

//		★users日付,passwordエンコーダー分
	}

}
