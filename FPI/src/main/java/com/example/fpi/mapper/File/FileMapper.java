package com.example.fpi.mapper.File;

import com.example.fpi.domain.dto.certify.CardInfoFileDTO;
import com.example.fpi.domain.dto.file.ProCardInfoFileDTO;
import com.example.fpi.domain.dto.file.ProUploadFileDTO;
import com.example.fpi.domain.dto.file.UserUploadFileDTO;
import com.example.fpi.domain.vo.board.CommunityFileVO;
import com.example.fpi.domain.vo.file.ProUploadFileVO;
import com.example.fpi.domain.vo.file.UserUploadFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    // 전문가 서비스 올릴때 첨부파일
    void insertProUploadFile(ProUploadFileVO proUploadFileVO);

    // 전문가 서비스 올리기 첨부파일 목록
    List<ProUploadFileDTO> selectProUploadFileList(Long proUploadId);

    // 회원 견적 작성하기 첨부파일
    void insertUserUploadFile(UserUploadFileVO userUploadFileVO);

    // 회원 견적 작성하기 첨부파일 목록
    List<UserUploadFileDTO> selectUserUploadFileList(Long userUploadId);

    void insertSummernoteImg(CommunityFileVO communityFileVO);


//    void insertSummernoteImg(CommunityFileVO communityFileVO);

    List<ProCardInfoFileDTO> selectProCardFileList(Long proId);

    //    전문가 마이페이지에서 자격증 사진 선택시 삭제, 서버에서도 삭제위해 전문가아이디도필요
    void deleteCardPhotoFile(Long cardInfoFileId);

    CardInfoFileDTO cardFindImg(Long cardInfoFileId);


}
