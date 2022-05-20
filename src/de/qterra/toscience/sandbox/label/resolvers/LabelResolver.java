/**
 * 
 */
package de.qterra.toscience.sandbox.label.resolvers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

/**
 * @author aquast
 *
 */
public interface LabelResolver {

    public String lookup(String urlString, String Language);

    public static class Factory {

        public static LabelResolver getInstance(String urlString) {
            LabelResolver lResolv = null;
            URL url = createUrlFromString(urlString);
            System.out.println("Domain extracted from urlString: " + url.getHost());
            lResolv = getLabelResolver(url.getHost());
            return lResolv;
        }

        public boolean existsLabelResolver(String urlString) {
            URL url = createUrlFromString(urlString);
            return getLabelResolverTable().containsKey(url.getHost());
        }

        private static URL createUrlFromString(String urlString) {
            URL createdUrl = null;
            try {
                createdUrl = new URL(urlString);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
              System.out.println("Can't generate URL from " + urlString);
            }
            return createdUrl;
        }

        private static Hashtable<String, LabelResolver> getLabelResolverTable() {

            Hashtable<String, LabelResolver> lResolver = new Hashtable<String, LabelResolver>();
            // put all known Class that implements the Interface into Hashtable
            lResolver.put(OrcaMediaTypesLabelResolver.DOMAIN, new OrcaMediaTypesLabelResolver());
            lResolver.put(ResearchOrganizationLabelResolver.DOMAIN, new ResearchOrganizationLabelResolver());
            return lResolver;
        }

        private static LabelResolver getLabelResolver(String domain) {
          System.out.println("Method getLabelResolver Domain : " + domain);
            Hashtable<String, LabelResolver> lResolvTable = getLabelResolverTable();
            LabelResolver labelResolver = lResolvTable.get(domain);
            System.out.println(lResolvTable.get(domain).toString());
            System.out.println("Return LabelResolver of Class: " + labelResolver.getClass().toString());
            return labelResolver;
        }

    }

}
