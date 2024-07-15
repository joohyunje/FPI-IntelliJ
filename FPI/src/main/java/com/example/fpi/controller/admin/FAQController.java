package com.example.fpi.controller.admin;

import com.example.fpi.domain.dto.admin.FAQDTO;
import com.example.fpi.domain.dto.admin.NotiDTO;
import com.example.fpi.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/FAQ")
@RequiredArgsConstructor
public class FAQController {
    private final AdminService adminService;

    @GetMapping("/list")
    public String list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       Model model) {

        int totalBoards = adminService.getFAQListCount();
        int totalPages = (int) Math.ceil((double)totalBoards/pageSize);

        List<FAQDTO> FAQs = adminService.getFAQList(pageNo, pageSize);

        int pageGroupSize = 5;
        int startPage = ((pageNo - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        model.addAttribute("FAQs", FAQs);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "faq/list";
    }
}