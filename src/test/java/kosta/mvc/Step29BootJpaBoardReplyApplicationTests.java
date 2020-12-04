package kosta.mvc;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import kosta.mvc.domain.FreeBoard;
import kosta.mvc.repository.BoardRepository;

@SpringBootTest
@Transactional
@Commit
class Step29BootJpaBoardReplyApplicationTests {
	@Autowired
	private BoardRepository rep;

	@Test
	void insert() {
		for(int i=0;i<=100;i++) {
			rep.save(new FreeBoard(null, "제목"+i,"user"+i ,"bootJpa"+i, "1234", null,0 , null));
		}
	}
	
	
}
