package com.yorijori.foodcode.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.yorijori.foodcode.jpa.entity.CookingClass;
import com.yorijori.foodcode.jpa.entity.CookingClassContent;
import com.yorijori.foodcode.jpa.entity.CookingClassCurriculum;
import com.yorijori.foodcode.jpa.entity.CookingClassForm;
import com.yorijori.foodcode.jpa.entity.CookingClassImage;
import com.yorijori.foodcode.jpa.entity.Payment;
import com.yorijori.foodcode.jpa.repository.CookingClassContentRepository;
import com.yorijori.foodcode.jpa.repository.CookingClassCurriculumRepository;
import com.yorijori.foodcode.jpa.repository.CookingClassFormRepository;
import com.yorijori.foodcode.jpa.repository.CookingClassRepository;
import com.yorijori.foodcode.jpa.repository.PaymentRepository;

@Repository
public class CookingClassDAOImpl implements CookingClassDAO {
	CookingClassRepository classRepo;
	CookingClassContentRepository contentRepo;
	CookingClassCurriculumRepository curriculumRepo;
	CookingClassFormRepository formRepo;
	PaymentRepository payRepo;
	
	@Autowired
	public CookingClassDAOImpl(CookingClassRepository classRepo, CookingClassContentRepository contentRepo,
			CookingClassCurriculumRepository curriculumRepo, CookingClassFormRepository formRepo,
			PaymentRepository payRepo) {
		super();
		this.classRepo = classRepo;
		this.contentRepo = contentRepo;
		this.curriculumRepo = curriculumRepo;
		this.formRepo = formRepo;
		this.payRepo = payRepo;
	}


	@Override
	public void delete(int cookNo) {
		CookingClass cookingclass= classRepo.findById(cookNo).get();
		cookingclass.setCookNo(cookNo);
		cookingclass.setState(1);
		
	}
	@Override
	public void restore(int cookNo) {
		CookingClass cookingclass= classRepo.findById(cookNo).get();
		cookingclass.setCookNo(cookNo);
		cookingclass.setState(0);
	}
	@Override
	public List<CookingClass> selectAllClass(int state) {
		return classRepo.findByState(state);
	}

	@Override
	public void updateClass(CookingClass cookingclass) {
		
	}

	@Override
	public void updateContent(CookingClassContent content) {
		
		
	}

	@Override
	public void updateCurriculum(CookingClassCurriculum curriculum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CookingClass readClass(int cookNo) {
		return classRepo.findById(cookNo).get();
	}

	@Override
	public List<CookingClassContent> readContent(int cookNo) {
		List<CookingClassContent> result=contentRepo.findByCookNo(cookNo);
		return result;
	}

	@Override
	public List<CookingClassCurriculum> readCurriculum(int cookNo) {
		List<CookingClassCurriculum> result=curriculumRepo.findByCookNo(cookNo);
		return result;
	}

	
	@Override
	public void insertClassTest(CookingClass cookingclass) {
		classRepo.save(cookingclass);
	}


	@Override
	public void insertImage(CookingClassImage image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CookingClass> findTop5ByOrderByCount() {
		return classRepo.findTop5ByOrderByCountDesc();
	}

	@Override
	public long countAll() {
		return classRepo.count();
	}
	@Override
	public List<CookingClass> selectByPageAndpagePerCount(int pageNo, int pagePerCount) {
		PageRequest pageRequest = PageRequest.of(pageNo, pagePerCount, Sort.by(Sort.Direction.DESC,"cookNo"));								
		//Page<Board> page = repository.findAll(pageRequest);
		 Page<CookingClass> page = classRepo.findByState(0, pageRequest);
		 List<CookingClass> list = page.getContent();
		return list;
	}
	
	@Override
	public void formInsert(CookingClassForm form) {
		formRepo.save(form);
	}
	@Override
	public CookingClass findById(Integer cookNo) {
		return classRepo.findById(cookNo).get();
	}
}
