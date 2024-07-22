package com.example.fpi.controller.board;

import com.example.fpi.domain.dto.board.CommunityDTO;
import com.example.fpi.domain.dto.board.CommunityDetailDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.service.board.CommunityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping({"/community", "/freeTalk", "/proTip"})
    public String list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                       Model model,
                       HttpServletRequest request) {

        String requestURI = request.getRequestURI();

        int totalCommunities = communityService.getCommunityListCount();
        int totalPages = (int) Math.ceil((double)totalCommunities/pageSize);

        List<CommunityDTO> commus = communityService.getCommunityList(pageNo, pageSize);

        int pageGroupSize = 5;
        int startPage = ((pageNo - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        model.addAttribute("commus", commus);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        // Determine which view to return based on the request URI
        if (requestURI.equals("/community")) {
            return "board/Community";
        } else if (requestURI.equals("/freeTalk")) {
            return "board/FreeTalk";
        } else if (requestURI.equals("/proTip")) {
            return "board/ProTip";
        }

        // Default return if no matching URI is found
        return "board/Community";
    }

    @GetMapping("/detail/{communityId}")
    public String detail(@PathVariable("communityId") Long communityId, Model model,
                         @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {

        CommunityDetailDTO commu = communityService.getCommunityById(communityId, customOAuth2User);

        model.addAttribute("commu", commu);

        return "board/Detail";
    }

    @GetMapping("/write")
    public String writeForm(Model model){
        model.addAttribute("community", new CommunityDTO());
        return "board/Write";
    }

    @PostMapping("/write")
    public String write(CommunityDTO community, @RequestParam("userId") String userId){
        community.setUserId(userId);
        communityService.saveCommunity(community);
        return "redirect:/community";
    }
}
