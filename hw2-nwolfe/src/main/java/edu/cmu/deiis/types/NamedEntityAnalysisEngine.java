/**
 * 
 */
package edu.cmu.deiis.types;

import java.io.IOException;

import org.apache.uima.UIMARuntimeException;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunking;
import com.aliasi.chunk.NBestChunker;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.ScoredObject;

import type.UIMATypeEnum;

/**
 * @author nwolfe
 *
 */
public class NamedEntityAnalysisEngine extends JCasAnnotator_ImplBase {

  // HMM model
  NBestChunker modelHMM;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    try {
      // get HMM modelFile
      String filename = aContext.getConfigParameterValue(UIMATypeEnum.HMM_MODEL.getParam())
              .toString();
      modelHMM = (NBestChunker) AbstractExternalizable.readResourceObject(filename);
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
    String sentence = jCas.getSofaDataString();
    ScoredObject<Chunking> chunkSet = modelHMM.nBest(sentence.toCharArray(), 0, sentence.length(),
            MAX_N_BEST_CHUNKS).next();

    for (Chunk c : chunkSet.getObject().chunkSet()) {
      // get all indices...
      Integer begin = sentence.substring(0, c.start()).replaceAll("\\s", "").length();
      Integer end = sentence.substring(0, c.end()).replaceAll("\\s", "").length() - 1;

      // Extract Annotation details
      NamedEntityAnnotation nea = new NamedEntityAnnotation(jCas, begin, end);
      String namedEntity = sentence.substring(c.start(), c.end());
      String cpid = this.getClass().getName();
      Double confidence = chunkSet.score();

      // Add Annotation details to Annotator
      nea.setNamedEntityString(namedEntity);
      nea.setCasProcessorId(cpid);
      nea.setConfidence(confidence);
      nea.addToIndexes();
    }

  }
}
