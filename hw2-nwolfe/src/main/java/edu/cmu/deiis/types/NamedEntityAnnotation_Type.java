
/* First created by JCasGen Wed Oct 08 02:32:15 EDT 2014 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** 
 * Updated by JCasGen Fri Oct 10 21:29:46 EDT 2014
 * @generated */
public class NamedEntityAnnotation_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (NamedEntityAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = NamedEntityAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new NamedEntityAnnotation(addr, NamedEntityAnnotation_Type.this);
  			   NamedEntityAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new NamedEntityAnnotation(addr, NamedEntityAnnotation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = NamedEntityAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.deiis.types.NamedEntityAnnotation");
 
  /** @generated */
  final Feature casFeat_NamedEntityString;
  /** @generated */
  final int     casFeatCode_NamedEntityString;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getNamedEntityString(int addr) {
        if (featOkTst && casFeat_NamedEntityString == null)
      jcas.throwFeatMissing("NamedEntityString", "edu.cmu.deiis.types.NamedEntityAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_NamedEntityString);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setNamedEntityString(int addr, String v) {
        if (featOkTst && casFeat_NamedEntityString == null)
      jcas.throwFeatMissing("NamedEntityString", "edu.cmu.deiis.types.NamedEntityAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_NamedEntityString, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public NamedEntityAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_NamedEntityString = jcas.getRequiredFeatureDE(casType, "NamedEntityString", "uima.cas.String", featOkTst);
    casFeatCode_NamedEntityString  = (null == casFeat_NamedEntityString) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_NamedEntityString).getCode();

  }
}



    