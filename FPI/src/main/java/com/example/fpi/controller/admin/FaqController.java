package com.example.fpi.controller.admin;

import com.example.fpi.domain.dto.admin.FaqDTO;
import com.example.fpi.domain.dto.admin.FaqDetailDTO;
import com.example.fpi.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class FaqController {

    private final AdminService adminService;

    @GetMapping("/faq")
    public String list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                       Model model) {

        int totalFaqs = adminService.getFaqListCount();
        int totalPages = (int) Math.ceil((double)totalFaqs/pageSize);

        List<FaqDTO> faqs = adminService.getFaqList(pageNo, pageSize);

        int pageGroupSize = 5;
        int startPage = ((pageNo - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        model.addAttribute("faqs", faqs);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "admin/Faq";
    }

    // FAQ 상세보기
    @GetMapping("/faqDetail/{FaqId}")
    public String detail(@PathVariable("FaqId") Long faqId, Model model) {

        FaqDetailDTO faq = adminService.getFaqById(faqId);
        List<FaqDTO> faqRecent = adminService.getRecentFaq();

        model.addAttribute("faq", faq);
        model.addAttribute("faqRecent", faqRecent);


        return "admin/FaqDetail";
    }


    // 삭제하기
    @PostMapping("/delete/{faqId}")
    public String delete(@PathVariable Long faqId) {
        adminService.deleteFaq(faqId);
        return "redirect:/admin/FAQ";
    }
}