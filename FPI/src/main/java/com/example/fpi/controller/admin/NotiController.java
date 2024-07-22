package com.example.fpi.controller.admin;

import com.example.fpi.domain.dto.admin.NotiDTO;
import com.example.fpi.domain.dto.admin.NotiDetailDTO;
import com.example.fpi.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class NotiController {

    private final AdminService adminService;

    @GetMapping("/noti")
    public String list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                       Model model) {

        int totalNotis = adminService.getNotiListCount();
        int totalPages = (int) Math.ceil((double)totalNotis/pageSize);

        List<NotiDTO> notis = adminService.getNotiList(pageNo, pageSize);

        int pageGroupSize = 5;
        int startPage = ((pageNo - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        model.addAttribute("notis", notis);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "admin/Noti";
    }

    // Noti 상세보기
    @GetMapping("/notiDetail/{notiId}")
    public String detail(@PathVariable("notiId") Long notiId, Model model) {

        NotiDetailDTO noti = adminService.getNotiById(notiId);
        List<NotiDTO> notiRecent = adminService.getRecentNoti();

        model.addAttribute("noti", noti);
        model.addAttribute("notiRecent", notiRecent);

        return "admin/NotiDetail";
    }

    // 삭제하기
    @PostMapping("/delete/{notiId}")
    public String delete(@PathVariable Long notiId) {
        adminService.deleteNoti(notiId);
        return "redirect:/admin/Noti";
    }
}
