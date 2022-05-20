package de.qterra.toscience.sandbox;

import java.util.Hashtable;
import java.util.Locale;
import de.qterra.toscience.sandbox.label.resolvers.*;

public class CheckResolvers {

  public static void main(String[] args) {
    
    // Check for Language 
    System.out.println("\nCheck for Language Resolving by Locale.Class");
    Locale loc = Locale.forLanguageTag("de");
    System.out.println(loc.getISO3Language());
    System.out.println(loc.getDisplayLanguage());
    System.out.println(loc.getLanguage());
    
    // Check for LabelResolver
    System.out.println("\nCheck for LabelResolver");
    LabelResolver lRes1 = LabelResolver.Factory.getInstance("https://ror.org/00n3mcd10");
    LabelResolver lRes2 = LabelResolver.Factory.getInstance("https://medientypen.orca.nrw/audio");
    lRes1.lookup("https://ror.org/00n3mcd10", null);
    lRes2.lookup("https://medientypen.orca.nrw/audio", null);
    
    // Low level code for turn from "lastname, firstname" to "firstname lastname" 
    System.out.println("\nLow level code for turn from \"lastname, firstname\" to \"firstname lastname\"");
    Hashtable<String, String> ht = new Hashtable<>();
    ht.put("Person", "Quast, Andres");
    ht.put("Institut", "hbz");
    StringBuffer personNameLabel = new StringBuffer();
    String result = "Nix";
    if(ht.containsKey("Person")) {
      String[] pName = ht.get("Person").split(", ");
      for (int i = pName.length; i > 0; i--) {
        personNameLabel.append(pName[i - 1] + " ");
      }      
    }
    
    System.out.println("Ergebnis: " + personNameLabel.toString());
  } 
  

}
