package com.example.fpi.controller.board;

import com.example.fpi.domain.dto.board.CommentDTO;
import com.example.fpi.service.board.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentRestController {

    private final CommentService commentService;

    //댓글 조회(특정 게시글의 모든 댓글 조회하기)
    @GetMapping("/{communityId}")
    // ok()안에 있는것과 ?가 자동 매칭됨
    public ResponseEntity<?> getComment(@PathVariable Long communityId) {
        return  ResponseEntity.ok(commentService.selectCommentList(communityId));
    }

    //    댓글추가
    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO, HttpSession session) {
        String loginName = (String) session.getAttribute("loginName");
        String proName = (String) session.getAttribute("proName");
        if(session.getAttribute("loginName") == null){
            commentDTO.setAuthor(proName);
        }
        else if(session.getAttribute("proName") == null){
            commentDTO.setAuthor(loginName);
        }

        commentService.saveComment(commentDTO);
//         ResponseEntity.ok() 응답만들때사용
//        build: 최종 객체 생성,
//        완성된 http응답 객체 반환함
        return  ResponseEntity.ok().build();
    }

    //    댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestBody CommentDTO commentDTO) {
        commentDTO.setCommentId(commentId);
        commentService.updateComment(commentDTO);

        return ResponseEntity.ok().build();
    }

    //    삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return  ResponseEntity.ok().build();
    }

//    디테일에서 댓글수 바로 반영하기 위해서
    @GetMapping("/countComment/{communityId}")
    public ResponseEntity<?> getCommentCount(@PathVariable Long communityId) {
        // 현재 댓글 수를 가져옴
        int commentCount = commentService.countComment(communityId);
        return ResponseEntity.ok().body(Collections.singletonMap("commentCount", commentCount));
    }
}