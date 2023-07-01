package com.arxivj.weekend_server.domain.thread.api;

import com.arxivj.weekend_server.domain.thread.dto.ThreadDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/thread")
public class ThreadApi {

    //Post
    @PostMapping
    public ResponseEntity<HttpStatus> postThread(@RequestHeader("Authorization") String authorization,
                                                 @RequestBody ThreadDto.Request dto) {
        //TODO: 사용자의 식별정보는 payload에 담겨있으므로 서비스로직 작성시 참고
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Get (전체 게시물)
    @GetMapping
    public ResponseEntity<HttpStatus> getThreads(Optional<Integer> page){
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //Get (정렬된 게시물들)
    @GetMapping("/{sort-type}")
    public ResponseEntity<HttpStatus> getSortedThreads(@PathVariable("sort-type") String sortType,
                                                       Optional<Integer> page){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Patch
    @PatchMapping("/{thread-id}")
    public ResponseEntity<HttpStatus> patchThread(@PathVariable("thread-id") String threadId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Delete
    @DeleteMapping("/{thread-id}")
    public ResponseEntity<HttpStatus> deleteThread(@PathVariable("thread-id") String threadId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
