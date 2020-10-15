package com.example.quizapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class QuizApiController {
    private List<Quiz> quizzes = new ArrayList<>();
    private QuizFileDao quizFileDao = new QuizFileDao();

    @GetMapping("/quiz")
    public Quiz quiz(){
        int index = new Random().nextInt(quizzes.size());
        return quizzes.get(index);
    }

    @GetMapping("/show")
    public List<Quiz> show(){
        return quizzes;
    }

    @PostMapping("/create")
    public void create(@RequestParam String question, @RequestParam boolean answer){
        Quiz quiz = new Quiz(question,answer);
        quizzes.add(quiz);
    }

    @GetMapping("/check")
    public String check(String question, boolean answer){
        for(Quiz quiz: quizzes){
            if(quiz.getQuestion().equals(question)){
                if(answer==quiz.isAnswer()) return "正解";
                else return "不正解";
            }
        }
        return "クイズがありません";
    }

    @PostMapping("/save")
    public String save(){
        try {
            quizFileDao.write(quizzes);
            return "ファイルに保存しました";
        } catch (IOException e) {
            e.printStackTrace();
            return "ファイルの保存に失敗しました";
        }
    }

    @GetMapping
    public String load(){
        try {
            quizzes = quizFileDao.read();
            return "ファイルを読み込みました";
        } catch (IOException e) {
            e.printStackTrace();
            return "読み込みを失敗しました";
        }
    }
}
