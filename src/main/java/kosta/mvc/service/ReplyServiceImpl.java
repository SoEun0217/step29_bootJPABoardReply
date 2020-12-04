package kosta.mvc.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.mvc.domain.Reply;
import kosta.mvc.repository.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository replyRep;
	
	public ReplyServiceImpl() {
		System.out.println("ReplyServiceImpl의 생성자 : "+replyRep);
	}
	
	/**
	 * 객체 생성이 되고 주입 완료된 후에 자동 호출..
	 * */
	@PostConstruct
	public void aa() {
		System.out.println("aa() 메소드 : "+replyRep);
	}
	
	@Override
	public void insert(Reply reply) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
