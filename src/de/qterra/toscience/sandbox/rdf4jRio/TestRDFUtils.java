package de.qterra.toscience.sandbox.rdf4jRio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.impl.TreeModel;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.RDFParseException;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.UnsupportedRDFormatException;
import org.eclipse.rdf4j.rio.helpers.StatementCollector;

public class TestRDFUtils {

  private RDFFormat inputFormat = null;
  private RDFFormat outFormat = null;
  private String baseUrl = "https://api.paideia.hbz-nrw.de/resource/orca:415a81e0-6457-4321-afc0-e2ba537723c3.rdf";
  private String mimetype = null;

  public RDFFormat getInputFormat() {
    return inputFormat;
  }

  public void setInputFormat(RDFFormat inputFormat) {
    this.inputFormat = inputFormat;
  }

  public RDFFormat getOutFormat() {
    return outFormat;
  }

  public void setOutFormat(RDFFormat outFormat) {
    this.outFormat = outFormat;
  }

  public InputStream remoteFileLoader() {
    java.net.URL documentUrl = null;
    InputStream inputStream = null;
    try {
      documentUrl = new URL(baseUrl);
      mimetype = documentUrl.openConnection().getContentType();
      inputStream = documentUrl.openStream();
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.getMessage();
    }
    return inputStream;
  }

  public InputStream fileLoader() {
    File jsonFile = new File(System.getProperty("user.dir") + "/resources/hoerkaen.json");
    mimetype = "application/ld+json";
    InputStream bis = null;
    try {
      FileInputStream fis = new FileInputStream(jsonFile);
      bis = new BufferedInputStream(fis);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return bis;
  }

  /**
   * Method operates as equivalent (replacement?) for RdfUtils.readRdfToGraph(InputStream, RDFFormat, String)
   * only RDFFormat is taken from variable InputFormat.
   * RDF or Json will be parsed into an semantic graph i.e a Collection of Statements   
   * @param inStream
   * @param baseUrl
   * @return
   */
  public Collection<Statement> createStatementCollection(InputStream inStream) {
    Collection<Statement> rdfGraph = new TreeModel();

    RDFParser rdfParser = null;

    try {
      rdfParser = Rio.createParser(inputFormat);      
      StatementCollector collector = new StatementCollector(rdfGraph);
      rdfParser.setRDFHandler(collector);
    }catch (UnsupportedRDFormatException e) {
      System.out.println("UnsupportedRDFormatException" + e.getMessage());      
    }

    try {
      rdfParser.parse(inStream, baseUrl);
    } catch (IOException e) {
      System.out.println("IOException" + e.getMessage());
    } catch (RDFParseException e) {
      // handle unrecoverable parse error
      System.out.println("RDFParseException" + e.getMessage());
    } catch (RDFHandlerException e) {
      // handle a problem encountered by the RDFHandler
      System.out.println("RDFHandlerException" + e.getMessage());
    } catch (Exception e) {
      System.out.println("Any other Exception" + e.getMessage());
    }

    return rdfGraph;
  }

  public void printCollectionItems(Collection<Statement> collection) {
    Iterator<Statement> cit = collection.iterator(); 
    while(cit.hasNext()) {
      Statement statement = cit.next();
      System.out.println(statement.getSubject() + "  |  " + statement.getPredicate() + "  |  " + statement.getObject().toString());
      //System.out.println(statement.getPredicate());
      //System.out.println(statement.getObject().toString());
    }
  }

  public RDFFormat getRDFFormat() {
    RDFFormat rdfFormat = Rio.getParserFormatForMIMEType(mimetype).orElse(RDFFormat.N3);
    System.out.println("Applied MimeType: " + mimetype);
    System.out.println("Found RDFFormat: " + rdfFormat.toString());
    return rdfFormat;
  }

}
