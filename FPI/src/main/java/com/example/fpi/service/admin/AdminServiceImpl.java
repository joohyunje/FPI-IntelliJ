package com.example.fpi.service.admin;

import com.example.fpi.domain.dto.admin.FAQDTO;
import com.example.fpi.domain.dto.admin.FAQDetailDTO;
import com.example.fpi.domain.dto.admin.NotiDTO;
import com.example.fpi.domain.dto.admin.NotiDetailDTO;
import com.example.fpi.domain.vo.admin.FAQVO;
import com.example.fpi.domain.vo.admin.NotiVO;
import com.example.fpi.mapper.admin.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminMapper adminMapper;


    @Override
    public List<NotiDTO> getNotiList(int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        return adminMapper.NotiSelectAll(startRow, endRow);
    }

    @Override
    public int getNotiListCount() {
        return adminMapper.countNoti();
    }

    @Override
    public NotiDetailDTO getNotiById(Long notiId) {
        NotiDetailDTO noti = adminMapper.selectNotiDetail(notiId);

        return noti;
    }


    @Override
    public NotiDetailDTO goUpdateNoti(Long notiId) {
        return adminMapper.selectNotiDetail(notiId);
    }


    @Override
    public void updateNoti(NotiDTO noti) {
        adminMapper.updateNoti(NotiVO.toEntity(noti));

        saveNoti(noti);
    }

    @Override
    @Transactional
    public void saveNoti(NotiDTO noti) {
        Long notiId = adminMapper.getSeq();
        noti.setNotiId(notiId);
        adminMapper.saveNoti(noti);
    }

    @Override
    public void deleteNoti(Long notiId) {
        adminMapper.deleteNoti(notiId);
    }

    @Override
    public List<NotiDTO> getRecentList() {
        return adminMapper.selectRecentNoti();
    }






    @Override
    public List<FAQDTO> getFAQList(int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        return adminMapper.FAQSelectAll(startRow, endRow);
    }


    @Override
    public int getFAQListCount() {
        return adminMapper.countFAQ();
    }

    @Override
    public FAQDetailDTO getFAQById(Long faqId) {
        FAQDetailDTO faq = adminMapper.selectFAQDetail(faqId);

        return faq;
    }


    @Override
    public FAQDetailDTO goUpdateFAQ(Long faqId) {
        return adminMapper.selectFAQDetail(faqId);
    }


    @Override
    public void updateFAQ(FAQDTO faq) {
        adminMapper.updateFAQ(FAQVO.toEntity(faq));

        saveFAQ(faq);
    }

    @Override
    @Transactional
    public void saveFAQ(FAQDTO faq) {
        Long faqId = adminMapper.getSeq();
        faq.setFAQId(faqId);
        adminMapper.saveFAQ(faq);
    }

    @Override
    public void deleteFAQ(Long faqId) {
        adminMapper.deleteFAQ(faqId);
    }

//    안씀
//    @Override
//    public List<FAQDTO> getRecentList() {
//        return adminMapper.selectRecentFAQ();
//    }






}
