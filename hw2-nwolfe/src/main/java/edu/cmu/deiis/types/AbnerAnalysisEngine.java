package edu.cmu.deiis.types;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import abner.Tagger;

public class AbnerAnalysisEngine extends JCasAnnotator_ImplBase {

  private Tagger abnerTag;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    abnerTag = new Tagger(Tagger.BIOCREATIVE);
  }

  @Override
  public void process(JCas jCas) throws AnalysisEngineProcessException {
    String data = jCas.getDocumentText();
    
  }

}
