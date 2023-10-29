package com.hugo83.chap09.controller;

import com.hugo83.chap09.dto.TodoDTO;
import com.hugo83.chap09.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @RequestMapping("/list")
    public void list(Model model) {
        log.info("todo list.......");
        model.addAttribute("dtoList", todoService.getAll());
    }

    //@RequestMapping(value = "/register", method = RequestMethod.GET)
    @GetMapping(value = "/register")
    public void registerGET() {
        log.info("GET Todo register........");
    }

    @PostMapping(value = "/register")
    public String registerPOST(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("POST Todo register........");

        if (bindingResult.hasErrors()) { // 검증 추가
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            return "redirect:/todo/register";
        }
        log.info(todoDTO);
        todoService.register(todoDTO); // 등록기능 추가
        return "redirect:/todo/list";
    }

//    @GetMapping("/read")  // GET 한개짜리는 최초로 주석처리
//    public void read(Long tno, Model model){
//        TodoDTO todoDTO = todoService.getOne(tno);
//        log.info(todoDTO);
//        model.addAttribute("dto", todoDTO);
//    }
    @GetMapping({"/read", "/modify"})
    public void read(Long tno, Model model){
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);
        model.addAttribute("dto", todoDTO );
    }
    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes){
        log.info("-------------remove------------------");
        log.info("tno: " + tno);
        todoService.remove(tno); // 추가할 부분
        return "redirect:/todo/list";
    }
}
