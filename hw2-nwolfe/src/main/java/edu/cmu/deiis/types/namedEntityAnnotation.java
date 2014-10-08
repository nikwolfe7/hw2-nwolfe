

/* First created by JCasGen Wed Oct 08 01:32:13 EDT 2014 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Wed Oct 08 01:32:14 EDT 2014
 * XML source: /home/nwolfe/git/hw2-nwolfe/hw2-nwolfe/src/main/resources/descriptors/deiis_types.xml
 * @generated */
public class namedEntityAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(namedEntityAnnotation.class);
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
  protected namedEntityAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public namedEntityAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public namedEntityAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public namedEntityAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: namedEntityString

  /** getter for namedEntityString - gets 
   * @generated
   * @return value of the feature 
   */
  public String getNamedEntityString() {
    if (namedEntityAnnotation_Type.featOkTst && ((namedEntityAnnotation_Type)jcasType).casFeat_namedEntityString == null)
      jcasType.jcas.throwFeatMissing("namedEntityString", "edu.cmu.deiis.types.namedEntityAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((namedEntityAnnotation_Type)jcasType).casFeatCode_namedEntityString);}
    
  /** setter for namedEntityString - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setNamedEntityString(String v) {
    if (namedEntityAnnotation_Type.featOkTst && ((namedEntityAnnotation_Type)jcasType).casFeat_namedEntityString == null)
      jcasType.jcas.throwFeatMissing("namedEntityString", "edu.cmu.deiis.types.namedEntityAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((namedEntityAnnotation_Type)jcasType).casFeatCode_namedEntityString, v);}    
  }

    