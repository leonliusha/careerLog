package com.careerlog.controller.MessageService;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.careerlog.entity.Message;
import com.careerlog.entity.Page;
import com.careerlog.entity.User;
import com.careerlog.service.MessageService;
import com.careerlog.util.MessageType;
@Controller
@RequestMapping(value="/message")
public class MessageController {
	@Resource(name="messageService")
	MessageService messageService;
	
	@RequestMapping(value="/newlog",method=RequestMethod.GET)
	public String writeNewLog(ModelMap model){
		model.addAttribute("Message",new Message());	
		return "NewLogPage";
	}
	
	@RequestMapping(value="/newlog",method=RequestMethod.POST)
	public String writeNewLog(@ModelAttribute("Message") Message message, HttpSession session, ModelMap model){
		User user = (User)session.getAttribute("user");
		message.setUserId(user.getUserId());
		message.setUserName(user.getUserName());
		Timestamp timeStamp = new Timestamp(new Date().getTime());
		message.setCreationDate(timeStamp);
		String messageTypeId =  new MessageType().getLog();
		message.setMessageTypeId(messageTypeId);
		message.setViewCount(0);
		message.setScore(0);
		message.setCommentCount(0);
		System.out.println(message.toString());
		messageService.insertMessage(message);
		return "redirect:/MyPage";
	}
	
	@RequestMapping(value="/logs",method=RequestMethod.GET)
	public String getLogsByPagination(ModelMap model,HttpSession session){
		Page<Message> page = new Page<Message>();
		User user = (User)session.getAttribute("user");
		page.setParams("userName", user.getUserName());
		page.setParams("messageTypeId", new MessageType().getLog());
		page.setResults(messageService.queryMessageByPage(page));
		//make content becomes to a short brief introduction
		int resultSize = page.getResults().size();
		for(int i=0; i<resultSize;i++){
			String contentIntro = this.getContentIntro(page.getResults().get(i).getText());
			page.getResults().get(i).setText(contentIntro);
		}
		model.addAttribute("page",page);
		return "logsPage";	
	}
	
	@RequestMapping(value="/logs/{pageNumber}")
	public String getLogsByPageNumber(@PathVariable("pageNumber") int pageNumber, ModelMap model, HttpSession session){
		Page<Message> page = new Page<Message>();
		User user = (User)session.getAttribute("user");
		page.setParams("userName",user.getUserName());
		page.setParams("messageTypeId", new MessageType().getLog());
		page.setStart(pageNumber);
		page.setResults(messageService.queryMessageByPage(page));
		//make content becomes to a short brief introduction
		int resultSize = page.getResults().size();
		for(int i=0; i<resultSize;i++){
			String contentIntro = this.getContentIntro(page.getResults().get(i).getText());
			page.getResults().get(i).setText(contentIntro);
		}
		model.addAttribute("page",page);
		return "logsPage";
	}
	
	@RequestMapping(value="/logid/{messageId}")
	public String getLogById(@PathVariable("messageId") int messageId, ModelMap model, HttpSession session){
		Message message = messageService.queryMessageById(messageId);
		model.addAttribute("message",message);
		return "logPage";
	}
	
	@RequestMapping(value="/indexAllMessages")
	public String createIndex() throws Exception{
		//create a file which will hold the index files
		final File file = new File("F:\\index");
		if(!file.exists() || !file.canRead()){
			throw new Exception("Document directory '"+file.getAbsolutePath()+"' does not exist or is not readable, check the path");
		}
		//open that index
		FSDirectory directory = FSDirectory.open(file);
		//unlock index directory if the directory has been locked by previous unsuccessful indexing process.
		if(IndexWriter.isLocked(directory))
			IndexWriter.unlock(directory);
		//create an analyzer used for constructing of IndexWriter
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
		//create an IndexWriterConfig for IndexWriter
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_46,analyzer);
		config.setOpenMode(OpenMode.CREATE);
		//constructing an IndexWriter
		IndexWriter writer = new IndexWriter(directory,config);
		//getting all Messages from Database
		List<Message> allMessages = messageService.queryAllMessages();
		long start = System.currentTimeMillis();
		try{
		for(int i=0;i<allMessages.size();i++){
			Message message = allMessages.get(i);
			Document document = new Document();
			//adding each Field into Document
			Field idField = new IntField("id",message.getMessageId(),Field.Store.NO);
			document.add(idField);
			Field titleField = new TextField("title",message.getTitle(),Field.Store.YES);
			document.add(titleField);
			Field contentField = new TextField("content",message.getText(),Field.Store.YES);
			document.add(contentField);
			Field creationField = new LongField("createdDate",message.getCreationDate().getTime(),Field.Store.NO);
			document.add(creationField);
			//Field modifiedField = new LongField("modified",message.getLastEditDate().getTime(),Field.Store.NO);
			//System.out.println("before document.add(modifiedField)");
			//document.add(modifiedField);
			Field scoreField = new IntField("score",message.getScore(),Field.Store.NO);
			document.add(scoreField);
			writer.addDocument(document);			
			/*if(writer.getConfig().getOpenMode() == OpenMode.CREATE){
				//new index,add doc
				writer.addDocument(document);
			}
			else{
				//existing index, update index
				writer.updateDocument(new Term("title",message.getTitle()), document);
			}*/
			
		}
		}catch (Exception e){
			e.printStackTrace();
		}
		finally{			
			writer.commit();
			writer.close();
			analyzer.close();
			directory.close();
		}
		long end = System.currentTimeMillis();
		return "MyPage";
	}
	
	@RequestMapping(value="/MessageSearching",method=RequestMethod.POST)
	public String MessageSearching(@ModelAttribute("queryString") String queryString, ModelMap model) throws IOException, ParseException{
		//same analyzer when creating index
	System.out.println("before new StandardAnalyzer()");
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
	System.out.println("before FSDirectory.open()");
		FSDirectory directory = FSDirectory.open(new File("F:\\index"));
		//specify the searching column as you put it in index file
	System.out.println("before new QueryParser()");
		Query query = new QueryParser(Version.LUCENE_46,"content",analyzer).parse(queryString);
		QueryParser q = new QueryParser(null, queryString, analyzer);
		int hitsPerPage=10;
	System.out.println("before IndexReader.open()");
		IndexReader reader = IndexReader.open(directory);
	System.out.println("before new IndexSearcher()");
		IndexSearcher searcher = new IndexSearcher(reader);
	System.out.println("before TopScoreDocCollector.create()");
		TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage,true);
	System.out.println("before searcher.search()");
		searcher.search(query, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		List<Message> searchingResult = new ArrayList<Message>();
		for(int i=0;i<hits.length;i++){
			int docId=hits[i].doc;
			Document d = searcher.doc(docId);
			Message message = new Message();
			message.setTitle(d.get("title"));
			message.setText(d.get("content"));
			message.setCreationDate(new Timestamp(new Long(d.get("createdDate"))));
			message.setScore(new Integer(d.get("score")));
			searchingResult.add(message);
		}
		model.addAttribute("searchingResult", searchingResult);
		return "searchingPage";
	}
	
	
	private String getContentIntro(String content){
		if(content==null)
			return "";
		int stringLength=0;
		int count =0;
		StringBuffer sb = new StringBuffer();
		char[] chars = content.toCharArray();
		int index = 0;
		for(index=0;index<chars.length;index++){
			if(chars[index]<255){
				count++;
				if(count==2){
					stringLength++;
					count=0;
				}
			}
			else{
				stringLength++;
			}
			if(stringLength>100){
				 return sb.toString();
			}
			sb.append(chars[index]);
		}
		return sb.toString();
	}
	
}
