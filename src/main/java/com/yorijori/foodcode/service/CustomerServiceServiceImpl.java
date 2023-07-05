package com.yorijori.foodcode.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.CustomerService;
import com.yorijori.foodcode.jpa.entity.Inquiry;
import com.yorijori.foodcode.jpa.entity.Notice;
import com.yorijori.foodcode.jpa.entity.Question;
import com.yorijori.foodcode.repository.CustomerServiceDAO;
@Transactional
@Service
public class CustomerServiceServiceImpl implements CustomerServiceService {
	CustomerServiceDAO dao;
	
	@Autowired
	public CustomerServiceServiceImpl(CustomerServiceDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public Notice noticeInsert(Notice notice) {
		//System.out.println("AAAAAAAAAAAAAAserv");
		return dao.noticeInsert(notice);
	}

	@Override
	public List<Notice> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long noticeCountAll() {
		// TODO Auto-generated method stub
		return dao.noticeCountAll();
	}

	@Override
	public List<Notice> selectByPageAndpagePerCount(int pageNo, int pagePerCount) {
		return dao.selectByPageAndpagePerCount(pageNo, pagePerCount);
	}


	@Override
	public void noticeDelete(int noticeNo) {
		dao.noticeDelete(noticeNo);
	}

	@Override
	public Question questionInsert(Question question) {
		// TODO Auto-generated method stub
		return dao.questionInsert(question);
	}

	@Override
	public long questionCountAll() {
		// TODO Auto-generated method stub
		return dao.questionCountAll();
	}

	@Override
	public List<Question> questionSelectByPageAndpagePerCount(int pageNo, int pagePerCount) {
		// TODO Auto-generated method stub
		return dao.questionSelectByPageAndpagePerCount(pageNo, pagePerCount);
	}

	@Override
	public void questionDelete(int questionNo) {
		dao.questionDelete(questionNo);
	}

	@Override
	public Inquiry inquiryInsert(Inquiry inquiry) {
		System.out.println("iiiiiiiiiinqdao");

		return dao.inquiryInsert(inquiry);
		
	}

}
