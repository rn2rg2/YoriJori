package com.yorijori.foodcode.repository;

import java.util.List;

import com.yorijori.foodcode.jpa.entity.Inquiry;
import com.yorijori.foodcode.jpa.entity.InquiryComment;
import com.yorijori.foodcode.jpa.entity.Notice;
import com.yorijori.foodcode.jpa.entity.Question;
import com.yorijori.foodcode.jpa.entity.UserInfo;

public interface CustomerServiceDAO {
	//공지사항
	public	Notice	noticeInsert(Notice notice);
	
	List<Notice> selectAll();
	
	long noticeCountAll();
	
	List<Notice> selectByPageAndpagePerCount(int pageNo,int pagePerCount );

	public void noticeDelete(int noticeNo);
	
	//자주 묻는 질문
	
	public	Question	questionInsert(Question question);
	
	long questionCountAll();
	
	List<Question> questionSelectByPageAndpagePerCount(int pageNo,int pagePerCount );

	public void questionDelete(int questionNo);
	
	//문의하기
	public	Inquiry	inquiryInsert(Inquiry  inquiry);
	
	long inquiryCountAll();
	
	List<Inquiry> inquirySelectByPageAndpagePerCount(int pageNo,int pagePerCount );
	
	List<Inquiry> findByUserId(UserInfo user);

	public void inquiryDelete(int inquiryNo);
	
	Inquiry select(int inquiryNo);
	
	//문의하기 댓글
	
	public	InquiryComment	inquiryCommentInsert(InquiryComment  inquiryComment);
	
	List<InquiryComment> inquiryCommentList(int inquiryNo);	

	public void inquiryCommentDelete(int id);
	

}
