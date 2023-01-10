package bitcamp.bootapp.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.bootapp.dao.BoardDao;
import bitcamp.bootapp.vo.Board;

// SpringBoot 에게 다음 클래가 클라이언트 요청을 처리하는 일을 한다는 것을 SpringBoot에게 알리는 표시!~
// => SpringBoot는 다음 클래스의 인스턴스를 생성해서 보관해 둔다.
// => "\hello" 라는 URL로 클라이언트 요청이 들어오면 해당 메서드를 호출한다.
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
public class BoardController {

  BoardDao boardDao = new BoardDao();

  @PostMapping("/boards")
  public Object addBoard(
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String content,
      @RequestParam(required = false) String password) {

    Board b = new Board();
    b.setTitle(title);
    b.setContent(content);
    b.setPassword(password);
    b.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    this.boardDao.insert(b);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("stauts", "success");

    return contentMap;
  }


  @GetMapping("/boards")
  public Object getBoards() {

    Board[] boards = this.boardDao.findAll();

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("stauts", "success");
    contentMap.put("data", boards);

    return contentMap;
  }


  @PutMapping("/boards/{boardNo}")
  public Object updateBoard(
      @PathVariable int boardNo,
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String content,
      @RequestParam(required = false) String password) {

    Map<String,Object> contentMap = new HashMap<>();
    Board old = this.boardDao.findByNo(boardNo);
    if (old == null || !old.getPassword().equals(password)) {
      contentMap.put("stauts", "failure");
      contentMap.put("data", "게시글이 없거나 암호가 맞지 않습니다.");
      return contentMap;
    }

    Board b = new Board();
    b.setNo(boardNo);
    b.setTitle(title);
    b.setContent(content);
    b.setPassword(password);
    b.setCreatedDate(old.getCreatedDate());
    b.setViewCount(old.getViewCount());

    this.boardDao.update(b);

    contentMap.put("stauts", "success");

    return contentMap;
  }

  @DeleteMapping("/boards/{boardNo}")
  public Object deleteBoard(
      @PathVariable int boardNo,
      @RequestParam String password) {

    Board b = this.boardDao.findByNo(boardNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();
    if (b == null || !b.getPassword().equals(password)) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 게시글이 없습니다.");
    } else {
      this.boardDao.delete(b);
      contentMap.put("data", b);
    }

    return contentMap;
  }


  @GetMapping("/boards/{boardNo}")
  public Object getBoard(@PathVariable int boardNo) {

    Board b = this.boardDao.findByNo(boardNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();
    if (b == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 게시글이 없습니다.");
    } else {
      contentMap.put("stauts", "success");
      contentMap.put("data", b);
    }

    return contentMap;
  }


}
