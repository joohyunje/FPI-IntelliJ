package com.example.fpi.controller.admin;

import com.example.fpi.domain.dto.admin.FAQDTO;
import com.example.fpi.domain.dto.admin.FAQDetailDTO;
import com.example.fpi.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class FAQController {

    private final AdminService adminService;

    @GetMapping("/FAQ")
    public String list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                       Model model) {

        int totalFAQs = adminService.getFAQListCount();
        int totalPages = (int) Math.ceil((double)totalFAQs/pageSize);

        List<FAQDTO> faqs = adminService.getFAQList(pageNo, pageSize);

        int pageGroupSize = 5;
        int startPage = ((pageNo - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        model.addAttribute("faqs", faqs);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


        return "admin/FAQ";
    }

    // FAQ 상세보기
    @GetMapping("/FaqDetail/{faqId}")
    public String detail(@PathVariable("faqId") Long faqId, Model model) {

        FAQDetailDTO faq = adminService.getFAQById(faqId);
//        안씀
//        List<FAQDTO> faqRecent = adminService.getRecentList();

        System.out.println(adminService.getRecentList());
        model.addAttribute("faq", faq);
//        안씀
//        model.addAttribute("faqRecent", faqRecent);

        return "admin/FAQDetail";
    }

    // 작성하기
//    @PostMapping("/board/WriteForm")
//    public String write(FAQDTO faq) {
//        adminService.saveFAQ(faq);
//        return "redirect:/admin/FAQ";
//    }

//    // 수정하기
//    @PostMapping("/edit")
//    public String edit(FAQDTO faq) {
//        adminService.updateFAQ(faq);
//
//        return "redirect:/admin/FAQDetail/" + faq.getFAQId();
//    }

    // 삭제하기
    @PostMapping("/delete/{faqId}")
    public String delete(@PathVariable Long faqId) {
        adminService.deleteFAQ(faqId);
        return "redirect:/admin/FAQ";
    }
}