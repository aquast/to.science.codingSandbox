package de.qterra.toscience.sandbox.label.resolvers;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author aquast LabelResolverService provides the default methods required for
 *         the specific Authority Files LabelResolvers. LabelResolverService can
 *         be extended by specific LabelResolver Clsses that implement the
 *         LabelResolver Interface
 *
 */
public abstract class LabelResolverService implements Runnable {

    public String urlString = null;
    public String label = null;
    public String language = null;

    /**
     * Default lookup Method that returns a label for an given Uri-String.
     * Either from Etikett Cache or from requesting the Url
     * 
     * @param uri
     *            the URI-String or URI-String we like to have resolved as label
     * @param language
     *            not supported
     * @return etikettLabel The label requested
     */
    public String lookup(String uri, String language) {
        return "Test";
    }

    protected abstract void lookupAsync(String uri, String language);

 
    @Override
    public void run() {

        lookupAsync(urlString, language);

    }

    protected void runLookupThread() {

        Thread thread = new Thread(this);
        thread.start();
    }

}
