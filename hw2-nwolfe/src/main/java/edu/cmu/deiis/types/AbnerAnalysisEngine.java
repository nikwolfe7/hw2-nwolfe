package edu.cmu.deiis.types;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import type.UIMATypeEnum;

public class AbnerAnalysisEngine extends JCasAnnotator_ImplBase {

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    // get HMM modelFile
    String filename = aContext.getConfigParameterValue(UIMATypeEnum.HMM_MODEL.getParam())
            .toString();
    // modelHMM = (NBestChunker) AbstractExternalizable.readResourceObject(filename);
  }

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    // TODO Auto-generated method stub

  }

}
