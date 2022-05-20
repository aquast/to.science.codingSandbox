package de.qterra.toscience.sandbox.label.resolvers;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LocaleExaminer {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String lang = new String("ger");
    LocaleExaminer lexi = new LocaleExaminer();
    
    String result = lexi.iso639_1TagExtractor(lang);
    System.out.println("Das Ergebnis ist: " + result);
    
  }
  
  
  private String iso639_1TagExtractor(String iso639_2Uri) {
    String result = "unknown";
    Locale loc = Locale.forLanguageTag(
        iso639_2Uri.replace("http://id.loc.gov/vocabulary/iso639-2/", "").replace("ger", "deu"));
    Map<String, Locale> localeMap = new HashMap<String, Locale>();
    String[] iso639_1Tags = Locale.getISOLanguages();
    for(int i=0; i < iso639_1Tags.length; i++) {
      Locale locale = new Locale(iso639_1Tags[i]);
      localeMap.put(locale.getISO3Language(), locale);
    }
    if(localeMap.get(loc.getISO3Language()) != null) {
    result = localeMap.get(loc.getISO3Language()).getLanguage();    
    System.out.println("Die Sprache ist:  " + localeMap.get(loc.getISO3Language()).getDisplayLanguage(loc));
    }
  return result;
  }


}
