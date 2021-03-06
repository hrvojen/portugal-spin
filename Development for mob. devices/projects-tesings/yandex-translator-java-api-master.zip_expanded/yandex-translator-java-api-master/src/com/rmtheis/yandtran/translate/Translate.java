/*
 * Copyright 2013 Robert Theis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rmtheis.yandtran.translate;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rmtheis.yandtran.ApiKeys;
import com.rmtheis.yandtran.YandexTranslatorAPI;
import com.rmtheis.yandtran.language.Language;

/**
 * Makes calls to the Yandex machine translation web service API
 */
public final class Translate extends YandexTranslatorAPI {

  private static final String SERVICE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?";
  private static final String TRANSLATION_LABEL = "text";
  public static String DEFAULT_LANG;

  
	private static final int String = 0;

	static String str="C 6Z PORCO COSTELETAS\nC 62 ERVILHAS IGLO 700G\nE 232 D PX INTEG LO 360G\nE 232 NUGETS PF POS40G UC";

	private static String[] poljeStr=null;
	private static ArrayList<String> listaStr=new ArrayList<>();
	private static List<String> listaStr2=new ArrayList<>();
	private static String polje[]=null;
  
  
  //prevent instantiation
  private Translate(){};

  /**
   * Translates text from a given Language to another given Language using Yandex.
   * 
   * @param text The String to translate.
   * @param from The language code to translate from.
   * @param to The language code to translate to.
   * @return The translated String.
   * @throws Exception on error.
   */
  public static String execute(final String text, final Language from, final Language to) throws Exception {
    validateServiceState(text); 
    final String params = 
        PARAM_API_KEY + URLEncoder.encode(apiKey,ENCODING) 
        + PARAM_LANG_PAIR + URLEncoder.encode(from.toString(),ENCODING) + URLEncoder.encode("-",ENCODING) + URLEncoder.encode(to.toString(),ENCODING) 
        + PARAM_TEXT + URLEncoder.encode(text,ENCODING);
    final URL url = new URL(SERVICE_URL + params);
    return retrievePropArrString(url, TRANSLATION_LABEL).trim();
  }

  private static void validateServiceState(final String text) throws Exception {
    final int byteLength = text.getBytes(ENCODING).length;
    if(byteLength>10240) { // TODO What is the maximum text length allowable for Yandex?
      throw new RuntimeException("TEXT_TOO_LARGE");
    }
    validateServiceState();
  }
  
  public static void main(String[] args) {
	  
	  
	  
		listaStr.add("C 62 PAO FORMA PO 600GR\nE 232 POLPA TOMAT. PD L3P2\nC 62 MASSA PO MACARRON 750");
		listaStr.add("2 X 0,49");
		listaStr.add("TALHO");
		listaStr.add("C 6Z PORCO COSTELETAS\nC 62 ERVILHAS IGLO 700G\nE 232 D PX INTEG LO 360G\nE 232 NUGETS PF POS40G UC");
		listaStr.add("Poupan�a Inediata");
		listaStr.add("Poupan�e Inediata\nPoupan�a Isedtata");
		
		for(int i=0;i<listaStr.size();i++){
			List<String> tempList = new ArrayList<String>();
			if(listaStr.get(i).contains("\n")){
				
				tempList=Arrays.asList(listaStr.get(i).split("\n"));
			}
			listaStr2.addAll(tempList);
		}
		
//poljeStr = str.split("\\n");	  
	  
    try {
      Translate.setKey(ApiKeys.YANDEX_API_KEY);
      
      for (String string : listaStr2) {
          String translation = Translate.execute(string, Language.PORTUGUESE, Language.ENGLISH);
          System.out.println("Translation: " + translation);
	}
      

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
