package com.jorge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorge.dao.AdjectiveClient;
import com.jorge.dao.ArticleClient;
import com.jorge.dao.NounClient;
import com.jorge.dao.SubjectClient;
import com.jorge.dao.VerbClient;
import com.jorge.domain.Word;
import com.jorge.domain.Word.Role;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;

import rx.Observable;

/**
 * @author Ken Krueger, Jorge Centeno Fernandez 
 */
@Service
public class WordServiceImpl implements WordService {

	@Autowired VerbClient verbClient;
	@Autowired SubjectClient subjectClient;
	@Autowired ArticleClient articleClient;
	@Autowired AdjectiveClient adjectiveClient;
	@Autowired NounClient nounClient;
	
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackSubject")
	public Observable<Word> getSubject() {
	    return new ObservableResult<Word>() {
	        @Override
	        public Word invoke() {
	        	return new Word (subjectClient.getWord().getWord(), Role.subject);
	        }
	    };
	}
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackVerb")
	public Observable<Word> getVerb() {
		 return new ObservableResult<Word>() {
		        @Override
		        public Word invoke() {
		        	return new Word (verbClient.getWord().getWord(), Role.verb);
		        }
		    };
	}
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackArticle")
	public Observable<Word> getArticle() {
		 return new ObservableResult<Word>() {
		        @Override
		        public Word invoke() {
		        	return new Word (articleClient.getWord().getWord(), Role.article);
		        }
		 };
	}
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackAdjective")
	public Observable<Word> getAdjective() {
	    return new ObservableResult<Word>() {
	        @Override
	        public Word invoke() {
	        	return new Word (adjectiveClient.getWord().getWord(), Role.adjective);
	        }
	    };
	}
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackNoun")
	public Observable<Word> getNoun() {
	    return new ObservableResult<Word>() {
	        @Override
	        public Word invoke() {
	        	return new Word (nounClient.getWord().getWord(), Role.noun);
	        }
	    };
	}	
	
	
	
	public Word getFallbackSubject() {
		return new Word("Someone", Role.subject);
	}
	
	public Word getFallbackVerb() {
		return new Word("does", Role.verb);
	}
	
	public Word getFallbackArticle() {
		return new Word("", Role.article);
	}
	
	public Word getFallbackAdjective() {
		return new Word("", Role.adjective);
	}
	
	public Word getFallbackNoun() {
		return new Word("something", Role.noun);
	}

}
