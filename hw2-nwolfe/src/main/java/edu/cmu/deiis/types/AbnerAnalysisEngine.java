package edu.cmu.deiis.types;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import abner.Tagger;

public class AbnerAnalysisEngine extends JCasAnnotator_ImplBase {

  private Tagger abnerTag;

  /**
   *  marker for NOT a gene mention...
   */
  private final String OTHER = "O";

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    abnerTag = new Tagger(Tagger.BIOCREATIVE);
  }
  
  /**
   *  The CAS processor id
   * @return
   */
  private String getMyId() {
    return this.getClass().getName();
  }

  @SuppressWarnings("unchecked")
  @Override
  public void process(JCas jCas) throws AnalysisEngineProcessException {
    String data = jCas.getSofaDataString();
    Iterable<String[][]> entities = abnerTag.getSegments(data);
    
    // Get segments in the strings...
    for (String[][] seg : entities) {
      Integer start = 0;
      Integer stop = 0;
      
      for (int i = 0; i < seg[0].length; i++) {
        String text = seg[0][i];
        String label = seg[1][i];
        stop = text.replaceAll("\\s", "").length();
        
        // check if label is a gene mention
        if (!label.equals(OTHER)) {
          
          //System.out.println(text + " " + label);
          NamedEntityAnnotation nea = new NamedEntityAnnotation(jCas, start, (start + stop - 1));
          String cpid = this.getMyId();
          Double confidence = 0.5; // not provided by this model
          
          // add annotation details to Annotator
          nea.setNamedEntityString(text);
          nea.setCasProcessorId(cpid);
          nea.setConfidence(confidence);
          nea.addToIndexes();
        }
        start += stop;
      }
    }

  }

}
