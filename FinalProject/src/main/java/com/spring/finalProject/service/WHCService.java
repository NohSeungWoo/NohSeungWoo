package com.spring.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.finalProject.model.InterWHCDAO;

//==== #31. Service 선언 ====
//트랜잭션 처리를 담당하는 곳, 업무를 처리하는 곳, 비지니스(Business)단
@Service // 원래 bean에 올려주기 위해서 @Component를 써야하는데 @Service를 쓰기 때문에 자동적으로 bean에 올라간다.
public class WHCService implements InterWHCService {
	
	// === #34. 의존객체 주입하기(DI: Dependency Injection) ===
	@Autowired
	private InterWHCDAO dao; // 다형성 // 원래는 dao가 아니라 boardDAO라고 써줘야하는데 지금은 BoardDAO가 한개밖에 없으므로 @Autowired에 의해 타입만 맞으면 되니까 dswefwf라고 써도 된다.
	// Type 에 따라 Spring 컨테이너가 알아서 bean 으로 등록된 com.spring.model.BoardDAO 의 bean 을  dao 에 주입시켜준다. 
    // 그러므로 dao 는 null 이 아니다.
	
	
	
	
}
