package com.example.fpi.service.admin;

import com.example.fpi.domain.dto.admin.FaqDTO;
import com.example.fpi.domain.dto.admin.FaqDetailDTO;
import com.example.fpi.domain.dto.admin.NotiDTO;
import com.example.fpi.domain.dto.admin.NotiDetailDTO;
import com.example.fpi.domain.vo.admin.FaqVO;
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

        return adminMapper.notiSelectAll(startRow, endRow);
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
    public List<NotiDTO> getRecentNoti() {
        return adminMapper.selectRecentNoti();
    }






    @Override
    public List<FaqDTO> getFaqList(int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        return adminMapper.faqSelectAll(startRow, endRow);
    }


    @Override
    public int getFaqListCount() {
        return adminMapper.countFaq();
    }

    @Override
    public FaqDetailDTO getFaqById(Long faqId) {
        FaqDetailDTO faq = adminMapper.selectFaqDetail(faqId);

        return faq;
    }


    @Override
    public FaqDetailDTO goUpdateFaq(Long faqId) {
        return adminMapper.selectFaqDetail(faqId);
    }


    @Override
    public void updateFaq(FaqDTO faq) {
        adminMapper.updateFaq(FaqVO.toEntity(faq));

        saveFaq(faq);
    }

    @Override
    @Transactional
    public void saveFaq(FaqDTO faq) {
        Long faqId = adminMapper.getSeq();
        faq.setFaqId(faqId);
        adminMapper.saveFaq(faq);
    }

    @Override
    public void deleteFaq(Long faqId) {
        adminMapper.deleteFaq(faqId);
    }

    @Override
    public List<FaqDTO> getRecentFaq() {
        return adminMapper.selectRecentFaq();
    }

}
