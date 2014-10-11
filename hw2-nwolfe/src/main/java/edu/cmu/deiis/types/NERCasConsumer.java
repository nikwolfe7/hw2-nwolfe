package edu.cmu.deiis.types;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

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

  @Override
  public void initialize() throws ResourceInitializationException {
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

  @Override
  public void processCas(CAS aCAS) throws ResourceProcessException {
    try {
      JCas jCas = aCAS.getJCas();
      FSIterator<TOP> fsIterator = jCas.getJFSIndexRepository().getAllIndexedFS(Sentence.type);
      if (fsIterator.hasNext()) {
        Sentence sentence = (Sentence) fsIterator.next();
        String sentenceId = sentence.getSentenceId();
        Iterable<Annotation> iter = jCas.getAnnotationIndex(NamedEntityAnnotation.type);
        for (Annotation a : iter) {
          NamedEntityAnnotation nea = (NamedEntityAnnotation) a;
          Integer begin = nea.getBegin();
          Integer end = nea.getEnd();
          String namedEntity = nea.getNamedEntityString();
          String output = sentenceId + "|" + begin.toString() + " " + end.toString() + "|"
                  + namedEntity;
          this.writer.println(output);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new AnalysisEngineProcessException();
    }

  }

}
