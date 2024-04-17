package com.example.SpringDataJDBCSample2;

import com.example.SpringDataJDBCSample2.enitity.Member;
import com.example.SpringDataJDBCSample2.repository.MemberCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJdbcSample2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJdbcSample2Application.class, args).getBean(SpringDataJdbcSample2Application.class).execute();
	}
	@Autowired
	MemberCrudRepository repository; //1)

	private void execute(){
		//등록
		executeInset();
		//모든 데이터 취득
		executeSelect();
	}
	private void executeInset(){
		Member member = new Member(null,"이순신");
		member = repository.save(member);
		System.out.println("등록 데이터:" +member);
	}

	private void executeSelect(){
		System.out.println("--- 전체 데이터를 취득합니다 ---");
		Iterable <Member> members = repository.findAll();
		for(Member member : members){
			System.out.println(member);
		}
	}

}
