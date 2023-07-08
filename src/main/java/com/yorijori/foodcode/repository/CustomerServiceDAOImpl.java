package com.yorijori.foodcode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.Inquiry;
import com.yorijori.foodcode.jpa.entity.InquiryComment;
import com.yorijori.foodcode.jpa.entity.Notice;
import com.yorijori.foodcode.jpa.entity.Question;
import com.yorijori.foodcode.jpa.entity.UserInfo;
import com.yorijori.foodcode.jpa.repository.InquiryCommentRepository;
import com.yorijori.foodcode.jpa.repository.NoticeRepository;
import com.yorijori.foodcode.jpa.repository.QuestionRepository;
import com.yorijori.foodcode.jpa.repository.inquiryRepository;
@Repository
public class CustomerServiceDAOImpl implements CustomerServiceDAO {
	private QuestionRepository questionRepository;
	private inquiryRepository inquiryRepository;
	private NoticeRepository noticeRepository;
	private InquiryCommentRepository inquiryCommentRepository;

	@Autowired
	public CustomerServiceDAOImpl(QuestionRepository questionRepository,
			com.yorijori.foodcode.jpa.repository.inquiryRepository inquiryRepository, NoticeRepository noticeRepository,
			InquiryCommentRepository inquiryCommentRepository) {
		super();
		this.questionRepository = questionRepository;
		this.inquiryRepository = inquiryRepository;
		this.noticeRepository = noticeRepository;
		this.inquiryCommentRepository = inquiryCommentRepository;
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
		////System.out.println("dddddddao");
		return noticeRepository.save(notice);
	}


	@Override
	public List<Notice> selectByPageAndpagePerCount(int pageNo, int pagePerCount) {
		Pageable pageable = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, "noticeNo"));
		Page<Notice> page = noticeRepository.findByState(0, pageable);
		List<Notice> list = page.getContent(); 
		////System.out.println("dao"+list);
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
		////System.out.println("dao"+list);
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
		////System.out.println("iiiiiiiiiinqdao");
		return inquiryRepository.save(inquery);
	}

	@Override
	public List<Inquiry> inquirySelectByPageAndpagePerCount(int pageNo, int pagePerCount) {
		Pageable pageable = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC, "questionNo"));
		Page<Inquiry> page = inquiryRepository.findByState(0, pageable);
		List<Inquiry> list = page.getContent(); 
		////System.out.println("dao"+list);
		return list; 
	}

	@Override
	public long inquiryCountAll() {

		return inquiryRepository.count();
	}

	@Override
	public void inquiryDelete(int inquiryNo) {
		Inquiry inquiry = inquiryRepository.findById(inquiryNo).get();
		inquiry.setInquiryNo(inquiryNo);
		inquiry.setState(1);
	}

	@Override
	public List<Inquiry> findByUserId(UserInfo user) {
		return inquiryRepository.findByUserId(user);
	}

	@Override
	public Inquiry select(int inquiryNo) {

		return inquiryRepository.findByInquiryNo(inquiryNo);
	}

	@Override
	public InquiryComment inquiryCommentInsert(InquiryComment inquiryComment) {
		return inquiryCommentRepository.save(inquiryComment);
	}

	@Override
	public List<InquiryComment> inquiryCommentList(int inquiryNo) {
		// TODO Auto-generated method stub
		return inquiryCommentRepository.findByInquiryNo(inquiryNo);
	}

	@Override
	public InquiryComment inquiryCommentDelete(int id) {
		InquiryComment inquiryComment = inquiryCommentRepository.findById(id).get();
		inquiryComment.setId(id);
		inquiryComment.setState(1);
		return inquiryComment;
	}

	@Override
	public List<Inquiry> getAllInquiries() {
		// TODO Auto-generated method stub
		return inquiryRepository.findAll();
	}

	@Override
	public List<InquiryComment> getInquiryCommentsByState(int inquiryNo, int state) {
		// TODO Auto-generated method stub
        return inquiryCommentRepository.findByInquiryNoAndState(inquiryNo, state);
	}

}
