package com.proxy.demo;

import com.proxy.demo.domain.User;
import com.proxy.demo.domain.UserDto;
import com.proxy.demo.mapper.ModelMapper;
import com.proxy.demo.repository.UserJpaRepository;
import com.proxy.demo.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.properties")
@SpringBootTest
public class DemoProxyApplicationTests {

	@Autowired
	UserJpaRepository userJpaRepository;
	@Autowired
	UserService userService;
	@Autowired
	ModelMapper modelMapper;

//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPersist");
//	EntityManager em = emf.createEntityManager();

	@Before
	public void givenAndWhen() throws Exception {
		//given
		String username = "user";
		String realName = "real";

		userService.addUser(username, realName);
		userService.addUser(username + "Follow", realName + "Follow");

		//when
		userService.addFollow("userFollow", "user");

	}
	@Transactional
	@Test
	public void 유저_팔로우_테스트1() throws Exception {

		//then
		User checkFollower = userService.getOneUserEntity("userFollow");
		User checkUser = userService.getOneUserEntity("user");

		Assert.assertEquals(checkUser.getFollowerList().contains(checkFollower), true);
		Assert.assertEquals(checkFollower.getFollowingList().contains(checkUser), true);
	}

	@Transactional
	@Test
	public void 유저_팔로우_테스트2() throws Exception {
		//then
		User checkFollower = userService.getOneUserEntity("userFollow");
		User checkUser = userService.getOneUserEntity("user");

		List<UserDto> followerList = modelMapper.convertUserList(checkUser.getFollowerList());
		List<UserDto> followingList = modelMapper.convertUserList(checkFollower.getFollowingList());

		boolean anyFollow =
				followerList.stream().anyMatch(person -> person.getUsername().equals("userFollow"));
		boolean anyFollowing =
				followingList.stream().anyMatch(person -> person.getUsername().equals("user"));

		Assert.assertEquals(anyFollow, true);
		Assert.assertEquals(anyFollowing, true);
	}

}
