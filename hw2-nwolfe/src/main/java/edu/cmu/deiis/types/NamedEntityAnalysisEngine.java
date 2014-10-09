/**
 * 
 */
package edu.cmu.deiis.types;

import java.io.File;
import java.io.IOException;

import org.apache.uima.UIMARuntimeException;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.resource.ResourceInitializationException;

import com.aliasi.chunk.ConfidenceChunker;
import com.aliasi.util.AbstractExternalizable;

import type.UIMATypeEnum;

/**
 * @author nwolfe
 *
 */
public class NamedEntityAnalysisEngine extends JCasAnnotator_ImplBase {

  // HMM model
  ConfidenceChunker modelHMM;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    try {
      // get HMM modelFile
      String filename = aContext.getConfigParameterValue(UIMATypeEnum.HMM_MODEL.getParam())
              .toString();
      File modelHMMFile = new File(NamedEntityAnalysisEngine.class.getClassLoader()
              .getResource(filename).getFile());
      modelHMM = (ConfidenceChunker) AbstractExternalizable.readObject(modelHMMFile);
    } catch (IOException e) {
      e.printStackTrace();
      throw new UIMARuntimeException();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      throw new UIMARuntimeException();
    }
  };

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.apache.uima.analysis_component.JCasAnnotator_ImplBase#process(org.apache.uima.jcas.JCas)
   */
  @Override
  public void process(JCas jCas) throws AnalysisEngineProcessException {
    // use N best chunks
    Integer MAX_N_BEST_CHUNKS = 100;
    @SuppressWarnings("rawtypes")
    FSIterator fsIterator = jCas.getJFSIndexRepository().getAllIndexedFS(Sentence.type);
   
    //FSIterator<NamedEntityAnnotation> fsIterator = jCas.getJFSIndexRepository().getFSIndexRepository().getAllIndexedFS(NamedEntityAnnotation);
    if (fsIterator.hasNext()) {
      NamedEntityAnnotation sentence = fsIterator.next();
    }

  }

}
