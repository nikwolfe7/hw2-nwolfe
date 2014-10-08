
/* First created by JCasGen Wed Oct 08 01:32:13 EDT 2014 */
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
 * Updated by JCasGen Wed Oct 08 01:32:14 EDT 2014
 * @generated */
public class namedEntityAnnotation_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (namedEntityAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = namedEntityAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new namedEntityAnnotation(addr, namedEntityAnnotation_Type.this);
  			   namedEntityAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new namedEntityAnnotation(addr, namedEntityAnnotation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = namedEntityAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.deiis.types.namedEntityAnnotation");
 
  /** @generated */
  final Feature casFeat_namedEntityString;
  /** @generated */
  final int     casFeatCode_namedEntityString;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getNamedEntityString(int addr) {
        if (featOkTst && casFeat_namedEntityString == null)
      jcas.throwFeatMissing("namedEntityString", "edu.cmu.deiis.types.namedEntityAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_namedEntityString);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setNamedEntityString(int addr, String v) {
        if (featOkTst && casFeat_namedEntityString == null)
      jcas.throwFeatMissing("namedEntityString", "edu.cmu.deiis.types.namedEntityAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_namedEntityString, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public namedEntityAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_namedEntityString = jcas.getRequiredFeatureDE(casType, "namedEntityString", "uima.cas.String", featOkTst);
    casFeatCode_namedEntityString  = (null == casFeat_namedEntityString) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_namedEntityString).getCode();

  }
}



    