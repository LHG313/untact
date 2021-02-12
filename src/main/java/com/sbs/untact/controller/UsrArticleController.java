package com.sbs.untact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;

@Controller
public class UsrArticleController {
	private int articleLastId;
	private List<Article> articles;

	public UsrArticleController() {
		// 멤버변수 초기화
		articleLastId = 0;
		articles = new ArrayList<>();

		// 게시물 2개 생성
		articles.add(new Article(++articleLastId, "2020-12-12 12:12:21", "제목1", "내용1"));
		articles.add(new Article(++articleLastId, "2020-12-12 12:12:21", "제목2", "내용2"));
	}

	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
		return articles.get(id - 1);
	}

	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {
		return articles;
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Map<String, Object> doAdd(String regDate, String title, String body) {
		articles.add(new Article(++articleLastId, regDate, title, body));

		Map<String, Object> rs = new HashMap<>();
		rs.put("resultCode", "S-1");
		rs.put("msg", "성공하였습니다.");
		rs.put("id", "articlesLastId");
		
		
		return rs;
	}

}