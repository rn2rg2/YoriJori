package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.CustomerService;
import com.yorijori.foodcode.jpa.entity.Inquiry;
import com.yorijori.foodcode.jpa.entity.Notice;
import com.yorijori.foodcode.jpa.entity.Question;
import com.yorijori.foodcode.jpa.repository.NoticeRepository;
import com.yorijori.foodcode.jpa.repository.QuestionRepository;
import com.yorijori.foodcode.jpa.repository.inquiryRepository;
@Repository
public class CustomerServiceDAOImpl implements CustomerServiceDAO {
	private QuestionRepository questionRepository;
	private inquiryRepository inquiryRepository;
	private NoticeRepository noticeRepository;
	

	public CustomerServiceDAOImpl(QuestionRepository questionRepository,
			com.yorijori.foodcode.jpa.repository.inquiryRepository inquiryRepository,
			NoticeRepository noticeRepository) {
		super();
		this.questionRepository = questionRepository;
		this.inquiryRepository = inquiryRepository;
		this.noticeRepository = noticeRepository;
	}

	@Override
	public List<Notice> selectAll() {
		return null;
	}

	@Override
	public long noticeCountAll() {
		return noticeRepository.count();
	}
	
	@Override
	public Notice noticeInsert(Notice notice) {
		//System.out.println("dddddddao");
		return noticeRepository.save(notice);
	}


	@Override
	public List<Notice> selectByPageAndpagePerCount(int pageNo, int pagePerCount) {
		Pageable pageable = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, "noticeNo"));
		Page<Notice> page = noticeRepository.findByState(0, pageable);
		List<Notice> list = page.getContent(); 
		//System.out.println("dao"+list);
		return list; 
	}


	@Override
	public void noticeDelete(int noticeNo) {
		Notice notice = noticeRepository.findById(noticeNo).get();
		notice.setNoticeNo(noticeNo);
		notice.setState(1);
	}

	@Override
	public Question questionInsert(Question question) {
		return questionRepository.save(question);
	}

	@Override
	public long questionCountAll() {
		return questionRepository.count();
	}

	@Override
	public List<Question> questionSelectByPageAndpagePerCount(int pageNo, int pagePerCount) {
		Pageable pageable = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, "questionNo"));
		Page<Question> page = questionRepository.findByState(0, pageable);
		List<Question> list = page.getContent(); 
		//System.out.println("dao"+list);
		return list; 
	}

	@Override
	public void questionDelete(int questionNo) {
		Question question = questionRepository.findById(questionNo).get();
		question.setQuestionNo(questionNo);
		question.setState(1);
	}

	@Override
	public Inquiry inquiryInsert(Inquiry inquery) {
		System.out.println("iiiiiiiiiinqdao");
		return inquiryRepository.save(inquery);
	}

}
