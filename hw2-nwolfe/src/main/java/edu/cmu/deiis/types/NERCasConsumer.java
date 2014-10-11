package edu.cmu.deiis.types;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.uima.UIMA_IllegalArgumentException;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.jcas.tcas.Annotation;

public class NERCasConsumer extends CasConsumer_ImplBase {

  private PrintStream writer;

  private String HMMEngine = "edu.cmu.deiis.types.NamedEntityAnalysisEngine";

  private String ABNEngine = "edu.cmu.deiis.types.AbnerAnalysisEngine";

  private HashMap<String, ArrayList<NamedEntityAnnotation>> mergeList;

  @Override
  public void initialize() throws ResourceInitializationException {
    mergeList = new HashMap<String, ArrayList<NamedEntityAnnotation>>();
    try {
      this.writer = new PrintStream(new File(getConfigParameterValue(
              UIMATypeEnum.OUTPUT_FILE.getParam()).toString()));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new UIMA_IllegalArgumentException();
    }
  };

  @Override
  public void destroy() {
    this.writer.flush();
    this.writer.close();
  };

  protected void outputAnnotation(HashMap<String, ArrayList<NamedEntityAnnotation>> model) {
    for (String key : model.keySet()) {
      Iterable<NamedEntityAnnotation> iter = model.get(key);
      for (NamedEntityAnnotation nea : iter) {
        Integer begin = nea.getBegin();
        Integer end = nea.getEnd();
        String namedEntity = nea.getNamedEntityString();
        String output = key + "|" + begin.toString() + " " + end.toString() + "|" + namedEntity;
        this.writer.println(output);
      }
    }
  }

  // Rules for comparing annotations of the same sentence
  protected void compareAnnotations(String key, ArrayList<NamedEntityAnnotation> item1,
          ArrayList<NamedEntityAnnotation> item2) {
    if (item2 != null) {
      this.mergeList.put(key, item2);
    }
  }

  @Override
  public void processCas(CAS aCAS) throws ResourceProcessException {
    try {
      JCas jCas = aCAS.getJCas();
      FSIterator<TOP> fsIterator = jCas.getJFSIndexRepository().getAllIndexedFS(Sentence.type);
      HashMap<String, ArrayList<NamedEntityAnnotation>> HMMlist = new HashMap<String, ArrayList<NamedEntityAnnotation>>();
      HashMap<String, ArrayList<NamedEntityAnnotation>> ABNlist = new HashMap<String, ArrayList<NamedEntityAnnotation>>();

      ArrayList<String> sentences = new ArrayList<String>();

      if (fsIterator.hasNext()) {
        Sentence sentence = (Sentence) fsIterator.next();
        String sentenceId = sentence.getSentenceId();
        sentences.add(sentenceId);

        // Divide annotations into lists...
        Iterable<Annotation> iter = jCas.getAnnotationIndex(NamedEntityAnnotation.type);
        for (Annotation a : iter) {
          NamedEntityAnnotation nea = (NamedEntityAnnotation) a;
          String cpid = nea.getCasProcessorId();
          if (cpid.equals(HMMEngine)) {
            if (!HMMlist.containsKey(sentenceId)) {
              HMMlist.put(sentenceId, (new ArrayList<NamedEntityAnnotation>()));
            }
            HMMlist.get(sentenceId).add(nea);
          } else if (cpid.equals(ABNEngine)) {
            if (!ABNlist.containsKey(sentenceId)) {
              ABNlist.put(sentenceId, (new ArrayList<NamedEntityAnnotation>()));
            }
            ABNlist.get(sentenceId).add(nea);
          }
        }

        // Compare annotations for each sentence....
        if (ABNlist.containsKey(sentenceId)) {
          //compareAnnotations(sentenceId, HMMlist.get(sentenceId), ABNlist.get(sentenceId));
        }
        
        // outputAnnotation(HMMlist);
        outputAnnotation(this.mergeList);
      }

    } catch (Exception e) {
      e.printStackTrace();
      throw new AnalysisEngineProcessException();
    }

  }

}
