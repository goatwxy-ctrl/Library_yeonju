package com.example.Test_Book.controller;

import com.example.Test_Book.dto.BookDTO;
import com.example.Test_Book.entity.BookEntity;
import com.example.Test_Book.service.BookInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class BookController {

    String path = System.getProperty("user.dir")
            + "//src//main//resources//static//image";

    @Autowired
    BookInterface bookInterface;

    @GetMapping("/")
    public String bb1(){
        return "main";}

    @GetMapping("/inputgo")
    public String bb2(){
        return "input";}

    @PostMapping("/inputsave")
    public String bb3(BookDTO dto) throws IOException {
        MultipartFile mf = dto.getImage();
        String fname = mf.getOriginalFilename(); //화일명만 가져옴
        //화일파일 고유번호 지정
        UUID ud = UUID.randomUUID();
        fname = ud.toString() + "-" + fname;

        //디비에 저장
        dto.setImage_name(fname);
        BookEntity bookEntity = dto.toentity(); //숨김기능
        bookInterface.insert(bookEntity);

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }//삭제필요 임시용

        //이미지 폴더에 저장
        mf.transferTo(new File(path + "\\" + fname));
        return "redirect:/";
    }

    @GetMapping("/outgo")
    public String bb4(Model mo){
        List<BookEntity>list = bookInterface.allout();
        mo.addAttribute("list",list);
        return "out";
    }

    @GetMapping("/update")
    public String bb5(@RequestParam("number") long number, Model mo){
        BookEntity dto = bookInterface.update(number);
        mo.addAttribute("dto",dto);
        return "updateview";
    }

    @PostMapping("/updatesave")
    public String bb6(BookDTO dto) throws IOException {

        BookEntity old = bookInterface.update(dto.getBunho());

        MultipartFile mf = dto.getImage();
        if (mf != null && !mf.isEmpty()) {

            //기존이미지 삭제
            if (old.getImage_name() != null) {
                File oldFile = new File(path + "\\" + old.getImage_name());
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }

            //새이미지 저장
            String fname = mf.getOriginalFilename();
            UUID ud = UUID.randomUUID();
            fname = ud.toString() + "-" + fname;
            dto.setImage_name(fname);
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }//삭제필요시

            mf.transferTo(new File(path + "\\" + fname));
            } else {
                // 새 이미지가 없으면 기존 이미지명 유지
                dto.setImage_name(old.getImage_name());
            }

            //재업로드
            BookEntity bookEntity = dto.reentity();
            bookInterface.reupdate(bookEntity);
            return "redirect:/outgo";
        }

        @GetMapping("/delete")
        public String bb7(@RequestParam("number")long number,
                          @RequestParam("delimage")String delimage,Model model){
            bookInterface.delete(number);

            //이미지폴더 삭제
            File imgFile = new File(path+"\\"+delimage);
            imgFile.delete();

            return "redirect:/";

        }

        @GetMapping("/detail")
        public String bb8(@RequestParam("number") long number, Model mo){

         bookInterface.count(number);

        BookEntity dto = bookInterface.detail(number);
        mo.addAttribute("dto",dto);

        return "detailview";
        }

        @GetMapping("/searchgo")
        public String bb9(){
        return "booksearch";
        }

        @PostMapping("/searchsave")
        public String bb10(@RequestParam("bkey")String bkey,
                           @RequestParam("bvalue")String bvalue,Model model) {
          List<BookEntity>list = bookInterface.searchgo(bkey,bvalue);
          model.addAttribute("list", list);
          return "searchview";
         }

}





