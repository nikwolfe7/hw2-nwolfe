package edu.cmu.deiis.types;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.activemq.transport.stomp.Stomp.Headers.Send;
import org.apache.uima.UIMA_IllegalArgumentException;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.ProcessTrace;
import org.apache.uima.jcas.tcas.Annotation;

/**
 * @author nwolfe
 *
 */
public class NERCasConsumer<T extends NamedEntityAnnotation> extends CasConsumer_ImplBase {

  private Double confidenceThreshold = -350.0;

  private PrintStream writer;

  private String HMMEngine = "edu.cmu.deiis.types.NamedEntityAnalysisEngine";

  private String ABNEngine = "edu.cmu.deiis.types.AbnerAnalysisEngine";

  private HashMap<String, ArrayList<AnnotationDataStorage>> mergeList;

  private HashMap<String, ArrayList<AnnotationDataStorage>> HMMlist;

  private HashMap<String, ArrayList<AnnotationDataStorage>> ABNlist;

  private ArrayList<String> alreadySeen;

  /**
   * 
   * @author nwolfe This class is to serve as a data storage member the useful data items which have
   *         been culled from a {@link T};
   */
  private class AnnotationDataStorage {
    Integer begin;

    Integer end;

    @SuppressWarnings("unused")
    Double confidence;

    String namedEntity;

    String outputString;

    String sentenceId;

    public AnnotationDataStorage(T nea, String sentId) {
      this.begin = nea.getBegin();
      this.end = nea.getEnd();
      this.namedEntity = nea.getNamedEntityString();
      this.confidence = nea.getConfidence();
      this.sentenceId = sentId;
      this.outputString = this.sentenceId + "|" + this.begin.toString() + " " + end.toString()
              + "|" + namedEntity;
    }
  }

  @Override
  public void initialize() throws ResourceInitializationException {
    mergeList = new HashMap<String, ArrayList<AnnotationDataStorage>>();
    HMMlist = new HashMap<String, ArrayList<AnnotationDataStorage>>();
    ABNlist = new HashMap<String, ArrayList<AnnotationDataStorage>>();

    try {
      this.writer = new PrintStream(new File(getConfigParameterValue(
              UIMATypeEnum.OUTPUT_FILE.getParam()).toString()));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new UIMA_IllegalArgumentException();
    }
  };

  public void destroy() {
    this.writer.flush();
    this.writer.close();
  };

  /**
   * @param model
   *          Given a model of strings mapping to {@link T} objects, print to the output file...
   */
  protected void outputAnnotation(HashMap<String, ArrayList<AnnotationDataStorage>> model) {
    for (String key : model.keySet()) {
      Iterable<AnnotationDataStorage> iter = model.get(key);
      for (AnnotationDataStorage nea : iter) {
        this.writer.println(nea.outputString);
      }
    }
  }

  @Override
  public void collectionProcessComplete(ProcessTrace arg0) throws ResourceProcessException,
          IOException {
    // TODO Auto-generated method stub
    super.collectionProcessComplete(arg0);
    compareAnnotations();
    outputAnnotation(mergeList);
  }

  /*
   * Compare the annotaion lists Simple intersection...
   * Remove words which are only 1 letter long...
   */
  private void compareAnnotations() {
    for (String key : HMMlist.keySet()) {
      if (ABNlist.containsKey(key)) {
        for (AnnotationDataStorage gene : HMMlist.get(key)) {
          if( gene.namedEntity.length() > 1 ) {
            addItemToMap(key, gene, mergeList);
          }
        }
      }
    }
  }

  /*
   * Add items to one of these complex sentence mappings...
   */
  private void addItemToMap(String sentenceId, AnnotationDataStorage ds,
          HashMap<String, ArrayList<AnnotationDataStorage>> map) {
    if (!map.containsKey(sentenceId)) {
      map.put(sentenceId, (new ArrayList<AnnotationDataStorage>()));
    }
    map.get(sentenceId).add(ds);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void processCas(CAS aCAS) throws ResourceProcessException {
    try {
      JCas jCas = aCAS.getJCas();
      FSIterator<TOP> fsIterator = jCas.getJFSIndexRepository().getAllIndexedFS(Sentence.type);

      if (fsIterator.hasNext()) {
        Sentence sentence = (Sentence) fsIterator.next();
        String sentenceId = sentence.getSentenceId();

        // Divide annotations into lists...
        Iterable<Annotation> iter = jCas.getAnnotationIndex(T.type);
        for (Annotation a : iter) {
          // System.out.println(a);
          T nea = (T) a;
          AnnotationDataStorage ds = new AnnotationDataStorage(nea, sentenceId);
          String cpid = nea.getCasProcessorId();
          if (cpid.equals(HMMEngine)) {
            addItemToMap(sentenceId, ds, HMMlist);
          } else if (cpid.equals(ABNEngine)) {
            addItemToMap(sentenceId, ds, ABNlist);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new AnalysisEngineProcessException();
    }

  }
}
