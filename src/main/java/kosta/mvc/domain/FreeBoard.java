package kosta.mvc.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "free_seq_id")
	@SequenceGenerator(name = "free_seq_id" , allocationSize = 1, sequenceName = "free_seq_id")
	private Long bno;
	private String subject;
	private String writer;
	private String content;
	private String password;
	
	@CreationTimestamp
	private LocalDateTime writeday;
	private int readnum;
	
	/**
	 * CascadeType 옵션은 만약 Entity의 상태변화가 생기면 연관관계가 있는 
	 * Entity도 상태변화를 전이 시키는 것이다.
	 * */
	@OneToMany(mappedBy = "freeBoard", cascade = CascadeType.ALL)
	private List<Reply> replyList;	
	
}

