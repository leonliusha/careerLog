package com.careerlog.controller.MessageService;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

public class AnalyzerTest {

public static void analyze(Analyzer analyzer, String text) throws Exception {
	List l = new LinkedList();
	Set s = new HashSet();
	Map m = new HashMap();
	List l1 = new ArrayList();
   System.out.println("分词器：" + analyzer.getClass());
   TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));
   tokenStream.addAttribute(CharTermAttribute.class);  
   tokenStream.reset();
        while (tokenStream.incrementToken()) {
               CharTermAttribute chartermAttribute =tokenStream.addAttribute(CharTermAttribute.class);
               System.out.println(chartermAttribute.toString());                                         
        }
        tokenStream.end();
        tokenStream.close();
}

public static void main(String[] args) throws Exception {
   String enText = "China is a great country!";
   String chText = "昨天，一阵雷阵雨后，空气异常地清新！";
   SimpleAnalyzer analyzer1 = new SimpleAnalyzer(Version.LUCENE_46);
   analyze(analyzer1,enText);
   analyze(analyzer1,chText);
   StopAnalyzer analyzer2 = new StopAnalyzer(Version.LUCENE_46);
   analyze(analyzer2,enText);
   analyze(analyzer2,chText);
   StandardAnalyzer analyzer3 = new StandardAnalyzer(Version.LUCENE_46);
   analyze(analyzer3,enText);
   analyze(analyzer3,chText);//逐个字切分
   WhitespaceAnalyzer analyzer4 = new WhitespaceAnalyzer(Version.LUCENE_46);
   analyze(analyzer4,enText);
   analyze(analyzer4,chText);
   KeywordAnalyzer analyzer5 = new KeywordAnalyzer();
   analyze(analyzer5,enText);
   analyze(analyzer5,chText);
   }
}