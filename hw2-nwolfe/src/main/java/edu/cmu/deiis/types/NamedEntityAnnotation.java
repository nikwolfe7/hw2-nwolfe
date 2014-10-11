

/* First created by JCasGen Wed Oct 08 02:32:15 EDT 2014 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Fri Oct 10 21:29:46 EDT 2014
 * XML source: /home/nwolfe/git/hw2-nwolfe/hw2-nwolfe/src/main/resources/AggregateAnalysisEngineDescriptor.xml
 * @generated */
public class NamedEntityAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NamedEntityAnnotation.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected NamedEntityAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public NamedEntityAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public NamedEntityAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public NamedEntityAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: NamedEntityString

  /** getter for NamedEntityString - gets 
   * @generated
   * @return value of the feature 
   */
  public String getNamedEntityString() {
    if (NamedEntityAnnotation_Type.featOkTst && ((NamedEntityAnnotation_Type)jcasType).casFeat_NamedEntityString == null)
      jcasType.jcas.throwFeatMissing("NamedEntityString", "edu.cmu.deiis.types.NamedEntityAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntityAnnotation_Type)jcasType).casFeatCode_NamedEntityString);}
    
  /** setter for NamedEntityString - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setNamedEntityString(String v) {
    if (NamedEntityAnnotation_Type.featOkTst && ((NamedEntityAnnotation_Type)jcasType).casFeat_NamedEntityString == null)
      jcasType.jcas.throwFeatMissing("NamedEntityString", "edu.cmu.deiis.types.NamedEntityAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntityAnnotation_Type)jcasType).casFeatCode_NamedEntityString, v);}    
  }

    