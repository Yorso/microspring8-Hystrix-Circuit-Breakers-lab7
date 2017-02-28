package com.jorge.service;

import com.jorge.domain.Word;

public interface WordService {

	Word getSubject();
	Word getVerb();
	Word getArticle();
	Word getAdjective();
	Word getNoun();

}
