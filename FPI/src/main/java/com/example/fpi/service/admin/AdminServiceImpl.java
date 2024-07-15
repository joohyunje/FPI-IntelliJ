package com.example.fpi.service.admin;

import com.example.fpi.domain.dto.admin.FAQDTO;
import com.example.fpi.domain.dto.admin.NotiDTO;
import com.example.fpi.domain.dto.admin.NotiDetailDTO;
import com.example.fpi.domain.vo.admin.NotiVO;
import com.example.fpi.mapper.admin.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    public List<FAQDTO> getFAQList(int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        return adminMapper.FAQSelectAll(startRow, endRow);
    }

    @Override
    public int getNotiListCount() {
        return adminMapper.countNoti();
    }

    @Override
    public int getFAQListCount() {
        return adminMapper.countFAQ();
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
}
