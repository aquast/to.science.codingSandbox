/**
 * 
 */
package de.qterra.toscience.sandbox.label.resolvers;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author aquast
 *
 */
public class OrcaMediaTypesLabelResolver extends LabelResolverService implements LabelResolver {

    public OrcaMediaTypesLabelResolver() {
    }

    public final static String DOMAIN = "medientypen.orca.nrw";

    public String lookup(String uri, String language) {
        String label = null;
        //System.out.println("Search label for: " + uri);
        GenericPropertiesLoader genProp = new GenericPropertiesLoader();
        Map<String, String> map = genProp.loadVocabMap("OrcaMedientypen-de.properties");
        if (map.get(uri) != null) {
            System.out.println("Found Label " + map.get(uri) + " for: " + uri);
            label = map.get(uri);
        } else {
            label = uri;
        }
        return label;

    }

    @Override
    protected void lookupAsync(String uri, String language) {
      // TODO Auto-generated method stub
      
    }


}
