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
@RequestMapping("/Noti")
@RequiredArgsConstructor
public class NotiController {
    private final AdminService adminService;

    @GetMapping("/Noti")
    public String list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       Model model) {

        int totalBoards = adminService.getNotiListCount();
        int totalPages = (int) Math.ceil((double)totalBoards/pageSize);

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

        return "/admin/Noti";
    }

    // Noti 상세보기
    @GetMapping("/NotiDetail/{notiId}")
    public String detail(@PathVariable("notiId") Long notiId, Model model) {

        NotiDetailDTO noti = adminService.getNotiById(notiId);

        model.addAttribute("noti", noti);

        return "admin/NotiDetail";
    }

    // 작성하기
    @PostMapping("/board/WriteForm")
    public String write(NotiDTO noti) {
        adminService.saveNoti(noti);
        return "redirect:/admin/Noti";
    }

    // 수정하기
    @PostMapping("/edit")
    public String edit(NotiDTO noti) {
        adminService.updateNoti(noti);

        return "redirect:/admin/NotiDetail/" + noti.getNotiId();
    }


    // 삭제하기
    @PostMapping("/delete/{notiId}")
    public String delete(@PathVariable Long notiId) {
        adminService.deleteNoti(notiId);
        return "redirect:/admin/Noti";
    }
}
