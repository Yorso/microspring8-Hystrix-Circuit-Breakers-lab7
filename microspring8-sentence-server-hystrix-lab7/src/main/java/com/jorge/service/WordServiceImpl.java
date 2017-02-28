package com.jorge.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorge.dao.AdjectiveClient;
import com.jorge.dao.ArticleClient;
import com.jorge.dao.NounClient;
import com.jorge.dao.SubjectClient;
import com.jorge.dao.VerbClient;
import com.jorge.domain.Word;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class WordServiceImpl implements WordService {

	@Autowired VerbClient verbClient;
	@Autowired SubjectClient subjectClient;
	@Autowired ArticleClient articleClient;
	@Autowired AdjectiveClient adjectiveClient;
	@Autowired NounClient nounClient;
	
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackSubject") //If something goes wrong, we call getFallbackSubject() fallback method
	public Word getSubject() {
		return subjectClient.getWord();
	}
	
	@Override
	public Word getVerb() { //We don't have a fallback method here. If something goes wrong, there is not any fallback method and an error could happen
		                    //Example: if you don't start up the verb server, http://localhost:8020/sentence crashes and shows error messages. With fallback methods doesn't,
		                    //the execution of the microservices goes on and the exception is treated in fallback method
		return verbClient.getWord();
	}
	
	@Override
	public Word getArticle() {
		return articleClient.getWord();
	}
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackAdjective")
	public Word getAdjective() {
		return adjectiveClient.getWord();
	}
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackNoun")
	public Word getNoun() {
		return nounClient.getWord();
	}	
	
	
	//FALLBACKS - If circuit breaker is open (open => something is going wrong) then fallbacks are called
	public Word getFallbackSubject() {
		return new Word("getFallbackSubject() called!!!"); //When an error happens, "getFallbackNoun() called!!!" is diplayed in page
	}
	
	public Word getFallbackAdjective() {
		return new Word(""); //"We have decided that it is not strictly necessary to have an adjective in our 
		                     //sentence if the adjective service is failing, so modify the getAdjective service 
		                     //to run within a Hystrix Command. Establish a fallback method that will return an 
		                     //empty Word (new Word(“”))."
	}
	
	public Word getFallbackNoun() {
		return new Word("getFallbackNoun() called!!!");
	}

}
