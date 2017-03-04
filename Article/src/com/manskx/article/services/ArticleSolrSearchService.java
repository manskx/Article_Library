package com.manskx.article.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manskx.article.config.SolrConfigurations;
import com.manskx.article.models.Article;

public class ArticleSolrSearchService {
	private static ArticleSolrSearchService instance;

	private static Map<String, Integer> stoppingWordsList;

	private ArticleSolrSearchService() {
	}

	public static ArticleSolrSearchService getInstance() throws IOException {
		if (instance == null) {
			instance = new ArticleSolrSearchService();
			stoppingWordsList = getStoppingWords();
		}

		return instance;
	}

	public List<Article> getArticlesSearchRsults(String query)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, String> solrQueryParam = createSolrQueryParams(query);

		// do ge request to solr server and return the results
		String responseEntity = ClientBuilder.newClient().target(SolrConfigurations.SOLR_HOST).path("select")
				.queryParam("defType", solrQueryParam.get("defType")).queryParam("fl", solrQueryParam.get("fl"))
				.queryParam("indent", solrQueryParam.get("indent")).queryParam("q", solrQueryParam.get("q"))
				.queryParam("qf", solrQueryParam.get("qf")).queryParam("wt", solrQueryParam.get("wt"))

				.request().get(String.class);
		JSONObject responseJSON_obj = new JSONObject(responseEntity);
		ObjectMapper mapper = new ObjectMapper();
		String sss = responseJSON_obj.getJSONObject("response").getJSONArray("docs").toString();
		List<Article> myObjects = Arrays.asList(mapper.readValue(sss, Article[].class));
		return myObjects;
	}

	/**
	 * this method is responsable for creating the solr query parameters
	 * 
	 * @param searchQuery
	 *            input search query from the user
	 * @return hashmap contains all query parameters to be used in url call
	 * @throws UnsupportedEncodingException
	 */
	public Map<String, String> createSolrQueryParams(String searchQuery) {
		List<String> words = Arrays.asList(searchQuery.split(" "));
		ArrayList<String> cleanWords = removeStoppingWords(words);
		// create query words
		String wordsQuery = createQueryWords(cleanWords);

		Map<String, String> solrQueryParam = new HashMap<String, String>();
		solrQueryParam.put("defType", "edismax");
		// selected fields to be shown in the results
		solrQueryParam.put("fl", SolrConfigurations.SOLR_RESULTS_FIELDS);

		solrQueryParam.put("indent", "c");
		String searchQueryPlus = searchQuery.replaceAll(" ", "+");

		// if the complete sentence appears in the text, so it has higher score
		// than seperate words
		String qSolrQuery = "title:" + wordsQuery + "+OR+" + "\"" + searchQueryPlus + "\"^"
				+ SolrConfigurations.SOLR_SENTENCE_SCORE + " body:" + wordsQuery + "+OR+" + "\"" + searchQueryPlus
				+ "\"^" + SolrConfigurations.SOLR_SENTENCE_SCORE;

		solrQueryParam.put("q", qSolrQuery);
		// let some fields have higher score than other fields
		// ex: if word "cancer" appears in title then it is better than to be
		// appeared in content
		String qfSolrQuery = "title^" + SolrConfigurations.SOLR_TITLE_SCORE + " body^"
				+ SolrConfigurations.SOLR_BODY_SCORE;

		solrQueryParam.put("qf", qfSolrQuery);
		// result format
		solrQueryParam.put("wt", SolrConfigurations.SOLR_RESULTS_FORMATE);

		return solrQueryParam;

	}

	/**
	 * 
	 * @param clearWords
	 *            query words to be inserted to query string
	 * @return clear words seperated by "+" operator
	 */
	private String createQueryWords(ArrayList<String> clearWords) {
		String wordsQuery = "";
		String separator = "";

		for (String s : clearWords) {
			wordsQuery += separator + s;
			separator = "+";
		}
		return wordsQuery;
	}

	/**
	 * this is genaric method to remove stopping words inside an input string
	 * query
	 * 
	 * @param query
	 * @return list of clean words that don't exists in the list of stopping
	 *         words
	 */
	public ArrayList<String> removeStoppingWords(List<String> words) {
		ArrayList<String> cleanWords = new ArrayList<String>();
		for (int i = 0; i < words.size(); i++) {
			if (!stoppingWordsList.containsKey(words.get(i)))
				cleanWords.add(words.get(i));
		}
		return cleanWords;
	}

	/**
	 * this methods reads the stopping words from solr file and returns it
	 * 
	 * @return hashmap contains list of stopping words
	 * @throws IOException
	 */
	private static Map<String, Integer> getStoppingWords() throws IOException {
		// Open the file
		FileInputStream fstream = new FileInputStream(SolrConfigurations.SOLR_STOPPING_WORDS_FILE);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		Map<String, Integer> stoppingWordsList = new HashMap<String, Integer>();
		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			// ignore comment line ( starts with "#")
			if (!strLine.isEmpty() && strLine.charAt(0) != '#') {
				stoppingWordsList.put(strLine, 0);
			}

		}
		// Close the input stream
		br.close();
		return stoppingWordsList;
	}

	public static void importData() {
		Response response = ClientBuilder.newClient().target(SolrConfigurations.SOLR_HOST).path("dataimport")
				.queryParam("command", SolrConfigurations.SOLR_DATA_IMPORT_TYPE).request().get();

	}
}
