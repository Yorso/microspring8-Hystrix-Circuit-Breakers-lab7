package com.jorge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorge.dao.AdjectiveClient;
import com.jorge.dao.ArticleClient;
import com.jorge.dao.NounClient;
import com.jorge.dao.SubjectClient;
import com.jorge.dao.VerbClient;

/**
 * Build a sentence by assembling randomly generated subjects, verbs, 
 * articles, adjectives, and nouns.  The individual parts of speech will 
 * be obtained by calling the various DAOs.
 */
@Service
public class SentenceServiceImpl implements SentenceService {

	@Autowired WordService wordService;
	

	/**
	 * Assemble a sentence by gathering random words of each part of speech:
	 */
	public String buildSentence() {
		return  
			String.format("%s %s %s %s %s.",
				wordService.getSubject().getString(),
				wordService.getVerb().getString(),
				wordService.getArticle().getString(),
				wordService.getAdjective().getString(),
				wordService.getNoun().getString() )
			;
	}	
}
