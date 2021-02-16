package com.sbs.untact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;
import com.sbs.untact.util.Util;

@Controller
public class UsrArticleController {
	private int articlesLastId;
	private List<Article> articles;

	public UsrArticleController() {
		// 멤버변수 초기화
		articlesLastId = 0;
		articles = new ArrayList<>();

		// 게시물 2개 생성
		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:21", "2020-12-12 12:12:21", "제목1", "내용1"));
		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:21", "2020-12-12 12:12:21", "제목2", "내용2"));
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
	public Map<String, Object> doAdd(String title, String body) {

		String regDate = Util.getNowDateStr();
		String updateDate = regDate;

		articles.add(new Article(++articlesLastId, regDate, updateDate, title, body));

		return Util.mapOf("resultCode", "S-1", "msg", "성공하였습니다.", "id", articlesLastId);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public Map<String, Object> doDelete(int id) {
		boolean deleteArticleRs = deleteArticle(id);

		Map<String, Object> rs = new HashMap<>();

		if (deleteArticleRs ==false) {
			return Util.mapOf("resultCode", "F-1", "msg", "해당 게시물은 존재하지 않습니다." );
		}

		else {
			return Util.mapOf("resultCode", "S-1", "msg", "성공하였습니다.", "id", id );
			
		}
	}

	private boolean deleteArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				articles.remove(article);
				return true;
			}
		}
		return false;
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Map<String, Object> doModify(int id, String title, String body) {
		Article selArticle = null;

		for (Article article : articles) {
			if (article.getId() == id) {
				selArticle = article;
				break;
			}
		}
		Map<String, Object> rs = new HashMap<>();
		
		if (selArticle == null) {
			return Util.mapOf("resultCode", "F-1", "msg", String.format("%d번 게시물은 존재하지 않습니다.", id));
		}

		selArticle.setUpdateDate(Util.getNowDateStr());
		selArticle.setTitle(title);
		selArticle.setBody(body);

		return Util.mapOf("resultCode", "S-1", "msg", String.format("%d번 게시물이 수정되었습니다.", id), "id", id);
	}
}
