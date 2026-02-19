package com.github.sky_rebel.skybot.api.extend.google_book;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GB1ApiService
{
	private final String API_KEY;

	private static final Logger LOGGER = LoggerFactory.getLogger(GB1ApiService.class);

	private static final String GOOGLE_BOOK_SEARCH_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";

	public GB1ApiService(String apiKey)
	{
		API_KEY = apiKey;
	}

	public String getApiKey()
	{
		return API_KEY;
	}

	public GB1ResponseInfo searchBook
	(
		String keyword,
		boolean useTitle,
		String title,
		boolean useAuthor,
		String author,
		boolean usePublisher,
		String publisher,
		boolean useSubject,
		String subject,
		boolean useISBN,
		String isbn,
		boolean useIccn,
		String iccn,
		boolean useOclc,
		String oclc,
		boolean onlyEpub,
		int startIndex,
		int maxResults,
		GB1OrderByType orderBy,
		GB1PrintType gb1PrintType,
		GB1FilterType gb1FilterType
	)
	{
		try
		{
			List<String> queryPartList = new ArrayList<>();
			queryPartList.add(useISBN ? "isbn".concat(":").concat(isbn) : "");
			queryPartList.add(useIccn ? "iccn".concat(":").concat(iccn) : "");
			queryPartList.add(useOclc ? "oclc".concat(":").concat(oclc) : "");
			queryPartList.add(useTitle ? "intitle".concat(":").concat(title) : "");
			queryPartList.add(useAuthor ? "inauthor".concat(":").concat(author) : "");
			queryPartList.add(useSubject ? "subject".concat(":").concat(subject) : "");
			queryPartList.add(usePublisher ? "inpublisher".concat(":").concat(publisher) : "");
			List<String> validParts = queryPartList.stream().filter(str -> !str.isEmpty()).toList();
			String q;
				q = validParts.isEmpty() ? keyword : String.join(" ", validParts);
				q = URLEncoder.encode(q, StandardCharsets.UTF_8);
			String url = GOOGLE_BOOK_SEARCH_BASE_URL + "q=" + q +
				(gb1FilterType != null ? "&filter=" + gb1FilterType.getValue() : "") +
				(onlyEpub ? "&download=epub" : "") +
				(orderBy != null ? "&orderBy=" + orderBy : "") +
				(gb1PrintType != null ? "&printType=" + gb1PrintType : "") +
				(startIndex != -1 ? "&startIndex=" + startIndex : "") +
				(maxResults != -1 ? "&maxResults=" + maxResults : "") +
				"&key=" + getApiKey();
			String validUrl = url.replaceAll(" ", "+");
			System.out.println(validUrl);
			HttpClient httpClient = HttpClient
				.newBuilder()
				.connectTimeout(Duration.ofMinutes(1))
				.build();
			HttpRequest httpRequest = HttpRequest
				.newBuilder()
				.uri(URI.create(validUrl))
				.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
				.GET()
				.build();
			HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			System.out.println(httpResponse.body());
			if (httpResponse.statusCode() == HttpURLConnection.HTTP_OK)
			{
				if (httpResponse.body() != null)
				{
					JSONObject volumeSearchResultJson = new JSONObject(httpResponse.body());
					GB1ResponseInfo gb1ResponseInfo = new GB1ResponseInfo();
					gb1ResponseInfo.kind = volumeSearchResultJson.optString("kind");
					gb1ResponseInfo.totalItems = volumeSearchResultJson.optInt("totalItems");
					JSONArray itemArray = volumeSearchResultJson.optJSONArray("items");
					if (itemArray != null)
					{
						if (!itemArray.isEmpty())
						{
							List<GB1ItemInfo> GB1ItemInfoList = new ArrayList<>();
							itemArray.forEach(obj ->
							{
								if (obj instanceof JSONObject itemInfoJson)
								{
									GB1ItemInfo gb1ItemInfo = GB1ItemInfo.getInstance(itemInfoJson);
									GB1ItemInfoList.add(gb1ItemInfo);
								}
							});
							gb1ResponseInfo.gb1ItemInfoList = GB1ItemInfoList;
							return gb1ResponseInfo;
						}
					}
				}
			}
			return new GB1ResponseInfo();
		}
		catch (IOException e)
		{
			LOGGER.error("searchBookAPI", e);
			return new GB1ResponseInfo();
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
			return new GB1ResponseInfo();
		}
	}

	public static void main() throws IOException, InterruptedException
	{
		String apiKey = "APIKEY";
		GB1ApiService GB1ApiService = new GB1ApiService(apiKey);
		String keyword = "Java编程";
		boolean useTitle = true;
		String title = "Java编程";
		boolean useAuthor = false   ;
		String author = null;
		boolean usePublisher = false;
		String publisher = null;
		boolean useSubject = false;
		String subject = null;
		boolean useISBN = false;
		String isbn = null;
		boolean useIccn = false;
		String iccn = null;
		boolean useOclc = false;
		String oclc = null;
		boolean onlyEpub = false;
		int startIndex = -1;
		int maxResults = 10;
		GB1OrderByType orderBy = null;
		GB1PrintType gb1PrintType = null;
		GB1FilterType gb1FilterType = null;
		GB1ResponseInfo gb1ResponseInfo = GB1ApiService.searchBook
		(
			keyword,
			useTitle,
			title,
			useAuthor,
			author,
			usePublisher,
			publisher,
			useSubject,
			subject,
			useISBN,
			isbn,
			useIccn,
			iccn,
			useOclc,
			oclc,
			onlyEpub,
			startIndex,
			maxResults,
			orderBy,
			gb1PrintType,
			gb1FilterType
		);
		System.out.println(gb1ResponseInfo.toString());
	}
}
