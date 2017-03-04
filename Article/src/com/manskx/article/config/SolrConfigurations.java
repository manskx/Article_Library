package com.manskx.article.config;

public class SolrConfigurations {
	// solr engine base server url
	// this is important because in most cases solr is not running in the same
	// machine
	public static final String SOLR_HOST = "http://localhost:8983/solr/article_library/";
	// these are some configurations to enhance search results accuracy based on
	// query scoring
	// There numbers are generated from multiple experiments, we could select
	// the best values
	// by learning machine learning module and doing data annotation
	public static final int SOLR_BODY_SCORE = 6;
	public static final int SOLR_TITLE_SCORE = 10;
	public static final int SOLR_SENTENCE_SCORE = 5;

	// the output formate to show results
	public static final String SOLR_RESULTS_FORMATE = "json";
	// the fields to be shown in the result json/xml
	public static final String SOLR_RESULTS_FIELDS = "id,title,body";
	// stopping words file
	public static final String SOLR_STOPPING_WORDS_FILE = "/home/mansk/workspace/article_library/solr-6.3.0/server/solr/article_library/conf/stopwords.txt";

	// Solr dataimport type
	public static final String SOLR_DATA_IMPORT_TYPE = "full-import";
}
