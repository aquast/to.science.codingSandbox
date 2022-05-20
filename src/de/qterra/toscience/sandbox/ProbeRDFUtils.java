/**
 * 
 */
package de.qterra.toscience.sandbox;

import java.io.InputStream;
import java.util.Collection;

import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.RDFFormat;

import de.qterra.toscience.sandbox.rdf4jRio.TestRDFUtils;

/**
 * @author aquast
 *
 */
public class ProbeRDFUtils {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

    TestRDFUtils test = new TestRDFUtils();
    test.setInputFormat(RDFFormat.RDFXML);

    
    System.out.println("\nProbe with local file");
    InputStream inputStream = test.fileLoader();
    test.setInputFormat(test.getRDFFormat());
    Collection<Statement> collection = test.createStatementCollection(inputStream);
    test.printCollectionItems(collection);

    System.out.println("\nProbe with remote file");
    inputStream = test.remoteFileLoader();
    test.setInputFormat(test.getRDFFormat());
    collection = test.createStatementCollection(inputStream);
    test.printCollectionItems(collection);

  }

}
