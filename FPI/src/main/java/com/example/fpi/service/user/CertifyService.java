package com.example.fpi.service.user;

import com.example.fpi.domain.dto.certify.CertifyDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface CertifyService {
    void addCertify(CertifyDTO dto, List<MultipartFile> files);
    CertifyDTO selectCertify(String userId);

}
